package kleague.kbti.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class KbtiResponse {
    private int teamId;
    private String teamName;
    private String kbtiCode;
    private String description;

    public static KbtiResponse of(int teamId, String teamName, String code, String description) {
        return KbtiResponse.builder()
                .teamId(teamId)
                .teamName(teamName)
                .kbtiCode(code)
                .description(description)
                .build();
    }
}
