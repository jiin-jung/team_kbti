package kleague.kbti.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class TeamTactics {
    private int teamId;
    private String teamName;
    private int typeId;
    private TacticalVector vector;

    public double getTempo() {
        return vector.tempo();
    }

    public double getDirectness() {
        return vector.directness();
    }

    public double getPressing() {
        return vector.pressing();
    }

    public double getSideUsage() {
        return vector.sideUsage();
    }

    public double getFight() {
        return vector.fight();
    }
}
