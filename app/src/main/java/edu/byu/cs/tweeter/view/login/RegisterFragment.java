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
import edu.byu.cs.tweeter.model.service.request.RegisterRequest;
import edu.byu.cs.tweeter.model.service.response.LoginResponse;
import edu.byu.cs.tweeter.presenter.RegisterPresenter;
import edu.byu.cs.tweeter.view.asyncTasks.RegisterTask;
import edu.byu.cs.tweeter.view.main.MainActivity;

public class RegisterFragment extends Fragment implements TextWatcher, RegisterTask.Observer, RegisterPresenter.View {
    private final String LOG_TAG = "RegisterFragment";

    private RegisterPresenter presenter;

    Button registerButton;
    EditText firstName;
    EditText lastName;
    EditText username;
    EditText password;

    Toast registeringToast;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        presenter = new RegisterPresenter(this);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_register, container, false);
        registerButton = view.findViewById(R.id.register_button);
        firstName = view.findViewById(R.id.register_first_name);
        lastName = view.findViewById(R.id.register_last_name);
        username = view.findViewById(R.id.register_username);
        password = view.findViewById(R.id.register_password);

        firstName.addTextChangedListener(this);
        lastName.addTextChangedListener(this);
        username.addTextChangedListener(this);
        password.addTextChangedListener(this);
        registerButton.setEnabled(false);

        registerButton.setOnClickListener((View v) -> {
            registeringToast = Toast.makeText(getContext(), getResources().getText(R.string.registering), Toast.LENGTH_LONG);
            registeringToast.show();
            RegisterRequest request = new RegisterRequest(firstName.getText().toString(),
                    lastName.getText().toString(), username.getText().toString(),
                    password.getText().toString());

            RegisterTask registerTask = new RegisterTask(presenter, this);
            registerTask.execute(request);

        });

        return view;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    /**
     * enables the register button once all the required fields are non-empty
     *
     * @param s the text that changed
     */
    @Override
    public void afterTextChanged(Editable s) {
        if (firstName.getText().toString().isEmpty() ||
                lastName.getText().toString().isEmpty() ||
                username.getText().toString().isEmpty() ||
                password.getText().toString().isEmpty()) {
            registerButton.setEnabled(false);
        }
        else {
            registerButton.setEnabled(true);
        }

    }

    /**
     * Called on successful registration. Starts the main application with the newly logged in user
     *
     * @param loginResponse the response from successful registration
     */
    @Override
    public void registerSuccessful(LoginResponse loginResponse) {
        Intent intent = new Intent(getContext(), MainActivity.class);

        intent.putExtra(MainActivity.LOGGED_IN_USER_KEY, loginResponse.getUser());
        intent.putExtra(MainActivity.AUTH_TOKEN_KEY, loginResponse.getAuthToken());

        registeringToast.cancel();
        startActivity(intent);
    }

    /**
     * Called on unsuccessful registration. Shows a toast with an error message
     *
     * @param loginResponse the response from the unsuccessful registration
     */
    @Override
    public void registerUnsuccessful(LoginResponse loginResponse) {
        Toast.makeText(getContext(), "Failed to register. " + loginResponse.getMessage(), Toast.LENGTH_LONG).show();
    }

    /**
     * Called when an exception was thrown while trying to register a new user.
     * Logs the exception and displays it as a toast.
     *
     * @param exception The thrown exception
     */
    @Override
    public void handleException(Exception exception) {
        Log.e(LOG_TAG, exception.getMessage(), exception);
        Toast.makeText(getContext(), "Failed to register because of exception: " + exception.getMessage(), Toast.LENGTH_LONG).show();

    }
}