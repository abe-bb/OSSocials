package edu.byu.cs.tweeter.view.login;

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

public class RegisterFragment extends Fragment implements TextWatcher {
    Button registerButton;
    EditText firstName;
    EditText lastName;
    EditText username;
    EditText password;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.register_fragment, container, false);
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

        return view;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

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
}
