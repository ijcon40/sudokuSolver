package CNN;

public class Filter {
    double[][] weightArray;


    public Filter(int array_dimensions){
        weightArray = new double[array_dimensions][array_dimensions];
        HeEtAlWeights();
    }

    private void HeEtAlWeights(){
        for(double[] weights: weightArray) {
            for (double weight : weights) {
                weight = Math.random() * Math.sqrt(2.0 / (weights.length*weightArray.length));
            }
        }
    }

    public double[][] convolve(double[][] flatLayer){
        //we need to select a section of the flat layer, with the same dimensions as the filter
        //Assume correct zero padding
        //Also that filters are odd length and square
        int padding = (weightArray.length-1)/2;
        double[][] activationLayer = new double[flatLayer.length-((weightArray.length-1)/2)][flatLayer[0].length-((weightArray.length-1)/2)];
        for(int x = 0; x<flatLayer.length-weightArray.length; x++){
            for(int y = 0; y<flatLayer[0].length-weightArray[0].length; y++){
                activationLayer[x-padding][y-padding]=dotMultiplication(getArraySub(x, x+weightArray.length, y, y+weightArray.length, flatLayer));
            }
        }
        return activationLayer;
    }

    private double[][] getArraySub(int xo, int xf, int yo, int yf, double[][] sourceArray){
        double[][] next = new double[xf-xo][yf-yo];
        for(int x=0; x<next.length; x++){
            for(int y = 0; y<next[0].length; y++){
                next[x][y]=sourceArray[xo+x][yo+y];
            }
        }
        return next;
    }

    private double dotMultiplication(double[][] array){
        //[x][y]
        double total = 0;
        for(int x = 0; x<array.length; x++){
            for(int y = 0; y<array[0].length; y++){
                total+=weightArray[x][y]*array[x][y];
            }
        }
        return total;
    }

    //TODO: get the backpropagation down, and add calculus based weight adjustments
}
