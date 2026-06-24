package kleague.kbti.dto.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class KbtiRequest {

    @Min(1)
    @Max(5)
    private int tempo;

    @Min(1)
    @Max(5)
    private int directness;

    @Min(1)
    @Max(5)
    private int pressing;

    @Min(1)
    @Max(5)
    private int fight;
}
