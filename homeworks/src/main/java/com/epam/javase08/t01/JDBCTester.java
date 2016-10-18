package com.epam.javase08.t01;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.Properties;
import java.util.Scanner;


/**
 * Создайте таблицу в БД и с помощью JDBC выполните следующие действи
 *  извлеките информацию из таблицы с помощью подготовленного запроса;
 *  обновите несколько записей в таблице;
 *  выберите конкретную запись в таблице;
 *  вставьте новую запись в таблицу;
 *  удалите таблицу.
 */
public class JDBCTester {

    private Connection dataBaseConnection;




    public  JDBCTester initBase(String initFileName) throws SQLException, IOException {
        Properties properties=new Properties();
        properties.load(new FileReader(initFileName));

        String url=properties.getProperty("url");


        dataBaseConnection= DriverManager.getConnection(url);

        return this;
    }

    public JDBCTester executeSqlFromFile(String createTableScript) throws IOException, SQLException {
       executeSQLFromFile(createTableScript,dataBaseConnection);
        return this;
    }
    public static void executeSQLFromFile(String fileWithSql, Connection connection) throws FileNotFoundException, SQLException {
        try(Scanner fileScanner=new Scanner(new File(fileWithSql));
            Statement st=connection.createStatement()){
            fileScanner.useDelimiter(";");
            while (fileScanner.hasNext())
            {
                String sqlString=fileScanner.next().replace("\n","");
                if(!sqlString.startsWith("//")) {
                    st.addBatch(sqlString);
                    st.executeBatch();
                }
            }
        }
    }





    public Connection getConnection(){
        return dataBaseConnection;
    }





}
