package com.codespark.springbootrabbitmqrediskafka.redis.as_cache;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public class UserRepository {

    private static final Map<String, User> users = new HashMap<>() {
        {
            put("1", new User("1", "John", 19));
            put("2", new User("2", "Jane", 24));
            put("3", new User("3", "Mary", 36));
            put("4", new User("4", "Harry", 45));
        }
    };

    public User findById(String id) {
        return users.get(id);
    }

}

class User implements Serializable {

    private String id;
    private String name;
    private int rollNo;

    public User() {
    }

    public User(String id, String name, int rollNo) {
        this.id = id;
        this.name = name;
        this.rollNo = rollNo;
    }

    @Override
    public String toString() {
        return "User [id=" + id + ", name=" + name + ", rollNo=" + rollNo + "]";
    }

}