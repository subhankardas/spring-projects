package com.codespark.springbootdatajpa.datajpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codespark.springbootdatajpa.inputvalidation.User;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void save(User user) {
        UserEntity newUser = new UserEntity();
        newUser.setName(user.getName());
        newUser.setAge(user.getAge());
        newUser.setEmail(user.getEmail());
        newUser.setAddress(user.getAddress());
        newUser.setRegisteredOn(user.getRegisteredOn());
        newUser.setIpAddress(user.getIpAddress());
        newUser.setPassword(user.getPassword());

        userRepository.save(newUser);
    }

    @Override
    public User findUserByEmail(String email) {
        UserEntity user = userRepository.findByEmail(email);
        if (user == null) { // User not found (search by email failed)
            return null;
        }

        // Map entity to DTO
        User userDTO = new User();
        userDTO.setName(user.getName());
        userDTO.setAge(user.getAge());
        userDTO.setEmail(user.getEmail());
        userDTO.setAddress(user.getAddress());
        userDTO.setRegisteredOn(user.getRegisteredOn());

        return userDTO;
    }

}