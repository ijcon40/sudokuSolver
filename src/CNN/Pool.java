package CNN;

public class Pool {
    private int poolSize = 3;

    public Pool(){};

    public Pool(int poolSize){
        this.poolSize = poolSize;
    }

    public double[][] pool(double[][] activationLayer){
        //Assume no zero padding
        int padding = poolSize/2;
        double[][] pooledLayer = new double[activationLayer.length-2*padding][activationLayer[0].length-2*padding];
        for(int x = 0; x<activationLayer.length-poolSize; x++){
            for(int y = 0; y<activationLayer[0].length-poolSize; y++){
                pooledLayer[x][y] = getMaxInArray(getArraySub(x, x+poolSize, y, y+poolSize, activationLayer));
            }
        }
        return pooledLayer;
    }


    private double[][] getArraySub(int xo, int xf, int yo, int yf, double[][] sourceArray){
        double[][] nextArray = new double[xf-xo][yf-yo];
        for(int x=0; x<nextArray.length; x++){
            for(int y = 0; y<nextArray[1].length; y++){
                nextArray[x][y]=sourceArray[xo+x][yo+y];
            }
        }
        return nextArray;
    }

    private double getMaxInArray(double[][] array){
        double Max = -1000000000;
        for(int x = 0; x<array.length; x++){
            for(int y = 0; y<array.length; y++){
                if(array[x][y]>Max){
                    Max = array[x][y];
                }
            }
        }
        return Max;
    }
}
