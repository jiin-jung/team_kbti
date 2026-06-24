package kleague.kbti.loader;

import kleague.kbti.exception.DataLoadException;
import kleague.kbti.loader.row.TeamRankingRow;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Component
public class TeamRankingCsvLoader {

    private static final String FILE_NAME = "kleague_team_ranking.csv";

    public List<TeamRankingRow> load() {
        try {
            ClassPathResource resource = new ClassPathResource(FILE_NAME);

            try (BufferedReader br = new BufferedReader(
                    new InputStreamReader(resource.getInputStream(), StandardCharsets.UTF_8)
            )) {
                return br.lines()
                        .skip(1) // 헤더 제거
                        .map(line -> line.split(",", -1))
                        .map(cols -> new TeamRankingRow(
                                Integer.parseInt(cols[0].trim().replace("\uFEFF", "")), // 순위 (BOM 방지)
                                cols[1].trim(),                                         // 팀명
                                Integer.parseInt(cols[2].trim()), // 경기수
                                Integer.parseInt(cols[3].trim()), // 승점
                                Integer.parseInt(cols[4].trim()), // 득실차
                                Integer.parseInt(cols[5].trim()), // 승
                                Integer.parseInt(cols[6].trim()), // 무
                                Integer.parseInt(cols[7].trim()), // 패
                                Integer.parseInt(cols[8].trim()), // 득점
                                Integer.parseInt(cols[9].trim())  // 실점
                        ))
                        .toList();
            }

        } catch (Exception e) {
            throw new DataLoadException("kleague_team_ranking.csv 로드 실패", e);
        }
    }
}
