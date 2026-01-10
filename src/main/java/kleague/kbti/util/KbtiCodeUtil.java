package kleague.kbti.util;

import kleague.kbti.dto.TeamTactics;

public final class KbtiCodeUtil {

    private KbtiCodeUtil() {}

    // 팀 전술값을 KBTI 4글자 코드로 변환
    public static String fromTeam(TeamTactics t) {
        StringBuilder code = new StringBuilder();

        code.append(t.getTempo() >= 3 ? "F" : "S");        // tempo
        code.append(t.getDirectness() >= 3 ? "L" : "S");   // directness
        code.append(t.getPressing() >= 3 ? "A" : "P");     // pressing
        code.append(t.getFight() >= 3 ? "T" : "D");        // fight

        return code.toString();
    }
}
