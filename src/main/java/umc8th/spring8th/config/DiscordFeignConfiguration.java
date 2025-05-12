package umc8th.spring8th.config;

import feign.codec.Encoder;
import feign.jackson.JacksonEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class DiscordFeignConfiguration {

    @Bean
    public Encoder feignEncoder() {
        return new JacksonEncoder();
    }
}
