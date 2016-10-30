package controller.listeners;

import dao.DeviceDao;
import dao.UserDao;
import model.RolesInspector;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import pool.ConnectionPool;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import java.util.Properties;


/**
 * Created by Freemind on 2016-10-23.
 */
public class ServerSetupLoader implements ServletContextListener {

    private final Logger logger= LogManager.getLogger(ServerSetupLoader.class);
    private final  String DB_CONN_INIT_KEY="sqlConnectionProperties";
    private final String DB_INIT_SCRIPT_KEY="initBaseFromFileScript";

    private final String USER_DAO_KEY="userDao";
    private final String DEVICE_DAO_KEY="deviceDao";
    private final String ROLES_INSPECTOR_KEY="rolesInspector";


    @Override
    public void contextInitialized(ServletContextEvent sce) {
        try {
            ConnectionPool connectionPool=initConnectionPool(sce.getServletContext());
            initBase(sce.getServletContext(),connectionPool);
            initDAO(connectionPool,sce.getServletContext());
            initRolesInspector(sce);
        } catch (Exception e) {
            logger.warn("Error during connection pool init",e);
        }

    }

    private void initDAO(ConnectionPool connectionPool, ServletContext servletContext){
        UserDao userDao=new UserDao(connectionPool);
        servletContext.setAttribute(USER_DAO_KEY,userDao);

        DeviceDao deviceDao=new DeviceDao(connectionPool);
        servletContext.setAttribute(DEVICE_DAO_KEY,deviceDao);

    }

    private ConnectionPool initConnectionPool(ServletContext servletContext) throws IOException, SQLException {
        String initBaseConnectionFile=servletContext.getRealPath("/WEB-INF/classes/")+servletContext.getInitParameter(DB_CONN_INIT_KEY);

        Properties dbConnSetup=new Properties();
        try(FileReader reader=new FileReader(initBaseConnectionFile) ){
            dbConnSetup.load(reader);
            return ConnectionPool.getInstance(dbConnSetup);
        }
    }

    private void initBase(ServletContext servletContext,ConnectionPool connectionPool) throws InterruptedException, FileNotFoundException, SQLException {
        String initBaseFile=servletContext.getInitParameter(DB_INIT_SCRIPT_KEY);
        if(initBaseFile!=null){
            try(Connection con=connectionPool.takeConnection()){
                ConnectionPool.executeSQLFromFile(servletContext.getRealPath("/WEB-INF/classes/")+initBaseFile,con);
            }
        }
    }

    private void initRolesInspector(ServletContextEvent sce){
        sce.getServletContext().setAttribute(ROLES_INSPECTOR_KEY, RolesInspector.getMockRolesInspector());
    }




}
