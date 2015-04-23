package com.savosh.exx.seven;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import com.savosh.exx.R;

public class FirstActivity extends Activity {


    private TextView textView;

    private class ProgressTask extends AsyncTask<Void, Integer, Void>{

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            textView.setText(values[0] + "%");
        }

        @Override
        protected Void doInBackground(Void... params) {
            int progress = 0;
            publishProgress(progress);
            while (progress < 100 && !isCancelled()){
                progress++;
                try {
                    Thread.sleep(100);
                } catch (Exception e){
                    Log.e(getClass().getName(), e.getMessage());
                    Log.d(getClass().getName(), e.getMessage(), e);
                }
                publishProgress(progress);
            }

            return null;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.seven_first_activity);
        textView = (TextView) findViewById(R.id.seven_first_activity_txt);
        textView.setText("");
    }


    private ProgressTask progressTask;
    public void click(View v){
        switch (v.getId()){
            case R.id.seven_first_activity_btn_start:{
                if(progressTask == null){
                    progressTask = new ProgressTask();
                    progressTask.execute();
                    return;
                }

                if(progressTask.getStatus() == AsyncTask.Status.PENDING){
                    progressTask.execute();
                    return;
                }

                if(progressTask.getStatus() == AsyncTask.Status.RUNNING){
                    progressTask.cancel(true);
                    progressTask = new ProgressTask();
                    progressTask.execute();
                    return;
                }

                if(progressTask.getStatus() == AsyncTask.Status.FINISHED){
                    progressTask = new ProgressTask();
                    progressTask.execute();
                    return;
                }
                break;
            }
            case R.id.seven_first_activity_btn_stop: {
                if(progressTask == null){
                    return;
                }
                if(progressTask.getStatus() == AsyncTask.Status.RUNNING){
                    progressTask.cancel(true);
                }
                break;
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(progressTask != null && progressTask.getStatus() == AsyncTask.Status.RUNNING){
            progressTask.cancel(true);
        }
    }
}
