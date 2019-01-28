package com.project.app.gameauthorization;

import com.project.app.model.Client;
import com.project.app.model.ClientsDataAccessor;
import com.project.app.model.DictionaryAccessor;
import com.project.app.model.Word;

import java.util.ArrayList;
import java.util.Random;

public class Game {

    private Client player_1;
    private Client player_2;
    private int player_1_points = -1;
    private int player_2_points = -1;
    private ClientsDataAccessor accessor;
    private DictionaryAccessor dictionaryAccessor;
    private int typeOfGame;
    private String levelOfGame;
    private ArrayList<Word> randomWords;

    public void setPlayers(Client player_1, Client player_2){
        accessor = new ClientsDataAccessor();
        dictionaryAccessor = new DictionaryAccessor();
        Random random = new Random();

        this.player_1 = player_1;
        this.player_2 = player_2;
        int elo1 = player_1.getElo();
        int elo2 = player_2.getElo();
        int minElo = elo1;
        if(elo2 < minElo)
        {
            minElo = elo2;
        }
        String level = getLevelFromElo(minElo);
        this.levelOfGame = level;

        int type = random.nextInt((2 - 1) + 1) + 1;

        if(type==1)
        {
            this.randomWords = dictionaryAccessor.getWordsFromLevel(level, 5);
            this.typeOfGame = 1;
        }
        else
        {
            this.randomWords = dictionaryAccessor.getWordsFromLevel(level, 3);
            this.typeOfGame = 2;
        }
    }

    public String getLevelFromElo(int elo)
    {
        String level="";
        if(elo >=1200 && elo < 1250)
        {
            level = "B1";
        }
        else if(elo >=1250 && elo < 1300)
        {
            level = "B2";
        }
        else if(elo >=1300 && elo < 1350)
        {
            level = "C1";
        }
        else if(elo >=1350)
        {
            level = "C2";
        }
        else if(elo >=1250 && elo < 1200)
        {
            level = "A2";
        }
        else
        {
            level = "A1";
        }
    return level;
    }
    public void setPoints(String player, int playerPoints)
    {
        if(player_1.getLogin().equals(player))
        {
            player_1_points = playerPoints;
        }
        else
        {
            player_2_points = playerPoints;

        }
    }

    public int getPoints(int player)
    {
        if(player == 1)
        {
            return  player_1_points;
        }
        else
        {
            return  player_2_points;

        }
    }

    public String getPlayer(int player)
    {
        if(player == 1)
        {
            return  player_1.getLogin();
        }
        else
        {
            return  player_2.getLogin();

        }
    }

    public int getType()
    {
        return typeOfGame;
    }

    public String getLevelOfGame()
    {
        return levelOfGame;
    }

    public ArrayList<Word> getWordsFromGame()
    {
        return randomWords;
    }

    public void startGame(){
        System.out.println("GAME -> "+player_1.getLogin() +" VS "+player_2.getLogin());
    }

    public String getSecondPlayer(String player) {

        if(player_1.getLogin().equals(player))
        {
            return player_2.getLogin();
        }
        else
        {
            return player_1.getLogin();
        }
    }
}
