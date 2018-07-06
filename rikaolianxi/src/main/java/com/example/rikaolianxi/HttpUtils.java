package com.example.rikaolianxi;

import android.content.Context;
import android.os.AsyncTask;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpUtils {
     public static void getdata(final String path , Context context , final JsonBack jsonBack){
                 AsyncTask<String,Void,String> asyncTask = new AsyncTask<String, Void, String>() {
                     @Override
                     protected String doInBackground(String... strings) {
                         String json = "";

                         try {
                             URL url = new URL(path);
                            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

                            urlConnection.setRequestMethod("GET");
                            if (urlConnection.getResponseCode() == 200){
                                InputStream inputStream = urlConnection.getInputStream();
                                json = SteamToString.getString(inputStream);
                            }


                         } catch (Exception e) {
                             e.printStackTrace();
                         }
                         return json;
                     }

                     @Override
                     protected void onPostExecute(String s) {
                         super.onPostExecute(s);
                         jsonBack.getjsondata(s);
                     }

                 };
                 asyncTask.execute();
             }
}
