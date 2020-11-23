package br.com.testevialaser.service;

import java.util.List;

import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import br.com.testevialaser.dto.FiltroPessoaDTO;
import br.com.testevialaser.model.Pessoa;
import br.com.testevialaser.model.PessoaFisica;
import br.com.testevialaser.model.PessoaJuridica;

@Named
public class PessoaService {

	@PersistenceContext(unitName = "viaLaserUnit", name = "entityManager")
	private EntityManager entityManager;

	@Transactional
	public void salvarPessoa(Pessoa pessoa) {

		// validar(pessoa);
		
		Integer id = pessoa.getId();
		if(id == null) {
			entityManager.persist(pessoa);
		} else {
			entityManager.merge(pessoa);
		}
		entityManager.flush();
	}

	@SuppressWarnings("unchecked")
	public List<PessoaFisica> buscarPessoasFisica(FiltroPessoaDTO filtroPessoaDTO) {

		String nome = filtroPessoaDTO.getNome();
		String cpf = filtroPessoaDTO.getCpf();
		StringBuffer hql = new StringBuffer();

		hql.append("SELECT pf FROM PessoaFisica pf WHERE (1=1) ");
		if (nome != null && nome.length() > 0) {
			hql.append("AND LOWER(pf.nome) LIKE LOWER(:pNome) ");
		}
		if (cpf != null && cpf.length() > 0) {
			hql.append("AND pf.cpf = :pCPF ");
		}
		Query query = entityManager.createQuery(hql.toString());
		if (nome != null && nome.length() > 0) {
			query.setParameter("pNome", "%" + nome+ "%");
		}
		if (cpf != null && cpf.length() > 0) {
			query.setParameter("pCPF", cpf);
		}
		
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<PessoaJuridica> buscarPessoasJuridica(FiltroPessoaDTO filtroPessoaDTO) {

		String razao = filtroPessoaDTO.getRazaoSocial();
		String nomeFantasia = filtroPessoaDTO.getNomeFantasia();
		String cnpj = filtroPessoaDTO.getCnpj();
		StringBuffer hql = new StringBuffer();

		hql.append("SELECT pj FROM PessoaJuridica pj WHERE (1=1) ");
		if (razao != null && razao.length() > 0) {
			hql.append("AND LOWER(pj.razaoSocial) LIKE LOWER(:pRazaoSocial)  ");
		}
		if (nomeFantasia != null && nomeFantasia.length() > 0) {
			hql.append("AND LOWER(pj.nomeFantasia) LIKE LOWER(:pNomeFantasia)  ");
		}
		if (cnpj != null && cnpj.length() > 0) {
			hql.append("AND pj.cnpj = :pCNPJ ");
		}
		Query query = entityManager.createQuery(hql.toString());
		if (razao != null && razao.length() > 0) {
			query.setParameter("pRazaoSocial", "%" + razao + "%");
		}
		if (nomeFantasia != null && nomeFantasia.length() > 0) {
			query.setParameter("pNomeFantasia", "%" + nomeFantasia + "%");
		}
		if (cnpj != null && cnpj.length() > 0) {
			query.setParameter("pCNPJ", cnpj);
		}
		
		return query.getResultList();
	}

	@Transactional()
	public void excluirPessoa(Pessoa pessoa) {

		entityManager.remove(entityManager.contains(pessoa) ? pessoa : entityManager.merge(pessoa));
		entityManager.flush();
	}

}
