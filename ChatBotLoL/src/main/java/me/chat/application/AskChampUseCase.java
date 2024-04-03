package me.chat.application;

import me.chat.Exceptions.ChampionNotFoundException;
import me.chat.domain.ports.ChampionsRepository;
import me.chat.domain.models.Champions;
import me.chat.domain.ports.GenerativeAiAPI;

public record AskChampUseCase(ChampionsRepository repository, GenerativeAiAPI genAiAPI) {
    public String askChampion(String question, Long champid){
        Champions champion = repository.findById(champid).orElseThrow(() -> new ChampionNotFoundException(champid));

    

        String context = champion.generateContextByQuestion(question);
        String objetive = """
                Atue como um campeão do jogo League of Legends, incorpore sua personalidade
                e estilo do campeão, leve em consideração sua história, objetivos e função dentro do jogo.
                Segue a pergunta, nome do campeão, função e sua história.
                """;
        return genAiAPI.generateContent(objetive, context);

    }
}
