package inlovepet.app.backend.enums.roles;

public enum UserRoles {
    PETOWNER("petowner"),
    PETSHOP("petshop");

    private String role;

    UserRoles(String role) {
        this.role = role;
    }
}
