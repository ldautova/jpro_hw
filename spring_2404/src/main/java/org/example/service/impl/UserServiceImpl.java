package org.example.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.UserRepository;
import org.example.model.User;
import org.example.service.UserService;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

/**
 * UserServiceImpl.
 *
 * @author Lina_Dautova
 */
@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public void createUser(String first) throws SQLException {
        userRepository.save(first);
    }

    @Override
    public User getUserById(Integer id) throws SQLException {
        return userRepository.findById(id);
    }

    @Override
    public List<User> findAll() throws SQLException {
        return userRepository.findAll();
    }

    @Override
    public void deleteById(Integer id) throws SQLException {
        userRepository.deleteById(id);
    }

    @Override
    public void clearUsers() throws SQLException {
        userRepository.deleteAllUsers();
    }
}
