package kleague.kbti.dto.response;

import kleague.kbti.model.KbtiDimension;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class TeamResponse {

    private Integer rank;
    private int teamId;
    private String teamName;

    private double tempo;
    private double directness;
    private double pressing;
    private double sideUsage;
    private double fight;

    private String teamKbti;
    private String teamKbtiDesc;
    private List<KbtiDimension> teamKbtiDetails;
}
