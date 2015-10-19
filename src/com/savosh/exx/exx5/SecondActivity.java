package com.savosh.exx.exx5;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import com.savosh.exx.R;

public class SecondActivity extends Activity {

    private View loginView;
    private View catsView;
    private LinearLayout catsLayout; //need for DownloadImageTask

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.exx5_second_activity);

        loginView = findViewById(R.id.five_second_activity_login_layout);
        catsView = View.inflate(this, R.layout.exx3_first_activity, null);

        ((ViewGroup)findViewById(R.id.five_second_activity_frame)).addView(catsView);

        loginView.setVisibility(View.VISIBLE);
        catsView.setVisibility(View.GONE);

        catsLayout = (LinearLayout) catsView.findViewById(R.id.three_first_activity_linear_layout);

    }


    public void click(View v){
        switch (v.getId()){
            case R.id.five_second_activity_btn_login:{
                loginView.setVisibility(View.VISIBLE);
                catsView.setVisibility(View.GONE);
                break;
            }
            case R.id.five_second_activity_btn_cats:{
                loginView.setVisibility(View.GONE);
                catsView.setVisibility(View.VISIBLE);
                break;
            }
            case R.id.three_first_activity_btn_add:{
                new com.savosh.exx.exx3.FirstActivity.DownloadImageTask(this, catsLayout).execute();
            }
        }
    }


}
