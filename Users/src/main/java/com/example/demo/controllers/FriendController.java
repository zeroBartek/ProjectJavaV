package com.example.demo.controllers;

import com.example.demo.dto.FriendDTO;
import com.example.demo.entities.Friend;
import com.example.demo.services.FriendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/joueurs")
public class FriendController {

    @Autowired
    private FriendService friendService;

    @PostMapping("/{idUser}/amis")
    public ResponseEntity<FriendDTO> addFriend(@PathVariable Long idUser, @RequestBody FriendDTO friendDTO) {

        Long idFriend = friendDTO.getIdFriend();

        Friend friend = friendService.addFriend(idUser, idFriend);

        FriendDTO responseDTO = new FriendDTO();
        responseDTO.setIdUser(friend.getUser().getId());
        responseDTO.setIdFriend(friend.getFriend().getId());

        return ResponseEntity.ok(responseDTO);
    }
}

