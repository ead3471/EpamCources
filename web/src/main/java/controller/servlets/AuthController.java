package controller.servlets;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by Freemind on 2016-10-21.
 */
public class AuthController extends HttpServlet {
private final Logger logger= LogManager.getLogger(AuthController.class);
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

       logger.debug(req.getParameter("user"));

        if("root".equals(req.getParameter("user"))){
            HttpSession session=req.getSession();
            session.setAttribute("user",req.getParameter("user"));
            RequestDispatcher rd=req.getRequestDispatcher("/welcome.jsp");
            rd.forward(req,resp);
        }
        else{
            RequestDispatcher rd=req.getRequestDispatcher("/login.html");
            rd.forward(req,resp);
        }


    }
}
