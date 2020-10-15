package edu.byu.cs.tweeter.view.main.Feed;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import edu.byu.cs.tweeter.R;
import edu.byu.cs.tweeter.model.domain.Status;
import edu.byu.cs.tweeter.model.domain.User;
import edu.byu.cs.tweeter.view.util.ImageUtils;

public class FeedFragment extends Fragment {
    RecyclerView.LayoutManager layoutManager;
    FeedRecyclerViewAdapter recyclerViewAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_feed, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.feedRecycleView);

        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        recyclerViewAdapter = new FeedRecyclerViewAdapter();
        recyclerView.setAdapter(recyclerViewAdapter);

        return view;
    }


    private class FeedRecyclerViewAdapter extends RecyclerView.Adapter<StatusHolder> {
        ArrayList<Status> stati;

        @NonNull
        @Override
        public StatusHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.fragment_feed, parent, false);

            StatusHolder statusHolder = new StatusHolder(view);
            return statusHolder;

        }

        @Override
        public void onBindViewHolder(@NonNull StatusHolder holder, int position) {
            holder.bindStatus(stati.get(position));
        }

        @Override
        public int getItemCount() {
            return stati.size();
        }
    }

    private class StatusHolder extends RecyclerView.ViewHolder {
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

        /**
         * binds a status to the status row layout fields
         * @param status
         */
        public void bindStatus(Status status) {
            User author = status.getAuthor();
            photo.setImageDrawable(ImageUtils.drawableFromByteArray(author.getImageBytes()));
            name.setText(String.format("%s %s", author.getFirstName(), author.getLastName()));
            alias.setText(String.format("@%s", author.getAlias()));
            text.setText(status.getText());
        }
    }
}
