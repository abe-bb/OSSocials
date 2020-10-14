package edu.byu.cs.tweeter.view.main.Feed;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import edu.byu.cs.tweeter.R;
import edu.byu.cs.tweeter.model.domain.Status;

public class StatusHolder extends RecyclerView.ViewHolder {
    ImageView photo;
    TextView name;
    TextView alias;
    TextView text;

    public StatusHolder(@NonNull View itemView) {
        super(itemView);
        photo = itemView.findViewById(R.id.status_user_image);
        name = itemView.findViewById(R.id.status_user_name);
        alias = itemView.findViewById(R.id.status_user_alias);
        text = itemView.findViewById(R.id.status_text);
    }


}
