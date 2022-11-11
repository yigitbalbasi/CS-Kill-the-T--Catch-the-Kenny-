package com.yiit.catchgame;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    TextView killText;
    TextView timeText;
    int kill;
    ImageView imageView1;
    ImageView imageView2;
    ImageView imageView3;
    ImageView imageView4;
    ImageView imageView5;
    ImageView imageView6;
    ImageView imageView7;
    ImageView imageView8;
    ImageView imageView9;
    ImageView[] imageArray;
    Handler handler;
    Runnable runnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timeText = findViewById(R.id.TimeText);
        killText = findViewById(R.id.KillText);
        kill = 0;
        imageView1 = findViewById(R.id.image1);
        imageView2 = findViewById(R.id.image2);
        imageView3 = findViewById(R.id.image3);
        imageView4 = findViewById(R.id.image4);
        imageView5 = findViewById(R.id.image5);
        imageView6 = findViewById(R.id.image6);
        imageView7 = findViewById(R.id.image7);
        imageView8 = findViewById(R.id.image8);
        imageView9 = findViewById(R.id.image9);

        imageArray = new ImageView[]{imageView9, imageView8, imageView7, imageView6, imageView5, imageView4, imageView3, imageView2, imageView1};


        new CountDownTimer(10000, 1) {

            @Override
            public void onTick(long millisUntilFinished) {
                timeText.setText("Time:" + "" + millisUntilFinished / 1000);
            }

            @Override
            public void onFinish() {
                timeText.setText("Time off!");
                handler.removeCallbacks(runnable);
                for (ImageView image : imageArray) {
                    image.setVisibility(View.INVISIBLE);
                }

                AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
                alert.setTitle("Restart?");
                alert.setMessage("Are you sure to restart game?");
                alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //restart
                        Intent intent = getIntent();
                        finish();
                        startActivity(intent);
                    }
                });
                alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this, "Game over", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }.start();

        hideImages();
    }

    public void increaseKill(View view) {
        kill++;
        killText.setText("Your kill:" + "" + kill);

    }

    public void hideImages() {
        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
                for (ImageView image : imageArray) {
                    //tüm image ları görünmez yaptı
                    image.setVisibility(View.INVISIBLE);

                    //random seçimler yapmamıza yarar
                    Random random = new Random();
                    //0 ile 9 arasında sayı seçer
                    int i = random.nextInt(9);
                    //oluşturulan sayının indeksindeki fotoyu göstermeye başlar
                    imageArray[i].setVisibility(View.VISIBLE);

                    handler.postDelayed(this, 1000);
                }
            }
        };

        handler.post(runnable);


    }
}