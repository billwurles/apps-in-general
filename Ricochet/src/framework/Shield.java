/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package framework;

/**
 *
 * @author Will
 */
public class Shield extends Piece implements IShield {
    private final double damping;
    
    public Shield(int owner, String name, double radius, double damping, double health, double x, double y){
        super(owner, name, radius, health, x, y);
        this.damping = damping;
    }
    
    @Override
    public double getDamping() {
        return damping;
    }
    
    @Override
    public String toString(){
        return "shield";
    }
    
}
