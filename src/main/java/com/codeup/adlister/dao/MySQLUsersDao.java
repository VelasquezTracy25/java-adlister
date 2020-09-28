package com.codeup.adlister.dao;

import com.codeup.adlister.config.Config;
import com.codeup.adlister.models.Ad;
import com.codeup.adlister.models.User;
import com.mysql.cj.jdbc.Driver;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySQLUsersDao implements Users {
    private Connection connection = null;
    private Object User;

    public MySQLUsersDao(Config config) {
        try {
            DriverManager.registerDriver(new Driver());
            connection = DriverManager.getConnection(
                    config.getUrl(),
                    config.getUser(),
                    config.getPassword()
            );
        } catch (SQLException e) {
            throw new RuntimeException("Error connecting to the database!", e);
        }
    }

    @Override
    public User findByUsername(String username) throws SQLException {
        PreparedStatement stmt = null;
        stmt String = "SELECT * FROM users WHERE username LIKE ?";
        stmt.setString = username;

        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setString(1, searchTermWithWildcards);

        ResultSet rs = stmt.executeQuery();
        User user = new User();
        while (rs.next()) {
            return user1;
        }
        return user1;
    }

@Override
    public Long insert(User user) {
        try {
            String sql = String.format("INSERT INTO Users(id, username, email, password) VALUES ('%d', '%s', '%s', '%s');", user.getId(), user.getUsername(), user.getEmail(), user.getPassword());
            PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            rs.next();
            return rs.getLong(1);
        } catch (SQLException e) {
            throw new RuntimeException("Error creating a new user.", e);
        }
    }

    private String getInsertQuery(Ad ad) {
        return "INSERT INTO Users(id, username, email, password) VALUES (?, ?, ?, ?);";
    }

    private User extractUser(ResultSet rs) throws SQLException {
        if (!rs.next()) {
            return null;
        }
        return new User(
                rs.getLong("id"),
                rs.getString("username"),
                rs.getString("email"),
                rs.getString("password")
        );
    }

    private List<User> createUsersFromResults(ResultSet rs) throws SQLException {
        List<User> users = new ArrayList<>();
        while (rs.next()) {
            users.add(extractUser(rs));
        }
        return users;
    }

}
