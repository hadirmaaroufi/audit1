package tn.talys.spring.audit.Config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {
    @Bean
    public ObjectMapper ObjectMapper(){
        return new ObjectMapper();
    }
}
