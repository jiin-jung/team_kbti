package kleague.kbti.service;

import kleague.kbti.domain.MatchData;
import org.springframework.stereotype.Service;

@Service
public class TacticsAnalysisService {

    public Double calculateTempoIndex(MatchData match) {
        // 수직 전진 거리 합산 (x좌표 차이)
        double totalVerticalDistance = match.getPasses().stream()
                .mapToDouble(p -> Math.max(0, p.getArrivalX() - p.getStartX())) // 백패스 0 처리 (방법 2 선택)
                .sum();

        // 수직 전진 속도 (m/s)
        double vVelocity = totalVerticalDistance / match.getPossessionTimeSeconds();

        // 패스 빈도 (회/분)
        double passFreq = match.getTotalPasses() / (match.getPossessionTimeSeconds() / 60.0);

        return vVelocity * passFreq;
    }
}