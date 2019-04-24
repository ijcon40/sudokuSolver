package CNN;

public class Neuron {
    private double weights[];
    private double bias;
    private double ReLuSlope;
    private Equation ReLu = (double... x)->{
        //A Leaky ReLU function abbreviated for simpler notation
        if(x[0]>0){
            return x[0];
        }
        else{
            return 0.01*x[0];
        }

    };

    public Neuron(int NWeights){
        //NWeights also corresponds to the size of the previous layer as each weight has a 1:1 correspondence to a previous neuron
        this.weights = new double[NWeights];
        this.bias = 0.01;
        HeEtAlWeights();//This should assign them all
    }

    public Neuron(double[] weights){
      this.weights = weights;
      this.bias = 0.01;//This could be 0. It is disputed whether or not this helps forward propagation in ReLUs
    }

    public Neuron(double[] weights, double bias){
        this.weights = weights;
        this.bias = bias;
    }

    private void HeEtAlWeights(){
        //This may not overwrite the
        for(double weight: weights){
            weight = Math.random()*Math.sqrt(2/weights.length);
        }
    }


    public double getRawOutput(double[] inputs){
        double inputSum=0;
        for(int a = 0; a<inputs.length; a++){
            inputSum+=inputs[a]*weights[a];//input and corresponding weight
        }
        return inputSum+bias;
    }

    public double getActivatedOutput(double[] inputs){
        //Adding the saving of the ReLu slope with respect to the outputs should make the backprop much easier
        double val = ReLu.resolve(getRawOutput(inputs));
        if(val<0){
            ReLuSlope = .01;
        }
        else{
            ReLuSlope = 1.0;
        }
        return val;
    }

    public double[] getDerivatives(double[] prevDerivs){
        //So dL/dO will be passed, but the first layer will be all 1 since they are additive
        //Next we just need to see how the previous layer outputs affected this one neuron. Bias can be ignored since derivative of bias is just 0
        //First Step: Take the derivative of all the prevDerivs with respect to ReLu
    }


}
