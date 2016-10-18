package com.epam.javase08.t01;

import org.junit.Test;

import java.io.IOException;
import java.sql.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertTrue;

/**
 * Создайте таблицу в БД и с помощью JDBC выполните следующие действи
 *  извлеките информацию из таблицы с помощью подготовленного запроса;+
 *  обновите несколько записей в таблице;+
 *  выберите конкретную запись в таблице;+
 *  вставьте новую запись в таблицу;+
 *  удалите таблицу.
 */

public class JDBCTesterTest {
    private final static String DATA_BASE_PROP_FILE="src/test/resources/db/prop.txt";
    private final static String DATA_BASE_INIT_FILE="src/test/resources/db/init.sql";
    private final Connection dataBaseConnection=new JDBCTester().initBase(DATA_BASE_PROP_FILE).executeSqlFromFile(DATA_BASE_INIT_FILE).getConnection();

    public JDBCTesterTest() throws IOException, SQLException {
    }


    @Test
    public void preparedStatementSelectTest() throws IOException, SQLException {
        PreparedStatement prepSelectStatement=dataBaseConnection.prepareStatement("SELECT book_name, book_author FROM Books WHERE  book_name=?");
        prepSelectStatement.setString(1,"Effective Java");
        ResultSet result=prepSelectStatement.executeQuery();
        result.next();

        assertThat(result.getString("book_author"),is("Joshua Bloch"));
    }

    @Test
    public void updateRecordsTest() throws SQLException {
        try(Statement st=dataBaseConnection.createStatement()){
            st.executeUpdate("UPDATE Books SET comment=\'SomeComment\' WHERE book_author=\'Joshua Bloch\'");
            ResultSet rs=st.executeQuery("SELECT comment FROM Books WHERE book_author=\'Joshua Bloch'");
            while(rs.next())
                assertThat(rs.getString("comment"),is("SomeComment"));
            rs.close();
        }
   }

   @Test
    public void selectRecordTest() throws SQLException {
       try(Statement st=dataBaseConnection.createStatement()){

           ResultSet rs=st.executeQuery("SELECT book_author,book_name,comment FROM Books WHERE book_author=\'Martin Fowler\'");

           rs.next();
           assertThat(rs.getString("book_author"),is("Martin Fowler") );

           rs.close();
       }

   }

   @Test
   public void insertTest() throws SQLException {

       try(Statement st=dataBaseConnection.createStatement()){
           st.execute("INSERT INTO Books (book_author,book_name) VALUES (\'Maurice Naftalin\',\'Java Generics and Collections\')");
       }
   }

   @Test public void removeTableTest() throws SQLException {
       try(Statement st=dataBaseConnection.createStatement()){

           st.execute("DROP TABLE Books");

       }

   }


}