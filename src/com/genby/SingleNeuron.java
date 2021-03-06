package com.genby;


public class SingleNeuron {

    protected double gradient;
    private double value;

    SingleNeuron(double value, double gradient){
        this.value = value;
        this.gradient = gradient;
    }

    SingleNeuron() { //if give not value in the construction
       System.out.println("Run");

    }

    public class sigmoidGate{

        private SingleNeuron u0,u1;
        private  SingleNeuron utop;

        public double sigmoidGate(double x) {return (1 / (1 + Math.exp(-x)));}

        public double forward(SingleNeuron u0){
            this.u0 = u0;
            this.utop = new SingleNeuron(this.sigmoidGate(this.u0.value),0.0);
            return this.utop.value; //edit <<<-----
        }
        public void backward(){
            double s = 0;
            s = this.sigmoidGate(u0.value);
            this.u0.gradient += (s * (1 - s)) * this.utop.gradient;
        }
    }

    public class multiplyGate{
        private SingleNeuron u0,u1;
        private  SingleNeuron utop;
        public SingleNeuron forward(double u0, double u1){
            this.u0.value = u0;
            this.u1.value = u1;
             SingleNeuron temp = new SingleNeuron(this.u0.value * this.u1.value, 0.0);
            this.utop = temp;
            return temp;
        }
        //For SN types.
        public SingleNeuron forward(SingleNeuron u0, SingleNeuron u1){
            this.u0= u0;
            this.u1 = u1;
            SingleNeuron temp = new SingleNeuron(this.u0.value * this.u1.value, 0.0);
            this.utop = temp;
            return temp;
        }

        public void backward(){
            this.u0.gradient += this.u0.value * this.utop.gradient;
            this.u1.gradient += this.u1.value * this.utop.gradient;
        }
    }
    public class addGate{

        private SingleNeuron u0,u1;
        private  SingleNeuron utop;

        public SingleNeuron forward(double u0, double u1){
            this.u0.value = u0;
            this.u1.value = u1;
            this.utop = new SingleNeuron(this.u0.value + this.u1.value, 0.0);
            return utop;
        }
        //For SN types.
        public SingleNeuron forward(SingleNeuron u0, SingleNeuron u1){
            this.u0= u0;
            this.u1 = u1;
            this.utop = new SingleNeuron(this.u0.value + this.u1.value, 0.0);
            return utop;
        }
        public void backward(){
            this.u0.value += this.utop.gradient * 1;
            this.u1.value += this.utop.gradient * 1;
        }
    }

    public class Circuit {
        multiplyGate mulg0 = new multiplyGate();
        multiplyGate mulg1 = new multiplyGate();
        addGate addg0 = new addGate();
        addGate addg1 = new addGate();
        SingleNeuron ax,by,axpby,axpbypc,grad_top;

        public double forward(SingleNeuron x, SingleNeuron y, SingleNeuron a,SingleNeuron b, SingleNeuron c){
            ax = this.mulg0.forward(a,x);
            by = this.mulg1.forward(b,y);
            axpby = this.addg0.forward(ax.value,by.value);
            axpbypc = this.addg1.forward(axpby,c);
            return axpbypc.value;
        }
        public void backward(double grad_top){
            this.grad_top.value = grad_top;
            addg0.backward();
            addg1.backward();
            mulg0.backward();
            mulg1.backward();
        }
    }

    //Data Base
 /*   private static SingleNeuron a = new SingleNeuron(1.0, 0.0);
    private static SingleNeuron b = new SingleNeuron(2.0, 0.0);
    private static SingleNeuron c = new SingleNeuron(-3.0, 0.0);
    private static SingleNeuron x = new SingleNeuron(-1.0, 0.0);
    private static SingleNeuron y = new SingleNeuron(3.0, 0.0);

    //Primary functions
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
*/
}
