package ansarbektassov.model;

import ansarbektassov.dto.UserRequestDTO;

import java.time.LocalDate;
import java.util.*;

//целочисленный идентификатор — id;
//электронная почта — email;
//логин пользователя — login;
//имя для отображения — name;
//дата рождения — birthday.
public class User {

    private String userId;
    private String email;
    private String login;
    private String name;
    private LocalDate birthday;
    private Set<String> friends;

    public User() {
        this.userId = UUID.randomUUID().toString();
        this.friends = new HashSet<>();
    }

    public User(String email, String login, String name, LocalDate birthday) {
        this.userId = UUID.randomUUID().toString();
        this.email = email;
        this.login = login;
        this.name = name;
        this.birthday = birthday;
        this.friends = new HashSet<>();
    }

    public User(UserRequestDTO userRequestDTO) {
        this.userId = UUID.randomUUID().toString();
        this.email = userRequestDTO.getEmail();
        this.login = userRequestDTO.getLogin();
        if(userRequestDTO.getName() == null || userRequestDTO.getName().isBlank()) {
           this.name = login;
        } else {
            this.name = userRequestDTO.getName();
        }
        this.birthday = userRequestDTO.getBirthdate();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public Set<String> getFriends() {
        return friends;
    }

    public void setFriends(Set<String> friends) {
        this.friends = friends;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + userId +
                ", email='" + email + '\'' +
                ", login='" + login + '\'' +
                ", name='" + name + '\'' +
                ", birthday=" + birthday +
                '}';
    }
}
