package com.codespark.springbootdatajpa.datajpa;

import com.codespark.springbootdatajpa.inputvalidation.User;

/**
 * UserService interface is service layer business logic blueprint for User
 * entity CRUD operations. UserServiceImpl implements UserService
 * functionalities.
 */
public interface UserService {
    public void save(User user);

    public User findUserByEmail(String email);
}
