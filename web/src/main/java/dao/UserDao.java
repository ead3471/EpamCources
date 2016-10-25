package dao;

import model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pool.ConnectionPool;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

/**
 * Created by Freemind on 2016-10-21.
 */
public class UserDao {
    private final ConnectionPool connectionPool;
    private final Logger logger= LogManager.getLogger(UserDao.class);

    private final String getUserByNameSql="SELECT name,password,role from Users WHERE name='";



    public UserDao(ConnectionPool connectionPool){
        this.connectionPool=connectionPool;
    }

    public Optional<User> getUserByName(String userName,String validationString) throws InterruptedException, SQLException {
        logger.debug("User validation request user="+userName+" validationString="+validationString);
       try(Connection con=connectionPool.takeConnection();
           Statement st= con.createStatement();
            ResultSet resultSet=st.executeQuery(getUserByNameSql+userName+"'")){
           if(resultSet.next()&& resultSet.getString("password").equals(validationString)){
                logger.debug("loaded pass:"+resultSet.getString("password"));
               return  Optional.of(new User(userName,validationString,resultSet.getString("role")));
           }

       }
       return Optional.empty();
    }






}
