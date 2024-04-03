package me.chat;

import me.chat.application.AskChampUseCase;
import me.chat.application.ListChampionsUseCase;
import me.chat.domain.ports.ChampionsRepository;
import me.chat.domain.ports.GenerativeAiAPI;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

@EnableFeignClients
@SpringBootApplication
public class ChatBotLoLApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChatBotLoLApplication.class, args);
	}
	@Bean
	public ListChampionsUseCase provideListChampUsecase(ChampionsRepository repository) {
		return new ListChampionsUseCase(repository);
	}

	@Bean
	public AskChampUseCase provideAskChampUseCase(ChampionsRepository repository, GenerativeAiAPI genAiAPI){
		return new AskChampUseCase(repository, genAiAPI);
	}
}
