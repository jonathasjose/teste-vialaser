package br.com.testevialaser.model;

public enum TipoPessoa {
	
	FISICA("Pessoa F�sica"),
	JURIDICA("Pessoa Jur�dica");
	
	private String descricao;
	
	TipoPessoa(String descricao) {
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return descricao;
	}

}
