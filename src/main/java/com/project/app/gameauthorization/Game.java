package com.project.app.gameauthorization;

import com.project.app.model.Client;
import com.project.app.model.ClientsDataAccessor;
import com.project.app.model.DictionaryAccessor;
import com.project.app.model.Word;

import java.util.ArrayList;
import java.util.Random;

/**
 * Class that regulates process of making a game with two players and calculating results of it.
 * @author      Dominika Kunc
 * @version     1.0
 */
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

    /**
     *  Method that set up Game parameters.
     * @param player_1 object of Client with information about his elo.
     * @param player_2 object of Client with information about his elo.
     */
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

    /**
     *  Method that calculates level difficulty of a game based on elo points of player.
     * @param elo contains elo points of player.
     * @return level difficulty of game.
     */
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
        else if(elo >= 1150)
        {
            level = "A2";
        }
        else
        {
            level = "A1";
        }
    return level;
    }

    /**
     *  Method that set points of player after the game.
     * @param player contains player's name.
     * @return playerPoints contains player's points after the game.
     */
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

    /**
     *  Method that returns how many points player get after the game.
     *  @param player contains player's index in game.
     *  @return number of points won in a game by player.
     */
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

    /**
     *  Method that returns player's name based on his index in game.
     *  @param player contains player's index in game.
     *  @return player's name.
     */
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

    /**
     *  Method that returns type of Game.
     *  @return type of Game.
     */
    public int getType()
    {
        return typeOfGame;
    }

    /**
     *  Method that returns level of Game.
     *  @return level of Game.
     */
    public String getLevelOfGame()
    {
        return levelOfGame;
    }

    /**
     *  Method that returns ArrayList of words used in a game.
     *  @return ArrayList of words used in a game.
     */
    public ArrayList<Word> getWordsFromGame()
    {
        return randomWords;
    }

    /**
     *  Method that starts a game.
     *
     */
    public void startGame(){
        System.out.println("GAME -> "+player_1.getLogin() +" VS "+player_2.getLogin());
    }

    /**
     *  Method that returns second player's name based on a first player's name.
     * @param player first player's name.
     * @return second's player name.
     */
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
