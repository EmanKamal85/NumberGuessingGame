package com.example.numberguessinggame;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class GameActivity extends AppCompatActivity {

    TextView lastGuess, remainingRights, tips;
    EditText guessedNumber;
    Button confirm;
    boolean twoDigits, threeDigits, fourDigits;
    int rights = 10;
    Random r = new Random();
    int random;
    String guess;
    ArrayList<Integer> guessesList = new ArrayList<>();
    String guessesListString = "[ ";
    int userAttempts = 0;
    int g=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        lastGuess = findViewById(R.id.last_guess_textview);
        remainingRights = findViewById(R.id.remaining_rights_textview);
        tips = findViewById(R.id.tip_textview);
        guessedNumber = findViewById(R.id.guess);
        confirm = findViewById(R.id.buttonConfirm);

        Intent intent = getIntent();
        twoDigits = intent.getBooleanExtra("two", false);
        threeDigits = intent.getBooleanExtra("three", false);
        fourDigits = intent.getBooleanExtra("four", false);

        if (twoDigits){
            random = r.nextInt(90)+10;
        }
        if (threeDigits){
            random = r.nextInt(900)+100;
        }
        if(fourDigits){
            random = r.nextInt(9000)+1000;
        }



        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                guess = guessedNumber.getText().toString().trim();


                if(guess.equals(" ")){
                    Toast.makeText(GameActivity.this, "You should guess a number", Toast.LENGTH_SHORT).show();
                }else {

                    lastGuess.setVisibility(View.VISIBLE);
                    remainingRights.setVisibility(View.VISIBLE);
                    tips.setVisibility(View.VISIBLE);
                    rights--;
                    userAttempts++;


                    //int g = String.valueOf(guess);

                        g =Integer.parseInt(guess);
                    
                    //guessesListString = guessesListString+ " "+g;
                    guessesList.add(g);
                    remainingRights.setText("Your remaining rights: " + rights);
                    lastGuess.setText("Your last guess: "+g);

                    if (random>g){
                        tips.setText("Increase your guess");
                    }
                    if (random<g){
                        tips.setText("Decrease your guess");
                    }


                    if(g==random){
                AlertDialog.Builder builder = new AlertDialog.Builder(GameActivity.this);
                builder.setCancelable(false);
                builder.setTitle("Guessing Game Finish");
                builder.setMessage("Congratulations!, You guessed the number correctly after "+ userAttempts+ " attempts.\n\n your guesses are: "+ guessesList+"\n\n Would you like to continue playing?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(GameActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                   moveTaskToBack(true);
                   android.os.Process.killProcess(android.os.Process.myPid());
                   System.exit(1);
                    }
                });
                builder.create().show();
                    }


                    if (rights == 0){
                AlertDialog.Builder builder = new AlertDialog.Builder(GameActivity.this);
                builder.setCancelable(false);
                builder.setTitle("Guessing Game Finish");
                builder.setMessage("Sorry! you lost this round.\n\n Your guesses are: "+guessesList +"\n\n Would you like to play again?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(GameActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();;
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        moveTaskToBack(true);
                        android.os.Process.killProcess(android.os.Process.myPid());
                        System.exit(1);
                    }
                });
                builder.create().show();
                    }

                    guessedNumber.setText(" ");

                }
            }
        });
    }
}