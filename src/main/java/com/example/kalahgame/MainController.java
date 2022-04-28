package com.example.kalahgame;

import com.example.kalahgame.model.Pit;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("api")
@CrossOrigin("http://localhost:8081/")
public class MainController {
    @GetMapping("/")
    public List<Integer> index(Model model) {
        List<Integer> initialBoard = Arrays.asList(6,6,6,6,6,6,0,6,6,6,6,6,6,0);
        return initialBoard;
    }
    @PutMapping("/play")
    public ResponseEntity<Pit> play(@RequestBody Pit pitId){
        List<Integer> currentBoard = pitId.getCurrentBoard();
        Integer pitIndex = pitId.getPitIndex();

        for(int i = pitIndex + 1; i <= (pitIndex + currentBoard.get(pitIndex)) ; i ++){
            currentBoard.set(i%14, currentBoard.get(i%14) + 1);
        }
        currentBoard.set(pitIndex, 0);
        Pit resultBoard = new Pit(pitIndex, currentBoard);
        return ResponseEntity.status(HttpStatus.OK).body(pitId);
    }
}
