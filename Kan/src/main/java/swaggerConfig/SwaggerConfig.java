package swaggerConfig;

import io.swagger.annotations.AuthorizationScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;
import java.util.List;

@Configuration
@EnableSwagger2
public class SwaggerConfig extends WebMvcConfigurationSupport {

    private final ResponseMessage m200 = simpleMessage(200, "Api executada com Sucesso");
    private final ResponseMessage m201 = simpleMessage(201, "Recurso criado");
    private final ResponseMessage m204 = simpleMessage(204, "Atualização Ok");
    private final ResponseMessage m401 = simpleMessage(401, "Atualização é Requerida!");
    private final ResponseMessage m403 = simpleMessage(403, "Você Não está Autorizado!");
    private final ResponseMessage m404 = simpleMessage(404, "Objeto Não econtrado");
    private final ResponseMessage m422 = simpleMessage(422, "Erro de validação!");
    private final ResponseMessage m500 = simpleMessage(500, "Erro de Servidor");

@Bean
    public Docket api(){
    return new Docket(DocumentationType.SWAGGER_2)
            .useDefaultResponseMessages(false)
            .globalResponseMessage(RequestMethod.POST, Arrays.asList(m201, m403, m422, m500, m200))
            .globalResponseMessage(RequestMethod.GET, Arrays.asList(m403, m403, m404,  m500))
            .globalResponseMessage(RequestMethod.POST, Arrays.asList(m403, m204, m422, m500))
            .globalResponseMessage(RequestMethod.POST, Arrays.asList(m403, m404, m200))
            .select()
            .apis(RequestHandlerSelectors.any())
            .paths(PathSelectors.any())
            .build()
            .apiInfo(apiInfo());
}
 private ApiInfo apiInfo(){
    return new ApiInfoBuilder()
            .title("Teste Kan com Spring Boot")
            .version("1.0")
            .description(("Marcello Ventura Leoratti"))
            .build();
 }

 private ResponseMessage simpleMessage(int code, String msg){
    return new ResponseMessageBuilder().code(code).message(msg)
            .build();
 }



}
