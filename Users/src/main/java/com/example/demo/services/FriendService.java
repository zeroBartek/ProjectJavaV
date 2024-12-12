package com.example.demo.services;

import com.example.demo.entities.Friend;
import com.example.demo.entities.User;
import com.example.demo.repositories.FriendRepository;
import com.example.demo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FriendService {

    @Autowired
    private FriendRepository friendRepository;

    @Autowired
    private UserRepository userRepository;

    public Friend addFriend(Long idUser, Long idFriend) {
        User user = userRepository.findById(idUser).orElseThrow(() -> new RuntimeException("user not found"));
        User friend = userRepository.findById(idFriend).orElseThrow(() -> new RuntimeException("Friend not found"));

        Friend newFriend = new Friend();
        newFriend.setUser(user);
        newFriend.setFriend(friend);

        return friendRepository.save(newFriend);
    }
}
