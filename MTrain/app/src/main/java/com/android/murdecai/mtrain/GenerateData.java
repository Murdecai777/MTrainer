package com.android.murdecai.mtrain;

import java.io.Serializable;
import java.util.Random;

public class GenerateData implements Serializable{

    String[] option = new String[4];
    String s_exp = "";
    int answer;
    String s_answer;

    protected void chooseLevel(int level){
        switch(level){
            case 1:
                easyGen();
                break;
            case 2:
                //mediumGen();
                break;
            case 3:
               // hardGen();
                break;
        }
    }

   private void easyGen(){

        Random random = new Random();
        int op1, op2, exp;

        op1 = random.nextInt(101);
        op2 = random.nextInt(101);
        exp = random.nextInt(2)+1;

        switch(exp){
            case 1:
                s_exp = op1 + "+" + op2;
                answer = op1 + op2;
                break;
            case 2:
                s_exp = op1 + "-" + op2;
                answer = op1 - op2;
                break;
        }

        int i = random.nextInt(4);
       s_answer = Integer.toString(answer);
        option[i] = s_answer;
        for(int j=0; j<4; j++)
            if(j!=i)
                option[j] = Integer.toString(answer - (random.nextInt(11) - 5));


    }

}
