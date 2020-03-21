package com.example.tacotapper;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainMenu extends AppCompatActivity {

    ConstraintLayout layout;
    ImageView startButton;
    ImageView title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        layout = findViewById(R.id.id_layout);
        layout.setBackgroundResource(R.drawable.yellowback);
        title = findViewById(R.id.id_title);
        title.setImageResource(R.drawable.title);
        startButton = findViewById(R.id.id_start_button);
        startButton.setImageResource(R.drawable.start);

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startGame();
            }
        });
    }

    public void startGame(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
