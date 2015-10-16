package com.savosh.exx;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import com.savosh.exx.ten.DownloadGifUrlsTaskByBitSteps;
import com.savosh.exx.ten.GifReflectArrayAdapter;

public class MenuActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu);
    }


    public void click(View v){
        Toast.makeText(this, "id: " + v.getId(), Toast.LENGTH_LONG).show();

        switch (v.getId()){
            case R.id.menu_btn_exx_one:{
                Intent intent = new Intent(this, com.savosh.exx.one.FirstActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.menu_btn_exx_two:{
                Intent intent = new Intent(this, com.savosh.exx.two.FirstActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.menu_btn_exx_three_dot_one:{
                Intent intent = new Intent(this, com.savosh.exx.three.FirstActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.menu_btn_exx_three_dot_two:{
                Intent intent = new Intent(this, com.savosh.exx.three.SecondActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.menu_btn_exx_four:{
                Intent intent = new Intent(this, com.savosh.exx.four.FirstActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.menu_btn_exx_five_dot_one:{
                Intent intent = new Intent(this, com.savosh.exx.five.FirstActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.menu_btn_exx_five_dot_two:{
                Intent intent = new Intent(this, com.savosh.exx.five.SecondActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.menu_btn_exx_six:{
                Intent intent = new Intent(this, com.savosh.exx.six.FirstActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.menu_btn_exx_seven_dot_one:{
                Intent intent = new Intent(this, com.savosh.exx.seven.FirstActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.menu_btn_exx_seven_dot_two:{
                Intent intent = new Intent(this, com.savosh.exx.seven.SecondActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.menu_btn_exx_eight:{
                Intent intent = new Intent(this, com.savosh.exx.eight.SplashScreenActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.menu_btn_exx_nine:{
                Intent intent = new Intent(this, com.savosh.exx.nine.FirstActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.menu_btn_exx_ten:{
                Intent intent = new Intent(this, com.savosh.exx.nine.FirstActivity.class);
                intent.putExtra("outerAdapter", GifReflectArrayAdapter.class);
                intent.putExtra("outerTask", DownloadGifUrlsTaskByBitSteps.class);
                startActivity(intent);
                break;
            }
            case R.id.menu_btn_exx_eleven:{
                Intent intent = new Intent(this, com.savosh.exx.eleven.SplashScreenActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.menu_btn_exx_twelve:{
                Intent intent = new Intent(this, com.savosh.exx.twelve.MainActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.menu_btn_exx_thirteen:{
                Intent intent = new Intent(this, com.savosh.exx.thirteen.MainActivity.class);
                startActivity(intent);
                break;
            }
        }


    }

}
