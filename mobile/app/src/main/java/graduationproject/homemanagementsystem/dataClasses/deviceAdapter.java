package graduationproject.homemanagementsystem.dataClasses;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import graduationproject.homemanagementsystem.R;

public class deviceAdapter extends ArrayAdapter<deviceClass> {
    public deviceAdapter(Context context, ArrayList<deviceClass> devices) {
        super(context, 0, devices);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        deviceClass device = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.device_item, parent, false);
        }
        // Lookup view for data population
        ImageView devicePhoto = (ImageView) convertView.findViewById(R.id.devicePhoto);
        TextView deviceName = (TextView) convertView.findViewById(R.id.deviceNameText);
        TextView deviceLocation = (TextView) convertView.findViewById(R.id.deviceLocationText);
        // Populate the data into the template view using the data object
        deviceName.setText(device.getName());
        deviceLocation.setText(device.getLocation());
        // Return the completed view to render on screen
        return convertView;
    }
}
