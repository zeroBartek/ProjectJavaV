package com.example.games.services;

import com.example.games.entities.Type;
import com.example.games.repositories.TypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TypeService {
    @Autowired
    private TypeRepository typeRepository;

    public Type getTypeById(Long id) {
        return typeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Type not found"));
    }

    public Type getTypeByName(String name) {
        return typeRepository.findByName(name);
    }
}
