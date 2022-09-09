package br.ufscar.dc.dsw.service.spec;

import java.util.List;

import br.ufscar.dc.dsw.domain.PacoteTuristico;
import br.ufscar.dc.dsw.domain.Agencia;

public interface IPacoteService {

	PacoteTuristico buscarPorId(Long id);

	List<PacoteTuristico> buscarTodos();
	
	void salvar(PacoteTuristico pacote);
	
	void excluir(Long id);

	void cancelar(Long id);

	List<PacoteTuristico> getByKeyword(String keyword);

	List<PacoteTuristico> buscarTodosAtivos();

    List<PacoteTuristico> buscarPorAgencia(Agencia agencia);
}
