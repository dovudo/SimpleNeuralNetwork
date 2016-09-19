package com.genby;

//It's main values of Neural Network.
//This for sometimes.

public class DataBase extends SingleNeuron{
    SingleNeuron a = new SingleNeuron(1.0, 0.0);
    SingleNeuron b = new SingleNeuron(2.0, 0.0);
    SingleNeuron c = new SingleNeuron(-3.0, 0.0);
    SingleNeuron x = new SingleNeuron(-1.0, 0.0);
    SingleNeuron y = new SingleNeuron(3.0, 0.0);

    multiplyGate mlp0 = new multiplyGate();
    multiplyGate mlp1 = new multiplyGate();
    addGate addg0 = new addGate();
    addGate addg1 = new addGate();
    sigmoidGate sg0 = new sigmoidGate();


    public void forwardNeuron(){

        SingleNeuron ax,by,axpby,axpbypc;
        double s;
        ax = mlp0.forward(a,x);
        by = mlp1.forward(b,y);
        axpby = addg0.forward(ax,by);
        axpbypc = addg1.forward(axpby, c); // a*x + b*y + c = 2
        s = sg0.forward(axpbypc);

        System.out.println("S = " + s);
    }
}
