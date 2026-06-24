package kleague.kbti.service;

import kleague.kbti.dto.request.KbtiRequest;
import kleague.kbti.dto.response.KbtiResponse;
import kleague.kbti.model.TacticalVector;
import kleague.kbti.model.TeamTactics;
import kleague.kbti.recommendation.code.KbtiCodeGenerator;
import kleague.kbti.recommendation.matcher.TeamMatcher;
import kleague.kbti.recommendation.vector.PreferenceVectorMapper;
import kleague.kbti.repository.TeamTacticsRepository;
import kleague.kbti.util.KbtiCodeUtil;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KbtiService {

    private final TeamTacticsRepository teamTacticsRepository;
    private final KbtiCodeGenerator kbtiCodeGenerator;
    private final PreferenceVectorMapper preferenceVectorMapper;
    private final TeamMatcher teamMatcher;

    public KbtiService(
            TeamTacticsRepository teamTacticsRepository,
            KbtiCodeGenerator kbtiCodeGenerator,
            PreferenceVectorMapper preferenceVectorMapper,
            TeamMatcher teamMatcher
    ) {
        this.teamTacticsRepository = teamTacticsRepository;
        this.kbtiCodeGenerator = kbtiCodeGenerator;
        this.preferenceVectorMapper = preferenceVectorMapper;
        this.teamMatcher = teamMatcher;
    }

    public KbtiResponse findBestMatch(KbtiRequest request) {
        List<TeamTactics> teams = teamTacticsRepository.findAll();
        String kbtiCode = kbtiCodeGenerator.fromRequest(request);
        TacticalVector userVector = preferenceVectorMapper.map(request);
        TeamTactics bestMatch = teamMatcher.findBestMatch(userVector, teams);

        String description = KbtiCodeUtil.getDescription(kbtiCode);

        return KbtiResponse.of(
                bestMatch.getTeamId(),
                bestMatch.getTeamName(),
                kbtiCode,
                description
        );
    }
}
