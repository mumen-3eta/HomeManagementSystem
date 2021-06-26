package graduationproject.homemanagementsystem;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.interfaces.ItemClickListener;
import com.denzcoskun.imageslider.models.SlideModel;

import java.util.ArrayList;
import java.util.List;


public class AboutUsActivity extends AppCompatActivity {

    private LinearLayout drawer_about;
    private LinearLayout drawer_home;
    private LinearLayout drawer_sign;
    private Drawable drawable;
    private DrawerLayout drawer_layout;
    private ImageSlider imageSlider;

    @RequiresApi(api = Build.VERSION_CODES.O)
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
        setContentView(R.layout.activity_about_us);

        drawable = ContextCompat.getDrawable(getApplicationContext(), R.drawable.drawer_shoice);
        drawer_layout = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer_about = (LinearLayout) findViewById(R.id.drawer_about);
        drawer_about.setBackground(drawable);
        drawer_home = (LinearLayout) findViewById(R.id.drawer_home);
        drawer_sign = (LinearLayout) findViewById(R.id.drawer_sign);

        imageSlider = findViewById(R.id.slider);
        List<SlideModel> slideModels = new ArrayList<>();
        slideModels.add(new SlideModel(R.drawable.mumen2, "Mumen\nAndroid Developer"));
        slideModels.add(new SlideModel(R.drawable.osama, "Osama\nBackend Developer"));
        slideModels.add(new SlideModel(R.drawable.ahmed,"Ahemd\nWeb Developer"));
        slideModels.add(new SlideModel(R.drawable.aboobada,"Ahmed\nHardware Developer"));
        imageSlider.setImageList(slideModels, false);
        

    }



    public void ClickMenu(View view) {
        openDrawer(drawer_layout);
    }

    public void goHome(View view) {
        drawer_about.setBackground(null);
        drawer_home.setBackground(drawable);
        redirectActivity(this, MainActivity.class);
    }

    public void goSignUp(View view) {
        drawer_about.setBackground(null);
        drawer_sign.setBackground(drawable);
        redirectActivity(this, SignUpActivity.class);
    }

    public void goAboutUs(View view) {
        closeDrawer(drawer_layout);
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

    @Override
    public void onBackPressed() {
        //do nothing
    }


}