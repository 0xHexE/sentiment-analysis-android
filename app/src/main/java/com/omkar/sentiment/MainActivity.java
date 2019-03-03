package com.omkar.sentiment;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    public static final MediaType JSON
            = MediaType.get("application/json; charset=utf-8");

    OkHttpClient client = new OkHttpClient();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Thread thread = new Thread(new Runnable() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void run() {
                try {
                    final String text = summaryGenerator("John is great also awesome also he is working at google as data scientist. He also use firebase angular js typescript also system is greate", "Test");
                    Log.d("OMKAR", text);
                } catch (Exception e) {
                    Log.e("OMKAR", e.getMessage());
                }
            }
        });
        thread.start();
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    String summaryGenerator(String myInputString, String title) throws JSONException {

        final JSONObject jsonObject = new JSONObject();

        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("title", title)
                .addFormDataPart("text", myInputString)
                .build();

        Request request = new Request.Builder()
                .url("https://api.aylien.com/api/v1/summarize")
                .addHeader("X-AYLIEN-TextAPI-Application-Key", "feb9d044e201df8c926602346d98de68")
                .addHeader("X-AYLIEN-TextAPI-Application-ID", "785ffb7d")
                .post(requestBody)
                .build();

        try (Response response = client.newCall(request).execute()) {
                assert response.body() != null;
            return (new JSONObject(response.body().string())).getString("text");
        } catch (IOException e) {
            e.printStackTrace();
            return "Could not resolve";
        }

    }

}
