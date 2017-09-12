/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package student;

import java.awt.Color;
import java.util.Random;

/**
 *
 * @author 14038690
 */
public class KTriangle {
    private Color color;
    private int x;
    private int y;
    private int d;
    private final Random rand = new Random();
    
    /**
     *
     * @param x
     * @param y
     * @param d
     * @param color
     */
    public KTriangle(int x ,int y, int d, Color color){
        this.x = x;
        this.y = y;
        this.d = d;
        this.color = color;
    }
    
    public int[] getData(){
        int[] data = new int[9];
        
        double cosTheta = Math.cos(Math.PI / 6);
        double sinTheta = Math.sin(Math.PI / 6);

        data[0] = getColor().getRed();
        data[1] = getColor().getGreen();
        data[2] = getColor().getBlue();
        data[3] = getX();
        data[4] = getY()-getD();
        data[5] = (int) (getX() + getD()*cosTheta);
        data[6] = (int) (getY() + getD()*sinTheta);
        data[7] = (int) (getX() - getD()*cosTheta);
        data[8] = (int) (getY() + getD()*sinTheta);
        
        return data;
    }
    
    public void nudge(int xmax, int ymax, int nVal){
        x=x-nVal+rand.nextInt(2*nVal+1);
        if (x<0){
            x=0;
        }
        if (x>xmax){
            x=xmax;
        }
        
        y=y-nVal+rand.nextInt(2*nVal+1);
        if (y<0){
            y=0;
        }
        if (y>ymax){
            y=ymax;
        }
    }

    public Color getColor() {
        return color;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getD() {
        return d;
    }
    
}
