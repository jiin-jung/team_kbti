package kleague.kbti.controller;

import kleague.kbti.dto.KbtiRequest;
import kleague.kbti.dto.KbtiResponse;
import kleague.kbti.service.KbtiService;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*") // 모든 도메인에서의 접근을 허용 (테스트용)
@RestController
@RequestMapping("/api/kbti")
public class KbtiController {

    private final KbtiService kbtiService;

    // 생성자 주입 방식 사용
    public KbtiController(KbtiService kbtiService) {
        this.kbtiService = kbtiService;
    }

    /**
     * 사용자의 입력 데이터를 받아 최적의 매칭 결과를 반환.
     */
    @PostMapping("/test")
    public KbtiResponse runTest(@RequestBody KbtiRequest request) {
        return kbtiService.findBestMatch(request);
    }
}