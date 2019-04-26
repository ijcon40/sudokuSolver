package CNN;

public class Network{
    Neuron[][] Neurons;

    public Network(int depth, int width){
        this.Neurons = new Neuron[depth][width];//Array[x][y]
        for(int x = 0; x<depth; x++){
            for(int y = 0; y<width; y++){
                Neurons[x][y] = new Neuron(width);
            }
        }
    }

    //Part 1: Forward Propagation
    public double[] forwardProp(double[] input, int layer){
        //Resolve: if there is no layer or out of bounds, then return the input
        if(layer>Neurons.length){
            return input;
        }
        //Calculate the weight layer that needs to be passed to every neuron
        double[] ReLuOutputArray = new double[Neurons[0].length];
        int index = 0;
        for(Neuron neuron: Neurons[layer]){
            ReLuOutputArray[index] = neuron.getActivatedOutput(input);
            index++;
        }
        return forwardProp(ReLuOutputArray, layer+1);
    }

    public double[] getLoss(double[] expected, double[] actual){
        double[] PointwiseLoss = new double[expected.length];
        for(int counter = 0; counter<expected.length; counter++){
            PointwiseLoss[counter] = actual[counter]-expected[counter];
        }
        return PointwiseLoss;
    }

    public double[] backProp(double[] passedDerivatives, int layer){
        //Resolve: if layer==-1 then return the passed derivatives for convolution back prop
        return null;
    }


    //Part 2: Back Propagation

}