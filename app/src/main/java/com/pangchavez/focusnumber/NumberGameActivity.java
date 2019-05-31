package com.pangchavez.focusnumber;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class NumberGameActivity extends AppCompatActivity {

    public static final String TWO_NUMBER_GAME_MODE = "TwoNumberGame";
    public static final String FOUR_NUMBER_GAME_MODE = "FourNumberGame";

    GameNumber _game;
    Button firstNumber, secondNumber, thirdNumber, fourthNumber;
    TextView txtScore, txtChances;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_number_game);

        Intent gameIntent = this.getIntent();
        String _mode = gameIntent.getStringExtra("GAME_MODE");

        firstNumber = findViewById(R.id.btnFirstNumber);
        secondNumber = findViewById(R.id.btnSecondNumber);

        txtScore = findViewById(R.id.txtFinalScore);
        txtChances = findViewById(R.id.txtChancesRemaining);

        _game = new GameNumber(4);

        txtScore.setText(_game.getScore() + "");
        txtChances.setText(_game.getChancesRemaining() + "");

        createNewGame();

        if(_mode == FOUR_NUMBER_GAME_MODE)
        {
            modifyToFourNumberGameMode();
        }
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

        firstNumber.setText(_game.getNumberWithPosition(1) + "");
        firstNumber.setBackgroundResource(android.R.drawable.btn_default);
        secondNumber.setText(_game.getNumberWithPosition(2) + "");
        secondNumber.setBackgroundResource(android.R.drawable.btn_default);
    }

    private void modifyToFourNumberGameMode()
    {
        thirdNumber = findViewById(R.id.btnThirdNumber);
        fourthNumber = findViewById(R.id.btnFourthNumber);

        thirdNumber.setText(_game.getNumberWithPosition(3) + "");
        thirdNumber.setBackgroundResource(android.R.drawable.btn_default);
        fourthNumber.setText(_game.getNumberWithPosition(4) + "");
        fourthNumber.setBackgroundResource(android.R.drawable.btn_default);
    }
}
