package ru.urfu.storage;

import ru.urfu.model.User;

import java.util.Optional;

public interface UserStorage {

    User addUser(User user);

    Optional<User> findUser(String username);
}
