package com.devsuperior.dslist.services;

import com.devsuperior.dslist.dto.GameMinDTO;
import com.devsuperior.dslist.entities.Game;
import com.devsuperior.dslist.repositories.GameRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GameService {

    private GameRepository repo;

    public GameService(GameRepository repo) {
        this.repo = repo;
    }

    public List<GameMinDTO> findAll() {
        List<Game> result = repo.findAll();
        return result.stream().map(x -> new GameMinDTO(x)).toList();
    }
}