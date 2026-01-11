package kleague.kbti.service;

import kleague.kbti.dto.TeamRankResponse;
import kleague.kbti.dto.TeamRankingRow;
import kleague.kbti.dto.TeamResponse;
import kleague.kbti.dto.TeamTactics;
import kleague.kbti.loader.TeamRankingCsvLoader;
import kleague.kbti.loader.TeamTacticsCsvLoader;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class TeamQueryService {

    private final List<TeamTactics> teams;
    private final List<TeamRankingRow> rankings;

    public TeamQueryService(
            TeamTacticsCsvLoader loader,
            TeamRankingCsvLoader rankingLoader
    ) {
        this.teams = loader.load();
        this.rankings = rankingLoader.load();
    }

    // 전체 팀 (랭킹 없음)
    public List<TeamResponse> findAll() {
        return teams.stream()
                .map(TeamResponse::from)
                .toList();
    }

    // 단일 팀 조회
    public TeamResponse findById(int teamId) {
        TeamTactics team = teams.stream()
                .filter(t -> t.getTeamId() == teamId)
                .findFirst()
                .orElseThrow(() ->
                        new IllegalArgumentException("존재하지 않는 팀 ID: " + teamId)
                );

        return TeamResponse.from(team);
    }

    // 랭킹 기준 조회
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
                    return TeamRankResponse.from(r, team);
                })
                .toList();
    }
}
