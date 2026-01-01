package kleague.kbti.domain;

import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter @Setter
public class MatchData {
    private double possessionTimeSeconds;
    private List<Pass> passes;

    @Getter @Setter
    public static class Pass {
        private double startX;
        private double arrivalX;
    }

    public int getTotalPasses() {
        return passes != null ? passes.size() : 0;
    }
}