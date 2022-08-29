package br.ufscar.dc.dsw.service.impl;

import java.beans.Transient;
import java.util.List;
import java.util.Optional;

import com.sun.org.apache.xpath.internal.operations.Bool;
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

	public void cancelar(Long id){
		Optional<PacoteTuristico> pacoteTuristico = dao.findById(id);
		if(pacoteTuristico.isPresent()){
			PacoteTuristico pacoteACancelar = pacoteTuristico.get();
			pacoteACancelar.setAtivo(Boolean.FALSE);
			dao.save(pacoteACancelar);
		}
	}

	@Transactional(readOnly = true)
	public PacoteTuristico buscarPorId(Long id) {
		return dao.findById(id.longValue());
	}

	@Transactional(readOnly = true)
	public List<PacoteTuristico> buscarTodos() {
		return dao.findAll();
	}

	@Transactional(readOnly = true)
	public List<PacoteTuristico> buscarTodosAtivos() {
		return dao.findAllByAtivo(Boolean.TRUE);
	}

	public List<PacoteTuristico> getByKeyword(String keyword){
		return dao.findByKeyword(keyword);
	}
}
