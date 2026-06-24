package kleague.kbti.controller;

import kleague.kbti.exception.code.TeamErrorCode;
import kleague.kbti.exception.domain.TeamException;
import kleague.kbti.exception.GlobalExceptionHandler;
import kleague.kbti.service.TeamQueryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class GlobalExceptionHandlerTest {

    private MockMvc mockMvc;
    private TeamQueryService teamQueryService;

    @BeforeEach
    void setUp() {
        teamQueryService = mock(TeamQueryService.class);
        mockMvc = MockMvcBuilders.standaloneSetup(new TeamController(teamQueryService))
                .setControllerAdvice(new GlobalExceptionHandler())
                .build();
    }

    @Test
    void handlesDomainExceptionWithDomainErrorCode() throws Exception {
        when(teamQueryService.findById(999))
                .thenThrow(new TeamException(TeamErrorCode.TEAM_NOT_FOUND, "존재하지 않는 팀 ID: 999"));

        mockMvc.perform(get("/api/teams/{teamId}", 999))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.domain").value("TEAM"))
                .andExpect(jsonPath("$.code").value("TEAM-404-001"))
                .andExpect(jsonPath("$.message").value("존재하지 않는 팀 ID: 999"))
                .andExpect(jsonPath("$.path").value("/api/teams/999"));
    }
}
