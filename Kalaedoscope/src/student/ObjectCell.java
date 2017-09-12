package student;

import java.awt.Color;
import static java.lang.Math.random;
import java.util.ArrayList;
import java.util.Random;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author p0073862
 */
public class ObjectCell {

    private int width;
    private int height;
    private ArrayList<KSquare> squares;
    private ArrayList<KTriangle> triangles;
    private int nbr_squares = 450;
    private int nbr_triangles = 450;
    private Color[] colors ={Color.RED, Color.GREEN, Color.BLUE, Color.CYAN, Color.MAGENTA, Color.ORANGE, Color.PINK, Color.YELLOW};
    private static final int nudge_val = 4;
    

    public ObjectCell(int width, int height) {
        this.width = width;
        this.height = height;
        reset();
    }

    public ArrayList<int[]> getShapeData() {
        ArrayList<int[]> result = new ArrayList<int[]>();
        for (KSquare s : squares){
            result.add(s.getData());
            
        }
        return result;
    }

    public void reset() {
        Random rand = new Random();
        squares = new ArrayList<>();
        for(int i=0;i<nbr_squares;i++){
            int newX = rand.nextInt(width);
            int newY = rand.nextInt(height);
            int newW = rand.nextInt(30);
            Color newC = colors[rand.nextInt(colors.length)];
            squares.add(new KSquare(newX, newY, newW, newC));
        }
        
        triangles = new ArrayList<>();
        for(int i=0; i<nbr_triangles; i++){
            int newX = rand.nextInt(width);
            int newY = rand.nextInt(height);
            int newD = rand.nextInt(20);
            Color newC = colors[rand.nextInt(colors.length)];
            triangles.add(new KTriangle(newX, newY, newD, newC));
                    
        }
        
    }

        public void but1() {
    }

    public void but2() {
    }

    public void nudge() {
        for(KSquare s : squares){
            s.nudge(width, height, nudge_val);
        }
        for(KTriangle t : triangles){
            t.nudge(width, height, nudge_val);
        }
    }
    
    private ArrayList<int[]> getRedBlackSquares() {

        ArrayList<int[]> result = new ArrayList<int[]>();
        int squareWidth = Math.min(width, height) / 50;
        int sep = squareWidth * 3 / 2;
        boolean black = true;
        for (int i = 0;
                i < width;
                i += sep) {
            for (int j = 0; j < height; j += sep) {
                int[] data = new int[11];
                data[0] = black ? 0 : 255;
                data[1] = 0;
                data[2] = 0;
                data[3] = i;
                data[4] = j;
                data[5] = i + squareWidth;
                data[6] = j;
                data[7] = i + squareWidth;
                data[8] = j + squareWidth;
                data[9] = i;
                data[10] = j + squareWidth;
                result.add(data);
                black = !black;
            }
        }
        return result;
    }
}
