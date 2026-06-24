package kleague.kbti.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Players", description = "K리그 선수 랭킹 조회 API")
public class PlayerController {

    private final PlayerQueryService playerQueryService;

    public PlayerController(PlayerQueryService playerQueryService) {
        this.playerQueryService = playerQueryService;
    }

    @GetMapping("/rank")
    @Operation(summary = "선수 랭킹 조회", description = "AI 평점 기준 선수 랭킹을 팀, 포지션, 최소 경기 수로 필터링해 조회합니다.")
    public List<PlayerRankResponse> getPlayerRank(
            @Min(1) @Max(200) @RequestParam(required = false) Integer top,
            @RequestParam(required = false) String team,
            @RequestParam(required = false, name = "position") String positionGroup,
            @Min(0) @Max(60) @RequestParam(required = false, defaultValue = "15") Integer minGames
    ) {
        return playerQueryService.getPlayerRankings(top, team, positionGroup, minGames);
    }
}
