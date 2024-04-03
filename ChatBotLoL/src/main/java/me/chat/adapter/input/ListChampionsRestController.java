package me.chat.adapter.input;

import me.chat.application.ListChampionsUseCase;
import me.chat.domain.models.Champions;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/champions") 
@Tag(name = "Campeões", description = "Endpoint do dominio Campeões")
public record ListChampionsRestController(ListChampionsUseCase useCase) {
	
	@GetMapping("/champions")
	public List<Champions> findAllChampions(){
		return useCase.findAll();
	}
}
