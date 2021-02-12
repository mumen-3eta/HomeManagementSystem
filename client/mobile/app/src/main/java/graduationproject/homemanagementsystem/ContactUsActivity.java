package graduationproject.homemanagementsystem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class ContactUsActivity extends AppCompatActivity {

    private LinearLayout drawer_contact;
    private DrawerLayout drawer_layout;

    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);

        drawer_layout = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer_contact = (LinearLayout) findViewById(R.id.drawer_contact);

        Drawable drawable =  getResources().getDrawable(R.drawable.drawer_shoice);

        drawer_contact.setBackground(drawable);
    }

    public void ClickMenu(View view){
        openDrawer(drawer_layout);
    }

    public void goHome(View view){ redirectActivity(this, MainActivity.class);}

    public void goLogin(View view){ redirectActivity(this, LoginActivity.class);}

    public void goSignUp(View view){ redirectActivity(this, SiginupActivity.class);}

    public void goContactUs(View view){ closeDrawer(drawer_layout);}

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
}