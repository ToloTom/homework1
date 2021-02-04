package com.example.homework1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class MainActivity extends AppCompatActivity {

    private static final String api_url = "http://madlibz.herokuapp.com/api/random";
    private static AsyncHttpClient client = new AsyncHttpClient();
    private Button goButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        goButton = findViewById(R.id.startButton);

        goButton.setOnClickListener(v -> {
                launchNextActivity(v);
        });

        }

        private void launchNextActivity(View view){

        client.get(api_url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {

                try {
                    JSONObject json = new JSONObject(new String (responseBody));

                    Intent intent = new Intent(MainActivity.this, SecondActivity.class);

                    String numBlanks = String.valueOf(json.getJSONArray("blanks").length());

                    intent.putExtra("title", json.get("title").toString());

                    intent.putExtra("numBlanks", numBlanks);
                    for(int i = 0; i < json.getJSONArray("blanks").length() + 0; i++){
                        intent.putExtra("blanks" + i, json.getJSONArray("blanks").get(i).toString());
                    }

                    for(int i = 0; i < json.getJSONArray("value").length(); i++){
                        intent.putExtra("value" + i, json.getJSONArray("value").get(i).toString());
                    }

                    startActivity(intent);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });

    }
}