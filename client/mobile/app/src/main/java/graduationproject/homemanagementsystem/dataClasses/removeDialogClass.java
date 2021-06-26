package graduationproject.homemanagementsystem.dataClasses;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
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
import retrofit2.http.Body;
import retrofit2.http.HTTP;

public class removeDialogClass extends Dialog implements
        View.OnClickListener {

    // Write your code here to execute after dialog
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://home-msy.herokuapp.com/api/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    JsonApi jsonApi = retrofit.create(JsonApi.class);

    public Activity c;
    public Dialog d;
    public Button yes, no;
    public String s;
    public int id;

    public removeDialogClass(Activity a, String s, int id) {
        super(a);
        // TODO Auto-generated constructor stub
        this.c = a;
        this.s = s;
        this.id = id;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.remove_dialog);
        yes = (Button) findViewById(R.id.btn_yes);
        no = (Button) findViewById(R.id.btn_no);
        yes.setOnClickListener(this);
        no.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_yes:
                if (s.equals("type")){
                    typeData type = new typeData();
                    type.setTypeId(id);

                    Call<typeResponseAdmin> call = jsonApi.deleteControllerType(Splash.sp.getString("Cookie", ""), type);
                    call.enqueue(new Callback<typeResponseAdmin>() {
                        @Override
                        public void onResponse(Call<typeResponseAdmin> call, Response<typeResponseAdmin> response) {
                            if (!response.isSuccessful()){
                                ResponseBody loginResponse = response.errorBody();
                                try {
                                    String message = loginResponse.string();
                                    String messageSub = message.substring(10, 35);
                                    //Toast.makeText(yes.getContext(), messageSub, Toast.LENGTH_LONG).show();
                                    if (messageSub.equals("update or delete on table")){
                                        Toast.makeText(yes.getContext(), "Type is used, can't be removed ", Toast.LENGTH_SHORT).show();
                                        return;
                                    }
                                    Toast.makeText(yes.getContext(), message.substring(10, message.length()-2), Toast.LENGTH_SHORT).show();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                                return;
                            }

                            if (response.body().getDetail().equals("Controller Type deleted Successfully")){
                                Toast.makeText(yes.getContext(), "Controller Type deleted Successfully", Toast.LENGTH_SHORT).show();
                                redirectActivity(c, ViewAllTypesActivity.class);
                            }
                        }

                        @Override
                        public void onFailure(Call<typeResponseAdmin> call, Throwable t) {
                            Toast.makeText(yes.getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }
                if (s.equals("location")){
                    locationData location = new locationData();
                    location.setLocationId(id);

                    Call<locationResponseAdmin> call = jsonApi.deleteControllerLocation(Splash.sp.getString("Cookie", ""), location);
                    call.enqueue(new Callback<locationResponseAdmin>() {
                        @Override
                        public void onResponse(Call<locationResponseAdmin> call, Response<locationResponseAdmin> response) {
                            if (!response.isSuccessful()){
                                ResponseBody loginResponse = response.errorBody();
                                try {
                                    String message = loginResponse.string();
                                    String messageSub = message.substring(10, 35);
                                    //Toast.makeText(yes.getContext(), messageSub, Toast.LENGTH_LONG).show();
                                    if (messageSub.equals("update or delete on table")){
                                        Toast.makeText(yes.getContext(), "Location is used, can't be removed ", Toast.LENGTH_SHORT).show();
                                        return;
                                    }
                                    Toast.makeText(yes.getContext(), message.substring(10, message.length()-2), Toast.LENGTH_SHORT).show();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                                return;
                            }

                            if (response.body().getDetail().equals("Controller location deleted Successfully")){
                                Toast.makeText(yes.getContext(), "Controller location deleted Successfully", Toast.LENGTH_SHORT).show();
                                redirectActivity(c, ViewAllLocationsActivity.class);
                            }
                        }

                        @Override
                        public void onFailure(Call<locationResponseAdmin> call, Throwable t) {
                            Toast.makeText(yes.getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }
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
