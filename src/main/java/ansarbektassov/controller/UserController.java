package ansarbektassov.controller;

import ansarbektassov.dto.UserRequestDTO;
import ansarbektassov.exception.UserNotCreatedException;
import ansarbektassov.model.User;
import ansarbektassov.service.UserService;
import ansarbektassov.utils.ErrorBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Set;

//создание пользователя;
//обновление пользователя;
//получение списка всех пользователей.
@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService usersService;
    private final Logger log;

    @Autowired
    public UserController(UserService userService) {
        this.usersService = userService;
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
        log.debug("User created");
        return usersService.createUser(user);
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
        log.debug("User updated");
        return usersService.updateUser(userId,user);
    }

    @GetMapping
    public Collection<User> getAllUsers() {
        return usersService.getAllUsers();
    }

    @PutMapping("/{userId}/friends/{friendId}")
    public User addFriend(@PathVariable("userId") String userId, @PathVariable("friendId") String friendId) {
        return usersService.addFriend(userId,friendId);
    }

    @DeleteMapping("/{userId}/friends/{friendId}")
    public User deleteFriend(@PathVariable("userId") String userId, @PathVariable("friendId") String friendId) {
        return usersService.deleteFriend(userId,friendId);
    }

    @GetMapping("/{userId}/friends")
    public Set<String> getAllFriends(@PathVariable("userId") String userId, @PathVariable("friendId") String friendId) {
        return usersService.findAllFriends(userId);
    }

    @GetMapping("/users/{userId}/friends/common/{friendId}")
    public Set<String> getCommonFriends(@PathVariable("userId") String userId, @PathVariable("friendId") String friendId) {
        return usersService.findCommonFriends(userId,friendId);
    }
}
