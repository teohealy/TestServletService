package servlet;

import service.UserAuthenticationService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private UserAuthenticationService userAuthenticationService = new UserAuthenticationService();

    @Override
    public void init() throws ServletException {
        //this.userAuthenticationService = (UserAuthenticationService) getServletContext()
        System.out.println("SERVLET IS INIT");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/login.html").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final String login = req.getParameter("login");
        final String password = req.getParameter("password");

        System.out.println(userAuthenticationService);
        boolean res = userAuthenticationService.isAuthenticated(login, password);
        System.out.println(res);

        if (res){
            final HttpSession userSession = req.getSession();
            userSession.setAttribute("login", login);
            resp.sendRedirect(req.getContextPath() + "/menu");
            System.out.println("Вы успешно вошли в систему");
        }else {
            resp.setCharacterEncoding("UTF-8");
            resp.setContentType("text/HTML");
            resp.getWriter().write("Неверный логин или пароль");
            System.out.println("Неверный логин или пароль");
        }
    }
}
