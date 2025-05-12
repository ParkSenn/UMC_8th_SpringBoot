package umc8th.spring8th.service.DiscordAlarmService;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import umc8th.spring8th.apiPayload.discord.DiscordClient;
import umc8th.spring8th.web.dto.Discord.DiscordMessage;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Profile("dev")
public class DevDiscordAlarmService implements DiscordAlarmService {

    private final DiscordClient discordClient;

    @Override
    public void sendErrorAlert(Exception e, WebRequest request) {

        DiscordMessage message = createMessage(e, request);
        discordClient.sendAlarm(message);
    }

    private DiscordMessage createMessage(Exception e, WebRequest request) {
        return DiscordMessage.builder()
                .content("# 🚨 에러 발생 🚨")
                .embeds(
                        List.of(
                                DiscordMessage.Embed.builder()
                                        .title("ℹ️ 에러 정보")
                                        .description(
                                                "### 🕖 발생 시간\n"
                                                        + LocalDateTime.now()
                                                        + "\n"
                                                        + "### 🔗 요청 URL\n"
                                                        + createRequestFullPath(request)
                                                        + "\n"
                                                        + "### 📄 Stack Trace\n"
                                                        + "```\n"
                                                        + getStackTrace(e).substring(0, 1000)
                                                        + "\n```")
                                        .build()
                        )
                )
                .build();
    }

    private String createRequestFullPath(WebRequest webRequest) {
        HttpServletRequest request = ((ServletWebRequest) webRequest).getRequest();
        String fullPath = request.getMethod() + " " + request.getRequestURL();

        String queryString = request.getQueryString();
        if (queryString != null) {
            fullPath += "?" + queryString;
        }

        return fullPath;
    }

    private String getStackTrace(Exception e) {
        StringWriter stringWriter = new StringWriter();
        e.printStackTrace(new PrintWriter(stringWriter));
        return stringWriter.toString();
    }
}
