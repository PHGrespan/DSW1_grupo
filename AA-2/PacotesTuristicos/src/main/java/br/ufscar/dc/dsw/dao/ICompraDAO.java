package br.ufscar.dc.dsw.dao;

import org.springframework.data.repository.CrudRepository;

import br.ufscar.dc.dsw.domain.Compra;

@SuppressWarnings("unchecked")
public interface ICompraDAO extends CrudRepository<Compra, Long>{
    
}
