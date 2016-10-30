package dao;

import model.Device;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pool.ConnectionPool;

import java.sql.*;

import java.time.LocalDate;

import java.util.ArrayList;
import java.util.List;

public class DeviceDao {
    private final ConnectionPool connectionPool;
    private final Logger logger= LogManager.getLogger(UserDao.class);
    private final String GET_ALL_DEVICES_SQL ="SELECT name,serial,mount_place,last_verification,next_verification,passport_url FROM DEVICES";


    public DeviceDao(ConnectionPool connectionPool) {
        this.connectionPool = connectionPool;
    }

    private List<Device> getDevicesBySql(String sql) {
        List<Device> resultList=new ArrayList<>();
        try(Connection con=connectionPool.takeConnection();
            Statement st=con.createStatement();
            ResultSet rs=st.executeQuery(sql)) {
            while (rs.next()) {

                String firstColumn=rs.getString(2);
                resultList.add(new Device(rs.getString(1),
                                        rs.getString("serial"),
                                        rs.getDate("next_verification").toLocalDate(),
                                        rs.getDate("last_verification").toLocalDate(),
                                        rs.getString("mount_place")
                        ));
            }
        }
        catch(Exception ex){
            logger.warn("Exception at load devices by sql:"+sql,ex);

        }
        return resultList;
    }

    public List<Device> getDevicesByFilter(DeviceFilter filter){
        return getDevicesBySql(filter.build());
    }

    public List<Device> getAll(){
        return getDevicesBySql(GET_ALL_DEVICES_SQL);
    }

    public boolean insertDevice(Device device){
        StringBuilder insertSQl=new StringBuilder("INSERT INTO DEVICES (name,serial,mount_place,last_verification,next_verification,passport_url) values (")
                .append("'").append(device.getName()).append("',")
                .append("'").append(device.getSerialNumber()).append("',")
                .append("'").append(device.getMountPlace()).append("',")
                .append("'").append(device.getDateOfLastVerification()).append("',")
                .append("'").append(device.getDateOfNextVerification()).append("',")
                .append("'").append(device.getPassportImgUrl()).append("')");

        try(Connection con=connectionPool.takeConnection();Statement st=con.createStatement()){
            st.executeUpdate(insertSQl.toString());
            return true;
        }
        catch (SQLException|InterruptedException ex)
        {
            logger.warn("Error insert new Device",ex);
            return false;
        }
    }



    public boolean updateDevice(Device device){
        StringBuilder sqlStringBuder=new StringBuilder("UPDATE DEVICES SET mount_place='")
                .append(device.getMountPlace()).append("',")
                .append("last_verification='").append(device.getDateOfLastVerification()).append("',")
                .append("next_verification='").append(device.getDateOfNextVerification()).append("',")
                .append("passport_url='").append(device.getPassportImgUrl()).append("'")
                .append(" WHERE serial='").append(device.getSerialNumber()).append("'");

        try(Connection con=connectionPool.takeConnection();
               Statement st=con.createStatement() ){
                st.executeUpdate(sqlStringBuder.toString());
            return true;
        }
        catch(SQLException|InterruptedException ex){
            logger.warn("Error update device",ex);
            return false;
        }
    }






    public DeviceFilter filter(){
        return new DeviceFilter();
    }

    public class DeviceFilter{
        private DeviceFilter(){}
        private  StringBuilder resultSqlBuilder= new StringBuilder("SELECT name,serial,mount_place,last_verification,next_verification,passport_url FROM DEVICES");

        ArrayList<String> filterConditions=new ArrayList();
        private void addCondition(String sql){
            filterConditions.add(sql);
        }

        public DeviceFilter withName(String name){
            addCondition("name='"+name+"'");
            return this;
        }

        public DeviceFilter withMountPlace(String mountPlace){
            addCondition("mount_place='"+mountPlace+"'");
            return this;
        }

        public DeviceFilter withNextVerificationDateBefore(LocalDate date){
            addCondition("next_verification<'"+ Date.valueOf(date)+"'");
            return this;
        }

        public DeviceFilter withNextVerificationDateAfter(LocalDate date){
            addCondition("next_verification>'"+ Date.valueOf(date)+"'");
            return this;
        }

        public DeviceFilter withSerial(String serial){
            addCondition("serial='"+ serial+"'");
            return this;
        }



        private String build(){
            if(filterConditions.size()==0)
                    return resultSqlBuilder.toString();
            else{
              resultSqlBuilder.append(" WHERE ").append(filterConditions.get(0));
              int i=1;
              while(i<filterConditions.size()){
                  resultSqlBuilder.append(" AND ").append(filterConditions.get(i));
                  i++;
              }
                return resultSqlBuilder.toString();
            }
        }
    }
}
