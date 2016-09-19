package com.genby;

public class StepOne {
    final static int x = -2;
    final static int y = 3;
    static float tweak_amount = (float) 0.01;
    static float h = (float)0.0001;
    static float out;
    public static float forwardMultiplyGate(float x,float y){return x * y;}

    public static void RandomLocalSearch() {
        float best_out_y = y;
        float best_out_x = x;
        float y_try;
        float x_try;
        float best_out = -999; //will fix

        for (int i = 0; i < 100; i++) {
            x_try = (float) (x + tweak_amount * (Math.random() * 2 - 1));
            y_try = (float) (y + tweak_amount * (Math.random() * 2 - 1));
            out = forwardMultiplyGate(x_try, y_try);
            if (out > best_out) {
                best_out = out;
                best_out_x = x_try;
                best_out_y = y_try;
                System.out.println("\n best x = " + best_out_x + "\n best y = " + best_out_y + "\n best out = " + best_out);
            }
        }
    }
        //-->
        public static void NumericalGradient(){

            float xph = h + x; //X and Step size
            float yph = y + h; //Y and Step size
            out = forwardMultiplyGate(x,y);
            float xph_out = forwardMultiplyGate(xph,y);
            float x_derivative = (xph_out - out) / h;

            float yph_out = forwardMultiplyGate(yph,x);
            float y_derivative = (yph_out - out) / h;

            float this_x = x + tweak_amount * x_derivative;
            float this_y = y + tweak_amount * y_derivative;

            float new_out = forwardMultiplyGate(this_x,this_y);
            System.out.println("Numerical Gradient " + new_out);
    }


    //-->
    public static void AnalyticGradient(){
        out = forwardMultiplyGate(x,y);
        float x_gradient = y;
        float y_gradient = x;
        float this_x = x, this_y = y;
        this_x += tweak_amount * x_gradient;
        this_y += tweak_amount * y_gradient;

        float new_out = forwardMultiplyGate(this_x,this_y);
        System.out.println("AnalyticGradient " + new_out);
    }
}

