package kleague.kbti.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PlayerRankResponse {

    private int rank;

    private String playerName;
    private String teamName;
    private String position;
    private String roleGroup;

    private double rawScore;
    private int games;
    private double aiRating;

    public static PlayerRankResponse from(int rank, PlayerRatingRow r) {
        return PlayerRankResponse.builder()
                .rank(rank)
                .playerName(r.getPlayerName())
                .teamName(r.getTeamName())
                .position(r.getPosition())
                .roleGroup(r.getRoleGroup())
                .rawScore(r.getRawScore())
                .games(r.getGames())
                .aiRating(r.getAiRating())
                .build();
    }
}
