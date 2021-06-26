package graduationproject.homemanagementsystem;

import androidx.appcompat.app.AppCompatActivity;
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
import graduationproject.homemanagementsystem.dataClasses.controllerData;
import graduationproject.homemanagementsystem.dataClasses.controllerResponse;
import graduationproject.homemanagementsystem.dataClasses.deviceAdapter;
import graduationproject.homemanagementsystem.dataClasses.processorData;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ViewControllers extends AppCompatActivity {

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://home-msy.herokuapp.com/api/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    JsonApi jsonApi = retrofit.create(JsonApi.class);
    private final int logoutTimeOut = 499;
    private Drawable homepage_selected;
    private LinearLayout log_out;
    private ImageView pic;
    ListView controllerListView;
    private deviceAdapter adapter;
    private TextView numberOfControllers;
    private ProgressBar controllerPageProgressBar;
    private TextView connectedProcessorId;

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
        setContentView(R.layout.activity_view_controllers);

        homepage_selected = ContextCompat.getDrawable(getApplicationContext(), R.drawable.homepage_selected);
        log_out = (LinearLayout) findViewById(R.id.log_out);
        pic = (ImageView) findViewById(R.id.pic);
        controllerListView = (ListView) findViewById(R.id.controllerListView);
        numberOfControllers = (TextView) findViewById(R.id.numberOfControllers);
        controllerPageProgressBar = (ProgressBar) findViewById(R.id.controllerPageProgressBar);
        controllerPageProgressBar.setVisibility(View.VISIBLE);
        connectedProcessorId = (TextView) findViewById(R.id.connectedProcessorId);

        Intent intent = getIntent();
        int connectionID = intent.getIntExtra("connectionID", -1);
        getControllers(connectionID);
        connectedProcessorId.setText(String.valueOf(connectionID));

        if (Splash.currentUser.getImage() == null || Splash.currentUser.getImage().equals("")){

        }else {
            Glide.with(ViewControllers.this).load(Splash.currentUser.getImage()).into(pic);
        }
    }

    private void getControllers(int connectionID) {
        processorData processor = new processorData();
        processor.setProcessorId(connectionID);
        Call<controllerResponse> call = jsonApi.getProcessorControllers(Splash.sp.getString("Cookie",""), processor);
        call.enqueue(new Callback<controllerResponse>() {
            @Override
            public void onResponse(Call<controllerResponse> call, Response<controllerResponse> response) {
                if (!response.isSuccessful()){
                    ResponseBody loginResponse = response.errorBody();
                    try {
                        String message = loginResponse.string();
                        Toast.makeText(getApplicationContext(), message.substring(10, message.length()-2), Toast.LENGTH_SHORT).show();
                        controllerPageProgressBar.setVisibility(View.INVISIBLE);
                        onBackPressed();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    return;
                }
                if (response.body().getDetail().equals("processor unit controllers data collected Successfully ")){
                    if (response.body().getConnectionData().toString().equals("[no controllers connected yet]")){
                        Toast.makeText(getApplicationContext(), "This processor has no controllers", Toast.LENGTH_SHORT).show();
                        controllerPageProgressBar.setVisibility(View.INVISIBLE);
                        onBackPressed();
                    }else{
                        List<controllerData> controllers = response.body().getConnectionData();
                        numberOfControllers.setText(String.valueOf(controllers.size()));
                        adapter = new deviceAdapter(ViewControllers.this, controllers);
                        controllerListView.setAdapter(adapter);
                    }
                }
                controllerPageProgressBar.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onFailure(Call<controllerResponse> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                controllerPageProgressBar.setVisibility(View.INVISIBLE);
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
                Activity activity = ViewControllers.this;
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
            redirectActivity(this, ViewProcessors.class);
    }
}