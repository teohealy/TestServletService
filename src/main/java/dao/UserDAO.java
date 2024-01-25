package dao;

import database.DBConnector;
import model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class UserDAO {

    private Connection connection = null;
    private DBConnector dbConnector = new DBConnector();

    public User getUserByRole(int userRole) throws SQLException, ClassNotFoundException{
        User user = new User();
        String query = "SELECT * FROM users WHERE user_role = ?";
        connection = dbConnector.getDbConnection();

        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, userRole);

        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()){
            user.setId(resultSet.getInt(1));
            user.setLogin(resultSet.getString(2));
            user.setPassword(resultSet.getString(3));
            user.setRole(resultSet.getInt(4));
        }
        return user;
    }

    public User findUserByLoginAndPassword(String userLogin, String userPassword) throws SQLException, ClassNotFoundException{
        System.out.println("CHECK DAO");
        User user = new User();
        String query = "SELECT * FROM users WHERE user_login = ? AND user_password = ?";
        connection = dbConnector.getDbConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(query);

        preparedStatement.setString(1, userLogin);
        preparedStatement.setString(2, userPassword);
        ResultSet resultSet = preparedStatement.executeQuery();

        while(resultSet.next()){
            user.setId(resultSet.getInt(1));
            user.setLogin(resultSet.getString(2));
            user.setPassword(resultSet.getString(3));
            user.setRole(resultSet.getInt(4));
        }
        return user;
    }
}
