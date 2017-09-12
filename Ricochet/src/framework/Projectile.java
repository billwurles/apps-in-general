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
public class Projectile implements IProjectile{
    private double momentum;
    private double direction;
    private double originX;
    private double originY;
    
    public Projectile(double momentum, double direction, double originX, double originY){
        this.momentum = momentum;
        this.direction = direction;
        this.originX = originX;
        this.originY = originY;
    }
    
    @Override
    public double getMomentum() {
        if(momentum >= 0){
            return momentum;
        }
        return 0;
    }

    @Override
    public double getDirection() {
        return direction;
    }

    @Override
    public double getOriginX() {
        return originX;}

    @Override
    public double getOriginY() {
        return originY;}
}
