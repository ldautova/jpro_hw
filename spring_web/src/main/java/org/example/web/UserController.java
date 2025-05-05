package org.example.web;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.example.model.User;
import org.example.service.UserService;
import org.example.web.dto.UserDto;
import org.example.web.mapper.MapperService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * UserController.
 *
 * @author Lina_Dautova
 */
@RequiredArgsConstructor
@RestController
public class UserController {
    private final UserService userService;
    private final MapperService mapperService;

    @GetMapping("/users")
    public List<UserDto> getAllProducts() {
        List<User> users = userService.findAll();
        if (users.isEmpty()) {
            return List.of();
        }
        return users.stream()
                .map(mapperService::toUserDto)
                .toList();
    }

    @GetMapping("/users/{userId}")
    public UserDto getUserById(@PathVariable(name = "userId") Long userId) {
        User user = userService.getUserById(userId)
                .orElseThrow(() -> new EntityNotFoundException(String.format("User with id %s not found", userId)));
        return mapperService.toUserDto(user);
    }
}
