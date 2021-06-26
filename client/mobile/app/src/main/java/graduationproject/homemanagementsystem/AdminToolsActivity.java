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
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import org.w3c.dom.Text;

import java.io.IOException;
import java.util.List;

import graduationproject.homemanagementsystem.dataClasses.JsonApi;
import graduationproject.homemanagementsystem.dataClasses.controllerData;
import graduationproject.homemanagementsystem.dataClasses.controllerResponseAll;
import graduationproject.homemanagementsystem.dataClasses.data;
import graduationproject.homemanagementsystem.dataClasses.getProfileAll;
import graduationproject.homemanagementsystem.dataClasses.locationData;
import graduationproject.homemanagementsystem.dataClasses.locationResponse;
import graduationproject.homemanagementsystem.dataClasses.processorData;
import graduationproject.homemanagementsystem.dataClasses.processorResponseAll;
import graduationproject.homemanagementsystem.dataClasses.typeData;
import graduationproject.homemanagementsystem.dataClasses.typeResponse;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AdminToolsActivity extends AppCompatActivity {

    private final int logoutTimeOut = 499;
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://home-msy.herokuapp.com/api/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    JsonApi jsonApi = retrofit.create(JsonApi.class);
    private Drawable homepage_selected;
    private LinearLayout log_out;
    private ProgressBar HomepageProgressBar;
    private TextView numberOfTypes;
    private TextView numberOfLocations;
    private TextView numberOfUses;
    private TextView numberOfProcessors;
    private TextView numberOfControllers;
    private ImageView pic;
    private boolean user, type, location, controller, processor;

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
        setContentView(R.layout.activity_admin_tools);

        homepage_selected = ContextCompat.getDrawable(getApplicationContext(), R.drawable.homepage_selected);
        log_out = (LinearLayout) findViewById(R.id.log_out);
        HomepageProgressBar = (ProgressBar) findViewById(R.id.HomepageProgressBar);
        numberOfTypes = (TextView) findViewById(R.id.numberOfTypes);
        numberOfLocations = (TextView) findViewById(R.id.numberOfLocations);
        numberOfUses = (TextView) findViewById(R.id.numberOfUses);
        numberOfProcessors = (TextView) findViewById(R.id.numberOfProcessors);
        numberOfControllers = (TextView) findViewById(R.id.numberOfControllers);
        pic = (ImageView) findViewById(R.id.pic);

        HomepageProgressBar.setVisibility(View.VISIBLE);

        if (Splash.currentUser.getImage() == null || Splash.currentUser.getImage().equals("")){

        }else {
            Glide.with(AdminToolsActivity.this).load(Splash.currentUser.getImage()).into(pic);
        }


        getControllerTypes();
        getControllerLocations();
        getAllUsers();
        getAllProcessors();
        getAllControllers();

        if (user && type && location && processor && controller){
            HomepageProgressBar.setVisibility(View.INVISIBLE);
        }
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
                Activity homePageActivity = AdminToolsActivity.this;
                redirectActivity(homePageActivity, MainActivity.class);
            }
        }, logoutTimeOut);

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
                        Toast.makeText(AdminToolsActivity.this, message.substring(10, message.length()-2), Toast.LENGTH_SHORT).show();
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
                    numberOfLocations.setText(String.valueOf(locations.size()));
                    Splash.locationList = new String[locations.size()];
                    for (int i=0; i<locations.size(); i++){
                        Splash.locationList[i] = locations.get(i).getLabel();
                    }
                    location = true;
                    if (user && type && location && processor && controller){
                        HomepageProgressBar.setVisibility(View.INVISIBLE);
                    }
                }
            }

            @Override
            public void onFailure(Call<locationResponse> call, Throwable t) {
                Toast.makeText(AdminToolsActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
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
                        Toast.makeText(AdminToolsActivity.this, message.substring(10, message.length()-2), Toast.LENGTH_SHORT).show();
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
                    numberOfTypes.setText(String.valueOf(types.size()));
                    Splash.typesList = new String[types.size()];
                    for (int i=0; i<types.size(); i++){
                        Splash.typesList[i] = types.get(i).getLabel();
                    }
                    type = true;
                    if (user && type && location && processor && controller){
                        HomepageProgressBar.setVisibility(View.INVISIBLE);
                    }
                }
            }

            @Override
            public void onFailure(Call<typeResponse> call, Throwable t) {
                Toast.makeText(AdminToolsActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                type = true;
                if (user && type && location && processor && controller){
                    HomepageProgressBar.setVisibility(View.INVISIBLE);
                }
            }
        });
    }

    private void getAllUsers(){
        Call<getProfileAll> call = jsonApi.getAllUsers(Splash.sp.getString("Cookie", ""));
        call.enqueue(new Callback<getProfileAll>() {
            @Override
            public void onResponse(Call<getProfileAll> call, Response<getProfileAll> response) {
                if (!response.isSuccessful()){
                    ResponseBody loginResponse = response.errorBody();
                    try {
                        String message = loginResponse.string();
                        Toast.makeText(AdminToolsActivity.this, message.substring(10, message.length()-2), Toast.LENGTH_SHORT).show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    user = true;
                    if (user && type && location && processor && controller){
                        HomepageProgressBar.setVisibility(View.INVISIBLE);
                    }
                    return;
                }
                if (response.body().getDetail().equals("all users data collected Successfully ")){
                    List<data> users = response.body().getProductionData();
                    numberOfUses.setText(String.valueOf(users.size()));
                    user = true;
                    if (user && type && location && processor && controller){
                        HomepageProgressBar.setVisibility(View.INVISIBLE);
                    }
                }

            }

            @Override
            public void onFailure(Call<getProfileAll> call, Throwable t) {
                Toast.makeText(AdminToolsActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                user = true;
                if (user && type && location && processor && controller){
                    HomepageProgressBar.setVisibility(View.INVISIBLE);
                }
            }
        });
    }

    private void getAllProcessors(){
        Call<processorResponseAll> call = jsonApi.getProcessorsAll(Splash.sp.getString("Cookie", ""));
        call.enqueue(new Callback<processorResponseAll>() {
            @Override
            public void onResponse(Call<processorResponseAll> call, Response<processorResponseAll> response) {
                if (!response.isSuccessful()){
                    ResponseBody loginResponse = response.errorBody();
                    try {
                        String message = loginResponse.string();
                        Toast.makeText(AdminToolsActivity.this, message.substring(10, message.length()-2), Toast.LENGTH_SHORT).show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    processor = true;
                    if (user && type && location && processor && controller){
                        HomepageProgressBar.setVisibility(View.INVISIBLE);
                    }
                    return;
                }
                if (response.body().getDetail().equals("processor unit production data collected Successfully ")){
                    List<processorData> processors = response.body().getProductionData();
                    numberOfProcessors.setText(String.valueOf(processors.size()));
                    processor = true;
                    if (user && type && location && processor && controller){
                        HomepageProgressBar.setVisibility(View.INVISIBLE);
                    }
                }
            }

            @Override
            public void onFailure(Call<processorResponseAll> call, Throwable t) {
                Toast.makeText(AdminToolsActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                processor = true;
                if (user && type && location && processor && controller){
                    HomepageProgressBar.setVisibility(View.INVISIBLE);
                }
            }
        });
    }

    private void getAllControllers(){
        Call<controllerResponseAll> call = jsonApi.getControllersAll(Splash.sp.getString("Cookie", ""));
        call.enqueue(new Callback<controllerResponseAll>() {
            @Override
            public void onResponse(Call<controllerResponseAll> call, Response<controllerResponseAll> response) {
                if (!response.isSuccessful()){
                    ResponseBody loginResponse = response.errorBody();
                    try {
                        String message = loginResponse.string();
                        Toast.makeText(AdminToolsActivity.this, message.substring(10, message.length()-2), Toast.LENGTH_SHORT).show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    controller = true;
                    if (user && type && location && processor && controller){
                        HomepageProgressBar.setVisibility(View.INVISIBLE);
                    }
                    return;
                }
                if (response.body().getDetail().equals("Controller units production data collected Successfully ")){
                    List<controllerData> controllers = response.body().getProductionData();
                    numberOfControllers.setText(String.valueOf(controllers.size()));
                    controller = true;
                    if (user && type && location && processor && controller){
                        HomepageProgressBar.setVisibility(View.INVISIBLE);
                    }
                }
            }

            @Override
            public void onFailure(Call<controllerResponseAll> call, Throwable t) {
                Toast.makeText(AdminToolsActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                controller = true;
                if (user && type && location && processor && controller){
                    HomepageProgressBar.setVisibility(View.INVISIBLE);
                }
            }
        });
    }

    public static void redirectActivity(Activity activity, Class aClass) {
        Intent intent = new Intent(activity,aClass);
        activity.startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        redirectActivity(this, HomePageAdminActivity.class);
    }

    public void viewAllUsers(View view) {
        if (numberOfUses.getText().toString().equals("0")) {
            Toast.makeText(getApplicationContext(), "There is no registered users",Toast.LENGTH_SHORT).show();
        } else {
            redirectActivity(AdminToolsActivity.this, ViewAllUsersActivity.class);
        }
    }

    public void viewAllTypes(View view) {
        redirectActivity(AdminToolsActivity.this, ViewAllTypesActivity.class);
    }

    public void ViewAllLocations(View view) {
        redirectActivity(AdminToolsActivity.this, ViewAllLocationsActivity.class);
    }

    public void viewAllProcessors(View view) {
        redirectActivity(AdminToolsActivity.this, ViewAllProcessorsActivity.class);
    }

    public void viewAllControllers(View view) {
        redirectActivity(AdminToolsActivity.this, ViewAllControllersActivity.class);
    }
}