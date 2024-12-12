package com.example.demo.services;

import com.example.demo.entities.User;
import com.example.demo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public User modifyUser(Long id, User user) {
        Optional<User> existingUser = userRepository.findById(id);
        if (existingUser.isPresent()) {
            User userToUpdate = existingUser.get();
            userToUpdate.setName(user.getName());
            userToUpdate.setPseudonyme(user.getPseudonyme());
            userToUpdate.setEmail(user.getEmail());
            userToUpdate.setLvl(user.getLvl());
            userToUpdate.setTotalPoints(user.getTotalPoints());
            return userRepository.save(userToUpdate);
        }
        throw new RuntimeException("user not found");
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public User findUser(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("user not found"));
    }


}