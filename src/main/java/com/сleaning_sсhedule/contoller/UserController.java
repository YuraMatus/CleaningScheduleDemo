package com.сleaning_sсhedule.contoller;

import com.сleaning_sсhedule.dao.UserInformation;
import com.сleaning_sсhedule.dao.Users;
import com.сleaning_sсhedule.entity.User;
import com.сleaning_sсhedule.exceptions.UserNotFoundException;
import com.сleaning_sсhedule.service.UserService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users/")
@AllArgsConstructor
@NoArgsConstructor
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping
    public Users getAllUsers() {
        return new Users(userService.getAllUsers());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public User createUser(@RequestBody UserInformation userInformation) {
        return userService.createUser(userInformation.getUsername());
    }

    @GetMapping(path = "/{id}")
    public User getUser(@PathVariable Long id) throws UserNotFoundException {
        return userService.getUserById(id).orElseThrow(UserNotFoundException::new);
    }

    @PutMapping(path = "/{id}")
    public User updateUser(
            @PathVariable Long id,
            @RequestBody UserInformation userInformation
    ) throws UserNotFoundException {
        User user = userService.getUserById(id).orElseThrow(UserNotFoundException::new);
        userService.updateUser(user, userInformation.getUsername());

        return user;
    }

    @DeleteMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable Long id) throws UserNotFoundException {
        userService.deleteUser(
                userService.getUserById(id).orElseThrow(UserNotFoundException::new)
        );
    }
}
