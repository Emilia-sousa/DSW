package br.ufscar.dc.dsw.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import br.ufscar.dc.dsw.domain.PacoteTuristico;

@SuppressWarnings("unchecked")
public interface IPacoteDAO extends CrudRepository<PacoteTuristico, Long>{

	PacoteTuristico findById(long id);

	List<PacoteTuristico> findAll();
	
	PacoteTuristico save(PacoteTuristico pacote);

	void deleteById(Long id);
}
