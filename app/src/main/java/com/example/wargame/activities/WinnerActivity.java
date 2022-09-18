package com.example.wargame.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.wargame.R;
import com.example.wargame.model.GameManager;
import com.example.wargame.model.Winner;

import java.util.ArrayList;
import java.util.Objects;

public class WinnerActivity extends AppCompatActivity {
    public static String winnerName;
    public static int winnerImageInt;
    public static final String WINNER_NAME_KEY = "WINNER_NAME_KEY";
    public static final String WINNER_iMAGE_INT_KEY = "WINNER_iMAGE_INT_KEY";
    public static final String WINNER_SCORE_KEY = "WINNER_SCORE_KEY";
    public TextView winner_name_lbl;
    private int winnerScore;
    public ImageView winner_img;
    public TextView winner_LBL_score;
    private Button back_menu_btn;
    private ArrayList<Winner> topTenWinnersList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Objects.requireNonNull(getSupportActionBar()).hide();
        setContentView(R.layout.activity_winner);

        Bundle extras = getIntent().getExtras();
        if(extras != null){
            winnerName = extras.getString(WINNER_NAME_KEY);
            winnerImageInt = extras.getInt(WINNER_iMAGE_INT_KEY);
            winnerScore = extras.getInt(WINNER_SCORE_KEY);

        }
        initViews();
        setViews();
        loadTopTenWinnersList();
        updateTopTenWinnersList();
        back_menu_btn.setOnClickListener(view->{
            finish();
        });

    }

    private void loadTopTenWinnersList() {
        try{

        }catch (Exception exception){
            topTenWinnersList = new ArrayList<>();
        }
    }


    private void updateTopTenWinnersList() {
        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences(TopTenActivity.TOP_TEN_LIST_KEY, MODE_PRIVATE);
        @SuppressLint("CommitPrefEdits")
        SharedPreferences.Editor editor = sharedPreferences.edit();
    }

    private void setViews(){
        winner_name_lbl.setText(winnerName);
        winner_img.setBackgroundResource(winnerImageInt);
        winner_LBL_score.setText("Score: " +winnerScore);
    }
    private void initViews() {
        back_menu_btn = findViewById(R.id.back_menu_btn);
        winner_name_lbl = findViewById(R.id.winner_name_lbl);
        winner_img = findViewById(R.id.winner_img);
        winner_LBL_score = findViewById(R.id.winner_LBL_score);
    }


}