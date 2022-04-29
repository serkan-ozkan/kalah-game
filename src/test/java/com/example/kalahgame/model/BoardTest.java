package com.example.kalahgame.model;

import org.junit.Assert;
import org.junit.Test;

public class BoardTest {

    @Test
    public void CreateBoard(){
        Board board = initBoard();

        Assert.assertNotNull(board.getPits());
        Assert.assertEquals(14, board.getPits().size());
    }

    @Test
    public void shouldGetOppositePit() {
        //given
        Board board = initBoard();

        //when
        Pit pit1 = board.getPitByPitIndex(0);
        Pit oppositePit = board.getOppositePit(pit1);
        Pit pit1Again = board.getOppositePit(oppositePit);

        //then
        Assert.assertEquals(Integer.valueOf(12), oppositePit.getPitIndex());
        Assert.assertEquals(pit1, pit1Again);
    }



    private static Board initBoard(){

        return new Board();
    }
}
