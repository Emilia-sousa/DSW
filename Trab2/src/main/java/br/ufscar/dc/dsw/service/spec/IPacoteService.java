package br.ufscar.dc.dsw.service.spec;

import java.util.List;

import br.ufscar.dc.dsw.domain.PacoteTuristico;

public interface IPacoteService {

	PacoteTuristico buscarPorId(Long id);
	
	List<PacoteTuristico> buscarTodos();
	
	void salvar(PacoteTuristico pacote);
	
	void excluir(Long id);
	
}
