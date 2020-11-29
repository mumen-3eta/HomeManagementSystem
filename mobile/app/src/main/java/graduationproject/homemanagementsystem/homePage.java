package graduationproject.homemanagementsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import java.io.Console;

import graduationproject.homemanagementsystem.dataClasses.userClass;

public class homePage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        for (userClass user: MainActivity.users){
            Log.e("Check user list", user.toString());
        }
    }
}