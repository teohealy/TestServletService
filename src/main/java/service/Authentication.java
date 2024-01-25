package service;

public interface Authentication {

    boolean isAuthenticated(String login, String password);
}
