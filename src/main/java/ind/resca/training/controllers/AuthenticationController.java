package ind.resca.training.controllers;

import ind.resca.training.config.TokenService;
import ind.resca.training.models.LoginForm;
import ind.resca.training.utils.dtos.TokenDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
@Profile(value = {"prod", "test"})
public class AuthenticationController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    TokenService tokenService;

    @PostMapping
    public ResponseEntity<?> authenticate(
            @RequestBody @Valid LoginForm loginForm
    ) {
        UsernamePasswordAuthenticationToken loginData = loginForm.convert();
        try {
            Authentication auth = authenticationManager.authenticate(loginData);
            String jwt = tokenService.genToken(auth);
            return ResponseEntity.ok(new TokenDto(jwt, "Bearer"));
        } catch (AuthenticationException e) {
            return ResponseEntity.badRequest().build();
        }

    }
}
