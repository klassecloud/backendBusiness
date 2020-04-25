package cloud.klasse.backendbusiness.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Service;

import java.security.PublicKey;

@Service
public class JwtTokenVerifier implements TokenVerifier {

    private final JwtParser parser;

    public JwtTokenVerifier(PublicKey publicKey) {
        this.parser = Jwts.parserBuilder().setSigningKey(publicKey).build();
    }

    @Override
    public Jws<Claims> verifyToken(String token) {
        return parser.parseClaimsJws(token);
    }
}
