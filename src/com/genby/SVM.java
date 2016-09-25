package com.genby;

/**
 * Support Vector Machine
 * The SVM is a very popular linear classifier
 *
 * Support Vector Machine “Force Specification”:
 * If we feed a positive datapoint through the SVM circuit and the output value is less than 1, pull on the circuit with force +1.
 * This is a positive example so we want the score to be higher for it.
 * Conversely, if we feed a negative datapoint through the SVM and the output is greater than -1,
 * then the circuit is giving this datapoint dangerously high score: Pull on the circuit downwards with force -1.
 * In addition to the pulls above, always add a small amount of pull on the parameters a,b (notice, not on c!) that pulls them towards zero.
 * You can think of both a,b as being attached to a physical spring that is attached at zero. Just as with a physical spring, this will make the pull proprotional to the value of each of a,b (Hooke’s law in physics, anyone?).
 * For example, if a becomes very high it will experience a strong pull of magnitude |a| back towards zero.
 * This pull is something we call regularization, and it ensures that neither of our parameters a or b gets disproportionally large.
 * This would be undesirable because both a,b get multiplied to the input features x,y (remember the equation is a*x + b*y + c), so if either of them is too high, our classifier would be overly sensitive to these features.
 * This isn’t a nice property because features can often be noisy in practice,
 * so we want our classifier to change relatively smoothly if they wiggle around
 */

public class SVM extends SingleNeuron{
    // random initial parameter values
    private SingleNeuron a = new SingleNeuron(1.0,0.0);
    private SingleNeuron b = new SingleNeuron(-2.0,0.0);
    private SingleNeuron c = new SingleNeuron(-1.0,0.0);
    Circuit circuit = new Circuit();
    double unit_out;

    public double forward(SingleNeuron x,SingleNeuron y){
        this.unit_out = circuit.forward(x,y,a,b,c);
        return  unit_out;
    }
    public void backward(int label){
        this.a.gradient = 0.0;
        this.b.gradient = 0.0;
        this.c.gradient = 0.0;
        int pull = 0;
        if(label == 1 && this.unit_out < 1){
            pull = 1;
        }
        if(label == 0 && this.unit_out > -1){
            pull = -1;
        }
        circuit.backward(pull);

        //this.a.gradient = a;
        //this.b.gradient = b;
    }
    //Next learnFrom and parameterUpdate
}
