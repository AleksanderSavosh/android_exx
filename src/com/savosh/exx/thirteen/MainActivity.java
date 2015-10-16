package com.savosh.exx.thirteen;

import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import com.savosh.exx.R;

public class MainActivity extends AppCompatActivity {

    TextInputLayout phone;
    EditText phoneEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.thirteen_main_activity);

        //Displaying TextInputLayout Error
        phone = (TextInputLayout) findViewById(R.id.thirteen_phone);
        phone.setErrorEnabled(true);
        phoneEditText = phone.getEditText();
        phoneEditText.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (phoneEditText.getText().toString().length() != 13) {
                    phone.setError("Length of phone number must be equal 13 characters");
                } else {
                    phone.setError("");
                }
                return false;
            }
        });

    }
}
