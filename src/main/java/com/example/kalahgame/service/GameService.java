package com.example.kalahgame.service;

import com.example.kalahgame.model.Game;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Service
public interface GameService {


    Game initGame();

    Game play(Integer pitIndex);
}
