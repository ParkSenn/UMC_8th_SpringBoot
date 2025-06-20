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
        url = "https://discord.com/api/webhooks/1371204404321058927/vTKLLP6FNyFOFRluBUXCvD7JK6xZjKXrWUXyLb_OjxmI0Cizd8TO6kR-fKiFz1AFTvW9",
        configuration = DiscordFeignConfiguration.class)
public interface DiscordClient {

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Void> sendAlarm(@RequestBody DiscordMessage message);
}
