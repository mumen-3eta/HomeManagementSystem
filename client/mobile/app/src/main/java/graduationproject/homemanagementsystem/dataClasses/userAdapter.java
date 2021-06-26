package graduationproject.homemanagementsystem.dataClasses;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import com.bumptech.glide.Glide;

import java.util.List;

import graduationproject.homemanagementsystem.R;

public class userAdapter extends ArrayAdapter<data> {
    public userAdapter(Context context, List<data> processors) {
        super(context, 0, processors);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        final data user = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.user_item, parent, false);
        }
        // Lookup view for data population
        ImageView userPhoto = (ImageView) convertView.findViewById(R.id.Photo);
        TextView usernameText = (TextView) convertView.findViewById(R.id.usernameText);
        TextView userEmailText = (TextView) convertView.findViewById(R.id.userEmailText);
        TextView userStatusText = (TextView) convertView.findViewById(R.id.userStatusText);
        // Populate the data into the template view using the data object
        usernameText.setText(user.getUser_name());
        userEmailText.setText(user.getEmail());
        if (user.isAdmin()){
            userStatusText.setText("Admin");
        }else {
            userStatusText.setText("User");
        }
        if (user.getImage() == null || user.getImage().equals("")){
            Glide.with(userPhoto.getContext()).load(ContextCompat.getDrawable(userPhoto.getContext(), R.drawable.ic_profile)).into(userPhoto);
        }else {
            Glide.with(userPhoto.getContext()).load(user.getImage()).into(userPhoto);
        }


        return convertView;
    }
}

