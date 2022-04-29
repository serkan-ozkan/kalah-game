package com.example.kalahgame.service;

import com.example.kalahgame.model.Game;
import com.example.kalahgame.model.Pit;
import com.example.kalahgame.repository.GameRepository;
import org.springframework.stereotype.Component;

/**
 * Bridge between controller and GamePlay
 */
@Component
public class GameServiceImpl implements GameService{

    private final GameRepository gameRepository;
    private final GamePlay gamePlay;

    public GameServiceImpl(GameRepository gameRepository, GamePlay gamePlay){
        this.gameRepository = gameRepository;
        this.gamePlay = gamePlay;
    }


    @Override
    public Game initGame() {
        return gameRepository.create();
    }

    @Override
    public Game play(Integer pitIndex) {
        Game game = gameRepository.getGame();
        Pit pit = game.getCurrentBoard().getPitByPitIndex(pitIndex);
        gamePlay.play(game,pit);

        return game;
    }
}
