package com.epam.javase08.t02.dao;

import com.epam.javase08.t02.model.Book;
import com.epam.javase08.t02.pool.ConnectionPool;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


/**

 */
public class BookDaoSql extends BookDao {

    private static String BOOKS_TABLE_NAME ="BOOKS";
    protected final ConnectionPool connectionPool;


    private final static String GET_ALL_BOOKS_SQL="SELECT id,name,author,publicationYear,annotation FROM "+ BOOKS_TABLE_NAME;
    private final static String GET_ALL_BOOKS_BY_ID_SQL=GET_ALL_BOOKS_SQL+" WHERE id=";
    private final static String GET_BOOKS_BY_AUTHOR_SQL=GET_ALL_BOOKS_SQL+" WHERE author=";
    private final static String GET_BOOKS_BY_NAME_SQL =GET_ALL_BOOKS_SQL+" WHERE name=";
    private final static String GET_BOOKS_BY_YEAR =GET_ALL_BOOKS_SQL+" WHERE publicationYear=";

    public BookDaoSql(ConnectionPool connectionPool){
        this.connectionPool=connectionPool;
    }

    public Optional<Book> getBookById(int id){
        List<Book> resultList=getBooksBySql(GET_ALL_BOOKS_BY_ID_SQL+"id");
        return  Optional.of(resultList.size()>0?resultList.get(0):null);
    }

    public List<Book> getAllBooks(){
        return getBooksBySql(GET_ALL_BOOKS_SQL);
    }

    private List<Book> getBooksBySql(String sql){
        List<Book> resultList=new ArrayList<>();
        try(Connection con=connectionPool.takeConnection();
            Statement st=con.createStatement();
            ResultSet rs=st.executeQuery(sql);){
            while(rs.next()){
                try {
                    String bookName = rs.getString("name");
                    String bookAuthor = rs.getString("author");
                    int publicationYear = rs.getInt("publicationYear");
                    String annotation = rs.getString("annotation");
                    resultList.add(new Book(bookName, bookAuthor, publicationYear, annotation));
                }
                catch(SQLException ex){
                    logger.warn("Error load Book by sql:"+sql+" "+ex);
                }
            }
        }
        catch(Exception ex){
            logger.error(ex);
        }
        return resultList;
    }


    public void addBooks(List<Book> books){
        try(Connection con =connectionPool.takeConnection();
            PreparedStatement preparedStatement=con.prepareStatement("INSERT INTO "+BOOKS_TABLE_NAME+"(name,author,publicationYear,annotation) VALUES (?,?,?,?)");
        ){
         for(Book book:books){
             try{
                 preparedStatement.setString(1,book.getBookName());
                 preparedStatement.setString(2,book.getAuthorName());
                 preparedStatement.setInt(3,book.getPublicationYear());
                 preparedStatement.setString(4,book.getAnnotation());
                 preparedStatement.execute();
             }
             catch(SQLException ex){
                 logger.warn(ex);
             }
         }
        }
        catch(SQLException|InterruptedException ex){
            logger.warn(ex);
        }
    }

    public void addBookToBase(Book book){
        ArrayList<Book> tmp=new ArrayList<>();
        tmp.add(book);
        addBooks(tmp);
    }

    public int removeBook(Book bookForRemoving){
        try(Connection con=connectionPool.takeConnection();
        Statement st=con.createStatement();
        ){
            return st.executeUpdate("DELETE FROM "+BOOKS_TABLE_NAME+" WHERE " +
                    "name="+bookForRemoving.getBookName()+
                    " author="+bookForRemoving.getAuthorName()+
                    " publicationYear="+bookForRemoving.getPublicationYear());
        }
        catch(SQLException|InterruptedException ex){
            logger.warn(ex);
        }
        return 0;
    }

    @Override
    public List<Book> getBooksByAuthor(String authorName) {
        return getBooksBySql(GET_BOOKS_BY_AUTHOR_SQL+"'"+authorName+"'");
    }

    @Override
    public List<Book> getBooksByPublishingYear(int publishingYear) {
        return getBooksBySql(GET_BOOKS_BY_YEAR +"'"+publishingYear+"'");
    }

    @Override
    public List<Book> getBooksByName(String bookName) {
        return getBooksBySql(GET_BOOKS_BY_NAME_SQL +"'"+bookName+"'");
    }


}
