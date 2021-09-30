package br.ufscar.dc.dsw.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.ufscar.dc.dsw.domain.Cliente;


public class ClienteDAO extends GenericDAO {

    public void insert(Cliente cliente) {

        String sql = "INSERT INTO TB_CLIENTE (EMAIL, SENHA, CPF, IS_ADM, NOME, TELEFONE, SEXO, DATA_NASC) VALUES(?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setString(1, cliente.getEmail());
            statement.setString(2, cliente.getSenha());
            statement.setString(3, cliente.getCpf());
            statement.setString(4, cliente.getIsAdm());
            statement.setString(5, cliente.getNome());
            statement.setString(6, cliente.getTelefone());
            statement.setString(7, cliente.getSexo());
            statement.setString(8, cliente.getDataNasc());
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

        String sql = "SELECT * FROM TB_CLIENTE";

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
        String sql = "DELETE FROM TB_CLIENTE WHERE CPF=?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setString(1, cliente.getCpf());
            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
        }
    }

    public void update(Cliente cliente) {
        String sql = "UPDATE TB_CLIENTE SET EMAIL=?, SENHA=?, IS_ADM=?, NOME=?, TELEFONE=?, SEXO=?, DATA_NASC=? WHERE CPF=?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setString(1, cliente.getEmail());
            statement.setString(2, cliente.getSenha());
            statement.setString(3, cliente.getIsAdm()); 
            statement.setString(4, cliente.getNome());
            statement.setString(5, cliente.getTelefone());
            statement.setString(6, cliente.getSexo());
            statement.setString(7, cliente.getDataNasc());
            statement.setString(8, cliente.getCpf());
            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Cliente getByCpf(String cpf) {

        Cliente cliente = null;
        String sql = "SELECT * FROM TB_CLIENTE WHERE CPF=?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setString(1, cpf);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                cliente = getValues(resultSet);
            }

            resultSet.close();
            statement.close();
            conn.close();
            return cliente;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    public Cliente getByEmail(String email) {

        Cliente cliente = null;
        String sql = "SELECT * FROM TB_CLIENTE WHERE EMAIL=?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setString(1, email);
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
            String email = resultSet.getString("EMAIL");
            String senha = resultSet.getString("SENHA");
            String cpf = resultSet.getString("CPF");
            String isAdm = resultSet.getString("IS_ADM");
            String nome = resultSet.getString("NOME");
            String telefone = resultSet.getString("TELEFONE");
            String sexo = resultSet.getString("SEXO");
            String dataNasc = resultSet.getString("DATA_NASC");

            return new Cliente(email, senha, cpf, isAdm, nome, telefone, sexo, dataNasc);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}