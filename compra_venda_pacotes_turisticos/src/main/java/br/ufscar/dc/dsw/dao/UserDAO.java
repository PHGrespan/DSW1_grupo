package br.ufscar.dc.dsw.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.ufscar.dc.dsw.domain.User;


public class UserDAO extends GenericDAO {

    public void insert(User user) {

        String sql = "INSERT INTO tb_user (EMAIL, SENHA, IS_ADM) VALUES(?, ?, ?)";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setString(1, user.getEmail());
            statement.setString(2, user.getSenha());
            statement.setBoolean(3, user.getIsAdm());

            statement.executeUpdate();
            statement.close();
            conn.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<User> getAll() {

        List<User> listaUsers = new ArrayList<>();

        String sql = "SELECT ID_USER, EMAIL, SENHA, IS_ADM FROM tb_user";

        try {
            Connection conn = this.getConnection();
            Statement statement = conn.createStatement();

            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {

                long id = resultSet.getLong("ID_USER");
                String email = resultSet.getString("EMAIL");
                String senha = resultSet.getString("SENHA");
                Boolean isAdm = resultSet.getBoolean("IS_ADM");

                User user = new User(id, email, senha, isAdm);
                listaUsers.add(user);
            }

            resultSet.close();
            statement.close();
            conn.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return listaUsers;
    }

    public void delete(User user) {
        String sql = "DELETE FROM tb_user WHERE ID_USER=?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setLong(1, user.getId());
            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void update(User user) {
        String sql = "UPDATE tb_user SET EMAIL=?, SENHA=?, IS_ADM=? WHERE ID_USER=?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setString(1, user.getEmail());
            statement.setString(2, user.getSenha());
            statement.setBoolean(3, user.getIsAdm());
            statement.setLong(4, user.getId());
            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public User get(Long id) {
        User user = null;
        String sql = "SELECT ID_USER, EMAIL, SENHA, IS_ADM FROM tb_user WHERE ID_USER=?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                
                String email = resultSet.getString("EMAIL");
                String senha = resultSet.getString("SENHA");
                Boolean isAdm = resultSet.getBoolean("IS_ADM");

                user = new User(id, email, senha, isAdm);
            }

            resultSet.close();
            statement.close();
            conn.close();

            return user;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}