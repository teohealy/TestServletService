package servlet;

import model.User;
import util.Utils;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


@WebListener
public class ContextListener implements ServletContextListener {

    private Map<Integer, User> userMap;

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {

        System.out.println("CONTEXT LISTENER IS INIT");

        final ServletContext servletContext = servletContextEvent.getServletContext();

        userMap = new ConcurrentHashMap<>();

        servletContext.setAttribute("users", userMap);

        final User user = Utils.createUser(1, "Первый", 10);
        this.userMap.put(user.getId(), user);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        userMap = null;
        System.out.println("CONTEXT LISTENER IS DESTROY");
    }
}
