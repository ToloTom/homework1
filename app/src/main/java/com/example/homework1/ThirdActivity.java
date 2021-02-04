package com.example.homework1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ThirdActivity extends AppCompatActivity {

    private Button goHome;
    private TextView textView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        goHome = findViewById(R.id.goHome);
        textView = findViewById(R.id.finalResult);

        Intent intent = getIntent();

        textView.setText(intent.getStringExtra("compiledText"));
        textView.setTextSize(30);
        textView.setTextColor(getResources().getColor(R.color.black));

        goHome.setOnClickListener(v -> {
            launchNextActivity(v);
        });
    }

    private void launchNextActivity(View view){
        Intent intent = new Intent(ThirdActivity.this, MainActivity.class);
        startActivity(intent);
    }
}
