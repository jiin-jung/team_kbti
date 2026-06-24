package kleague.kbti.recommendation.matcher;

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
            throw new IllegalStateException("팀 전술 데이터가 초기화되지 않았습니다.");
        }

        return teams.stream()
                .min(Comparator.comparingDouble(team -> userVector.distanceTo(team.getVector())))
                .orElseThrow();
    }
}
