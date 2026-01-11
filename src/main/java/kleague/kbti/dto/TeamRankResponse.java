package kleague.kbti.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class TeamRankResponse {

    private int rank;
    private int teamId;
    private String teamName;

    private int games;         // 경기수
    private int points;        // 승점
    private int goalDiff;      // 득실차
    private int win;
    private int draw;
    private int loss;
    private int goalsFor;      // 득점
    private int goalsAgainst;  // 실점

    private String teamKbti;

    public static TeamRankResponse from(TeamRankingRow r, TeamTactics t) {
        return TeamRankResponse.builder()
                .rank(r.getRank())
                .teamId(t.getTeamId())
                .teamName(r.getTeamName())
                .games(r.getGames())
                .points(r.getPoints())
                .goalDiff(r.getGoalDiff())
                .win(r.getWin())
                .draw(r.getDraw())
                .loss(r.getLoss())
                .goalsFor(r.getGoalsFor())
                .goalsAgainst(r.getGoalsAgainst())
                .teamKbti(t.getTeamKbti())
                .build();
    }
}
