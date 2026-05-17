package summary.news_line.subscript;
// package summary.news_line.service;

// import com.linecorp.bot.messaging.client.MessagingApiClient;
// import com.linecorp.bot.messaging.model.PushMessageRequest;
// import com.linecorp.bot.messaging.model.TextMessage;

// import org.springframework.stereotype.Service;

// import java.util.List;

// /**
//  * LINE のPushMessage APIを使用してユーザーにメッセージを送信するサービス。
//  * replyTokenを必要としないため、スケジューラーからの配信に適している。
//  */
// @Service
// public class BroadcastMessageService {

//     private final MessagingApiClient messagingApiClient;

//     public BroadcastMessageService(MessagingApiClient messagingApiClient) {
//         this.messagingApiClient = messagingApiClient;
//     }

//     public void sendMessage(String userId, TextMessage message) {
//         try {
//             PushMessageRequest request = new PushMessageRequest(
//                     userId,
//                     List.of(message)
//             );
//             messagingApiClient.pushMessage(request);
//             System.out.println("[BroadcastMessageService] Message sent to user: " + userId);
//         } catch (Exception e) {
//             System.err.println("[BroadcastMessageService] Failed to send message to user: " + userId);
//             e.printStackTrace();
//         }
//     }
// }
