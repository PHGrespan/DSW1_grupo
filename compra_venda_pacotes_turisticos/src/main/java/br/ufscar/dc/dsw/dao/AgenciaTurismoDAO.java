package br.ufscar.dc.dsw.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.ufscar.dc.dsw.domain.AgenciaTurismo;


public class AgenciaTurismoDAO extends GenericDAO {

    public void insert(AgenciaTurismo agencia) {

        String sql = "INSERT INTO TB_AGENCIA_TURISMO (EMAIL, SENHA, CNPJ, NOME, DESCRICAO) VALUES(?, ?, ?, ?, ?)";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

     
            statement.setString(1, agencia.getEmail());
            statement.setString(2, agencia.getSenha());           
            statement.setString(3, agencia.getCnpj()); 
            statement.setString(4, agencia.getNome());
            statement.setString(5, agencia.getDescricao());

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
        String sql = "SELECT * FROM TB_AGENCIA_TURISMO";

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
        String sql = "DELETE FROM TB_AGENCIA_TURISMO WHERE CNPJ=?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setString(1, agencia.getCnpj());
            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
        }
    }

    public void update(AgenciaTurismo agencia) {
        String sql = "UPDATE TB_AGENCIA_TURISMO SET EMAIL=?, SENHA=?, NOME=?, DESCRICAO=? WHERE CNPJ=?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setString(1, agencia.getEmail());
            statement.setString(2, agencia.getSenha());
            statement.setString(3, agencia.getNome());
            statement.setString(4, agencia.getDescricao());
            statement.setString(5, agencia.getCnpj());
            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public AgenciaTurismo getByCnpj(String cnpj) {

        String sql = "SELECT * FROM TB_AGENCIA_TURISMO WHERE CNPJ=?";
        AgenciaTurismo agencia = null;
        
        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setString(1, cnpj);
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
    
    public AgenciaTurismo getByEmail(String email) {

        String sql = "SELECT * FROM TB_AGENCIA_TURISMO WHERE EMAIL=?";
        AgenciaTurismo agencia = null;
        
        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setString(1, email);
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
           
            String email = resultSet.getString("EMAIL");
            String senha = resultSet.getString("SENHA");
            String cnpj = resultSet.getString("CNPJ");
            String nome = resultSet.getString("NOME");
            String descricao = resultSet.getString("DESCRICAO");
            
            return new AgenciaTurismo(email, senha, cnpj, nome, descricao);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}