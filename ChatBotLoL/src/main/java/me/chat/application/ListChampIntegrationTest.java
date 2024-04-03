package me.chat.application;

import me.chat.domain.models.Champions;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import java.util.List;

@SpringBootTest
public class ListChampIntegrationTest {
	
	@Autowired
	private ListChampionsUseCase listChampionsUseCase;
	
	@Test
	public void testListChamps() {
		List<Champions> champions = listChampionsUseCase.findAll();
		Assertions.assertEquals(12, champions.size());
	}
}
