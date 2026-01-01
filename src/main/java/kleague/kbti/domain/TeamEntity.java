package kleague.kbti.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TeamEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String teamName;     // 팀명

    // KBTI 5대 지표 (Revised CSV)
    private Integer tempo;
    private Integer directness;
    private Integer pressing;
    private Integer sideUsage;
    private Integer fight;
    private Integer clusterType; // KMeans 결과 (0~3)

    // 상세 스탯 (Detailed CSV)
    private Double goalsPerGame;     // Real_Goals_PerGame
    private Double passSuccessRate;  // Pass_SuccessRate(%)
    private Double clearancePerGame; // Clearance_PerGame
    private Double shotOnTarget;     // Shot_OnTarget_PerGame
}