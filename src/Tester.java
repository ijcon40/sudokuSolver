
public class Tester {

    public static void main(String[] args) {
        // Tile box1 = new Tile();
        // Tile box2 = new Tile(1);

        // System.out.println(box1.toString());
        // System.out.println(box2.toString());

        Board b = new Board();

        b.setTile(0, 0, 1);
        // System.out.println(b.getTileInfo(0, 0));

        b.print();
        b.solve(0, 0, false);
    }

}
