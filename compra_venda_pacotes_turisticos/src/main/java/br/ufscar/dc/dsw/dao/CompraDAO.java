package br.ufscar.dc.dsw.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.ufscar.dc.dsw.domain.Compra;
import br.ufscar.dc.dsw.domain.Cliente;
import br.ufscar.dc.dsw.domain.PacoteTuristico;


public class CompraDAO extends GenericDAO {

    public void insert(Compra compra) {

        String sql = "INSERT INTO TB_COMPRA (CPF, NOME_PACOTE) VALUES(?, ?)";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setString(1, compra.getCliente().getCpf());
            statement.setString(2, compra.getPacote().getNome());

            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Compra> getAll() {

        List<Compra> listaCompras = new ArrayList<>();
        Compra compra = null;
        String sql = "SELECT * FROM TB_COMPRA";

        try {
        	Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery(); 
            
            while (resultSet.next()) {
                compra = getValues(resultSet);
                listaCompras.add(compra);
            }

            resultSet.close();
            statement.close();
            conn.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listaCompras;
    }

    public void delete(Compra compra) {
        String sql = "DELETE FROM TB_COMPRA WHERE CPF=? AND NOME_PACOTE=?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setString(1, compra.getCliente().getCpf());
            statement.setString(2, compra.getPacote().getNome());
            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
        }
    }

    public Compra getByCpf(String cpf) {

        Compra compra = null;
        String sql = "SELECT * FROM tb_compra WHERE CPF=?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setString(1, cpf);

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                compra = getValues(resultSet);
            }

            resultSet.close();
            statement.close();
            conn.close();

            return compra;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private Compra getValues(ResultSet resultSet){
        try{
        
            String cpfCliente = resultSet.getString("CPF");
            String nomePacote = resultSet.getString("NOME_PACOTE");
            
            Cliente cliente = new ClienteDAO().getByCpf(cpfCliente);
            PacoteTuristico pacote = new PacoteTuristicoDAO().getByNome(nomePacote);
            
            return new Compra(cliente, pacote);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
}