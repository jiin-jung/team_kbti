package kleague.kbti.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import kleague.kbti.dto.request.KbtiRequest;
import kleague.kbti.dto.response.KbtiResponse;
import kleague.kbti.service.KbtiService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/kbti")
@Tag(name = "KBTI", description = "사용자 축구 취향 기반 팀 추천 API")
public class KbtiController {

    private final KbtiService kbtiService;

    public KbtiController(KbtiService kbtiService) {
        this.kbtiService = kbtiService;
    }

    @PostMapping("/test")
    @Operation(summary = "K-BTI 팀 추천", description = "사용자 취향 점수를 기반으로 가장 가까운 전술 성향의 K리그 팀을 추천합니다.")
    public KbtiResponse runTest(@Valid @RequestBody KbtiRequest request) {
        return kbtiService.findBestMatch(request);
    }
}
