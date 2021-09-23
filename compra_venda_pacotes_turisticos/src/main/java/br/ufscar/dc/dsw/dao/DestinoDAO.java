package br.ufscar.dc.dsw.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.ufscar.dc.dsw.domain.Destino;
import br.ufscar.dc.dsw.domain.PacoteTuristico;


public class DestinoDAO extends GenericDAO {

    public void insert(Destino destino, PacoteTuristico pacote) {

        String sql = "INSERT INTO tb_destino (NOME, ID_PACOTE) VALUES(?, ?)";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setString(1, destino.getNome());
            statement.setFloat(1, pacote.getId());

            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Destino> getAll() {

        List<Destino> listaDestinos = new ArrayList<>();
        Destino destino = null;
        String sql = "SELECT ID_DESTINO, NOME, ID_PACOTE FROM tb_destino";

        try {
        	Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery(); 
            
            while (resultSet.next()) {
                destino = getValues(resultSet);
                listaDestinos.add(destino);
            }

            resultSet.close();
            statement.close();
            conn.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listaDestinos;
    }

    public void delete(Destino destino) {
        String sql = "DELETE FROM tb_destino WHERE ID_DESTINO=?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setLong(1, destino.getId());
            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void update(Destino destino, PacoteTuristico pacote) {
        String sql = "UPDATE tb_destino SET NOME=?, ID_PACOTE=? WHERE ID_DESTINO=?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setString(1, destino.getNome());
            statement.setLong(2, pacote.getId());
            statement.setLong(3, destino.getId());
            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Destino get(Long id) {

        Destino destino = null;
        String sql = "SELECT ID_DESTINO, NOME, ID_PACOTE FROM tb_destino WHERE ID_DESTINO=?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setLong(1, id);

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                destino = getValues(resultSet);
            }

            resultSet.close();
            statement.close();
            conn.close();

            return destino;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private Destino getValues(ResultSet resultSet){
        try{
            
                Long id = resultSet.getLong("ID_DESTINO");
                String nome = resultSet.getString("NOME");
                Long idPacote = resultSet.getLong("ID_PACOTE");
                
                PacoteTuristico pacote = new PacoteTuristicoDAO().get(idPacote);
            
            return new Destino(id, nome, pacote);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
}