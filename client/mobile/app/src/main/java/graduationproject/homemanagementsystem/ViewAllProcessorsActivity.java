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
import graduationproject.homemanagementsystem.dataClasses.locationAdapter;
import graduationproject.homemanagementsystem.dataClasses.processorAdminAdapter;
import graduationproject.homemanagementsystem.dataClasses.processorData;
import graduationproject.homemanagementsystem.dataClasses.processorResponseAdmin;
import graduationproject.homemanagementsystem.dataClasses.processorResponseAll;
import graduationproject.homemanagementsystem.dataClasses.qrDialogClass;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ViewAllProcessorsActivity extends AppCompatActivity {

    private final int logoutTimeOut = 499;
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://home-msy.herokuapp.com/api/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    JsonApi jsonApi = retrofit.create(JsonApi.class);
    private ImageView pic;
    private Drawable homepage_selected;
    private LinearLayout log_out;
    private ProgressBar HomepageProgressBar;
    private ListView listView;
    private TextView numberOfProcessors;
    private processorAdminAdapter adapter;

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
        setContentView(R.layout.activity_view_all_processors);

        pic = (ImageView) findViewById(R.id.pic);
        homepage_selected = ContextCompat.getDrawable(getApplicationContext(), R.drawable.homepage_selected);
        log_out = (LinearLayout) findViewById(R.id.log_out);
        HomepageProgressBar = (ProgressBar) findViewById(R.id.processorPageProgressBar);
        listView = (ListView) findViewById(R.id.processorListView);
        numberOfProcessors = (TextView) findViewById(R.id.numberOfProcessors);

        HomepageProgressBar.setVisibility(View.VISIBLE);

        if (Splash.currentUser.getImage() == null || Splash.currentUser.getImage().equals("")){

        }else {
            Glide.with(ViewAllProcessorsActivity.this).load(Splash.currentUser.getImage()).into(pic);
        }

        getAllProcessors();
    }

    private void getAllProcessors(){
        HomepageProgressBar.setVisibility(View.VISIBLE);
        Call<processorResponseAll> call = jsonApi.getProcessorsAll(Splash.sp.getString("Cookie", ""));
        call.enqueue(new Callback<processorResponseAll>() {
            @Override
            public void onResponse(Call<processorResponseAll> call, Response<processorResponseAll> response) {
                if (!response.isSuccessful()){
                    ResponseBody loginResponse = response.errorBody();
                    try {
                        String message = loginResponse.string();
                        Toast.makeText(ViewAllProcessorsActivity.this, message.substring(10, message.length()-2), Toast.LENGTH_SHORT).show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    HomepageProgressBar.setVisibility(View.INVISIBLE);
                    return;
                }
                if (response.body().getDetail().equals("processor unit production data collected Successfully ")){
                    List<processorData> processors = response.body().getProductionData();
                    numberOfProcessors.setText(String.valueOf(processors.size()));
                    adapter = new processorAdminAdapter(ViewAllProcessorsActivity.this, processors);
                    listView.setAdapter(adapter);
                    HomepageProgressBar.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void onFailure(Call<processorResponseAll> call, Throwable t) {
                Toast.makeText(ViewAllProcessorsActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                HomepageProgressBar.setVisibility(View.INVISIBLE);
            }
        });
    }

    public void homePage(View view){
        LinearLayout home_logo = (LinearLayout) findViewById(R.id.home_logo);
        home_logo.setBackground(homepage_selected);
        redirectActivity(this, HomePageAdminActivity.class);
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
    public void logoutAction(View view){
        log_out.setBackground(homepage_selected);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                Splash.sp.edit().putString("Cookie", "").apply();
                Activity homePageActivity = ViewAllProcessorsActivity.this;
                redirectActivity(homePageActivity, MainActivity.class);
            }
        }, logoutTimeOut);

    }

    public static void redirectActivity(Activity activity, Class aClass) {
        Intent intent = new Intent(activity,aClass);
        activity.startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        redirectActivity(this, AdminToolsActivity.class);
    }

    public void showAddProcessor(View view) {
        HomepageProgressBar.setVisibility(View.VISIBLE);
        Call<processorResponseAdmin> call = jsonApi.createNewProcessor(Splash.sp.getString("Cookie", ""));
        call.enqueue(new Callback<processorResponseAdmin>() {
            @Override
            public void onResponse(Call<processorResponseAdmin> call, Response<processorResponseAdmin> response) {
                if (!response.isSuccessful()){
                    ResponseBody loginResponse = response.errorBody();
                    try {
                        String message = loginResponse.string();
                        Toast.makeText(ViewAllProcessorsActivity.this, message.substring(10, message.length()-2), Toast.LENGTH_SHORT).show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    return;
                }
                if (response.body().getDetail().equals("processor unit created Successfully ")){
                    HomepageProgressBar.setVisibility(View.INVISIBLE);
                    getAllProcessors();
                    int id = response.body().getNewProcessor().get(0).getId();
                    qrDialogClass qr = new qrDialogClass(ViewAllProcessorsActivity.this, id);
                    qr.show();
                }
            }

            @Override
            public void onFailure(Call<processorResponseAdmin> call, Throwable t) {
                Toast.makeText(ViewAllProcessorsActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}