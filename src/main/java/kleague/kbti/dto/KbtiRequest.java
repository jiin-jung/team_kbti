/**
 * 사용자 입력 데이터를 담는 객체
 */
package kleague.kbti.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class KbtiRequest {
    private int tempo;
    private int directness;
    private int pressing;
    private int fight;
}