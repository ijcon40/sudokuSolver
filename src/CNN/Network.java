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

    //This loss function may need to be rewritten
    public double[] getLoss(double[] expected, double[] actual){
        double[] PointwiseLoss = new double[expected.length];
        for(int counter = 0; counter<expected.length; counter++){
            PointwiseLoss[counter] = actual[counter]-expected[counter];
        }
        return PointwiseLoss;
        //This may not be the best loss function, consider using euclidean geometry to get a cosine difference
    }

    public double[] backProp(double[] passedDerivatives, int layer){
        //Resolve: if layer==-1 then return the passed derivatives for convolution back prop
        //layer should be passed in as length-1
        if(layer==-1){
            return passedDerivatives;
        }
        double[] nextDerivs = new double[passedDerivatives.length];
        for(double toZero: nextDerivs){
            toZero = 0.0;
        }
        for(Neuron neuron: Neurons[layer]){
            nextDerivs = combineArray(nextDerivs, neuron.getDerivatives(passedDerivatives));
        }
        return backProp(nextDerivs, layer-1);
    }

    private double[] combineArray(double[] array1, double[] array2){
        int n = 0;
        double[] combined = new double[array1.length];
        for (double array1val: array1){
            combined[n]=array1val+array2[n];
            n++;
        }
        return combined;
    }




}