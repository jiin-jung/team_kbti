package kleague.kbti.dto;

import kleague.kbti.util.KbtiCodeUtil;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class TeamResponse {

    private Integer rank;      // rank endpoint에서만 사용(없으면 null)
    private int teamId;
    private String teamName;

    private double tempo;
    private double directness;
    private double pressing;
    private double sideUsage;
    private double fight;

    // 팀 KBTI 코드
    private String teamKbti;

    // 기본: rank 없이
    public static TeamResponse from(TeamTactics t) {
        return from(null, t);
    }

    // rank 포함 버전
    public static TeamResponse from(Integer rank, TeamTactics t) {
        return TeamResponse.builder()
                .rank(rank)
                .teamId(t.getTeamId())
                .teamName(t.getTeamName())
                .tempo(t.getTempo())
                .directness(t.getDirectness())
                .pressing(t.getPressing())
                .sideUsage(t.getSideUsage())
                .fight(t.getFight())
                .teamKbti(t.getTeamKbti())
                .build();
    }
}
