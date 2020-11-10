package edu.byu.cs.tweeter.view.main;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import edu.byu.cs.tweeter.R;
import edu.cs.byu.tweeter.shared.model.Status;
import edu.cs.byu.tweeter.shared.model.User;
import edu.cs.byu.tweeter.shared.request.FeedRequest;
import edu.cs.byu.tweeter.shared.response.FeedResponse;
import edu.byu.cs.tweeter.presenter.FeedPresenter;
import edu.byu.cs.tweeter.view.asyncTasks.GetFeedTask;
import edu.byu.cs.tweeter.view.util.ImageUtils;

public class FeedFragment extends Fragment {
    MainActivity mainActivity;

    private final String LOG_TAG = "FeedFragment";
    private final int LOADING_DATA_VIEW = 0;
    private final int ITEM_VIEW = 1;
    private final int PAGE_SIZE = 10;
    private boolean story;

    private FeedPresenter presenter;

    FeedRecyclerViewAdapter recyclerViewAdapter;

    public static FeedFragment newInstance(boolean story) {
        return new FeedFragment(story);
    }

    public FeedFragment(boolean story) {
        this.story = story;
        presenter = new FeedPresenter();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mainActivity = (MainActivity) getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_feed, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.feedRecycleView);


        LinearLayoutManager layoutManager = new LinearLayoutManager(this.getContext());
        recyclerView.setLayoutManager(layoutManager);

        recyclerViewAdapter = new FeedRecyclerViewAdapter();
        recyclerView.setAdapter(recyclerViewAdapter);

        recyclerView.addOnScrollListener(new FeedRecyclerViewPaginationScrollListener(layoutManager));

        return view;
    }


    private class FeedRecyclerViewAdapter extends RecyclerView.Adapter<StatusHolder> implements GetFeedTask.Observer {
        ArrayList<Status> stati;

        Status lastStatus;

        boolean isLoading = false;
        boolean hasMorePages;

        public FeedRecyclerViewAdapter() {
            stati = new ArrayList<>();
            lastStatus = null;
            hasMorePages = true;

            loadMoreItems();
        }

        @NonNull
        @Override
        public StatusHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            View view;

            if (viewType == LOADING_DATA_VIEW) {
                view = inflater.inflate(R.layout.loading_row, parent, false);
            }
            else {
                    view = inflater.inflate(R.layout.status_row, parent, false);
            }

            StatusHolder statusHolder = new StatusHolder(view, viewType);
            return statusHolder;

        }

        @Override
        public void onBindViewHolder(@NonNull StatusHolder holder, int position) {
            if (!isLoading) {
                holder.bindStatus(stati.get(position));
            }
        }

        @Override
        public int getItemCount() {
            return stati.size();
        }

        public void loadMoreItems() {
            isLoading = true;
            addLoadingFooter();

            GetFeedTask task = new GetFeedTask(presenter, this);
            FeedRequest request = new FeedRequest(mainActivity.getDisplayUser(), lastStatus, PAGE_SIZE, story, MainActivity.getLoggedInToken());
            task.execute(request);

        }
        /**
         * Adds new users to the list from which the RecyclerView retrieves the users it displays
         * and notifies the RecyclerView that items have been added.
         *
         * @param newStati the users to add.
         */
        void addItems(List<Status> newStati) {
            int startInsertPosition = stati.size();
            stati.addAll(newStati);
            this.notifyItemRangeInserted(startInsertPosition, newStati.size());
        }

        /**
         * Adds a single Status to the list from which the RecyclerView retrieves the users it
         * displays and notifies the RecyclerView that an item has been added.
         *
         * @param status the user to add.
         */
        void addItem(Status status) {
            stati.add(status);
            this.notifyItemInserted(stati.size() - 1);
        }

        /**
         * Removes a status from the list from which the RecyclerView retrieves the users it displays
         * and notifies the RecyclerView that an item has been removed.
         *
         * @param status the status to remove.
         */
        void removeItem(Status status) {
            int position = stati.indexOf(status);
            stati.remove(position);
            this.notifyItemRemoved(position);
        }

        /**
         * Adds a dummy status to the list of users so the RecyclerView will display a view (the
         * loading footer view) at the bottom of the list.
         */
        private void addLoadingFooter() {
            addItem(new Status());
        }

        /**
         * Removes the dummy status from the list of users so the RecyclerView will stop displaying
         * the loading footer at the bottom of the list.
         */
        private void removeLoadingFooter() {
            removeItem(stati.get(stati.size() - 1));
        }

        /**
         * Returns the type of the view that should be displayed for the item currently at the
         * specified position.
         *
         * @param position the position of the items whose view type is to be returned.
         * @return the view type.
         */
        @Override
        public int getItemViewType(int position) {
            return (position == stati.size() - 1 && isLoading) ? LOADING_DATA_VIEW : ITEM_VIEW;
        }

        @Override
        public void feedPageRetrieved(FeedResponse response) {
            List<Status> stati = response.getStati();

            hasMorePages = response.getHasMorePages();
            lastStatus = (stati.size() > 0) ? stati.get(stati.size() - 1) : null;

            removeLoadingFooter();
            isLoading = false;
            recyclerViewAdapter.addItems(stati);

        }

        @Override
        public void handleException(Exception exception) {
            Log.e(LOG_TAG, exception.getMessage(), exception);
            removeLoadingFooter();
            Toast.makeText(getContext(), exception.getMessage(), Toast.LENGTH_LONG).show();

        }
    }

    private class StatusHolder extends RecyclerView.ViewHolder {
        User currentAuthor;
        ImageView photo;
        TextView name;
        TextView alias;
        TextView text;

        public StatusHolder(@NonNull View itemView, int viewType) {
            super(itemView);

            if (viewType == ITEM_VIEW) {
                photo = itemView.findViewById(R.id.status_user_image);
                name = itemView.findViewById(R.id.status_user_name);
                alias = itemView.findViewById(R.id.status_user_alias);
                text = itemView.findViewById(R.id.status_text);

                if (!story) {
                    View clickableView = itemView.findViewById(R.id.user_display);

                    clickableView.setOnClickListener((View v) -> {
                        Intent intent = new Intent(getContext(), MainActivity.class);
                        intent.putExtra(MainActivity.DISPLAY_USER_KEY, currentAuthor);

                        startActivity(intent);
                    });
                }
            }
            else {
                photo = null;
                name = null;
                alias = null;
                text = null;
            }
        }

        /**
         * binds a status to the status row layout fields
         * @param status status to be bound to the view
         */
        public void bindStatus(Status status) {
            currentAuthor = status.getAuthor();
            photo.setImageDrawable(ImageUtils.drawableFromByteArray(currentAuthor.getImageBytes()));
            name.setText(String.format("%s %s", currentAuthor.getFirstName(), currentAuthor.getLastName()));
            alias.setText(currentAuthor.getAlias());
            text.setText(status.getText());
        }
    }

    private class FeedRecyclerViewPaginationScrollListener extends RecyclerView.OnScrollListener {
        LinearLayoutManager layoutManager;

        public FeedRecyclerViewPaginationScrollListener(LinearLayoutManager layoutManager) {
            this.layoutManager = layoutManager;
        }

        @Override
        public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);

            int visibleItemCount = layoutManager.getChildCount();
            int totalItemCount = layoutManager.getItemCount();
            int firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition();

            if (!recyclerViewAdapter.isLoading && recyclerViewAdapter.hasMorePages) {
                if ((visibleItemCount + firstVisibleItemPosition) >=
                        totalItemCount && firstVisibleItemPosition >= 0) {
                    recyclerViewAdapter.loadMoreItems();
                }
            }
        }
    }
}
