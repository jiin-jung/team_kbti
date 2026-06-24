package kleague.kbti.recommendation.matcher;

import kleague.kbti.model.TacticalVector;
import kleague.kbti.model.TeamTactics;

import java.util.List;

public interface TeamMatcher {

    TeamTactics findBestMatch(TacticalVector userVector, List<TeamTactics> teams);
}
