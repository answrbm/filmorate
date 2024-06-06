package ansarbektassov.dto;

import ansarbektassov.validation.Login;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

public class UserRequestDTO {

    @NotNull(message = "Email cannot be null")
    @NotEmpty(message = "Email cannot be empty")
    @Email
    private String email;
    @NotNull(message = "Login cannot be null")
    @NotEmpty(message = "Login cannot be empty")
    @Size(min = 2, message = "Login must be minimum: 8 characters")
    @Login
    private String login;
    @Size(min = 2, message = "Name must be minimum: 2 characters")
    private String name;
    @Past(message = "Birthdate must be in past")
    @NotNull(message = "Birthdate cannot be null")
    private LocalDate birthdate;

    public UserRequestDTO() {
    }

    public UserRequestDTO(String email, String login, String name, LocalDate birthdate) {
        this.email = email;
        this.login = login;
        this.name = name;
        this.birthdate = birthdate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }
}
