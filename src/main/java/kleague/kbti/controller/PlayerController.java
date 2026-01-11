package kleague.kbti.controller;

import kleague.kbti.dto.PlayerRankResponse;
import kleague.kbti.service.PlayerQueryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/players")
public class PlayerController {

    private final PlayerQueryService playerQueryService;

    public PlayerController(PlayerQueryService playerQueryService) {
        this.playerQueryService = playerQueryService;
    }

    @GetMapping("/rank")
    public List<PlayerRankResponse> getPlayerRank(
            @RequestParam(required = false) Integer top,
            @RequestParam(required = false) String team,
            @RequestParam(required = false, name = "position") String positionGroup,
            @RequestParam(required = false, defaultValue = "15") Integer minGames
    ) {
        return playerQueryService.getPlayerRankings(top, team, positionGroup, minGames);
    }
}
