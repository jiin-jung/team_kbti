package kleague.kbti.service;

import kleague.kbti.dto.response.TeamRankResponse;
import kleague.kbti.loader.row.TeamRankingRow;
import kleague.kbti.loader.TeamRankingCsvLoader;
import kleague.kbti.dto.response.TeamResponse;
import kleague.kbti.model.TeamTactics;
import kleague.kbti.recommendation.code.KbtiCodeGenerator;
import kleague.kbti.repository.TeamTacticsRepository;
import kleague.kbti.util.KbtiCodeUtil;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class TeamQueryService {

    private final List<TeamTactics> teams;
    private final List<TeamRankingRow> rankings;
    private final KbtiCodeGenerator kbtiCodeGenerator;

    public TeamQueryService(
            TeamTacticsRepository teamTacticsRepository,
            TeamRankingCsvLoader rankingLoader,
            KbtiCodeGenerator kbtiCodeGenerator
    ) {
        this.teams = teamTacticsRepository.findAll();
        this.rankings = rankingLoader.load();
        this.kbtiCodeGenerator = kbtiCodeGenerator;
    }

    public List<TeamResponse> findAll() {
        return teams.stream()
                .map(team -> toResponse(null, team))
                .toList();
    }

    public TeamResponse findById(int teamId) {
        TeamTactics team = teams.stream()
                .filter(t -> t.getTeamId() == teamId)
                .findFirst()
                .orElseThrow(() ->
                        new IllegalArgumentException("존재하지 않는 팀 ID: " + teamId)
                );

        return toResponse(null, team);
    }

    public List<TeamRankResponse> findAllSortedByRank() {
        Map<String, TeamTactics> teamMap = teams.stream()
                .collect(Collectors.toMap(TeamTactics::getTeamName, t -> t));

        return rankings.stream()
                .map(r -> {
                    TeamTactics team = teamMap.get(r.getTeamName());
                    if (team == null) {
                        throw new IllegalStateException(
                                "랭킹 CSV의 팀명이 전술 데이터와 매칭되지 않음: " + r.getTeamName()
                        );
                    }
                    return TeamRankResponse.builder()
                            .rank(r.getRank())
                            .teamId(team.getTeamId())
                            .teamName(r.getTeamName())
                            .games(r.getGames())
                            .points(r.getPoints())
                            .goalDiff(r.getGoalDiff())
                            .win(r.getWin())
                            .draw(r.getDraw())
                            .loss(r.getLoss())
                            .goalsFor(r.getGoalsFor())
                            .goalsAgainst(r.getGoalsAgainst())
                            .teamKbti(kbtiCodeGenerator.fromTeam(team))
                            .build();
                })
                .toList();
    }

    private TeamResponse toResponse(Integer rank, TeamTactics team) {
        String kbti = kbtiCodeGenerator.fromTeam(team);

        return TeamResponse.builder()
                .rank(rank)
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
}
