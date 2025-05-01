package org.example.service;

import org.example.model.User;

import java.util.List;
import java.util.Optional;

/**
 * UserService.
 *
 * @author Lina_Dautova
 */
public interface UserService {
    User createUser(String first);

    Optional<User> getUserById(Long id);

    List<User> findAll();

    void deleteById(Long id);
}
