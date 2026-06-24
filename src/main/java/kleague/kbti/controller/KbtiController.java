package kleague.kbti.controller;

import jakarta.validation.Valid;
import kleague.kbti.dto.request.KbtiRequest;
import kleague.kbti.dto.response.KbtiResponse;
import kleague.kbti.service.KbtiService;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/kbti")
public class KbtiController {

    private final KbtiService kbtiService;

    public KbtiController(KbtiService kbtiService) {
        this.kbtiService = kbtiService;
    }

    @PostMapping("/test")
    public KbtiResponse runTest(@Valid @RequestBody KbtiRequest request) {
        return kbtiService.findBestMatch(request);
    }
}
