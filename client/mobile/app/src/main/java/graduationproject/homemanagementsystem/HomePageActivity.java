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
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

import java.io.Console;

import graduationproject.homemanagementsystem.dataClasses.deviceAdapter;
import graduationproject.homemanagementsystem.dataClasses.deviceClass;
import graduationproject.homemanagementsystem.dataClasses.userClass;

public class HomePageActivity extends AppCompatActivity {

    private DrawerLayout homePageDrawer;
    private LinearLayout drawer_home;
    private LinearLayout drawer_logout;
    private Drawable drawable;
    private TextView userNameBar;
    private String userEmail;
    private ImageView pic;
    private final int logoutTimeOut = 499;
    private ListView listView;
    private deviceAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);


        homePageDrawer = (DrawerLayout) findViewById(R.id.home_page_drawer_layout);
        drawer_home = (LinearLayout) findViewById(R.id.drawer_home);
        drawer_logout = (LinearLayout) findViewById(R.id.drawer_logout);
        userNameBar = (TextView) findViewById(R.id.userNameBar);
        listView = (ListView) findViewById(R.id.listView);

        drawable =  getResources().getDrawable(R.drawable.home_page_drawer_shoice);
        pic = (ImageView) findViewById(R.id.pic);
        drawer_home.setBackground(drawable);

        Intent intent = getIntent();
        try {
            if (intent.getStringExtra("email").equals("")){

            }else {
                userEmail = intent.getStringExtra("email");
                Log.e("Check user list", userEmail);
                for (userClass user: MainActivity.users){
                    if (user.getUserEmail().equals(userEmail)){
                        userNameBar.setText(user.getUserName());
                        if (user.getUserPhoto() != null){
                            pic.setImageBitmap(user.getUserPhoto());
                        }
                        adapter = new deviceAdapter(this, user.getDevices());
                        listView.setAdapter(adapter);
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

    public void goHome(View view){ closeDrawer(homePageDrawer);}

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
                MainActivity.sp.edit().putBoolean("logged",false).apply();
                Activity homePageActivity = HomePageActivity.this;
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