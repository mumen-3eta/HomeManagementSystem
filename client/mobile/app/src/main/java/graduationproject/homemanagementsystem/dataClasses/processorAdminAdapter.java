package graduationproject.homemanagementsystem.dataClasses;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import graduationproject.homemanagementsystem.R;
import graduationproject.homemanagementsystem.ViewAllProcessorsActivity;
import graduationproject.homemanagementsystem.ViewControllers;

public class processorAdminAdapter extends ArrayAdapter<processorData> {
    public processorAdminAdapter(Context context, List<processorData> processors) {
        super(context, 0, processors);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        final processorData processor = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.processor_item_admin, parent, false);
        }
        // Lookup view for data population
        ImageView devicePhoto = (ImageView) convertView.findViewById(R.id.Photo);
        TextView deviceName = (TextView) convertView.findViewById(R.id.deviceNameText);
        ImageButton qrGenerator = (ImageButton) convertView.findViewById(R.id.qrGenerator);
        // Populate the data into the template view using the data object
        deviceName.setText(String.valueOf(processor.getId()));
        qrGenerator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                qrDialogClass qr = new qrDialogClass((Activity)deviceName.getContext(), processor.getId());
                qr.show();
            }
        });

        return convertView;
    }
}
