package kleague.kbti;

import kleague.kbti.service.CsvMigrationService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    private final CsvMigrationService migrationService;

    public DataInitializer(CsvMigrationService migrationService) {
        this.migrationService = migrationService;
    }

    @Override
    public void run(String... args) {
        // 애플리케이션 시작 시 자동으로 마이그레이션 로직을 실행합니다.
        System.out.println(">>> [시스템 초기화] CSV 데이터 마이그레이션을 시작합니다...");
        migrationService.migrateData();
        System.out.println(">>> [시스템 초기화] 모든 준비가 완료되었습니다.");
    }
}