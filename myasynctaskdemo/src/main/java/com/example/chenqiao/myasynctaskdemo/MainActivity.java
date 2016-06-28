package com.example.chenqiao.myasynctaskdemo;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity {

    private ProgressBar pg_progressBar;
    private MyAsyncTask myAsyncTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pg_progressBar = (ProgressBar) findViewById(R.id.pg_progressBar);

    }

    @Override
    protected void onPause() {
        super.onPause();
//        if(myAsyncTask!=null&&myAsyncTask.getStatus()== AsyncTask.Status.RUNNING)
//        myAsyncTask.cancel(true);

    }

    public void progress(View view){

        myAsyncTask = new MyAsyncTask();
        myAsyncTask.execute();
    }




    class MyAsyncTask extends AsyncTask<Void,Integer,Void>{


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pg_progressBar.setVisibility(View.VISIBLE);
            Log.i("MyAsyncTask", "onPreExecute");
        }

        @Override
        protected Void doInBackground(Void... params) {
            Log.i("MyAsyncTask", "doInBackground");

            for (int i = 0;i<50;i++){
                if(isCancelled())
                    break;
                publishProgress(i);

                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }


            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            pg_progressBar.setMax(49);
            pg_progressBar.setProgress(values[0]);
        }


        @Override
        protected void onCancelled() {
            super.onCancelled();
            Log.i("MyAsyncTask", "onCancelled");
        }


        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            Log.i("MyAsyncTask", "onPostExecute");
        }




    }
}
