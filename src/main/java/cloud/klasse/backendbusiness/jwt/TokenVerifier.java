package cloud.klasse.backendbusiness.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;

public interface TokenVerifier {

    Jws<Claims> verifyToken(String token);

}
