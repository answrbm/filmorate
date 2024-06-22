package ansarbektassov.service;

import ansarbektassov.exception.UserNotFoundException;
import ansarbektassov.model.User;
import ansarbektassov.repository.InMemoryUserStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Service
public class UserService {

    private final InMemoryUserStorage userStorage;

    @Autowired
    public UserService(InMemoryUserStorage userStorage) {
        this.userStorage = userStorage;
    }


    public User createUser(User user) {
        return userStorage.save(user);
    }

    public User updateUser(String userId, User user) {
        user.setUserId(userId);
        return userStorage.save(user);
    }

    public User getUserById(String userId) {
        User foundUser = userStorage.findById(userId);
        if(foundUser == null) {
            throw new UserNotFoundException("User with such id not found!");
        }
        return foundUser;
    }

    public Collection<User> getAllUsers() {
        return userStorage.findAll();
    }

    public User addFriend(String userId, String friendId) {
        User user = getUserById(userId);
        User friend = getUserById(friendId);
        user.getFriends().add(friend.getUserId());
        friend.getFriends().add(user.getUserId());

        updateUser(friend.getUserId(),friend);
        updateUser(user.getUserId(),user);

        return friend;
    }

    public User deleteFriend(String userId, String friendId) {
        User user = getUserById(userId);
        User friend = getUserById(friendId);
        user.getFriends().remove(friend.getUserId());
        friend.getFriends().remove(user.getUserId());

        updateUser(friend.getUserId(),friend);
        updateUser(user.getUserId(),user);

        return friend;
    }

    public Set<String> findAllFriends(String userId) {
        User user = getUserById(userId);
        return user.getFriends();
    }

    public Set<String> findCommonFriends(String userId, String friendId) {
        User user = getUserById(userId);
        User friend = getUserById(friendId);
        Set<String> userFriends = new HashSet<>(user.getFriends());
        userFriends.retainAll(friend.getFriends());
        return userFriends;
    }
}
