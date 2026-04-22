package com.ankit.module4.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.client.RestClient;

import static org.apache.tomcat.util.http.fileupload.FileUploadBase.CONTENT_TYPE;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Configuration
public class RestClientConfig {

    @Value("${employeeService.base.url}")
    private String baseUrl;

    @Bean
    @Qualifier("getEmployeeServiceRestClient")
    RestClient getEmployeeServiceRestClient() {
        return RestClient.builder()
                .baseUrl(baseUrl)
                .defaultHeader(CONTENT_TYPE, APPLICATION_JSON_VALUE)
                .defaultStatusHandler(HttpStatusCode::is5xxServerError,(req,res)->{
                    System.out.println(new String(res.getBody().readAllBytes()));
                    throw new RuntimeException("Server Error ");
                })
                .build();
    }
}
