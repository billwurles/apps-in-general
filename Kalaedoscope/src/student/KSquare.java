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
public class KSquare {
    private Color color;
    private int x;
    private int y;
    private int width;
    private Random rand = new Random();
    
    public KSquare(int x, int y, int w, Color color){
        this.x = x;
        this.y = y;
        this.width = w;
        this.color = color;
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
    
    public int[] getData(){
        int[] data = new int[11];
        data[0]=getColor().getRed();
        data[1]=getColor().getGreen();
        data[2]=getColor().getBlue();
        data[3]=getX()-getWidth()/2;
        data[4]=getY()-getWidth()/2;
        data[5]=getX()+getWidth()/2;
        data[6]=getY()-getWidth()/2;
        data[7]=getX()+getWidth()/2;
        data[8]=getY()+getWidth()/2;
        data[9]=getX()-getWidth()/2;
        data[10]=getY()+getWidth()/2;
        return data;
    }
    public Color getColor(){
        return color;
    }
    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }
    public int getWidth(){
        return width;
    }
            
}
