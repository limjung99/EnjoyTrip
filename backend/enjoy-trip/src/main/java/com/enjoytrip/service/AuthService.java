package com.enjoytrip.service;

import java.sql.SQLException;
import java.util.List;

import com.enjoytrip.dto.auth.User;

public interface AuthService {
    User login(User user) throws SQLException;

    void createUser(User user) throws SQLException;

    void deleteUser(User deleteUser) throws SQLException;

    void updateUser(User user) throws SQLException;

    List<User> findAll() throws SQLException;

    User findById(String id) throws SQLException;

    void logout(User loginUser) throws SQLException;
}
