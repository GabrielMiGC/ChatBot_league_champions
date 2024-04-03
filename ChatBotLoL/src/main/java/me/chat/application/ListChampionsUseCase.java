package me.chat.application;

import me.chat.domain.models.Champions;
import me.chat.domain.ports.ChampionsRepository;

import java.util.List;

public record ListChampionsUseCase(ChampionsRepository repository){
	public List<Champions> findAll(){
		return repository.findAll();
	}
}
