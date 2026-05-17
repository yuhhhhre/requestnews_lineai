package summary.news_line.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.linecorp.bot.messaging.client.MessagingApiClient;
import com.linecorp.bot.messaging.model.ReplyMessageRequest;
import com.linecorp.bot.messaging.model.TextMessage;


@Service
public class LineReplyService {

    private final MessagingApiClient messagingApiClient;

    public LineReplyService(MessagingApiClient messagingApiClient) {
        this.messagingApiClient = messagingApiClient;
    }

    public void reply(String replyToken, String message) {
        messagingApiClient.replyMessage(new ReplyMessageRequest(
            replyToken,
            List.of(new TextMessage(message)),
            false));
            
    }
}
