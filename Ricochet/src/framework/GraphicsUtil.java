/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package framework;

import java.awt.Color;
import java.awt.Graphics;
import java.util.List;

/**
 *
 * @author p0073862
 * 
 * Various static methods useful for drawing pieces and processing mouse clicks
 * on them.
 */
public class GraphicsUtil {

    private static final int MUZZLE_FRACTION = 3;
    private static final Color MUZZLE_COLOR = Color.YELLOW;

    /**
     * Draw a shield. The shield is drawn as two concentric circles. The radius
     * of the outer circle is the radius of the piece (converted to screen
     * coordinates). The radius of the inner circle indicates the health of the 
     * piece. The ratio of the radiuses of the two circles is equal to the ratio
     * of the current health of the piece and the health it had when created
     * (as expressed by the origHealth parameter).
     * 
     * @param shield The Shield to be drawn
     * @param g Graphics object to be used when drawing the Shield
     * @param gameSpace GameSpace object expressing the relationship between
     * game and screen coordinates
     * @param origHealth Original health of the Shield
     */
    public static void drawShield(IShield shield, Graphics g, GameSpace gameSpace, double origHealth) {
        drawPiece(shield, g, gameSpace, origHealth);
    }

    /**
     * Draw a weapon. The weapon is drawn using the same two concentric circles
     * as a shield, but also has a line, with a smaller circle at the end of it,
     * which represents the orientation of the weapon.
     * 
     * @param weapon Weapon to be drawn
     * @param g Graphics object to be used when drawing the weapon
     * @param gameSpace GameSpace object expressing the relationship between
     * game and screen coordinates
     * @param origHealth Original health of the Weapon
     */
    public static void drawWeapon(IWeapon weapon, Graphics g, GameSpace gameSpace, double origHealth) {
        g.setColor(Color.BLACK);
        Color oldColor = g.getColor();
        drawPiece(weapon, g, gameSpace, origHealth);

        double orientation = weapon.getOrientation();
        int x1 = gameSpace.convertToScreenX(weapon.getXPos());
        int y1 = gameSpace.convertToScreenY(weapon.getYPos());
        int radius = gameSpace.convertToScreenDistance(weapon.getRadius());

        int x2 = gameSpace.convertToScreenX(weapon.getXPos()
                + weapon.getRadius() * Math.cos(weapon.getOrientation()));
        int y2 = gameSpace.convertToScreenY(weapon.getYPos()
                + weapon.getRadius() * Math.sin(weapon.getOrientation()));

        double frac = 1 - 1f / MUZZLE_FRACTION;

        int muzzleCentreX
                = x1 + (int) Math.round(frac * radius * Math.cos(orientation));
        int muzzleCentreY
                = y1 - (int) Math.round(frac * radius * Math.sin(orientation));
        int muzzleRadius = radius / MUZZLE_FRACTION;
        int topMuzzleX = muzzleCentreX - muzzleRadius;
        int topMuzzleY = muzzleCentreY - muzzleRadius;
        g.setColor(MUZZLE_COLOR);
        g.fillOval(topMuzzleX, topMuzzleY, 2 * muzzleRadius, 2 * muzzleRadius);

        g.setColor(Color.BLACK);
        g.drawLine(x1, y1, x2, y2);

        
        g.setColor(oldColor);
    }

    /**
     * Determine whether a given point (expressed in game coordinates) lies
     * within the small circle that indicates the orientation of a weapon
     * @param piece A piece in the game
     * @param x x coordinate of a point in the game
     * @param y y coordinate of a point in the game
     * @return True if the piece is a weapon, and the specified point lies
     * within the small circle that indicates the orientation of the weapon when
     * it is drawn using the drawWeapon method. False otherwise.
     */
    public static boolean isInMuzzle(IPiece piece, double x, double y) {
        boolean result;
        if (piece instanceof IWeapon) {
            IWeapon weapon = (IWeapon) piece;
            double radius = weapon.getRadius();
            double orientation = weapon.getOrientation();
            double frac = 1 - 1f / MUZZLE_FRACTION;
            double cx = piece.getXPos() + frac * radius * Math.cos(orientation);
            double cy = piece.getYPos() + frac * radius * Math.sin(orientation);
            double dx = x - cx;
            double dy = y - cy;
            double dist = Math.sqrt(dx * dx + dy * dy);
            result = dist <= radius / MUZZLE_FRACTION;

        } else {
            result = false;
        }
        return result;
    }

    /**
     * Get the angle that a line makes with the x axis in the game space
     * 
     * @param x1 x coordinate of start of line
     * @param y1 y coordinate of start of line
     * @param x2 x coordinate of end of line
     * @param y2 y coordinate of end of line
     * @return The angle of the line, measured anticlockwise from the horizontal
     */
    public static double getAngle(double x1, double y1, double x2, double y2) {
        double dx = x2 - x1;
        double dy = y2 - y1;
        double hypotenuse = Math.sqrt(dx * dx + dy * dy);
        double angle;
        if (dy > 0) {
            angle = Math.acos(dx / hypotenuse);
        } else {
            angle = 2 * Math.PI - Math.acos(dx / hypotenuse);
        }
        return angle;
    }

    private static void drawPiece(IPiece piece, Graphics g, GameSpace gameSpace, double maxHealth) {
        Color oldColor = g.getColor();
        int centreX = gameSpace.convertToScreenX(piece.getXPos());
        int centreY = gameSpace.convertToScreenY(piece.getYPos());

        int displayRadius = gameSpace.convertToScreenDistance(piece.getRadius());

        int topX = centreX - displayRadius;
        int topY = centreY - displayRadius;

        if (piece.getOwner() == 0) {
            g.setColor(Color.CYAN);
        } else {
            g.setColor(Color.PINK);
        }
        if (piece.isSelected()){ // if the piece is selected change its colour
            g.setColor(Color.GREEN);
        }

        g.drawOval(topX, topY, 2 * displayRadius, 2 * displayRadius);

        int healthRadius
                = (int) Math.round(displayRadius * piece.getHealth() / maxHealth);
        int topHealthX = centreX - healthRadius;
        int topHealthY = centreY - healthRadius;
        g.fillOval(topHealthX, topHealthY, 2 * healthRadius, 2 * healthRadius);

        String name = piece.getName();
        if (name != null) {
            g.setColor(Color.BLACK);
            g.drawString(name, centreX-28, centreY);
        }
        g.setColor(oldColor);
    }
    
    public static void drawAim(IWeapon weapon, double angle, GameSpace gameSpace, Graphics g){ // draws the aiming cursor
        Color oldColor = g.getColor();
        g.setColor(Color.BLACK);
        
        int x1 = gameSpace.convertToScreenX(weapon.getXPos());
        int y1 = gameSpace.convertToScreenY(weapon.getYPos());

        int x2 = gameSpace.convertToScreenX(weapon.getXPos() // converts angle from mouse cursor and selected piece
                + weapon.getRadius() * Math.cos(angle));     // to x and y coordinates for a line pointing to the cursor
        int y2 = gameSpace.convertToScreenY(weapon.getYPos()
                + weapon.getRadius() * Math.sin(angle));

        g.setColor(Color.RED); // sets color red
        g.drawLine(x1, y1, x2, y2); // draws the line
        g.setColor(oldColor);
    }
    
    public static void drawImpacts(List<IImpact> impacts, GameSpace space, Graphics g){
        for(IImpact impact : impacts){ // for all the impacts in the list
            Color oldColor = g.getColor();
            int xImpact = (int) space.convertToScreenX(impact.getxImpact()); // converts the impact pos to Screen pos
            int yImpact = (int) space.convertToScreenY(impact.getyImpact());
            
            IProjectile origin = impact.getProjectile();
            int xOrigin = (int) space.convertToScreenX(origin.getOriginX()); // converst origin pos to screen po
            int yOrigin = (int) space.convertToScreenY(origin.getOriginY());
            
            g.setColor(Color.RED);
            g.fillOval(xImpact-3, yImpact-3, 6,6); //draws a red marker where the impact is
            
            g.setColor(Color.LIGHT_GRAY);
            g.drawLine(xImpact, yImpact, xOrigin, yOrigin); // draws a line between origin and impacts
            
            g.setColor(oldColor);
        }
    }
}