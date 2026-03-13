package ru.itis.spring.infrastructure;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;

@Configuration
public class AppConfig {
    ViewResolver resolver = new FreeMarkerViewResolver();
    // Spring
//    @Bean
//    public ViewResolver viewResolver() {
//        ViewResolver resolver = new FreeMarkerViewResolver();
//        try {
//            resolver.resolveViewName("index.html", Locale.ROOT);
//        } catch (Exception e) {
//            throw new IllegalArgumentException(e);
//        }
//        return resolver;
//    }
}
