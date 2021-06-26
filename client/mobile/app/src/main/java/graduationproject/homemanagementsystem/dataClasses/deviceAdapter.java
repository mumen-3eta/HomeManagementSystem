package graduationproject.homemanagementsystem.dataClasses;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import graduationproject.homemanagementsystem.HomePageActivity;
import graduationproject.homemanagementsystem.HomePageAdminActivity;
import graduationproject.homemanagementsystem.R;
import graduationproject.homemanagementsystem.Splash;
import graduationproject.homemanagementsystem.UserProfileActivity;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class deviceAdapter extends ArrayAdapter<controllerData> {
    public deviceAdapter(Context context, List<controllerData> devices) {
        super(context, 0, devices);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        final controllerData device = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.device_item, parent, false);
        }
        // Lookup view for data population
        ImageView devicePhoto = (ImageView) convertView.findViewById(R.id.Photo);
        TextView deviceName = (TextView) convertView.findViewById(R.id.deviceNameText);
        TextView deviceLocation = (TextView) convertView.findViewById(R.id.deviceLocationText);
        TextView deviceType = (TextView) convertView.findViewById(R.id.deviceTypeText);
        final Switch switch1 = (Switch) convertView.findViewById(R.id.switch1);
        ImageButton deleteButton = (ImageButton) convertView.findViewById(R.id.deleteButton);
        // Populate the data into the template view using the data object
        /*String deviceLocationName = "";

        for (locationData location : Splash.locations) {
            if (location.getId() == device.getLocation_id()){
                deviceLocationName = location.getLabel();
            }
        }
        String deviceTypeName = "";
        for (typeData type: Splash.types) {
            if (type.getId() == device.getTypeid()){
                deviceTypeName = type.getLabel();
            }
        }*/
        deviceType.setText(device.getType_label());
        deviceLocation.setText(device.getLocation_label());
        deviceName.setText(device.getName());
        switch1.setChecked(device.isStatus());
        switch1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, final boolean status) {
                final Toast toast = new Toast(switch1.getContext());

                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("https://home-msy.herokuapp.com/api/v1/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                JsonApi jsonApi = retrofit.create(JsonApi.class);

                controllerData data = new controllerData();
                data.setControllerId(device.getController_id());

                Call<changeStatusResponse> call = jsonApi.changeStatus(Splash.sp.getString("Cookie",""), data);
                call.enqueue(new Callback<changeStatusResponse>() {
                    @Override
                    public void onResponse(Call<changeStatusResponse> call, Response<changeStatusResponse> response) {
                        if (!response.isSuccessful()){
                            ResponseBody loginResponse = response.errorBody();
                            try {
                                String message = loginResponse.string();
                                toast.makeText(switch1.getContext(), message.substring(10, message.length()-2), Toast.LENGTH_SHORT).show();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            return;
                        }
                        if (response.body().getDetail().equals("changing controller status Successfully ")){
                            device.setStatus(response.body().getControllerStatus().get(0).isStatus());
                            toast.makeText(switch1.getContext(), "Status Changed Successfully", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<changeStatusResponse> call, Throwable t) {
                        Log.e("error", t.getMessage());
                        toast.makeText(switch1.getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Splash.sp.getBoolean("Admin", false)){
                    CustomDialogClassAdmin cdd = new CustomDialogClassAdmin((Activity) deleteButton.getContext(), device.getController_id(), "controller", device.getProcessor_id());
                    cdd.show();
                }else {
                    CustomDialogClass cdd = new CustomDialogClass((Activity) deleteButton.getContext(), device.getController_id(), "controller", device.getProcessor_id());
                    cdd.show();
                }

            }
        });
        // Return the completed view to render on screen
        return convertView;
    }
}
