package kleague.kbti.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI kbtiOpenApi() {
        return new OpenAPI()
                .info(new Info()
                        .title("K-BTI API")
                        .description("K리그 전술 성향 기반 팀 추천 서비스 API 문서")
                        .version("v1")
                        .license(new License()
                                .name("K-BTI")
                                .url("https://github.com/jiin-jung/team_kbti")));
    }
}
