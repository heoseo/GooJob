package com.goojob.api.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

/**
 * Swagger 설정
 *
 * @author heoseo
 * http://localhost:8080/swagger-ui/index.html
 */
@Configuration
public class SwaggerConfig {

	// OpenAPI 빈설정 (1) JWT를 사용하지 않는 경우
	@Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .components(new Components())
                .info(apiInfo());
    }

	// OpenAPI 빈설정 (2) JWT를 사용하는 경우
//	@Bean
//    public OpenAPI openAPI() {
//        String jwt = "JWT";
//        SecurityRequirement securityRequirement = new SecurityRequirement().addList(jwt);
//        Components components = new Components().addSecuritySchemes(jwt, new SecurityScheme()
//                .name(jwt)
//                .type(SecurityScheme.Type.HTTP)
//                .scheme("bearer")
//                .bearerFormat("JWT")
//        );
//        return new OpenAPI()
//                .components(new Components())
//                .info(apiInfo())
//                .addSecurityItem(securityRequirement)
//                .components(components);
//    }

	// swagger 설명 설정(1) - 클래스로 정의하기
    private Info apiInfo() {
        return new Info()
                .title("GooJob API") // API의 제목
                .description("구잡 API") // API에 대한 설명
                .version("1.0.0"); // API의 버전
    }

    /*
    // swagger 설명 설정(2) - 어노테이션으로 정의하기

    public class SwaggerConfig {} 위에 다음과 같은 어노테이션을 설정할수도 있음.

    @OpenAPIDefinition(
			info = @Info(title = "PigSpace API",
		        description = "쩝쩝박사 API 명세서입니다.\n\n <a href='https://www.notion.so/08ec5510a4054318b0bee5fb40fd28bb?v=10ed6d2ef48c44bb8995eac39934271e'>인터페이스정의서 바로가기</a>\n ",
		        version = "v1"))
     * */
}
