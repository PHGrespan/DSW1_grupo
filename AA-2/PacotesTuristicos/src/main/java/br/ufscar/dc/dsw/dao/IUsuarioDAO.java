package br.ufscar.dc.dsw.dao;

import org.springframework.data.repository.CrudRepository;

import br.ufscar.dc.dsw.domain.Usuario;

@SuppressWarnings("unchecked")
public interface IUsuarioDAO extends CrudRepository<Usuario, Long>{
    
}