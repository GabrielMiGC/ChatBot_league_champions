package me.chat.adapter.out;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.PostMapping;
import me.chat.domain.ports.GenerativeAiAPI;
import feign.FeignException;
import feign.RequestInterceptor;
import java.util.List;

@FeignClient(name = "openAIAPI", url = "${openai.base-url}", configuration = OpenAiAPI.Config.class)
public interface OpenAiAPI extends GenerativeAiAPI{
    
    @PostMapping("/v1/chat/completions")
    OpenAiChatCompletionResp chatCompletion(OpenAiChatCompletionReq req);
    
    @Override
    default String generateContent(String objective, String context){

        String model = "gpt-3.5-turbo";
        List<Message> messages = List.of(new Message("system", objective),
            new Message("user", context)
        );

        OpenAiChatCompletionReq req = new OpenAiChatCompletionReq(model, messages);
        
        try{
            OpenAiChatCompletionResp resp = chatCompletion(req);
            return resp.choices().get(0).message().content();
        } catch(FeignException httpErrors) {
            return "Erro de comunicação com API da OpenAI.";
        } catch(Exception unexpetedError){
            return "Retorno da API não contem dados esperados.";
        }
    }

    record Message(String role, String content){}
    record Choice(Message message) {}

    record OpenAiChatCompletionReq(String model, List<Message> messages){}
    record OpenAiChatCompletionResp(List<Choice> choices) {}

    class Config {
        @Bean
        public RequestInterceptor apiKeyRequestInterceptor(@Value("${openai.api-key}") String apiKey) {
            return requestTemplate -> requestTemplate.header(
                    HttpHeaders.AUTHORIZATION, "Bearer %s".formatted(apiKey));
        }
    }
}
