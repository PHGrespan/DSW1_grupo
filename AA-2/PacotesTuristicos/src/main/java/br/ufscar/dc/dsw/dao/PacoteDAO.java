package br.ufscar.dc.dsw.dao;

import org.springframework.data.repository.CrudRepository;

import br.ufscar.dc.dsw.domain.Pacote;

@SuppressWarnings("unchecked")
public interface PacoteDAO extends CrudRepository<Pacote, Long>{
    
}
