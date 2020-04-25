package cloud.klasse.backendbusiness.jwt;

import cloud.klasse.backendbusiness.user.TokenIssuer;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.PrivateKey;

@Service
@RequiredArgsConstructor
public class JwtTokenIssuer implements TokenIssuer {

    private final PrivateKey key;

    @Override
    public String createToken(String username) {
        return Jwts.builder().setSubject(username).signWith(key).compact();
    }

}
