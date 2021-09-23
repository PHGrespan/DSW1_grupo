package br.ufscar.dc.dsw.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.ufscar.dc.dsw.domain.Compra;
import br.ufscar.dc.dsw.domain.Cliente;
import br.ufscar.dc.dsw.domain.AgenciaTurismo;
import br.ufscar.dc.dsw.domain.PacoteTuristico;


public class CompraDAO extends GenericDAO {

    public void insert(Compra compra) {

        String sql = "INSERT INTO tb_compra (ID_CLIENTE, ID_AGENCIA, ID_PACOTE_TURISTICO, VALOR) VALUES(?, ?, ?, ?)";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setLong(1, compra.getId());
            statement.setLong(2, compra.getAgenciaTurismo().getId());
            statement.setLong(3, compra.getPacoteTuristico().getId());
            statement.setFloat(4, compra.getValor());

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
        String sql = "SELECT ID_COMPRA, ID_CLIENTE, ID_AGENCIA, ID_PACOTE_TURISTICO, VALOR FROM tb_compra";

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
        String sql = "DELETE FROM tb_compra WHERE ID_COMPRA=?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setLong(1, compra.getId());
            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void update(Compra compra) {
        String sql = "UPDATE tb_compra SET ID_CLIENTE=?, ID_AGENCIA=?, ID_PACOTE_TURISTICO=?, VALOR=? WHERE ID_COMPRA=?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setLong(1, compra.getCliente().getId());
            statement.setLong(2, compra.getAgenciaTurismo().getId());
            statement.setLong(3, compra.getPacoteTuristico().getId());
            statement.setFloat(4, compra.getValor());
            statement.setFloat(5, compra.getId());
            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Compra get(Long id) {

        Compra compra = null;
        String sql = "SELECT ID_COMPRA, ID_CLIENTE, ID_AGENCIA, ID_PACOTE_TURISTICO, VALOR FROM tb_compra WHERE ID_COMPRA=?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setLong(1, id);

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
            
                Long id = resultSet.getLong("ID_COMPRA");
                Long idCliente = resultSet.getLong("ID_CLIENTE");
                Long idAgencia = resultSet.getLong("ID_AGENCIA");
                Long idPacote = resultSet.getLong("ID_PACOTE_TURISTICO");
                Float valor = resultSet.getFloat("VALOR");
                
                Cliente cliente = new ClienteDAO().get(idCliente);
                AgenciaTurismo agencia = new AgenciaTurismoDAO().get(idAgencia);
                PacoteTuristico pacote = new PacoteTuristicoDAO().get(idPacote);
            
            return new Compra(id, cliente, agencia, pacote, valor);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
}