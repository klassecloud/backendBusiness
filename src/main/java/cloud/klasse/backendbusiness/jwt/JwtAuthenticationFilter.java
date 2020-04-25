package cloud.klasse.backendbusiness.jwt;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.preauth.RequestHeaderAuthenticationFilter;


import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@RequiredArgsConstructor
public class JwtAuthenticationFilter extends RequestHeaderAuthenticationFilter {

    private final TokenVerifier verifier;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        final var token = StringUtils.removeStart(((HttpServletRequest) request)
                .getHeader(AUTHORIZATION), "Bearer").trim();

        verifier.verifiedToken(token);
        SecurityContextHolder.getContext()
                .setAuthentication(new TestingAuthenticationToken("", "", ""));
    }


}
