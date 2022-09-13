package com.example.wargame;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Objects;

public class WinnerActivity extends AppCompatActivity {
    public static String winnerName;
    public static int winnerImageInt;
    public static final String WINNER_NAME_KEY = "WINNER_NAME_KEY";
    public static final String WINNER_iMAGE_INT_KEY = "WINNER_iMAGE_INT_KEY";
    public TextView winner_name_lbl;
    public ImageView winner_img;
    Button back_menu_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Objects.requireNonNull(getSupportActionBar()).hide();
        setContentView(R.layout.activity_winner);

        Bundle extras = getIntent().getExtras();
        if(extras != null){
            winnerName = extras.getString(WINNER_NAME_KEY);
           winnerImageInt = extras.getInt(WinnerActivity.WINNER_iMAGE_INT_KEY);
        }
        initViews();
        setViews();
        back_menu_btn.setOnClickListener(view->{
            finish();
        });

    }
    private void setViews(){
        winner_name_lbl.setText(winnerName);
        winner_img.setBackgroundResource(winnerImageInt);
    }
    private void initViews() {
        back_menu_btn = findViewById(R.id.back_menu_btn);
        winner_name_lbl = findViewById(R.id.winner_name_lbl);
        winner_img = findViewById(R.id.winner_img);
    }


}