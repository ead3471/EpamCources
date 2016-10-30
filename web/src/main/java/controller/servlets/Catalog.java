package controller.servlets;

import dao.DeviceDao;
import dao.UserDao;
import model.Device;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;

/**
 * Created by Freemind on 2016-10-30.
 */

@WebServlet("/catalog/*")
public class Catalog extends HttpServlet{
    public final static String CATALOG_KEY="devices";
    private final  static String DEFAULT_VIEW="/userpages/common/devices.jsp";
    private DeviceDao deviceDao;
    private final Logger logger= LogManager.getLogger(UserDao.class);

    @Override
    public void init(ServletConfig config) throws ServletException {
       deviceDao=(DeviceDao)config.getServletContext().getAttribute("deviceDao");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      Map parametersMap=req.getParameterMap();

          List<Device> listOfAllDevices=deviceDao.getDevicesByFilter(createFilterFromRequest(req));
        req.setAttribute(CATALOG_KEY,listOfAllDevices);

        if(req.getParameter("forward")!=null){
            req.getRequestDispatcher(req.getParameter("forward")).forward(req,resp);
        }
        else{
          req.getRequestDispatcher(DEFAULT_VIEW).forward(req,resp);
        }
    }

    private DeviceDao.DeviceFilter createFilterFromRequest(HttpServletRequest req) {
        DeviceDao.DeviceFilter filter = deviceDao.filter();
        Map<String,String[]> requestParameters=req.getParameterMap();

        if(!requestParameters.isEmpty()) {

            if (requestParameters.containsKey("serial")) {
                String parameterValue=requestParameters.get("serial")[0];
                if(parameterValue.length()>0)
                    filter.withSerial(parameterValue);
            }
            if (requestParameters.containsKey("mountPlace")) {
                String parameterValue=requestParameters.get("mountPlace")[0];
                if(parameterValue.length()>0)
                    filter.withMountPlace(parameterValue);
            }
            if (requestParameters.containsKey("nextVerificationAfter")) {
                String parameterValue=requestParameters.get("nextVerificationAfter")[0];
                if(parameterValue.length()>0)
                    filter.withNextVerificationDateAfter(LocalDate.parse(parameterValue));
            }

            if (requestParameters.containsKey("nextVerificationBefore")) {
                String parameterValue=requestParameters.get("nextVerificationBefore")[0];
                if(parameterValue.length()>0)
                filter.withNextVerificationDateBefore(LocalDate.parse(parameterValue));
            }

            if (requestParameters.containsKey("name")) {
                String parameterValue=requestParameters.get("name")[0];
                if(parameterValue.length()>0)
                    filter.withName(requestParameters.get("name")[0]);
            }
        }
        return filter;




    }
}
