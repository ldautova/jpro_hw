package org.example.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.model.User;
import org.example.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * CommandLineRunnerImpl.
 *
 * @author Lina_Dautova
 */
@Slf4j
@RequiredArgsConstructor
@Component
public class CommandLineRunnerImpl implements CommandLineRunner {
    private final UserService userService;

    @Override
    public void run(String... args) {
        log.info("CommandLineRunnerImpl run start");

        User forDelete = userService.createUser("User_for_delete");
        log.info("Created user = {}", forDelete);

        List<User> users = userService.findAll();
        log.info("All users found = {}", users);

        var userOpt = userService.getUserById(forDelete.getId());
        log.info("User found by id = {}, user = {}", forDelete.getId(), userOpt.orElse(null));

        userService.deleteById(forDelete.getId());
        log.info("Delete user by id = {}", forDelete.getId());

        var foundUserAfterDeleteOpt = userService.getUserById(forDelete.getId());
        log.info("Get user by id = {}, user = {}", forDelete.getId(), foundUserAfterDeleteOpt.orElse(null));

        log.info("CommandLineRunnerImpl run end");
    }
}