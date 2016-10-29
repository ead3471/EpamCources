package controller.servlets;


import controller.filters.AuthFilter;
import dao.UserDao;
import model.RolesInspector;
import model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.h2.engine.Session;

import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;

/**
 * Created by Freemind on 2016-10-21.
 */
public class AuthController extends HttpServlet {
    private final Logger logger= LogManager.getLogger(AuthController.class);
    private final static String LOGIN_PAGE = "/login.jsp";
    private final static String DEFAULT_AFTER_LOGIN_PAGE="/";
    private UserDao userDao;

    @Override
    public void init() throws ServletException {
        userDao = (UserDao) getServletContext().getAttribute("userDao");
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String requestUser=req.getParameter("user");
        String authString=req.getParameter("authString");
        HttpSession session=req.getSession();
        String destinationUrl=Optional.ofNullable(req.getParameter("userDestinationUrl")).orElse(DEFAULT_AFTER_LOGIN_PAGE);

        try {
            Optional<User> loadedUser = userDao.getUserByName(requestUser, authString);

            if(loadedUser.isPresent())
            {
                session.setAttribute("user",loadedUser.get());
                session.setAttribute("userName",loadedUser.get().getUserName());
                req.getRequestDispatcher(destinationUrl).forward(req,resp);
            }
            else{
                req.setAttribute("login_msg","Cant find user "+requestUser);
                req.getRequestDispatcher(LOGIN_PAGE).forward(req,resp);
            }

        } catch (SQLException |InterruptedException e) {
            req.getRequestDispatcher(LOGIN_PAGE).forward(req,resp);
        }


    }


}
