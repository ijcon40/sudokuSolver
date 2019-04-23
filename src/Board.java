
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


}