package edu.byu.cs.tweeter.view.main;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import edu.byu.cs.tweeter.R;
import edu.byu.cs.tweeter.model.domain.AuthToken;
import edu.byu.cs.tweeter.model.domain.User;
import edu.byu.cs.tweeter.view.login.LoginActivity;
import edu.byu.cs.tweeter.view.util.ImageUtils;

/**
 * The main activity for the application. Contains tabs for feed, story, following, and followers.
 */
public class MainActivity extends AppCompatActivity {

    public static String LOGGED_IN_USER_KEY = "CurrentUser";
    public static  String AUTH_TOKEN_KEY = "AuthTokenKey";
    public static String DISPLAY_USER_KEY = "DisplayUser";
    private static User LOGGED_IN_USER;
    private static AuthToken LOGGED_IN_TOKEN;
    private User displayUser = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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

        AuthToken authToken = (AuthToken) getIntent().getSerializableExtra(AUTH_TOKEN_KEY);

        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager(), loggedInUser, authToken);
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);

        FloatingActionButton fab = findViewById(R.id.fab);

        // We should use a Java 8 lambda function for the listener (and all other listeners), but
        // they would be unfamiliar to many students who use this code.
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        TextView userName = findViewById(R.id.userName);
        userName.setText(this.displayUser.getName());

        TextView userAlias = findViewById(R.id.userAlias);
        userAlias.setText(this.displayUser.getAlias());

        ImageView userImageView = findViewById(R.id.userImage);
        userImageView.setImageDrawable(ImageUtils.drawableFromByteArray(this.displayUser.getImageBytes()));

        TextView followeeCount = findViewById(R.id.followeeCount);
        followeeCount.setText(getString(R.string.followeeCount, 42));

        TextView followerCount = findViewById(R.id.followerCount);
        followerCount.setText(getString(R.string.followerCount, 27));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.logoutMenu:
                MainActivity.LOGGED_IN_TOKEN = null;
                MainActivity.LOGGED_IN_USER = null;
                Intent logoutIntent = new Intent(this, LoginActivity.class);
                logoutIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(logoutIntent);
                return true;
            case R.id.home_button:
                Intent homeIntent = new Intent(this, MainActivity.class);
                homeIntent.putExtra(MainActivity.DISPLAY_USER_KEY, LOGGED_IN_USER);
                startActivity(homeIntent);
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}