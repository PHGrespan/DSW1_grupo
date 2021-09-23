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

    public void insert(Cliente cliente, User user) {

        String sql = "INSERT INTO tb_cliente (ID_USER, CPF, NOME, TELEFONE, SEXO, DATA_NASC) VALUES(?, ?, ?, ?, ?, ?)";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setLong(1, user.getId());
            statement.setString(2, cliente.getCpf());
            statement.setString(3, cliente.getNome());
            statement.setString(4, cliente.getTelefone());
            statement.setString(5, cliente.getSexo());
            statement.setString(6, cliente.getDataNasc());
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

        String sql = "SELECT ID_CLIENTE, ID_USER, CPF, NOME, TELEFONE, SEXO, DATA_NASC FROM tb_cliente";

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
        String sql = "DELETE FROM tb_client WHERE ID_CLIENTE=?";

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

    public void update(Cliente cliente, User user) {
        String sql = "UPDATE tb_cliente SET ID_USER=?, CPF=?, NOME=?, TELEFONE=?, SEXO=?, DATA_NASC=? WHERE ID_CLIENTE=?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setLong(1, user.getId());
            statement.setString(2, cliente.getCpf());
            statement.setString(3, cliente.getNome());
            statement.setString(4, cliente.getTelefone());
            statement.setString(5, cliente.getSexo());
            statement.setString(6, cliente.getDataNasc());
            statement.setLong(7, cliente.getId());
            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Cliente get(Long id) {

        Cliente cliente = null;
        String sql = "SELECT ID_USER, CPF, NOME, TELEFONE, SEXO, DATA_NASC FROM tb_cliente WHERE ID_CLIENTE=?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setLong(1, id);
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
            long id = resultSet.getLong("ID_CLIENTE");
            long idUser = resultSet.getLong("ID_USER");
            String cpf = resultSet.getString("CPF");
            String nome = resultSet.getString("NOME");
            String telefone = resultSet.getString("TELEFONE");
            String sexo = resultSet.getString("SEXO");
            String dataNasc = resultSet.getString("DATA_NASC");

            User user = new UserDAO().get(idUser);
            
            return new Cliente(id, user, cpf, nome, telefone, sexo, dataNasc);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}