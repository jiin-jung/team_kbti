package kleague.kbti.recommendation.matcher;

import kleague.kbti.exception.code.RecommendationErrorCode;
import kleague.kbti.exception.domain.RecommendationException;
import kleague.kbti.model.TacticalVector;
import kleague.kbti.model.TeamTactics;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;

@Component
public class EuclideanTeamMatcher implements TeamMatcher {

    @Override
    public TeamTactics findBestMatch(TacticalVector userVector, List<TeamTactics> teams) {
        if (teams == null || teams.isEmpty()) {
            throw new RecommendationException(RecommendationErrorCode.TEAM_TACTICS_EMPTY);
        }

        return teams.stream()
                .min(Comparator.comparingDouble(team -> userVector.distanceTo(team.getVector())))
                .orElseThrow();
    }
}
