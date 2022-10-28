package com.chen.star.service;

import com.chen.star.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MongodbService extends MongoRepository<User, Long> {

    List<User> findByPhone(String phone);

    User findBy(User user);

}
