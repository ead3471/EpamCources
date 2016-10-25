package model;

/**
 * Created by Freemind on 2016-10-21.
 */
public class User {

    private final String userName;
    private final String passwordHash;
    private final String userRole;



    public User(String userName, String validationString,String userRole) {
        this.userName=userName;
        this.userRole=userRole;
        this.passwordHash=validationString;


    }



    public String getUserName() {
        return userName;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public String getRole() {
        return userRole;
    }
}
