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
import graduationproject.homemanagementsystem.dataClasses.controllerData;
import graduationproject.homemanagementsystem.dataClasses.controllerResponse;
import graduationproject.homemanagementsystem.dataClasses.data;
import graduationproject.homemanagementsystem.dataClasses.deviceAdapter;
import graduationproject.homemanagementsystem.dataClasses.getProfile;
import graduationproject.homemanagementsystem.dataClasses.locationData;
import graduationproject.homemanagementsystem.dataClasses.locationResponse;
import graduationproject.homemanagementsystem.dataClasses.processorData;
import graduationproject.homemanagementsystem.dataClasses.processorResponse;
import graduationproject.homemanagementsystem.dataClasses.typeData;
import graduationproject.homemanagementsystem.dataClasses.typeResponse;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HomePageActivity extends AppCompatActivity {

    private final int logoutTimeOut = 499;
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://home-msy.herokuapp.com/api/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    JsonApi jsonApi = retrofit.create(JsonApi.class);
    private LinearLayout log_out;
    private LinearLayout home_logo;
    private Drawable homepage_selected;
    private ImageView pic;
    private ListView listView;
    private deviceAdapter adapter;
    private TextView numberOfControllers;
    private  Toast toast;
    private TextView numberOfProcessors;
    private ProgressBar HomepageProgressBar;
    private boolean user, type, location, controller, processor;


    public static void redirectActivity(Activity activity, Class aClass) {
        Intent intent = new Intent(activity,aClass);
        activity.startActivity(intent);
    }


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
        setContentView(R.layout.activity_home_page);


        listView = (ListView) findViewById(R.id.listView);
        home_logo = (LinearLayout) findViewById(R.id.home_logo);
        log_out = (LinearLayout) findViewById(R.id.log_out);
        numberOfProcessors = (TextView) findViewById(R.id.numberOfProcessors);
        numberOfControllers = (TextView) findViewById(R.id.numberOfControllers);
        HomepageProgressBar = (ProgressBar) findViewById(R.id.HomepageProgressBar);
        toast = new Toast(this);


        homepage_selected = ContextCompat.getDrawable(getApplicationContext(), R.drawable.homepage_selected);
        pic = (ImageView) findViewById(R.id.pic);
        home_logo.setBackground(homepage_selected);
        HomepageProgressBar.setVisibility(View.VISIBLE);
        user = location = type = processor = controller = false;



        chechUser();
        getControllerTypes();
        getControllerLocations();
        getProcessors();



        if (user && type && location && processor && controller){
            HomepageProgressBar.setVisibility(View.INVISIBLE);
        }
    }

    private void getControllerLocations() {
        Call<locationResponse> call = jsonApi.getLocations(Splash.sp.getString("Cookie", ""));
        call.enqueue(new Callback<locationResponse>() {
            @Override
            public void onResponse(Call<locationResponse> call, Response<locationResponse> response) {
                if (!response.isSuccessful()){
                    ResponseBody loginResponse = response.errorBody();
                    try {
                        String message = loginResponse.string();
                        toast.makeText(HomePageActivity.this, message.substring(10, message.length()-2), Toast.LENGTH_SHORT).show();
                        getControllers();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    location = true;
                    if (user && type && location && processor && controller){
                        HomepageProgressBar.setVisibility(View.INVISIBLE);
                    }
                    return;
                }

                if (response.body().getDetail().equals("Controller location fetched Successfully")){
                    List<locationData> locations = response.body().getLocationLabels();
                    Splash.locations = locations;
                    Splash.locationList = new String[locations.size()];
                    for (int i=0; i<locations.size(); i++){
                        Splash.locationList[i] = locations.get(i).getLabel();
                    }
                    location = true;
                    if (location && type && ! controller){
                        getControllers();
                    }
                    if (user && type && location && processor && controller){
                        HomepageProgressBar.setVisibility(View.INVISIBLE);

                    }
                }
            }

            @Override
            public void onFailure(Call<locationResponse> call, Throwable t) {
                getControllers();
                toast.makeText(HomePageActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                location = true;
                if (user && type && location && processor && controller){
                    HomepageProgressBar.setVisibility(View.INVISIBLE);
                }
            }
        });
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
                        toast.makeText(HomePageActivity.this, message.substring(10, message.length()-2), Toast.LENGTH_SHORT).show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    type = true;
                    if (user && type && location && processor && controller){
                        HomepageProgressBar.setVisibility(View.INVISIBLE);
                    }
                    return;
                }

                if (response.body().getDetail().equals("Controller Type fetched Successfully")){
                    List<typeData> types = response.body().getTypeLabels();
                    Splash.types = types;
                    Splash.typesList = new String[types.size()];
                    for (int i=0; i<types.size(); i++){
                        Splash.typesList[i] = types.get(i).getLabel();
                    }
                    type = true;
                    if (location && type && !controller){
                        getControllers();
                    }
                    if (user && type && location && processor && controller){
                        HomepageProgressBar.setVisibility(View.INVISIBLE);
                    }
                }
            }

            @Override
            public void onFailure(Call<typeResponse> call, Throwable t) {
                toast.makeText(HomePageActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                type = true;
                if (user && type && location && processor && controller){
                    HomepageProgressBar.setVisibility(View.INVISIBLE);
                }
            }
        });
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
                        toast.makeText(HomePageActivity.this, message.substring(10, message.length()-2), Toast.LENGTH_SHORT).show();
                        if (message.equals("\"error\": \"you dont have any connected processor units\"")){
                            numberOfProcessors.setText("0");
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    processor = true;
                    if (user && type && location && processor && controller){
                        HomepageProgressBar.setVisibility(View.INVISIBLE);
                    }
                    return;
                }
                if(response.body().getDetail().equals("processor unit connection data collected Successfully ")){
                    List<processorData> processors = response.body().getConnectionData();
                    numberOfProcessors.setText(String.valueOf(processors.size()));
                    Splash.processorList = new String[processors.size()];
                    for (int i=0; i<processors.size(); i++){
                        Splash.processorList[i] = String.valueOf(processors.get(i).getId());
                    }
                    processor = true;
                    if (location && type && !controller){
                        getControllers();
                    }
                    if (user && type && location && processor && controller){
                        HomepageProgressBar.setVisibility(View.INVISIBLE);
                    }
                }
            }

            @Override
            public void onFailure(Call<processorResponse> call, Throwable t) {
                toast.makeText(HomePageActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                processor = true;
                if (user && type && location && processor && controller){
                    HomepageProgressBar.setVisibility(View.INVISIBLE);
                }
            }
        });
    }

    private void getControllers() {
        Call<controllerResponse> call = jsonApi.getAllControllers(Splash.sp.getString("Cookie",""));
        call.enqueue(new Callback<controllerResponse>() {
            @Override
            public void onResponse(Call<controllerResponse> call, Response<controllerResponse> response) {
                if (!response.isSuccessful()){
                    ResponseBody loginResponse = response.errorBody();
                    try {
                        String message = loginResponse.string();
                        //toast.makeText(HomePageActivity.this, message.substring(10, message.length()-2), Toast.LENGTH_SHORT).show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    controller = true;
                    if (user && type && location && processor && controller){
                        HomepageProgressBar.setVisibility(View.INVISIBLE);
                    }
                    return;
                }
                if (response.body().getDetail().equals("user controllers data collected Successfully ")){
                    if (response.body().getConnectionData().toString().equals("[no controllers connected yet]")){
                        numberOfControllers.setText("0");
                    }else{
                        List<controllerData> controllers = response.body().getConnectionData();
                        numberOfControllers.setText(String.valueOf(controllers.size()));
                        adapter = new deviceAdapter(HomePageActivity.this, controllers);
                        listView.setAdapter(adapter);
                    }
                    controller = true;
                    if (user && type && location && processor && controller){
                        HomepageProgressBar.setVisibility(View.INVISIBLE);
                    }
                }
            }

            @Override
            public void onFailure(Call<controllerResponse> call, Throwable t) {
                controller = true;
                if (user && type && location && processor && controller){
                    HomepageProgressBar.setVisibility(View.INVISIBLE);
                }
                //toast.makeText(HomePageActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void chechUser() {
        Call <getProfile> call = jsonApi.getProfileData(Splash.sp.getString("Cookie",""));
        call.enqueue(new Callback<getProfile>() {
            @Override
            public void onResponse(Call<getProfile> call, Response<getProfile> response) {
                if (!response.isSuccessful()){
                    ResponseBody loginResponse = response.errorBody();
                    try {
                        String message = loginResponse.string();
                        toast.makeText(HomePageActivity.this, message.substring(10, message.length()-2), Toast.LENGTH_SHORT).show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    user = true;
                    if (user && type && location && processor && controller){
                        HomepageProgressBar.setVisibility(View.INVISIBLE);
                    }
                    return;
                }
                Splash.currentUser = response.body().getProfileData().get(0);
                user = true;
                if (user && type && location && processor && controller){
                    HomepageProgressBar.setVisibility(View.INVISIBLE);
                }
                if (Splash.currentUser.getImage() == null || Splash.currentUser.getImage().equals("")){

                }else {
                    downloadPhoto(Splash.currentUser.getImage());
                }
            }

            @Override
            public void onFailure(Call<getProfile> call, Throwable t) {
                user = true;
                if (user && type && location && processor && controller){
                    HomepageProgressBar.setVisibility(View.INVISIBLE);
                }
                toast.makeText(HomePageActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void downloadPhoto(String image) {
        Glide.with(getApplicationContext()).load(image).into(pic);
    }


    public void homePage(View view){
    }

    public void goAddNewDevice(View view){
        home_logo.setBackground(null);
        LinearLayout add_device = (LinearLayout) findViewById(R.id.add_device);
        add_device.setBackground(homepage_selected);
        redirectActivity(this, AddNewDeviceActivity.class);
    }

    public void goUserProfile(View view){
        home_logo.setBackground(null);
        LinearLayout frame = (LinearLayout) findViewById(R.id.frame);
        frame.setBackground(homepage_selected);
        redirectActivity(this, UserProfileActivity.class);}

    @SuppressLint("ResourceAsColor")
    public void logoutAction(View view){
        home_logo.setBackground(null);
        log_out.setBackground(homepage_selected);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                Splash.sp.edit().putString("Cookie", "").apply();
                Activity homePageActivity = HomePageActivity.this;
                redirectActivity(homePageActivity, MainActivity.class);
            }
        }, logoutTimeOut);

    }

    @Override
    public void onBackPressed() {
        //do nothing
    }

    public void viewProcessors(View view) {
        if (numberOfProcessors.getText().toString().equals("0")) {
            Toast.makeText(getApplicationContext(), "There is no connected processors",Toast.LENGTH_SHORT).show();
        } else {
            redirectActivity(HomePageActivity.this, ViewProcessors.class);
        }
    }
}