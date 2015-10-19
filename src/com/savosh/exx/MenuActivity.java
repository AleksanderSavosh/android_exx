package com.savosh.exx;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import com.savosh.exx.exx10.DownloadGifUrlsTaskByBitSteps;
import com.savosh.exx.exx10.GifReflectArrayAdapter;

public class MenuActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu);
    }


    public void click(View v){
        Toast.makeText(this, "id: " + v.getId(), Toast.LENGTH_LONG).show();

        switch (v.getId()){
            case R.id.menu_btn_exx1:{
                Intent intent = new Intent(this, com.savosh.exx.exx1.FirstActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.menu_btn_exx2:{
                Intent intent = new Intent(this, com.savosh.exx.exx2.FirstActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.menu_btn_exx3_1:{
                Intent intent = new Intent(this, com.savosh.exx.exx3.FirstActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.menu_btn_exx3_2:{
                Intent intent = new Intent(this, com.savosh.exx.exx3.SecondActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.menu_btn_exx4:{
                Intent intent = new Intent(this, com.savosh.exx.exx4.FirstActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.menu_btn_exx5_1:{
                Intent intent = new Intent(this, com.savosh.exx.exx5.FirstActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.menu_btn_exx5_2:{
                Intent intent = new Intent(this, com.savosh.exx.exx5.SecondActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.menu_btn_exx6:{
                Intent intent = new Intent(this, com.savosh.exx.exx6.FirstActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.menu_btn_exx7_1:{
                Intent intent = new Intent(this, com.savosh.exx.exx7.FirstActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.menu_btn_exx7_2:{
                Intent intent = new Intent(this, com.savosh.exx.exx7.SecondActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.menu_btn_exx8:{
                Intent intent = new Intent(this, com.savosh.exx.exx8.SplashScreenActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.menu_btn_exx9:{
                Intent intent = new Intent(this, com.savosh.exx.exx9.FirstActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.menu_btn_exx10:{
                Intent intent = new Intent(this, com.savosh.exx.exx9.FirstActivity.class);
                intent.putExtra("outerAdapter", GifReflectArrayAdapter.class);
                intent.putExtra("outerTask", DownloadGifUrlsTaskByBitSteps.class);
                startActivity(intent);
                break;
            }
            case R.id.menu_btn_exx11:{
                Intent intent = new Intent(this, com.savosh.exx.exx11.SplashScreenActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.menu_btn_exx12:{
                Intent intent = new Intent(this, com.savosh.exx.exx12.MainActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.menu_btn_exx13:{
                Intent intent = new Intent(this, com.savosh.exx.exx13.MainActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.menu_btn_exx14:{
                Intent intent = new Intent(this, com.savosh.exx.exx14.MainActivity.class);
                startActivity(intent);
                break;
            }
        }


    }

}
