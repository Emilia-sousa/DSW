package br.ufscar.dc.dsw.service.impl;

import java.beans.Transient;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.ufscar.dc.dsw.dao.IPacoteDAO;
import br.ufscar.dc.dsw.domain.PacoteTuristico;
import br.ufscar.dc.dsw.service.spec.IPacoteService;

@Service
@Transactional(readOnly = false)
public class PacoteService implements IPacoteService {

	@Autowired
	IPacoteDAO dao;
	
	public void salvar(PacoteTuristico pacote) {
		dao.save(pacote);
	}

	public void excluir(Long id) {
		dao.deleteById(id);
	}

	@Transactional(readOnly = true)
	public PacoteTuristico buscarPorId(Long id) {
		return dao.findById(id.longValue());
	}

	@Transactional(readOnly = true)
	public List<PacoteTuristico> buscarTodos() {
		return dao.findAll();
	}

	public List<PacoteTuristico> getByKeyword(String keyword){
		return dao.findByKeyword(keyword);
	}
}
