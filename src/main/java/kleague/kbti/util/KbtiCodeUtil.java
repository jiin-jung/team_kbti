package kleague.kbti.util;

import kleague.kbti.dto.KbtiDimension;

import java.util.List;
import java.util.Map;

public class KbtiCodeUtil {

    private static final Map<String, String> KBTI_DESCRIPTIONS = Map.ofEntries(
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

    public static String getDescription(String kbti) {
        if (kbti == null) return null;
        String code = kbti.trim().toUpperCase();
        return KBTI_DESCRIPTIONS.getOrDefault(code, "분석 중입니다...");
    }

    public static List<KbtiDimension> getDetails(String kbti) {
        if (kbti == null) return List.of();
        String code = kbti.trim().toUpperCase();
        if (code.length() != 4) return List.of();

        char c1 = code.charAt(0); // Tempo: F/S
        char c2 = code.charAt(1); // Directness: L/S
        char c3 = code.charAt(2); // Pressing: A/P
        char c4 = code.charAt(3); // Fight: T/D

        return List.of(
                detailTempo(c1),
                detailDirectness(c2),
                detailPressing(c3),
                detailFight(c4)
        );
    }

    private static KbtiDimension detailTempo(char c) {
        if (c == 'F') return new KbtiDimension("F", "빠른 전개", "공을 탈취하면 빠르게 전진하며 속도감 있는 공격 전개를 선호");
        if (c == 'S') return new KbtiDimension("S", "차분한 전개", "점유와 빌드업으로 템포를 조절하며 안정적으로 전개");
        return new KbtiDimension(String.valueOf(c), "전개 성향", "정의되지 않은 코드");
    }

    private static KbtiDimension detailDirectness(char c) {
        if (c == 'S') return new KbtiDimension("S", "짧은 패스 지향", "짧고 정교한 패스로 점유를 유지하며 기회를 만들려는 성향");
        if (c == 'L') return new KbtiDimension("L", "직선/롱패스 지향", "전방으로 빠르게 연결하는 직선적인 전개와 롱패스를 선호");
        return new KbtiDimension(String.valueOf(c), "패스 성향", "정의되지 않은 코드");
    }

    private static KbtiDimension detailPressing(char c) {
        if (c == 'A') return new KbtiDimension("A", "강한 압박", "전방 압박으로 공을 탈취하고 즉시 공격 전환을 시도");
        if (c == 'P') return new KbtiDimension("P", "조절된 압박", "라인과 조직을 우선하며 압박 강도를 조절");
        return new KbtiDimension(String.valueOf(c), "압박 성향", "정의되지 않은 코드");
    }

    private static KbtiDimension detailFight(char c) {
        if (c == 'T') return new KbtiDimension("T", "강한 경합", "피지컬과 활동량으로 경합을 이겨내며 밀어붙이는 성향");
        if (c == 'D') return new KbtiDimension("D", "정교함 지향", "거친 경합보다는 기술/정교함 중심으로 경기를 풀어가는 성향");
        return new KbtiDimension(String.valueOf(c), "경합 성향", "정의되지 않은 코드");
    }
}
