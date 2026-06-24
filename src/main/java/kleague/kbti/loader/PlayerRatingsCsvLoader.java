package kleague.kbti.loader;

import kleague.kbti.exception.DataLoadException;
import kleague.kbti.loader.row.PlayerRatingRow;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Component
public class PlayerRatingsCsvLoader {

    private static final String FILE_NAME = "kleague_player_ratings_final_v2.csv";

    public List<PlayerRatingRow> load() {
        try {
            ClassPathResource resource = new ClassPathResource(FILE_NAME);

            try (BufferedReader br = new BufferedReader(
                    new InputStreamReader(resource.getInputStream(), StandardCharsets.UTF_8)
            )) {
                return br.lines()
                        .skip(1) // 헤더 제거
                        .map(this::parseCsvLine) // 따옴표 포함 CSV도 안전하게 처리
                        .map(cols -> new PlayerRatingRow(
                                cols.get(0).trim(),                         // 선수명
                                cols.get(1).trim(),                         // 팀명
                                cols.get(2).trim(),                         // 포지션
                                cols.get(3).trim(),                         // 역할군
                                Double.parseDouble(cleanNumber(cols.get(4))),// 평가점수_Raw
                                Integer.parseInt(cleanNumber(cols.get(5))),  // 경기수
                                Double.parseDouble(cleanNumber(cols.get(6))) // AI평점
                        ))
                        .toList();
            }

        } catch (Exception e) {
            throw new DataLoadException("kleague_player_ratings_final_v2.csv 로드 실패", e);
        }
    }

    // "..." 안의 콤마를 깨지 않도록 간단 CSV 파서
    private List<String> parseCsvLine(String line) {
        List<String> out = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        boolean inQuotes = false;

        for (int i = 0; i < line.length(); i++) {
            char c = line.charAt(i);

            if (c == '"') {
                inQuotes = !inQuotes;
                continue;
            }

            if (c == ',' && !inQuotes) {
                out.add(sb.toString());
                sb.setLength(0);
                continue;
            }

            sb.append(c);
        }
        out.add(sb.toString());
        return out;
    }

    private String cleanNumber(String s) {
        // BOM/공백 제거 + 천단위 콤마 제거(있을 경우)
        return s.replace("\uFEFF", "").trim().replace(",", "");
    }
}
