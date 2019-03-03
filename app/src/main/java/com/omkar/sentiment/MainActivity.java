package com.omkar.sentiment;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import com.aylien.textapi.TextAPIClient;
import com.aylien.textapi.TextAPIException;
import com.aylien.textapi.parameters.SummarizeParams;
import com.aylien.textapi.responses.Summarize;

public class MainActivity extends AppCompatActivity {
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    this.summaryGenerator("John is great", "test");
    this.summaryGenerator("John is great also awesome", "Test");
  }

  String summaryGenerator(String myInputString, String title) {
    TextAPIClient client = new TextAPIClient("785ffb7d", "feb9d044e201df8c926602346d98de68");

    SummarizeParams.Builder builder = SummarizeParams.newBuilder();
    builder.setText(myInputString);
    builder.setTitle(title);

    builder.setNumberOfSentences(2);

    Summarize sentiment = null;

    try {
      sentiment = client.summarize(builder.build());
    } catch (TextAPIException e) {
      e.printStackTrace();
    }

    assert sentiment != null;

    return sentiment.getText();
  }

}
