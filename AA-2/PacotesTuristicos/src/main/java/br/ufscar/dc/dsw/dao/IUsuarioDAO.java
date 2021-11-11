package br.ufscar.dc.dsw.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import br.ufscar.dc.dsw.domain.Usuario;

@SuppressWarnings("unchecked")
public interface IUsuarioDAO extends CrudRepository<Usuario, Long>{
    @Query("SELECT u FROM Usuario u WHERE u.email = :email")
    public Usuario getUserByEmail(@Param("email") String email);

    //SELECT u FROM Cliente c inner join Usuario u on c.id = u.id WHERE u.email = :username;

}
