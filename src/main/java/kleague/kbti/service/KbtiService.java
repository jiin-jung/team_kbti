package kleague.kbti.service;

import kleague.kbti.dto.KbtiRequest;
import kleague.kbti.dto.KbtiResponse;
import kleague.kbti.dto.TeamTactics;
import kleague.kbti.loader.TeamTacticsCsvLoader;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Map;

@Service
public class KbtiService {

    // KBTI
    private final Map<String, String> kbtiDescriptions = Map.ofEntries(
            Map.entry("SSPD", "차분한 빌드업과 정교한 패스를 추구하는 '그라운드의 예술가'"),
            Map.entry("SSPT", "점유율을 중시하면서도 강한 경합을 마다하지 않는 '중원의 지배자'"),
            Map.entry("SSAD", "세밀한 패스로 경기를 풀어나가며 전방 압박을 멈추지 않는 '지능적 압박가'"),
            Map.entry("SSAT", "조직적인 패스 워크와 헌신적인 활동량을 겸비한 '무결점 살림꾼'"),
            Map.entry("SLPD", "안정적인 수비를 바탕으로 날카로운 롱패스 한 방을 노리는 '후방의 설계자'"),
            Map.entry("SLPT", "거친 몸싸움으로 공을 따내어 전방으로 길게 뿌려주는 '불굴의 보급선'"),
            Map.entry("SLAD", "높은 수비 라인에서 공을 탈취해 즉시 롱패스로 연결하는 '전략적 사냥꾼'"),
            Map.entry("SLAT", "끊임없는 압박과 시원한 롱볼로 상대를 정신없게 만드는 '질주하는 전차'"),
            Map.entry("FSPD", "빠른 속도로 측면을 허물고 짧은 패스로 기회를 만드는 '섬세한 단검'"),
            Map.entry("FSPT", "거친 몸싸움을 이겨내며 빠른 속도로 전진하는 '탱크형 윙어' 스타일"),
            Map.entry("FSAD", "숨막히는 압박으로 공을 뺏자마자 짧고 빠르게 역습하는 '전술적 스나이퍼'"),
            Map.entry("FSAT", "지치지 않는 체력으로 압박하고 폭풍처럼 몰아치는 '에너제틱 해결사'"),
            Map.entry("FLPD", "단 몇 번의 터치와 긴 패스만으로 상대 골문을 위협하는 '효율적 종결자'"),
            Map.entry("FLPT", "피지컬을 앞세워 롱볼을 따내고 속도감 있게 밀어붙이는 '선 굵은 돌격대'"),
            Map.entry("FLAD", "전방 압박 후 바로 빈 공간에 롱패스를 찌러넣는 '속도광 지략가'"),
            Map.entry("FLAT", "압도적인 활동량과 직선적인 축구로 승리를 쟁취하는 '무적의 야생마'")
    );

    // CSV 기반 팀 전술 데이터
    private final List<TeamTactics> teamTactics;

    public KbtiService(TeamTacticsCsvLoader loader) {
        this.teamTactics = loader.load();
    }

    public KbtiResponse findBestMatch(KbtiRequest request) {

        // 방어 코드
        if (teamTactics == null || teamTactics.isEmpty()) {
            throw new IllegalStateException("teamTactics not initialized");
        }

        // 4글자 KBTI 코드 생성
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

        // 결과 반환
        String description = kbtiDescriptions.getOrDefault(kbtiCode, "분석 중입니다...");
        return KbtiResponse.of(
                bestMatch.getTeamId(),
                bestMatch.getTeamName(),
                kbtiCode,
                description
        );
    }

    // ------------------ 내부 로직 ------------------

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
            sum += Math.pow(v1[i] - v2[i], 2);
        }
        return Math.sqrt(sum);
    }
}
