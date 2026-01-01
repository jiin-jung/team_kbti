/**
 * 팀별 전술 데이터를 담는 객체
 */

package kleague.kbti.dto;

import lombok.*;

@Getter @Setter
@AllArgsConstructor // 7개 인자 생성자 자동 생성
public class TeamTactics {
    private String teamName;
    private int typeId;
    private double tempo;
    private double directness;
    private double pressing;
    private double sideUsage;
    private double fight;

    // 서비스에서 사용하는 getScores() 메서드 추가
    public double[] getScores() {
        return new double[]{tempo, directness, pressing, sideUsage, fight};
    }
}