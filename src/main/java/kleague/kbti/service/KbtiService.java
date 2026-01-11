package kleague.kbti.service;

import kleague.kbti.dto.KbtiRequest;
import kleague.kbti.dto.KbtiResponse;
import kleague.kbti.dto.TeamTactics;
import kleague.kbti.loader.TeamTacticsCsvLoader;
import kleague.kbti.util.KbtiCodeUtil;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
public class KbtiService {

    private final List<TeamTactics> teamTactics;

    public KbtiService(TeamTacticsCsvLoader loader) {
        this.teamTactics = loader.load();
    }

    public KbtiResponse findBestMatch(KbtiRequest request) {

        if (teamTactics == null || teamTactics.isEmpty()) {
            throw new IllegalStateException("teamTactics not initialized");
        }

        // 4글자 KBTI 코드 생성 (사용자 입력 기준)
        String kbtiCode = generateKbtiCode(request);

        // 사용자 입력 벡터화
        double[] userVector = {
                request.getTempo() * 20.0,
                request.getDirectness() * 20.0,
                request.getPressing() * 20.0,
                50.0, // sideUsage 기본값
                request.getFight() * 20.0
        };

        // 가장 가까운 팀 탐색 (유클리드 거리)
        TeamTactics bestMatch = teamTactics.stream()
                .min(Comparator.comparingDouble(
                        t -> calculateDistance(userVector, t.getScores())
                ))
                .orElseThrow();

        // 설명은 util로 통일
        String description = KbtiCodeUtil.getDescription(kbtiCode);

        return KbtiResponse.of(
                bestMatch.getTeamId(),
                bestMatch.getTeamName(),
                kbtiCode,
                description
        );
    }

    private String generateKbtiCode(KbtiRequest req) {
        StringBuilder code = new StringBuilder();

        code.append(req.getTempo() >= 3 ? "F" : "S");
        code.append(req.getDirectness() >= 3 ? "L" : "S");
        code.append(req.getPressing() >= 3 ? "A" : "P");
        code.append(req.getFight() >= 3 ? "T" : "D");

        return code.toString();
    }

    private double calculateDistance(double[] v1, double[] v2) {
        double sum = 0;
        for (int i = 0; i < v1.length; i++) {
            double diff = v1[i] - v2[i];
            sum += diff * diff;
        }
        return Math.sqrt(sum);
    }
}
