package graduationproject.homemanagementsystem.dataClasses;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;

import graduationproject.homemanagementsystem.R;
import graduationproject.homemanagementsystem.Splash;
import graduationproject.homemanagementsystem.ViewAllLocationsActivity;
import graduationproject.homemanagementsystem.ViewAllTypesActivity;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class editLocationClass extends Dialog implements
        View.OnClickListener {

    public Activity c;
    public Dialog d;
    public Button yes, no;
    public EditText label;
    public int id;

    public editLocationClass(Activity a, int id) {
        super(a);
        // TODO Auto-generated constructor stub
        this.c = a;
        this.id = id;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.edit_location);
        yes = (Button) findViewById(R.id.btn_yes);
        no = (Button) findViewById(R.id.btn_no);
        label = (EditText) findViewById(R.id.typeLabel);
        yes.setOnClickListener(this);
        no.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_yes:
                // Write your code here to execute after dialog
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("https://home-msy.herokuapp.com/api/v1/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                JsonApi jsonApi = retrofit.create(JsonApi.class);
                locationData location = new locationData();
                location.setLabel(label.getText().toString().trim());
                location.setLocationId(id);
                Call<locationResponseAdmin> call = jsonApi.editControllerLocation(Splash.sp.getString("Cookie", ""), location);
                call.enqueue(new Callback<locationResponseAdmin>() {
                    @Override
                    public void onResponse(Call<locationResponseAdmin> call, Response<locationResponseAdmin> response) {
                        if (!response.isSuccessful()){
                            ResponseBody loginResponse = response.errorBody();
                            try {
                                String message = loginResponse.string();
                                Toast.makeText(label.getContext(), message.substring(10, message.length()-2), Toast.LENGTH_SHORT).show();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            return;
                        }

                        if (response.body().getDetail().equals("Controller location renamed Successfully")){
                            Toast.makeText(label.getContext(), "Controller location renamed Successfully", Toast.LENGTH_SHORT).show();
                            redirectActivity(c, ViewAllLocationsActivity.class);
                        }
                    }

                    @Override
                    public void onFailure(Call<locationResponseAdmin> call, Throwable t) {
                        Toast.makeText(label.getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
                break;
            case R.id.btn_no:
                dismiss();
                break;
            default:
                break;
        }
        dismiss();
    }

    public static void redirectActivity(Activity activity, Class aClass) {
        Intent intent = new Intent(activity,aClass);
        activity.startActivity(intent);
    }
}
