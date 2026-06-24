package kleague.kbti.dto.response;

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
}
