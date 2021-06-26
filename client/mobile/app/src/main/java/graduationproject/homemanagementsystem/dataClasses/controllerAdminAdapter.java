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

public class controllerAdminAdapter extends ArrayAdapter<controllerData> {
    public controllerAdminAdapter(Context context, List<controllerData> processors) {
        super(context, 0, processors);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        final controllerData controller = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.controller_item, parent, false);
        }
        // Lookup view for data population
        ImageView devicePhoto = (ImageView) convertView.findViewById(R.id.Photo);
        TextView deviceName = (TextView) convertView.findViewById(R.id.deviceNameText);
        ImageButton qrGenerator = (ImageButton) convertView.findViewById(R.id.qrGenerator);
        // Populate the data into the template view using the data object
        deviceName.setText(String.valueOf(controller.getId()));
        qrGenerator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                qrDialogClass qr = new qrDialogClass((Activity)deviceName.getContext(), controller.getId());
                qr.show();
            }
        });

        return convertView;
    }
}
