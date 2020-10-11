package edu.byu.cs.tweeter.view;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import edu.byu.cs.tweeter.R;

public class LoginFragment extends Fragment implements TextWatcher {
    Button loginButton;
    EditText username;
    EditText password;
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
}
