package com.epam.javase08.t02.pool;





import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * Created by Freemind on 2016-10-16.
 */
public class ConnectionPool implements AutoCloseable {

    private final static Logger logger= LogManager.getLogger(ConnectionPool.class);

    private ConnectionPool(){};
    private  ArrayBlockingQueue<PooledConnection> connectionsQueue;

    public static ConnectionPool getInstance(Properties setup) throws SQLException {
        int poolSize=Integer.parseInt(setup.getProperty("poolSize","5"));
        ConnectionPool resultPool=new ConnectionPool();
        resultPool.connectionsQueue =new ArrayBlockingQueue<PooledConnection>(poolSize);


        for(int i=0;i<poolSize;i++){
            resultPool.connectionsQueue.add(new PooledConnection(DriverManager.getConnection(setup.getProperty("url"),setup),resultPool));
        }
        return resultPool;
    }

    public Connection takeConnection() throws InterruptedException {
        return connectionsQueue.take();
    }

    void freeConnection(PooledConnection freeConnection){
        try {
            connectionsQueue.put(freeConnection);
        } catch (InterruptedException e) {
           logger.warn(e);
        }
    }

    public void close(){
        for(PooledConnection connection: connectionsQueue){
            try{
                connection.reallyCloseInternalConnection();
            }
            catch(Exception ex){
                logger.error("Error closing connection "+connection);
            }

        }
    }

    public static void main(String[] args) {
        logger.info("Some test message");
        logger.warn("Some test warn message");
        logger.error("Some test err message");
    }
}
