package com.сleaning_sсhedule.service.impl;

import com.сleaning_sсhedule.entity.User;
import com.сleaning_sсhedule.repository.UserRepository;
import com.сleaning_sсhedule.service.UserService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public Collection<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public User createUser(String username) {
        return userRepository.save(
                new User().setUsername(username)
        );
    }

    @Override
    public void updateUser(User user, String username) {
        user.setUsername(username);
        saveUser(user);
    }

    @Override
    public void saveUser(User user) {
        userRepository.save(user);
    }

    @Override
    public void deleteUser(User user) {
        userRepository.delete(user);
    }
}
