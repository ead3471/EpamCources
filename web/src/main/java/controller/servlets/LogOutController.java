package controller.servlets;

import org.h2.engine.Session;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Optional;

/**
 * Created by Freemind on 2016-10-29.
 */
public class LogOutController extends HttpServlet{
    private static final String DEFAULT_AFTER_LOGOUT_PAGE="/";

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session=req.getSession();

        if(session!=null){
            session.removeAttribute("user");
            session.removeAttribute("userName");
            session.invalidate();
        }
        req.getRequestDispatcher(DEFAULT_AFTER_LOGOUT_PAGE).forward(req,resp);
    }
}
