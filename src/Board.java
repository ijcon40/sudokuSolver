
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

    public void solve(int xPos, int yPos, boolean backTracking) {

        int nextXPos, nextYPos;

        if (backTracking && ) {

        }

        if (backTracking == false && game[xPos][yPos].getKnown()) {
            solve()
        }
    }


}