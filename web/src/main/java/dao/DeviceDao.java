package dao;

import model.Device;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pool.ConnectionPool;

import java.sql.*;

import java.time.LocalDate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DeviceDao {
    private final ConnectionPool connectionPool;
    private final Logger logger= LogManager.getLogger(UserDao.class);



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
        return getDevicesBySql(filter().build());
    }

    public Optional<Device> getDeviceBySerial(String serial){
        List<Device> resultList=getDevicesByFilter(filter().withSerial(serial));

        if(resultList.size()>0){
            return Optional.of(resultList.get(0));
        }
        return Optional.empty();

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

    public void updateDevice(Device device) throws DAOException{
        StringBuilder sqlStringBuder=new StringBuilder("UPDATE DEVICES SET mount_place='")
                .append(device.getMountPlace()).append("',")
                .append("last_verification='").append(device.getDateOfLastVerification()).append("',")
                .append("next_verification='").append(device.getDateOfNextVerification()).append("',")
                .append("passport_url='").append(device.getPassportImgUrl()).append("'")
                .append(" WHERE serial='").append(device.getSerialNumber()).append("'");

        try(Connection con=connectionPool.takeConnection();
               Statement st=con.createStatement() ){
                st.executeUpdate(sqlStringBuder.toString());

        }
        catch (InterruptedException|SQLException ex){
            throw new DAOException(ex);
        }
    }

    public boolean deleteDevice(String serial) throws DAOException {
        try(Connection con=connectionPool.takeConnection();
            Statement st=con.createStatement();
        ){
            st.executeUpdate("DELETE FROM DEVICES WHERE serial="+serial);
            return true;
        }
        catch(InterruptedException|SQLException ex){
            throw new DAOException(ex);
        }
    }





    public DeviceFilter filter(){
        return new DeviceFilter();
    }

    public class DeviceFilter{
        private DeviceFilter(){}
        private  StringBuilder resultSqlBuilder= new StringBuilder("SELECT name,serial,mount_place,last_verification,next_verification,passport_url FROM DEVICES");

        ArrayList<String> filterConditions=new ArrayList();

        ArrayList<String> postConditions=new ArrayList<>();
        private String limitCondition="";

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

        public DeviceFilter withColumnBetween(String columnName, String startValue,String endValue){
            addCondition("id BETWEEN "+startValue+" AND "+endValue);
            return this;
        }

        public DeviceFilter withColumnGreaterThen(String columnName, String minValue){
            addCondition(columnName+">"+minValue);
            return this;
        }

        public DeviceFilter withColumnLessThen(String columnName, String maxValue){
            addCondition(columnName+"<"+maxValue);
            return this;
        }

        public DeviceFilter withLimit(int limit){
            postConditions.add(" LIMIT "+limit);
            return this;
        }

        public DeviceFilter orderBy(String columnName,String orderDirection){
            postConditions.add(" ORDER BY "+columnName+" "+Optional.ofNullable(orderDirection).filter(val->val.matches("^(ASC|DESC)$")).orElse(""));
            return this;
        }

        private String build(){
            if(filterConditions.size()>0)
              resultSqlBuilder.append(" WHERE ").append(filterConditions.get(0));

              for(String condition:filterConditions){
                  resultSqlBuilder.append(" AND ").append(condition);
              }

            for(String condition:postConditions){
                resultSqlBuilder.append(condition);
            }


                return resultSqlBuilder.toString()+limitCondition;
            }

    }
}
