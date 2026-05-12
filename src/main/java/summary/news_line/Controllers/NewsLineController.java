package summary.news_line.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.linecorp.bot.messaging.model.TextMessage;
import com.linecorp.bot.spring.boot.handler.annotation.EventMapping;
import com.linecorp.bot.spring.boot.handler.annotation.LineMessageHandler;
import com.linecorp.bot.webhook.model.Event;
import com.linecorp.bot.webhook.model.MessageEvent;

import summary.news_line.service.NewsLineService;

/**
 * LINE Botのメッセージハンドラー。
 * スケジューラーから定期的にニュースを配信する。
 */

@LineMessageHandler
public class NewsLineController {

    /**
     * テキストメッセージイベントを受信してもスケジューラーからの配信を待つ。
     * 従来のような即座の返信は行わない。
     */
    private final NewsLineService newsLineService;

    public NewsLineController(NewsLineService newsLineService) {
        this.newsLineService = newsLineService;
    }

    @EventMapping
    public void handleTextMessageEvent(MessageEvent event) {
        System.out.println("[NewsLineController] User ID: " + event.source().userId());
        newsLineService.handleTextMessageEvent(event);
    }

    @EventMapping
    public void handleDefaultMessageEvent(Event event) {
        System.out.println("[NewsLineController] handleDefaultMessageEvent called");
        System.out.println("event: " + event);
    }

    @PostMapping()
    public void callback(@RequestBody String body) {
        System.out.println(body);
    }
}
