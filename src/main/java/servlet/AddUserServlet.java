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
import java.util.concurrent.atomic.AtomicInteger;


@WebServlet("/add-user")
public class AddUserServlet extends HttpServlet {

    private Map<Integer, User> userMap;
    private AtomicInteger id;

    @Override
    public void init() throws ServletException {
        System.out.println("SERVLET FOR ADDITION IS INIT");

        final Object userMap = getServletContext().getAttribute("users");

        if (userMap == null || !(userMap instanceof ConcurrentHashMap)){
            throw new IllegalStateException("Данные не проицициализированы");
        }else{
            this.userMap = (ConcurrentHashMap<Integer, User>) userMap;
        }

        id = new AtomicInteger(2);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        if(!Utils.isRequestValid(req)){
            doGet(req, resp);
        }else {
            final String name = req.getParameter("name");
            final String age = req.getParameter("age");

            final User user = new User();
            final int id = this.id.getAndIncrement();
            user.setId(id);
            user.setName(name);
            user.setAge(Integer.parseInt(age));

            userMap.put(id, user);
        }
        resp.sendRedirect(req.getContextPath() + "/users");
    }


    @Override
    public void destroy() {
        System.out.println("SERVLET FOR ADDITION IS DESTROY");
    }
}
