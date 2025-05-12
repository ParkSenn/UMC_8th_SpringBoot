package umc8th.spring8th.service.DiscordAlarmService;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.WebRequest;

@Service
@Profile("!dev")
public class DummyDiscordAlertService implements DiscordAlarmService {

    @Override
    public void sendErrorAlert(Exception e, WebRequest request) {
        // dev 환경이 아니면 아무 동작 X
    }
}
