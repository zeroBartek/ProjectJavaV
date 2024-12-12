package com.example.demo.repositories;

import com.example.demo.entities.Friend;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FriendRepository extends JpaRepository<Friend, Long> {

    <S extends Friend> S save(S friend);
}
