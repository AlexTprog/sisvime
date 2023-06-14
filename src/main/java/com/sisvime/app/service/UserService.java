package com.sisvime.app.service;

import com.sisvime.app.entity.users.User;
import com.sisvime.app.repository.IUserRepository;
import com.sisvime.app.share.UserStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private IUserRepository userRepository;

    public ArrayList<User> getAll() {
        return (ArrayList<User>) userRepository.findAll();
    }

    public User create(User user) {
        return userRepository.save(user);
    }

    public Optional<User> getById(Long id) {
        return userRepository.findById(id);
    }

    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    public void activeUser(Long id) {
        var dbUser = (User) userRepository.findById(id).orElse(null);
        if (dbUser != null) {
            dbUser.setStatus(UserStatus.Active);
        }
    }

    public void blockUser(Long id) {
        var dbUser = (User) userRepository.findById(id).orElse(null);
        if (dbUser != null) {
            dbUser.setStatus(UserStatus.Blocked);
        }
    }
}
