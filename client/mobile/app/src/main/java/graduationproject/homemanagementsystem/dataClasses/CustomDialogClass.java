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

import graduationproject.homemanagementsystem.HomePageActivity;
import graduationproject.homemanagementsystem.R;
import graduationproject.homemanagementsystem.Splash;
import graduationproject.homemanagementsystem.ViewAllLocationsActivity;
import graduationproject.homemanagementsystem.ViewProcessors;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CustomDialogClass extends Dialog implements
        android.view.View.OnClickListener {

    // Write your code here to execute after dialog
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://home-msy.herokuapp.com/api/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    JsonApi jsonApi = retrofit.create(JsonApi.class);

    public Activity c;
    public Dialog d;
    public Button yes, no;
    public int id, connection;
    public String s;

    public CustomDialogClass(Activity a, int id, String s, int connection) {
        super(a);
        // TODO Auto-generated constructor stub
        this.c = a;
        this.id = id;
        this.s = s;
        this.connection = connection;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.custom_dialog);
        yes = (Button) findViewById(R.id.btn_yes);
        no = (Button) findViewById(R.id.btn_no);
        yes.setOnClickListener(this);
        no.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_yes:
                if (s.equals("processor")) {
                    processorData processor = new processorData();
                    processor.setProcessorId(id);
                    Call<processorResponse> call = jsonApi.removeProcessor(Splash.sp.getString("Cookie", ""), processor);
                    call.enqueue(new Callback<processorResponse>() {
                        @Override
                        public void onResponse(Call<processorResponse> call, Response<processorResponse> response) {
                            if (!response.isSuccessful()) {
                                ResponseBody loginResponse = response.errorBody();
                                try {
                                    String message = loginResponse.string();
                                    Toast.makeText(yes.getContext(), message.substring(10, message.length() - 2), Toast.LENGTH_SHORT).show();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                                return;
                            }

                            if (response.body().getDetail().equals("processor unit connection data deleted Successfully ")) {
                                Toast.makeText(yes.getContext(), "processor deleted Successfully ", Toast.LENGTH_SHORT).show();
                                redirectActivity(c, ViewProcessors.class);
                            }
                        }

                        @Override
                        public void onFailure(Call<processorResponse> call, Throwable t) {
                            Toast.makeText(yes.getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }
                if (s.equals("controller")){
                    controllerData controller = new controllerData();
                    controller.setProcessorId(connection);
                    controller.setControllerId(id);
                    Call<controllerResponse> call = jsonApi.removeController(Splash.sp.getString("Cookie", ""), controller);
                    call.enqueue(new Callback<controllerResponse>() {
                        @Override
                        public void onResponse(Call<controllerResponse> call, Response<controllerResponse> response) {
                            if (!response.isSuccessful()) {
                                ResponseBody loginResponse = response.errorBody();
                                try {
                                    String message = loginResponse.string();
                                    Toast.makeText(yes.getContext(), message.substring(10, message.length() - 2), Toast.LENGTH_SHORT).show();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                                return;
                            }

                            if (response.body().getDetail().equals("controller unit connection data deleted Successfully ")) {
                                Toast.makeText(yes.getContext(), "controller deleted Successfully ", Toast.LENGTH_SHORT).show();
                                redirectActivity(c, HomePageActivity.class);
                            }
                        }

                        @Override
                        public void onFailure(Call<controllerResponse> call, Throwable t) {
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
        Intent intent = new Intent(activity, aClass);
        activity.startActivity(intent);
    }
}
