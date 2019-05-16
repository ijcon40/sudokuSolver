
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GUI {
    Tile[][] tiles = new Tile[9][9];

    public GUI(){
        createAndShowGUI();
    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                GUI gui = new GUI();
            }
        });
    }

    public Container addComponentsToPane() {
        Container pane = new Container();
        pane.setLayout(new GridLayout(9,9,0,0));
        for(int counter1 = 0; counter1<9; counter1++){
            //This is the y
            for(int counter = 0; counter<9; counter++){
                //This is the x
                Tile test = new Tile(counter, counter1);
                tiles[counter][counter1] = test;
                pane.add(test);
            }
        }
        return pane;


    }

    private void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("Sudoku Solver");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Container fullPanel = frame.getContentPane();
        fullPanel.setLayout(new BorderLayout(0,0));
        fullPanel.add(BorderLayout.PAGE_START, addComponentsToPane());
        //Add the solve button here
        JButton solveButton = new JButton("Solve");
        solveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                solveUI();
            }
        });
        fullPanel.add(BorderLayout.CENTER, solveButton);

        frame.pack();
        frame.setVisible(true);
    }

    private Board makeBoardFromUI(){
        Board b = new Board();
        for(int x = 0; x<tiles.length; x++){
            for(int y = 0; y<tiles[0].length; y++){
                if(tiles[x][y].getValue()!=0)
                b.setTile(x,y, tiles[x][y].getValue());
            }
        }
        return b;
    };

    private void updateUIfromBoard(Board b){
        for(int x = 0; x<9; x++){
            for(int y = 0; y<9; y++){
                tiles[x][y].setValue(b.getTile(x,y).getVal());
            }
        }
    }

    public void solveUI(){
        Board b = makeBoardFromUI();
        b.solveStack();
        updateUIfromBoard(b);
    }















    private class Tile extends JComponent{
        int value = 0;
        boolean selected = false;
        private int tileSize = 60;
        private int strokeWidth = 15;
        private boolean render;
        private boolean original = false;
        int xIndex; int yIndex;

        public Tile(boolean render, int x, int y){
            this.render =render;
            this.xIndex = x;
            this.yIndex = y;
            setForeground(Color.black);
            addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    System.out.println("Clicked x:"+xIndex);
                    super.mouseClicked(e);
                    selected=!selected;
                    requestFocusInWindow();
                }
            });
            addKeyListener(new KeyListener() {
                @Override
                public void keyTyped(KeyEvent e) {
                    System.out.println(e.getKeyChar()-48);
                    int value = e.getKeyChar()-48;
                    if(value<10&&value>=0){
                        setValue(value, true);
                    }
                    if(value==-38){
                        solveUI();
                        //This is just the enter key
                    }
                    if(value==-40){
                        original = false;
                        setValue(0, false);
                    }
                }

                @Override
                public void keyPressed(KeyEvent e) {

                }

                @Override
                public void keyReleased(KeyEvent e) {

                }
            });
        }

        public Tile(int x, int y){
            this(true, x, y);
        }


        public void paintComponent(Graphics graphics){
            if(render) {
                Graphics2D graphics2D = (Graphics2D) graphics;
                if (value != 0) {
                    int fontSize = tileSize / 2;
                    Font font = new Font("TimesRoman", Font.PLAIN, fontSize);
                    String stringType = ("" + value);
                    FontMetrics metrics = graphics2D.getFontMetrics(font);
                    int x = ((tileSize - metrics.stringWidth(stringType)) / 2);
                    int y = ((tileSize - metrics.getHeight()) / 2) + metrics.getAscent();
                    graphics2D.setFont(font);
                    graphics.drawRect(0, 0, tileSize, tileSize);
                    graphics2D.setColor(Color.lightGray);
                    if(original){
                        graphics2D.setColor(Color.BLACK);
                    }
                    graphics2D.drawString(stringType, x, y);
                    graphics2D.setColor(Color.black);
                    graphics2D.setStroke(new BasicStroke(strokeWidth));
                } else {
                    graphics2D.drawRect(0, 0, tileSize, tileSize);
                    graphics2D.setStroke(new BasicStroke(strokeWidth));

                }
                if(xIndex==2||xIndex==5||xIndex==8){
                    graphics2D.drawLine(tileSize, 0, tileSize, tileSize);
                }
                if(yIndex==2||yIndex==5||yIndex==8){
                    graphics2D.drawLine(0, tileSize, tileSize, tileSize);
                }
                if(yIndex==0){
                    graphics2D.drawLine(0,0,tileSize, 0);
                }
                if(xIndex==0){
                    graphics2D.drawLine(0,0,0, tileSize);
                }
            }
        }

        public Dimension getPreferredSize() {
            if (render) {
                return new Dimension(tileSize, tileSize);
            }
            else{
                return new Dimension(0,0);
            }
        }

        public void setValue(int value) {
            setValue(value, false);
        }

        private void setValue(int value, boolean original){
            this.value = value;
            this.selected=false;
            if(original){
                this.original = original;
            }
            repaint();
        }

        public int getValue(){
            return value;
        }
    }

}
