package kleague.kbti.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class TeamRankResponse {

    private int rank;
    private int teamId;
    private String teamName;

    private int games;
    private int points;
    private int goalDiff;
    private int win;
    private int draw;
    private int loss;
    private int goalsFor;
    private int goalsAgainst;

    private String teamKbti;
}
