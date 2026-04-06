package summary.news_line.logic;

import com.openai.client.OpenAIClient;
import com.openai.client.okhttp.OpenAIOkHttpClient;
import com.openai.models.ChatModel;
import com.openai.models.chat.completions.ChatCompletionCreateParams;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class OpenaiAccess {
    OpenAIClient client = OpenAIOkHttpClient.builder()
            .apiKey("OPENAI_API_KEY")
            .build();

    public String getOpenaiResponse(String userMessage) {
        ChatCompletionCreateParams createParams = ChatCompletionCreateParams.builder()
                .addUserMessage(userMessage)
                .model(ChatModel.GPT_5)
                .build();
        return client.chat().completions().create(createParams).choices().stream()
                .flatMap(choice -> choice.message().content().stream())
                .collect(Collectors.joining("\n"));
    }

}
