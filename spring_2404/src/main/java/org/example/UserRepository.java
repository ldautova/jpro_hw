package org.example;

import com.zaxxer.hikari.HikariDataSource;
import lombok.RequiredArgsConstructor;
import org.example.model.User;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

/**
 * UserRepository.
 *
 * @author Lina_Dautova
 */
@RequiredArgsConstructor
@Service
public class UserRepository {
    private final HikariDataSource dataSource;

    public User findById(Integer id) throws SQLException {
        try (Connection con = dataSource.getConnection();
             PreparedStatement pst = con.prepareStatement("select id, user_name from users where id = ?")) {
            pst.setInt(1, id);
            ResultSet result = pst.executeQuery();
            if (Objects.isNull(result) || !result.next()) {
                return null;
            }
            return new User().setId(result.getInt("id"))
                    .setUserName(result.getString("user_name"));
        }
    }

    public void save(String userName) throws SQLException {
        try (Connection con = dataSource.getConnection();
             PreparedStatement pst = con.prepareStatement("insert into users(user_name) values (?)")) {
            pst.setString(1, userName);
            pst.executeUpdate();
        }
    }

    public List<User> findAll() throws SQLException {
        List<User> result = new LinkedList<>();
        try (Connection con = dataSource.getConnection();
             PreparedStatement pst = con.prepareStatement("select id, user_name from users")) {
            ResultSet resultSet = pst.executeQuery();
            while (resultSet.next()) {
                result.add(new User()
                        .setId(resultSet.getInt("id"))
                        .setUserName(resultSet.getString("user_name")));
            }
        }
        return result;
    }

    public void deleteById(Integer id) throws SQLException {
        try (Connection con = dataSource.getConnection();
             PreparedStatement pst = con.prepareStatement("delete from users where id = ?")) {
            pst.setInt(1, id);
            pst.executeUpdate();
        }
    }

    public void deleteAllUsers() throws SQLException {
        try (Connection con = dataSource.getConnection();
             PreparedStatement pst = con.prepareStatement("truncate users")) {
            pst.executeUpdate();
        }
    }
}
