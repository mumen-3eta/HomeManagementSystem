package graduationproject.homemanagementsystem;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import graduationproject.homemanagementsystem.dataClasses.userClass;

public class SiginupActivity extends AppCompatActivity {

    private LinearLayout drawer_sign;
    private DrawerLayout drawer_layout;
    private EditText userNameTextField;
    private EditText userEmailTextField;
    private EditText userPasswordTextField;
    private EditText confirmPasswordTextField;

    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_siginup);

        drawer_layout = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer_sign = (LinearLayout) findViewById(R.id.drawer_sign);
        userNameTextField = (EditText) findViewById(R.id.userNameTextField);
        userEmailTextField = (EditText) findViewById(R.id.userEmailTextField);
        userPasswordTextField = (EditText) findViewById(R.id.userPasswordTextField);
        confirmPasswordTextField = (EditText) findViewById(R.id.confirmPasswordTextField);

        Drawable drawable =  getResources().getDrawable(R.drawable.drawer_shoice);

        drawer_sign.setBackground(drawable);
    }

    public void ClickMenu(View view){
        openDrawer(drawer_layout);
    }

    public void goHome(View view){ redirectActivity(this, MainActivity.class);}

    public void goLogin(View view){ redirectActivity(this, LoginActivity.class);}

    public void goSignUp(View view){ closeDrawer(drawer_layout);}

    public void goContactUs(View view){ redirectActivity(this, ContactUsActivity.class);}

    public void goAboutUs(View view){ redirectActivity(this, AboutUsActivity.class);}

    public void signUpAction(View view){
        String userName = userNameTextField.getText().toString().trim();
        String userEmail = userEmailTextField.getText().toString().trim();
        String userPassword = userPasswordTextField.getText().toString();
        String confirmPassword = confirmPasswordTextField.getText().toString();
        Toast toast = new Toast(this);
        boolean validUserName = checkUserName(userName);
        boolean validUserEmail = checkUserEmail(userEmail);
        boolean isUsedEmail = isUsedEmail(userEmail);
        String passwordToastMessage = checkUserPassword(userPassword, confirmPassword);

        if ( validUserName && validUserEmail && passwordToastMessage.equals("valid") && !isUsedEmail){
            //preform sign up and login function

            userClass newUser = new userClass(userName, userEmail,userPassword);
            MainActivity.users.add(newUser);
            toast.makeText(this, "sign up successful", Toast.LENGTH_SHORT).show();
            redirectActivity(this, HomePageActivity.class, userEmail);
        }else if (!validUserName){
            toast.makeText(this, "name is not valid", Toast.LENGTH_SHORT).show();
            return;
        }else if (!validUserEmail){
            toast.makeText(this, "email is not valid", Toast.LENGTH_SHORT).show();
            return;
        }else if (isUsedEmail){
            toast.makeText(this, "email is used", Toast.LENGTH_SHORT).show();
        }else if (!passwordToastMessage.equals("valid")){
            toast.makeText(this, passwordToastMessage, Toast.LENGTH_SHORT).show();
        } else {
            toast.makeText(this, "something went wrong, please try again", Toast.LENGTH_SHORT).show();
        }

    }


    public static boolean checkUserName(String userName){
        if (userName.matches("^[a-zA-Z]{4,}(?: [a-zA-Z]+){0,5}$")){
            return true;
        }else {
            return false;
        }
    }

    public static boolean checkUserEmail(String userEmail){
        if (userEmail.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")){
            return true;
        }else {
            return false;
        }
    }

    public static boolean isUsedEmail(String userEmail) {
        for (userClass user: MainActivity.users){
            if (user.getUserEmail().equals(userEmail)){
                return true;
            }
        }
        return false;
    }

    public static String checkUserPassword(String userPassword, String confirmPassword){
        final int passwordMinLength = 6;
        if (userPassword.length() < passwordMinLength){
            return "password is short, it must have at least 6 characters";
        }
        if (!userPassword.matches("(.*[0-9]+.*)")){
            return "password must contain at least one number";
        }
        if (!userPassword.matches(".*[A-Z]+.*")){
            return "password must contain at least one capital letter";
        }
        if (!userPassword.equals(confirmPassword)){
            return  "password does not match";
        }

        return  "valid";
    }

    public static String checkUserPassword(String userPassword){
        final int passwordMinLength = 6;
        if (userPassword.length() < passwordMinLength){
            return "password is short, it must have at least 6 characters";
        }
        if (!userPassword.matches("(.*[0-9]+.*)")){
            return "password must contain at least one number";
        }
        if (!userPassword.matches(".*[A-Z]+.*")){
            return "password must contain at least one capital letter";
        }


        return  "valid";
    }

    public static void openDrawer(DrawerLayout drawerLayout) {
        drawerLayout.openDrawer(GravityCompat.START);
    }

    public static void closeDrawer(DrawerLayout drawerLayout){
        drawerLayout.closeDrawer(GravityCompat.START);
    }

    public static void redirectActivity(Activity activity, Class aClass) {
        Intent intent = new Intent(activity,aClass);
        activity.startActivity(intent);
    }

    public static void redirectActivity(Activity activity, Class aClass, String userEmail) {
        Intent intent = new Intent(activity,aClass);
        intent.putExtra("email", userEmail);
        activity.startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        //do nothing
    }
}