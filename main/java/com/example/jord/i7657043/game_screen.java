package com.example.jord.i7657043;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;



public class game_screen extends AppCompatActivity {

    Button btnResetMaze, btnHelp, btnExitGameScreen;
    private TextView lblTurns,lblPlayerName;
    private View custView;
    String playerName;
    int difficulty;

    public static Game game;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_screen);
        Intent i1 = getIntent();

        //Get the difficulty level, selected by the dialog
        difficulty = i1.getIntExtra("Diff",0);
        playerName = i1.getStringExtra("player");

        //If player has set a name show it
        if (playerName.length()>0) {
            lblPlayerName = (TextView) findViewById(R.id.lblPlayerName);
            lblPlayerName.setText(getString(R.string.welcome_msg) + " " + playerName);
        }
        else
        {
            playerName = "default";
        }

        //create new Game
        game = new Game(difficulty, playerName);



        custView = (View) findViewById(R.id.customView);
        btnResetMaze = (Button) findViewById(R.id.btnResetMaze);
        btnResetMaze.setOnClickListener(new btnListener1());
        btnHelp = (Button) findViewById(R.id.btnHelp);
        btnHelp.setOnClickListener(new btnListener1());

    }

    public class btnListener1 implements View.OnClickListener
    {
        @Override
        public void onClick(View v)
        {
            switch (v.getId())
            {
                case (R.id.btnResetMaze):
                    game = new Game(difficulty, playerName);
                    custView.invalidate();
                    break;

                case (R.id.btnHelp):
                    Intent i1 = new Intent(game_screen.this,help_screen.class);
                    startActivity(i1);
                    break;
            }
        }
    }
}
