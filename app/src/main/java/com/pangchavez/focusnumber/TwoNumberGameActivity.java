package com.pangchavez.focusnumber;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

public class TwoNumberGameActivity extends AppCompatActivity {

    // TwoNumberGame _game;
    GameNumber _game;
    Button firstNumber, secondNumber;
    TextView txtScore, txtChances;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_two_number_game);

        firstNumber = findViewById(R.id.btnFirstNumber);
        secondNumber = findViewById(R.id.btnSecondNumber);

        txtScore = findViewById(R.id.txtFinalScore);
        txtChances = findViewById(R.id.txtChancesRemaining);

        _game = new GameNumber(2);

        txtScore.setText(_game.getScore() + "");
        txtChances.setText(_game.getChancesRemaining() + "");

        createNewGame();
    }

    public void BtnChoiceClick(View view)
    {
        Button btn = (Button)view;
        int choice = Integer.parseInt(btn.getText().toString());

        if(_game.checkAnswer(choice))
        {
            // Add score
            // Then create a new game
            txtScore.setText(_game.getScore() + "");
            createNewGame();
        } else {
            // Make button red
            if(_game.getChancesRemaining() > 0){

                btn.setBackgroundColor(Color.RED);
            } else {
                // Go to GameOverActivity
                Intent gameOver = new Intent(this, GameOverActivity.class);
                gameOver.putExtra("FINAL_SCORE", _game.getScore());
                gameOver.putExtra("FROM_ACTIVITY", this.getClass().getSimpleName());
                startActivity(gameOver);
            }
        }

        txtScore.setText(_game.getScore() + "");
        txtChances.setText(_game.getChancesRemaining() + "");
    }

    private void createNewGame()
    {
        _game.createNewGame();

        firstNumber.setText(_game.getFirstNumber() + "");
        firstNumber.setBackgroundResource(android.R.drawable.btn_default);
        secondNumber.setText(_game.getSecondNumber() + "");
        secondNumber.setBackgroundResource(android.R.drawable.btn_default);
    }

}
