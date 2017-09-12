/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package framework;

/**
 * Interface to be implemented by classes that represent pieces (either weapons
 * or shields)
 * @author davidsutton
 */
public interface IPiece {

    /**
     * Get the number of the player who owns the piece
     * @return number of the player who owns the piece
     */
    public int getOwner();

    /**
     * Get the current health of the piece
     * @return a value between 0 and 100 representing the health of the piece
     */
    public double getHealth();

    /**
     * Get the x coordinate of the piece
     * @return x coordinate of the centre of the piece, expressed in game coordinates
     */
    public double getXPos();

    /**
     * Get the y coordinate of the piece
     * @return y coordinate of the centre of the piece, expressed in game coordinates
     */
    public double getYPos();

    /**
     * Get the radius of the piece
     * @return radius of the piece, expressed in game coordinates
     */
    public double getRadius();

    /**
     * Get the name of the piece 
     * @return the name of the piece (this can be used to identify pieces when
     * displaying them)
     */
    public String getName();
    
    public double getDamping();
    
    /** 
     * Method to be called by the game engine when a projectile hits this piece
     * This method should not be called in code written by the student.
     * @param impact object representing the impact of the incoming projectile
     * @return object representing the outgoing projectile. This should not be the
     * same as the object representing the incoming projectile
     */
    public IProjectile onImpact(IImpact impact);

    /**
     * Method to be called by the game engine when a piece has been successfully moved to
     * a new position. The method should be implemented so that the x and y coordinates 
     * are replaced by those specified in the parameters to this method. This method
     * is called by the game engine, after it has checked that a move is valid. It should
     * not be called in code written by students
     * @param x new x coordinate for piece, in game coordinates
     * @param y new y coordinate for piece, in game coordinates
     */
    public void onSuccessfulMove(double x, double y);

    public boolean isSelected();
    
    public void setSelected(boolean selected);

}
