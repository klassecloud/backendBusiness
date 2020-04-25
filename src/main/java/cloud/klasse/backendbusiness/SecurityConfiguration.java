package cloud.klasse.backendbusiness;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;

public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final KeyPair keyPair;

    // TODO Inject key from environment?
    public SecurityConfiguration() throws NoSuchAlgorithmException {
        this.keyPair = KeyPairGenerator.getInstance("RSA").generateKeyPair();
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

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests(authorize -> authorize
                .anyRequest().permitAll())
                .csrf().disable()
                .formLogin().disable();
    }

}
