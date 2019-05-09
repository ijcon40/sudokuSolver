
import java.util.Scanner;

public class Tester {

    public static void main(String[] args) {
        // Tile box1 = new Tile();
        // Tile box2 = new Tile(1);

        // System.out.println(box1.toString());
        // System.out.println(box2.toString());

        Board b = new Board();

        b.setTile(1,0,9);
//        b.setTile(4,0,3);
//        b.setTile(7,0,9);
//        b.setTile(8,0,6);

        b.setTile(3,1,1);
        b.setTile(4,1,9);
        b.setTile(6,1,3);
        b.setTile(8,1,7);

        b.setTile(3,2,8);

        b.setTile(2,3,5);
        b.setTile(4,3,7);
        b.setTile(8,3,9);

//        b.setTile(4,3,7);
//        b.setTile(5,3,8);
//        b.setTile(6,3,9);

        b.setTile(3,4,4);
        b.setTile(7,4,8);
        b.setTile(8,4,1);

        b.setTile(1,5,2);
        b.setTile(7,5,7);
        //b.setTile(8,5,4);

        b.setTile(1,6,7);
        b.setTile(5,6,4);
        //b.setTile(5,6,4);

        b.setTile(3,7,2);
        b.setTile(4,7,5);
        b.setTile(7,7,4);

        b.setTile(0,8,5);
        b.setTile(1,8,4);
        b.setTile(2,8,9);
        b.setTile(5,8,3);
        b.setTile(7,8,2);
        // System.out.println(b.getTileInfo(0, 0));
        b.print();

//        Scanner in = new Scanner(System.in);
//        String nothing = in.next();

        b.solveStack();
    }

}
