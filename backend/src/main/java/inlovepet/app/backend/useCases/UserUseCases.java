package inlovepet.app.backend.useCases;

import inlovepet.app.backend.dtos.LoginDTO;
import inlovepet.app.backend.dtos.RegisterDTO;
import inlovepet.app.backend.entities.users.User;
import inlovepet.app.backend.repositories.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserUseCases {

    private UserRepository userRepository;
    private AuthenticationManager authenticationManager;

    public UserUseCases(UserRepository rep, AuthenticationManager am) {
        this.userRepository = rep;
        this.authenticationManager = am;
    }

    public ResponseEntity login(LoginDTO data) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.email(), data.password());

        var auth = authenticationManager.authenticate(usernamePassword);

        return ResponseEntity.ok().build();

    }

    public ResponseEntity signup(RegisterDTO data) {
        var user = userRepository.findByEmail(data.email());
        if(user != null) {
            return ResponseEntity.badRequest().body("User already exists in our database");
        }
        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
        User newUser = new User(data.name(), data.email(), encryptedPassword, data.role());
        userRepository.save(newUser);

        return ResponseEntity.ok().body(data);//later on the project stop returning the data after the login.
    }
}
