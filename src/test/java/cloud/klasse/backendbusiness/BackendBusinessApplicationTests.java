package cloud.klasse.backendbusiness;

import cloud.klasse.backendbusiness.student.CreateStudentModel;
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
import java.util.Arrays;
import java.util.Base64;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

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
        final var createUserModel = new CreateStudentModel();
        createUserModel.setUserName("Klaus");
        createUserModel.setNickName("klaus");
        createUserModel.setPassword("It's super-cereal");
        final var createUserResponse = restTemplate.postForEntity("/user", createUserModel, User.class);
        assertThat(createUserResponse.getStatusCode()).isEqualTo(HttpStatus.CREATED);

        // Check created user data provided by the API
        final var user = createUserResponse.getBody();
        assertThat(user).isNotNull();
        assertThat(user.getId()).isEqualTo(1);
        assertThat(user.getUserName()).isEqualTo("Klaus");

        // Login using the provided credentials but no access token => Should be accepted
        final var loginHeaders = new HttpHeaders();
        final var usernameAndPassword = "Klaus:It's super-cereal".getBytes(StandardCharsets.UTF_8);
        loginHeaders.set(HttpHeaders.AUTHORIZATION, "Basic " + base64Encoder.encodeToString(usernameAndPassword));
        final var loginRequest = new HttpEntity<>(loginHeaders);
        final var loginResponse = restTemplate.exchange("/login", HttpMethod.GET, loginRequest, User.class);
        assertThat(loginResponse.getStatusCode()).isEqualTo(HttpStatus.OK);

        // Extract access token from Cookie
        final var cookies = Cookie.parse(loginResponse.getHeaders().get(HttpHeaders.SET_COOKIE));
        final var accessToken = cookies.stream().filter(cookie -> "token".equalsIgnoreCase(cookie.name)).findFirst().orElse(null);
        assertThat(accessToken).isNotNull();
        assertThat(accessToken.sameSite).isNotNull();
        assertThat(accessToken.secure).isTrue();
        assertThat(accessToken.httpOnly).isTrue();

        // Attempt to access protected resource WITH access token => Should be accepted
        final var getUserHeaders = new HttpHeaders();
        getUserHeaders.set(HttpHeaders.COOKIE, String.format("token=%s", accessToken.value));
        final var getUserRequest = new HttpEntity<>(getUserHeaders);
        final var getUserResponse = restTemplate.exchange("/user/1", HttpMethod.GET, getUserRequest, User.class);
        assertThat(getUserResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    protected static class Cookie {

        private String name;
        private String value;
        private String sameSite;
        private boolean secure;
        private boolean httpOnly;

        public static List<Cookie> parse(final List<String> cookies) {
            if (cookies == null || cookies.isEmpty()) return Collections.emptyList();
            return cookies.stream().map(Cookie::parse).collect(Collectors.toUnmodifiableList());
        }

        public static Cookie parse(final String s) {
            if (s == null) return null;
            System.out.println("Parsing Cookie-String: " + s);
            final var cookie = new Cookie();
            final var cookieAttributes = s.split(";");
            final var nameValuePair = cookieAttributes[0].split("=");
            cookie.name = nameValuePair[0];
            cookie.value = nameValuePair.length > 1 ? nameValuePair[1] : null;
            System.out.println("New cookie: " + cookie.name + " = " + cookie.value);
            final var attributes = Arrays.stream(cookieAttributes).map(String::trim).skip(1).collect(Collectors.toList());
            for (final String attribute : attributes) {
                if ("secure".equalsIgnoreCase(attribute)) {
                    cookie.secure = true;
                } else if ("HttpOnly".equalsIgnoreCase(attribute)) {
                    cookie.httpOnly = true;
                } else {
                    System.out.println("Key-Value Pair: " + attribute);
                    final var keyValue = attribute.split("=", 2);
                    final var attributeName = keyValue[0];
                    final var attributeValue = keyValue.length > 1 ? keyValue[1] : null;
                    if ("SameSite".equalsIgnoreCase(attributeName)) {
                        cookie.sameSite = attributeValue;
                    }
                }
            }
            return cookie;
        }

    }

}
