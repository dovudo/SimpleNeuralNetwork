package com.genby;

/**
 * Created by dov on 8/18/16.
 */
public class StepOne {
    static int x = -2;
    static int y = 3;
    static float tweak_amount = (float) 0.01;
    static float out;

    public static float forwardMultiplyGate(float x,float y){return x * y;}

    public static void run() {
        float best_out_y = y;
        float best_out_x = x;
        float y_try;
        float x_try;
        float best_out = -333; //will fix

        for(int i = 0; i < 100; i++){
            x_try = (float) (x + tweak_amount * (Math.random()* 2 - 1));
            y_try = (float) (y + tweak_amount * (Math.random()* 2 - 1));
            out = forwardMultiplyGate(x_try,y_try);
            //System.out.println("out = " + out);
            if (out > best_out){
                best_out = out;
                best_out_x = x_try;
                best_out_y = y_try;
                System.out.println("\n best x = " + best_out_x + "\n best y = " + best_out_y + "\n best out = " + best_out);
            }
        }
    }
}

