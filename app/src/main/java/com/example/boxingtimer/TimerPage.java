package com.example.boxingtimer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

public class TimerPage extends MainActivity {
    private int roundNum;
// variables for the timer
    private TextView countdownText;
    private Button countdownButton;
    private CountDownTimer countDownTimer;
    private CountDownTimer restTimer;

    private long timeLeft;
    private boolean timerRunning;
    private long startTime;
    private long lenRest;
    private long lenRound;
    private long restLeft;

    private int counter = 0;
    private boolean change;

    MediaPlayer player;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer_page);
        //BRINGS VARIABLES OVER
        bringOver();

        change = true;

        //timer stuff
        countdownText = findViewById(R.id.countdownText);
        countdownButton = findViewById(R.id.countdownButton);
        countdownButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startStop();
            }
        });

        updateTimer();

    }
    //BRINGS OVER THE VARIABLES

    public void bringOver(){
        Intent intent = getIntent();
        String roundsNum = intent.getStringExtra("key1");
        String roundLen = intent.getStringExtra("key2");
        String restLen = intent.getStringExtra("key3");

        roundNum = Integer.parseInt(roundsNum);

        switch (roundLen){
            case "1":
                timeLeft = 60000;
                break;
            case "2":
                timeLeft = 120000;
                break;
            case "3":
                timeLeft = 180000;
                break;
            case "4":
                timeLeft = 240000;
                break;
            case "5":
                timeLeft = 300000;
                break;
            case "6":
                timeLeft = 360000;
                break;
            case "7":
                timeLeft = 420000;
                break;
            case "8":
                timeLeft = 480000;
                break;
            case "9":
                timeLeft = 540000;
                break;
            case "10":
                timeLeft = 600000;
                break;
        }
        startTime = timeLeft;
        lenRound = timeLeft;

        switch (restLen){
            case "1":
                lenRest = 60000;
                break;
            case "2":
                lenRest = 120000;
                break;
            case "3":
                lenRest = 180000;
                break;
            case "4":
                lenRest = 240000;
                break;
            case "5":
                lenRest = 300000;
                break;
        }
        restLeft = lenRest;
    }

    public void startStop(){
        if (timerRunning){
            stopTimer();
        }
        else{
            startTimer();
        }
    }
    
    public void startTimer(){
        countDownTimer = new CountDownTimer(timeLeft, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeLeft = millisUntilFinished;
                updateTimer();
            }

            @Override
            public void onFinish() {
                playSound();
                timeLeft =  startTime;
                counter++;
                if (change && counter < roundNum){
                    setRestTimer();
                }
            }
        }.start();

        timerRunning = true;
        countdownButton.setText("STOP");
    }

    public void setRestTimer(){
        change = !change;

        restTimer = new CountDownTimer(restLeft,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                restLeft = millisUntilFinished;
                updateTimer();
            }

            @Override
            public void onFinish() {
                playSound();
                restLeft = lenRest;
                change = !change;
                startTimer();
            }
        }.start();
    }

    public void stopTimer(){
        countDownTimer.cancel();
        timerRunning = false;
        countdownButton.setText("START");
    }

    public void updateTimer(){
        if (change){
            int minutes = (int) (timeLeft/1000) / 60;
            int seconds = (int) (timeLeft/1000) % 60;
            String timeLeftText = String.format(Locale.getDefault(),"%02d:%02d", minutes, seconds);
            countdownText.setText(timeLeftText);
        }
        else{
            int minutes = (int) (restLeft/1000) / 60;
            int seconds = (int) (restLeft/1000) % 60;
            String timeLeftText = String.format(Locale.getDefault(),"%02d:%02d", minutes, seconds);
            countdownText.setText(timeLeftText);
        }
    }

    public void playSound(){
        if (player == null){
            player = MediaPlayer.create(this,R.raw.beep);
        }
        player.start();
    }
}
