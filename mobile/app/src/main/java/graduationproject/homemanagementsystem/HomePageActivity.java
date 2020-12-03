package graduationproject.homemanagementsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import java.io.Console;

import graduationproject.homemanagementsystem.dataClasses.userClass;

public class HomePageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);



        Intent intent = getIntent();
        String userEmail;
        if (intent.getStringExtra("email").equals("")){

        }else {
            userEmail = intent.getStringExtra("email");
            Log.e("Check user list", userEmail);
        }



    }
}