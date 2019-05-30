package com.pangchavez.focusnumber;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class GameOverActivity extends AppCompatActivity {

    TextView txtFinalScore;
    Intent gameIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);

        gameIntent = getIntent();
        long score = gameIntent.getLongExtra("FINAL_SCORE", 0);

        txtFinalScore = findViewById(R.id.txtFinalScore);
        Log.v("SCORE:", score + "");

        txtFinalScore.setText(score + "");
    }

    public void goBackToMainMenu(View view)
    {
        Intent mainMenu = new Intent(this, MainActivity.class);
        startActivity(mainMenu);
    }

    public void tryAgain(View view)
    {
        Intent tryAgain = null;

        String previousActivity = gameIntent.getStringExtra("FROM_ACTIVITY");

        Log.v("PREVIOUS_ACTIVITY", previousActivity + " == " + FourNumberGameActivity.class.getSimpleName());
        if(previousActivity.contains("FourNumberGameActivity"))
        {
            tryAgain = new Intent(this, FourNumberGameActivity.class);
        } else {
            tryAgain = new Intent(this, TwoNumberGameActivity.class);
        }

        startActivity(tryAgain);
    }
}
