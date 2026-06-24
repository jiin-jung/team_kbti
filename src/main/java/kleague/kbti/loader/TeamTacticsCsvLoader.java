package kleague.kbti.loader;

import kleague.kbti.exception.code.DataErrorCode;
import kleague.kbti.exception.domain.DataException;
import kleague.kbti.model.TacticalVector;
import kleague.kbti.model.TeamTactics;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Component
public class TeamTacticsCsvLoader {

    private static final String CSV_PATH = "kleague_kbti_service.csv";

    public List<TeamTactics> load() {
        List<TeamTactics> list = new ArrayList<>();

        try {
            ClassPathResource resource = new ClassPathResource(CSV_PATH);

            try (BufferedReader br = new BufferedReader(
                    new InputStreamReader(resource.getInputStream(), StandardCharsets.UTF_8)
            )) {

                br.readLine();
                String line;

                while ((line = br.readLine()) != null) {
                    String[] t = line.split(",");

                    list.add(new TeamTactics(
                            Integer.parseInt(t[0]),
                            t[1],
                            Integer.parseInt(t[2]),
                            new TacticalVector(
                                    Double.parseDouble(t[3]),
                                    Double.parseDouble(t[4]),
                                    Double.parseDouble(t[5]),
                                    Double.parseDouble(t[6]),
                                    Double.parseDouble(t[7])
                            )
                    ));
                }
            }
        } catch (Exception e) {
            throw new DataException(DataErrorCode.TEAM_TACTICS_CSV_LOAD_FAILED, e);
        }

        return list;
    }
}
