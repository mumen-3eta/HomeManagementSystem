package graduationproject.homemanagementsystem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import graduationproject.homemanagementsystem.dataClasses.userClass;

public class LoginActivity extends AppCompatActivity {

    private LinearLayout drawer_login;
    private DrawerLayout drawer_layout;
    private EditText userEmailTextField;
    private EditText userPasswordTextField;

    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        drawer_layout = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer_login = (LinearLayout) findViewById(R.id.drawer_login);
        userEmailTextField = (EditText) findViewById(R.id.userEmailTextField);
        userPasswordTextField = (EditText) findViewById(R.id.userPasswordTextField);

        Drawable drawable =  getResources().getDrawable(R.drawable.drawer_shoice);

        drawer_login.setBackground(drawable);
    }

    public void ClickMenu(View view){
        openDrawer(drawer_layout);
    }

    public void goHome(View view){ redirectActivity(this, MainActivity.class);}

    public void goLogin(View view){ closeDrawer(drawer_layout);}

    public void goSignUp(View view){ redirectActivity(this, SiginupActivity.class);}

    public void goContactUs(View view){ redirectActivity(this, ContactUsActivity.class);}

    public void goAboutUs(View view){ redirectActivity(this, AboutUsActivity.class);}

    public void goResetPassword(View view){ redirectActivity(this, ResetPasswordActivity.class);}

    public void loginAction(View view){
        String userEmail = userEmailTextField.getText().toString().trim();
        String userPassword = userPasswordTextField.getText().toString();
        Toast toast = new Toast(this);
        boolean validUserEmail = checkUserEmail(userEmail);
        String correspondingPassword = getCorrespondingPassword(userEmail);

        if (validUserEmail && correspondingPassword.equals(userPassword)){
            toast.makeText(this, "login successful", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, homePage.class);
            intent.putExtra("email", userEmail);
            this.startActivity(intent);
        }else if (!validUserEmail){
            toast.makeText(this, "email is not valid", Toast.LENGTH_SHORT).show();
        }else if (validUserEmail && !correspondingPassword.equals("no")){
            toast.makeText(this, "password is not correct", Toast.LENGTH_SHORT).show();
        }else if (correspondingPassword.equals("no")){
            toast.makeText(this, "email or password is not valid", Toast.LENGTH_SHORT).show();
        }
    }

    public boolean checkUserEmail(String userEmail){
        if (userEmail.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")){
            return true;
        }else {
            return false;
        }
    }

    private String  getCorrespondingPassword(String userEmail) {
        for (userClass user: MainActivity.users){
            if (user.getUserEmail().equals(userEmail)){
                return user.getUserPassword();
            }
        }
        return "no";
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
}