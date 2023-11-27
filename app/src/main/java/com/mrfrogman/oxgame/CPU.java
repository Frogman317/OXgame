package com.mrfrogman.oxgame;

import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class CPU {
    protected int[] priority1(int[][] List){
        int[][] copy_List = new int[3][3];
        for (int i = 0; i < 3; i++) {
            copy_List[i] = Arrays.copyOf(List[i], 3);
        }
        int[] Return = new int[2];
        for (int i = 0; i<3; i++){
            for (int j = 0; j<3; j++){
                if (copy_List[i][j] == 0) {
                    copy_List[i][j] = 2;
                    if (isWin(copy_List)){
                        Return[0] = i;
                        Return[1] = j;
                        return Return;
                    }
                    copy_List[i][j] = 0;
                }
            }
        }
        for (int i = 0; i<3; i++){
            for (int j = 0; j<3; j++){
                if (copy_List[i][j] == 0) {
                    copy_List[i][j] = 1;
                    if (isWin(copy_List)){
                        Return[0] = i;
                        Return[1] = j;
                        return Return;
                    }
                    copy_List[i][j] = 0;
                }
            }
        }
        if (copy_List[1][1] == 0){
            Return[0] = 1;
            Return[1] = 1;
            return Return;
        }
        ArrayList<int[]> evaluation = new ArrayList<>();
        for (int i = 0; i<3; i++){
            for (int j = 0; j<3; j++){
                if (copy_List[i][j] == 0) {
                    int[] n = new int[2];
                    n[0] = i;
                    n[1] = j;
                    evaluation.add(evaluationCheck(n,List));
                }
            }
        }
        evaluation.sort(Comparator.comparingInt((int[] arr) -> arr[0]).reversed());
        for (int[] array : evaluation) {
                Log.d("TAG", "priority1: "+array[0]+" "+array[1]+" "+array[2]);
        }
        Return[0] = evaluation.get(0)[1];
        Return[1] = evaluation.get(0)[2];
        return Return;
    }
    protected Boolean isWin(int[][] List){
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

    protected int[] evaluationCheck(int[] n,int[][] List){
        int i = 0;
        int[] re = new int[3];
        switch (List[n[0]][0]){
            case 1: i += 1; break;
            case 2: i -= 1; break;
        }
        switch (List[n[0]][1]){
            case 1: i += 1; break;
            case 2: i -= 1; break;
        }
        switch (List[n[0]][2]){
            case 1: i += 1; break;
            case 2: i -= 1; break;
        }
        switch (List[0][n[1]]){
            case 1: i += 1; break;
            case 2: i -= 1; break;
        }
        switch (List[1][n[1]]){
            case 1: i += 1; break;
            case 2: i -= 1; break;
        }
        switch (List[2][n[1]]){
            case 1: i += 1; break;
            case 2: i -= 1; break;
        }
        re[0] = i;
        re[1] = n[0];
        re[2] = n[1];
        return re;
    }
}
