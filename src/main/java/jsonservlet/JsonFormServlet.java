package jsonservlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/json-form")
public class JsonFormServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/jsonPost.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final String data = req.getParameter("data");
        final User user = new ObjectMapper().readValue(data, User.class);

        logPrintUserData(user);
    }

    private void logPrintUserData(final User user) {
        System.out.println();
        System.out.println();
        System.out.println("************************************************");
        System.out.println("        Id   : " + user.getId());
        System.out.println("        Name : " + user.getName());
        System.out.println("        Age  : " + user.getAge());
        System.out.println("************************************************");
    }


}
