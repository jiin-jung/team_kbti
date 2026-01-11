package kleague.kbti.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class KbtiDimension {
    private String key;          // 예: F
    private String title;        // 예: 빠른 전개
    private String description;  // 예: 공 탈취 후 빠르게 전진...
}
