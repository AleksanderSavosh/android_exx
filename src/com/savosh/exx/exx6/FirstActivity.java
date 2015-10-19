package com.savosh.exx.exx6;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import com.savosh.exx.R;

public class FirstActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.exx6_first_activity);
    }


    public void click(View v){
        switch (v.getId()){
            case R.id.six_first_layout_btn:{
                Toast.makeText(this, "OK", Toast.LENGTH_LONG).show();
                break;
            }
        }
    }
}
