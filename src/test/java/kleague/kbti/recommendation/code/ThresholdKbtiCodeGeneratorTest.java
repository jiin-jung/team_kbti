package kleague.kbti.recommendation.code;

import kleague.kbti.dto.request.KbtiRequest;
import kleague.kbti.model.TacticalVector;
import kleague.kbti.model.TeamTactics;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ThresholdKbtiCodeGeneratorTest {

    private final ThresholdKbtiCodeGenerator generator = new ThresholdKbtiCodeGenerator();

    @Test
    void createsCodeFromRequestThresholds() {
        KbtiRequest request = new KbtiRequest();
        request.setTempo(5);
        request.setDirectness(1);
        request.setPressing(3);
        request.setFight(2);

        assertThat(generator.fromRequest(request)).isEqualTo("FSAD");
    }

    @Test
    void createsCodeFromTeamThresholds() {
        TeamTactics team = new TeamTactics(
                1,
                "테스트FC",
                0,
                new TacticalVector(49.9, 50.0, 50.1, 10.0, 49.0)
        );

        assertThat(generator.fromTeam(team)).isEqualTo("SLAD");
    }
}
