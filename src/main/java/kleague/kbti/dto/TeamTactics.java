/**
 * 팀별 전술 데이터를 담는 객체
 */

package kleague.kbti.dto;

import lombok.*;

@Getter @Setter
@AllArgsConstructor // 7개 인자 생성자 자동 생성
public class TeamTactics {
    private int teamId;
    private String teamName;
    private int typeId;
    private double tempo;
    private double directness;
    private double pressing;
    private double sideUsage;
    private double fight;

    // 서비스에서 사용
    public double[] getScores() {
        return new double[]{tempo, directness, pressing, sideUsage, fight};
    }

    // 팀 데이터 기준 KBTI
    public String getTeamKbti() {
        StringBuilder code = new StringBuilder();
        code.append(tempo >= 50 ? "F" : "S");
        code.append(directness >= 50 ? "L" : "S");
        code.append(pressing >= 50 ? "A" : "P");
        code.append(fight >= 50 ? "T" : "D");
        return code.toString();
    }
}