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

        String sql = "INSERT INTO TB_USER (EMAIL, SENHA, IS_ADM, FUNC) VALUES(?, ?, ?, ?)";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setString(1, user.getEmail());
            statement.setString(2, user.getSenha());
            statement.setBoolean(3, user.getIsAdm());
            statement.setString(4, user.getFunc());

            statement.executeUpdate();
            statement.close();
            conn.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<User> getAll() {

        List<User> listaUsers = new ArrayList<>();

        String sql = "SELECT ID_USER, EMAIL, SENHA, IS_ADM, FUNC FROM TB_USER";

        try {
            Connection conn = this.getConnection();
            Statement statement = conn.createStatement();

            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {

                long id = resultSet.getLong("ID_USER");
                String email = resultSet.getString("EMAIL");
                String senha = resultSet.getString("SENHA");
                Boolean isAdm = resultSet.getBoolean("IS_ADM");
                String func = resultSet.getString("FUNC");

                User user = new User(id, email, senha, isAdm, func);
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
        String sql = "DELETE FROM TB_USER WHERE ID_USER=?";

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
        String sql = "UPDATE TB_USER SET EMAIL=?, SENHA=?, IS_ADM=?, FUNC=? WHERE ID_USER=?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setString(1, user.getEmail());
            statement.setString(2, user.getSenha());
            statement.setBoolean(3, user.getIsAdm());
            statement.setString(4, user.getFunc());
            statement.setLong(5, user.getId());
            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public User get(Long id) {
        User user = null;
        String sql = "SELECT ID_USER, EMAIL, SENHA, IS_ADM, FUNC FROM TB_USER WHERE ID_USER=?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                
                String email = resultSet.getString("EMAIL");
                String senha = resultSet.getString("SENHA");
                Boolean isAdm = resultSet.getBoolean("IS_ADM");
                String func = resultSet.getString("FUNC");

                user = new User(id, email, senha, isAdm, func);
            }

            resultSet.close();
            statement.close();
            conn.close();

            return user;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    public User getLogin(String email) {
        User user = null;
        String sql = "SELECT ID_USER, EMAIL, SENHA, IS_ADM, FUNC FROM TB_USER WHERE EMAIL=?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
            	Long id = resultSet.getLong("ID_USER");
                String senha = resultSet.getString("SENHA");
                Boolean isAdm = resultSet.getBoolean("IS_ADM");
                String func = resultSet.getString("FUNC");

                user = new User(id, email, senha, isAdm, func);
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