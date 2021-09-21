package br.ufscar.dc.dsw.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.ufscar.dc.dsw.domain.AgenciaTurismo;
import br.ufscar.dc.dsw.domain.User;


public class AgenciaTurismoDAO extends GenericDAO {

    public void insert(AgenciaTurismo agencia, User user) {

        String sql = "INSERT INTO tb_agencia_turismo(ID_USER, CNPJ, NOME, DESCRICAO) VALUES(?, ?, ?, ?)";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setLong(2, user.getId());
            statement.setString(2, agencia.getCnpj());
            statement.setString(3, agencia.getDescricao());
            statement.setString(4, agencia.getDescricao());

            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<AgenciaTurismo> getAll() {

        List<AgenciaTurismo> listaAgencias = new ArrayList<>();

        String sql = "SELECT ID_AGENCIA_TURISMO, ID_USER, CNPJ, NOME, DESCRICAO FROM tb_agencia_turismo";

        try {
        	Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery(); 
            
            while (resultSet.next()) {
                Long id = resultSet.getLong("ID_AGENCIA_TURISMO");
                Long idUser = resultSet.getLong("ID_USER");
                String cnpj = resultSet.getString("CNPJ");
                String nome = resultSet.getString("NOME");
                String descricao = resultSet.getString("DESCRICAO");
                
                User user = new UserDAO().get(idUser);
                AgenciaTurismo agencia = new AgenciaTurismo(id, user, cnpj, nome, descricao);
                listaAgencias.add(agencia);
            }

            resultSet.close();
            statement.close();
            conn.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listaAgencias;
    }

    public void delete(AgenciaTurismo agencia) {
        String sql = "DELETE FROM tb_agencia_turismo WHERE ID_AGENCIA_TURISMO=?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setLong(1, agencia.getId());
            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void update(AgenciaTurismo agencia) {
        String sql = "UPDATE tb_agencia_turismo SET CNPJ=?, NOME=?, DESCRICAO=? WHERE ID_AGENCIA_TURISMO=?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setString(1, agencia.getCnpj());
            statement.setString(2, agencia.getNome());
            statement.setString(3, agencia.getDescricao());
            statement.setLong(4, agencia.getId());
            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public AgenciaTurismo get(Long id) {
        AgenciaTurismo agencia = null;
        String sql = "SELECT ID_USER, CNPJ, NOME, DESCRICAO FROM tb_agencia_turismo WHERE ID_AGENCIA_TURISMO=?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                
                Long idUser = resultSet.getLong("ID_USER");
                String cnpj = resultSet.getString("CNPJ");
                String nome = resultSet.getString("NOME");
                String descricao = resultSet.getString("DESCRICAO");

                User user = new UserDAO().get(idUser);

                agencia = new AgenciaTurismo(id, user, cnpj, nome, descricao);
            }

            resultSet.close();
            statement.close();
            conn.close();

            return agencia;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}