package br.ufscar.dc.dsw.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.ufscar.dc.dsw.domain.AgenciaTurismo;
import br.ufscar.dc.dsw.domain.PacoteTuristico;


public class PacoteTuristicoDAO extends GenericDAO {

    public void insert(PacoteTuristico  pacote, AgenciaTurismo agencia) {

        String sql = "INSERT INTO tb_pacote_turistico (ID_AGENCIA, DATA_PARTIDA, DATA_CHEGADA, VALOR, DESCRICAO) VALUES(?, ?, ?, ?, ?)";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setLong(1, agencia.getId());
            statement.setDate(2, (java.sql.Date) pacote.getDataPartida());
            statement.setDate(3, (java.sql.Date) pacote.getDataChegada());
            statement.setFloat(4, pacote.getValor());
            statement.setString(5, pacote.getDescricao());

            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<PacoteTuristico> getAll() {

        List<PacoteTuristico> listaPacotes = new ArrayList<>();
        PacoteTuristico pacote = null;
        String sql = "SELECT ID_PACOTE_TURISTICO, ID_AGENCIA, DATA_PARTIDA, DATA_CHEGADA, VALOR, DESCRICAO FROM tb_pacote_turistico";

        try {
        	Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery(); 
            
            while (resultSet.next()) {
                pacote = getValues(resultSet);
                listaPacotes.add(pacote);
            }

            resultSet.close();
            statement.close();
            conn.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listaPacotes;
    }

    public void delete(PacoteTuristico pacote) {
        String sql = "DELETE FROM tb_pacote_turistico WHERE ID_PACOTE_TURISTICO=?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setLong(1, pacote.getId());
            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void update(PacoteTuristico pacote) {
        String sql = "UPDATE tb_pacote_turistico SET ID_AGENCIA=?, DATA_PARTIDA=?, DATA_CHEGADA=?, VALOR=?, DESCRICAO=? WHERE ID_PACOTE_TURISTICO=?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setLong(1, pacote.getAgenciaTurismo().getId());
            statement.setDate(2, (java.sql.Date) pacote.getDataPartida());
            statement.setDate(3, (java.sql.Date) pacote.getDataChegada());
            statement.setFloat(4, pacote.getValor());
            statement.setString(4, pacote.getDescricao());
            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public PacoteTuristico get(Long id) {

        PacoteTuristico pacote = null;
        String sql = "SELECT ID_PACOTE_TURISTICO, ID_AGENCIA, DATA_PARTIDA, DATA_CHEGADA, VALOR, DESCRICAO FROM tb_pacote_turistico WHERE ID_PACOTE_TURISTICO=?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setLong(1, id);

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                pacote = getValues(resultSet);
            }

            resultSet.close();
            statement.close();
            conn.close();

            return pacote;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private PacoteTuristico getValues(ResultSet resultSet){
        try{
            
                Long id = resultSet.getLong("ID_PACOTE_TURISTICO");
                Long idAgencia = resultSet.getLong("ID_AGENCIA");
                Date dataPartida = resultSet.getDate("DATA_PARTIDA");
                Date dataChegada = resultSet.getDate("DATA_CHEGADA");
                Float valor = resultSet.getFloat("VALOR");
                String descricao = resultSet.getString("DESCRICAO");
                
                AgenciaTurismo agencia = new AgenciaTurismoDAO().get(idAgencia);
            
            return new PacoteTuristico(id, agencia, dataPartida, dataChegada, valor, descricao);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
}