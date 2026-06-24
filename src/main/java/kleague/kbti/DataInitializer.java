package kleague.kbti;

import kleague.kbti.service.CsvMigrationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("migration")
public class DataInitializer implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(DataInitializer.class);

    private final CsvMigrationService migrationService;

    public DataInitializer(CsvMigrationService migrationService) {
        this.migrationService = migrationService;
    }

    @Override
    public void run(String... args) {
        log.info("CSV 데이터 마이그레이션 시작");
        migrationService.migrateData();
        log.info("CSV 데이터 마이그레이션 종료");
    }
}
