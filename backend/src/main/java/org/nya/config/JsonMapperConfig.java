package org.nya.config;

import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.UUID;

import org.nya.mappers.UuidDeserializer;

@Configuration
public class JsonMapperConfig {

    @Bean
    public Module uuidModule() {
        SimpleModule module = new SimpleModule();
        module.addDeserializer(UUID.class, new UuidDeserializer());
        return module;
    }
}
