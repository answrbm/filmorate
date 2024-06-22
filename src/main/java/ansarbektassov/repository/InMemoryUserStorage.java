package ansarbektassov.repository;

import ansarbektassov.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;

@Component
public class InMemoryUserStorage {

    private final HashMap<String, User> users;

    @Autowired
    public InMemoryUserStorage(HashMap<String, User> users) {
        this.users = users;
    }

    public User save(User user) {
        return users.put(user.getUserId(),user);
    }

    public List<User> findAll() {
        return users.values().stream().toList();
    }

    public User findById(String userId) {
        return users.get(userId);
    }
}
