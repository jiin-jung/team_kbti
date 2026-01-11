package kleague.kbti.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class TeamRankingRow {

    private int rank;
    private String teamName;

    private int games;
    private int points;
    private int goalDiff;
    private int win;
    private int draw;
    private int loss;
    private int goalsFor;
    private int goalsAgainst;
}