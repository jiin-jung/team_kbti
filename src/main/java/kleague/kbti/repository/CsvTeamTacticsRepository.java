package kleague.kbti.repository;

import kleague.kbti.loader.TeamTacticsCsvLoader;
import kleague.kbti.model.TeamTactics;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CsvTeamTacticsRepository implements TeamTacticsRepository {

    private final List<TeamTactics> teams;

    public CsvTeamTacticsRepository(TeamTacticsCsvLoader loader) {
        this.teams = List.copyOf(loader.load());
    }

    @Override
    public List<TeamTactics> findAll() {
        return teams;
    }

    @Override
    public Optional<TeamTactics> findById(int teamId) {
        return teams.stream()
                .filter(team -> team.getTeamId() == teamId)
                .findFirst();
    }
}
