package edu.byu.cs.tweeter.view.main;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import edu.byu.cs.tweeter.R;

public class ComposeTwitActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compose_twit);

        Button button = findViewById(R.id.send_twit_button);
        EditText editText = findViewById(R.id.twit);
        button.setEnabled(false);

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (editText.getText().toString().trim().isEmpty()) {
                    button.setEnabled(false);
                }
                else {
                    button.setEnabled(true);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        button.setOnClickListener((View v) -> {
            Intent resultIntent = new Intent();
            String result = editText.getText().toString();
            resultIntent.putExtra(MainActivity.TWIT_EXTRA, result);
            setResult(Activity.RESULT_OK, resultIntent);
            Toast.makeText(getApplicationContext(), getString(R.string.sending_twit), Toast.LENGTH_LONG).show();
            finish();
        });
    }
}
