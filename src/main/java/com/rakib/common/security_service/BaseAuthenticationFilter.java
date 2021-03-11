package com.rakib.common.security_service;

import com.rakib.common.config.AppConstant;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;


@Component
public class BaseAuthenticationFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest,
                                    HttpServletResponse httpServletResponse,
                                    FilterChain filterChain) throws ServletException, IOException {

        if (checkJWTToken(httpServletRequest, httpServletResponse)) {
            Claims claims = validateToken(httpServletRequest);
            if (Objects.nonNull(claims.get("authorities"))) {
                setUpSpringAuthentication(claims);
            } else {
                httpServletResponse.sendError(HttpServletResponse.SC_FORBIDDEN, "Token is invalid.");
            }
        }
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        switch (request.getServletPath()) {
            case "/v1/auth/jwt/login":
                return true;
            default:
                return false;
        }
    }

    private void setUpSpringAuthentication(Claims claims) {
        String authorities = String.valueOf(claims.get("authorities"));
        UsernamePasswordAuthenticationToken localAuthentication =
                new UsernamePasswordAuthenticationToken(claims.getIssuer(), null,
                        AuthorityUtils.commaSeparatedStringToAuthorityList(authorities));
        SecurityContextHolder.getContext().setAuthentication(localAuthentication);
    }

    private Claims validateToken(HttpServletRequest request) {
        String jwtToken = request.getHeader(AppConstant.HEADER).replace(AppConstant.PREFIX, "");
        return Jwts.parserBuilder()
                .setSigningKey(AppConstant.SECURITY_SECRATE.getBytes())
                .build()
                .parseClaimsJws(jwtToken)
                .getBody();
    }

    private boolean checkJWTToken(HttpServletRequest request, HttpServletResponse response) {
        String authenticationHeader = request.getHeader(AppConstant.HEADER);
        return Objects.nonNull(authenticationHeader) && authenticationHeader.startsWith(AppConstant.PREFIX);
    }
}
