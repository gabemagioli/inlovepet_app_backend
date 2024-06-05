package inlovepet.app.backend.entities.users.petshop;

import inlovepet.app.backend.entities.users.User;
import inlovepet.app.backend.enums.roles.UserRoles;
import jakarta.persistence.Entity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

@Entity
public class Petshop extends User {

    public Petshop(){}
    public Petshop(String name, String email, String password, UserRoles role) {
        super(name, email, password, role);
    }
}
