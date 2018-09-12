package com.xyz.tictactoe;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private AdView mAdView;

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt("roundCount",roundCount);
        outState.putInt("player1Points",player1Points);
        outState.putInt("player2Points",player2Points);
        outState.putBoolean("player1Turn",player1Turn);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        roundCount=savedInstanceState.getInt("roundCount");
        player1Points=savedInstanceState.getInt("player1Points");
        player2Points=savedInstanceState.getInt("player2Points");
        player1Turn=savedInstanceState.getBoolean("player1Turn");
    }

    private Button[][] buttons = new Button[3][3];

    private int roundCount;

    private boolean player1Turn=true;

    private int player1Points;
    private int player2Points;
    private int drawPoints;

    private TextView textViewPlayer1;
    private  TextView textViewPlayer2;
    private TextView textViewDraw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        // hiding the status bar
//        View decorView = getWindow().getDecorView();
//        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
//        decorView.setSystemUiVisibility(uiOptions);
        MobileAds.initialize(this, "ca-app-pub-3355224362612000~2584094991");
        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
    textViewPlayer1=findViewById(R.id.play1);
    textViewPlayer2=findViewById(R.id.play2);
    textViewDraw=findViewById(R.id.draw);

    for(int i=0;i<3;i++){
        for(int j=0;j<3;j++){
            //btn00 btn01 btn02 btn10...and so on upto btn22. and give find viewby id
            String buttonId="btn"+i+j;
            int resId =getResources().getIdentifier(buttonId,"id",getPackageName());
            //find all 9 buttons by this nested loop and resId to convert in int maybe
            buttons[i][j]=findViewById(resId);
            buttons[i][j].setOnClickListener(this);
        }
    }

    // Button Reset
    Button btnReset=findViewById(R.id.btnreset);
    btnReset.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            resetGame();
            //set All buttons Empty and player score becomes 0
        }
    });
     Button btnClear=findViewById(R.id.btnclear);
     btnClear.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             resetBoard();
         }
     });

    }


    @Override
    public void onClick(View v) {
// we hv to use v so we did this . if this buttons are not equal to empty String then return
        if(!((Button)v).getText().toString().equals("")){
            return;
        }if(player1Turn){
            // we hv to cast v in this  if upper contains empty String then do this ;
            ((Button)v).setText("X");
        }else {
            ((Button) v).setText("O");
        }
        roundCount++;
        if(checkForWin()){
            if(player1Turn){
                player1Wins();
            }else{
                player2Wins();
            }}
            else if(roundCount==9){
                // if all buttons are filled means round count reaches to 9 it will call draw method
                draw();
            }
            else
                // this will switch the player turns.
                player1Turn=!player1Turn;
        }


        private boolean checkForWin() {
            String[][] field = new String[3][3];
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    // this will button inputs are save in fields string so we use in if condition
                    field[i][j] = buttons[i][j].getText().toString();
                }
            }
            for (int i = 0; i < 3; i++) {
                // this if condition checks 00 with 01 and 00 with 02 and also not 00 with non empty and this loops increments row wise 00 further 10 and further 20
                if (field[i][0].equals(field[i][1]) && field[i][0].equals(field[i][2]) && !field[i][0].equals("")) {
                    return true;
                }
            }
            for (int i = 0; i < 3; i++) {
                // this if condition checks 00 with 01 and 00 with 02 and also not 00 with non empty and this loops increments row wise 00 further 10 and further 20
                if (field[0][i].equals(field[1][i]) && field[0][i].equals(field[2][i]) && !field[0][i].equals("")) {
                    return true;
                }
            }
            // compare diagoally 00 with 11 and 00 with 22
            if (field[0][0].equals(field[1][1]) && field[0][0].equals(field[2][2]) && !field[0][0].equals("")) {
                return true;
            }
            //other diagonall 02 with 11 ...
            if (field[0][2].equals(field[1][1]) && field[0][2].equals(field[2][0]) && !field[0][2].equals("")) {
                return true;
            }
            return false;
        }
    private  void player1Wins(){
        player1Points++;
//toast occuring method
        Toast.makeText(this,"Player 1 Wins!",Toast.LENGTH_SHORT).show();
        UpdatePointsText();
        resetBoard();
    }
        private void player2Wins(){
            player2Points++;
//toast occuring method
            Toast.makeText(this,"Player 2 Wins!",Toast.LENGTH_SHORT).show();
            UpdatePointsText();
            resetBoard();
        }

      private void draw(){
        drawPoints++;
       Toast.makeText(this,"Draw",Toast.LENGTH_SHORT).show();
       UpdatePointsText();
       resetBoard();
        }

       private void UpdatePointsText(){
     textViewPlayer1.setText("Player X : " +player1Points);
     textViewPlayer2.setText("Player O : " +player2Points);
     textViewDraw.setText("Draw : " +drawPoints);

       }

       private void resetBoard(){
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                buttons[i][j].setText("");
            }
        }
        roundCount=0;
        player1Turn=true;

       }
       private void resetGame(){
        player1Points=0;
        player2Points=0;
        drawPoints=0;
        UpdatePointsText();
        resetBoard();
       }



}
