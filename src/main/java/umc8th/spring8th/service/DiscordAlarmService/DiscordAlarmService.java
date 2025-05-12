package umc8th.spring8th.service.DiscordAlarmService;

import org.springframework.web.context.request.WebRequest;

public interface DiscordAlarmService {

    void sendErrorAlert(Exception e, WebRequest request);
}
