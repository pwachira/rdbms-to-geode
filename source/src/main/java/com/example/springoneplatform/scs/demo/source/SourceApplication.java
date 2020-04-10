package com.example.springoneplatform.scs.demo.source;

import com.example.springoneplatform.scs.demo.config.ActuatorSecurity;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.app.jdbc.source.JdbcSourceConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@SpringBootApplication
@Import({JdbcSourceConfiguration.class})
public class SourceApplication {
    public static void main(String[] args) {
        SpringApplication.run(SourceApplication.class, args);
    }

    @Bean
    public WebSecurityConfigurerAdapter actuatorSecurity(){
        return new ActuatorSecurity();
    }
}

