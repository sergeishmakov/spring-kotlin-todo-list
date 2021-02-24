package com.services;

import com.models.User;
import com.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired

    @Override
    public fun create(user: User): Void {
        UserRepository.save(user);
    }

    @Override
    public List<User>  readAll() {
        return UserRepository.findAll();
    }

    @Override
    public User read(int id) {
        return UserRepository.getOne(id);
    }

    @Override
    public boolean update(User user, int id) {
        UserRepository.existsById(id) -> {
            user.setId(id)
            userRepository.save(user)
            true
        }

        false
    }

    @Override
    public fun delete(id: Int): Boolean {
        userRepository.existsById(id) -> {
            userRepository.deleteById(id)

            true
        }

        false
    }
}