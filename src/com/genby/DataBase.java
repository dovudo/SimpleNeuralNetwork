package com.genby;

//It's main values of Neural Network.
//This for sometimes.

public class DataBase extends SingleNeuron{

    private SingleNeuron a = new SingleNeuron(1.0, 0.0);
    private SingleNeuron b = new SingleNeuron(2.0, 0.0);
    private SingleNeuron c = new SingleNeuron(-3.0, 0.0);
    private SingleNeuron x = new SingleNeuron(-1.0, 0.0);
    private SingleNeuron y = new SingleNeuron(3.0, 0.0);

    private multiplyGate mlp0 = new multiplyGate();
    private multiplyGate mlp1 = new multiplyGate();
    private addGate addg0 = new addGate();
    private addGate addg1 = new addGate();
    private sigmoidGate sg0 = new sigmoidGate();


    public void forwardNeuron(){

        SingleNeuron ax,by,axpby,axpbypc;
        double s;
        ax = mlp0.forward(a,x);
        by = mlp1.forward(b,y);
        axpby = addg0.forward(ax,by);
        axpbypc = addg1.forward(axpby, c); // a*x + b*y + c
        s = sg0.forward(axpbypc);

        System.out.println("S = " + s );
    }
}
