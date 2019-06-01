package com.pangchavez.focusnumber;

import android.content.Intent;
import android.graphics.Color;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

public class NumberGameActivity extends AppCompatActivity {

    public static final String TWO_NUMBER_GAME_MODE = "TwoNumberGame";
    public static final String FOUR_NUMBER_GAME_MODE = "FourNumberGame";

    GameNumber _game;
    Button firstNumber, secondNumber, thirdNumber, fourthNumber;
    TextView txtScore, txtChances, txtGameInstruction;
    String _mode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_number_game);

        Intent gameIntent = this.getIntent();
        _mode = gameIntent.getStringExtra("GAME_MODE");

        ConstraintLayout parent = findViewById(R.id.activity_number_game_layout);

        firstNumber = findViewById(R.id.btnFirstNumber);
        secondNumber = findViewById(R.id.btnSecondNumber);
        thirdNumber = findViewById(R.id.btnThirdNumber);
        fourthNumber = findViewById(R.id.btnFourthNumber);

        txtScore = findViewById(R.id.txtFinalScore);
        txtChances = findViewById(R.id.txtChancesRemaining);
        txtGameInstruction = findViewById(R.id.txtGameInstruction);

        if(_mode.equalsIgnoreCase(FOUR_NUMBER_GAME_MODE))
        {
            _game = new GameNumber(4);

        } else {
            _game = new GameNumber(2);

            // Remove extra buttons
            parent.removeView(thirdNumber);
            parent.removeView(fourthNumber);
        }

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
                btn.setBackgroundResource(R.drawable.button_shape_red);
            } else {
                // Go to GameOverActivity
                Intent gameOver = new Intent(this, GameOverActivity.class);
                gameOver.putExtra("FINAL_SCORE", _game.getScore());
                gameOver.putExtra("GAME_MODE", _mode);
                startActivity(gameOver);
            }
        }

        txtScore.setText(_game.getScore() + "");
        txtChances.setText(_game.getChancesRemaining() + "");
    }

    private void createNewGame()
    {
        _game.createNewGame();

        if(_game._mode.equalsIgnoreCase(GameNumber.BIG_MODE))
        {
            txtGameInstruction.setText("Click the BIGGER number.");
        } else {    // SMALL_MODE
            txtGameInstruction.setText("Click the SMALLER number");
        }


        firstNumber.setText(_game.getNumberWithPosition(1) + "");
        firstNumber.setBackgroundResource(R.drawable.button_shape);
        secondNumber.setText(_game.getNumberWithPosition(2) + "");
        secondNumber.setBackgroundResource(R.drawable.button_shape);

        if(_mode.equalsIgnoreCase(FOUR_NUMBER_GAME_MODE)) {

            if(_game._mode.equalsIgnoreCase(GameNumber.BIG_MODE))
            {
                txtGameInstruction.setText("Click the BIGGEST number.");
            } else {    // SMALL_MODE
                txtGameInstruction.setText("Click the SMALLEST number");
            }

            thirdNumber.setText(_game.getNumberWithPosition(3) + "");
            thirdNumber.setBackgroundResource(R.drawable.button_shape);
            fourthNumber.setText(_game.getNumberWithPosition(4) + "");
            fourthNumber.setBackgroundResource(R.drawable.button_shape);
        }
    }
}
