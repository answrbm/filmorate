package ansarbektassov.controller;

import ansarbektassov.dto.UserRequestDTO;
import ansarbektassov.exception.FilmNotCreatedException;
import ansarbektassov.exception.UserNotCreatedException;
import ansarbektassov.model.User;
import ansarbektassov.utils.ErrorBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.HashMap;

//создание пользователя;
//обновление пользователя;
//получение списка всех пользователей.
@RestController
@RequestMapping("/users")
public class UserController {

    private final HashMap<String, User> users;
    private final Logger log;

    public UserController() {
        this.users = new HashMap<>();
        this.log = LoggerFactory.getLogger(UserController.class);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public User createUser(@RequestBody @Validated UserRequestDTO userRequestDTO, BindingResult bindingResult) {
        log.debug("User creation");
        if(bindingResult.hasErrors()) {
            try {
                throw new UserNotCreatedException(ErrorBuilder.buildError(bindingResult.getFieldErrors()));
            } catch (UserNotCreatedException e) {
                log.error("User create error",e);
                throw new UserNotCreatedException(ErrorBuilder.buildError(bindingResult.getFieldErrors()));
            }
        }
        User user = new User(userRequestDTO);
        users.put(user.getUserId(),user);
        log.debug("User created");
        return user;
    }

    @PutMapping("/{userId}")
    @ResponseStatus(HttpStatus.CREATED)
    public User updateUser(@PathVariable("userId") String userId, @RequestBody @Validated UserRequestDTO userRequestDTO, BindingResult bindingResult) {
        log.debug("User updating");
        if(bindingResult.hasErrors()) {
            try {
                throw new UserNotCreatedException(ErrorBuilder.buildError(bindingResult.getFieldErrors()));
            } catch (UserNotCreatedException e) {
                log.error("User update error",e);
                throw new UserNotCreatedException(ErrorBuilder.buildError(bindingResult.getFieldErrors()));
            }
        }
        User user = new User(userRequestDTO);
        user.setUserId(userId);
        users.put(userId,user);
        log.debug("User updated");
        return user;
    }

    @GetMapping
    public Collection<User> getAllUsers() {
        return users.values();
    }


}
