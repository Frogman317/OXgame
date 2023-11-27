package com.mrfrogman.oxgame;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    int game_flg = 0;
    Context context;
    int[][] List = new int[3][3];
    Boolean turn = true;
    CPU cpu = new CPU();
    int count = 0;
    TextView tv;
    ImageButton[][] imageButtonList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = getApplicationContext();
        tv = findViewById(R.id.game_text);
        imageButtonList = create_masu();
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    public void onButton(View v) {
        if (game_flg > 0){
            ImageButton imageButton = (ImageButton)v;
            String ContentDescription = (String) imageButton.getContentDescription();
            String[] position = ContentDescription.split("");
            int[] selected = new int[2];
            selected[0] = Integer.parseInt(position[0]);
            selected[1] = Integer.parseInt(position[1]);
            select(selected);
        }
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    protected void select(int[] i){
        Log.d("select", "select: "+i[0]+i[1]);
        if (List[i[0]][i[1]] == 0){
            if (turn){
                List[i[0]][i[1]] = 1;
                imageButtonList[i[0]][i[1]].setImageDrawable(getDrawable(R.drawable.maru));
            }else{
                List[i[0]][i[1]] = 2;
                imageButtonList[i[0]][i[1]].setImageDrawable(getDrawable(R.drawable.batu));
            }
            if(isWin()){
                game_flg = 0;
                Toast.makeText(context , turn?"Oの勝ち":"Xの勝ち", Toast.LENGTH_LONG).show();
                tv.setText(turn?"Oの勝ち":"Xの勝ち");
            }else{
                if(++count==9){
                    Toast.makeText(context , "引き分け", Toast.LENGTH_LONG).show();
                    tv.setText("引き分け");
                }else{
                    turn = !turn;
                    if (turn){
                        tv.setText("Oのターン");
                    }else{
                        tv.setText("Xのターン");
                        if (game_flg == 1){
                            int[] next = cpu.priority1(List);
                            Log.d("select", "select: "+next[0]+next[1]);
                            select(next);
                        }
                    }
                }
            }
        }
    }

    protected Boolean isWin(){
        if (List[0][0]==List[0][1] && List[0][1]==List[0][2] && List[0][2]!=0)return true;
        if (List[1][0]==List[1][1] && List[1][1]==List[1][2] && List[1][2]!=0)return true;
        if (List[2][0]==List[2][1] && List[2][1]==List[2][2] && List[2][2]!=0)return true;
        if (List[0][0]==List[1][0] && List[1][0]==List[2][0] && List[2][0]!=0)return true;
        if (List[0][1]==List[1][1] && List[1][1]==List[2][1] && List[2][1]!=0)return true;
        if (List[0][2]==List[1][2] && List[1][2]==List[2][2] && List[2][2]!=0)return true;
        if (List[0][0]==List[1][1] && List[1][1]==List[2][2] && List[2][2]!=0)return true;
        if (List[0][2]==List[1][1] && List[1][1]==List[2][0] && List[2][0]!=0)return true;
        return false;
    }

    protected ImageButton[][] create_masu(){
        ImageButton[][] imageButtonList = new ImageButton[3][3];
        imageButtonList[0][0] = findViewById(R.id.topLeft);
        imageButtonList[0][1] = findViewById(R.id.top);
        imageButtonList[0][2] = findViewById(R.id.topRight);
        imageButtonList[1][0] = findViewById(R.id.centerLeft);
        imageButtonList[1][1] = findViewById(R.id.center);
        imageButtonList[1][2] = findViewById(R.id.centerRight);
        imageButtonList[2][0] = findViewById(R.id.bottomLeft);
        imageButtonList[2][1] = findViewById(R.id.bottom);
        imageButtonList[2][2] = findViewById(R.id.bottomRight);
        return imageButtonList;
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    public void onReset(View v) {
        List = new int[3][3];
        turn = true;
        count = 0;
        RelativeLayout layout = findViewById(R.id.masu);
        for (int i = 0; i < 9; i++) {
            ImageButton imageButton = (ImageButton)layout.getChildAt(i);
            imageButton.setImageDrawable(getDrawable(R.drawable.none));
        }
    }

    public void play_game(View v){
        int id = v.getId();
        if (id == R.id.play1p) game_flg = 1;
        if (id == R.id.play2p) game_flg = 2;
        tv.setText("Oのターン");
        onReset(v);
    }
}