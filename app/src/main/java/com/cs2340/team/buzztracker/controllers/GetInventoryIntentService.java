package com.cs2340.team.buzztracker.controllers;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * for making call to database
 */
public class GetInventoryIntentService extends IntentService {

    /**
     * naming the intent service
     */
    public GetInventoryIntentService() {
        super("CheckRegistrationIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {

        URL url;
        HttpURLConnection urlConnection = null;
        String output = "";

        try {

            url = new URL("http://10.0.2.2:80/buzzTrackerScripts/getInventory.php?"
                    + "location=" + intent.getStringExtra("location"));

            urlConnection = (HttpURLConnection) url.openConnection();

            InputStream inputStream = urlConnection.getInputStream();

            InputStreamReader isw = new InputStreamReader(inputStream);

            int data = isw.read();
            while (data != -1) {
                char current = (char) data;
                data = isw.read();
                output = output + current;
            }

        } catch (Exception e) {
            Log.e("error",e.toString());
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
        }

        Intent broadcastIntent = new Intent();
        broadcastIntent.setAction(LocationDetailActivity.GetInventoryResponseReceiver.ACTION_RESP);
        broadcastIntent.addCategory(Intent.CATEGORY_DEFAULT);
        broadcastIntent.putExtra("output", output);
        sendBroadcast(broadcastIntent);
    }

}