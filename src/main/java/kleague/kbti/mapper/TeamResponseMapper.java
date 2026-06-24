package kleague.kbti.mapper;

import kleague.kbti.dto.response.TeamRankResponse;
import kleague.kbti.dto.response.TeamResponse;
import kleague.kbti.loader.row.TeamRankingRow;
import kleague.kbti.model.TeamTactics;
import kleague.kbti.recommendation.code.KbtiCodeGenerator;
import kleague.kbti.util.KbtiCodeUtil;
import org.springframework.stereotype.Component;

@Component
public class TeamResponseMapper {

    private final KbtiCodeGenerator kbtiCodeGenerator;

    public TeamResponseMapper(KbtiCodeGenerator kbtiCodeGenerator) {
        this.kbtiCodeGenerator = kbtiCodeGenerator;
    }

    public TeamResponse toResponse(TeamTactics team) {
        String kbti = kbtiCodeGenerator.fromTeam(team);

        return TeamResponse.builder()
                .teamId(team.getTeamId())
                .teamName(team.getTeamName())
                .tempo(team.getTempo())
                .directness(team.getDirectness())
                .pressing(team.getPressing())
                .sideUsage(team.getSideUsage())
                .fight(team.getFight())
                .teamKbti(kbti)
                .teamKbtiDesc(KbtiCodeUtil.getDescription(kbti))
                .teamKbtiDetails(KbtiCodeUtil.getDetails(kbti))
                .build();
    }

    public TeamRankResponse toRankResponse(TeamRankingRow ranking, TeamTactics team) {
        return TeamRankResponse.builder()
                .rank(ranking.getRank())
                .teamId(team.getTeamId())
                .teamName(ranking.getTeamName())
                .games(ranking.getGames())
                .points(ranking.getPoints())
                .goalDiff(ranking.getGoalDiff())
                .win(ranking.getWin())
                .draw(ranking.getDraw())
                .loss(ranking.getLoss())
                .goalsFor(ranking.getGoalsFor())
                .goalsAgainst(ranking.getGoalsAgainst())
                .teamKbti(kbtiCodeGenerator.fromTeam(team))
                .build();
    }
}
