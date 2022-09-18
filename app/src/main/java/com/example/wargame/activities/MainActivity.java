package com.example.wargame.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.example.wargame.R;
import com.example.wargame.activities.GameActivity;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    Button start_game_btn;
    Button top_ten_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Objects.requireNonNull(getSupportActionBar()).hide();
        setContentView(R.layout.activity_main);

        initViews();
        start_game_btn.setOnClickListener(view -> {
            startGameButtonClick();
        });
        top_ten_btn.setOnClickListener(view->{
            topTenButtonClick();
        });
    }

    /**
     * Open Top Ten Activity
     */
    private void topTenButtonClick() {
        Intent intent = new Intent(this, TopTenActivity.class);
        startActivity(intent);
    }

    /**
     * Open Game Activity
     */
    protected void startGameButtonClick(){
        Intent intent = new Intent(this, GameActivity.class);
        startActivity(intent);
    }

    public void initViews(){
        start_game_btn = findViewById(R.id.start_game_btn);
        top_ten_btn = findViewById(R.id.top_ten_btn);
    }


    
    


}