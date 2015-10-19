package com.savosh.exx.exx2;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.savosh.exx.R;

public class FirstActivity extends Activity {

    private EditText edtxtOne;
    private EditText edtxtTwo;
    private Button btnOne;
    private Button btnTwo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.exx2_first_activity);

        edtxtOne = (EditText) findViewById(R.id.two_first_activity_edtxt_one);
        edtxtTwo = (EditText) findViewById(R.id.two_first_activity_edtxt_two);

        btnOne = (Button) findViewById(R.id.two_first_activity_btn_one);
        btnTwo = (Button) findViewById(R.id.two_first_activity_btn_two);

    }

    public void click(View v){
        Log.v(getClass().getName(),"edtxtOne is " + edtxtOne);
        Log.v(getClass().getName(),"edtxtTwo is " + edtxtTwo);
        Log.v(getClass().getName(),"btnOne is " + btnOne);
        Log.v(getClass().getName(),"btnTwo is " + btnTwo);

        switch (v.getId()){
            case R.id.two_first_activity_btn_one:{
                String temp = edtxtOne.getText().toString();
                edtxtOne.setText(edtxtTwo.getText().toString());
                edtxtTwo.setText(temp);
                break;
            }
            case R.id.two_first_activity_btn_two:{
                edtxtTwo.setText("" + edtxtTwo.getText() + edtxtOne.getText());
                break;
            }
        }
    }

}
