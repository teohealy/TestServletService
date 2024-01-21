package servlet;

import model.User;
import util.Utils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


@WebServlet("/delete-user")
public class DeleteUserServlet extends HttpServlet {

    private Map<Integer, User> userMap;

    @Override
    public void init() throws ServletException {
        System.out.println("SERVLET FOR DELETE IS INIT");

        final Object userMap = getServletContext().getAttribute("users");

        if (userMap == null || !(userMap instanceof ConcurrentHashMap)){
            throw new IllegalStateException("Данные не проицициализированы");
        }else{
            this.userMap = (ConcurrentHashMap<Integer, User>) userMap;
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        if (Utils.idIsNumber(req)){
            userMap.remove(Integer.parseInt(req.getParameter("id")));
        }

        resp.sendRedirect(req.getContextPath() + "/users");

    }

    @Override
    public void destroy() {
        System.out.println("SERVLET FOR DELETE IS DESTROY");
    }

}
