package model;

/**
 * Created by pkatya on 11/6/16.
 */
public class User {
    private String userName;
    private String fullName;
    private String emailId;
    private String password;
    private String phoneNumber;
    private String block;
    private String flatNumber;

    public User(String userName, String fullName, String emailId, String password, String phoneNumber, String block, String flatNumber) {
        this.userName = userName;
        this.fullName = fullName;
        this.emailId = emailId;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.block = block;
        this.flatNumber = flatNumber;
    }

    public User() {

    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getBlock() {
        return block;
    }

    public void setBlock(String block) {
        this.block = block;
    }

    public String getFlatNumber() {
        return flatNumber;
    }

    public void setFlatNumber(String flatNumber) {
        this.flatNumber = flatNumber;
    }
}
