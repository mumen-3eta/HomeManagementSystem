package graduationproject.homemanagementsystem.dataClasses;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import androidx.appcompat.app.AlertDialog;

import java.util.List;

import graduationproject.homemanagementsystem.HomePageActivity;
import graduationproject.homemanagementsystem.HomePageAdminActivity;
import graduationproject.homemanagementsystem.R;
import graduationproject.homemanagementsystem.Splash;
import graduationproject.homemanagementsystem.ViewControllers;
import graduationproject.homemanagementsystem.ViewProcessors;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class processorAdapter extends ArrayAdapter<processorData> {
    public processorAdapter(Context context, List<processorData> processors) {
        super(context, 0, processors);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        final processorData processor = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.processor_item, parent, false);
        }
        // Lookup view for data population
        ImageView devicePhoto = (ImageView) convertView.findViewById(R.id.Photo);
        TextView deviceName = (TextView) convertView.findViewById(R.id.deviceNameText);
        ImageButton delete = (ImageButton) convertView.findViewById(R.id.deleteButton);
        TextView viewControllers = (TextView) convertView.findViewById(R.id.viewControllersTextView);
        // Populate the data into the template view using the data object
        deviceName.setText(String.valueOf(processor.getId()));
        viewControllers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Activity activity = (Activity) delete.getContext();
                Intent intent = new Intent(activity, ViewControllers.class);
                intent.putExtra("connectionID", processor.getId());
                activity.startActivity(intent);
            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Splash.sp.getBoolean("Admin", false)){
                    CustomDialogClassAdmin cdd = new CustomDialogClassAdmin((Activity) delete.getContext(), processor.getProcessor_id(), "processor", 0);
                    cdd.show();
                }else {
                    CustomDialogClass cdd = new CustomDialogClass((Activity) delete.getContext(), processor.getProcessor_id(), "processor", 0);
                    cdd.show();
                }

                /*AlertDialog.Builder alertDialog2 = new AlertDialog.Builder(delete.getContext());

                // Setting Dialog Title
                alertDialog2.setTitle("Confirm Removing Processor...");


                // Setting Dialog Message
                alertDialog2.setMessage("Removing this processor will cause all it's controllers to be removed");


                // Setting Icon to Dialog
                alertDialog2.setIcon(R.drawable.ic_warning);

                // Setting Positive "Yes" Btn
                alertDialog2.setPositiveButton("Remove",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // Write your code here to execute after dialog
                                Retrofit retrofit = new Retrofit.Builder()
                                        .baseUrl("https://home-msy.herokuapp.com/api/v1/")
                                        .addConverterFactory(GsonConverterFactory.create())
                                        .build();
                                JsonApi jsonApi = retrofit.create(JsonApi.class);

                            }
                        });
                // Setting Negative "NO" Btn
                alertDialog2.setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // Write your code here to execute after dialog
                                dialog.cancel();
                            }
                        });

                // Showing Alert Dialog
                alertDialog2.show();*/
            }
        });
        return convertView;
    }
}
