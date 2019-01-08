package com.project.app.gameauthorization;

public class Elo {

    public EloTuple calculateEloRating(EloTuple x){

        float WinnerProbabilityElo = probability(x.LoserElo, x.WinnerElo);
        float LoserProbabilityElo  = probability(x.WinnerElo, x.LoserElo);

        float RWinner = (float) x.WinnerElo + 44 * (1 - WinnerProbabilityElo);
        float RLoser = (float) x.LoserElo + 44 * (0 - LoserProbabilityElo);

        x.WinnerElo = Math.round(RWinner);
        x.LoserElo = Math.round(RLoser);

        return x;
    }

    private float probability(int firstPersonElo, int secondPersonElo) {

        float rating1 = (float) firstPersonElo;
        float rating2 = (float) secondPersonElo;

        return 1.0f * 1.0f / (1 + 1.0f *
                (float)(Math.pow(10, 1.0f *
                        (rating1 - rating2) / 400)));

    }


}
