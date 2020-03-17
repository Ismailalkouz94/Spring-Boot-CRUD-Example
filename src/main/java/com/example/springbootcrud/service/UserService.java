package com.example.springbootcrud.service;


import com.example.springbootcrud.entity.User;

import java.util.Arrays;
import java.util.HashSet;

public interface UserService {

    public User findUserByEmail(String email);

    public User findUserByUserName(String userName);

    public User saveUser(User user);

}