package cloud.microservices.spring.cloud.zuulproxy.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component
@Configuration
public class ServiceConfig {

    @Value("${SIGNING_KEY}")
    private String jwtSigningKey;

    public String getJwtSigningKey() {
        return jwtSigningKey;
    }

}
