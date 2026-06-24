package kleague.kbti.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Positive;
import kleague.kbti.dto.response.TeamRankResponse;
import kleague.kbti.dto.response.TeamResponse;
import kleague.kbti.service.TeamQueryService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController
@RequestMapping("/api/teams")
@Tag(name = "Teams", description = "K리그 팀 전술 정보 및 순위 조회 API")
public class TeamController {

    private final TeamQueryService teamQueryService;

    public TeamController(TeamQueryService teamQueryService) {
        this.teamQueryService = teamQueryService;
    }

    @GetMapping
    @Operation(summary = "전체 팀 조회", description = "전체 팀의 전술 지표와 KBTI 정보를 조회합니다.")
    public List<TeamResponse> getAllTeams() {
        return teamQueryService.findAll();
    }

    @GetMapping("/rank")
    @Operation(summary = "팀 순위 조회", description = "K리그 팀 순위와 팀별 KBTI 코드를 함께 조회합니다.")
    public List<TeamRankResponse> getTeamsRank() {
        return teamQueryService.findAllSortedByRank();
    }

    @GetMapping("/{teamId}")
    @Operation(summary = "단일 팀 조회", description = "팀 ID로 단일 팀의 전술 지표와 KBTI 정보를 조회합니다.")
    public TeamResponse getTeam(@Positive @PathVariable int teamId) {
        return teamQueryService.findById(teamId);
    }
}
