package graduationproject.homemanagementsystem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.content.ContextCompat;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.io.IOException;
import java.util.List;

import graduationproject.homemanagementsystem.dataClasses.JsonApi;
import graduationproject.homemanagementsystem.dataClasses.deviceAdapter;
import graduationproject.homemanagementsystem.dataClasses.processorAdapter;
import graduationproject.homemanagementsystem.dataClasses.processorData;
import graduationproject.homemanagementsystem.dataClasses.processorResponse;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ViewProcessors extends AppCompatActivity {
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://home-msy.herokuapp.com/api/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    JsonApi jsonApi = retrofit.create(JsonApi.class);
    private final int logoutTimeOut = 499;
    private ListView processorListView;
    private processorAdapter adapter;
    private Drawable homepage_selected;
    private LinearLayout log_out;
    private ImageView pic;
    private ProgressBar processorPageProgressBar;
    private TextView numberOfProcessors;


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
        setContentView(R.layout.activity_view_processors);

        processorListView = (ListView) findViewById(R.id.processorListView);
        homepage_selected = ContextCompat.getDrawable(getApplicationContext(), R.drawable.homepage_selected);
        log_out = (LinearLayout) findViewById(R.id.log_out);
        pic = (ImageView) findViewById(R.id.pic);
        processorPageProgressBar = (ProgressBar) findViewById(R.id.processorPageProgressBar);
        processorPageProgressBar.setVisibility(View.VISIBLE);
        numberOfProcessors = (TextView) findViewById(R.id.numberOfProcessors);

        getProcessors();
        if (Splash.currentUser.getImage() == null || Splash.currentUser.getImage().equals("")){

        }else {
            Glide.with(ViewProcessors.this).load(Splash.currentUser.getImage()).into(pic);
        }
    }

    private void getProcessors() {
        Call<processorResponse> call = jsonApi.getAllProcessors(Splash.sp.getString("Cookie", ""));
        call.enqueue(new Callback<processorResponse>() {
            @Override
            public void onResponse(Call<processorResponse> call, Response<processorResponse> response) {
                if (!response.isSuccessful()){
                    ResponseBody loginResponse = response.errorBody();
                    try {
                        String message = loginResponse.string();
                        Toast.makeText(ViewProcessors.this, message.substring(10, message.length()-2), Toast.LENGTH_SHORT).show();
                        if (message.equals("\"error\": \"you dont have any connected processor units\"")){
                            processorPageProgressBar.setVisibility(View.INVISIBLE);
                            onBackPressed();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    return;
                }
                if(response.body().getDetail().equals("processor unit connection data collected Successfully ")){
                    List<processorData> processors = response.body().getConnectionData();
                    adapter = new processorAdapter(ViewProcessors.this, processors);
                    processorListView.setAdapter(adapter);
                    numberOfProcessors.setText(String.valueOf(processors.size()));
                    processorPageProgressBar.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void onFailure(Call<processorResponse> call, Throwable t) {
                Toast.makeText(ViewProcessors.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                processorPageProgressBar.setVisibility(View.INVISIBLE);
                onBackPressed();
            }
        });
    }


    public void homePage(View view){
        LinearLayout home_logo = (LinearLayout) findViewById(R.id.home_logo);
        home_logo.setBackground(homepage_selected);
        if (Splash.sp.getBoolean("Admin", false)){
            redirectActivity(this, HomePageAdminActivity.class);
        }else {
            redirectActivity(this, HomePageActivity.class);
        }
    }

    public void goAddNewDevice(View view){
        LinearLayout add_device = (LinearLayout) findViewById(R.id.add_device);
        add_device.setBackground(homepage_selected);
        redirectActivity(this, AddNewDeviceActivity.class);
    }

    public void goUserProfile(View view){
        LinearLayout frame = (LinearLayout) findViewById(R.id.frame);
        frame.setBackground(homepage_selected);
        redirectActivity(this, UserProfileActivity.class);}

    @SuppressLint("ResourceAsColor")
    public void logoutAction(View view) {
        log_out.setBackground(homepage_selected);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                Splash.sp.edit().putString("Cookie", "").apply();
                Activity activity = ViewProcessors.this;
                redirectActivity(activity, MainActivity.class);
            }
        }, logoutTimeOut);
    }

    public static void redirectActivity(Activity activity, Class aClass) {
        Intent intent = new Intent(activity,aClass);
        activity.startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        if (Splash.sp.getBoolean("Admin", false)){
            redirectActivity(this, HomePageAdminActivity.class);
        }else {
            redirectActivity(this, HomePageActivity.class);
        }
    }
}