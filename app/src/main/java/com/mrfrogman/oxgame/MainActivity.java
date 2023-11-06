package com.mrfrogman.oxgame;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Context context;
    int[][] List = new int[3][3];
    Boolean turn = true;
    int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = getApplicationContext();
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    public void onButton(View v) {
        ImageButton imageButton = (ImageButton)v;
        String ContentDescription = (String) imageButton.getContentDescription();
        String[] position = ContentDescription.split("");
        int positionY = Integer.parseInt(position[0]);
        int positionX = Integer.parseInt(position[1]);
        if (List[positionY][positionX] == 0){
            if (turn){
                List[positionY][positionX] = 1;
                imageButton.setImageDrawable(getDrawable(R.drawable.maru));
            }else{
                List[positionY][positionX] = 2;
                imageButton.setImageDrawable(getDrawable(R.drawable.batu));
            }
            if(isWin()){
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        List[i][j] = 1;
                    }
                }
                Toast.makeText(context , turn?"Oの勝ち":"Xの勝ち", Toast.LENGTH_LONG).show();
            }else{
                if(++count==9)Toast.makeText(context , "引き分け", Toast.LENGTH_LONG).show();
            }
        }
        turn = !turn;
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

    @SuppressLint("UseCompatLoadingForDrawables")
    public void onReset(View view) {
        List = new int[3][3];
        turn = true;
        count = 0;
        RelativeLayout layout = findViewById(R.id.masu);
        for (int i = 0; i < 9; i++) {
            ImageButton imageButton = (ImageButton)layout.getChildAt(i);
            imageButton.setImageDrawable(getDrawable(R.drawable.none));
        }
    }
}