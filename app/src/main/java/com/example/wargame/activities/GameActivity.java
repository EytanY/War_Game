package com.example.wargame.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.wargame.model.Card;
import com.example.wargame.model.GameManager;
import com.example.wargame.R;

import java.util.ArrayList;
import java.util.Objects;

public class GameActivity extends AppCompatActivity {
    public Button play_btn;
    public TextView black_player_score_lbl;
    public TextView white_player_score_lbl;
    public ImageView white_player_card;
    public ImageView black_player_card;
    public ArrayList<Card> whitePlayerCards;
    public ArrayList<Card> blackPlayerCards;
    private int turn = 0;
    public int black_player_score = 0;
    public int white_player_score = 0;
    private final Handler handler = new Handler();
    private boolean isForeground;
    private MediaPlayer game_flip_sound;
    private boolean isPlayButtonClicked = false;
    private final String BLACK_SCORE_KEY = "BLACK_SCORE_KEY";
    private final String WHITE_SCORE_KEY = "WHITE_SCORE_KEY";
    private final String WHITE_PLAYER_CARDS_KEY = "WHITE_PLAYER_CARDS_KEY";
    private final String BLACK_PLAYER_CARDS_KEY = "BLACK_PLAYER_CARDS_KEY";
    private final String TURN_KEY = "TURN_KEY";
    private final String is_Play_Button_Clicked_KEY= "is_Play_Button_Clicked_KEY";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Objects.requireNonNull(getSupportActionBar()).hide();
        setContentView(R.layout.activity_game);
        setViews(savedInstanceState);
        initViews();
        play_btn.setOnClickListener(view->{
            if(!isPlayButtonClicked)
                playGame();
            isPlayButtonClicked = true;
        });


    }

    @Override
    protected void onStop() {
        super.onStop();
        stopPlaying();
    }



    @Override
    protected void onStart() {
        super.onStart();
        continuePlaying();
    }

    private void continuePlaying() {
        if(isPlayButtonClicked){
            playGame();
        }
    }


    private void stopPlaying() {
        isForeground = false;
    }


    private void setViews(Bundle savedInstanceState) {

        if(savedInstanceState != null){
            black_player_score = savedInstanceState.getInt(BLACK_SCORE_KEY);
            white_player_score = savedInstanceState.getInt(WHITE_SCORE_KEY);
            whitePlayerCards = (ArrayList<Card>) savedInstanceState.getSerializable(WHITE_PLAYER_CARDS_KEY);
            blackPlayerCards = (ArrayList<Card>) savedInstanceState.getSerializable(BLACK_PLAYER_CARDS_KEY);
            turn = savedInstanceState.getInt(TURN_KEY);
            isPlayButtonClicked = savedInstanceState.getBoolean(is_Play_Button_Clicked_KEY);
            for(int i=0; i< turn; i++){
                View view = findViewById(getResources().getIdentifier("game_VW_" + i, "id", getPackageName()));
                view.setVisibility(View.VISIBLE);
            }

        }
        else{
            whitePlayerCards = GameManager.getCards();
            blackPlayerCards = GameManager.getCards();
        }
    }

    @SuppressLint("SetTextI18n")
    public void initViews(){
        play_btn = findViewById(R.id.play_btn);
        black_player_score_lbl = findViewById(R.id.black_player_score_lbl);
        white_player_score_lbl = findViewById(R.id.white_player_score_lbl);
        white_player_card = findViewById(R.id.white_player_card);
        black_player_card = findViewById(R.id.black_player_card);
        black_player_score_lbl.setText("" + black_player_score);
        white_player_score_lbl.setText( "" + white_player_score);
        game_flip_sound =  MediaPlayer.create(this, R.raw.poker_flip_sound);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(BLACK_SCORE_KEY, black_player_score);
        outState.putInt(WHITE_SCORE_KEY, white_player_score);
        outState.putSerializable(WHITE_PLAYER_CARDS_KEY, whitePlayerCards);
        outState.putSerializable(BLACK_PLAYER_CARDS_KEY, blackPlayerCards);
        outState.putInt(TURN_KEY, turn);
        outState.putBoolean(is_Play_Button_Clicked_KEY, isPlayButtonClicked);
    }
    public void playGame(){
        isForeground = true;
        int delay = 1000;
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                playOneTurn();
                if(whitePlayerCards.size() == 0){
                    startWinnerActivity();

                }
                else if(isForeground){
                    handler.postDelayed(this, delay);
                }
            }
        }, delay);
    }

    @SuppressLint({"UseCompatLoadingForDrawables", "SetTextI18n"})
    public void playOneTurn(){
        Card white_card = GameManager.randCard(whitePlayerCards);
        Card black_card = GameManager.randCard(blackPlayerCards);
        if(white_card != null && black_card != null){
            switch (GameManager.checkWinner(white_card, black_card)){
                case GameManager.WHITE_PLAYER_WIN:
                    white_player_score++;
                    break;
                case GameManager.BLACK_PLAYER_WIN:
                    black_player_score++;
                    break;
            }
            white_player_score_lbl.setText(white_player_score + "");
            black_player_score_lbl.setText(black_player_score + "");
            black_player_card.setImageDrawable(getResources().getDrawable(getResources().getIdentifier(black_card.getSrc(), null, getPackageName())));
            white_player_card.setImageDrawable(getResources().getDrawable(getResources().getIdentifier(white_card.getSrc(), null, getPackageName())));
            View view = findViewById(getResources().getIdentifier("game_VW_" + turn, "id", getPackageName()));
            if(view!= null)
                view.setVisibility(View.VISIBLE);
            turn++;
            playCardFlipSound();
        }

    }

    private void playCardFlipSound() {
        runOnUiThread(() -> game_flip_sound.start());
    }

    public void startWinnerActivity(){
        String winner;
        int image_int;
        int winnerScore;
        if(white_player_score > black_player_score){
            winner = "White Player";
            image_int = R.drawable.poker_white_player;
            winnerScore = white_player_score;
        }
        else{
            winner = "Black Player";
            image_int = R.drawable.poker_black_player;
            winnerScore = black_player_score;
        }

        Intent intent = new Intent(this, WinnerActivity.class);
        intent.putExtra(WinnerActivity.WINNER_NAME_KEY, winner);
        intent.putExtra(WinnerActivity.WINNER_iMAGE_INT_KEY, image_int);
        intent.putExtra(WinnerActivity.WINNER_SCORE_KEY, winnerScore);
        startActivity(intent);
        finish();

    }
}