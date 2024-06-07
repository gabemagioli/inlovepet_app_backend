package inlovepet.app.backend.controllers;

import inlovepet.app.backend.entities.users.User;
import inlovepet.app.backend.useCases.UserUseCases;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/user/requests")
public class UserController {
    private UserUseCases userUseCases;

    public UserController(UserUseCases userUseCases) {
        this.userUseCases = userUseCases;
    }

    @DeleteMapping
    @RequestMapping("/delete-account/{id}")
    public ResponseEntity<?> deleteUserAccount(@RequestBody @PathVariable String id) {
        return this.userUseCases.deleteAccount(id);
    }

    @GetMapping //delete later ********
    @RequestMapping("/all")
    public ResponseEntity<List<User>> findAllUsers() {
        return userUseCases.getAllUsers();
    }
}
