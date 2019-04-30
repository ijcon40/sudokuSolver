import java.util.Stack;

public class Board {

    private Tile[][] game;

    public Board() {
        game = new Tile[9][9];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                game[j][i] = new Tile();
            }
        }
    }

    public Board(int[] array) {
        game = new Tile[9][9];
        int y = -1;

        for (int i = 0; i < 81; i++) {

            if (i % 9 == 0) {
                y++;
            }

            if (array[i] != 0) {
                Tile newTile = new Tile(array[i]);
                game[i % 9][y] = newTile;
            }


        }
    }

    public void setTile(int xPos, int yPos, int num) {
        Tile newTile = new Tile(num);
        game[xPos][yPos] = newTile;
    }

    public Tile getTile(int xPos, int yPos) {
        return game[xPos][yPos];
    }

    public String getTileInfo(int xPos, int yPos) {
        return "(" + xPos + ", " + yPos + ") " + this.getTile(xPos, yPos).toString();
    }

    public void print() {
        int count1 = 0;
        for (int i = 0; i < 9; i++) {
            String line = "";

            line = game[0][i].getVal() + " " + game[1][i].getVal() + " " + game[2][i].getVal() + "  ";
            line = line + game[3][i].getVal() + " " + game[4][i].getVal() + " " + game[5][i].getVal() + "  ";
            line = line + game[6][i].getVal() + " " + game[7][i].getVal() + " " + game[8][i].getVal() + "  ";
            System.out.println(line);

            count1++;
            if (count1 % 3 == 0) {
                System.out.println();
            }
        }
    }

    public boolean valid(int xPos, int yPos) {

        if (game[xPos][yPos].getVal() == 0) {
            return false;
        }

        for (int i = 0; i < 9; i++) {
            if (game[xPos][yPos].getVal() == game[i][yPos].getVal() && i != xPos) {
                return false;
            }

            if (game[xPos][yPos].getVal() == game[xPos][i].getVal() && i != yPos) {
                return false;
            }
        }

        int xStart = 3 * (xPos / 3);
        int yStart = 3 * (yPos / 3);

        for (int i = xStart; i < xStart + 3; i++) {
            for (int j = yStart; j < yStart + 3; j++) {
                if (game[xPos][yPos].getVal() == game[i][j].getVal() && i != xPos && j != yPos) {
                    return false;
                }
            }
        }
        return true;
    }

    public paramObj solve(paramObj params) {
        int xPos = params.x;
        int yPos = params.y;
        boolean backTracking = params.backTracking;

        print();
        System.out.println(xPos + " " + yPos);
        //System.out.println("Guess: "+game[xPos][yPos]);

        int forXPos, forYPos = yPos;
        int backXPos, backYPos = yPos;

        backXPos = (9 + xPos - 1) % 9;
        if (xPos == 0) {
            backYPos--;
        }

        forXPos = (9 + xPos + 1) % 9;
        if (xPos == 8) {
            forYPos++;
        }


        if (yPos == 9) { //base case
            print();
            return null;
        } else if (game[xPos][yPos].getKnown() && backTracking) { //on a known tile and back tracking

            if (game[backXPos][backYPos].getKnown()) {
                return new paramObj(backXPos, backYPos, true);
            } else {
                return new paramObj(backXPos, backYPos, true);
            }

        } else if (game[xPos][yPos].getKnown() && !backTracking) { //on a known tile and not back tracking

            return new paramObj(forXPos, forYPos, false);

        } else if (!valid(xPos, yPos)) { //the tile is invalid

            if (game[xPos][yPos].increment()) {
                return new paramObj(xPos, yPos, false);
            } else {
                return new paramObj(backXPos, backYPos, true);
            }

        } else if (valid(xPos, yPos)) { //the tile is valid
            if (backTracking) {
                if (game[xPos][yPos].increment()) {
                    return new paramObj(xPos, yPos, false);
                } else {
                    return new paramObj(backXPos, backYPos, true);
                }
            } else {
                return new paramObj(forXPos, forYPos, false);
            }
        }
        return params;

    }

    public void solveStack(){
        int x = 0; int y = 0;
        Stack<paramObj> paramStack = new Stack();
        paramStack.push(new paramObj(x,y,false));
        while(!paramStack.empty()){
            paramObj argument = paramStack.peek();
            paramObj nextParams = solve(argument);
            if (nextParams==null){
                break;
            }
            else{
                paramStack.push(nextParams);
            }

        }
    }

    private class paramObj{
        public int x;
        public int y;
        public boolean backTracking;

        paramObj(int x, int y, boolean backTracking){
            this.x =x; this.y=y; this.backTracking = backTracking;
        }
    }

}