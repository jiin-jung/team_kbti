/**
 * 최종 결과와 멘트를 담는 객체
 */

package kleague.kbti.dto;

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
    private String teamName;     // 추천 팀 이름
    private String kbtiCode;     // 4글자 코드
    private String description;  // 성향 분석 설명

    // 서비스 계층에서 간편하게 생성하기 위한 정적 팩토리 메서드
    public static KbtiResponse of(int teamId, String teamName, String code, String description) {
        return KbtiResponse.builder()
                .teamId(teamId)
                .teamName(teamName)
                .kbtiCode(code)
                .description(description)
                .build();
    }
}