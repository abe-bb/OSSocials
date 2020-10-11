package edu.byu.cs.tweeter.view.login;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import edu.byu.cs.tweeter.R;
import edu.byu.cs.tweeter.model.service.request.LoginRequest;
import edu.byu.cs.tweeter.model.service.response.LoginResponse;
import edu.byu.cs.tweeter.presenter.LoginPresenter;
import edu.byu.cs.tweeter.view.asyncTasks.LoginTask;
import edu.byu.cs.tweeter.view.main.MainActivity;

public class LoginFragment extends Fragment implements LoginPresenter.View, TextWatcher, LoginTask.Observer {
    private static final String LOG_TAG = "LoginActivity";
    private Toast loggingInToast;
    private LoginPresenter presenter;

    private Button loginButton;
    private EditText username;
    private EditText password;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        presenter = new LoginPresenter(this);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.login_fragment, container, false);
        loginButton = view.findViewById(R.id.login_button);
        username = view.findViewById(R.id.login_username);
        password = view.findViewById(R.id.login_password);

        username.addTextChangedListener(this);
        password.addTextChangedListener(this);
        loginButton.setEnabled(false);

        loginButton.setOnClickListener((View v) -> {
            loggingInToast = Toast.makeText(getContext(),
                    getResources().getString(R.string.logging_in), Toast.LENGTH_LONG);
            loggingInToast.show();

            LoginRequest request = new LoginRequest("DummyUser", "DummyPassword");
            LoginTask loginTask = new LoginTask(presenter, this);
            loginTask.execute(request);
        });

        return view;
    }

    @Override
    public void afterTextChanged(Editable s) {
        if (username.getText().toString().isEmpty() ||
                password.getText().toString().isEmpty())
            loginButton.setEnabled(false);
        else
            loginButton.setEnabled(true);
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
    }

    /**
     * The callback method that gets invoked for a successful login. Displays the MainActivity.
     *
     * @param loginResponse the response from the login request.
     */
    @Override
    public void loginSuccessful(LoginResponse loginResponse) {
        Intent intent = new Intent(getContext(), MainActivity.class);

        intent.putExtra(MainActivity.CURRENT_USER_KEY, loginResponse.getUser());
        intent.putExtra(MainActivity.AUTH_TOKEN_KEY, loginResponse.getAuthToken());

        loggingInToast.cancel();
        startActivity(intent);
    }

    /**
     * The callback method that gets invoked for an unsuccessful login. Displays a toast with a
     * message indicating why the login failed.
     *
     * @param loginResponse the response from the login request.
     */
    @Override
    public void loginUnsuccessful(LoginResponse loginResponse) {
        Toast.makeText(getContext(), "Failed to login. " + loginResponse.getMessage(), Toast.LENGTH_LONG).show();
    }

    /**
     * A callback indicating that an exception was thrown in an asynchronous method called on the
     * presenter.
     *
     * @param exception the exception.
     */
    @Override
    public void handleException(Exception exception) {
        Log.e(LOG_TAG, exception.getMessage(), exception);
        Toast.makeText(getContext(), "Failed to login because of exception: " + exception.getMessage(), Toast.LENGTH_LONG).show();

    }
}
