package kleague.kbti.repository;

import kleague.kbti.model.TeamTactics;

import java.util.List;
import java.util.Optional;

public interface TeamTacticsRepository {

    List<TeamTactics> findAll();

    Optional<TeamTactics> findById(int teamId);
}
