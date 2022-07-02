package com.example.sample.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.web.cors.CorsConfiguration;

@Component
@ConfigurationProperties("application")
@Getter
@Setter
public class ApplicationProperties {

    private final CorsConfiguration cors = new CorsConfiguration();
    private final Security security = new Security();

    @Getter
    @Setter
    public static class Security {
        private String base64Secret;
        private long tokenValidityInSeconds;
    }

}
