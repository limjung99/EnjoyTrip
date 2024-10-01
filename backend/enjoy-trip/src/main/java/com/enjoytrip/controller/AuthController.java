package com.enjoytrip.controller;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.enjoytrip.dto.auth.LoginResponse;
import com.enjoytrip.exception.TokenInvalidException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.enjoytrip.dto.auth.Login;
import com.enjoytrip.dto.auth.Token;
import com.enjoytrip.dto.auth.User;
import com.enjoytrip.filter.JwtFilter;
import com.enjoytrip.service.AuthService;
import com.enjoytrip.util.TokenProvider;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequestMapping("/api/v1/auth")
@RestController
@Slf4j
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;
    private final TokenProvider tokenProvider;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/token")
    public ResponseEntity<?> refresh() {
        Authentication authentication = SecurityContextHolder.getContext()
                .getAuthentication();
        Token token = tokenProvider.createToken(authentication);
        Map<String,String> accessToken = new HashMap<>();
        accessToken.put("token",token.getToken());

        return ResponseEntity.status(HttpStatus.OK).body(accessToken);
    }

    @GetMapping("/health")
    public ResponseEntity<?> health() {
        return ResponseEntity.status(HttpStatus.OK).body("app start!");
    }

    @PostMapping("/users")
    public ResponseEntity<?> signup(@RequestBody User user) {
        try {
            authService.createUser(user);
            // authentication context
            Authentication authentication = new UsernamePasswordAuthenticationToken(user.getId(),
                    user.getPassword());
            Token tokenPair = tokenProvider.createToken(authentication);

            return ResponseEntity.status(HttpStatus.OK).body(tokenPair);
        } catch (SQLException e) {
            log.debug(e.getMessage());
            return ResponseEntity.status(HttpStatus.OK).body(e.getMessage());
        }
    }

    @GetMapping("/users")
    public ResponseEntity<?> findAll() {
        try {
            List<User> userList = authService.findAll();
            return ResponseEntity.status(HttpStatus.OK).body(userList);
        } catch (SQLException e) {
            log.debug(e.getMessage());
            return ResponseEntity.status(HttpStatus.OK).body(e.getMessage());
        }
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<?> retrieveById(@PathVariable String id) {
        try {
            User user = authService.findById(id);
            return ResponseEntity.status(HttpStatus.OK).body(user);
        } catch (SQLException e) {
            log.debug(e.getMessage());
            return ResponseEntity.status(HttpStatus.OK).body(e.getMessage());
        }
    }

    @PostMapping("/users/login")
    public ResponseEntity<?> login(@RequestBody Login loginDto) {
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(loginDto.getId(), loginDto.getPassword());
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        Token tokenPair = tokenProvider.createToken(authentication);
        User loginUser = (User)authentication.getPrincipal();
        loginUser.setPassword("");
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(JwtFilter.AUTHORIZATION_HEADER, "Bearer " + tokenPair.getToken());
        LoginResponse response = new LoginResponse(loginUser,tokenPair);

        return ResponseEntity.status(HttpStatus.OK).headers(httpHeaders).body(response);
    }

    @DeleteMapping("/users")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> withdrawUser(Authentication authentication) {
        try {
            User contextUser = (User) authentication.getPrincipal();
            authService.deleteUser(contextUser);

            return ResponseEntity.status(HttpStatus.OK).body("Successfully deleted");
        } catch (SQLException e) {
            log.debug(e.getMessage());

            return ResponseEntity.status(HttpStatus.OK).body(e.getMessage());
        }
    }

    @PostMapping("/logout")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> logout(Authentication authentication) {
        User user = (User)authentication.getPrincipal();

        try {
            authService.logout(user);
            return ResponseEntity.status(HttpStatus.OK).body("");
        } catch (SQLException e){
            log.debug(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("");
        }
    }

    @PatchMapping("/users")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> updateUser(@RequestBody User updateUser,
                                        Authentication authentication) {
        try {
            User contextUser = (User) authentication.getPrincipal();

            if(!updateUser.getId().equals(contextUser.getId())){
                throw new TokenInvalidException();
            }

            authService.updateUser(updateUser);

            return ResponseEntity.status(HttpStatus.OK).body(updateUser);
        } catch (SQLException e) {
            log.debug(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        } catch (TokenInvalidException e){
            log.debug(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
