package graduationproject.homemanagementsystem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Toast;

import java.net.CookieHandler;
import java.net.CookieManager;
import java.util.ArrayList;

import graduationproject.homemanagementsystem.dataClasses.JsonApi;
import graduationproject.homemanagementsystem.dataClasses.LoginBody;
import graduationproject.homemanagementsystem.dataClasses.deviceClass;
import graduationproject.homemanagementsystem.dataClasses.userClass;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout drawer_layout;
    private LinearLayout drawer_about;
    private LinearLayout drawer_home;
    private LinearLayout drawer_sign;
    private Drawable drawable;
    private EditText userEmailTextField;
    private EditText userPasswordTextField;
    private ScrollView main_page_scroll;
    public static SharedPreferences sp;


    public static ArrayList<userClass> users = new ArrayList<>();

    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawer_layout = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer_about = (LinearLayout) findViewById(R.id.drawer_about);
        drawer_home = (LinearLayout) findViewById(R.id.drawer_home);
        drawer_sign = (LinearLayout) findViewById(R.id.drawer_sign);
        userEmailTextField = (EditText) findViewById(R.id.userEmailTextField);
        userPasswordTextField = (EditText) findViewById(R.id.userPasswordTextField);
        main_page_scroll = (ScrollView) findViewById(R.id.main_page_scroll);

        sp = getSharedPreferences("login",MODE_PRIVATE);

        if (sp.getBoolean("logged", false)){
            redirectActivity(this, HomePageActivity.class, sp.getString("userEmail",""));
        }

        drawable =  getResources().getDrawable(R.drawable.drawer_shoice);

        drawer_home.setBackground(drawable);

        userClass newUser = new userClass("Mumen", "mumen.3eta@gmail.com", "Note12345");
        deviceClass deviceClass = new deviceClass("My TV", "Living Room", "TV");
        deviceClass deviceClass2 = new deviceClass("My light", "Living Room", "Light");
        newUser.getDevices().add(deviceClass);
        newUser.getDevices().add(deviceClass2);
        newUser.getDevices().add(deviceClass);
        newUser.getDevices().add(deviceClass2);
        newUser.getDevices().add(deviceClass);
        newUser.getDevices().add(deviceClass2);
        newUser.getDevices().add(deviceClass);
        newUser.getDevices().add(deviceClass2);
        newUser.getDevices().add(deviceClass);
        newUser.getDevices().add(deviceClass2);
        newUser.getDevices().add(deviceClass);
        newUser.getDevices().add(deviceClass2);
        users.add(newUser);

        CookieManager cookieManager = new CookieManager();
        CookieHandler.setDefault(cookieManager);
    }

    public void ClickMenu(View view){
        openDrawer(drawer_layout);
    }

    public void goHome(View view){ closeDrawer(drawer_layout);}

    public void goDown(View view){main_page_scroll.smoothScrollTo(0, main_page_scroll.getBottom()); }

    public void goSignUp(View view){
        drawer_home.setBackground(null);
        drawer_sign.setBackground(drawable);
        redirectActivity(this, SignUpActivity.class);}

    public void goAboutUs(View view){
        drawer_home.setBackground(null);
        drawer_about.setBackground(drawable);
        redirectActivity(this, AboutUsActivity.class);}

    public void goResetPassword(View view){ redirectActivity(this, ResetPasswordActivity.class);}

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

    public final static Bitmap stringToBitmap(String in){
        byte[] bytes = Base64.decode(in, Base64.DEFAULT);
        return BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
    }

    public void logApi(View view){

    }

    public void loginAction(View view){
        String userEmail = userEmailTextField.getText().toString().trim();
        String userPassword = userPasswordTextField.getText().toString();
        Toast toast = new Toast(this);
        boolean validUserEmail = checkUserEmail(userEmail);
        String correspondingPassword = getCorrespondingPassword(userEmail);

        if (validUserEmail && correspondingPassword.equals(userPassword)){
            toast.makeText(this, "login successful", Toast.LENGTH_SHORT).show();
            redirectActivity(this, HomePageActivity.class, userEmail);
            sp.edit().putBoolean("logged",true).apply();
            sp.edit().putString("userEmail",userEmail).apply();
        }else if (!validUserEmail){
            toast.makeText(this, "email is not valid", Toast.LENGTH_SHORT).show();
        }else if (validUserEmail && !correspondingPassword.equals("no")){
            toast.makeText(this, "password is not correct", Toast.LENGTH_SHORT).show();
        }else if (correspondingPassword.equals("no")){
            toast.makeText(this, "email or password is not valid", Toast.LENGTH_SHORT).show();
        }

        Login();
    }

    private void Login() {
        LoginBody body = new LoginBody();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://hmsy-app.herokuapp.com/api/v1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        JsonApi jsonApi = retrofit.create(JsonApi.class);
        Call call = jsonApi.Login(body);
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

    @Override
    public void onBackPressed() {
        //do nothing
    }
}