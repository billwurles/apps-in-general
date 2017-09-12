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
public class Weapon extends Piece implements IWeapon {
    private double orientation;
    private double momentum;
    
    public Weapon(int owner, String name, double radius, double momentum, double health, double x, double y){
        super(owner, name, radius, health, x, y);
        this.orientation = 0;
        this.momentum = momentum;
    }

    public double getMomentum() {
        return momentum;
    }

    public void setMomentum(double momentum) {
        this.momentum = momentum;
    }
    
    @Override
    public void setOrientation(double angle) {
        this.orientation = angle;
    }

    @Override
    public double getOrientation() {
        return orientation;
    }

    @Override
    public IProjectile onFireProjectile() {
        IProjectile newProjectile = new Projectile(this.getMomentum(), this.getOrientation(), this.getXPos(), this.getYPos());
        return newProjectile;
    }
    
    @Override
    public String toString(){
        return "weapon";
    }

}
