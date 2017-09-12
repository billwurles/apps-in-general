package framework;

import framework.GameEngine;
import javax.swing.JComponent;
import javax.swing.JFrame;

/**
 *
 * @author p0073862
 *
 * Handles conversions between game co-ordinates and screen (pixel) co-ordinates on a
 * specified JComponent (e.g. a JPanel). The space within the game radius is
 * mapped on to the biggest circle that can be displayed within the component
 */
public class GameSpace {

    private final GameEngine gameEngine;
    private final JFrame component;

    /**
     * Create a GameSpace object that maps the space within the game radius on
     * to the biggest circle that can be displayed within a specified component
     *
     * @param gameEngine The GameEngine representing the game
     * @param component A component on to which the state of the game is to be
     * drawn (for example a JPanel)
     */
    public GameSpace(GameEngine gameEngine, JFrame component) {
        this.gameEngine = gameEngine;
        this.component = component;
    }

    /**
     * Convert an x coordinate on the component to the corresponding 
     * x coordinate in the game
     * @param screenX x coordinate on component 
     * @return corresponding x coordinate in game
     */
    public double convertToGameX(double screenX) {
        double screenRadius = getScreenRadius();
        double dx = screenX - screenRadius;
        double result = dx * gameEngine.getRadius() / screenRadius;
        return result;
    }
    /**
     * Convert a y coordinate on the component to the corresponding 
     * y coordinate in the game
     * @param screenY y coordinate on component (expressed as number of pixels
     * from top of component)
     * @return corresponding y coordinate in game
     */
    public double convertToGameY(double screenY) {
        double screenRadius = getScreenRadius();
        double dy = screenRadius - screenY;
        double result = dy * gameEngine.getRadius() / screenRadius;
        return result;
    }

    

    /**
     * Convert an x coordinate in the game to the corresponding 
     * x coordinate on the component
     * @param gameX x coordinate in the game
     * @return corresponding x coordinate on the component (expressed as number of 
     * pixels from left margin of component)
     */
    public int convertToScreenX(double gameX) {
        gameX = gameX-85;
        int screenRadius = getScreenRadius();
        double gameRadius = gameEngine.getRadius();
        return (int) Math.round(screenRadius + gameX * screenRadius / gameRadius)+55;
        
    }

    /**
     * Convert a y coordinate in the game to the corresponding 
     * y coordinate on the component
     * @param gameY y coordinate in game
     * @return corresponding y coordinate on component (expressed as number of pixels
     * from top of component)
     */
    public int convertToScreenY(double gameY) {
        gameY = gameY+85;
        int screenRadius = getScreenRadius();
        double gameRadius = gameEngine.getRadius();
        return (int) Math.round(screenRadius - gameY * screenRadius / gameRadius)+10;
    }

    /**
     * Convert a distance in the game to a distance on the screen
     * @param gameDistance distance in the game
     * @return corresponding distance on the screen (in pixels)
     */
    public int convertToScreenDistance(double gameDistance) {
        int screenRadius = getScreenRadius();
        double gameRadius = gameEngine.getRadius();
        return (int) Math.floor(gameDistance * screenRadius / gameRadius);
    }

    /**
     * Get the radius (in pixels) of the largest circle that will fit on the 
     * screen
     * @return radius of largest circle that will fit onto the component
     * (in pixels)
     */
    public int getScreenRadius() {
//        int diameter = (int) Math.min(dimension.getWidth(), dimension.getHeight());
        int diameter = Math.min(component.getWidth(), component.getWidth());
        return diameter / 2;
    }
}