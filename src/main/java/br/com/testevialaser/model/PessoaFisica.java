package br.com.testevialaser.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@NamedQueries({
    @NamedQuery(name=PessoaFisica.BUSCAR_PESSOAS,
                query="SELECT pf FROM PessoaFisica pf WHERE LOWER(pf.nome) LIKE LOWER(:pNome) AND pf.cpf = :pCPF "),
}) 
@Table(name = "pessoa_fisica")
@PrimaryKeyJoinColumn(name = "id_pessoa")
public class PessoaFisica extends Pessoa {
	
	public final static String BUSCAR_PESSOAS = "PessoaFisica.buscarPessoas";
	
	@Column
	private String nome;
	
	@Column
	private String cpf;
	
	@Column(name="dt_nascimento")
	private Date dataNascimento;

	@Column
	private String email;

	@Column
	private String telefone;

	public PessoaFisica() { }
	
	public PessoaFisica(String nome) {
		this.nome = nome;
	}

	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((cpf == null) ? 0 : cpf.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		PessoaFisica other = (PessoaFisica) obj;
		if (cpf == null) {
			if (other.cpf != null)
				return false;
		} else if (!cpf.equals(other.cpf))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "PessoaFisica [nome=" + nome + "]";
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

}
