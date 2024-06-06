package inlovepet.app.backend.controllers;

import inlovepet.app.backend.dtos.LoginDTO;
import inlovepet.app.backend.dtos.RegisterDTO;
import inlovepet.app.backend.useCases.UserUseCases;
import jakarta.transaction.Transactional;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/auth")
public class AuthenticationController {

    private UserUseCases userUseCases;

    public AuthenticationController(UserUseCases useCase) {
        this.userUseCases = useCase;
    }

    @PostMapping("/login")
    @Transactional
    public ResponseEntity loginUser(@RequestBody LoginDTO data) {
        return userUseCases.login(data);
    }

    @PostMapping("/register")
    @Transactional
    public ResponseEntity registerNewUser(@RequestBody RegisterDTO data) {
        return userUseCases.signup(data);
    }
}
