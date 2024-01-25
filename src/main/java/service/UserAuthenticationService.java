package service;

import dao.UserDAO;
import model.User;

import java.util.ArrayList;
import java.util.List;

public class UserAuthenticationService implements Authentication{

    private UserDAO userDAO = new UserDAO();

    public UserAuthenticationService() {
    }

    @Override
    public boolean isAuthenticated(String login, String password) {
        try{
            User user = userDAO.findUserByLoginAndPassword(login, password);
            System.out.println(user.toString());
            if (user.getId() == 0){
                return false;
            }else {
                return true;
            }
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public String toString() {
        return "UserAuthenticationService{" +
                "userDAO=" + userDAO +
                '}';
    }
}
