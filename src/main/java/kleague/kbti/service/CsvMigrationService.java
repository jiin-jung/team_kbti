package kleague.kbti.service;

import kleague.kbti.domain.TeamEntity;
import kleague.kbti.exception.DataLoadException;
import kleague.kbti.repository.TeamRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

@Service
public class CsvMigrationService {

    private static final Logger log = LoggerFactory.getLogger(CsvMigrationService.class);

    private final TeamRepository teamRepository;

    public CsvMigrationService(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    @Transactional
    public void migrateData() {
        try {
            teamRepository.deleteAll();
            parseKbtiCsv();
            parseDetailedCsv();
            log.info("K리그 데이터 마이그레이션 완료");
        } catch (Exception e) {
            throw new DataLoadException("K리그 데이터 마이그레이션 실패", e);
        }
    }

    private void parseKbtiCsv() throws Exception {
        InputStream is = getClass().getResourceAsStream("/kleague_kbti_final_revised.csv");
        if (is == null) throw new RuntimeException("파일을 찾을 수 없습니다: kleague_kbti_final_revised.csv");

        // 한글 깨짐 방지를 위해 MS949 또는 EUC-KR 사용
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(is, "MS949"))) {
            String line;
            reader.readLine(); // 헤더 스킵
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length < 6) continue;

                TeamEntity team = TeamEntity.builder()
                        .teamName(data[0].trim())
                        .tempo(Integer.parseInt(data[1].trim()))
                        .directness(Integer.parseInt(data[2].trim()))
                        .pressing(Integer.parseInt(data[3].trim()))
                        .sideUsage(Integer.parseInt(data[4].trim()))
                        .fight(Integer.parseInt(data[5].trim()))
                        .build();
                teamRepository.save(team);
            }
        }
    }

    private void parseDetailedCsv() throws Exception {
        InputStream is = getClass().getResourceAsStream("/kleague_stats_all_variables_detailed.csv");
        if (is == null) throw new RuntimeException("파일을 찾을 수 없습니다: kleague_stats_all_variables_detailed.csv");

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(is, "MS949"))) {
            String line;
            reader.readLine(); // 헤더 스킵
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                String teamName = data[0].trim();

                // 분석된 인덱스: Goals(3), Clearance(17), PassRate(81), Shot(95)
                teamRepository.findByTeamName(teamName).ifPresent(team -> {
                    team.setGoalsPerGame(safeParseDouble(data[3]));
                    team.setClearancePerGame(safeParseDouble(data[17]));
                    team.setPassSuccessRate(safeParseDouble(data[81]));
                    team.setShotOnTarget(safeParseDouble(data[95]));
                    teamRepository.save(team);
                });
            }
        }
    }

    // 데이터가 비어있거나 이상할 경우를 대비한 안전한 파싱 함수
    private double safeParseDouble(String value) {
        try {
            if (value == null || value.trim().isEmpty() || value.equals("NaN")) return 0.0;
            return Double.parseDouble(value.trim());
        } catch (NumberFormatException e) {
            return 0.0;
        }
    }
}
