package ru.aasmc.cems.rest.sec.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import ru.aasmc.cems.dj.ServiceConfig;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"ru.aasmc.cems.rest.sec.controllers"})
public class WebConfig implements WebMvcConfigurer, WebApplicationInitializer {

    @Bean
    public ObjectMapper objectMapper() {
        var objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        // registers additional modules of json serialization
        objectMapper.findAndRegisterModules();
        return objectMapper;
    }

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        var rootContext = new AnnotationConfigWebApplicationContext();
        rootContext.register(SecurityConfig.class, H2DBConfig.class, ServiceConfig.class);
        servletContext.addListener(new ContextLoaderListener(rootContext));

        var dispatcherContext =
                new AnnotationConfigWebApplicationContext();
        dispatcherContext.register(WebConfig.class);

        var dispatcher =
                servletContext.addServlet("cems-dispatcher", new DispatcherServlet(dispatcherContext));
        dispatcher.setLoadOnStartup(1);
        dispatcher.addMapping("/");
    }
}























