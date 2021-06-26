package graduationproject.homemanagementsystem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import graduationproject.homemanagementsystem.dataClasses.JsonApi;
import graduationproject.homemanagementsystem.dataClasses.authMe;
import graduationproject.homemanagementsystem.dataClasses.data;
import graduationproject.homemanagementsystem.dataClasses.deviceClass;
import graduationproject.homemanagementsystem.dataClasses.locationData;
import graduationproject.homemanagementsystem.dataClasses.typeData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class Splash extends AppCompatActivity {

    private static final int SPLASH_DISPLAY_LENGTH = 2000;
    public static SharedPreferences sp;
    public static data currentUser = new data();
    public static String[] typesList = new String[0];
    public static String[] locationList = new String[0];
    public static String[] processorList = new String[0];
    public static List<typeData> types;
    public static List<locationData> locations;

    @Override
    public void onCreate(Bundle icicle) {
        sp = getSharedPreferences("login", MODE_PRIVATE);
        if (Splash.sp.getString("Theme", "") != null || !Splash.sp.getString("Theme", "").equals("")){
            if (Splash.sp.getString("Theme", "").equals("dark")){
                setTheme(R.style.DarkTheme);
            }else {
                setTheme(R.style.AppTheme);
            }
        }else {
            setTheme(R.style.AppTheme);
        }
        super.onCreate(icicle);
        setContentView(R.layout.activity_splash);




        if (sp.getString("Cookie","") == null || sp.getString("Cookie","").equals("")){
            /* New Handler to start the Menu-Activity
             * and close this Splash-Screen after some seconds.*/
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    /* Create an Intent that will start the Menu-Activity. */

                    Intent mainIntent = new Intent(Splash.this, MainActivity.class);
                    Splash.this.startActivity(mainIntent);
                }
            }, SPLASH_DISPLAY_LENGTH);
        }else {
            checkLoged();
        }
    }

    public void checkLoged() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://home-msy.herokuapp.com/api/v1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        JsonApi jsonApi = retrofit.create(JsonApi.class);
        Call<authMe> call = jsonApi.authenticate(sp.getString("Cookie", ""));


        call.enqueue(new Callback<authMe>() {
            @Override
            public void onResponse(Call<authMe> call, Response<authMe> response) {
                if (response.isSuccessful()){
                    if (response.body().getUser().isIs_admin()){
                        sp.edit().putBoolean("Admin", true).apply();
                        redirectActivity(Splash.this, HomePageAdminActivity.class);
                    }else {
                        sp.edit().putBoolean("Admin", false).apply();
                        redirectActivity(Splash.this, HomePageActivity.class);
                    }
                }else {
                    /* New Handler to start the Menu-Activity
                     * and close this Splash-Screen after some seconds.*/
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            /* Create an Intent that will start the Menu-Activity. */

                            Intent mainIntent = new Intent(Splash.this, MainActivity.class);
                            Splash.this.startActivity(mainIntent);
                        }
                    }, SPLASH_DISPLAY_LENGTH);
                }
            }

            @Override
            public void onFailure(Call<authMe> call, Throwable t) {
                Toast.makeText(Splash.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                Intent mainIntent = new Intent(Splash.this, MainActivity.class);
                Splash.this.startActivity(mainIntent);
            }
        });

    }

    public void redirectActivity(Activity activity, Class aClass) {
        Intent intent = new Intent(activity,aClass);
        activity.startActivity(intent);
    }

}