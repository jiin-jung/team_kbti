package kleague.kbti.service;

import kleague.kbti.exception.code.TeamErrorCode;
import kleague.kbti.exception.domain.TeamException;
import kleague.kbti.loader.TeamRankingCsvLoader;
import kleague.kbti.mapper.TeamResponseMapper;
import kleague.kbti.model.TacticalVector;
import kleague.kbti.model.TeamTactics;
import kleague.kbti.recommendation.code.ThresholdKbtiCodeGenerator;
import kleague.kbti.repository.TeamTacticsRepository;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class TeamQueryServiceTest {

    @Test
    void throwsResourceNotFoundWhenTeamDoesNotExist() {
        TeamQueryService service = new TeamQueryService(
                new StubTeamTacticsRepository(),
                new TeamRankingCsvLoader(),
                new TeamResponseMapper(new ThresholdKbtiCodeGenerator())
        );

        assertThatThrownBy(() -> service.findById(999))
                .isInstanceOf(TeamException.class)
                .extracting("errorCode")
                .isEqualTo(TeamErrorCode.TEAM_NOT_FOUND);
    }

    private static class StubTeamTacticsRepository implements TeamTacticsRepository {

        @Override
        public List<TeamTactics> findAll() {
            return List.of(new TeamTactics(1, "테스트FC", 0, new TacticalVector(50, 50, 50, 50, 50)));
        }

        @Override
        public Optional<TeamTactics> findById(int teamId) {
            return findAll().stream()
                    .filter(team -> team.getTeamId() == teamId)
                    .findFirst();
        }
    }
}
