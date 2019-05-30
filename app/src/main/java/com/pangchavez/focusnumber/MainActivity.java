package com.pangchavez.focusnumber;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void BtnTwoNumberGame(View view) {
        Intent TwoNumberGame = new Intent(this, TwoNumberGameActivity.class);
        startActivity(TwoNumberGame);
    }

    public void BtnFourNumberGame(View view)
    {
        Intent FourNumberGame = new Intent(this, FourNumberGameActivity.class);
        startActivity(FourNumberGame);
    }
}
