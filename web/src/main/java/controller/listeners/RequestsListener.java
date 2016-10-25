package controller.listeners;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.http.HttpServletRequest;


public class RequestsListener  implements ServletRequestListener{
    private final Logger logger= LogManager.getLogger(ServletRequestListener.class);

    public RequestsListener() {
   }

    public void requestDestroyed(ServletRequestEvent arg0) {

        HttpServletRequest request = (HttpServletRequest) arg0.getServletRequest();

        logger.debug("Request from " + request.getRemoteAddr() + " was destroyed.");

    }

    public void requestInitialized(ServletRequestEvent arg0) {

        HttpServletRequest request = (HttpServletRequest) arg0.getServletRequest();

        logger.debug("Request from " + request.getRemoteAddr() + " was created.");

    }

}
