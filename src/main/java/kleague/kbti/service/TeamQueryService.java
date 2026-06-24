package kleague.kbti.service;

import kleague.kbti.dto.response.TeamRankResponse;
import kleague.kbti.loader.row.TeamRankingRow;
import kleague.kbti.loader.TeamRankingCsvLoader;
import kleague.kbti.dto.response.TeamResponse;
import kleague.kbti.exception.code.TeamErrorCode;
import kleague.kbti.exception.domain.TeamException;
import kleague.kbti.mapper.TeamResponseMapper;
import kleague.kbti.model.TeamTactics;
import kleague.kbti.repository.TeamTacticsRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class TeamQueryService {

    private final List<TeamTactics> teams;
    private final List<TeamRankingRow> rankings;
    private final TeamResponseMapper teamResponseMapper;

    public TeamQueryService(
            TeamTacticsRepository teamTacticsRepository,
            TeamRankingCsvLoader rankingLoader,
            TeamResponseMapper teamResponseMapper
    ) {
        this.teams = teamTacticsRepository.findAll();
        this.rankings = rankingLoader.load();
        this.teamResponseMapper = teamResponseMapper;
    }

    public List<TeamResponse> findAll() {
        return teams.stream()
                .map(teamResponseMapper::toResponse)
                .toList();
    }

    public TeamResponse findById(int teamId) {
        TeamTactics team = teams.stream()
                .filter(t -> t.getTeamId() == teamId)
                .findFirst()
                .orElseThrow(() ->
                        new TeamException(TeamErrorCode.TEAM_NOT_FOUND, "존재하지 않는 팀 ID: " + teamId)
                );

        return teamResponseMapper.toResponse(team);
    }

    public List<TeamRankResponse> findAllSortedByRank() {
        Map<String, TeamTactics> teamMap = teams.stream()
                .collect(Collectors.toMap(TeamTactics::getTeamName, t -> t));

        return rankings.stream()
                .map(r -> {
                    TeamTactics team = teamMap.get(r.getTeamName());
                    if (team == null) {
                        throw new TeamException(
                                TeamErrorCode.TEAM_RANKING_MISMATCH,
                                "랭킹 CSV의 팀명이 전술 데이터와 매칭되지 않음: " + r.getTeamName()
                        );
                    }
                    return teamResponseMapper.toRankResponse(r, team);
                })
                .toList();
    }
}
