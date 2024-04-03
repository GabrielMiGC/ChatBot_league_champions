package me.chat.adapter.out;

import me.chat.domain.models.Champions;
import me.chat.domain.ports.ChampionsRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ChampionsJDBCRepository implements ChampionsRepository{
	
	private final JdbcTemplate jdbcTemplate;
	private final RowMapper<Champions> rowMapper;
	
	public ChampionsJDBCRepository(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
		
		this.rowMapper = (rs, rowNum) -> new Champions(
				rs.getLong("id"),
				rs.getString("name"),
				rs.getString("role"),
				rs.getString("lore"),
				rs.getString("imageURL")
				);
	}
	@Override
	public List<Champions> findAll() {
		// Chama dentro do Banco de dados a query
		return jdbcTemplate.query("SELECT * FROM CHAMPIONS", rowMapper);
	}

	@Override
	public Optional<Champions> findById(Long id) {
		String sqlQuery = "SELECT * FROM CHAMPIONS WHERE ID = ?";
		List<Champions> champions =  jdbcTemplate.query(sqlQuery, rowMapper,id);
		
		return champions.stream().findFirst();
	}

}
