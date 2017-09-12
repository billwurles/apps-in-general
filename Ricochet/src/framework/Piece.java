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
public abstract class Piece implements IPiece{
    private final int owner;
    private final String name;
    private final double radius;
    private boolean selected = false;
    private double health;
    private double x;
    private double y;
    
    public Piece(int owner, String name, double radius, double health, double x, double y){
        this.owner = owner;
        this.name = name;
        this.radius = radius;
        this.health = health;
        this.x = x;
        this.y = y;
    }
           
    @Override
    public int getOwner() {
        return owner;
    }
    
    @Override
    public String getName() {
        return name;
    }
    
    @Override
    public double getRadius() {
        return radius;
    }

    @Override
    public double getHealth() {
        return health;
    }
    
    @Override
    public double getXPos() {
        return x;
    }

    @Override
    public double getYPos() {
        return y;
    }

    @Override
    public double getDamping() {
        return 1;
    }

    @Override
    public boolean isSelected() {
        return selected;
    }

    @Override
    public void setSelected(boolean selected) {
        this.selected = selected;
    }
    
    @Override
    public IProjectile onImpact(IImpact impact) {
        IProjectile projectile = impact.getProjectile();
        System.out.println("Impact! coords: "+impact.getxImpact()+" "+impact.getyImpact()+"  origin:"+projectile.getOriginX()+" "+projectile.getOriginY());
        IPiece piece = impact.getPiece();
        double angle = impact.getAngle();
        double impactDirection = projectile.getDirection();
        double xImpact = impact.getxImpact();
        double yImpact = impact.getyImpact();
        double direction = impactDirection + (2 * angle);
        double momentum = projectile.getMomentum();
        this.health = this.health - (momentum * Math.sin(angle));
        
        if(direction > (2* Math.PI)){
            direction = direction - (2*Math.PI);
        }
        if("shield".equals(piece.toString())){
            momentum = momentum * piece.getDamping();
        }
        
        IProjectile outProj = new Projectile(momentum, direction, xImpact, yImpact);
        return outProj;
                
    }

    @Override
    public void onSuccessfulMove(double x, double y) {
        this.x = x;
        this.y = y;
    }
    
    @Override
    public String toString(){
        return "piece";
    }
    
}
