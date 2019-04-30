
import java.util.ArrayList;

public class Tile {

    private int val;
    private boolean known;

    public Tile() {
        val = 0;
        known = false;
    }

    public Tile(int num) {
        val = num;
        known = true;
    }

    public void setVal(int num) {
        val = num;
    }

    public int getVal() {
        return val;
    }

    public boolean getKnown() { return known; }

    public boolean increment() {
        if (val < 9) {
            val++;
            return true;
        } else {
            val = 0;
            return false;
        }
    }

    public String toString() {
        String out = "";

        if(known) {
            out = "FINALVAL: " + val;
        } else {
            out = "VAL: " + val;
        }

        return out;
    }

}