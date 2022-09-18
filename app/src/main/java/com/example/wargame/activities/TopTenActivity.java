package com.example.wargame.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.wargame.R;
import com.example.wargame.model.Card;
import com.example.wargame.model.GameManager;
import com.example.wargame.model.Winner;

import java.io.IOException;
import java.util.ArrayList;

public class TopTenActivity extends AppCompatActivity {
    private Button top_ten_BTN_menu;
    private LinearLayout top_ten_LL_list;
    private ArrayList<Winner> winners;
    public static String TOP_TEN_LIST_KEY = "TOP_TEN_LIST_KEY";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_ten);
        initViews();
        winners = GameManager.getTopTenList();
        updateTopTenLeanerLayout();
        top_ten_BTN_menu.setOnClickListener(view -> {
            finish();
        });
    }


    private void updateTopTenLeanerLayout() {
    }

    private void initViews() {
        top_ten_LL_list = findViewById(R.id.top_ten_LL_list);
        top_ten_BTN_menu = findViewById(R.id.top_ten_BTN_menu);
    }

}