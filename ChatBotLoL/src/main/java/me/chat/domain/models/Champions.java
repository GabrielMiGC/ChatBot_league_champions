package me.chat.domain.models;

public record Champions(
		Long id,
		String name,
		String role,
		String lore,
		String imageURL) {

	public String generateContextByQuestion(String question){
		return """
			Nome do Campeão: %s
			Função: %s
			Lore: %s	
			""".formatted(this.name, this.role, this.lore);
	}

}

