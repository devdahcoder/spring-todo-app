package com.devdahcoder.security.filter;

import com.devdahcoder.security.jwt.JwtService;
import com.devdahcoder.user.repository.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.jetbrains.annotations.NotNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final UserRepository userRepository;
    private final JwtService jwtService;

    public JwtAuthenticationFilter(UserRepository userRepository, JwtService jwtService) {

        this.userRepository = userRepository;
        this.jwtService = jwtService;

    }

    @Override
    protected void doFilterInternal(@NotNull HttpServletRequest request, @NotNull HttpServletResponse response, @NotNull FilterChain filterChain) throws ServletException, IOException {

        final String authenticationHeader = request.getHeader("Authorization");

        if (this.validateHeader(authenticationHeader)) {

            filterChain.doFilter(request, response);

            return;

        }

        String jwtToken = this.extractJwtToken(authenticationHeader);

        String username = jwtService.extractUsername(jwtToken);

        if (this.isUserFound(username)) {

            UserDetails userDetails = userRepository.loadUserByUsername(username);

            if (jwtService.isTokenValid(jwtToken, userDetails)) {

                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = this.createAuthentication(userDetails, request);

                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);

            }

        }

        filterChain.doFilter(request, response);

    }

    private @NotNull UsernamePasswordAuthenticationToken createAuthentication(UserDetails userDetails, HttpServletRequest request) {

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

        authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

        return authenticationToken;

    }

    public boolean isUserFound(String username) {

        return username != null && SecurityContextHolder.getContext().getAuthentication() == null;

    }

    public String extractJwtToken(@NotNull String authenticationHeader) {

        return authenticationHeader.substring(7);

    }

    public boolean validateHeader(String authenticationHeader) {

        return authenticationHeader == null || !authenticationHeader.startsWith("Bearer ");

    }

}
