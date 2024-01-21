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


@WebServlet("/update-user")
public class UpdateUserServlet extends HttpServlet {

    private static String index = "/updateUser.jsp";

    private Map<Integer, User> userMap;

    @Override
    public void init() throws ServletException {
        System.out.println("SERVLET FOR UPDATING IS INIT");

        final Object userMap = getServletContext().getAttribute("users");

        if (userMap == null || !(userMap instanceof ConcurrentHashMap)){
            throw new IllegalStateException("Данные не проицициализированы");
        }else{
            this.userMap = (ConcurrentHashMap<Integer, User>) userMap;
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final String id = req.getParameter("id");

        if (Utils.idIsInvalid(id, userMap)){
            resp.sendRedirect(req.getContextPath() + "/users");
        }

        final User user = userMap.get(Integer.parseInt(id));
        req.setAttribute("user", user);

        req.getRequestDispatcher(index).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        final String id = req.getParameter("id");
        final String name = req.getParameter("name");

        userMap.get(Integer.parseInt(id)).setName(name);

        resp.sendRedirect(req.getContextPath() + "/users");
    }

    @Override
    public void destroy() {
        System.out.println("SERVLET FOR UPDATING IS INIT");
    }
}
