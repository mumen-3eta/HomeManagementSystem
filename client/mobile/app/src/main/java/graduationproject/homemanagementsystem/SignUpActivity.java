package graduationproject.homemanagementsystem;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

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

public class SignUpActivity extends AppCompatActivity {

    private LinearLayout drawer_about;
    private LinearLayout drawer_home;
    private LinearLayout drawer_sign;
    private Drawable drawable;
    private DrawerLayout drawer_layout;
    private EditText userNameTextField;
    private EditText userEmailTextField;
    private EditText userPasswordTextField;
    private EditText confirmPasswordTextField;
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
        setContentView(R.layout.activity_siginup);

        drawer_layout = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer_about = (LinearLayout) findViewById(R.id.drawer_about);
        drawer_home = (LinearLayout) findViewById(R.id.drawer_home);
        drawer_sign = (LinearLayout) findViewById(R.id.drawer_sign);
        userNameTextField = (EditText) findViewById(R.id.userNameTextField);
        userEmailTextField = (EditText) findViewById(R.id.userEmailTextField);
        userPasswordTextField = (EditText) findViewById(R.id.userPasswordTextField);
        confirmPasswordTextField = (EditText) findViewById(R.id.confirmPasswordTextField);

        drawable =  ContextCompat.getDrawable(getApplicationContext(), R.drawable.drawer_shoice);

        drawer_sign.setBackground(drawable);
    }

    public void ClickMenu(View view){
        openDrawer(drawer_layout);
    }

    public void goHome(View view){
        drawer_sign.setBackground(null);
        drawer_home.setBackground(drawable);
        redirectActivity(this, MainActivity.class);}

    public void goSignUp(View view){ closeDrawer(drawer_layout);}

    public void goAboutUs(View view){
        drawer_sign.setBackground(null);
        drawer_about.setBackground(drawable);
        redirectActivity(this, AboutUsActivity.class);}

    public void signUpAction(View view){
        String userName = userNameTextField.getText().toString().trim();
        String userEmail = userEmailTextField.getText().toString().trim();
        String userPassword = userPasswordTextField.getText().toString();
        String confirmPassword = confirmPasswordTextField.getText().toString();
        toast = new Toast(this);
        boolean validUserEmail = checkUserEmail(userEmail);
        String passwordToastMessage = checkUserPassword(userPassword, confirmPassword);

        if (validUserEmail && passwordToastMessage.equals("valid")){
            //preform sign up and login function
            loginBody body = new loginBody();
            body.setEmail(userEmail);
            body.setUserName(userName);
            body.setPassword(userPassword);
            Call<loginResponse> call = jsonApi.register(body);

            call.enqueue(new Callback<loginResponse>() {
                @Override
                public void onResponse(Call<loginResponse> call, Response<loginResponse> response) {
                    if (!response.isSuccessful()){
                        ResponseBody loginResponse = response.errorBody();
                        try {
                            String message = loginResponse.string();
                            toast.makeText(SignUpActivity.this, message.substring(10, message.length()-2), Toast.LENGTH_SHORT).show();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        return;
                    }
                    String token = response.headers().get("Set-Cookie");
                    loginResponse loginResponse = response.body();
                    if (loginResponse.getTitle().equals("User Registration")){
                        Splash.sp.edit().putString("Cookie",token).apply();
                        toast.makeText(SignUpActivity.this, "sign up successful", Toast.LENGTH_SHORT).show();

                        Call<authMe> call2 = jsonApi.authenticate(Splash.sp.getString("Cookie", ""));

                        call2.enqueue(new Callback<authMe>() {
                            @Override
                            public void onResponse(Call<authMe> call, Response<authMe> response) {
                                if (response.isSuccessful()){
                                    if (response.body().getUser().isIs_admin()){
                                        Toast.makeText(SignUpActivity.this, "Admin", Toast.LENGTH_SHORT).show();
                                        Splash.sp.edit().putBoolean("Admin", true).apply();
                                        redirectActivity(SignUpActivity.this, HomePageAdminActivity.class);
                                    }else {
                                        Toast.makeText(getApplicationContext(), "User", Toast.LENGTH_SHORT).show();
                                        Splash.sp.edit().putBoolean("Admin", false).apply();
                                        redirectActivity(SignUpActivity.this, HomePageActivity.class);
                                    }
                                }else {
                                    /* New Handler to start the Menu-Activity
                                     * and close this Splash-Screen after some seconds.*/
                                    new Handler().postDelayed(new Runnable() {
                                        @Override
                                        public void run() {
                                            /* Create an Intent that will start the Menu-Activity. */

                                            Intent mainIntent = new Intent(SignUpActivity.this, MainActivity.class);
                                            SignUpActivity.this.startActivity(mainIntent);
                                        }
                                    }, SPLASH_DISPLAY_LENGTH);
                                }
                            }

                            @Override
                            public void onFailure(Call<authMe> call, Throwable t) {
                                Toast.makeText(SignUpActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                                Intent mainIntent = new Intent(SignUpActivity.this, MainActivity.class);
                                SignUpActivity.this.startActivity(mainIntent);
                            }
                        });
                    }
                }

                @Override
                public void onFailure(Call<loginResponse> call, Throwable t) {
                    toast.makeText(SignUpActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });


        }else if (!validUserEmail){
            toast.makeText(this, "email is not valid", Toast.LENGTH_SHORT).show();
            return;
        }else if (!passwordToastMessage.equals("valid")){
            toast.makeText(this, passwordToastMessage, Toast.LENGTH_SHORT).show();
        } else {
            toast.makeText(this, "something went wrong, please try again", Toast.LENGTH_SHORT).show();
        }

    }



    public static boolean checkUserEmail(String userEmail){
        if (userEmail.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")){
            return true;
        }else {
            return false;
        }
    }

    public static String checkUserPassword(String userPassword, String confirmPassword){
        final int passwordMinLength = 8;
        if (userPassword.length() < passwordMinLength){
            return "password is short, it must have at least 8 characters";
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
        final int passwordMinLength = 8;
        if (userPassword.length() < passwordMinLength){
            return "password is short, it must have at least 8 characters";
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


    @Override
    public void onBackPressed() {
        //do nothing
    }
}