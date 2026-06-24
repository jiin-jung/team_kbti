package kleague.kbti.recommendation.vector;

import kleague.kbti.dto.request.KbtiRequest;
import kleague.kbti.model.TacticalVector;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ScaledPreferenceVectorMapperTest {

    private final ScaledPreferenceVectorMapper mapper = new ScaledPreferenceVectorMapper();

    @Test
    void mapsRequestToScaledTacticalVector() {
        KbtiRequest request = new KbtiRequest();
        request.setTempo(5);
        request.setDirectness(4);
        request.setPressing(2);
        request.setFight(1);

        TacticalVector vector = mapper.map(request);

        assertThat(vector.values()).containsExactly(100.0, 80.0, 40.0, 50.0, 20.0);
    }
}
