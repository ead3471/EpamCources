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
import java.util.Optional;

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
        Optional.ofNullable(req.getParameter("serial")).filter(value->value.length()>0).ifPresent(serial->filter.withSerial(serial));
        Optional.ofNullable(req.getParameter("mountPlace")).filter(value->value.length()>0).ifPresent(mountPlace->filter.withMountPlace(mountPlace));
        Optional.ofNullable(req.getParameter("name")).filter(value->value.length()>0).ifPresent(name->filter.withName(name));
        Optional.ofNullable(req.getParameter("nextVerificationAfter")).filter(value->value.length()>0).ifPresent(nextVerificationAfter->filter.withNextVerificationDateAfter(LocalDate.parse(nextVerificationAfter)));
        Optional.ofNullable(req.getParameter("nextVerificationBefore")).filter(value->value.length()>0).ifPresent(nextVerificationBefore->filter.withNextVerificationDateBefore(LocalDate.parse(nextVerificationBefore)));
        Optional.ofNullable(req.getParameter("startColumn")).filter(value->value.length()>0).ifPresent(column->filter.withColumnGreaterThen(column,req.getParameter("minValue")));
        Optional.ofNullable(req.getParameter("endColumn")).filter(value->value.length()>0).ifPresent(column->filter.withColumnLessThen(column,req.getParameter("maxValue")));
        Optional.ofNullable(req.getParameter("limit")).filter(value->value.length()>0).map(val->filter.withLimit(Integer.parseInt(val))).orElse(filter.withLimit(10));
        Optional.ofNullable(req.getParameter("orderBy")).filter(value->value.length()>0).ifPresent(order->filter.orderBy(order,req.getParameter("orderDirection")));

        return filter;
    }
}
