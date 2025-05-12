package umc8th.spring8th.apiPayload.discord;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import umc8th.spring8th.config.DiscordFeignConfiguration;
import umc8th.spring8th.web.dto.Discord.DiscordMessage;

@FeignClient(
        name = "discord-client",
        url = "${DISCORD_WEBHOOK_URL}",
        configuration = DiscordFeignConfiguration.class)
public interface DiscordClient {

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Void> sendAlarm(@RequestBody DiscordMessage message);
}
