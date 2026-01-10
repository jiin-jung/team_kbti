package kleague.kbti.service;

import kleague.kbti.domain.TeamStats;
import org.springframework.stereotype.Service;

@Service
public class TeamVibeService {

    /**
     * 상세 스탯을 바탕으로 팀의 성향 요약(Vibe Check)을 생성합니다.
     */
    public String generateVibeCheck(TeamStats stats) {
        StringBuilder vibe = new StringBuilder();

        // 전술 유형별 서두 (분석하신 KMeans 결과 반영)
        vibe.append(getTypeIntro(stats.getTypeId()));

        // 공격 지표 분석 (Real_Goals_PerGame)
        if (stats.getGoalsPerGame() >= 1.45) {
            vibe.append(String.format("경기당 평균 %.2f골을 터뜨리는 K리그 최고의 화력을 보유하고 있습니다. ", stats.getGoalsPerGame()));
        } else if (stats.getShotOnTarget() >= 4.0) {
            vibe.append("득점 여부와 상관없이 끊임없이 유효 슈팅을 만들어내며 상대를 압박하는 팀입니다. ");
        }

        // 빌드업 품질 분석 (Pass_SuccessRate)
        if (stats.getPassSuccessRate() >= 87.0) {
            vibe.append(String.format("%.1f%%의 정교한 패스 성공률로 중원을 장악하는 '교과서적인 축구'를 구사하죠. ", stats.getPassSuccessRate()));
        }

        // 수비적 특성 분석 (Clearance_PerGame)
        if (stats.getClearancePerGame() >= 26.0) {
            vibe.append(String.format("위기 상황마다 터져 나오는 평균 %.1f개의 클리어런스는 이 팀의 뒷문이 얼마나 단단한지 보여줍니다. ", stats.getClearancePerGame()));
        }

        // 투지 분석 (Revised CSV의 Fight 점수)
        if (stats.getFight() >= 70) {
            vibe.append("거친 몸싸움과 끝까지 포기하지 않는 투지가 이 팀의 진짜 매력 포인트입니다!");
        }

        return vibe.toString();
    }

    private String getTypeIntro(int typeId) {
        return switch (typeId) {
            case 0 -> "[심미적 지배자] ";
            case 1 -> "[날카로운 사냥꾼] ";
            case 2 -> "[에너제틱 전방압박] ";
            case 3 -> "[불굴의 전사] ";
            default -> "[매력적인 팀] ";
        };
    }
}