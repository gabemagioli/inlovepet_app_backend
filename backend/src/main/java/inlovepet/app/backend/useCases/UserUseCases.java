package inlovepet.app.backend.useCases;

import inlovepet.app.backend.dtos.LoginDTO;
import inlovepet.app.backend.dtos.RegisterDTO;
import inlovepet.app.backend.dtos.TokenDTO;
import inlovepet.app.backend.entities.users.User;
import inlovepet.app.backend.infra.security.TokenService;
import inlovepet.app.backend.repositories.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserUseCases {

    private UserRepository userRepository;
    private AuthenticationManager authenticationManager;
    private TokenService tokenService;

    public UserUseCases(UserRepository rep, AuthenticationManager am,TokenService ts) {
        this.userRepository = rep;
        this.authenticationManager = am;
        this.tokenService = ts;
    }

    public ResponseEntity<?> login(LoginDTO data) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.email(), data.password());

        var auth = authenticationManager.authenticate(usernamePassword);
        var user = (User) userRepository.findByEmail(data.email());

        if(user != null && user.getIsActive()) {
            var token = tokenService.generateToken((User) auth.getPrincipal());
            return ResponseEntity.ok(new TokenDTO(token));
        }
        return ResponseEntity.badRequest().body("Unable to login");
    }

    public ResponseEntity<?> signup(RegisterDTO data) {
        var user = userRepository.findByEmail(data.email());
        if(user != null) {
            return ResponseEntity.badRequest().body("User already exists in our database");
        }
        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
        User newUser = new User(data.name(), data.email(), encryptedPassword, data.role());
        userRepository.save(newUser);

        return ResponseEntity.ok().body(newUser);//later on the project stop returning the data after the login.
    }

    public ResponseEntity<?> deleteAccount(String id) {
        if(userRepository.findById(id).isPresent()) {
            User user = userRepository.findById(id).get();
            if(user.getIsActive()) {
                user.setIsActive(false);
                userRepository.save(user);
                return ResponseEntity.ok().body("Your account was deleted successfully");
            }
            return ResponseEntity.badRequest().body("Your account can't be deleted since you've done this action before");
        }
        return ResponseEntity.notFound().build();
    }

    public ResponseEntity<List<User>> getAllUsers() {//delete later **********
        List<User> allUsers = userRepository.findAll();
        List<User> active = new ArrayList<>();
        for(User i : allUsers) {
            if(i.getIsActive()) {
                active.add(i);
            }
        }
        return ResponseEntity.ok().body(active);
    }
}
