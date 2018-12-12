package com.project.app.gameauthorization;

import com.project.app.model.Client;
import com.project.app.model.ClientsDataAccessor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class QueueAccessor {

    private boolean block = true;

    private QueueAccessor()
    {
        queue = new ArrayList<>();
        currentgames = new ArrayList<>();
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


    public boolean isInQueue(String player){

        return queue.contains(player);
    }

    public void addToQueue(String player){
        queue.add(player);
    }

    public boolean twoElementsAtQueue(){
        return (queue.size() >= 2);
    }

    public void addToCurrentGame(String player){
        currentgames.add(player);
    }

    public boolean isInGame(String player){

        return currentgames.contains(player);
    }

    public void startGame() {

        if(block) {
            block = false;
            //Alfa version
            addToCurrentGame(queue.get(1));
            addToCurrentGame(queue.get(0));

            Client player_1 = accessor.getClient(queue.get(1));
            queue.remove(1);
            Client player_2 = accessor.getClient(queue.get(0));
            queue.remove(0);

            //panie prowizarka jakich mało
            Game game = new Game();
            game.setPlayers(player_1, player_2);
            game.startGame();
            block = true;
        }
    }
}
