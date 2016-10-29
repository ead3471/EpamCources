package controller.filters;

import dao.UserDao;
import model.RolesInspector;
import model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;

/**
 * Created by Freemind on 2016-10-22.
 */
public class AuthFilter extends HttpFilter {
    private final static Logger logger = LogManager.getLogger(AuthFilter.class);
    private final static String LOGIN_PAGE = "/login.jsp";
    private UserDao userDao;
    private RolesInspector rolesInspector;


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        ServletContext servletContext = filterConfig.getServletContext();
        userDao = (UserDao) servletContext.getAttribute("userDao");
        rolesInspector=(RolesInspector) servletContext.getAttribute("rolesInspector");
    }


    private void setUserAfterLoginUrl(HttpServletRequest request, HttpSession session){
        String requestQuery=request.getQueryString();
        if(requestQuery!=null){
           request.setAttribute("userAfterLoginUrl",request.getRequestURI()+"?"+requestQuery);
        }
        else
            request.setAttribute("userAfterLoginUrl",request.getRequestURI());
    }


    @Override
    public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {

        //logger.debug("In the filter " + request.getRequestURL().toString());

        HttpSession session = request.getSession();

        if(request.getParameter("logout")!=null){
            logger.debug("logout user "+session.getAttribute("userName"));
            request.setAttribute("userAfterLoginUrl","");
            session.setAttribute("user",null);
            session.invalidate();
            RequestDispatcher rd = request.getRequestDispatcher(LOGIN_PAGE);
            rd.forward(request, response);
        }

        setUserAfterLoginUrl(request,session);
        if (userIsLoggedIn(session) && userRequestIsValid(session, request)) {
            logger.debug("User"+session.getAttribute("userName")+" accepted access to"+request.getRequestURI());
            chain.doFilter(request, response);
        }else if(tryAuthoriseUser(session, request)&& userRequestIsValid(session, request)){
            logger.debug("User"+session.getAttribute("userName")+" is logged in and access accepted to "+request.getRequestURI());
            chain.doFilter(request, response);
        }
        else {
            RequestDispatcher rd = request.getRequestDispatcher(LOGIN_PAGE);
            rd.forward(request, response);
        }
    }

    private boolean tryAuthoriseUser(HttpSession session, HttpServletRequest request) {
        String user = request.getParameter("user");
        String pass = request.getParameter("password");
        if (user != null && pass != null) {
            try {
                Optional<User> loadedUser=userDao.getUserByName(request.getParameter("user"), request.getParameter("password"));
                if (!loadedUser.isPresent()) {
                    logger.debug("user with"+user+"/"+pass+" not found. Auth failed");
                    session.setAttribute("login_msg", "Authorization error");
                    return false;
                }
                logger.debug("user with"+user+"/"+pass+" found. Auth OK");
                session.setAttribute("user", loadedUser.get());
                session.setAttribute("userName",loadedUser.get().getUserName());
                return true;
            } catch (Exception e) {
                logger.warn("Server error. user "+user+"/"+pass);
                session.setAttribute("login_msg", "Server error during logging in. Please try again");
                logger.warn(e);
                return false;
            }
        }
        return false;
    }



    private boolean userIsLoggedIn(HttpSession session) {
        return session.getAttribute("user")!=null;
    }

    private boolean userRequestIsValid(HttpSession session, HttpServletRequest request){

        Optional<User> sessionUser=Optional.ofNullable((User) session.getAttribute("user"));
        if(sessionUser.isPresent()){
            boolean accessGranted= rolesInspector.isUserRequestAccessed(sessionUser.get(),request.getRequestURI());
            logger.debug("Access for user "+sessionUser.get().getUserName()+" to "+request.getRequestURI()+":"+accessGranted);
            return accessGranted;
        }
        logger.debug("Access to "+request.getRequestURI()+": Failed: user not found");

        return false;
    }




}
