package framework;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Class representing the game engine
 * @author davidsutton
 */
public class GameEngine {

    public static final int SUCCESS = 0;
    public static final int INVALID_POSITION = -1;
    private final double radius;

    private final List<IPiece> pieces = new ArrayList<>();
    private final List<IWeapon> weaponPieces = new ArrayList<>();
    private final List<IShield> shieldPieces = new ArrayList<>();

    /**
     * Constructor for game engine.
     *
     * The radius of the playing area (in game co-ordinates)
     *
     * @param radius
     */
    public GameEngine(double radius) {
        this.radius = radius;
    }

    /**
     * Return the radius of the playing area (in game co-ordinates)
     *
     * @return
     */
    public double getRadius() {
        return radius;
    }

    /**
     * Get a list containing all the shields in the game
     *
     * @return An unmodifiable list of all the shields in the game
     */
    public List<IShield> getShields() {
        return Collections.unmodifiableList(shieldPieces);
    }

    /**
     * Get a list containing all the weapons in the game
     *
     * @return An unmodifiable list of all the shields in the game
     */
    public List<IWeapon> getWeapons() {
        return Collections.unmodifiableList(weaponPieces);
    }

    /**
     * Request that a weapon be added to the game
     *
     * @param piece a IWeapon object to be added.
     * @return If it was possible to add the weapon then Game.SUCCESS is
     * returned If it was not possible (because the weapon would have been
     * outside the playing area, or would have overlapped with another
     * piece)then any value other than Game.SUCCESS may be returned,
     */
    public int addWeapon(IWeapon piece) {
        int result = positionOK(piece, piece.getXPos(), piece.getYPos());
        if (result == SUCCESS) {
            weaponPieces.add(piece);
            pieces.add(piece);
        }
        return result;
    }

    /**
     * Request that a shield be added to the game
     *
     * @param piece a IShield object to be added
     * @return If it was possible to add the shield then Game.SUCCESS is
     * returned If it was not possible (because the weapon would have been
     * outside the playing area, or would have overlapped with another piece)
     * then any value other than Game.SUCCESS may be returned
     */
    public int addShield(IShield piece) {
        int result = positionOK(piece, piece.getXPos(), piece.getYPos());
        if (result == SUCCESS) {
            shieldPieces.add(piece);
            pieces.add(piece);
        }
        return result;
    }

    /**
     * Get the weapon (if any) at a particular position
     *
     * @param x x value, in game coordinates.
     * @param y y value, in game coordinates
     * @return If the specified x,y position lies within a weapon then a
     * reference to that weapon is returned, otherwise the return value is null
     */
    public IWeapon getWeapon(double x, double y) {
        for (IWeapon weapon : weaponPieces) {
            if (contains(weapon, x, y)) {
                return weapon;
            }
        }
        return null;
    }

    /**
     * Get the shield (if any) at a particular position
     *
     * @param x x value, in game coordinates.
     * @param y y value, in game coordinates
     * @return If the specified x,y position lies within a weapon then a
     * reference to that weapon is returned, otherwise the return value is null
     */
    public IShield getShield(double x, double y) {
        for (IShield shield : shieldPieces) {
            if (contains(shield, x, y)) {
                return shield;
            }
        }
        return null;
    }

    /**
     * Fire a projectile from a weapon
     *
     * @param origin The weapon that should fire the projectile
     * @return A List of IImpact objects representing the trajectory of the
 projectile . The last object in this list represents the projectile's
 exit from the playing area. The other objects represent impacts with
 other pieces.
     */
    public List<IImpact> fireProjectile(IWeapon origin) {
        ArrayList<IImpact> result = new ArrayList<>();
        IPiece piece = origin;
        IImpact imp;
        IProjectile projectile = origin.onFireProjectile();
        do {
            imp = findFirstImpact(projectile, piece);
            //printImpact(imp);
            if (imp != null) {
                piece = imp.getPiece();
                projectile = piece.onImpact(imp);
                result.add(imp);
                System.out.println(imp.getxImpact()+" "+imp.getyImpact());
            } else {
                result.add(getExitPoint(projectile));
            }
            if (piece.getHealth() <= 0) {
                removePiece(piece);
            }
        } while (imp != null);

        return result;
    }

    /** 
     * Request that a piece be moved
     * @param piece the piece to be moved.
     * @param x x coordinate of position to which piece should be moved, in game coordinates
     * @param y x coordinate of position to which piece should be moved, in game coordinates
     * @return If it was possible to move the piece to the specified position then Game.SUCCESS is
     * returned If it was not possible (because the weapon would have been
     * outside the playing area, or would have overlapped with another piece)
     * then any value other than Game.SUCCESS may be returned
     */
    public int movePiece(IPiece piece, double x, double y) {
        int positionOK = positionOK(piece, x, y);
        if (positionOK == SUCCESS) {
            piece.onSuccessfulMove(x, y);
        }

        return positionOK;
    }

    private IImpact findFirstImpact(IProjectile projectile, IPiece firingPiece) {
        IPiece piece = null;
        double distance = Double.MAX_VALUE;//has to be initialised to something!
        for (IPiece testPiece : pieces) {
            if (testPiece != firingPiece) {
                double testDistance = getDistanceToPiece(projectile, testPiece);
                if (testDistance > 0) {
                    if (piece == null || testDistance < distance) {
                        piece = testPiece;
                        distance = testDistance;
                    }
                }
            }
        }
        ImpactImpl result;
        if (piece != null) {
            result = new ImpactImpl();
            result.piece = piece;
            result.projectile = projectile;
            result.xImpact = projectile.getOriginX()
                    + distance * Math.cos(projectile.getDirection());
            result.yImpact = projectile.getOriginY()
                    + distance * Math.sin(projectile.getDirection());

            double rx = result.xImpact - piece.getXPos();
            double ry = result.yImpact - piece.getYPos();
            double dx = result.xImpact - projectile.getOriginX();
            double dy = result.yImpact - projectile.getOriginY();
            double dotproduct = rx * dx + ry * dy;
            double crossproduct = rx * dy - ry * dx;
            if (crossproduct < 0) {
                result.angle = Math.asin(-dotproduct / (distance * piece.getRadius()));
            } else {
                result.angle = Math.PI - Math.asin(-dotproduct / (distance * piece.getRadius()));

            }

        } else {
            result = null;
        }
        return result;
    }

    private static double getDistanceToPiece(IProjectile projectile, IPiece piece) {
        double deltaX = piece.getXPos() - projectile.getOriginX();
        double deltaY = piece.getYPos() - projectile.getOriginY();
        //length of line connecting the projectile
        //origin to the centre of the piece
        double d = Math.sqrt(deltaX * deltaX + deltaY * deltaY);

        //angle between horizontal and line connecting the projectile
        //origin to the centre of the piece
        double theta;
        if (deltaY > 0) {
            theta = Math.acos(deltaX / d);
        } else {
            theta = 2 * Math.PI - Math.acos(deltaX / d);
        }

        //coefficients of quadratic equation for l, where l is the distance
        //from the projectile origin to the point (if any) at which it hits the
        //piece
        //double a = 1;
        double b = -2 * d * Math.cos(projectile.getDirection() - theta);
        double c = d * d - piece.getRadius() * piece.getRadius();
        double discriminant = b * b - 4 * c;

        //if c<=0 then projectile is inside piece, that should not happen
        //if the discriminant is negative, then the equation has no real
        //solution
        double result;
        if (c > 0 && discriminant >= 0) {
            //if we have a positive discriminant then the distance is the 
            //lower of the two real solutions
            result = (-b - Math.sqrt(discriminant)) / 2;
        } else {
            result = -1;
        }
        return result;
    }

    public int positionOK(IPiece piece, double x, double y) {
        double distFromCentre = Math.sqrt(x * x + y * y);
        if (distFromCentre > radius - piece.getRadius()) {
            return INVALID_POSITION;
        }
        for (IPiece otherPiece : pieces) {
            if (otherPiece != piece) {
                double dx = x - otherPiece.getXPos();
                double dy = y - otherPiece.getYPos();
                double dist = Math.sqrt(dx * dx + dy * dy);
                if (dist < piece.getRadius() + otherPiece.getRadius()) {
                    return INVALID_POSITION;
                }
            }
        }
        System.out.println(SUCCESS);
        return SUCCESS;
    }
    public IPiece getPieceAtPos(double x, double y){
        for (IPiece piece : pieces){
            double dx = x - piece.getXPos();
            double dy = y - piece.getYPos();
            double dist = Math.sqrt(dx * dx + dy * dy);
            if (dist < 30 + piece.getRadius()) {
                return piece;
            }
        }
        System.out.println("nullify");
        return null;
    }

    private void printImpact(IImpact impact) {
        if (impact != null) {
            System.out.println(impact);
        } else {
            System.out.println("Null impact");
        }
    }

    private IImpact getExitPoint(IProjectile projectile) {
        double x = projectile.getOriginX();
        double y = projectile.getOriginY();
        double theta = projectile.getDirection();
        double rg = this.radius;
        //get coefficients of quadratic
        //double a = 1;
        double b = 2 * (x * Math.cos(theta) + y * Math.sin(theta));
        double c = x * x + y * y - rg * rg;

        //now calculate distance to edge of game area
        double distance = (-b + Math.sqrt(b * b - 4 * c)) / 2;

        ImpactImpl result = new ImpactImpl();
        result.piece = null;
        result.projectile = projectile;
        result.xImpact = x + distance * Math.cos(theta);
        result.yImpact = y + distance * Math.sin(theta);

        return result;
    }

    private boolean contains(IPiece piece, double x, double y) {
        double dx = x - piece.getXPos();
        double dy = y - piece.getYPos();
        double dist = Math.sqrt(dx * dx + dy * dy);
        return (dist <= piece.getRadius());
    }

    private void removePiece(IPiece piece) {
        pieces.remove(piece);
        weaponPieces.remove(piece);
        shieldPieces.remove(piece);
    }
    
    public IPiece getPiece(int index){
        return pieces.get(index);
    }

    private class ImpactImpl implements IImpact {

        @Override
        public String toString() {
            return "ImpactImpl{" + "piece=" + piece + ", projectile=" + projectile + ", xImpact=" + xImpact + ", yImpact=" + yImpact + ", angle=" + angle + '}';
        }

        private IPiece piece;
        private IProjectile projectile;
        private double xImpact;
        private double yImpact;
        private double angle;

        @Override
        public IPiece getPiece() {
            return piece;
        }

        @Override
        public IProjectile getProjectile() {
            return projectile;
        }

        @Override
        public double getxImpact() {
            return xImpact;
        }

        @Override
        public double getyImpact() {
            return yImpact;
        }

        @Override
        public double getAngle() {
            return angle;
        }
    }
}
