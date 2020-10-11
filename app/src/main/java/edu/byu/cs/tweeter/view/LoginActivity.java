package edu.byu.cs.tweeter.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;

import edu.byu.cs.tweeter.R;
import edu.byu.cs.tweeter.model.service.response.LoginResponse;
import edu.byu.cs.tweeter.presenter.LoginPresenter;
import edu.byu.cs.tweeter.view.asyncTasks.LoginTask;
import edu.byu.cs.tweeter.view.main.MainActivity;

/**
 * Contains the minimum UI required to allow the user to login with a hard-coded user. Most or all
 * of this should be replaced when the back-end is implemented.
 */
public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);
        Resources res = getResources();

        LoginFragmentAdapter loginFragmentAdapter = new LoginFragmentAdapter(
                getSupportFragmentManager(), res.getString(R.string.login),
                res.getString(R.string.register));
        ViewPager viewPager = findViewById(R.id.login_pager);
        viewPager.setAdapter(loginFragmentAdapter);
        TabLayout tabLayout = findViewById(R.id.login_tabs);
        tabLayout.setupWithViewPager(viewPager);

//        presenter = new LoginPresenter(this);
//
//        Button loginButton = findViewById(R.id.LoginButton);
//        loginButton.setOnClickListener(new View.OnClickListener() {
//
//            /**
//             * Makes a login request. The user is hard-coded, so it doesn't matter what data we put
//             * in the LoginRequest object.
//             *
//             * @param view the view object that was clicked.
//             */
//            @Override
//            public void onClick(View view) {
//                loginInToast = Toast.makeText(LoginActivity.this, "Logging In", Toast.LENGTH_LONG);
//                loginInToast.show();
//
//                // It doesn't matter what values we put here. We will be logged in with a hard-coded dummy user.
//                LoginRequest loginRequest = new LoginRequest("dummyUserName", "dummyPassword");
//                LoginTask loginTask = new LoginTask(presenter, LoginActivity.this);
//                loginTask.execute(loginRequest);
//            }
//        });
    }
}
