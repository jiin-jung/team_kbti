package kleague.kbti.controller;

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
public class TeamController {

    private final TeamQueryService teamQueryService;

    public TeamController(TeamQueryService teamQueryService) {
        this.teamQueryService = teamQueryService;
    }

    @GetMapping
    public List<TeamResponse> getAllTeams() {
        return teamQueryService.findAll();
    }

    @GetMapping("/rank")
    public List<TeamRankResponse> getTeamsRank() {
        return teamQueryService.findAllSortedByRank();
    }

    @GetMapping("/{teamId}")
    public TeamResponse getTeam(@Positive @PathVariable int teamId) {
        return teamQueryService.findById(teamId);
    }
}
