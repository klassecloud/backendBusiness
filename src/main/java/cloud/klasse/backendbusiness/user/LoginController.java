package cloud.klasse.backendbusiness.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.Base64;

@Slf4j
@RestController
@RequiredArgsConstructor
public class LoginController {

    private final TokenIssuer tokenIssuer;

    private final PasswordEncoder passwordEncoder;

    private final UserRepository userRepository;

    @GetMapping("/login")
    public ResponseEntity<User> login(@RequestHeader(HttpHeaders.AUTHORIZATION) final String authorization) {
        // TODO Add some validations to this chain of string manipulations...
        log.info("Authorization-Header: {}", authorization);
        final var credentials = new String(Base64.getDecoder().decode(authorization.split(" ", 2)[1])).split(":", 2);
        final var username = credentials[0];
        final var password = credentials[1];
        final var user = userRepository.findByUserName(username);

        if (!passwordEncoder.matches(password, user.getPassword())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        final var token = tokenIssuer.createToken(username);

        return ResponseEntity
                .ok()
                // TODO Set SameSite=strict before merge
                .header(HttpHeaders.SET_COOKIE, String.format("token=%s; HttpOnly; SameSite=lax; secure", token))
                // TODO: Do not return the password. It's useful but not secure :)
                .body(user);
    }

}
