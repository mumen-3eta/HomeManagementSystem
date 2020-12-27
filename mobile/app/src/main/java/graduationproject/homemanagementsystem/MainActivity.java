package graduationproject.homemanagementsystem;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.ImageDecoder;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.LinearLayout;

import java.util.ArrayList;

import graduationproject.homemanagementsystem.dataClasses.deviceClass;
import graduationproject.homemanagementsystem.dataClasses.userClass;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout drawer_layout;
    private LinearLayout drawer_home;

    public static ArrayList<userClass> users = new ArrayList<>();

    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawer_layout = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer_home = (LinearLayout) findViewById(R.id.drawer_home);

        Drawable drawable =  getResources().getDrawable(R.drawable.drawer_shoice);

        drawer_home.setBackground(drawable);

        userClass newUser = new userClass("Mumen", "mumen.3eta@gmail.com", "Note12345");
        deviceClass deviceClass = new deviceClass("My TV", "Living Room", "TV");
        deviceClass deviceClass2 = new deviceClass("My light", "Living Room", "Light");
        newUser.getDevices().add(deviceClass);
        newUser.getDevices().add(deviceClass2);
        newUser.getDevices().add(deviceClass);
        newUser.getDevices().add(deviceClass2);
        newUser.getDevices().add(deviceClass);
        newUser.getDevices().add(deviceClass2);
        newUser.getDevices().add(deviceClass);
        newUser.getDevices().add(deviceClass2);
        newUser.getDevices().add(deviceClass);
        newUser.getDevices().add(deviceClass2);
        newUser.getDevices().add(deviceClass);
        newUser.getDevices().add(deviceClass2);
        users.add(newUser);
    }

    public void ClickMenu(View view){
        openDrawer(drawer_layout);
    }

    public void goHome(View view){ closeDrawer(drawer_layout);}

    public void goLogin(View view){ redirectActivity(this, LoginActivity.class);}

    public void goSignUp(View view){ redirectActivity(this, SiginupActivity.class);}

    public void goContactUs(View view){ redirectActivity(this, ContactUsActivity.class);}

    public void goAboutUs(View view){ redirectActivity(this, AboutUsActivity.class);}

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

    public final static Bitmap stringToBitmap(String in){
        byte[] bytes = Base64.decode(in, Base64.DEFAULT);
        return BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
    }

    @Override
    public void onBackPressed() {
        //do nothing
    }
}