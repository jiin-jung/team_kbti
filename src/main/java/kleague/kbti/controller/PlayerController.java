package kleague.kbti.controller;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import kleague.kbti.dto.response.PlayerRankResponse;
import kleague.kbti.service.PlayerQueryService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Validated
@RestController
@RequestMapping("/api/players")
public class PlayerController {

    private final PlayerQueryService playerQueryService;

    public PlayerController(PlayerQueryService playerQueryService) {
        this.playerQueryService = playerQueryService;
    }

    @GetMapping("/rank")
    public List<PlayerRankResponse> getPlayerRank(
            @Min(1) @Max(200) @RequestParam(required = false) Integer top,
            @RequestParam(required = false) String team,
            @RequestParam(required = false, name = "position") String positionGroup,
            @Min(0) @Max(60) @RequestParam(required = false, defaultValue = "15") Integer minGames
    ) {
        return playerQueryService.getPlayerRankings(top, team, positionGroup, minGames);
    }
}
