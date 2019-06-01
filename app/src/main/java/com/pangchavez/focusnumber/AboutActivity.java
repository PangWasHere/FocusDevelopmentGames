package com.pangchavez.focusnumber;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class AboutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
    }

    public void btnClick(View view)
    {
        ImageButton btn = (ImageButton)view;
        if(btn.getId() == R.id.btnGitHub)
        {
            // Go to GitHub page
            Uri githubURI = Uri.parse("http://www.github.com/PangWasHere");
            Intent visitGitHub = new Intent(Intent.ACTION_VIEW, githubURI);
            startActivity(visitGitHub);
        } else if(btn.getId() == R.id.btnGoBack)
        {
            Intent mainMenu = new Intent(this, MainActivity.class);
            startActivity(mainMenu);
        }
    }
}
