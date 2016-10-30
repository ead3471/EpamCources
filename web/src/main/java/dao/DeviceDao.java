package dao;

import model.Device;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pool.ConnectionPool;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;

/**
 * Created by Freemind on 2016-10-30.
 */
public class DeviceDao {
    private final ConnectionPool connectionPool;
    private final Logger logger= LogManager.getLogger(UserDao.class);
    private final String GET_DEVICES_BY_ONE_CONDITION="SELECT (name,serial,mount_place,last_verification,next_verification,verification_period,passport_url) FROM DEVICES WHERE "



    public DeviceDao(ConnectionPool connectionPool) {
        this.connectionPool = connectionPool;
    }


    public Optional<Device> getDeviceBySerialNumber(String serialNumber) throws InterruptedException, SQLException {
        try(Connection con=connectionPool.takeConnection();
            Statement st=con.createStatement();
            ResultSet rs=st.executeQuery(GET_DEVICES_BY_ONE_CONDITION+"serial="+serialNumber)){
                if(rs.next()){
                  // return Optional.of(new Device())
                }
            return null;

        }
    }
}
