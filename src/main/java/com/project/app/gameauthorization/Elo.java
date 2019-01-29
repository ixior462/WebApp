package com.project.app.gameauthorization;

/**
 * Class that calculates how many elo points players gonna loss or gain after a game.
 * @author      Paweł Krupski
 * @version     1.0
 */
public class Elo {

    /**
     *  Method that calculates how many elo points players gonna loss or gain after a game.
     * @param x object of EloTuple'a class that contains information about each player elo points before the game.
     * @return x bject of EloTuple'a class that contains information about each player elo points after the game.
     */
    public EloTuple calculateEloRating(EloTuple x){

        float WinnerProbabilityElo = probability(x.LoserElo, x.WinnerElo);
        float LoserProbabilityElo  = probability(x.WinnerElo, x.LoserElo);

        float RWinner = (float) x.WinnerElo + 44 * (1 - WinnerProbabilityElo);
        float RLoser = (float) x.LoserElo + 44 * (0 - LoserProbabilityElo);

        x.WinnerElo = Math.round(RWinner);
        x.LoserElo = Math.round(RLoser);

        return x;
    }


    /**
     *  Method that calculates the the probability of winning the game by each user.
     * @param firstPersonElo elo points of first player.
     * @param secondPersonElo elo points of second player.
     * @return the probability of winning the game by each user.
     */
    private float probability(int firstPersonElo, int secondPersonElo) {

        float rating1 = (float) firstPersonElo;
        float rating2 = (float) secondPersonElo;

        return 1.0f * 1.0f / (1 + 1.0f *
                (float)(Math.pow(10, 1.0f *
                        (rating1 - rating2) / 400)));

    }


}
