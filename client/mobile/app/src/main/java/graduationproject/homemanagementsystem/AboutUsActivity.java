package graduationproject.homemanagementsystem;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.ScrollView;

public class AboutUsActivity extends AppCompatActivity {

    private LinearLayout drawer_about;
    private LinearLayout drawer_home;
    private LinearLayout drawer_sign;
    private Drawable drawable;
    private DrawerLayout drawer_layout;
    private HorizontalScrollView scrollView;
    private LinearLayout team_linear;
    private LinearLayout dot1;
    private LinearLayout dot2;
    private LinearLayout dot3;
    private LinearLayout dot4;
    private LinearLayout dot5;
    private Drawable selected_dot;
    private Drawable other_dot;
    private ScrollView mainScrollView;
    private EditText editTextTextEmailAddress;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);

        drawer_layout = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer_about = (LinearLayout) findViewById(R.id.drawer_about);
        drawer_home = (LinearLayout) findViewById(R.id.drawer_home);
        drawer_sign = (LinearLayout) findViewById(R.id.drawer_sign);
        scrollView = (HorizontalScrollView) findViewById(R.id.team_members);
        team_linear = (LinearLayout) findViewById(R.id.team_linear);
        dot1 = (LinearLayout) findViewById(R.id.dot_1);
        dot2 = (LinearLayout) findViewById(R.id.dot_2);
        dot3 = (LinearLayout) findViewById(R.id.dot_3);
        dot4 = (LinearLayout) findViewById(R.id.dot_4);
        dot5 = (LinearLayout) findViewById(R.id.dot_5);
        other_dot = getResources().getDrawable(R.drawable.dot_shape);
        selected_dot = getResources().getDrawable(R.drawable.dot_shape_selected);
        mainScrollView = (ScrollView) findViewById(R.id.about_us_scroll);
        editTextTextEmailAddress = (EditText) findViewById(R.id.editTextTextEmailAddress);





        scrollView.post(new Runnable() {
            @Override
            public void run() {
                scrollView.smoothScrollTo((int)team_linear.getChildAt(1).getX() - 17, 0);
                dot2.setScaleX((float) 1.3);
                dot2.setScaleY((float) 1.3);

                dot1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        setDot1();
                        scrollView.smoothScrollTo((int)team_linear.getChildAt(0).getX(), 0);
                    }
                });

                dot2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        setDot2();
                        scrollView.smoothScrollTo((int)team_linear.getChildAt(1).getX() - 17, 0);
                    }
                });

                dot3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        setDot3();
                        scrollView.smoothScrollTo((int)team_linear.getChildAt(2).getX() - 17, 0);
                    }
                });

                dot4.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        setDot4();
                        scrollView.smoothScrollTo((int)team_linear.getChildAt(3).getX() - 17, 0);
                    }
                });

                dot5.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        setDot5();
                        scrollView.smoothScrollTo((int)team_linear.getChildAt(4).getX(), 0);
                    }
                });

                scrollView.setOnScrollChangeListener(new View.OnScrollChangeListener() {
                    @Override
                    public void onScrollChange(View view, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                        if (scrollX <= team_linear.getChildAt(1).getX() - 600){
                            setDot1();
                        } else if (scrollX <= team_linear.getChildAt(2).getX() - 600){
                            setDot2();
                        } else if (scrollX <= team_linear.getChildAt(3).getX() - 600){
                            setDot3();
                        } else if (scrollX <= team_linear.getChildAt(4).getX() - 600){
                            setDot4();
                        }else {
                            setDot5();
                        }

                    }
                });
            }
        });




        drawable =  getResources().getDrawable(R.drawable.drawer_shoice);

        drawer_about.setBackground(drawable);
    }

    public void setDot1(){
        dot1.setBackground(selected_dot);
        dot1.setScaleX((float) 1.4);
        dot1.setScaleY((float) 1.4);
        dot2.setBackground(other_dot);
        dot2.setScaleX((float) 1);
        dot2.setScaleY((float) 1);
        dot3.setBackground(other_dot);
        dot3.setScaleX((float) 1);
        dot3.setScaleY((float) 1);
        dot4.setBackground(other_dot);
        dot4.setScaleX((float) 1);
        dot4.setScaleY((float) 1);
        dot5.setBackground(other_dot);
        dot5.setScaleX((float) 1);
        dot5.setScaleY((float) 1);
    }

    public void setDot2(){
        dot1.setBackground(other_dot);
        dot1.setScaleX((float) 1);
        dot1.setScaleY((float) 1);
        dot2.setBackground(selected_dot);
        dot2.setScaleX((float) 1.4);
        dot2.setScaleY((float) 1.4);
        dot3.setBackground(other_dot);
        dot3.setScaleX((float) 1);
        dot3.setScaleY((float) 1);
        dot4.setBackground(other_dot);
        dot4.setScaleX((float) 1);
        dot4.setScaleY((float) 1);
        dot5.setBackground(other_dot);
        dot5.setScaleX((float) 1);
        dot5.setScaleY((float) 1);
    }

    public void setDot3(){
        dot1.setBackground(other_dot);
        dot1.setScaleX((float) 1);
        dot1.setScaleY((float) 1);
        dot2.setBackground(other_dot);
        dot2.setScaleX((float) 1);
        dot2.setScaleY((float) 1);
        dot3.setBackground(selected_dot);
        dot3.setScaleX((float) 1.4);
        dot3.setScaleY((float) 1.4);
        dot4.setBackground(other_dot);
        dot4.setScaleX((float) 1);
        dot4.setScaleY((float) 1);
        dot5.setBackground(other_dot);
        dot5.setScaleX((float) 1);
        dot5.setScaleY((float) 1);
    }

    public void setDot4(){
        dot1.setBackground(other_dot);
        dot1.setScaleX((float) 1);
        dot1.setScaleY((float) 1);
        dot2.setBackground(other_dot);
        dot2.setScaleX((float) 1);
        dot2.setScaleY((float) 1);
        dot3.setBackground(other_dot);
        dot3.setScaleX((float) 1);
        dot3.setScaleY((float) 1);
        dot4.setBackground(selected_dot);
        dot4.setScaleX((float) 1.4);
        dot4.setScaleY((float) 1.4);
        dot5.setBackground(other_dot);
        dot5.setScaleX((float) 1);
        dot5.setScaleY((float) 1);
    }

    public void setDot5(){
        dot1.setBackground(other_dot);
        dot1.setScaleX((float) 1);
        dot1.setScaleY((float) 1);
        dot2.setBackground(other_dot);
        dot2.setScaleX((float) 1);
        dot2.setScaleY((float) 1);
        dot3.setBackground(other_dot);
        dot3.setScaleX((float) 1);
        dot3.setScaleY((float) 1);
        dot4.setBackground(other_dot);
        dot4.setScaleX((float) 1);
        dot4.setScaleY((float) 1);
        dot5.setBackground(selected_dot);
        dot5.setScaleX((float) 1.4);
        dot5.setScaleY((float) 1.4);
    }


    public void ClickMenu(View view){
        openDrawer(drawer_layout);
    }

    public void goHome(View view){
        drawer_about.setBackground(null);
        drawer_home.setBackground(drawable);
        redirectActivity(this, MainActivity.class);
    }

    public void goSignUp(View view){
        drawer_about.setBackground(null);
        drawer_sign.setBackground(drawable);
        redirectActivity(this, SignUpActivity.class);
    }

    public void goAboutUs(View view){ closeDrawer(drawer_layout);}

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

    @Override
    public void onBackPressed() {
        //do nothing
    }
}