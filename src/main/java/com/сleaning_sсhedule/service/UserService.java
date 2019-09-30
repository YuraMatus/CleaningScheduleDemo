package com.сleaning_sсhedule.service;

import com.сleaning_sсhedule.entity.User;

import java.util.Collection;
import java.util.Optional;

public interface UserService {
    Collection<User> getAllUsers();

    Optional<User> getUserById(Long id);

    User createUser(String username);

    void updateUser(User user, String username);

    void saveUser(User user);

    void deleteUser(User user);
}
