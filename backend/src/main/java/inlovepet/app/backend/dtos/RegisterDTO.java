package inlovepet.app.backend.dtos;

import inlovepet.app.backend.enums.roles.UserRoles;

public record RegisterDTO(String name, String email, String password, UserRoles role) {

}
