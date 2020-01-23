package com.example.xp.eggtimer;

import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    // Variable initialise
    SeekBar seekBar;
    TextView timerTextView;
    CountDownTimer counter;
    Button stopButton;
    Button startButton;

    // Update timer text to match the time left
    public void updateTimer(int secondsLeft) {
        int minutes = secondsLeft / 60;
        int seconds = secondsLeft - minutes * 60;

        String secondsString = Integer.toString(seconds);
        String minutesString = Integer.toString(minutes);

        if (minutes < 10)
            minutesString = "0" + minutesString;

        if (seconds < 10)
            secondsString = "0" + secondsString;


        timerTextView.setText(minutesString + ":" + secondsString);

    }

    // start or stop timer when button clicked

    public void runTimer(View view) {

        if (view.getTag().toString().equals("0")) {
            view.setVisibility(View.INVISIBLE);
            seekBar.setVisibility(View.INVISIBLE);
            stopButton.setVisibility(View.VISIBLE);

            counter = new CountDownTimer(seekBar.getProgress() * 1000 + 100, 1000) {

                @Override
                public void onTick(long millisUntilFinished) {
                    updateTimer((int) millisUntilFinished / 1000);

                }

                @Override
                public void onFinish() {

                    timerTextView.setText("00:00");
                    MediaPlayer mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.airhorn);
                    mediaPlayer.start();
                }
            }.start();
        } else {
            seekBar.setVisibility(View.VISIBLE);
            view.setVisibility(View.INVISIBLE);
            startButton.setVisibility(View.VISIBLE);
            counter.cancel();

        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // View Init
        seekBar = (SeekBar) findViewById(R.id.seekBar);
        timerTextView = (TextView) findViewById(R.id.timerTextView);
        stopButton = (Button) findViewById(R.id.stopButton);
        startButton = (Button) findViewById(R.id.startButton);
        // Set SeekBar Values

        seekBar.setMax(600);
        seekBar.setProgress(30);


        // On seekBar Changer
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                updateTimer(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }
}
