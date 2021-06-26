package graduationproject.homemanagementsystem.dataClasses;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import graduationproject.homemanagementsystem.R;

public class locationAdapter extends ArrayAdapter<locationData> {
    public locationAdapter(Context context, List<locationData> processors) {
        super(context, 0, processors);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        final locationData location = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.location_item, parent, false);
        }
        // Lookup view for data population
        ImageView typePhoto = (ImageView) convertView.findViewById(R.id.Photo);
        TextView locationLabel = (TextView) convertView.findViewById(R.id.locationNameText);
        ImageButton editButton = (ImageButton) convertView.findViewById(R.id.editButton);
        ImageButton deleteButton = (ImageButton) convertView.findViewById(R.id.deleteButton);
        // Populate the data into the template view using the data object
        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editLocationClass cdd = new editLocationClass((Activity) view.getContext(), location.getId());
                cdd.show();
            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                removeDialogClass cdd = new removeDialogClass((Activity) view.getContext(), "location", location.getId());
                cdd.show();
            }
        });
        locationLabel.setText(location.getLabel());



        return convertView;
    }
}
