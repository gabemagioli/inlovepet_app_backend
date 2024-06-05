package inlovepet.app.backend.entities.users;

import inlovepet.app.backend.enums.roles.UserRoles;
import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

@Entity
@Table(name = "USERS")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class User implements UserDetails {
    //attributes
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @Column
    private String name;
    @Column
    private String email;
    @Column
    private String password;
    @Column
    @Enumerated(EnumType.STRING)
    private Set<UserRoles> roles;

    //constructors
    public User(){}

    public User(String name, String email, String password, UserRoles role) {
        this.name = name;
        this.email = email;
        this.password = password;
        if(this.roles == null) {
            this.roles = new HashSet<>();
            this.roles.add(role);
        }
    }

    //getters and setters
    public String getId() {
        return id;
    }

    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return this.email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<UserRoles> getRole() {
        return this.roles;
    }
    public void setRole(UserRoles role) {
        this.roles.add(role);
    }

    //equals and hashcode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User user)) return false;
        return Objects.equals(id, user.id) && Objects.equals(name, user.name) && Objects.equals(email, user.email) && Objects.equals(password, user.password);
    }
    @Override
    public int hashCode() {
        return Objects.hash(id, name, email, password);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
            return List.of(new SimpleGrantedAuthority("ROLE_PETSHOP"), new SimpleGrantedAuthority("ROLE_PETOWNER"));
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.email;
    }
}