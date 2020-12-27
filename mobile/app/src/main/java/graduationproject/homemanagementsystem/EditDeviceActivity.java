package graduationproject.homemanagementsystem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import graduationproject.homemanagementsystem.dataClasses.userClass;

public class EditDeviceActivity extends AppCompatActivity {

    private DrawerLayout homePageDrawer;
    private LinearLayout drawer_home;
    private LinearLayout drawer_logout;
    private Drawable drawable;
    private TextView userNameBar;
    private String userEmail;
    private EditText editTextTextPersonName3;
    private EditText editTextTextPersonName4;
    private EditText editTextTextPersonName5;
    private ImageView pic;
    private final int logoutTimeOut = 499;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_device);

        homePageDrawer = (DrawerLayout) findViewById(R.id.home_page_drawer_layout);
        drawer_logout = (LinearLayout) findViewById(R.id.drawer_logout);
        userNameBar = (TextView) findViewById(R.id.userNameBar);
        editTextTextPersonName3 = (EditText) findViewById(R.id.editTextTextPersonName3);
        editTextTextPersonName4 = (EditText) findViewById(R.id.editTextTextPersonName4);
        editTextTextPersonName5 = (EditText) findViewById(R.id.editTextTextPersonName5);
        drawable =  getResources().getDrawable(R.drawable.home_page_drawer_shoice);
        pic = (ImageView) findViewById(R.id.pic);

        Intent intent = getIntent();
        try {
            if (intent.getStringExtra("email").equals("")){

            }else {
                userEmail = intent.getStringExtra("email");
                Log.e("Check user list", userEmail);
                for (userClass user: MainActivity.users){
                    if (user.getUserEmail().equals(userEmail)){
                        userNameBar.setText(user.getUserName());
                        editTextTextPersonName3.setText(user.getUserEmail());
                        editTextTextPersonName4.setText(user.getUserName());
                        editTextTextPersonName5.setText(user.getUserPassword());
                        if (user.getUserPhoto() != null){
                            pic.setImageBitmap(user.getUserPhoto());
                        }
                    }
                }
            }
        }catch (NullPointerException e){
            //do nothing
        }
    }

    public void ClickMenu(View view){
        openDrawer(homePageDrawer);
    }

    public void hideDrawer(View view){ closeDrawer(homePageDrawer);}

    public void goHome(View view){ redirectActivity(this, HomePageActivity.class, userEmail);}

    public void goAddNewDevice(View view){ redirectActivity(this, AddNewDeviceActivity.class, userEmail);}

    public void goUserProfile(View view){ redirectActivity(this, UserProfileActivity.class, userEmail);}

    @SuppressLint("ResourceAsColor")
    public void logoutAction(View view){
        drawer_home.setBackground(new Drawable() {
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
                Activity homePageActivity = EditDeviceActivity.this;
                redirectActivity(homePageActivity, MainActivity.class);
            }
        }, logoutTimeOut);

    }

    public static void openDrawer(DrawerLayout drawerLayout) {
        drawerLayout.openDrawer(GravityCompat.START);
    }

    public static void closeDrawer(DrawerLayout drawerLayout){
        drawerLayout.closeDrawer(GravityCompat.START);
    }

    public static void redirectActivity(Activity activity, Class aClass) {
        Intent intent = new Intent(activity,aClass);
        activity.startActivity(intent);
    }

    public static void redirectActivity(Activity activity, Class aClass, String userEmail) {
        Intent intent = new Intent(activity,aClass);
        intent.putExtra("email", userEmail);
        activity.startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        //do nothing
    }
}