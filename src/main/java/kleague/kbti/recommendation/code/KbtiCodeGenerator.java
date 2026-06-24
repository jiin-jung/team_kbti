package kleague.kbti.recommendation.code;

import kleague.kbti.dto.request.KbtiRequest;
import kleague.kbti.model.TeamTactics;

public interface KbtiCodeGenerator {

    String fromRequest(KbtiRequest request);

    String fromTeam(TeamTactics team);
}
