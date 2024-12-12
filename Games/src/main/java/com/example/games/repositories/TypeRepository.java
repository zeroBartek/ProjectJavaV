package com.example.games.repositories;

import com.example.games.entities.Type;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TypeRepository extends JpaRepository<Type, Long> {
    Type findByName(String name);
}
