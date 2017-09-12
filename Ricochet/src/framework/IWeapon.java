/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package framework;

/**
 * Interface to be implemented by classes representing weapons
 *
 * @author davidsutton
 */
public interface IWeapon extends IPiece {

    /**
     * Set the orientation of the piece
     *
     * @param angle the orientation of the piece, in radians. This is the angle at which a
     * projectile will emerge from the piece when fired
     */
    public void setOrientation(double angle);

    /**
     * Get the orientation of the piece
     * @return the orientation of the piece, in radians. This is the angle at which a
     * projectile will emerge from the piece when fired
     */
    public double getOrientation();

    /**
     * Method called by the game engine when the weapon is fired. This method should not
     * be called in code written by students
     * @return The projectile that emerges from the weapon when it is fired
     */
    public IProjectile onFireProjectile();

}
