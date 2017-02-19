package com.example.jord.i7657043;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;



public class GameScreen extends AppCompatActivity {

    private Button btnResetMaze;
    private View nView;
    public static NetwalkGrid n;
    private int numOfTiles;
    private String playerName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_screen);

        btnResetMaze = (Button) findViewById(R.id.btnResetMaze);
        btnResetMaze.setOnClickListener(new btnListener1());
        Button btnHelp = (Button) findViewById(R.id.btnHelp);
        btnHelp.setOnClickListener(new btnListener1());

        nView = findViewById(R.id.customView);
        Intent i1 = getIntent();

        playerName = i1.getStringExtra("player");
        if (playerName.length()>0)
        {
            setLabel();
        }

        int difficulty = i1.getIntExtra("Diff",0);
        switch (difficulty)
        {
            case 1:
                numOfTiles = 3;
                break;
            case 2:
                numOfTiles = 5;
                break;
            case 3:
                numOfTiles = 7;
                break;
            default:
                numOfTiles = 3;
        }

        n = new NetwalkGrid(numOfTiles,numOfTiles);

    }

    private void setLabel()
    {
        TextView lblPlayerName = (TextView) findViewById(R.id.lblPlayerName);
        lblPlayerName.setText(getString(R.string.welcome_msg) + " " + playerName);

    }

    public class btnListener1 implements View.OnClickListener
    {
        @Override
        public void onClick(View v)
        {
            switch (v.getId())
            {
                case (R.id.btnResetMaze):
                    n = new NetwalkGrid(numOfTiles,numOfTiles);
                    resetTurnsLabel();
                    nView.invalidate();
                    break;

                case (R.id.btnHelp):
                    Intent i1 = new Intent(GameScreen.this,HelpScreen.class);
                    startActivity(i1);
                    break;
            }
        }

        //Update the turns label every time the game is reset
        public void resetTurnsLabel()
        {
            TextView turnsLabel;
            if (n.getTurn()>0)
            {
                turnsLabel = (TextView) findViewById(R.id.turnsLbl);
                String temp = "Turns: " + Integer.toString(n.getTurn());
                turnsLabel.setText(temp);
            }
            else
            {
                turnsLabel = (TextView) findViewById(R.id.turnsLbl);
                turnsLabel.setText(getResources().getString(R.string.turns_label));
            }
        }
    }
}
