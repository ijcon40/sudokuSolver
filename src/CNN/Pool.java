package CNN;

public class Pool {
    private int poolSize = 3;
    private indices[] derivCoordList;

    public Pool(){};

    public Pool(int poolSize){
        this.poolSize = poolSize;
    }

    public double[][] pool(double[][] activationLayer){
        //Assume no zero padding
        int padding = poolSize/2;
        double[][] pooledLayer = new double[activationLayer.length-2*padding][activationLayer[0].length-2*padding];
        this.derivCoordList = new indices[(activationLayer.length-2*padding)*(activationLayer.length-2*padding)];
        int derivListIndex = 0;
        for(int x = 0; x<activationLayer.length-poolSize; x++){//May need to change the incrementation of poolSize
            for(int y = 0; y<activationLayer[0].length-poolSize; y++){
                pooledLayer[x][y] = getMaxInArray(getArraySub(x, x+poolSize, y, y+poolSize, activationLayer), derivListIndex);
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

    private double getMaxInArray(double[][] array, int derivListIndex){
        double Max = array[0][0];
        int xMax = 0;
        int yMax = 0;
        for(int x = 0; x<array.length; x++){
            for(int y = 0; y<array.length; y++){
                if(array[x][y]>Max){
                    Max = array[x][y];
                    xMax = x;
                    yMax = y;
                }
            }
        }
        assignDerivative(derivListIndex, xMax, yMax);
        return Max;
    }

    private void assignDerivative(int index, int x, int y){
        this.derivCoordList[index] = new indices(x,y);
    }

    private class indices{
        public int x;
        public int y;
        public indices(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
}
