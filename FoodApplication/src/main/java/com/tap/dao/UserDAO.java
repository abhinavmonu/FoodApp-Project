package com.tap.dao;

import java.util.List;

import com.tap.model.User;

public interface UserDAO {
     int addUser(User user);
     User getUser(int userId);
     void updateUser(User user);
     void deleteUser(int userId);
     List<User> getAllUsers();
}
