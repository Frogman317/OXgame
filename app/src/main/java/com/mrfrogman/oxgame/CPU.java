package com.mrfrogman.oxgame;

import java.util.Arrays;

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
        for (int i = 0; i<3; i++){
            for (int j = 0; j<3; j++){
                if (copy_List[i][j] == 0) {
                    Return[0] = i;
                    Return[1] = j;
                    return Return;
                }
            }
        }
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
}
