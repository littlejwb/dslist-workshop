package com.devsuperior.dslist.controllers;

import com.devsuperior.dslist.dto.GameMinDTO;
import com.devsuperior.dslist.entities.Game;
import com.devsuperior.dslist.services.GameService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/games")
public class GameController {

    private GameService service;

    public GameController(GameService service) {
        this.service = service;
    }

    @GetMapping
    public List<GameMinDTO> findAll() {
        List<GameMinDTO> result = service.findAll();
        return result;
    }
}
