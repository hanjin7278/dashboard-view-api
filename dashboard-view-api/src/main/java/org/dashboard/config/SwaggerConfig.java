package org.dashboard.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @ClassName: SwaggerConfig
 * @Auther: jchan
 * @Date: 上午9:27 2019/7/1
 * @Version 1.0 by 韩进城(hanjin7278@126.com)
 * @Description: Swagger配置
 **/
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket createRestApi() {

        Docket docket = new Docket(DocumentationType.SWAGGER_2);
        docket.apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.dashboard"))
                .paths(PathSelectors.any())
                .build();


        return docket;
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("省移动视频监测大数据平台 RESTful APIs")
                .description("描述")
                .termsOfServiceUrl("http://dashboard.com")
                .version("1.0")
                .build();
    }
}
