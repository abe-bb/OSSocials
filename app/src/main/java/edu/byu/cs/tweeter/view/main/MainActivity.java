package edu.byu.cs.tweeter.view.main;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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

import java.time.Instant;

import edu.byu.cs.tweeter.R;
import edu.byu.cs.tweeter.model.domain.AuthToken;
import edu.byu.cs.tweeter.model.domain.Status;
import edu.byu.cs.tweeter.model.domain.User;
import edu.byu.cs.tweeter.model.domain.UserContextualDetails;
import edu.byu.cs.tweeter.model.service.request.FollowRequest;
import edu.byu.cs.tweeter.model.service.request.LogoutRequest;
import edu.byu.cs.tweeter.model.service.request.TwitRequest;
import edu.byu.cs.tweeter.model.service.request.UserDetailRequest;
import edu.byu.cs.tweeter.model.service.response.FollowResponse;
import edu.byu.cs.tweeter.model.service.response.LogoutResponse;
import edu.byu.cs.tweeter.model.service.response.TwitResponse;
import edu.byu.cs.tweeter.model.service.response.UserDetailResponse;
import edu.byu.cs.tweeter.presenter.MainPresenter;
import edu.byu.cs.tweeter.view.asyncTasks.FollowTask;
import edu.byu.cs.tweeter.view.asyncTasks.GetUserDetailTask;
import edu.byu.cs.tweeter.view.asyncTasks.LogoutTask;
import edu.byu.cs.tweeter.view.asyncTasks.SendTwitTask;
import edu.byu.cs.tweeter.view.login.LoginActivity;
import edu.byu.cs.tweeter.view.util.ImageUtils;

/**
 * The main activity for the application. Contains tabs for feed, story, following, and followers.
 */
public class MainActivity extends AppCompatActivity implements GetUserDetailTask.Observer, FollowTask.Observer, MainPresenter.View, SendTwitTask.Observer, LogoutTask.Observer {
    private static final String LOG_TAG = "MainActivity";

    public static final int TWIT_RESULT = 1;

    public static final String TWIT_EXTRA = "TwitExtra";
    public static final String LOGGED_IN_USER_KEY = "CurrentUser";
    public static  final String AUTH_TOKEN_KEY = "AuthTokenKey";
    public static final String DISPLAY_USER_KEY = "DisplayUser";
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

    private Toast loggingOutToast;
    private MenuItem logoutButton;


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
        fab.setOnClickListener((View v) -> {
            Intent intent = new Intent(this, ComposeTwitActivity.class);
            startActivityForResult(intent, TWIT_RESULT);
        });

        userName = findViewById(R.id.userName);
        userAlias = findViewById(R.id.userAlias);
        userImage = findViewById(R.id.userPhoto);
        followeeCount = findViewById(R.id.followeeCount);
        followerCount = findViewById(R.id.followerCount);

        followButton = findViewById(R.id.follow_button);
        followButton.setEnabled(false);

        GetUserDetailTask task = new GetUserDetailTask(this, presenter);
        task.execute(new UserDetailRequest(this.displayUser, LOGGED_IN_USER, LOGGED_IN_TOKEN));

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
                item.setEnabled(false);
                logoutButton = item;
                loggingOutToast = Toast.makeText(this, R.string.logging_out, Toast.LENGTH_LONG);
                loggingOutToast.show();
                LogoutTask task = new LogoutTask(this, presenter);
                LogoutRequest request = new LogoutRequest(LOGGED_IN_USER, LOGGED_IN_TOKEN);
                task.execute(request);
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
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == TWIT_RESULT) {
            if (resultCode == Activity.RESULT_OK) {
                String result = data.getStringExtra(TWIT_EXTRA);

                SendTwitTask task = new SendTwitTask(this, presenter);
                Status sendStatus = new Status(LOGGED_IN_USER, Instant.now(), result);
                TwitRequest request = new TwitRequest(sendStatus, LOGGED_IN_TOKEN);
                task.execute(request);
            }
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
            FollowRequest request = new FollowRequest(LOGGED_IN_USER, displayUser, details.isFollowing(), LOGGED_IN_TOKEN);
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
                FollowRequest request = new FollowRequest(LOGGED_IN_USER, displayUser, !response.isUnfollow(), LOGGED_IN_TOKEN);
                task.execute(request);
            });
            followButton.setEnabled(true);
        }
    }

    @Override
    public void twitSent(TwitResponse response) {
        Toast.makeText(this, getString(R.string.sent), Toast.LENGTH_SHORT).show();

    }

    @Override
    public void logoutSuccessful(LogoutResponse response) {
        MainActivity.LOGGED_IN_TOKEN = null;
        MainActivity.LOGGED_IN_USER = null;
        Intent logoutIntent = new Intent(this, LoginActivity.class);
        logoutIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        logoutButton.setEnabled(true);
        logoutButton = null;
        loggingOutToast.cancel();
        Toast.makeText(this, R.string.logged_out_successfully, Toast.LENGTH_LONG).show();
        startActivity(logoutIntent);

    }

    @Override
    public void logoutUnsuccessful(LogoutResponse response) {
        Toast.makeText(this, R.string.logout_failed, Toast.LENGTH_SHORT).show();
        logoutButton.setEnabled(true);
        logoutButton = null;
    }

    @Override
    public void handleException(Exception exception) {
        Log.e(LOG_TAG, exception.getMessage(), exception);
        Toast.makeText(this, exception.getMessage(), Toast.LENGTH_LONG).show();
    }
}