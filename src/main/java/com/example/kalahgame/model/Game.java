package com.example.kalahgame.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * The game class. Game contains current board and game status.
 */
@Getter
@Setter
@AllArgsConstructor
public class Game {
    private Board currentBoard;
    private GameStatus gameStatus;

    public Game() {
        this.currentBoard = new Board();
        this.gameStatus = GameStatus.PLAYER1_TURN;
    }
}
