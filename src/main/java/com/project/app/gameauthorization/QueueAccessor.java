package com.project.app.gameauthorization;

import com.project.app.model.Client;
import com.project.app.model.ClientsDataAccessor;

import java.util.*;
/**
 * Class that regulates process of making an queue of players willing to join a game.
 * @author      Paweł Krupski
 * @version     1.0
 */
public class QueueAccessor {

    private boolean block = true;

    private QueueAccessor()
    {
        queue = new ArrayList<>();
        currentgames = new ArrayList<>();
        listOfGames = new ArrayList<>();
        timer = new TreeMap<>();
    }

    private static QueueAccessor single_instance = null;

    /**
     *  Method that return instance of class.
     * @return  instance of class.
     */
    public static QueueAccessor getInstance()
    {
        if (single_instance == null)
            single_instance = new QueueAccessor();

        return single_instance;
    }

    private ClientsDataAccessor accessor = new ClientsDataAccessor();
    private List<String> queue;
    private List<String> currentgames;
    private List<Game> listOfGames;
    private Map<String,Integer> timer;


    /**
     *  Method that removes timer from player.
     * @param player contains player's name.
     */
    public void removeTimer(String player){
        timer.remove(player);
    }

    /**
     *  Method that adds 1 second to the player's timer.
     * @param player contains player's name.
     */
    public void addTimeToTimer(String player){
        Integer i = timer.get(player);
        timer.remove(player);
        timer.put(player, i + 1);
    }

    /**
     *  Method that returns player's current time spent after the second player finished sam game.
     * @param player contains player's name.
     *  @return player's timer value.
     */
    public int getTime(String player){
        return timer.get(player);
    }

    /**
     *  Method that check if player is in the queue.
     * @param player contains player's name.
     */
    public boolean isInQueue(String player){

        return queue.contains(player);
    }

    /**
     *  Method that removes player from the queue.
     * @param player contains player's name.
     */
    public boolean removeFromQueue(String player){

        return queue.remove(player);
    }

    /**
     *  Method that adds player to the queue.
     * @param player contains player's name.
     */
    public void addToQueue(String player){
        queue.add(player);
        timer.put(player, 0);
    }

    /**
     *  Method that checks if Queue is at least 2 people's long.
     */
    public boolean twoElementsAtQueue(){
        return (queue.size() >= 2);
    }


    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    /**
     *  Method that adds player to the list of current games.
     * @param player contains player's name.
     */
    public void addToCurrentGame(String player){
        currentgames.add(player);
    }

    /**
     *  Method that check if player is in the game.
     * @param player contains player's name.
     */
    public boolean isInGame(String player){

        return currentgames.contains(player);
    }

    /**
     *  Method that removes player from current games.
     * @param player contains player's name.
     */
    public void removeCurrentGame(String player){
        currentgames.remove(player);
    }


    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    /**
     *  Method that add a new game to the list of games.
     *
     */
    public void addNewGame(Game game){
        listOfGames.add(game);
    }

    /**
     *  Method that returns Game object based on player's name which is in this game.
     * @param player contains player's name.
     * @return object of Game.
     */
    public Game getGameByNameOfPlayer(String player){
        Game wantedGame=null;
        for(Game g : listOfGames)
        {

            if(g.getPlayer(1).equals(player) || g.getPlayer(2).equals(player))
            {
                wantedGame=g;
            }
        }
        return wantedGame;
    }

    /**
     *  Method creates new game and returns an object of it.
     * @return object of Game.
     */
    public Game startGame() {

        Game game = null;

        if(block) {
            block = false;
            addToCurrentGame(queue.get(1));
            addToCurrentGame(queue.get(0));

            Client player_1 = accessor.getClient(queue.get(1));
            queue.remove(1);
            Client player_2 = accessor.getClient(queue.get(0));
            queue.remove(0);

            game = new Game();
            game.setPlayers(player_1, player_2);
            game.startGame();
            block = true;
        }
        return game;
    }
}
