package br.com.testevialaser.bean;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import br.com.testevialaser.dto.FiltroPessoaDTO;
import br.com.testevialaser.model.Pessoa;
import br.com.testevialaser.model.PessoaFisica;
import br.com.testevialaser.model.PessoaJuridica;
import br.com.testevialaser.model.TipoPessoa;
import br.com.testevialaser.service.PessoaService;

@ManagedBean
@ViewScoped
public class PessoaBean {

	@Inject
	private PessoaService pessoaService;
	private FiltroPessoaDTO filtroPessoaDTO;
	private List<PessoaFisica> pessoasFisica = new ArrayList<PessoaFisica>();
	private List<PessoaJuridica> pessoasJuridica = new ArrayList<PessoaJuridica>();
	private PessoaFisica pessoaFisica;
	private PessoaJuridica pessoaJuridica;
	

	@PostConstruct
	public void init() {
		
		filtroPessoaDTO = new FiltroPessoaDTO();
		filtroPessoaDTO.setTipoPessoa(TipoPessoa.FISICA);
		pessoaFisica = new PessoaFisica();
		buscarPessoas();
	}
	
	public void novaPessoa() {
		
		if(isPF()) {
			pessoaFisica = new PessoaFisica();
		} else if(isPJ()) {
			pessoaJuridica = new PessoaJuridica();
		}
		buscarPessoas();
	}
	
	public TipoPessoa[] listaTipoPessoa() {
		return TipoPessoa.values();
	}

	public boolean isPF() {
		return TipoPessoa.FISICA.equals(filtroPessoaDTO.getTipoPessoa());
	}

	public boolean isPJ() {
		return TipoPessoa.JURIDICA.equals(filtroPessoaDTO.getTipoPessoa());
	}
	
	public void buscarPessoas() {
		
		if(isPF()) {
			buscarPessoasFisica();
		} else if(isPJ()) {
			buscarPessoasJuridica();
		}
	}
	
	public void buscarPessoasFisica() {
		setPessoasFisica(pessoaService.buscarPessoasFisica(filtroPessoaDTO));
	}

	public void buscarPessoasJuridica() {
		pessoasJuridica = pessoaService.buscarPessoasJuridica(filtroPessoaDTO);
	}
	
	public void excluirPessoa(Pessoa pessoa) {
		
		pessoaService.excluirPessoa(pessoa);
		buscarPessoas();
	}

	public void salvarPessoa(Pessoa pessoa) {
		
		pessoaService.salvarPessoa(pessoa);
		buscarPessoas();
		limpar();
		novaPessoa();
	}
	

	public void editarPessoa(Pessoa pessoa) {
		
		if(pessoa instanceof PessoaFisica) {
			this.pessoaFisica = (PessoaFisica) pessoa;
		} else {
			this.pessoaJuridica = (PessoaJuridica) pessoa;
		}
	}

	public void limpar() {
		
		filtroPessoaDTO.setRazaoSocial(null);
		filtroPessoaDTO.setNomeFantasia(null);
		filtroPessoaDTO.setCnpj(null);
		filtroPessoaDTO.setNome(null);
		filtroPessoaDTO.setCpf(null);
		pessoaFisica = new PessoaFisica();
		pessoaJuridica = new PessoaJuridica();
	}

	public FiltroPessoaDTO getFiltroPessoaDTO() {
		return filtroPessoaDTO;
	}

	public void setFiltroPessoaDTO(FiltroPessoaDTO filtroPessoaDTO) {
		this.filtroPessoaDTO = filtroPessoaDTO;
	}

	public List<PessoaFisica> getPessoasFisica() {
		return pessoasFisica;
	}

	public void setPessoasFisica(List<PessoaFisica> pessoasFisica) {
		this.pessoasFisica = pessoasFisica;
	}

	public List<PessoaJuridica> getPessoasJuridica() {
		return pessoasJuridica;
	}

	public void setPessoasJuridica(List<PessoaJuridica> pessoasJuridica) {
		this.pessoasJuridica = pessoasJuridica;
	}

	public PessoaFisica getPessoaFisica() {
		return pessoaFisica;
	}

	public void setPessoaFisica(PessoaFisica pessoaFisica) {
		this.pessoaFisica = pessoaFisica;
	}

	public PessoaJuridica getPessoaJuridica() {
		return pessoaJuridica;
	}

	public void setPessoaJuridica(PessoaJuridica pessoaJuridica) {
		this.pessoaJuridica = pessoaJuridica;
	}
	
	public void addTelefone() {
		
		if(pessoaJuridica.getTelefone() != null) {
			pessoaJuridica.getTelefones().add(pessoaJuridica.getTelefone());
		}
		pessoaJuridica.setTelefone(null);
	}
}
