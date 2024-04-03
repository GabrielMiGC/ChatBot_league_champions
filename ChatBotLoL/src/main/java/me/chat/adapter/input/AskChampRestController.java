package me.chat.adapter.input;

import me.chat.application.AskChampUseCase;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Campeões", description = "test")
@RestController
@RequestMapping
public record AskChampRestController(AskChampUseCase useCase) {

    @PostMapping("/{champid}/ask")
    public AskChampResponse askChampion(@PathVariable Long champId,
                                        @RequestBody AskChampRequest request){
        String answer = useCase.askChampion(request.question(), champId);
        return new AskChampResponse(answer);
    }
    public record AskChampRequest(String question){}
    public record AskChampResponse(String answer){}
}
