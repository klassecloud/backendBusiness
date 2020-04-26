package cloud.klasse.backendbusiness;

import cloud.klasse.backendbusiness.jwt.JwtAuthenticationFilter;
import cloud.klasse.backendbusiness.jwt.JwtTokenVerifier;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.io.IOException;
import java.security.*;
import java.util.List;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final KeyPair keyPair;

    // TODO Inject key from environment for sites available on the web!
    public SecurityConfiguration() throws GeneralSecurityException, IOException {
        final var keyStore = KeyStore.getInstance("PKCS12");
        try (final var inputStream = new ClassPathResource("keystore.p12").getInputStream()) {
            keyStore.load(inputStream, "changeit".toCharArray());
        }
        final var privateKey = (PrivateKey) keyStore.getKey("dev-key", "changeit".toCharArray());
        final var publicKey = keyStore.getCertificate("dev-key").getPublicKey();
        this.keyPair = new KeyPair(publicKey, privateKey);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    public PublicKey publicKey() {
        return keyPair.getPublic();
    }

    @Bean
    public PrivateKey privateKey() {
        return keyPair.getPrivate();
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of("http://localhost:8100"));
        configuration.setAllowedMethods(List.of("GET","POST", "PUT", "DELETE"));
        configuration.setAllowedHeaders(List.of("*"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .addFilterBefore(new JwtAuthenticationFilter(new JwtTokenVerifier(publicKey())), BasicAuthenticationFilter.class)

                .authorizeRequests(authorize -> authorize
                        .mvcMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                        .mvcMatchers(HttpMethod.POST, "/user").permitAll()
                        .mvcMatchers(HttpMethod.POST, "/teacher").permitAll()
                        .mvcMatchers(HttpMethod.GET, "/login").permitAll()
                        .anyRequest().hasAnyAuthority(JwtAuthenticationFilter.TEACHER.getAuthority())
                )

                .cors(Customizer.withDefaults())
                .csrf().disable()
                .formLogin().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

}
