package org.example.service;

import org.example.model.User;

import java.sql.SQLException;
import java.util.List;

/**
 * UserService.
 *
 * @author Lina_Dautova
 */
public interface UserService {
    void createUser(String first) throws SQLException;

    User getUserById(Integer id) throws SQLException;

    List<User> findAll() throws SQLException;

    void deleteById(Integer id) throws SQLException;

    void clearUsers() throws SQLException;
}
