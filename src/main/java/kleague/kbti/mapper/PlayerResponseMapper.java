package kleague.kbti.mapper;

import kleague.kbti.dto.response.PlayerRankResponse;
import kleague.kbti.loader.row.PlayerRatingRow;
import org.springframework.stereotype.Component;

@Component
public class PlayerResponseMapper {

    public PlayerRankResponse toRankResponse(int rank, PlayerRatingRow row) {
        return PlayerRankResponse.builder()
                .rank(rank)
                .playerName(row.getPlayerName())
                .teamName(row.getTeamName())
                .position(row.getPosition())
                .roleGroup(row.getRoleGroup())
                .rawScore(row.getRawScore())
                .games(row.getGames())
                .aiRating(row.getAiRating())
                .build();
    }
}
