package com.example.myapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;

public class Easy extends AppCompatActivity {

    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String scoreBigHead = "score1";
    public static final String scoreAngryFu = "score2";

    private String sc1, sc2;
    ImageView image0, image1, image2, image3, image4, image5, image6, image7, image8;
    TextView bigHeadScore, angryFuScore;
    Button reset;
    Boolean gameActive = true;
    int PLAYER_BIGHEAD = 0;
    int PLAYER_ANGRYFU = 1;
    int activePlayer=1;
    int[] array = {0, 1, 2, 3, 4, 5, 6, 7, 8};
    int[] filledPosition = {-1, -1, -1, -1, -1, -1, -1, -1, -1};
    int[][] winPositions = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, {0, 4, 8}, {2, 4, 6}};

    public int getRandom(int[] array) {
        int random = (int) (Math.random() * 9);
        return array[random];
    }

    public void setBigHeaddata() {
        SharedPreferences sharedPreferences1 = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        sc1 = sharedPreferences1.getString(scoreBigHead, "0");
    }

    public void setAngryFudata() {
        SharedPreferences sharedPreferences2 = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        sc2 = sharedPreferences2.getString(scoreAngryFu, "0");
    }

    public void setData() {
        bigHeadScore.setText(sc1);
        angryFuScore.setText(sc2);

    }


    public void playerTap(final View view) {
        if ((filledPosition[0] != -1 && filledPosition[1] != -1 && filledPosition[2] != -1 &&
                filledPosition[3] != -1 && filledPosition[4] != -1 && filledPosition[5] != -1
                && filledPosition[6] != -1 && filledPosition[7] != -1 && filledPosition[8] != -1)) {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    draw();
                    gameReset(view);
                }
            }, 100);
            return;
        }

        final MediaPlayer onClickSound = MediaPlayer.create(getApplicationContext(), R.raw.zapsplat_multimedia_button_click_007_53868);
        onClickSound.start();

        if (activePlayer == PLAYER_ANGRYFU) {
            ImageView img = (ImageView) view;
            int clickedTag = Integer.parseInt(view.getTag().toString());
            if (!gameActive) {
                gameReset(view);
            }
            if (filledPosition[clickedTag] != -1) {
                return;
            }
            filledPosition[clickedTag] = activePlayer;

            if (activePlayer == PLAYER_ANGRYFU) {
                img.setImageResource(R.drawable.angry_fu);
                activePlayer = PLAYER_BIGHEAD;
            } else {
                img.setImageResource(R.drawable.big_head);
                activePlayer = PLAYER_ANGRYFU;
            }

            for (int[] winPosition : winPositions) {
                if (filledPosition[winPosition[0]] == filledPosition[winPosition[1]] &&
                        filledPosition[winPosition[1]] == filledPosition[winPosition[2]] &&
                        filledPosition[winPosition[0]] != -1) {
                    gameActive = false;
                    if (filledPosition[winPosition[0]] == 0) {
                        int a = Integer.parseInt((String) bigHeadScore.getText());
                        a = a + 1;
                        bigHeadScore.setText(Integer.toString(a));
                        openDialogAngryFuLost();
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                gameReset(view);
                                activePlayer = PLAYER_ANGRYFU;
                            }
                        }, 100);
                        return;
                    } else {
                        int b = Integer.parseInt((String) angryFuScore.getText());
                        b = b + 1;
                        angryFuScore.setText(Integer.toString(b));
                        openDialogAngryFuWon();
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                gameReset(view);
                                activePlayer = PLAYER_ANGRYFU;
                            }
                        }, 100);
                        return;
                    }
                }
            }

            if (filledPosition[0] != -1 && filledPosition[1] != -1 && filledPosition[2] != -1 &&
                    filledPosition[3] != -1 && filledPosition[4] != -1 && filledPosition[5] != -1
                    && filledPosition[6] != -1 && filledPosition[7] != -1 && filledPosition[8] != -1) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        draw();
                        gameReset(view);
                    }
                }, 100);
                return;
            }

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    int temp = 0;
                    for (int i = 0; i < 8; i++) {
                        temp = getRandom(array);
                        if (filledPosition[temp] == -1)
                            break;
                    }

                    if (temp == 0 && filledPosition[temp] == -1) {
                        filledPosition[0] = 0;
                        image0.setImageResource(R.drawable.big_head);
                        activePlayer = PLAYER_ANGRYFU;
                    }
                    if (temp == 1 && filledPosition[temp] == -1) {
                        filledPosition[1] = 0;
                        image1.setImageResource(R.drawable.big_head);
                        activePlayer = PLAYER_ANGRYFU;
                    }
                    if (temp == 2 && filledPosition[temp] == -1) {
                        filledPosition[2] = 0;
                        image2.setImageResource(R.drawable.big_head);
                        activePlayer = PLAYER_ANGRYFU;
                    }
                    if (temp == 3 && filledPosition[temp] == -1) {
                        filledPosition[3] = 0;
                        image3.setImageResource(R.drawable.big_head);
                        activePlayer = PLAYER_ANGRYFU;
                    }
                    if (temp == 4 && filledPosition[temp] == -1) {
                        filledPosition[4] = 0;
                        image4.setImageResource(R.drawable.big_head);
                        activePlayer = PLAYER_ANGRYFU;
                    }
                    if (temp == 5 && filledPosition[temp] == -1) {
                        filledPosition[5] = 0;
                        image5.setImageResource(R.drawable.big_head);
                        activePlayer = PLAYER_ANGRYFU;
                    }
                    if (temp == 6 && filledPosition[temp] == -1) {
                        filledPosition[6] = 0;
                        image6.setImageResource(R.drawable.big_head);
                        activePlayer = PLAYER_ANGRYFU;
                    }
                    if (temp == 7 && filledPosition[temp] == -1) {
                        filledPosition[7] = 0;
                        image7.setImageResource(R.drawable.big_head);
                        activePlayer = PLAYER_ANGRYFU;
                    }
                    if (temp == 8 && filledPosition[temp] == -1) {
                        filledPosition[8] = 0;
                        image8.setImageResource(R.drawable.big_head);
                        activePlayer = PLAYER_ANGRYFU;
                    }

                    for (int[] winPosition : winPositions) {
                        if (filledPosition[winPosition[0]] == filledPosition[winPosition[1]] &&
                                filledPosition[winPosition[1]] == filledPosition[winPosition[2]] &&
                                filledPosition[winPosition[0]] != -1) {
                            gameActive = false;
                            if (filledPosition[winPosition[0]] == 0) {
                                int a = Integer.parseInt((String) bigHeadScore.getText());
                                a = a + 1;
                                bigHeadScore.setText(Integer.toString(a));
                                openDialogAngryFuLost();
                                new Handler().postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        gameReset(view);
                                        activePlayer = PLAYER_ANGRYFU;
                                    }
                                }, 100);
                                return;
                            } else {
                                int b = Integer.parseInt((String) angryFuScore.getText());
                                b = b + 1;
                                angryFuScore.setText(Integer.toString(b));
                                openDialogAngryFuWon();
                                new Handler().postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        gameReset(view);
                                        activePlayer = PLAYER_ANGRYFU;
                                    }
                                }, 100);
                                return;
                            }
                        }
                    }
                }
            }, 100);
        }
    }

    private void openDialogAngryFuWon() {
        final Dialog dialog = new Dialog(Easy.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.angry_fu_dialog_won);
        dialog.show();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                dialog.dismiss();
            }
        },2000);
    }

    private void openDialogAngryFuLost() {
        final Dialog dialog = new Dialog(Easy.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.you_lost);
        dialog.show();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                dialog.dismiss();
            }
        },2000);
    }


    private void draw() {
        final Dialog dialog = new Dialog(Easy.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.draw);
        dialog.show();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                dialog.dismiss();
            }
        },2000);
    }


    public void gameReset(View view) {
        gameActive = true;
        activePlayer = 1;
        Arrays.fill(filledPosition, -1);
        ((ImageView) findViewById(R.id.image0)).setImageResource(R.drawable.white);
        ((ImageView) findViewById(R.id.image1)).setImageResource(R.drawable.white);
        ((ImageView) findViewById(R.id.image2)).setImageResource(R.drawable.white);
        ((ImageView) findViewById(R.id.image3)).setImageResource(R.drawable.white);
        ((ImageView) findViewById(R.id.image4)).setImageResource(R.drawable.white);
        ((ImageView) findViewById(R.id.image5)).setImageResource(R.drawable.white);
        ((ImageView) findViewById(R.id.image6)).setImageResource(R.drawable.white);
        ((ImageView) findViewById(R.id.image7)).setImageResource(R.drawable.white);
        ((ImageView) findViewById(R.id.image8)).setImageResource(R.drawable.white);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_easy);

        image0 = findViewById(R.id.image0);
        image1 = findViewById(R.id.image1);
        image2 = findViewById(R.id.image2);
        image3 = findViewById(R.id.image3);
        image4 = findViewById(R.id.image4);
        image5 = findViewById(R.id.image5);
        image6 = findViewById(R.id.image6);
        image7 = findViewById(R.id.image7);
        image8 = findViewById(R.id.image8);

        bigHeadScore = findViewById(R.id.bigheadscore);
        angryFuScore = findViewById(R.id.angryfuscore);

        reset = findViewById(R.id.reset);
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gameReset(view);
                angryFuScore.setText("0");
                bigHeadScore.setText("0");
            }
        });



        setAngryFudata();
        setBigHeaddata();
        setData();
    }


    public void onBackPressed()
    {
        AlertDialog.Builder alertdialog=new AlertDialog.Builder(this);
        alertdialog.setTitle("Confirm Exit?");
        alertdialog.setIcon(R.drawable.ic_exit);
        alertdialog.setMessage("Are you sure you want to exit?");
        alertdialog.setCancelable(false);
        alertdialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                forSP1();
                forSP2();
                finish();
            }
        });

        alertdialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(Easy.this,"You clicked on Cancel",Toast.LENGTH_SHORT).show();
            }
        });

        AlertDialog AD=alertdialog.create();
        AD.show();
    }

    public void forSP1()
    {
        SharedPreferences sharedPreferences1 = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor1 = sharedPreferences1.edit();

        editor1.putString(scoreBigHead, bigHeadScore.getText().toString());
        editor1.apply();
    }

    public void forSP2()
    {
        SharedPreferences sharedPreferences2 = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor2 = sharedPreferences2.edit();

        editor2.putString(scoreAngryFu, angryFuScore.getText().toString());
        editor2.apply();

    }
}