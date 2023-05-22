package com.devsuperior.dslist.services;

import com.devsuperior.dslist.dto.GameDTO;
import com.devsuperior.dslist.dto.GameListDTO;
import com.devsuperior.dslist.dto.GameMinDTO;
import com.devsuperior.dslist.entities.Game;
import com.devsuperior.dslist.entities.GameList;
import com.devsuperior.dslist.projections.GameMinProjection;
import com.devsuperior.dslist.repositories.GameListRepository;
import com.devsuperior.dslist.repositories.GameRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class GameListService {

    private GameListRepository repositoryList;

    private GameRepository gameRepository;

    public GameListService(GameListRepository repositoryList, GameRepository gameRepository) {
        this.repositoryList = repositoryList;
        this.gameRepository = gameRepository;
    }

    @Transactional(readOnly = true)
    public List<GameListDTO> findAll() {
        List<GameList> result = repositoryList.findAll();
        return result.stream().map(x -> new GameListDTO(x)).toList();
    }

    @Transactional
    public void move(Long listId, int sourceIndex, int destinationIndex) {

        List<GameMinProjection> list = gameRepository.searchByList(listId);

        GameMinProjection obj = list.remove(sourceIndex);
        list.add(destinationIndex, obj);

        int min = sourceIndex < destinationIndex ? sourceIndex : destinationIndex;
        int max = sourceIndex < destinationIndex ? destinationIndex : sourceIndex;

        for (int i = min; i <= max; i++) {
            repositoryList.updateBelongingPosition(listId, list.get(i).getId(), i);
        }
    }
}
