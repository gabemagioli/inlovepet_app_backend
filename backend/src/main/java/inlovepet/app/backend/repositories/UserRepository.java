package inlovepet.app.backend.repositories;

import inlovepet.app.backend.entities.users.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserRepository extends JpaRepository<User, String> {

    UserDetails findByEmail(String email);
}
