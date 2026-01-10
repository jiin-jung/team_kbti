package kleague.kbti.service;

import kleague.kbti.dto.TeamResponse;
import kleague.kbti.dto.TeamTactics;
import kleague.kbti.loader.TeamTacticsCsvLoader;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
public class TeamQueryService {

    private final List<TeamTactics> teams;

    public TeamQueryService(TeamTacticsCsvLoader loader) {
        this.teams = loader.load();
    }

    // 전체 조회 (/api/teams)
    public List<TeamResponse> findAll() {
        return teams.stream()
                .map(TeamResponse::from)
                .toList();
    }

    // 단일 조회 (/api/teams/{id})
    public TeamResponse findById(int teamId) {
        TeamTactics team = teams.stream()
                .filter(t -> t.getTeamId() == teamId)
                .findFirst()
                .orElseThrow(() ->
                        new IllegalArgumentException("존재하지 않는 팀 ID: " + teamId)
                );

        return TeamResponse.from(team);
    }

    // 이름순 + rank 부여 < 추후에 실제 순위로 변경
    public List<TeamResponse> findAllSortedByRank() {
        List<TeamTactics> sorted = teams.stream()
                .sorted(Comparator.comparing(TeamTactics::getTeamName))
                .toList();

        return java.util.stream.IntStream.range(0, sorted.size())
                .mapToObj(i -> TeamResponse.from(i + 1, sorted.get(i))) // rank + teamKbti 포함
                .toList();
    }
}
