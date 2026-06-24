package kleague.kbti.service;

import kleague.kbti.dto.response.PlayerRankResponse;
import kleague.kbti.loader.row.PlayerRatingRow;
import kleague.kbti.loader.PlayerRatingsCsvLoader;
import kleague.kbti.mapper.PlayerResponseMapper;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@Service
public class PlayerQueryService {

    private final List<PlayerRatingRow> players;
    private final PlayerResponseMapper playerResponseMapper;

    private static final Set<String> GK = Set.of("GK");
    private static final Set<String> DF = Set.of("CB", "RB", "LB", "LWB", "RWB");
    private static final Set<String> MF = Set.of("CDM", "CM", "RM", "LM", "CAM");
    private static final Set<String> FW = Set.of("LW", "RW", "RF", "LF", "CF");

    public PlayerQueryService(PlayerRatingsCsvLoader loader, PlayerResponseMapper playerResponseMapper) {
        this.players = loader.load();
        this.playerResponseMapper = playerResponseMapper;
    }

    public List<PlayerRankResponse> getPlayerRankings(Integer top, String team, String positionGroup, Integer minGames) {
        int mg = (minGames == null) ? 15 : Math.max(minGames, 0);

        Stream<PlayerRatingRow> stream = players.stream()
                .filter(p -> p.getGames() >= mg);

        if (team != null && !team.isBlank()) {
            String q = team.trim();
            stream = stream.filter(p -> p.getTeamName() != null && p.getTeamName().contains(q));
        }

        if (positionGroup != null && !positionGroup.isBlank()) {
            String group = positionGroup.trim().toUpperCase();
            stream = stream.filter(p -> group.equals(toPosGroup(p.getPosition())));
        }

        // 정렬 기준: AI평점 desc, 동점이면 Raw desc
        Comparator<PlayerRatingRow> comparator =
                Comparator.comparingDouble(PlayerRatingRow::getAiRating).reversed()
                        .thenComparing(Comparator.comparingDouble(PlayerRatingRow::getRawScore).reversed())
                        .thenComparing(Comparator.comparingInt(PlayerRatingRow::getGames).reversed())
                        .thenComparing(PlayerRatingRow::getPlayerName, Comparator.nullsLast(String::compareTo));

        List<PlayerRatingRow> sorted = stream
                .sorted(comparator)
                .toList();

        int limit = (top == null || top <= 0) ? sorted.size() : Math.min(top, sorted.size());

        return IntStream.range(0, limit)
                .mapToObj(i -> playerResponseMapper.toRankResponse(i + 1, sorted.get(i)))
                .toList();
    }

    private String toPosGroup(String pos) {
        String p = (pos == null) ? "" : pos.trim().toUpperCase().replaceAll("\\s+", "");

        if (GK.contains(p)) return "GK";
        if (DF.contains(p)) return "DF";
        if (MF.contains(p)) return "MF";
        if (FW.contains(p)) return "FW";
        return "";
    }
}
