package kleague.kbti.recommendation.code;

import kleague.kbti.dto.request.KbtiRequest;
import kleague.kbti.model.TeamTactics;
import org.springframework.stereotype.Component;

@Component
public class ThresholdKbtiCodeGenerator implements KbtiCodeGenerator {

    private static final int REQUEST_THRESHOLD = 3;
    private static final double TEAM_THRESHOLD = 50.0;

    @Override
    public String fromRequest(KbtiRequest request) {
        return code(
                request.getTempo() >= REQUEST_THRESHOLD,
                request.getDirectness() >= REQUEST_THRESHOLD,
                request.getPressing() >= REQUEST_THRESHOLD,
                request.getFight() >= REQUEST_THRESHOLD
        );
    }

    @Override
    public String fromTeam(TeamTactics team) {
        return code(
                team.getTempo() >= TEAM_THRESHOLD,
                team.getDirectness() >= TEAM_THRESHOLD,
                team.getPressing() >= TEAM_THRESHOLD,
                team.getFight() >= TEAM_THRESHOLD
        );
    }

    private String code(boolean fast, boolean longPass, boolean aggressivePress, boolean tough) {
        StringBuilder code = new StringBuilder();
        code.append(fast ? "F" : "S");
        code.append(longPass ? "L" : "S");
        code.append(aggressivePress ? "A" : "P");
        code.append(tough ? "T" : "D");
        return code.toString();
    }
}
