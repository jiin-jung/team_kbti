package kleague.kbti.loader;

import kleague.kbti.dto.TeamTactics;
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

        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(
                        getClass().getClassLoader().getResourceAsStream(CSV_PATH)
                ))) {

            br.readLine(); // header skip
            String line;

            while ((line = br.readLine()) != null) {
                String[] t = line.split(",");

                list.add(new TeamTactics(
                        Integer.parseInt(t[0]),   // teamId
                        t[1],                     // teamName
                        Integer.parseInt(t[2]),
                        Double.parseDouble(t[3]),
                        Double.parseDouble(t[4]),
                        Double.parseDouble(t[5]),
                        Double.parseDouble(t[6]),
                        Double.parseDouble(t[7])
                ));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return list;
    }
}

