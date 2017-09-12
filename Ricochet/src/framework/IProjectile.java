/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package framework;

/**
 * Interface to be implemented by objects representing projectiles
 * @author davidsutton
 */
public interface IProjectile {
    /**
     * Get the momentum of the projectiles
     * @return a positive value representing the momentum of the projectile
     */
    public double getMomentum();
    
    /**
     * Get the direction of the projectile
     * @return the direction of the projectile, represented as an angle measured
     * anti-clockwise from the game coordinate x axis, in radians.
     */
    public double getDirection();
    
    /**
     * Get the x coordinate of the position from which the projectile originated
     * @return x coordinate of position from which projectile originates, in game
     * coordinates
     */
    public double getOriginX();
    
    /**
     * Get the y coordinate of the point from which the projectile originated
     * @return y coordinate of position from which projectile originates, in game
     * coordinates
     */
    public double getOriginY();
}
