package com.stone.njubbs.data;

import android.os.AsyncTask;
import android.util.Log;

/**
 * Created by Stone on 2016/12/8.
 */
public class TestAsyncTask extends AsyncTask<Void, Integer, Void> {
    private String taskName;

    public TestAsyncTask(String name) {
        super();
        taskName = name;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        Log.v("stone", "task = " + taskName + " onPostExecute in " + Thread.currentThread().toString());
    }

    @Override
    protected Void doInBackground(Void... params) {
        Log.v("stone", "task = " + taskName + " doInBackground in " + Thread.currentThread().toString());
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.toString();
        }
        return null;
    }
}
