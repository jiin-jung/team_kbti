package kleague.kbti.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TeamStats {
    private String teamName;

    // 상세 지표 (Detailed Stats)
    private double goalsPerGame;      // Real_Goals_PerGame
    private double passSuccessRate;   // Pass_SuccessRate(%)
    private double clearancePerGame;  // Clearance_PerGame
    private double shotOnTarget;      // Shot_OnTarget_PerGame

    // KBTI 전술 지표 (Revised CSV)
    private int tempo;
    private int directness;
    private int pressing;
    private int fight;
    private int typeId;               // KMeans Cluster (0~3)
}