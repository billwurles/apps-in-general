/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package framework;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.Point;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Dimension; // may not need this remove if not needed
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;



public class Main extends JPanel implements Runnable {
    Thread thread = null; // declaring variables
    GameEngine engine;
    GraphicsUtil utility;
    JFrame frame;
    Container content;
    Graphics g;
    JPanel gamePanel;
    GameSpace space;
    Dimension dimension;
    
    IPiece selectedPiece;
    List<IImpact> impacts;
    
    int turn = 1;
    Point targetP;
    Point targetConstant;
    double mouseAngle;
    
    public Main(){ // initialising the variables
        this.frame = new JFrame("Ricochet The Game");
        this.engine = new GameEngine(650);
        this.utility = new GraphicsUtil();
        
        
        this.gamePanel = new JPanel();
        this.dimension = new Dimension((int) ((engine.getRadius())+120), (int) ((engine.getRadius())+100));
        this.content = frame.getContentPane();
        this.g = content.getGraphics();
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        content.setLayout(new BorderLayout());
        frame.setBounds(450, 30, (int) engine.getRadius()+120, (int) engine.getRadius()+100);
        frame.setPreferredSize(dimension);
        
        this.space = new GameSpace(engine, frame);
    }
    
    @Override
    public void paint(Graphics g) { // the method that paints all of the objects
        g.setColor(Color.BLACK);
	g.drawOval(55, 10, 650, 650); // the background of the game
        g.drawLine(0, 335, 800, 335); // team separator
        for(IShield shield : engine.getShields()){
            GraphicsUtil.drawShield(shield, g, space, 100); // goes through each shield and paints them
        }
        for(IWeapon weapon : engine.getWeapons()){ // goes through each weapon and paints them
            GraphicsUtil.drawWeapon(weapon, g, space, 100);
        }
        if("weapon".equals(selectedPiece.toString())){ // if a weapon is selected, draw the aiming line on it
            GraphicsUtil.drawAim(engine.getWeapon(selectedPiece.getXPos(), selectedPiece.getYPos()), mouseAngle, space, g);
        }
        
        if(targetP!=null){ // if there is a point to paint it to, paint the target crosshair
            g.drawOval(targetP.x - 456, targetP.y - 97, 10, 10);
            g.drawLine(targetP.x - 451, targetP.y - 99, targetP.x - 451, targetP.y - 85);
            g.drawLine(targetP.x - 458, targetP.y - 92, targetP.x - 444, targetP.y - 92);
        }
        if(impacts!=null){ // if there are projectile impacts to paint, paint them
            GraphicsUtil.drawImpacts(impacts, space, g);
        }
    }
    public void createPieces(){ // initialising the pieces
        double x = -306;//initial x + y
        double y = 350;
        
        for (int weapon = 1; weapon < 4; weapon++){ // for each weapon in team 1
            System.out.println("creating weapon "+weapon+"for owner "+0);
            IWeapon newWeapon = new Weapon(0, "Weapon "+weapon, 50, 50, 100, x, y); // creates a new weapon
            newWeapon.setOrientation(-4.704424754304547); // changes orientation so they aren't aiming at each other
            engine.addWeapon(newWeapon); //adds weapon to the list
            x = x + 290;
        }
        x = -376;
        y = 175;
        for (int shield = 1; shield < 4; shield++){ // for each shield in team 1
            System.out.println("creating shield "+shield+"for owner "+0);
            IShield newShield = new Shield(0, "Shield "+shield, 80, 0.3, 100, x, y); // creates a new shield
            engine.addShield(newShield); // adds shield to the list
            x = x + 362;
        }
        
        x = -308;
        y = -346;
        for (int weapon = 1; weapon < 4; weapon++){ // for each weapon in team 2
            System.out.println("creating weapon "+weapon+"for owner "+1);
            IWeapon newWeapon = new Weapon(1, "Weapon "+weapon, 50, 50, 100, x, y); // creates a new weapon
            newWeapon.setOrientation(-1.5613564315751745); // changes orientation so they aren't aiming at each other
            engine.addWeapon(newWeapon);//adds the weapon to the list
            x = x + 290;
        }
        x = -376;
        y = -171;
        for (int shield = 1; shield < 4; shield++){ // for each shield in team 2
            System.out.println("creating shield "+shield+"for owner "+1);
            IShield newShield = new Shield(1, "Shield " + shield, 80, 0.3, 100, x, y); // creates a new shield
            engine.addShield(newShield); // adds the shield to the list
            x = x + 362;
        } 
    }

    public void start() { // starts the thread
        if (thread == null) {
            thread = new Thread(this);
            thread.start();
        }
    }
    @Override
    public void run() {
        while (thread != null) {
        try {
            Thread.sleep(1000); // sets the rate at which the game updates
        }   catch (InterruptedException e) {
        }
        repaint(); // paints the game
        }
        thread = null;
    }
    
    @Override
    public void update(Graphics g) {
        paint(g); // paints the game
    }
    
    public IPiece changeTurn(){
        turn++; // adds one to the turn counter
        selectedPiece.setSelected(false); // deselects the old piece
        for(IWeapon weapon : engine.getWeapons()){ //finds a weapon for the other team
            if(weapon.getOwner()!=selectedPiece.getOwner()){
                return weapon; // returns the weapon
            }
        }
        return null;
    }

    public static void main(String args[]) {
        Main main = new Main(); // initialises game
        
        JLabel turnLabel = new JLabel("Turn 1");
        JButton moveButton = new JButton("Move");
        JButton orientationButton = new JButton("Orientation");
        JButton selectButton = new JButton("Select");
        JButton fire = new JButton("Fire"); // creates ui buttons and labels
        JLabel text = new JLabel("Click on a piece and press select");
        
        fire.addActionListener(new ActionListener() { // responds if button is pressed
            public void actionPerformed(ActionEvent e) {
                System.out.println("Firing -------------------------------------------------------------------------------");
                main.turn++;
                turnLabel.setText("Turn "+main.turn); 
                if("weapon".equals(main.selectedPiece.toString())){ // if the piece is a weapon
                    main.impacts = main.engine.fireProjectile(main.engine.getWeapon(main.selectedPiece.getXPos(), main.selectedPiece.getYPos()));
                    text.setText("Click on a piece and press select");//^ fires projectile and gets the list of impacts
                    main.selectedPiece = main.changeTurn(); //changes the turn over and gets the new piece
                    turnLabel.setText("Turn "+main.turn); // updates turn label
                    main.selectedPiece.setSelected(true); // selects the new piece
                }
            }
        });
        orientationButton.addActionListener(new ActionListener() { // responds if button is pressed
            @Override
            public void actionPerformed(ActionEvent e){
                if("weapon".equals(main.selectedPiece.toString())){ // if piece is a weapon
                    System.out.println("Orientation -------------------------------------------------------------------------------");
                    double xInput = main.targetP.x - 456;
                    double yInput = main.targetP.y - 97;

                    xInput = main.space.convertToGameX((double) xInput); // converts from screen coords to game coords
                    yInput = main.space.convertToGameY((double) yInput);

                    yInput = yInput - 76;
                    IWeapon weapon = main.engine.getWeapon(main.selectedPiece.getXPos(), main.selectedPiece.getYPos()); // gets the weapon
                    double angle = Math.atan2(main.selectedPiece.getYPos() - yInput, main.selectedPiece.getXPos() - xInput) - Math.PI; // finds the angle
                    System.out.print("angle: "+angle+"   ");
                    System.out.println("vectors: "+main.selectedPiece.getXPos()+" "+main.selectedPiece.getYPos()+" "+xInput+" "+yInput);
                    weapon.setOrientation(angle); // sets weapon orientation
                    main.selectedPiece = main.changeTurn(); // changes the turn
                    turnLabel.setText("Turn "+main.turn); // updates turn label
                    main.selectedPiece.setSelected(true); // selects new piece
                    text.setText("Click on a piece and press select");
                }
            }
        });
        selectButton.addActionListener(new ActionListener() { // on select button
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Select -------------------------------------------------------------------------------");
                
                double x = main.space.convertToGameX(main.targetP.x - 456); // converts from screen pos to game pos
                double y = main.space.convertToGameY(main.targetP.y - 97);
                System.out.println("Select: "+x+" "+y);
                
                IPiece piece = main.engine.getPieceAtPos(x, y); // returns the piece
                if(piece != null){ // if there is a piece there
                    if("shield".equals(piece.toString())){
                        orientationButton.setEnabled(false); // if its a shield disable buttons
                        fire.setEnabled(false);
                    }
                    else{
                        orientationButton.setEnabled(true); // if it isn't reenable them
                        fire.setEnabled(true);
                    }
                    if(main.turn % 2 == 0 & piece.getOwner() == 1){ // if its team 2's turn and team 2's piece
                        main.selectedPiece.setSelected(false);  //deselect old piece
                        piece.setSelected(true); //select new piece
                        main.selectedPiece = piece; //assign new piece
                    }
                    else if(main.turn % 2 !=0 & piece.getOwner() == 0){ // if its team 1's turns and team 2's piece
                        main.selectedPiece.setSelected(false); //deselect old piece
                        piece.setSelected(true); //select new piece
                        main.selectedPiece = piece; //assign new piece
                    }
                    else{
                        orientationButton.setEnabled(true);//reenable buttons
                        fire.setEnabled(true);
                        text.setText("You must pick a piece from your own team"); //cannot pick from the wrong team
                        System.out.println("User attempted to pick a piece from the wrong side");
                    }
                }
            }
        });
        moveButton.addActionListener(new ActionListener() { // on move button
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Moving -------------------------------------------------------------------------------");
                
                double xInput = main.targetP.x - 456;
                double yInput = main.targetP.y - 97;
                double oldX = main.selectedPiece.getXPos();
                double oldY = main.selectedPiece.getYPos();
                double newX = main.space.convertToGameX((int) xInput); //convert from screen pos to game pos
                double newY = main.space.convertToGameY((int) yInput);
                
                System.out.println("game coords: "+newX+" "+newY);
                System.out.println("piece cords: "+main.selectedPiece.getXPos()+" "+main.selectedPiece.getYPos());
                if((main.selectedPiece.getXPos() - newX) > 250 | (main.selectedPiece.getXPos() - newX) < -250 | (main.selectedPiece.getYPos() - newY) > 250 | (main.selectedPiece.getYPos() - newY) < -250){
                    text.setText("You can only move 100 pixels at a time"); //^^^ You cannot move more than is allowed
                }
                else{   
                    main.engine.movePiece(main.selectedPiece, newX, newY - 76); // moves the piece
                    if(oldX != newX & oldY != newY-76){ // if piece has changed location
                        text.setText("Click on a piece and press select"); 
                        turnLabel.setText("Turn "+main.turn);// update turn label
                        main.selectedPiece = main.changeTurn(); // change turn
                        main.selectedPiece.setSelected(true); // select new piece
                    }
                }
            }
        });
        
        JPanel controlPanel = new JPanel(); //creates gui panel
        moveButton.setEnabled(false);
        fire.setEnabled(false);
        orientationButton.setEnabled(false); // disables buttons until button click
        controlPanel.add(turnLabel);
        controlPanel.add(moveButton);
        controlPanel.add(fire);
        controlPanel.add(orientationButton);
        controlPanel.add(selectButton); // adds all gui elements to controlPanel panel
        controlPanel.add(text);
        main.content.add(controlPanel, BorderLayout.BEFORE_FIRST_LINE); // sets layout
        
        main.createPieces(); // initialises the pieces
        main.selectedPiece = main.engine.getPiece(1); // selects first piece
        main.selectedPiece.setSelected(true); // sets piece selected
        main.content.add(main); //adds the swing graphics components
        main.content.addMouseListener(new MouseListener(){ // listens for mouse clicks

            @Override
            public void mouseClicked(MouseEvent ae) { // on mouse click
                if(main.targetP == null){ //if this is first run
                    moveButton.setEnabled(true); // enable buttons
                    fire.setEnabled(true);
                    orientationButton.setEnabled(true);
                }
                
                System.out.print("screen location: " + ae.getX() + " " + ae.getY()+"   ");
                main.targetP = ae.getLocationOnScreen(); // gets mouse location
                
                double xInput = main.targetP.x - 456;
                double yInput = main.targetP.y - 97;

                xInput = main.space.convertToGameX((double) xInput);
                yInput = main.space.convertToGameY((double) yInput); // converts from screen pos to game pos

                yInput = yInput - 76;
                main.mouseAngle = Math.atan2(main.selectedPiece.getYPos() - yInput, main.selectedPiece.getXPos() - xInput) - Math.PI; // finds angle for aiming cursor
                System.out.print("angle: "+main.mouseAngle+"   ");
                System.out.println("vectors: "+main.selectedPiece.getXPos()+" "+main.selectedPiece.getYPos()+" "+xInput+" "+yInput);
                
            }
            @Override
            public void mousePressed(MouseEvent ae) {}
            @Override
            public void mouseReleased(MouseEvent ae) {}
            @Override
            public void mouseEntered(MouseEvent ae) {}public void mouseExited(MouseEvent ae) {}
        });
        main.frame.setVisible(true); // sets the frame visible
        main.start(); // starts the program
    }
}