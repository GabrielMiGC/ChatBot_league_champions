package me.chat.domain.ports;

import me.chat.domain.models.Champions;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

@Repository
public interface ChampionsRepository {
	List<Champions> findAll();

	Optional<Champions> findById(Long id);
}
