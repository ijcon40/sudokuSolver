package CNN;

public class Convolution {
    private Filter[] filters;
    private final int FILTER_SIZE = 4;
    public Convolution(int numFilters){
        filters = new Filter[numFilters];
        for(Filter filter: filters){
            filter = new Filter(FILTER_SIZE);
        }
    }

    public double[][][] convolve(double[][][] activationLayers){
        //Assign by z,x,y
        double[][][] newLayers = new double[filters.length*activationLayers.length][activationLayers[0].length][activationLayers[0][0].length];
        int activationIndex = 0;
        for(double[][] activationLayer: activationLayers){
            //figure out how organization works with activation layers
            for(Filter filter: filters){
                newLayers[activationIndex] = filter.convolve(activationLayer);
                activationIndex++;
            }
        }
        return newLayers;
    }

    //Part 2: get dO/dF for output via a filter

    private void backPropActivationLayers(double[][][] dOdA){
        //change in output with respect to activation layer
        //format: z,x,y

    }

    private void backPropFilters(double[][][] dOdF){
        //change in output with respect to filter layer

    }


}
