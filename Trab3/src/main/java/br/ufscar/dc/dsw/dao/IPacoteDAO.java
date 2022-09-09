package br.ufscar.dc.dsw.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.ufscar.dc.dsw.domain.PacoteTuristico;
import br.ufscar.dc.dsw.domain.Agencia;

@SuppressWarnings("unchecked")
public interface IPacoteDAO extends CrudRepository<PacoteTuristico, Long>{

	PacoteTuristico findById(long id);

	List<PacoteTuristico> findAll();
	
	List<PacoteTuristico> findAllByAtivo(Boolean ativo);

    List<PacoteTuristico> findAllByAgencia(Agencia a);
	
	PacoteTuristico save(PacoteTuristico pacote);


	void deleteById(Long id);

	@Query(value = "select * from PacoteTuristico p where p.destino like %:keyword% or p.dataPartida like %:keyword%", nativeQuery = true)
	List<PacoteTuristico> findByKeyword(@Param("keyword") String keyword);
}
