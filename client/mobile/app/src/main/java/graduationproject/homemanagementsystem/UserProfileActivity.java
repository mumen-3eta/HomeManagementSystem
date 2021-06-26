package graduationproject.homemanagementsystem;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.content.ContextCompat;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.ImageDecoder;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.io.IOException;

import graduationproject.homemanagementsystem.dataClasses.JsonApi;
import graduationproject.homemanagementsystem.dataClasses.data;
import graduationproject.homemanagementsystem.dataClasses.getProfile;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UserProfileActivity extends AppCompatActivity {

    private LinearLayout log_out;
    private ImageView profilePhoto;
    private ImageView pic;
    private String userEmail;
    private final int logoutTimeOut = 499;
    private boolean isVisible = true;
    private boolean isVisibleNew = true;
    private LinearLayout frame;
    private Drawable homepage_selected;
    private Drawable info_background;
    private ViewFlipper view_flipper;
    private TextView basic_info_text;
    private TextView login_info_text;
    private TextView NewPasswordTextField;
    private TextView ConfirmPasswordTextField;
    private Toast toast;
    private EditText firstNameTextField;
    private EditText lastNameTextField;
    private EditText phoneNumberTextField;
    private EditText userNameTextField;
    private data userData;
    private ProgressBar progressBar;
    private Button updateBasicInfoButton;
    private StorageReference storageReference;
    private TextView darkMode;
    private Switch darkModeSwitch;
    private boolean dark;
    private int darkGreen;
    private int white;


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
                darkGreen = getResources().getColor(R.color.white);
                white = getResources().getColor(R.color.black);
                info_background = ContextCompat.getDrawable(getApplicationContext(), R.drawable.info_background_dark);
                dark = true;
            }else {
                setTheme(R.style.AppTheme);
                info_background = ContextCompat.getDrawable(getApplicationContext(), R.drawable.info_background);
                darkGreen = getResources().getColor(R.color.darkGreen);
                white = getResources().getColor(R.color.white);
                dark = false;
            }
        }else {
            setTheme(R.style.AppTheme);
            info_background = ContextCompat.getDrawable(getApplicationContext(), R.drawable.info_background);
            darkGreen = getResources().getColor(R.color.darkGreen);
            white = getResources().getColor(R.color.white);
            dark = false;
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);


        log_out = (LinearLayout) findViewById(R.id.log_out);
        profilePhoto = (ImageView) findViewById(R.id.profilePhoto);
        pic = (ImageView) findViewById(R.id.pic);
        view_flipper = (ViewFlipper) findViewById(R.id.view_flipper);
        basic_info_text = (TextView) findViewById(R.id.basic_info_text);
        login_info_text = (TextView) findViewById(R.id.login_info_text);
        NewPasswordTextField = (TextView) findViewById(R.id.NewPasswordTextField);
        ConfirmPasswordTextField = (TextView) findViewById(R.id.ConfirmPasswordTextField);
        homepage_selected = ContextCompat.getDrawable(getApplicationContext(), R.drawable.homepage_selected);
        frame = (LinearLayout) findViewById(R.id.frame);
        frame.setBackground(homepage_selected);
        firstNameTextField = (EditText) findViewById(R.id.firstNameTextField);
        lastNameTextField = (EditText) findViewById(R.id.lastNameTextField);
        phoneNumberTextField = (EditText) findViewById(R.id.phoneNumberTextField);
        userNameTextField = (EditText) findViewById(R.id.userNameTextField);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        updateBasicInfoButton = (Button) findViewById(R.id.updateBasicInfoButton);
        darkMode = (TextView) findViewById(R.id.darkMode);
        darkModeSwitch = (Switch) findViewById(R.id.darkModeSwitch);
        darkModeSwitch.setChecked(dark);
        storageReference = FirebaseStorage.getInstance().getReference("/User/Profile");

        Intent intent = getIntent();
        if (intent.hasExtra("child")){
            darkMode.setBackground(info_background);
            darkMode.setTextColor(darkGreen);
            basic_info_text.setBackground(null);
            basic_info_text.setTextColor(white);
            view_flipper.setDisplayedChild(intent.getIntExtra("child", -1));
        }else {
            darkMode.setBackground(null);
            darkMode.setTextColor(white);
            basic_info_text.setBackground(info_background);
            basic_info_text.setTextColor(darkGreen);
        }
        login_info_text.setBackground(null);
        login_info_text.setTextColor(white);



        darkModeSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                try {
                    if (isChecked){
                        setTheme(R.style.DarkTheme);
                        Splash.sp.edit().putString("Theme", "dark").apply();
                    }else {
                        setTheme(R.style.AppTheme);
                        Splash.sp.edit().putString("Theme", "light").apply();
                    }
                    Intent intent = new Intent(getApplicationContext(), UserProfileActivity.class);
                    intent.putExtra("child",2);
                    startActivity(intent);
                }catch (Exception e) {
                    Intent intent = new Intent(getApplicationContext(), UserProfileActivity.class);
                    intent.putExtra("child",2);
                    startActivity(intent);

                }

            }
        });

        updateBasicInfoButton.setVisibility(View.INVISIBLE);
        progressBar.setVisibility(View.VISIBLE);
        Call<getProfile> call = jsonApi.getProfileData(Splash.sp.getString("Cookie", ""));
        call.enqueue(new Callback<getProfile>() {
            @Override
            public void onResponse(Call<getProfile> call, Response<getProfile> response) {
                if (!response.isSuccessful()) {
                    ResponseBody loginResponse = response.errorBody();
                    try {
                        String message = loginResponse.string();
                        toast.makeText(UserProfileActivity.this, message.substring(10, message.length() - 2), Toast.LENGTH_SHORT).show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    progressBar.setVisibility(View.INVISIBLE);
                    updateBasicInfoButton.setVisibility(View.VISIBLE);
                    return;
                }
                userData = response.body().getProfileData().get(0);
                userEmail = userData.getEmail().trim();
                if (!(userData.getFirst_name() == null)) {
                    firstNameTextField.setText(userData.getFirst_name().trim());
                }
                if (!(userData.getLast_name() == null)) {
                    lastNameTextField.setText(userData.getLast_name().trim());
                }
                if (!(userData.getMobile() == null)) {
                    phoneNumberTextField.setText(userData.getMobile().trim());
                }
                userNameTextField.setText(userData.getUser_name().trim());
                progressBar.setVisibility(View.INVISIBLE);
                updateBasicInfoButton.setVisibility(View.VISIBLE);

            }

            @Override
            public void onFailure(Call<getProfile> call, Throwable t) {
                toast.makeText(UserProfileActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                progressBar.setVisibility(View.INVISIBLE);
                updateBasicInfoButton.setVisibility(View.VISIBLE);
            }
        });

        if (Splash.currentUser.getImage() == null || Splash.currentUser.getImage().equals("")) {

        } else {
            Glide.with(getApplicationContext()).load(Splash.currentUser.getImage()).into(profilePhoto);
            Glide.with(getApplicationContext()).load(Splash.currentUser.getImage()).into(pic);
        }
    }

    private void restartApp() {
        Intent intent = new Intent(getApplicationContext(), UserProfileActivity.class);
        intent.putExtra("child",2);
        startActivity(intent);
        finish();
    }

    public void homePage(View view) {
        frame.setBackground(null);
        LinearLayout home_logo = (LinearLayout) findViewById(R.id.home_logo);
        home_logo.setBackground(homepage_selected);
        if (Splash.sp.getBoolean("Admin", false)){
            redirectActivity(this, HomePageAdminActivity.class);
        }else {
            redirectActivity(this, HomePageActivity.class);
        }
    }

    public void goAddNewDevice(View view) {
        frame.setBackground(null);
        LinearLayout add_device = (LinearLayout) findViewById(R.id.add_device);
        add_device.setBackground(homepage_selected);
        redirectActivity(this, AddNewDeviceActivity.class);
    }

    public void goUserProfile(View view) {
    }

    public void viewPassword(View view) {
        if (isVisible) {
            NewPasswordTextField.setTransformationMethod(PasswordTransformationMethod.getInstance());
            isVisible = false;
        } else {
            NewPasswordTextField.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            isVisible = true;
        }
    }

    public void viewNewPassword(View view) {
        if (isVisibleNew) {
            ConfirmPasswordTextField.setTransformationMethod(PasswordTransformationMethod.getInstance());
            isVisibleNew = false;
        } else {
            ConfirmPasswordTextField.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            isVisibleNew = true;
        }
    }

    @SuppressLint("ResourceAsColor")
    public void logoutAction(View view) {
        frame.setBackground(null);
        log_out.setBackground(homepage_selected);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                Splash.sp.edit().putString("Cookie", "").apply();
                Activity UserProfileActivity = UserProfileActivity.this;
                redirectActivity(UserProfileActivity, MainActivity.class);
            }
        }, logoutTimeOut);
    }

    public static void redirectActivity(Activity activity, Class aClass) {
        Intent intent = new Intent(activity, aClass);
        activity.startActivity(intent);
    }

    public void onPickImage(View view) {
        CropImage.activity()
                .setActivityTitle("Crop Photo")
                .setGuidelines(CropImageView.Guidelines.ON)
                .setAspectRatio(1, 1)
                .setFixAspectRatio(true)
                .setAutoZoomEnabled(true)
                .start(this);
    }

    public Bitmap loadFromUri(Uri photoUri) {
        Bitmap image = null;
        try {
            // check version of Android on device
            if (Build.VERSION.SDK_INT > 27) {
                // on newer versions of Android, use the new decodeBitmap method
                ImageDecoder.Source source = ImageDecoder.createSource(this.getContentResolver(), photoUri);
                image = ImageDecoder.decodeBitmap(source);
            } else {
                // support older versions of Android by using getBitmap
                image = MediaStore.Images.Media.getBitmap(this.getContentResolver(), photoUri);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {
            if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
                CropImage.ActivityResult result = CropImage.getActivityResult(data);
                Uri resultUri = result.getUri();
                uploadProfilePhoto(resultUri);
                // Load the image located at photoUri into selectedImage
                Bitmap selectedImage = loadFromUri(resultUri);
                // Load the selected image into a preview\
                profilePhoto.setImageBitmap(selectedImage);
                pic.setImageBitmap(selectedImage);
            }
        } catch (Exception e) {
            return;
        }
    }


    private void uploadProfilePhoto(Uri resultUri) {

        final ProgressDialog pd = new ProgressDialog(this);
        pd.setTitle("Uploading Photo...");
        pd.show();

        // Create a reference to 'images/mountains.jpg'
        StorageReference mountainImagesRef = storageReference.child(userEmail + ".jpg");
        mountainImagesRef.putFile(resultUri)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        pd.dismiss();
                        toast.makeText(UserProfileActivity.this, "Photo uploaded", Toast.LENGTH_LONG).show();
                        Task<Uri> firebaseUri = taskSnapshot.getStorage().getDownloadUrl();
                        firebaseUri.addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {

                                String url = uri.toString();
                                updatePhotoUrl(url);
                                Log.e("url", url + "   " + url.length());
                            }
                        });
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        pd.dismiss();
                        toast.makeText(UserProfileActivity.this, "Failed to upload", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {
                        double progressPercent = (100.00 * snapshot.getBytesTransferred() / snapshot.getTotalByteCount());
                        pd.setMessage("Progress " + (int) progressPercent + "%");
                    }
                });

    }

    private void updatePhotoUrl(String url) {
        data userData = new data();
        userData.setImage(url);
        Call<getProfile> call = jsonApi.updateProfile(Splash.sp.getString("Cookie", ""), userData);
        call.enqueue(new Callback<getProfile>() {
            @Override
            public void onResponse(Call<getProfile> call, Response<getProfile> response) {
                if (!response.isSuccessful()) {
                    ResponseBody loginResponse = response.errorBody();
                    try {
                        String message = loginResponse.string();
                        Log.e("e", message);
                        toast.makeText(UserProfileActivity.this, message.substring(10, message.length() - 2), Toast.LENGTH_SHORT).show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    progressBar.setVisibility(View.INVISIBLE);
                    updateBasicInfoButton.setVisibility(View.VISIBLE);
                    return;
                }
                getProfile updateResponse = response.body();
                if (updateResponse.getDetail().equals("Data Successfully updated")) {
                    toast.makeText(UserProfileActivity.this, "Profile Successfully updated", Toast.LENGTH_SHORT).show();
                    Splash.currentUser = updateResponse.getProfileData().get(0);
                }
                progressBar.setVisibility(View.INVISIBLE);
                updateBasicInfoButton.setVisibility(View.VISIBLE);
            }

            @Override
            public void onFailure(Call<getProfile> call, Throwable t) {
                toast.makeText(UserProfileActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.e("t", t.getMessage());
                progressBar.setVisibility(View.INVISIBLE);
                updateBasicInfoButton.setVisibility(View.VISIBLE);
            }
        });
    }

    @Override
    public void onBackPressed() {
        //do nothing
    }



    public void showLoginInfo(View view) {
        if (view_flipper.getDisplayedChild() == 1) {
            return;
        }
        login_info_text.setBackground(info_background);
        login_info_text.setTextColor(darkGreen);
        basic_info_text.setBackground(null);
        basic_info_text.setTextColor(white);
        darkMode.setBackground(null);
        darkMode.setTextColor(white);
        if (view_flipper.getDisplayedChild() == 0) {
            view_flipper.setInAnimation(this, R.anim.slide_in_right);
            view_flipper.setOutAnimation(this, R.anim.slide_out_left);
        }else {
            view_flipper.setInAnimation(this, android.R.anim.slide_in_left);
            view_flipper.setOutAnimation(this, android.R.anim.slide_out_right);
        }
        view_flipper.setDisplayedChild(1);
    }



    public void showBasicInfo(View view) {
        if (view_flipper.getDisplayedChild() == 0) {
            return;
        }
        basic_info_text.setBackground(info_background);
        basic_info_text.setTextColor(darkGreen);
        login_info_text.setBackground(null);
        login_info_text.setTextColor(white);
        darkMode.setBackground(null);
        darkMode.setTextColor(white);
        view_flipper.setInAnimation(this, android.R.anim.slide_in_left);
        view_flipper.setOutAnimation(this, android.R.anim.slide_out_right);
        view_flipper.setDisplayedChild(0);
    }



    public void showDarkMode(View view) {
        if (view_flipper.getDisplayedChild() == 2) {
            return;
        }
        darkMode.setBackground(info_background);
        darkMode.setTextColor(darkGreen);
        basic_info_text.setBackground(null);
        basic_info_text.setTextColor(white);
        login_info_text.setBackground(null);
        login_info_text.setTextColor(white);
        view_flipper.setInAnimation(this, R.anim.slide_in_right);
        view_flipper.setOutAnimation(this, R.anim.slide_out_left);
        view_flipper.setDisplayedChild(2);
    }

    public void updateBasicInfo(View view) {

        final data updateData = new data();
        if (!firstNameTextField.getText().toString().trim().equals("")) {
            updateData.setFirstName(firstNameTextField.getText().toString().trim());
        }
        if (!lastNameTextField.getText().toString().trim().equals("")) {
            updateData.setLastName(lastNameTextField.getText().toString().trim());
        }
        if (!phoneNumberTextField.getText().toString().trim().equals("")) {
            updateData.setMobile(phoneNumberTextField.getText().toString().trim());
        }
        if (!userData.getUser_name().equals(userNameTextField.getText().toString().trim())) {
            updateData.setUserName(userNameTextField.getText().toString().trim());
        }
        if (checkNewPassword()) {
            updateData.setPassword(NewPasswordTextField.getText().toString());
        } else {

        }

        updateBasicInfoButton.setVisibility(View.INVISIBLE);
        progressBar.setVisibility(View.VISIBLE);

        Call<getProfile> call = jsonApi.updateProfile(Splash.sp.getString("Cookie", ""), updateData);
        call.enqueue(new Callback<getProfile>() {
            @Override
            public void onResponse(Call<getProfile> call, Response<getProfile> response) {
                if (!response.isSuccessful()) {
                    ResponseBody loginResponse = response.errorBody();
                    try {
                        String message = loginResponse.string();
                        toast.makeText(UserProfileActivity.this, message.substring(10, message.length() - 2), Toast.LENGTH_SHORT).show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    progressBar.setVisibility(View.INVISIBLE);
                    updateBasicInfoButton.setVisibility(View.VISIBLE);
                    return;
                }
                getProfile updateResponse = response.body();
                if (updateResponse.getDetail().equals("Data Successfully updated")) {
                    toast.makeText(UserProfileActivity.this, "Profile Successfully updated", Toast.LENGTH_SHORT).show();
                    Splash.currentUser = updateResponse.getProfileData().get(0);
                }
                progressBar.setVisibility(View.INVISIBLE);
                updateBasicInfoButton.setVisibility(View.VISIBLE);
            }

            @Override
            public void onFailure(Call<getProfile> call, Throwable t) {
                toast.makeText(UserProfileActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                progressBar.setVisibility(View.INVISIBLE);
                updateBasicInfoButton.setVisibility(View.VISIBLE);
            }
        });
    }

    private boolean checkNewPassword() {
        if (NewPasswordTextField.getText().toString().isEmpty() && ConfirmPasswordTextField.getText().toString().isEmpty()) {
            return false;
        }

        if (NewPasswordTextField.getText().toString().equals(ConfirmPasswordTextField.getText().toString())) {
            return true;
        } else {
            toast.makeText(UserProfileActivity.this, "Passwords Do Not Match", Toast.LENGTH_SHORT);
            return false;
        }
    }


}