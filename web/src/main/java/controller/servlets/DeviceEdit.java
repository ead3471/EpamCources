package controller.servlets;

import dao.DeviceDao;
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
import java.util.List;
import java.util.Map;

@WebServlet("/userpages/edit/device/")
public class DeviceEdit extends HttpServlet {

    private final static Logger logger = LogManager.getLogger(HttpServlet.class);
    private DeviceDao deviceDao;





    @Override
    public void init(ServletConfig config) throws ServletException {
        deviceDao= (DeviceDao) config.getServletContext().getAttribute("deviceDao");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String editDeviceSerial=req.getParameter("serial");
        if(editDeviceSerial!=null){
            List<Device> devicesInBase=deviceDao.getDevicesByFilter(deviceDao.filter().withSerial(editDeviceSerial));
            if(devicesInBase.size()>0)
            {
                Device deviceForEdit=devicesInBase.get(0);

                Map<String,String[]> parametersMap=req.getParameterMap();

                if(parametersMap.containsKey("mountPlace")){
                    deviceForEdit.setMountPlace(parametersMap.get("mountPlace")[0]);
                }

                if(parametersMap.containsKey("lastVerificationDate")){
                    deviceForEdit.setDateOfLastVerification(LocalDate.parse(parametersMap.get("lastVerificationDate")[0]));
                }

                if(parametersMap.containsKey("nextVerificationDate")){
                    deviceForEdit.setDateOfNextVerification(LocalDate.parse(parametersMap.get("nextVerificationDate")[0]));
                }

                deviceDao.updateDevice(deviceForEdit);

                req.getRequestDispatcher("/userpages/common/updateOk.jsp").forward(req,resp);

            }
        }



    }
}
