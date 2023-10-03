package com.devdahcoder.security.filter;

import com.devdahcoder.security.jwt.JwtService;
import com.devdahcoder.user.repository.UserRepository;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.SignatureException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private static final Logger filterLogger = LoggerFactory.getLogger(JwtAuthenticationFilter.class);
    private final UserRepository userRepository;
    private final HandlerExceptionResolver handlerExceptionResolver;
    private static  final String HEADER_PREFIX = "Bearer ";
    private static final String REQUEST_HEADER = "Authorization";
    private final JwtService jwtService;

    public JwtAuthenticationFilter(UserRepository userRepository, @Qualifier("handlerExceptionResolver") HandlerExceptionResolver handlerExceptionResolver, JwtService jwtService) {

        this.userRepository = userRepository;
        this.handlerExceptionResolver = handlerExceptionResolver;
        this.jwtService = jwtService;

    }

    @Override
    protected void doFilterInternal(@NotNull HttpServletRequest request, @NotNull HttpServletResponse response, @NotNull FilterChain filterChain) throws ServletException, IOException {

        final String authenticationHeader = request.getHeader(REQUEST_HEADER);

        if (this.validateHeader(authenticationHeader)) {

            logger.info("Header does not contain " + REQUEST_HEADER);

            filterChain.doFilter(request, response);

            return;

        }

        try {

            // Validate and process JWT token here

            String jwtToken = this.extractJwtToken(authenticationHeader);

            String username = jwtService.extractUsername(jwtToken);

            if (this.isUserFound(username)) {

                filterLogger.info("User {} was found from JWT filter", username);

                UserDetails userDetails = userRepository.loadUserByUsername(username);

                if (jwtService.isTokenValid(jwtToken, userDetails)) {

                    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = this.createAuthentication(userDetails, request);

                    SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);

                }

            }

            filterChain.doFilter(request, response);

        } catch (ExpiredJwtException expiredJwtException) {

            // Handle token expiration here

            handlerExceptionResolver.resolveException(request, response, null, expiredJwtException);

        } catch(SignatureException signatureException) {

            // Handle token signature validation failure here

            handlerExceptionResolver.resolveException(request, response, null, signatureException);

        } catch (MalformedJwtException malformedJwtException) {

            // Handle malformed token here

            handlerExceptionResolver.resolveException(request, response, null, malformedJwtException);

        } catch (UnsupportedJwtException unsupportedJwtException) {

            // Handle unsupported token here

            handlerExceptionResolver.resolveException(request, response, null, unsupportedJwtException);

        } catch(IllegalArgumentException  illegalArgumentException) {

            // Handle JWT configuration issues here

            handlerExceptionResolver.resolveException(request, response, null, illegalArgumentException);

        } catch (JwtException jwtException) {

            // Handle other JWT-related exceptions

            handlerExceptionResolver.resolveException(request, response, null, jwtException);

        }

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

        return authenticationHeader == null || !authenticationHeader.startsWith(HEADER_PREFIX);

    }

}
