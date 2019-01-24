package com.example.fergu.myapplication;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private MyCountDownTimer countDownTimer;
    private long timeElapsed;
    private boolean timerHasStarted = false;
    private Button startB;
    private TextView text;
    private TextView timeElapsedView;
    private final long startTime = 50 * 1000;
    private final long interval = 1 * 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startB = (Button) this.findViewById(R.id.button);
        startB.setOnClickListener(this);
        text = (TextView) this.findViewById(R.id.timer);
        timeElapsedView = (TextView) this.findViewById(R.id.timeElapsed);
        countDownTimer = new MyCountDownTimer(startTime, interval);
        text.setText(text.getText() + String.valueOf(startTime));
    }
    @Override
    public void onClick(View v) {
        if(!timerHasStarted){
            countDownTimer.start();
            timerHasStarted = true;
            startB.setText(R.string.start);
        }
        else{
            countDownTimer.cancel();
            timerHasStarted=false;
            startB.setText(R.string.reset);
        }
    }
    public class MyCountDownTimer extends CountDownTimer{
        public MyCountDownTimer(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);

        }

        @Override
        public void onTick(long millisUntilFinished) {
            text.setText(getString(R.string.remain) + Long.toString(millisUntilFinished));
            timeElapsed = startTime - millisUntilFinished;
            timeElapsedView.setText(getString(R.string.elapsed )+ String.valueOf(timeElapsed));
        }

        @Override
        public void onFinish() {
            text.setText(R.string.up);
            timeElapsedView.setText(getString(R.string.elapsed)+ String.valueOf(startTime));
        }
    }

}