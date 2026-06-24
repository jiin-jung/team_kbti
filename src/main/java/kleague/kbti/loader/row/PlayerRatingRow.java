package kleague.kbti.loader.row;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PlayerRatingRow {

    private String playerName;
    private String teamName;
    private String position;
    private String roleGroup;
    private double rawScore;
    private int games;
    private double aiRating;
}
