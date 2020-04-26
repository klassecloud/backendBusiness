package cloud.klasse.backendbusiness.jwt;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
public class JwtAuthenticationFilter extends AbstractPreAuthenticatedProcessingFilter {

    public static final SimpleGrantedAuthority TEACHER = new SimpleGrantedAuthority("TEACHER");

    private final TokenVerifier verifier;

    public JwtAuthenticationFilter(final TokenVerifier verifier) {
        this.verifier = verifier;
        this.setAuthenticationManager(authenticationRequest -> new PreAuthenticatedAuthenticationToken(
                authenticationRequest.getPrincipal(),
                authenticationRequest.getCredentials(),
                List.of(TEACHER)
        ));
    }

    @Override
    protected Object getPreAuthenticatedPrincipal(HttpServletRequest request) {
        final String bearerToken = extractBearerToken(request);

        if (bearerToken == null) return null;

        final var jwt = verifier.verifyToken(bearerToken);

        return jwt.getBody().getSubject();
    }

    @Override
    protected Object getPreAuthenticatedCredentials(HttpServletRequest request) {
        return extractBearerToken(request);
    }

    private String extractBearerToken(HttpServletRequest httpServletRequest) {
        final var authorization = searchAccessToken(httpServletRequest.getCookies());

        if (authorization == null) {
            return null;
        }

        return StringUtils
                .removeStart(authorization, "Bearer")
                .trim();
    }

    private String searchAccessToken(final Cookie[] cookies) {
        if (cookies == null) return null;
        for (final Cookie cookie : cookies) {
            if (cookie != null && "token".equalsIgnoreCase(cookie.getName())) {
                return cookie.getValue();
            }
        }
        return null;
    }

}
