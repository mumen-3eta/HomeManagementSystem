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

import graduationproject.homemanagementsystem.dataClasses.CustomDialogClass;
import graduationproject.homemanagementsystem.dataClasses.JsonApi;
import graduationproject.homemanagementsystem.dataClasses.addTypeClass;
import graduationproject.homemanagementsystem.dataClasses.typeAdapter;
import graduationproject.homemanagementsystem.dataClasses.typeData;
import graduationproject.homemanagementsystem.dataClasses.typeResponse;
import graduationproject.homemanagementsystem.dataClasses.userAdapter;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ViewAllTypesActivity extends AppCompatActivity {

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
    private TextView numberOfTypes;
    private typeAdapter adapter;

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
        setContentView(R.layout.activity_view_all_types);

        pic = (ImageView) findViewById(R.id.pic);
        homepage_selected = ContextCompat.getDrawable(getApplicationContext(), R.drawable.homepage_selected);
        log_out = (LinearLayout) findViewById(R.id.log_out);
        HomepageProgressBar = (ProgressBar) findViewById(R.id.HomepageProgressBar);
        listView = (ListView) findViewById(R.id.listView);
        numberOfTypes = (TextView) findViewById(R.id.numberOfTypes);

        HomepageProgressBar.setVisibility(View.VISIBLE);

        if (Splash.currentUser.getImage() == null || Splash.currentUser.getImage().equals("")){

        }else {
            Glide.with(ViewAllTypesActivity.this).load(Splash.currentUser.getImage()).into(pic);
        }

        getControllerTypes();
    }

    private void getControllerTypes() {
        Call<typeResponse> call = jsonApi.getTypes(Splash.sp.getString("Cookie", ""));
        call.enqueue(new Callback<typeResponse>() {
            @Override
            public void onResponse(Call<typeResponse> call, Response<typeResponse> response) {
                if (!response.isSuccessful()){
                    ResponseBody loginResponse = response.errorBody();
                    try {
                        String message = loginResponse.string();
                        Toast.makeText(ViewAllTypesActivity.this, message.substring(10, message.length()-2), Toast.LENGTH_SHORT).show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    HomepageProgressBar.setVisibility(View.INVISIBLE);
                    return;
                }

                if (response.body().getDetail().equals("Controller Type fetched Successfully")){
                    List<typeData> types = response.body().getTypeLabels();
                    Splash.types = types;
                    numberOfTypes.setText(String.valueOf(types.size()));
                    Splash.typesList = new String[types.size()];
                    for (int i=0; i<types.size(); i++){
                        Splash.typesList[i] = types.get(i).getLabel();
                    }
                    adapter = new typeAdapter(ViewAllTypesActivity.this, types);
                    listView.setAdapter(adapter);
                    HomepageProgressBar.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void onFailure(Call<typeResponse> call, Throwable t) {
                Toast.makeText(ViewAllTypesActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
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
                Activity homePageActivity = ViewAllTypesActivity.this;
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

    public void showAddType(View view) {
        addTypeClass cdd = new addTypeClass((Activity) view.getContext());
        cdd.show();
    }
}