package com.example.numberguessinggame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {
    Button startButton;
    RadioButton radio2, radio3, radio4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startButton = findViewById(R.id.buttonStart);
        radio2 = findViewById(R.id.radio2);
        radio3 = findViewById(R.id.radio3);
        radio4 = findViewById(R.id.radio4);

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, GameActivity.class);
                if(!radio2.isChecked()&&radio3.isChecked()&&radio4.isChecked()){
                    Snackbar.make(v, "You need to select a number of digits", Snackbar.LENGTH_SHORT).show();
                }else {
                    if (radio2.isChecked()) {
                        intent.putExtra("two", true);
                    } else if (radio3.isChecked()) {
                        intent.putExtra("three", true);
                    } else if (radio4.isChecked()) {
                        intent.putExtra("four", true);
                                            }
                    startActivity(intent);
                }

            }
        });
    }
}