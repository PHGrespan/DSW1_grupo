package br.ufscar.dc.dsw.dao;

import org.springframework.data.repository.CrudRepository;

import br.ufscar.dc.dsw.domain.Cliente;

@SuppressWarnings("unchecked")
public interface IClienteDAO extends CrudRepository<Cliente, Long>{
    Cliente findById(long id);
    Cliente findByCpf(String cpf);
}
