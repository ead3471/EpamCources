package controller.servlets;

import dao.DAOException;
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
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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
        try {
            Action requestAction=createActionForRequest(req);
            requestAction.doAction(req,resp);
        }
        catch(Exception ex){
            logger.warn("Error at update record",ex);
            req.setAttribute("error",new DAOException(ex));
            req.getRequestDispatcher("/userpages/common/updateError.jsp").forward(req,resp);
            return;
        }
        req.getRequestDispatcher("/userpages/common/updateOk.jsp").forward(req,resp);
    }


    private Action createActionForRequest(HttpServletRequest request) {
        String action = Optional.ofNullable(request.getParameter("action")).orElse("unknown");
        switch (action) {
            case "update":
                return new UpdateAction();
            case "delete":
                return new DeleteAction();
            case "insert":
                return new InsertAction();
            default:
                return new EmptyAction();
        }


    }
    interface Action{
        void doAction(HttpServletRequest request,HttpServletResponse responce) throws DAOException;
    }


    class EmptyAction implements  Action {
        @Override
        public void doAction(HttpServletRequest request, HttpServletResponse responce) throws DAOException {
            logger.info("Empty action for request"+request.getRequestURI()+request.getQueryString());
        }
    }
    class DeleteAction implements Action{
        @Override
        public void doAction(HttpServletRequest request, HttpServletResponse responce) throws DAOException {
            //TODO Do something if device not found?????
            Optional.ofNullable(request.getParameter("serial")).ifPresent(serial -> {
                try {
                    deviceDao.deleteDevice(serial);
                } catch (DAOException e) {
                    e.printStackTrace();
                }
            });
        }
    }

    class UpdateAction implements  Action{
        @Override
        public void doAction(HttpServletRequest request, HttpServletResponse responce) throws DAOException {

            Optional<Device> deviceForEditOptional=deviceDao.getDeviceBySerial(request.getParameter("serial"));
            if(deviceForEditOptional.isPresent()) {
                Device deviceForEdit=deviceForEditOptional.get();
                Optional.ofNullable(request.getParameter("mountPlace")).ifPresent(mountPlace->deviceForEdit.setMountPlace(mountPlace));
                Optional.ofNullable(request.getParameter("lastVerificationDate")).ifPresent(lastVerification->deviceForEdit.setDateOfLastVerification(LocalDate.parse(lastVerification)));
                Optional.ofNullable(request.getParameter("nextVerificationDate")).ifPresent(nextVerification->deviceForEdit.setDateOfNextVerification(LocalDate.parse(nextVerification)));;
                deviceDao.updateDevice(deviceForEdit);
            }
        }
    }

    class InsertAction implements  Action{

        @Override
        public void doAction(HttpServletRequest request, HttpServletResponse responce) throws DAOException {
            Optional<String> nameOptional=Optional.ofNullable(request.getParameter("name"));
            Optional<String> serialOptional=Optional.ofNullable(request.getParameter("serial"));

            if(nameOptional.isPresent()&&serialOptional.isPresent()){
                    Device newDevice=new Device(nameOptional.get(),serialOptional.get());
                    Optional.ofNullable(request.getParameter("mountPlace")).ifPresent(mountPlace->newDevice.setMountPlace(mountPlace));
                    try{
                        Optional.ofNullable(request.getParameter("lastVerificationDate")).ifPresent(lastVerification->newDevice.setDateOfLastVerification(LocalDate.parse(lastVerification)));
                        Optional.ofNullable(request.getParameter("nextVerificationDate")).ifPresent(nextVerification->newDevice.setDateOfNextVerification(LocalDate.parse(nextVerification)));;
                        deviceDao.insertDevice(newDevice);
                        return;
                    }
                    catch(Exception ex){
                        throw new DAOException( ex);
                    }
            }
            }

        }





}
