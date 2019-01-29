package com.project.app.gameauthorization;

import com.project.app.model.Client;
import com.project.app.model.ClientsDataAccessor;

import java.util.*;

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


    public void removeTimer(String player){
        timer.remove(player);
    }

    public void addTimeToTimer(String player){
        Integer i = timer.get(player);
        timer.remove(player);
        timer.put(player, i + 1);
    }

    public int getTime(String player){
        return timer.get(player);
    }

    public boolean isInQueue(String player){

        return queue.contains(player);
    }

    public boolean removeFromQueue(String player){

        return queue.remove(player);
    }

    public void addToQueue(String player){
        queue.add(player);
        timer.put(player, 0);
    }

    public boolean twoElementsAtQueue(){
        return (queue.size() >= 2);
    }


    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    public void addToCurrentGame(String player){
        currentgames.add(player);
    }

    public boolean isInGame(String player){

        return currentgames.contains(player);
    }

    public void removeCurrentGame(String player){
        currentgames.remove(player);
    }


    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    public void addNewGame(Game game){
        listOfGames.add(game);
    }

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
