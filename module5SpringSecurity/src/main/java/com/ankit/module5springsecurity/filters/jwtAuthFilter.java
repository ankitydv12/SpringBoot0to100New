package com.ankit.module5springsecurity.filters;

import com.ankit.module5springsecurity.entities.User;
import com.ankit.module5springsecurity.services.Impl.JwtService;
import com.ankit.module5springsecurity.services.Impl.UserServiceImpl;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.hibernate.boot.internal.Abstract;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import java.io.IOException;
@Component
@RequiredArgsConstructor
public class jwtAuthFilter extends OncePerRequestFilter {
    private final JwtService jwtService;
    private final UserServiceImpl userService;

    @Autowired
    @Qualifier("handlerExceptionResolver")
    private   HandlerExceptionResolver exceptionResolver;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            final String requestToken = request.getHeader("Authorization");
            // Requests without a JWT should continue through the chain. This is
            // expected for public endpoints such as /auth/**.
            if (requestToken == null || !requestToken.startsWith("Bearer "))
            {
               filterChain.doFilter(request,response);
               return;
            }

            String token = requestToken.substring("Bearer ".length()).trim();
            if (token.isEmpty()) {
                filterChain.doFilter(request, response);
                return;
            }

            Long userId = jwtService.getIdFromJwtToken(token);
            if (userId != null && SecurityContextHolder.getContext().getAuthentication() == null) {
               User user =  userService.getById(userId);
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user,null,null);
                authenticationToken.setDetails(
                        new WebAuthenticationDetailsSource().buildDetails(request)
                );
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
            filterChain.doFilter(request,response);
        } catch (Exception e) {
            exceptionResolver.resolveException(request,response,null,e);
        }
    }
}
