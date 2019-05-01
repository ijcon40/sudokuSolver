package CNN;

public abstract class Layer {
    public abstract double[][] produceNextLayer(double[][] previousLayer);

    private double[][][] produceNextBlock(double[][][] prevBlock){
        return null;
    }


}
