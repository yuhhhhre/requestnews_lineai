package summary.news_line.logic;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.openai.client.OpenAIClient;
import com.openai.client.okhttp.OpenAIOkHttpClient;
import com.openai.models.ChatModel;
import com.openai.models.chat.completions.ChatCompletionCreateParams;

import jakarta.annotation.PostConstruct;

@Service
public class OpenaiAccess {

        @Value("${chatgpt.apikey}")
        private String apikey;

        private OpenAIClient client;

        @PostConstruct
        public void init() {
                client = OpenAIOkHttpClient.builder()
                        .apiKey(apikey)
                        .build();
        }
        // OpenAIClient client = OpenAIOkHttpClient.builder()
        //         .apiKey(apikey)
        //         .build();

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
