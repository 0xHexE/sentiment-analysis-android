package com.omkar.sentiment;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.aylien.textapi.TextAPIClient;
import com.aylien.textapi.TextAPIException;
import com.aylien.textapi.parameters.SentimentParams;
import com.aylien.textapi.responses.Sentiment;

public class MainActivity extends AppCompatActivity {
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    TextAPIClient client = new TextAPIClient("785ffb7d", "feb9d044e201df8c926602346d98de68");
    SentimentParams.Builder builder = SentimentParams.newBuilder();
    builder.setText("John is a very good football player");
    Sentiment sentiment = null;
    try {
      sentiment = client.sentiment(builder.build());
    } catch (TextAPIException e) {
      e.printStackTrace();
    }

    assert sentiment != null;

    View parentLayout = findViewById(android.R.id.content);
    System.out.println(sentiment);
  }

}
