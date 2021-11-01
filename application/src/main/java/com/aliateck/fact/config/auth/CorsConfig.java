package com.aliateck.fact.config.auth;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import com.aliateck.util.CommonResource.RequestType;

@Configuration
@EnableWebMvc
public class CorsConfig implements WebMvcConfigurer {

  @Override
  public void addCorsMappings(CorsRegistry registry) {
    registry.addMapping("/**")

        // .allowedOrigins("*")
        // .allowedOrigins("http://localhost:3000")
        .allowedMethods(RequestType.PUT, RequestType.DELETE, RequestType.GET, RequestType.POST);
  }

}
