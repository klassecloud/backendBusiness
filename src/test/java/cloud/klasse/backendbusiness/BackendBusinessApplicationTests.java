package cloud.klasse.backendbusiness;

import cloud.klasse.backendbusiness.user.CreateUserModel;
import cloud.klasse.backendbusiness.user.User;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.client.RootUriTemplateHandler;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.TestPropertySource;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(properties = "spring.jpa.hibernate.ddl-auto=update")
class BackendBusinessApplicationTests {

    @LocalServerPort
    int serverPort;

    private final Base64.Encoder base64Encoder = Base64.getEncoder().withoutPadding();

    private final TestRestTemplate restTemplate = new TestRestTemplate(TestRestTemplate.HttpClientOption.ENABLE_COOKIES);

    @BeforeAll
    void setUp() {
        restTemplate.setUriTemplateHandler(new RootUriTemplateHandler(String.format("http://localhost:%s", serverPort)));
    }

    @Test
    void securitySmokeTest() {
        // Attempt to access protected resource without access token => Should be rejected
        final var unauthorizedResponse = restTemplate.getForEntity("/user/1", User.class);
        assertThat(unauthorizedResponse.getStatusCode()).isEqualTo(HttpStatus.FORBIDDEN);

        // Attempt to create user without access token => Should be accepted
        final var createUserModel = new CreateUserModel();
        createUserModel.setUserName("Klaus");
        createUserModel.setNickName("klaus");
        createUserModel.setPassword("It's super-cereal");
        final var createUserResponse = restTemplate.postForEntity("/register", createUserModel, User.class);
        assertThat(createUserResponse.getStatusCode()).isEqualTo(HttpStatus.CREATED);

        // Check created user data provided by the API
        final var user = createUserResponse.getBody();
        assertThat(user).isNotNull();
        assertThat(user.getId()).isEqualTo(1);
        assertThat(user.getUserName()).isEqualTo("Klaus");
        assertThat(user.getNickName()).isEqualTo("klaus");

        // Login using the provided credentials but no access token => Should be accepted
        final var loginHeaders = new HttpHeaders();
        final var usernameAndPassword = "Klaus:It's super-cereal".getBytes(StandardCharsets.UTF_8);
        loginHeaders.set(HttpHeaders.AUTHORIZATION, "Basic " + base64Encoder.encodeToString(usernameAndPassword));
        final var loginRequest = new HttpEntity<>(loginHeaders);
        final var loginResponse = restTemplate.exchange("/login", HttpMethod.GET, loginRequest, User.class);
        assertThat(loginResponse.getStatusCode()).isEqualTo(HttpStatus.OK);

        // Extract access token from Cookie
        final var cookies = loginResponse.getHeaders().get(HttpHeaders.SET_COOKIE);
        final var accessToken = findAccessToken(cookies);
        assertThat(accessToken).isNotEmpty();

        // Attempt to access protected resource WITH access token => Should be accepted
        final var getUserHeaders = new HttpHeaders();
        getUserHeaders.set(HttpHeaders.COOKIE, String.format("token=%s", accessToken));
        final var getUserRequest = new HttpEntity<>(getUserHeaders);
        final var getUserResponse = restTemplate.exchange("/user/1", HttpMethod.GET, getUserRequest, User.class);
        assertThat(getUserResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    private String findAccessToken(final List<String> cookies) {
        if (null == cookies || cookies.isEmpty()) return "";
        for (final String cookie : cookies) {
            final var keyValuePair = cookie.split(";", 2)[0].split("=", 2);
            if (!"token".equalsIgnoreCase(keyValuePair[0])) continue;
            return keyValuePair[1];
        }
        return "";
    }

}
