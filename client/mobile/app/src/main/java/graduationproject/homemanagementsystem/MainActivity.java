package graduationproject.homemanagementsystem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.util.Base64;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Toast;

import java.io.IOException;

import graduationproject.homemanagementsystem.dataClasses.JsonApi;
import graduationproject.homemanagementsystem.dataClasses.authMe;
import graduationproject.homemanagementsystem.dataClasses.loginBody;
import graduationproject.homemanagementsystem.dataClasses.loginResponse;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
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
    private Toast toast;
    private static final int SPLASH_DISPLAY_LENGTH = 2000;

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://home-msy.herokuapp.com/api/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    JsonApi jsonApi = retrofit.create(JsonApi.class);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (Splash.sp.getString("Theme", "") != null || !Splash.sp.getString("Theme", "").equals("")){
            if (Splash.sp.getString("Theme", "").equals("dark")){
                setTheme(R.style.DarkTheme);
            }else {
                setTheme(R.style.AppTheme);
            }
        }else {
            setTheme(R.style.AppTheme);
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawer_layout = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer_about = (LinearLayout) findViewById(R.id.drawer_about);
        drawer_home = (LinearLayout) findViewById(R.id.drawer_home);
        drawer_sign = (LinearLayout) findViewById(R.id.drawer_sign);
        userEmailTextField = (EditText) findViewById(R.id.userEmailTextField);
        userPasswordTextField = (EditText) findViewById(R.id.userPasswordTextField);
        main_page_scroll = (ScrollView) findViewById(R.id.main_page_scroll);
        toast = new Toast(this);

        drawable = ContextCompat.getDrawable(getApplicationContext(), R.drawable.drawer_shoice);

        drawer_home.setBackground(drawable);
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

    public final static Bitmap stringToBitmap(String in){
        byte[] bytes = Base64.decode(in, Base64.DEFAULT);
        return BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
    }

    public void loginAction(View view){
        String userEmail = userEmailTextField.getText().toString().trim();
        String userPassword = userPasswordTextField.getText().toString();
        boolean validEmail = checkUserEmail(userEmail);
        loginBody body = new loginBody();
        if (validEmail){
            body.setEmail(userEmail);
        }else if (userEmail.equals("") || userPassword.equals("")){
            toast.makeText(MainActivity.this, "Email or userName and Password are required", Toast.LENGTH_SHORT).show();
            return;
        }else {
            body.setUserName(userEmail);
        }

        body.setPassword(userPassword);
        Call<loginResponse> call = jsonApi.Login(body);

        call.enqueue(new Callback<loginResponse>() {
            @Override
            public void onResponse(Call<loginResponse> call, Response<loginResponse> response) {
                if (!response.isSuccessful()){
                    ResponseBody loginResponse = response.errorBody();
                    try {
                        String message = loginResponse.string();
                        toast.makeText(MainActivity.this, message.substring(10, message.length()-2), Toast.LENGTH_SHORT).show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    return;
                }

                String token = response.headers().get("Set-Cookie");
                loginResponse loginResponse = response.body();
                if (loginResponse.getTitle().equals("User login")){
                    toast.makeText(MainActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                    Splash.sp.edit().putString("Cookie",token).apply();

                    Call<authMe> call2 = jsonApi.authenticate(Splash.sp.getString("Cookie", ""));

                    call2.enqueue(new Callback<authMe>() {
                        @Override
                        public void onResponse(Call<authMe> call, Response<authMe> response) {
                            if (response.isSuccessful()){
                                if (response.body().getUser().isIs_admin()){
                                    Toast.makeText(MainActivity.this, "Admin", Toast.LENGTH_SHORT).show();
                                    Splash.sp.edit().putBoolean("Admin", true).apply();
                                    redirectActivity(MainActivity.this, HomePageAdminActivity.class);
                                }else {
                                    Toast.makeText(getApplicationContext(), "User", Toast.LENGTH_SHORT).show();
                                    Splash.sp.edit().putBoolean("Admin", false).apply();
                                    redirectActivity(MainActivity.this, HomePageActivity.class);
                                }
                            }else {
                                /* New Handler to start the Menu-Activity
                                 * and close this Splash-Screen after some seconds.*/
                                new Handler().postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        /* Create an Intent that will start the Menu-Activity. */

                                        Intent mainIntent = new Intent(MainActivity.this, MainActivity.class);
                                        MainActivity.this.startActivity(mainIntent);
                                    }
                                }, SPLASH_DISPLAY_LENGTH);
                            }
                        }

                        @Override
                        public void onFailure(Call<authMe> call, Throwable t) {
                            Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                            Intent mainIntent = new Intent(MainActivity.this, MainActivity.class);
                            MainActivity.this.startActivity(mainIntent);
                        }
                    });
                }
            }

            @Override
            public void onFailure(Call<loginResponse> call, Throwable t) {
                toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public boolean checkUserEmail(String userEmail){
        if (userEmail.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")){
            return true;
        }else {
            return false;
        }
    }

    @Override
    public void onBackPressed() {
        //do nothing
    }
}