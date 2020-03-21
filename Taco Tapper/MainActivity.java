package com.example.tacotapper;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.core.content.res.ResourcesCompat;

import android.animation.ObjectAnimator;
import android.graphics.Color;
import android.graphics.Path;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.VideoView;

import java.util.concurrent.atomic.AtomicInteger;


public class MainActivity extends AppCompatActivity {

    boolean placed = false;
    Typeface pixel;
    ImageView taco;
    TranslateAnimation translateAnimation;
    TextView oneImage;
    AtomicInteger score = new AtomicInteger();
    int income = 0;
    TextView scoreText;
    TextView incomeText;
    ConstraintLayout layout;
    VideoView videoView;
    ScaleAnimation scaleAnimation;

    public class Passive extends Thread{
        @Override
        public void run() {
            while (true) {

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                score.getAndAdd(income);
                Upgrade upgrades = new Upgrade();
                upgrades.start();

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        scoreText.setText("Tacos: " + score);
                        incomeText.setText("Taco Per Second: " + income);
                    }
                });
            }
        }
    }

    public class Upgrade extends Thread{
        @Override
        public void run() {

            final ImageView burrito = new ImageView(MainActivity.this);

                if(score.get() >= 30 && !placed){
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            placeView(burrito, 0.15f, 0.40f, R.drawable.burrito);
                            placed = true;
                        }
                    });
                }

                burrito.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        income++;
                        score.getAndSet(score.get() - 30);
                        if(score.get() < 30){
                            layout.removeView(burrito);
                            placed = false;
                        }
                        ImageView attainedBurrito = new ImageView(MainActivity.this);
                        placeView(attainedBurrito, 0.15f, 0.40f, R.drawable.burrito);
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                            Path path = new Path();
                            int randX = ((int)(Math.random()*17)-25) * 10;
                            int randY = ((int)(Math.random()*20)-10) * 10;
                            path.arcTo(burrito.getLeft(), burrito.getTop(), burrito.getRight() - 200 - randX, burrito.getBottom() + 700 + randY, -90f, 180f, true);
                            ObjectAnimator animator = ObjectAnimator.ofFloat(attainedBurrito, attainedBurrito.X, attainedBurrito.Y, path);
                            animator.setDuration(2000);
                            animator.start();
                        }
                    }
                });
        }
    }

    public void placeView(ImageView view, float horizontalBias, float verticalBias, int id){
        view.setImageResource(id);
        view.setId(View.generateViewId());
        ConstraintLayout.LayoutParams params = new ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.WRAP_CONTENT,
                ConstraintLayout.LayoutParams.WRAP_CONTENT);
        view.setLayoutParams(params);
        layout.addView(view);

        ConstraintSet constraintSet = new ConstraintSet();
        constraintSet.clone(layout);

        constraintSet.connect(view.getId(), ConstraintSet.TOP, layout.getId(), ConstraintSet.TOP);
        constraintSet.connect(view.getId(), ConstraintSet.RIGHT, layout.getId(), ConstraintSet.RIGHT);
        constraintSet.connect(view.getId(), ConstraintSet.BOTTOM, layout.getId(), ConstraintSet.BOTTOM);
        constraintSet.connect(view.getId(), ConstraintSet.LEFT, layout.getId(), ConstraintSet.LEFT);

        constraintSet.setHorizontalBias(view.getId(), horizontalBias);
        constraintSet.setVerticalBias(view.getId(), verticalBias);

        constraintSet.applyTo(layout);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        score.set(0);
        pixel = ResourcesCompat.getFont(this, R.font.mario );
        incomeText = findViewById(R.id.id_income);
        incomeText.setText("Taco Per Second: " + 0);
        layout = findViewById(R.id.id_game_layout);
        layout.setBackgroundColor(Color.parseColor("#ffcf00"));
        scoreText = findViewById(R.id.id_score);
        taco = findViewById(R.id.id_taco);
        taco.setImageResource(R.drawable.taco);
        videoView = findViewById(R.id.id_videoview);

        Passive passiveIncome = new Passive();
        passiveIncome.start();

        Uri uri = Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.gameback);
        videoView.setVideoURI(uri);
        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.setLooping(true);
            }
        });
        videoView.start();

        scaleAnimation = new ScaleAnimation(1.0f, 1.5f, 1.0f, 1.5f
                , Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);

        taco.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(scaleAnimation);
                score.getAndAdd(1);
                scoreText.setText("Tacos: " + score);
                plusOne();
            }
        });
    }

    public void plusOne(){
        oneImage = new TextView(this);
        oneImage.setId(View.generateViewId());
        oneImage.setText("+1");
        oneImage.setTypeface(pixel);
        oneImage.setTextColor(Color.parseColor("#C41135"));

        ConstraintLayout.LayoutParams params = new ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.WRAP_CONTENT,
                ConstraintLayout.LayoutParams.WRAP_CONTENT);
        oneImage.setLayoutParams(params);
        layout.addView(oneImage);

        ConstraintSet constraintSet = new ConstraintSet();
        constraintSet.clone(layout);

        constraintSet.connect(oneImage.getId(), ConstraintSet.TOP, layout.getId(), ConstraintSet.TOP);
        constraintSet.connect(oneImage.getId(), ConstraintSet.RIGHT, layout.getId(), ConstraintSet.RIGHT);
        constraintSet.connect(oneImage.getId(), ConstraintSet.BOTTOM, layout.getId(), ConstraintSet.BOTTOM);
        constraintSet.connect(oneImage.getId(), ConstraintSet.LEFT, layout.getId(), ConstraintSet.LEFT);

        constraintSet.setHorizontalBias(oneImage.getId(), 0.5f);
        constraintSet.setVerticalBias(oneImage.getId(), 0.5f);

        constraintSet.applyTo(layout);

        int randX = (int)(Math.random()*200)-100;
        translateAnimation = new TranslateAnimation(randX, randX, 200, -500);
        translateAnimation.setDuration(1000);

        oneImage.startAnimation(translateAnimation);
        oneImage.setVisibility(View.INVISIBLE);
    }

}
