package kleague.kbti.recommendation.matcher;

import kleague.kbti.model.TacticalVector;
import kleague.kbti.model.TeamTactics;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class EuclideanTeamMatcherTest {

    private final EuclideanTeamMatcher matcher = new EuclideanTeamMatcher();

    @Test
    void findsNearestTeamByEuclideanDistance() {
        TeamTactics close = new TeamTactics(1, "가까운팀", 0, new TacticalVector(80, 60, 40, 50, 20));
        TeamTactics far = new TeamTactics(2, "먼팀", 0, new TacticalVector(10, 10, 10, 10, 10));

        TeamTactics result = matcher.findBestMatch(
                new TacticalVector(82, 59, 40, 50, 20),
                List.of(far, close)
        );

        assertThat(result).isSameAs(close);
    }

    @Test
    void rejectsEmptyTeamData() {
        assertThatThrownBy(() -> matcher.findBestMatch(new TacticalVector(1, 1, 1, 1, 1), List.of()))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("팀 전술 데이터");
    }
}
