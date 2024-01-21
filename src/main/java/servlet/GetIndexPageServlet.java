package servlet;

import model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


@WebServlet("/users")
public class GetIndexPageServlet extends HttpServlet {

    private static String index = "/users.jsp";

    private Map<Integer, User> userMap;

    @Override
    public void init() throws ServletException{
        System.out.println("SERVLET IS INIT");

        final Object userMap = getServletContext().getAttribute("users");

        if (userMap == null || !(userMap instanceof ConcurrentHashMap)){
            throw new IllegalStateException("Данные не проицициализированы");
        }else{
            this.userMap = (ConcurrentHashMap<Integer, User>) userMap;
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        req.setAttribute("users", userMap.values());
        req.getRequestDispatcher(index).forward(req, res);
    }



    @Override
    public void destroy(){
        System.out.println("SERVLET IS DESTROY");
    }
}
