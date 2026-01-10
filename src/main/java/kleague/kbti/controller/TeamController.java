package kleague.kbti.controller;

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

    // 단일 팀 (ID)
    @GetMapping("/{teamId}")
    public TeamResponse getTeam(@PathVariable int teamId) {
        return teamQueryService.findById(teamId);
    }

    @GetMapping("/rank")
    public List<TeamResponse> getTeamsRank() {
        return teamQueryService.findAllSortedByRank();
    }

}