package graduationproject.homemanagementsystem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

public class SiginupActivity extends AppCompatActivity {

    private LinearLayout drawer_sign;
    private DrawerLayout drawer_layout;
    private EditText userNameTextField;

    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_siginup);

        drawer_layout = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer_sign = (LinearLayout) findViewById(R.id.drawer_sign);
        userNameTextField = (EditText) findViewById(R.id.userNameTextField);

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
        Toast toast = new Toast(this);
        if (userName.matches("^[a-zA-Z]{4,}(?: [a-zA-Z]+){0,5}$")){
             toast.makeText(this, "name is valid", Toast.LENGTH_SHORT).show();
        }else {
             toast.makeText(this, "name is not valid", Toast.LENGTH_SHORT).show();
        }

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