package inlovepet.app.backend.entities.users.petowner;

import inlovepet.app.backend.entities.users.User;
import inlovepet.app.backend.enums.roles.UserRoles;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import org.springframework.security.core.GrantedAuthority;

@Entity
@DiscriminatorValue("Petowner")
public class Petowner extends User {

    public Petowner(){}

    public Petowner(String name, String email, String password, UserRoles role) {
        super(name, email, password, role);
    }
}
