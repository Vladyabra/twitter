package ru.urfu.storage;

import org.springframework.stereotype.Component;
import ru.urfu.model.User;

import java.util.List;
import java.util.Optional;
import java.util.Vector;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class InMemoryUserStorage implements UserStorage {

    private final AtomicInteger curId = new AtomicInteger(0);
    private final List<User> users = new Vector<>();

    @Override
    public User addUser(User user) {
        user.setId(curId.getAndIncrement());
        users.add(user);

        return user;
    }

    @Override
    public Optional<User> findUser(String username) {
        final String lowerCaseUsername = username.toLowerCase();
        return users.stream()
                .filter(user -> user.getUsername().toLowerCase().equals(lowerCaseUsername))
                .findAny();
    }
}
