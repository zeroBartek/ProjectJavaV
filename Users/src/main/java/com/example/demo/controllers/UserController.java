package com.example.demo.controllers;

import com.example.demo.dto.UserDTO;
import com.example.demo.entities.User;
import com.example.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/joueurs")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO) {
        User user = new User();
        user.setName(userDTO.getName());
        user.setPseudonyme(userDTO.getPseudonyme());
        user.setEmail(userDTO.getEmail());
        user.setLvl(userDTO.getLvl());
        user.setTotalPoints(userDTO.getTotalPoints());

        User savedUser = userService.createUser(user);

        userDTO.setId(savedUser.getId());
        return ResponseEntity.ok(userDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> modifyUser(@PathVariable Long id, @RequestBody UserDTO userDTO) {
        User existingUser = userService.findUser(id);

        existingUser.setName(userDTO.getName());
        existingUser.setPseudonyme(userDTO.getPseudonyme());
        existingUser.setEmail(userDTO.getEmail());
        existingUser.setLvl(userDTO.getLvl());
        existingUser.setTotalPoints(userDTO.getTotalPoints());

        User updatedUser = userService.modifyUser(id, existingUser);

        userDTO.setId(updatedUser.getId());
        return ResponseEntity.ok(userDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> findUser(@PathVariable Long id) {
        User user = userService.findUser(id);

        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setName(user.getName());
        userDTO.setPseudonyme(user.getPseudonyme());
        userDTO.setEmail(user.getEmail());
        userDTO.setLvl(user.getLvl());
        userDTO.setTotalPoints(user.getTotalPoints());
        userDTO.setFriendIds(user.getFriends().stream().map(friend -> friend.getFriend().getId()).collect(Collectors.toList()));

        return ResponseEntity.ok(userDTO);
    }
}
