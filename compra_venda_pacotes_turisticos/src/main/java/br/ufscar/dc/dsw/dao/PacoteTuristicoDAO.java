package br.ufscar.dc.dsw.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.ufscar.dc.dsw.domain.PacoteTuristico;
import br.ufscar.dc.dsw.domain.AgenciaTurismo;

public class PacoteTuristicoDAO extends GenericDAO {

    public void insert(PacoteTuristico pacote) {

        String sql = "INSERT INTO TB_PACOTE_TURISTICO (NOME, CNPJ, DATA_PARTIDA, DURACAO_DIAS, VALOR, DESCRICAO, DESTINOS, FOTOS) VALUES(?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setString(1, pacote.getNome());
            statement.setString(2, pacote.getAgencia().getCnpj());
            statement.setString(3, pacote.getDataPartida());
            statement.setInt(4, pacote.getDuracao());
            statement.setFloat(5, pacote.getValor());
            statement.setString(6, pacote.getDescricao());
            statement.setString(7, pacote.getDestinos());
            statement.setString(8, pacote.getFotos());
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
        String sql = "SELECT * FROM TB_PACOTE_TURISTICO";

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
        String sql = "DELETE FROM TB_PACOTE_TURISTICO WHERE NOME=?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setString(1, pacote.getNome());
            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void update(PacoteTuristico pacote) {
        String sql = "UPDATE TB_PACOTE_TURISTICO SET CNPJ=?, DATA_PARTIDA=?, DURACAO_DIAS=?, VALOR=?, DESCRICAO=?, DESTINOS=?, FOTOS=? WHERE NOME=?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setString(1, pacote.getAgencia().getCnpj());
            statement.setString(2, pacote.getDataPartida());
            statement.setInt(3, pacote.getDuracao());
            statement.setFloat(4, pacote.getValor());
            statement.setString(5, pacote.getDescricao());
            statement.setString(6, pacote.getDestinos());
            statement.setString(7, pacote.getFotos());
            statement.setString(8, pacote.getNome());
            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public PacoteTuristico getByNome(String nome) {

        PacoteTuristico pacote = null;
        String sql = "SELECT * FROM TB_PACOTE_TURISTICO WHERE NOME=?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setString(1, nome);

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

            String nome = resultSet.getString("NOME");
            String cnpj = resultSet.getString("CNPJ");
            String dataPartida = resultSet.getString("DATA_PARTIDA");
            Integer duracao = resultSet.getInt("DURACAO_DIAS");
            Float valor = resultSet.getFloat("VALOR");
            String descricao = resultSet.getString("DESCRICAO");
            String destinos = resultSet.getString("DESTINOS");
            String fotos = resultSet.getString("FOTOS");
            
            AgenciaTurismo agencia = new AgenciaTurismoDAO().getByCnpj(cnpj);
            
            return new PacoteTuristico(nome, agencia, dataPartida, duracao, valor, descricao, destinos, fotos);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
}