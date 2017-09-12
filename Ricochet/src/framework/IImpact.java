/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package framework;

/**
 * Interface implemented by objects that represent the impact of a projectile
 * on a piece, or the projectile's exit from the playing area.
 * 
 * Note: students do not need to create an implementation of this interface. All objects
 * that implement it are created by the game engine.
 * @author p0073862
 */
public interface IImpact {

    /**
     * Get the angle of incidence of the projectile
     * @return angle of incidence in radians (calculated as angle between the
     * direction of the incoming projectile and the tangent to the surface of
     * the piece, at the point of impact).
     */
    double getAngle();

    /**
     * Get the piece (if any) on which the impact has occurred
     * @return null if this object represents the projectile's exit from
     * the playing area, otherwise a reference to the piece on which the 
     * impact occurred.
     */
    IPiece getPiece();

    /**
     * Get the projectile
     * @return the projectile that caused the impact
     */
    IProjectile getProjectile();

    /**
     * Get the x coordinate of impact
     * @return the x coordinate of the point at which the impact occurred, in game
     * coordinates
     */
    double getxImpact();

    /**
     * Get the y coordinate of impact
     * @return the x coordinate of the point at which the impact occurred, in game
     * coordinates
     */
    double getyImpact();
    
}
