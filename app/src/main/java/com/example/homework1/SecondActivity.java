package com.example.homework1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

public class SecondActivity extends AppCompatActivity {

    private LinearLayout linearLayout;
    private TextInputEditText text;
    private TextView textView;
    private Button generate;
    private int numBlanks;
    private int numLines;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        linearLayout = findViewById(R.id.listOfBlanks);
        textView = findViewById(R.id.storyTitle);
        generate = findViewById(R.id.generateButton);

        generate.setOnClickListener(v -> {
            if(checkInputs() == false){
                launchNextActivity(v);
            }
        });


        Intent intent = getIntent();

        textView.setText(intent.getStringExtra("title"));
        textView.setTextSize(24);
        textView.setTextColor(getResources().getColor(android.R.color.black));
        textView.setPadding(0,8,0,8);

        numBlanks = Integer.parseInt(intent.getStringExtra("numBlanks"));

        for(int i = 0; i < numBlanks; i++){
            text = new TextInputEditText(this);
            text.setId(i + 1);
            text.setText("");
            text.setTextColor(getResources().getColor(android.R.color.black));
            textView = new TextView(this);
            textView.setText(intent.getStringExtra("blanks" + i));
            textView.setTextSize(12);
            textView.setTextColor(getResources().getColor(android.R.color.black));
            textView.setPadding(12, 0, 0 ,0);
            linearLayout.addView(text);
            linearLayout.addView(textView);
        }
    }

    private boolean checkInputs(){

        boolean status = false;
        for(int i = 0; i < numBlanks; i++){
            text = findViewById(i + 1);

            if(!text.getText().toString().equals("")) {
                String input = text.getText().toString();

                while (input.charAt(0) == ' ') {
                    input = input.substring(1);
                    text.setText(input);
                }

                while (input.charAt(input.length() - 1) == ' ') {
                    /// MAKE SURE THIS CORRECTLY REACHES THE END OF THE STRING
                    input = input.substring(0, input.length() - 1);
                    text.setText(input);
                }
            }

            else{
                Toast toast = Toast.makeText(this, "Missing Field", Toast.LENGTH_SHORT);
                toast.show();
                status = true;
                break;
            }
        }
        return status;
    }

    private String createText(){

        Intent intent = getIntent();
        String compiledText = "";
        int lastLine = 0;
        for (int i = 0; i < numBlanks; i++){

            text = findViewById(i + 1);
            compiledText += intent.getStringExtra("value" + i) + text.getText().toString();
            lastLine = i;
        }
        lastLine += 1;
            compiledText += intent.getStringExtra("value" + lastLine);
        return compiledText;
    }

    private void launchNextActivity(View view){
        Intent intent = new Intent(SecondActivity.this, ThirdActivity.class);
        intent.putExtra("compiledText", createText());
        startActivity(intent);
    }
}
