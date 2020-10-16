package edu.byu.cs.tweeter.view.main;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import edu.byu.cs.tweeter.R;
import edu.byu.cs.tweeter.model.domain.AuthToken;
import edu.byu.cs.tweeter.model.domain.User;
import edu.byu.cs.tweeter.model.domain.UserContextualDetails;
import edu.byu.cs.tweeter.model.service.request.FollowRequest;
import edu.byu.cs.tweeter.model.service.request.UserDetailRequest;
import edu.byu.cs.tweeter.model.service.response.FollowResponse;
import edu.byu.cs.tweeter.model.service.response.UserDetailResponse;
import edu.byu.cs.tweeter.presenter.MainPresenter;
import edu.byu.cs.tweeter.view.asyncTasks.FollowTask;
import edu.byu.cs.tweeter.view.asyncTasks.GetUserDetailTask;
import edu.byu.cs.tweeter.view.login.LoginActivity;
import edu.byu.cs.tweeter.view.util.ImageUtils;

/**
 * The main activity for the application. Contains tabs for feed, story, following, and followers.
 */
public class MainActivity extends AppCompatActivity implements GetUserDetailTask.Observer, FollowTask.Observer, MainPresenter.View {
    private static final String LOG_TAG = "MainActivity";

    public static String LOGGED_IN_USER_KEY = "CurrentUser";
    public static  String AUTH_TOKEN_KEY = "AuthTokenKey";
    public static String DISPLAY_USER_KEY = "DisplayUser";
    private static User LOGGED_IN_USER;
    private static AuthToken LOGGED_IN_TOKEN;
    private MainPresenter presenter;
    private User displayUser = null;

    private TextView userName;
    private TextView userAlias;
    private ImageView userImage;
    private TextView followeeCount;
    private TextView followerCount;

    private Button followButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        presenter = new MainPresenter(this);

        setContentView(R.layout.activity_main);

        User loggedInUser = (User) getIntent().getSerializableExtra(LOGGED_IN_USER_KEY);
        User displayUser = (User) getIntent().getSerializableExtra(DISPLAY_USER_KEY);
        AuthToken token = (AuthToken) getIntent().getSerializableExtra(AUTH_TOKEN_KEY);
        if(loggedInUser != null) {
            MainActivity.LOGGED_IN_USER = loggedInUser;
            this.displayUser = loggedInUser;
            if (token == null)
                throw new RuntimeException("No valid AuthToken passed to activity");
            LOGGED_IN_TOKEN = token;
        }
        else if (displayUser != null) {
            this.displayUser = displayUser;
        }
        else {
            throw new RuntimeException("No valid user passed to activity");
        }

        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager(), loggedInUser, LOGGED_IN_TOKEN);
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);

        FloatingActionButton fab = findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        userName = findViewById(R.id.userName);
        userAlias = findViewById(R.id.userAlias);
        userImage = findViewById(R.id.userPhoto);
        followeeCount = findViewById(R.id.followeeCount);
        followerCount = findViewById(R.id.followerCount);

        followButton = findViewById(R.id.follow_button);
        followButton.setEnabled(false);

        GetUserDetailTask task = new GetUserDetailTask(this, presenter);
        task.execute(new UserDetailRequest(this.displayUser, LOGGED_IN_USER));

        if (this.displayUser == LOGGED_IN_USER) {
            followButton.setVisibility(View.GONE);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);

        if (LOGGED_IN_USER.equals(displayUser)) {
            MenuItem item = menu.findItem(R.id.home_button);
            item.setVisible(false);
        }
        else {
            MenuItem item = menu.findItem(R.id.home_button);
            item.setVisible(true);
        }

        return true;
    }

    public User getDisplayUser() {
        return displayUser;
    }

    public static AuthToken getLoggedInToken() {
        return LOGGED_IN_TOKEN;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.logoutMenu:
                MainActivity.LOGGED_IN_TOKEN = null;
                MainActivity.LOGGED_IN_USER = null;
                Intent logoutIntent = new Intent(this, LoginActivity.class);
                logoutIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(logoutIntent);
                return true;
            case R.id.home_button:
                Intent homeIntent = new Intent(this, MainActivity.class);
                homeIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                homeIntent.putExtra(MainActivity.DISPLAY_USER_KEY, LOGGED_IN_USER);
                startActivity(homeIntent);
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void userDetailsRetrieved(UserDetailResponse response) {
        UserContextualDetails details = response.getDetails();
        User viewee = details.getViewee();
        userName.setText(String.format("%s %s", viewee.getFirstName(), viewee.getLastName()));
        userAlias.setText(viewee.getAlias());
        userImage.setImageDrawable(ImageUtils.drawableFromByteArray(viewee.getImageBytes()));
        followeeCount.setText(String.format(getString(R.string.followeeCount), details.getNumFollowing()));
        followerCount.setText(String.format(getString(R.string.followerCount), details.getNumFollowers()));

        if (details.isFollowing()) {
            followButton.setText(R.string.unfollow);
        }
        else {
            followButton.setText(R.string.follow);
        }
        followButton.setOnClickListener((View v) -> {
            FollowTask task = new FollowTask(this, presenter);
            FollowRequest request = new FollowRequest(LOGGED_IN_USER, displayUser, details.isFollowing());
            task.execute(request);
        });

        followButton.setEnabled(true);



    }

    @Override
    public void followComplete(FollowResponse response) {
        if (response.isSuccess()) {
            if (response.isUnfollow()) {
                followButton.setText(R.string.follow);
            }
            else {
                followButton.setText(R.string.unfollow);
            }

            followButton.setOnClickListener((View v) -> {
                FollowTask task = new FollowTask(this, presenter);
                FollowRequest request = new FollowRequest(LOGGED_IN_USER, displayUser, !response.isUnfollow());
                task.execute(request);
            });
            followButton.setEnabled(true);
        }
    }

    @Override
    public void handleException(Exception exception) {
        Log.e(LOG_TAG, exception.getMessage(), exception);
        Toast.makeText(this, exception.getMessage(), Toast.LENGTH_LONG).show();
    }
}