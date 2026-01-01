package kleague.kbti.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Team {
    private String name;
    private int clusterType; // 0, 1, 2, 3
    private String description;
}