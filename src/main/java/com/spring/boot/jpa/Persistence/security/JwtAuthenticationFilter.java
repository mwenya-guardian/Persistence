package com.spring.boot.jpa.Persistence.security;

import com.spring.boot.jpa.Persistence.services.CustomUserDetailsService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Service
@AllArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private JwtService jwtService;
    private ApplicationContext applicationContext;

    @Override
    protected void doFilterInternal(
            @NotNull HttpServletRequest request,
            @NotNull HttpServletResponse response,
            @NotNull FilterChain filterChain
    ) throws ServletException, IOException {
        String authHeader = request.getHeader("authorization");
        String username = null;
        String token = null;

        var securityContext = SecurityContextHolder.getContext().getAuthentication();

        if(authHeader != null && authHeader.startsWith("Bearer")){
            token = authHeader.split(" ")[1];
            username = jwtService.extractUserName(token);
        }
        //Generating token
        else if(securityContext != null && securityContext.isAuthenticated() && request.getRequestURI().equals("/api/login")){
            String name = request.getUserPrincipal().getName();
            String generatedToken = jwtService.generateToken(name, 8);
            response.addHeader("access_token", generatedToken);
            response.addHeader("Access-Control-Expose-Headers", "access_token");
        }
        //Validating toke
        if(username != null && securityContext == null){
            UserDetails userDetails = applicationContext.getBean(CustomUserDetailsService.class)
                    .loadUserByUsername(username);
            if(jwtService.validateToken(token, userDetails)){
                UsernamePasswordAuthenticationToken authenticationToken =
                        new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        }

        filterChain.doFilter(request, response);
    }
}
