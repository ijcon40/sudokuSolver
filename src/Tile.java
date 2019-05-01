
import java.util.ArrayList;

public class Tile {

    private int val;
    String displayVal;
    private boolean known;

    public Tile() {
        val = 0;
        known = false;
        displayVal = "_";
    }

    public Tile(int num) {
        val = num;
        known = true;
        displayVal = "" + num;
    }

    public int getVal() {
        return val;
    }

    public boolean getKnown() { return known; }

    public boolean increment() {
        if (val < 9) {
            val++;
            displayVal = "" + val;
            return true;
        } else {
            val = 0;
            displayVal = "_";
            return false;
        }
    }

    public String getDisplayVal() {
        return displayVal;
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