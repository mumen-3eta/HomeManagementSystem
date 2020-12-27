package graduationproject.homemanagementsystem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.ImageDecoder;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageActivity;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import graduationproject.homemanagementsystem.dataClasses.userClass;
import graduationproject.homemanagementsystem.utility.ImagePicker;

public class UserProfileActivity extends AppCompatActivity {

    private DrawerLayout homePageDrawer;
    private LinearLayout drawer_profile;
    private LinearLayout drawer_logout;
    private Drawable drawable;
    private TextView userNameBar;
    private EditText profileEmailTextField;
    private EditText profileNameTextField;
    private EditText profilePasswordTextField;
    private ImageView profilePhoto;
    private ImageView pic;
    private String userEmail;
    private final int logoutTimeOut = 499;
    private boolean isVisible = true;
    private userClass currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        homePageDrawer = (DrawerLayout) findViewById(R.id.home_page_drawer_layout);
        drawer_profile = (LinearLayout) findViewById(R.id.drawer_profile);
        drawer_logout = (LinearLayout) findViewById(R.id.drawer_logout);
        userNameBar = (TextView) findViewById(R.id.userNameBar);
        profileEmailTextField = (EditText) findViewById(R.id.profileEmailTextField);
        profileNameTextField = (EditText) findViewById(R.id.profileNameTextField);
        profilePasswordTextField = (EditText) findViewById(R.id.profilePasswordTextField);
        profilePhoto = (ImageView) findViewById(R.id.profilePhoto);
        pic = (ImageView) findViewById(R.id.pic);

        drawable = getResources().getDrawable(R.drawable.home_page_drawer_shoice);

        drawer_profile.setBackground(drawable);

        Intent intent = getIntent();
        try {
            if (intent.getStringExtra("email").equals("")) {

            } else {
                userEmail = intent.getStringExtra("email");
                Log.e("Check user list", userEmail);
                for (userClass user : MainActivity.users) {
                    if (user.getUserEmail().equals(userEmail)) {
                        currentUser = user;
                        userNameBar.setText(user.getUserName());
                        profileEmailTextField.setText(user.getUserEmail());
                        profileNameTextField.setText(user.getUserName());
                        profilePasswordTextField.setText(user.getUserPassword());
                        if (user.getUserPhoto() != null) {
                            pic.setImageBitmap(user.getUserPhoto());
                            profilePhoto.setImageBitmap(user.getUserPhoto());
                        }
                    }
                }
            }
        } catch (NullPointerException e) {
            //do nothing
        }
    }

    public void ClickMenu(View view) {
        openDrawer(homePageDrawer);
    }

    public void hideDrawer(View view) {
        closeDrawer(homePageDrawer);
    }

    public void goHome(View view) {
        redirectActivity(this, HomePageActivity.class, userEmail);
    }

    public void goAddNewDevice(View view) {
        redirectActivity(this, AddNewDeviceActivity.class, userEmail);
    }

    public void goUserProfile(View view) {
        closeDrawer(homePageDrawer);
    }

    public void viewPassword(View view) {
        if (isVisible) {
            profilePasswordTextField.setTransformationMethod(PasswordTransformationMethod.getInstance());
            isVisible = false;
        } else {
            profilePasswordTextField.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            isVisible = true;
        }
    }

    public void updateProfile(View view) {
        boolean isChangedEmail = !(profileEmailTextField.getText().toString().trim().equals(currentUser.getUserEmail()));
        boolean isChangedName = !(profileNameTextField.getText().toString().trim().equals(currentUser.getUserName()));
        boolean isChangedPassword = !(profilePasswordTextField.getText().toString().trim().equals(currentUser.getUserPassword()));
        Toast toast = new Toast(this);
        if (!isChangedEmail && !isChangedName && !isChangedPassword) {
            toast.makeText(this, "no changes to profile", Toast.LENGTH_SHORT).show();
            redirectActivity(this, HomePageActivity.class, currentUser.getUserEmail());
        } else {
            boolean validUserEmail = SiginupActivity.checkUserEmail(profileEmailTextField.getText().toString());
            boolean isUsedEmail = isUsedEmail(profileEmailTextField.getText().toString().trim(), currentUser.getUserEmail());
            boolean validUserName = SiginupActivity.checkUserName(profileNameTextField.getText().toString().trim());
            String passwordToast = SiginupActivity.checkUserPassword(profilePasswordTextField.getText().toString());
            if (validUserEmail && !isUsedEmail && validUserName && passwordToast.equals("valid")) {
                currentUser.setUserEmail(profileEmailTextField.getText().toString().trim());
                currentUser.setUserName(profileNameTextField.getText().toString().trim());
                currentUser.setUserPassword(profilePasswordTextField.getText().toString());
                toast.makeText(this, "profile updated successfully", Toast.LENGTH_SHORT).show();
                redirectActivity(this, HomePageActivity.class, currentUser.getUserEmail());
            } else if (!validUserEmail) {
                toast.makeText(this, "new email is not valid", Toast.LENGTH_SHORT).show();
            } else if (isUsedEmail) {
                toast.makeText(this, "new email already exist", Toast.LENGTH_SHORT).show();
            } else if (!validUserName) {
                toast.makeText(this, "new name is not valid", Toast.LENGTH_SHORT).show();
            } else if (!passwordToast.equals("valid")) {
                toast.makeText(this, passwordToast, Toast.LENGTH_SHORT).show();
            } else {
                toast.makeText(this, "something went wrong, please try again", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @SuppressLint("ResourceAsColor")
    public void logoutAction(View view) {
        drawer_profile.setBackground(new Drawable() {
            @Override
            public void draw(@NonNull Canvas canvas) {

            }

            @Override
            public void setAlpha(int i) {

            }

            @Override
            public void setColorFilter(@Nullable ColorFilter colorFilter) {

            }

            @SuppressLint("WrongConstant")
            @Override
            public int getOpacity() {
                return 0;
            }
        });
        drawer_logout.setBackground(drawable);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                Activity UserProfileActivity = UserProfileActivity.this;
                redirectActivity(UserProfileActivity, MainActivity.class);
            }
        }, logoutTimeOut);

    }

    public static void openDrawer(DrawerLayout drawerLayout) {
        drawerLayout.openDrawer(GravityCompat.START);
    }

    public static void closeDrawer(DrawerLayout drawerLayout) {
        drawerLayout.closeDrawer(GravityCompat.START);
    }

    public static void redirectActivity(Activity activity, Class aClass) {
        Intent intent = new Intent(activity, aClass);
        activity.startActivity(intent);
    }

    public static void redirectActivity(Activity activity, Class aClass, String userEmail) {
        Intent intent = new Intent(activity, aClass);
        intent.putExtra("email", userEmail);
        activity.startActivity(intent);
    }

    public void onPickImage(View view) {
        CropImage.activity()
                .setActivityTitle("Crop Photo")
                .setGuidelines(CropImageView.Guidelines.ON)
                .setAspectRatio(1, 1)
                .start(this);
        /*// Create intent for picking a photo from the gallery
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

        // If you call startActivityForResult() using an intent that no app can handle, your app will crash.
        // So as long as the result is not null, it's safe to use the intent.
        if (intent.resolveActivity(getPackageManager()) != null) {
            // Bring up gallery to select a photo
            startActivityForResult(intent, PICK_PHOTO_CODE);
        }*/
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

        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            Uri resultUri = result.getUri();
            // Load the image located at photoUri into selectedImage
            Bitmap selectedImage = loadFromUri(resultUri);
            // Load the selected image into a preview\
            profilePhoto.setImageBitmap(selectedImage);
            pic.setImageBitmap(selectedImage);
            currentUser.setUserPhoto(selectedImage);
        }
        /*if ((data != null) && requestCode == PICK_PHOTO_CODE) {
            Uri photoUri = data.getData();


            // Load the image located at photoUri into selectedImage
            Bitmap selectedImage = loadFromUri(photoUri);
            // Load the selected image into a preview\
            profilePhoto.setImageBitmap(selectedImage);
            currentUser.setUserPhoto(selectedImage);
        }*/
    }

    public final static String bitmapToString(Bitmap in) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        in.compress(Bitmap.CompressFormat.PNG, 100, bytes);
        return Base64.encodeToString(bytes.toByteArray(), Base64.DEFAULT);
    }

    private boolean isUsedEmail(String newEmail, String userEmail) {
        if (!newEmail.equals(userEmail)) {
            for (userClass user : MainActivity.users) {
                if (user.getUserEmail().equals(newEmail)) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public void onBackPressed() {
        //do nothing
    }
}