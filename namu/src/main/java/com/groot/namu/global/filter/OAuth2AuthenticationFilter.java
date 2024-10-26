package com.groot.namu.global.filter;

import java.io.IOException;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.groot.namu.global.provider.OAuth2Provider;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class OAuth2AuthenticationFilter extends OncePerRequestFilter{
    private final OAuth2Provider oAuth2Provider;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        var authentication = SecurityContextHolder.getContext().getAuthentication();
        
        if (authentication instanceof OAuth2AuthenticationToken oauthToken) {
            String email = oAuth2Provider.getEmailFromOAuth2(oauthToken);

            if (email != null && !oAuth2Provider.isUserExists(email)) {
                response.sendRedirect("/signup?email=" + email);  // 회원가입 페이지로 이동
                return;
            } else {
                response.sendRedirect("/home");  // 홈 화면으로 이동
                return;
            }
        }

        filterChain.doFilter(request, response);
    }
}
