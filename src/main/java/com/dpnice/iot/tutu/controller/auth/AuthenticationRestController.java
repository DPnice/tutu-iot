package com.dpnice.iot.tutu.controller.auth;

import com.dpnice.iot.tutu.config.jwt.JWTFilter;
import com.dpnice.iot.tutu.config.jwt.TokenProvider;
import com.dpnice.iot.tutu.model.JWTToken;
import com.dpnice.iot.tutu.model.User;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author DPnice
 */
@RestController
@RequestMapping
public class AuthenticationRestController {

    private final TokenProvider tokenProvider;


    private AuthenticationManager authenticationManager;

    public AuthenticationRestController(TokenProvider tokenProvider,
                                        AuthenticationManager authenticationManager) {
        this.tokenProvider = tokenProvider;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/authenticate")
    public ResponseEntity authorize(@Valid @RequestBody User loginDto) {

        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword());
        try {
            Authentication authentication = authenticationManager.authenticate(authenticationToken);
            SecurityContextHolder.getContext().setAuthentication(authentication);

            boolean rememberMe = (loginDto.isRememberMe() == null) ? false : loginDto.isRememberMe();
            String jwt = tokenProvider.createToken(authentication, rememberMe);

            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.add(JWTFilter.AUTHORIZATION_HEADER, "Bearer " + jwt);
            return new ResponseEntity<>(new JWTToken(jwt), httpHeaders, HttpStatus.OK);
        } catch (BadCredentialsException authentication) {

            return new ResponseEntity<>("Login failed", HttpStatus.UNAUTHORIZED);
        }
    }

}
