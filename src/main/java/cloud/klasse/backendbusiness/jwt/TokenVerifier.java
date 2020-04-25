package cloud.klasse.backendbusiness.jwt;

import io.jsonwebtoken.Jwt;

public interface TokenVerifier {

    Jwt verifiedToken(String token);
}
