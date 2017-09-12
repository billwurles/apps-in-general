/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package framework;

/**
 * Interface to be implemented by classed representing shields
 * @author p0073862
 */
public interface IShield extends IPiece {
    /**
     * Get the damping coefficient of the shield
     * @return a number in the range 0-1, representing the damping coefficient of the
     * shield
     */
    double getDamping();
}
