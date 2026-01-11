package kleague.kbti.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PlayerRatingRow {

    private String playerName;      // 선수명
    private String teamName;        // 팀명
    private String position;        // 포지션
    private String roleGroup;       // 역할군
    private double rawScore;        // 평가점수_Raw
    private int games;              // 경기수
    private double aiRating;        // AI평점
}
