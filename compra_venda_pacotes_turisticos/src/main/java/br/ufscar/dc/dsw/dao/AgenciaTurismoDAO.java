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

    public void insert(AgenciaTurismo agencia) {

        String sql = "INSERT INTO TB_AGENCIA_TURISMO(EMAIL, SENHA, IS_ADM, CNPJ, NOME, DESCRICAO) VALUES(?, ?, ?, ?, ?, ?)";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

     
            statement.setString(1, agencia.getEmail());
            statement.setString(2, agencia.getSenha());
            statement.setString(3, agencia.getIsAdm());            
            statement.setString(4, agencia.getCnpj());
            statement.setString(5, agencia.getDescricao());
            statement.setString(6, agencia.getDescricao());

            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<AgenciaTurismo> getAll() {

        List<AgenciaTurismo> listaAgencias = new ArrayList<>();
        AgenciaTurismo agencia = null;
        String sql = "SELECT ID_AGENCIA_TURISMO, EMAIL, SENHA, IS_ADM, CNPJ, NOME, DESCRICAO FROM TB_AGENCIA_TURISMO";

        try {
        	Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery(); 
            
            while (resultSet.next()) {
            	agencia = getValues(resultSet);
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
        String sql = "DELETE FROM TB_AGENCIA_TURISMO WHERE ID_AGENCIA_TURISMO=?";

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
        String sql = "UPDATE TB_AGENCIA_TURISMO SET EMAIL=?, SENHA=?, IS_ADM=?, CNPJ=?, NOME=?, DESCRICAO=? WHERE ID_AGENCIA_TURISMO=?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setLong(1, agencia.getId());
            statement.setString(2, agencia.getEmail());
            statement.setString(3, agencia.getSenha());
            statement.setString(4, agencia.getIsAdm()); 
            statement.setString(5, agencia.getCnpj());
            statement.setString(6, agencia.getNome());
            statement.setString(7, agencia.getDescricao());
            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public AgenciaTurismo get(Long id) {

        String sql = "SELECT ID_AGENCIA_TURISMO, EMAIL, SENHA, IS_ADM, CNPJ, NOME, DESCRICAO FROM TB_AGENCIA_TURISMO WHERE ID_AGENCIA_TURISMO=?";
        AgenciaTurismo agencia = null;
        
        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            
            if (resultSet.next()) {
                agencia = getValues(resultSet);
            }

            resultSet.close();
            statement.close();
            conn.close();

            return agencia;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    public AgenciaTurismo getLogin(String id) {

        String sql = "SELECT ID_AGENCIA_TURISMO, EMAIL, SENHA, IS_ADM, CNPJ, NOME, DESCRICAO FROM TB_AGENCIA_TURISMO WHERE EMAIL=?";
        AgenciaTurismo agencia = null;
        
        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setString(1, id);
            ResultSet resultSet = statement.executeQuery();
            
            if (resultSet.next()) {
                agencia = getValues(resultSet);
            }

            resultSet.close();
            statement.close();
            conn.close();

            return agencia;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private AgenciaTurismo getValues(ResultSet resultSet){
        try{
           
            Long id = resultSet.getLong("ID_AGENCIA_TURISMO");
            String email = resultSet.getString("EMAIL");
            String cnpj = resultSet.getString("CNPJ");
            String senha = resultSet.getString("SENHA");
            String isAdm = resultSet.getString("IS_ADM");
            String nome = resultSet.getString("NOME");
            String descricao = resultSet.getString("DESCRICAO");
            
    
            
            return new AgenciaTurismo(id, email, senha, isAdm, cnpj, nome, descricao);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}