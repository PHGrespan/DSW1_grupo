package br.ufscar.dc.dsw.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.ufscar.dc.dsw.domain.Cliente;
import br.ufscar.dc.dsw.domain.User;


public class ClienteDAO extends GenericDAO {

    public void insert(Cliente cliente) {

        String sql = "INSERT INTO TB_CLIENTE (ID_USER, EMAIL, SENHA, CPF, IS_ADM, NOME, TELEFONE, SEXO, DATA_NASC) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setLong(1, cliente.getId());
            statement.setString(2, cliente.getEmail());
            statement.setString(3, cliente.getSenha());
            statement.setString(4, cliente.getIsAdm()); 
            statement.setString(5, cliente.getCpf());
            statement.setString(6, cliente.getNome());
            statement.setString(7, cliente.getTelefone());
            statement.setString(8, cliente.getSexo());
            statement.setString(9, cliente.getDataNasc());
            statement.executeUpdate();

            statement.close();
            conn.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Cliente> getAll() {

        List<Cliente> listaClientes = new ArrayList<>();
        Cliente cliente = null;

        String sql = "SELECT ID_CLIENTE, EMAIL, SENHA, CPF, IS_ADM, NOME, TELEFONE, SEXO, DATA_NASC FROM TB_CLIENTE";

        try {
            Connection conn = this.getConnection();
            Statement statement = conn.createStatement();

            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                cliente = getValues(resultSet);
                listaClientes.add(cliente);
            }

            resultSet.close();
            statement.close();
            conn.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listaClientes;
    }

    public void delete(Cliente cliente) {
        String sql = "DELETE FROM TB_CLIENTE WHERE ID_CLIENTE=?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setLong(1, cliente.getId());
            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void update(Cliente cliente) {
        String sql = "UPDATE TB_CLIENTE SET EMAIL=?, SENHA=?, CPF=?, IS_ADM=?, NOME=?, TELEFONE=?, SEXO=?, DATA_NASC=? WHERE ID_CLIENTE=?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setLong(1, cliente.getId());
            statement.setString(2, cliente.getEmail());
            statement.setString(3, cliente.getSenha());
            statement.setString(4, cliente.getIsAdm()); 
            statement.setString(5, cliente.getCpf());
            statement.setString(6, cliente.getNome());
            statement.setString(7, cliente.getTelefone());
            statement.setString(8, cliente.getSexo());
            statement.setString(9, cliente.getDataNasc());
            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Cliente get(Long id) {

        Cliente cliente = null;
        String sql = "SELECT ID_CLIENTE, EMAIL, SENHA, CPF, IS_ADM, NOME, TELEFONE, SEXO, DATA_NASC FROM TB_CLIENTE WHERE ID_CLIENTE=?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
            	String email = resultSet.getString("EMAIL");
                String senha = resultSet.getString("SENHA");
                String cpf = resultSet.getString("CPF");
                String isAdm = resultSet.getString("IS_ADM");
                String nome = resultSet.getString("NOME");
                String telefone = resultSet.getString("TELEFONE");
                String sexo = resultSet.getString("SEXO");
                String dataNasc = resultSet.getString("DATA_NASC");
                
                cliente = new Cliente(id, email, senha, cpf, isAdm, nome, telefone, sexo, dataNasc);
                
                
            }

            resultSet.close();
            statement.close();
            conn.close();
            return cliente;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    public Cliente getLogin(String id) {

        Cliente cliente = null;
        String sql = "SELECT ID_CLIENTE, EMAIL, SENHA, CPF, IS_ADM, NOME, TELEFONE, SEXO, DATA_NASC FROM TB_CLIENTE WHERE EMAIL=?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setString(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                cliente = getValues(resultSet);
            }

            resultSet.close();
            statement.close();
            conn.close();
            
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return cliente;
    }

    private Cliente getValues(ResultSet resultSet){
        try{
            Long id = resultSet.getLong("ID_CLIENTE");
            String email = resultSet.getString("EMAIL");
            String senha = resultSet.getString("SENHA");
            String cpf = resultSet.getString("CPF");
            String isAdm = resultSet.getString("IS_ADM");
            String nome = resultSet.getString("NOME");
            String telefone = resultSet.getString("TELEFONE");
            String sexo = resultSet.getString("SEXO");
            String dataNasc = resultSet.getString("DATA_NASC");

           
            
            return new Cliente(id, email, senha, cpf, isAdm, nome, telefone, sexo, dataNasc);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}