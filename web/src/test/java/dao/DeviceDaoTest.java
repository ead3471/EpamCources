package dao;

import model.Device;
import org.junit.Test;
import pool.ConnectionPool;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Properties;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;


public class DeviceDaoTest {

    private final String CONNECTION_PROP="src/test/resources/db/db.properties";
    private final String INIT_BASE_SCRIPT="src/test/resources/db/initBase.sql";

    private DeviceDao getDeviceDao() throws IOException, SQLException, InterruptedException {


        Properties setup=new Properties();
        setup.load(new FileInputStream(CONNECTION_PROP));
        ConnectionPool connectionPool=ConnectionPool.getInstance(setup);
        connectionPool.executeSQLFromFile(INIT_BASE_SCRIPT,connectionPool.takeConnection());
        return new DeviceDao(connectionPool);


    }

    @Test
    public void getAllTest() throws IOException, SQLException, InterruptedException {

        assertThat(getDeviceDao().getAll().size(),is(5));

    }

    @Test
    public void getByFilterTest() throws InterruptedException, SQLException, IOException {
        DeviceDao dao=getDeviceDao();

        String loadedDeviceName="DEVICE_TYPE_3";
        String loadedDevicePlace="PLACE_2";
        List<Device> loadedDevices=dao.getDevicesByFilter(dao.filter().withName(loadedDeviceName).withMountPlace(loadedDevicePlace));

        for (Device device:loadedDevices ) {
            assertThat(device.getName(),is(loadedDeviceName));
            assertThat(device.getMountPlace(),is(loadedDevicePlace));
        }


    }

    @Test
    public void getByTimesAfterTest() throws InterruptedException, SQLException, IOException {
        LocalDate verificationDate=LocalDate.of(2016,6,1);

        DeviceDao dao= getDeviceDao();
        List<Device> loadedDevices=dao.getDevicesByFilter(dao.filter().withNextVerificationDateAfter(verificationDate));

        for (Device device:loadedDevices ) {
            assertTrue(device.getDateOfNextVerification().compareTo(verificationDate)>0);
        }

    }


    @Test
    public void getByTimesBeforeTest() throws InterruptedException, SQLException, IOException {
        LocalDate verificationDate=LocalDate.of(2016,6,1);

        DeviceDao dao= getDeviceDao();
        List<Device> loadedDevices=dao.getDevicesByFilter(dao.filter().withNextVerificationDateBefore(verificationDate));

        for (Device device:loadedDevices ) {
            assertTrue(device.getDateOfNextVerification().compareTo(verificationDate)<0);
        }

    }

    @Test
    public void insertTest() throws InterruptedException, SQLException, IOException {
        DeviceDao dao=getDeviceDao();
        Device deviceForLoad=new Device("test_device","123",LocalDate.now().plusYears(4),LocalDate.now().minusYears(1),"testLocation");
        dao.insertDevice(deviceForLoad);
        List<Device> loadedDevices=dao.getDevicesByFilter(dao.filter().withName("test_device"));
        assertThat(loadedDevices.size(),is(1));
        assertEquals(loadedDevices.get(0),deviceForLoad);

    }


    @Test
    public void updateTest() throws InterruptedException, SQLException, IOException {
        DeviceDao dao=getDeviceDao();
        Device deviceForLoad=new Device("test_device","123",LocalDate.now().plusYears(4),LocalDate.now().minusYears(1),"testLocation");
        dao.insertDevice(deviceForLoad);
        deviceForLoad.setMountPlace("new Location");

        dao.updateDevice(deviceForLoad);

        Device deviceLoadedFromBase=dao.getDevicesByFilter(dao.filter().withSerial(deviceForLoad.getSerialNumber())).get(0);


        assertThat(deviceLoadedFromBase.getMountPlace(),is("new Location"));

    }



}