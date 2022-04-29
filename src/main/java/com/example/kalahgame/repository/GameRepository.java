package com.example.kalahgame.repository;

import com.example.kalahgame.model.Game;
import org.springframework.stereotype.Repository;

/**
 * Storage of the game.
 */
@Repository
public class GameRepository {

    private Game game = new Game();

    public Game create() {
        this.game = new Game();
        return game;
    }

    public Game getGame() {
        return this.game;
    }

}
