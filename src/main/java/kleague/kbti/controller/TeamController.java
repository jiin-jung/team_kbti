package kleague.kbti.controller;

import kleague.kbti.dto.TeamRankResponse;
import kleague.kbti.dto.TeamResponse;
import kleague.kbti.service.TeamQueryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/teams")
public class TeamController {

    private final TeamQueryService teamQueryService;

    public TeamController(TeamQueryService teamQueryService) {
        this.teamQueryService = teamQueryService;
    }

    // 전체 팀
    @GetMapping
    public List<TeamResponse> getAllTeams() {
        return teamQueryService.findAll();
    }

    // 랭킹 (문자열 경로)
    @GetMapping("/rank")
    public List<TeamRankResponse> getTeamsRank() {
        return teamQueryService.findAllSortedByRank();
    }

    // 단일 팀 (ID)
    @GetMapping("/{teamId}")
    public TeamResponse getTeam(@PathVariable int teamId) {
        return teamQueryService.findById(teamId);
    }
}
