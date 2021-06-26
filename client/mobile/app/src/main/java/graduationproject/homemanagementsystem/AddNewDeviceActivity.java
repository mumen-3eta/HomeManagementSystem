package graduationproject.homemanagementsystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.camera.core.Camera;
import androidx.camera.core.CameraSelector;
import androidx.camera.core.ImageAnalysis;
import androidx.camera.core.Preview;
import androidx.camera.core.UseCase;
import androidx.camera.lifecycle.ProcessCameraProvider;
import androidx.camera.view.PreviewView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.LifecycleOwner;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.util.Size;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.bumptech.glide.Glide;
import com.google.common.util.concurrent.ListenableFuture;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;

import graduationproject.homemanagementsystem.dataClasses.JsonApi;
import graduationproject.homemanagementsystem.dataClasses.controllerData;
import graduationproject.homemanagementsystem.dataClasses.controllerResponse;
import graduationproject.homemanagementsystem.dataClasses.processorData;
import graduationproject.homemanagementsystem.dataClasses.processorResponse;
import graduationproject.homemanagementsystem.utility.QRCodeFoundListener;
import graduationproject.homemanagementsystem.utility.QRCodeImageAnalyzer;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AddNewDeviceActivity extends AppCompatActivity {

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://home-msy.herokuapp.com/api/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    JsonApi jsonApi = retrofit.create(JsonApi.class);
    private LinearLayout log_out;
    private ImageView pic;
    private final int logoutTimeOut = 499;
    private LinearLayout add_device;
    private Drawable homepage_selected;
    private Spinner typeSpinner;
    private Spinner locationSpinner;
    private Spinner processorSpinner;
    private EditText controllerIdEditText;
    private static final int PERMISSION_REQUEST_CAMERA = 0;
    private ListenableFuture<ProcessCameraProvider> cameraProviderFuture;
    private PreviewView previewView;
    private Button qrCodeFoundButton;
    private String qrCode;
    private int caller = 1;
    private EditText processorIdEditText;
    private ViewFlipper add_device_viewFlipper;
    private Drawable info_background;
    private TextView processorTitle;
    private TextView controllerTitle;
    private ProcessCameraProvider cameraProvider;
    private EditText controllerNameEditText;
    private int darkGreen;
    private int white;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (Splash.sp.getString("Theme", "") != null || !Splash.sp.getString("Theme", "").equals("")){
            if (Splash.sp.getString("Theme", "").equals("dark")){
                setTheme(R.style.DarkTheme);
                darkGreen = getResources().getColor(R.color.white);
                white = getResources().getColor(R.color.black);
                info_background = ContextCompat.getDrawable(getApplicationContext(), R.drawable.info_background_dark);
            }else {
                setTheme(R.style.AppTheme);
                info_background = ContextCompat.getDrawable(getApplicationContext(), R.drawable.info_background);
                darkGreen = getResources().getColor(R.color.darkGreen);
                white = getResources().getColor(R.color.white);
            }
        }else {
            setTheme(R.style.AppTheme);
            info_background = ContextCompat.getDrawable(getApplicationContext(), R.drawable.info_background);
            darkGreen = getResources().getColor(R.color.darkGreen);
            white = getResources().getColor(R.color.white);
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_device);


        log_out = (LinearLayout) findViewById(R.id.log_out);
        controllerIdEditText = (EditText) findViewById(R.id.controllerIdEditText);
        processorIdEditText = (EditText) findViewById(R.id.processorIdEditText);
        add_device_viewFlipper = (ViewFlipper) findViewById(R.id.add_device_viewFlipper);
        processorTitle = (TextView) findViewById(R.id.processorTitle);
        controllerTitle = (TextView) findViewById(R.id.controllerTitle);
        controllerNameEditText = (EditText) findViewById(R.id.controllerNameEditText);

        processorTitle.setBackground(info_background);
        processorTitle.setTextColor(darkGreen);
        pic = (ImageView) findViewById(R.id.pic);

        homepage_selected = ContextCompat.getDrawable(getApplicationContext(), R.drawable.homepage_selected);
        add_device = (LinearLayout) findViewById(R.id.add_device);
        add_device.setBackground(homepage_selected);
        previewView = findViewById(R.id.activity_main_previewView);
        qrCodeFoundButton = findViewById(R.id.activity_main_qrCodeFoundButton);
        qrCodeFoundButton.setVisibility(View.INVISIBLE);


        cameraProviderFuture = ProcessCameraProvider.getInstance(this);


        typeSpinner = (Spinner) findViewById(R.id.typeSpinner);
        ArrayAdapter<CharSequence> typeAdapter = new ArrayAdapter<CharSequence>(this, R.layout.spinner_text, Splash.typesList );
        typeAdapter.setDropDownViewResource(R.layout.spinner_dropdown);
        typeSpinner.setAdapter(typeAdapter);

        locationSpinner = (Spinner) findViewById(R.id.locationSpinner);
        ArrayAdapter<CharSequence> locationAdapter = new ArrayAdapter<CharSequence>(this, R.layout.spinner_text, Splash.locationList );
        locationAdapter.setDropDownViewResource(R.layout.spinner_dropdown);
        locationSpinner.setAdapter(locationAdapter);

        processorSpinner = (Spinner) findViewById(R.id.processorSpinner);
        ArrayAdapter<CharSequence> processorAdapter = new ArrayAdapter<CharSequence>(this, R.layout.spinner_text, Splash.processorList );
        processorAdapter.setDropDownViewResource(R.layout.spinner_dropdown);
        processorSpinner.setAdapter(processorAdapter);



        if (Splash.currentUser.getImage() == null || Splash.currentUser.getImage().equals("")){

        }else {
            Glide.with(AddNewDeviceActivity.this).load(Splash.currentUser.getImage()).into(pic);
        }
    }

    public void homePage(View view){
        add_device.setBackground(null);
        LinearLayout home_logo = (LinearLayout) findViewById(R.id.home_logo);
        home_logo.setBackground(homepage_selected);
        if (Splash.sp.getBoolean("Admin", false)){
            redirectActivity(this, HomePageAdminActivity.class);
        }else {
            redirectActivity(this, HomePageActivity.class);
        }
    }

    public void goAddNewDevice(View view){
    }

    public void goUserProfile(View view){
        add_device.setBackground(null);
        LinearLayout frame = (LinearLayout) findViewById(R.id.frame);
        frame.setBackground(homepage_selected);
        redirectActivity(this, UserProfileActivity.class);}

    @SuppressLint("ResourceAsColor")
    public void logoutAction(View view){
        add_device.setBackground(null);
        log_out.setBackground(homepage_selected);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                Splash.sp.edit().putString("Cookie", "").apply();
                Activity AddNewDeviceActivity = AddNewDeviceActivity.this;
                redirectActivity(AddNewDeviceActivity, MainActivity.class);
            }
        }, logoutTimeOut);

    }

    public static void redirectActivity(Activity activity, Class aClass) {
        Intent intent = new Intent(activity,aClass);
        activity.startActivity(intent);
    }


    @Override
    public void onBackPressed() {
        //do nothing
    }

    public void readControllerCode(View view) {
        caller = 2;
        requestCamera();
    }

    public void readProcessorCode(View view) {
        caller = 1;
        requestCamera();
    }

    private void requestCamera() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
            startCamera();
        } else {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CAMERA)) {
                ActivityCompat.requestPermissions(AddNewDeviceActivity.this, new String[]{Manifest.permission.CAMERA}, PERMISSION_REQUEST_CAMERA);
            } else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, PERMISSION_REQUEST_CAMERA);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == PERMISSION_REQUEST_CAMERA) {
            if (grantResults.length == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                startCamera();
            } else {
                Toast.makeText(this, "Camera Permission Denied", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void startCamera() {
        add_device_viewFlipper.setInAnimation(null);
        add_device_viewFlipper.setOutAnimation(null);
        add_device_viewFlipper.setDisplayedChild(2);
        cameraProviderFuture.addListener(() -> {
            try {
                cameraProvider = cameraProviderFuture.get();
                bindCameraPreview();
            } catch (ExecutionException | InterruptedException e) {
                Toast.makeText(this, "Error starting camera " + e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }, ContextCompat.getMainExecutor(this));
    }

    private void bindCameraPreview() {
        previewView.setPreferredImplementationMode(PreviewView.ImplementationMode.SURFACE_VIEW);

        Preview preview = new Preview.Builder()
                .build();

        CameraSelector cameraSelector = new CameraSelector.Builder()
                .requireLensFacing(CameraSelector.LENS_FACING_BACK)
                .build();

        preview.setSurfaceProvider(previewView.createSurfaceProvider());

        ImageAnalysis imageAnalysis =
                new ImageAnalysis.Builder()
                        .setBackpressureStrategy(ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST)
                        .build();

        imageAnalysis.setAnalyzer(ContextCompat.getMainExecutor(this), new QRCodeImageAnalyzer(new QRCodeFoundListener() {
            @Override
            public void onQRCodeFound(String _qrCode) {
                qrCode = _qrCode;
                qrCodeFoundButton.setVisibility(View.VISIBLE);
            }

            @Override
            public void qrCodeNotFound() {
                qrCodeFoundButton.setVisibility(View.INVISIBLE);
            }
        }));

        Camera camera = cameraProvider.bindToLifecycle(this, cameraSelector, imageAnalysis, preview);
    }

    public void returnQR(View view) {
        switch (caller){
            case 2:
                controllerIdEditText.setText(qrCode);
                add_device_viewFlipper.setDisplayedChild(1);
                cameraProvider.unbindAll();
                return;
            case 1:
                processorIdEditText.setText(qrCode);
                add_device_viewFlipper.setDisplayedChild(0);
                cameraProvider.unbindAll();
                return;
            default:
                return;
        }
    }



    public void showProcessor(View view) {
        if (add_device_viewFlipper.getDisplayedChild() == 0){
            return;
        }
        processorTitle.setBackground(info_background);
        processorTitle.setTextColor(darkGreen);
        controllerTitle.setBackground(null);
        controllerTitle.setTextColor(white);
        add_device_viewFlipper.setInAnimation(this, android.R.anim.slide_in_left);
        add_device_viewFlipper.setOutAnimation(this, android.R.anim.slide_out_right);
        add_device_viewFlipper.setDisplayedChild(0);
    }



    public void showController(View view) {
        if (add_device_viewFlipper.getDisplayedChild() == 1){
            return;
        }
        controllerTitle.setBackground(info_background);
        controllerTitle.setTextColor(darkGreen);
        processorTitle.setBackground(null);
        processorTitle.setTextColor(white);
        add_device_viewFlipper.setInAnimation(this, R.anim.slide_in_right);
        add_device_viewFlipper.setOutAnimation(this, R.anim.slide_out_left);
        add_device_viewFlipper.setDisplayedChild(1);
    }


    public void goBack(View view) {
        switch (caller){
            case 2:
                add_device_viewFlipper.setDisplayedChild(1);
                cameraProvider.unbindAll();
                return;
            case 1:
                add_device_viewFlipper.setDisplayedChild(0);
                cameraProvider.unbindAll();
                return;
            default:
                return;
        }
    }

    public void connectController(View view) {
        if (controllerNameEditText.getText().toString().equals("")){
            Toast.makeText(getApplicationContext(), "Controller Name is Required", Toast.LENGTH_SHORT).show();
            return;
        }
        if (controllerIdEditText.getText().toString().equals("")){
            Toast.makeText(getApplicationContext(), "Controller Id is Required", Toast.LENGTH_SHORT).show();
            return;
        }

        String name = controllerNameEditText.getText().toString().trim();
        int controllerId = Integer.parseInt(controllerIdEditText.getText().toString());
        int typeId = Splash.types.get(typeSpinner.getSelectedItemPosition()).getId();
        int locationId = Splash.locations.get(locationSpinner.getSelectedItemPosition()).getId();
        int connectionId = Integer.parseInt(processorSpinner.getSelectedItem().toString());
        controllerData controller = new controllerData();
        controller.setName(name);
        controller.setTypeId(typeId);
        controller.setLocationId(locationId);
        controller.setControllerId(controllerId);
        controller.setProcessorId(connectionId);

        Call<controllerResponse> call = jsonApi.connectController(Splash.sp.getString("Cookie", ""), controller);
        call.enqueue(new Callback<controllerResponse>() {
            @Override
            public void onResponse(Call<controllerResponse> call, Response<controllerResponse> response) {
                if (!response.isSuccessful()){
                    ResponseBody loginResponse = response.errorBody();
                    try {
                        String message = loginResponse.string();
                        Toast.makeText(getApplicationContext(), message.substring(10, message.length()-2), Toast.LENGTH_SHORT).show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    return;
                }
                if (response.body().getDetail().equals("controller unit connected Successfully ")){
                    Toast.makeText(getApplicationContext(), response.body().getConnectionData().get(0).getName() + " added successfully", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<controllerResponse> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void connectProcessor(View view) {
        if (processorIdEditText.getText().toString().equals("")){
            Toast.makeText(getApplicationContext(), "Processor Id is Required", Toast.LENGTH_SHORT).show();
            return;
        }

        int processorId = Integer.parseInt(processorIdEditText.getText().toString());
        processorData processor = new processorData();
        processor.setProcessorId(processorId);

        Call<processorResponse> call = jsonApi.connectProcessor(Splash.sp.getString("Cookie", ""), processor);
        call.enqueue(new Callback<processorResponse>() {
            @Override
            public void onResponse(Call<processorResponse> call, Response<processorResponse> response) {
                if (!response.isSuccessful()){
                    ResponseBody loginResponse = response.errorBody();
                    try {
                        String message = loginResponse.string();
                        Toast.makeText(getApplicationContext(), message.substring(10, message.length()-2), Toast.LENGTH_SHORT).show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    return;
                }
                if (response.body().getDetail().equals("processor unit connected Successfully ")){
                    Toast.makeText(getApplicationContext(),"Processor added successfully", Toast.LENGTH_SHORT).show();

                    Call<processorResponse> call1 = jsonApi.getAllProcessors(Splash.sp.getString("Cookie", ""));
                    call1.enqueue(new Callback<processorResponse>() {
                        @Override
                        public void onResponse(Call<processorResponse> call, Response<processorResponse> response) {
                            if (!response.isSuccessful()){
                                ResponseBody loginResponse = response.errorBody();
                                try {
                                    String message = loginResponse.string();
                                    Toast.makeText(getApplicationContext(), message.substring(10, message.length()-2), Toast.LENGTH_SHORT).show();
                                    if (message.equals("\"error\": \"you dont have any connected processor units\"")){
                                    }
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                                return;
                            }
                            if(response.body().getDetail().equals("processor unit connection data collected Successfully ")){
                                List<processorData> processors = response.body().getConnectionData();
                                Splash.processorList = new String[processors.size()];
                                for (int i=0; i<processors.size(); i++){
                                    Splash.processorList[i] = String.valueOf(processors.get(i).getId());
                                }
                                finish();
                                overridePendingTransition(0, 0);
                                startActivity(getIntent());
                                overridePendingTransition(0, 0);
                            }
                        }

                        @Override
                        public void onFailure(Call<processorResponse> call, Throwable t) {
                            Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }

            @Override
            public void onFailure(Call<processorResponse> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}