package com.pangchavez.focusnumber;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void BtnGameModeClick(View view)
    {
        Button btn = (Button)view;
        Context btnContext = btn.getContext();
        Intent gameMode;
        String _mode = NumberGameActivity.FOUR_NUMBER_GAME_MODE;

        if(btn.getText().toString().equalsIgnoreCase(btnContext.getResources().getString(R.string.game_mode_two_number)))
        {
            _mode = NumberGameActivity.TWO_NUMBER_GAME_MODE;
        }

        gameMode = new Intent(this, NumberGameActivity.class);
        gameMode.putExtra("GAME_MODE", _mode);
        startActivity(gameMode);
    }
}
