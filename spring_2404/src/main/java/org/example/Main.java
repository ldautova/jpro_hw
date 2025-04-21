package org.example;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.model.User;
import org.example.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import java.sql.SQLException;
import java.util.List;

/**
 * Задание 4 дедлайн 24.04
 * - Разверните локально postgresql БД, создайте таблицу users (id bigserial primary key, username varchar(255) unique);
 * <p>
 * - Создайте Maven проект и подключите к нему: драйвер postgresql, hickaricp, spring context.
 * <p>
 * - Создайте пул соединений в виде Spring бина
 * <p>
 * - Создайте класс User (Long id, String username)
 * <p>
 * - Реализуйте в виде бина класс UserDao который позволяет выполнять CRUD операции над пользователями
 * <p>
 * - Реализуйте в виде бина UserService, который позволяет: создавать, удалять, получать одного, получать всех пользователей из базы данных
 * <p>
 * - Создайте Spring Context, получите из него бин UserService и выполните все возможные операции
 *
 * @author Lina_Dautova
 */
@ComponentScan
public class Main {
    public static void main(String[] args) throws SQLException, JsonProcessingException {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Main.class);

        UserService userService = context.getBean(UserService.class);
        ObjectMapper objectMapper = context.getBean(ObjectMapper.class);

        userService.clearUsers();

        userService.createUser("First");
        userService.createUser("Second");
        userService.createUser("Third");

        System.out.println("********* create users with names [First, Second, Third]");


        List<User> users = userService.findAll();
        System.out.printf("Users found: %s \n", print(objectMapper, users));

        Integer userIdForFind = users.getFirst().getId();
        User user = userService.getUserById(userIdForFind);
        System.out.printf("Find user by id = %d ::: %s\n", userIdForFind, print(objectMapper, user));

        userService.deleteById(user.getId());
        System.out.printf("********* delete user by id = %s\n", user.getId());
        System.out.printf("Find user by id = %d after delete ::: %s ", user
                .getId(), userService.getUserById(user.getId()));
    }


    private static String print(ObjectMapper objectMapper, List<User> users) throws JsonProcessingException {
        return objectMapper.writeValueAsString(users);
    }

    private static String print(ObjectMapper objectMapper, User user) throws JsonProcessingException {
        return objectMapper.writeValueAsString(user);
    }

}