package graduationproject.homemanagementsystem.dataClasses;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.content.ContextCompat;

import com.bumptech.glide.Glide;

import java.io.IOException;
import java.util.List;

import graduationproject.homemanagementsystem.HomePageActivity;
import graduationproject.homemanagementsystem.R;
import graduationproject.homemanagementsystem.Splash;
import graduationproject.homemanagementsystem.ViewAllTypesActivity;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class typeAdapter extends ArrayAdapter<typeData> {
    public typeAdapter(Context context, List<typeData> processors) {
        super(context, 0, processors);
    }

    public static void redirectActivity(Activity activity, Class aClass) {
        Intent intent = new Intent(activity,aClass);
        activity.startActivity(intent);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        final typeData type = getItem(position);
        type.setTypeId(type.getId());
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.type_item, parent, false);
        }
        // Lookup view for data population
        ImageView typePhoto = (ImageView) convertView.findViewById(R.id.Photo);
        TextView typeLabel = (TextView) convertView.findViewById(R.id.typeNameText);
        ImageButton deleteButton = (ImageButton) convertView.findViewById(R.id.deleteButton);
        ImageButton editButton = (ImageButton) convertView.findViewById(R.id.editButton);
        // Populate the data into the template view using the data object
        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editTypeClass cdd = new editTypeClass((Activity) view.getContext(), type.getId());
                cdd.show();
            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                removeDialogClass cdd = new removeDialogClass((Activity) view.getContext(), "type", type.getId());
                cdd.show();
            }
        });
        typeLabel.setText(type.getLabel());



        return convertView;
    }
}
