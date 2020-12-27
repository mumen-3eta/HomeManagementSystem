package graduationproject.homemanagementsystem.dataClasses;

import android.graphics.Bitmap;

import java.util.ArrayList;

public class userClass {
    private String userName;
    private String userEmail;
    private String userPassword;
    private Bitmap userPhoto;
    private ArrayList devices;

    public userClass() {
        this.devices = new ArrayList<deviceClass>();
    }

    public userClass(String name, String email, String password) {
        this.userName = name;
        this.userEmail = email;
        this.userPassword = password;
        this.devices = new ArrayList<deviceClass>();
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

    public Bitmap getUserPhoto() {
        return userPhoto;
    }

    public void setUserPhoto(Bitmap userPhoto) {
        this.userPhoto = userPhoto;
    }

    public ArrayList getDevices() {
        return devices;
    }

    public void setDevices(ArrayList devices) {
        this.devices = devices;
    }

    @Override
    public String toString(){
        String user = "Name: " + this.userName + "  Email: " + this.userEmail;
        return user;
    }
}
