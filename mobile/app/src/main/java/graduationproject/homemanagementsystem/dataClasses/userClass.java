package graduationproject.homemanagementsystem.dataClasses;

public class userClass {
    private String userName;
    private String userEmail;
    private String userPassword;

    public userClass() {
    }

    public userClass(String name, String email, String password) {
        this.userName = name;
        this.userEmail = email;
        this.userPassword = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }
}
