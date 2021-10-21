package br.ufscar.dc.dsw.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.ufscar.dc.dsw.domain.Foto;
import br.ufscar.dc.dsw.domain.Destino;

public class FotoDAO extends GenericDAO {

    public void insert(Foto foto) {

        String sql = "INSERT INTO tb_foto (ID_DESTINO, LINK) VALUES(?, ?)";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setLong(1, foto.getDestino().getId());
            statement.setLong(2, foto.getId());

            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Foto> getAll() {

        List<Foto> listaFotos = new ArrayList<>();
        Foto foto = null;
        String sql = "SELECT ID_FOTO, ID_DESTINO, LINK FROM tb_foto";

        try {
        	Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery(); 
            
            while (resultSet.next()) {
                foto = getValues(resultSet);
                listaFotos.add(foto);
            }

            resultSet.close();
            statement.close();
            conn.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listaFotos;
    }

    public void delete(Foto foto) {
        String sql = "DELETE FROM tb_foto WHERE ID_FOTO=?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setLong(1, foto.getId());
            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void update(Foto foto) {
        String sql = "UPDATE tb_foto SET ID_DESTINO=?, LINK=? WHERE ID_FOTO=?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setLong(1, foto.getDestino().getId());
            statement.setString(2, foto.getLink());
            statement.setLong(3, foto.getId());
            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Foto get(Long id) {

        Foto foto = null;
        String sql = "SELECT ID_FOTO, ID_DESTINO, LINK FROM tb_foto WHERE ID_FOTO=?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setLong(1, id);

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                foto = getValues(resultSet);
            }

            resultSet.close();
            statement.close();
            conn.close();

            return foto;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private Foto getValues(ResultSet resultSet){
        try{
            
                Long id = resultSet.getLong("ID_FOTO");
                Long idDestino = resultSet.getLong("ID_DESTINO");
                String link = resultSet.getString("LINK");
                
                Destino destino = new DestinoDAO().get(idDestino);
            
            return new Foto(id, destino, link);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
}