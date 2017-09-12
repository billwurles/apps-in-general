/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package framework;

import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Will
 */
public class GameEngineTest1 {
    
    public GameEngineTest1() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getRadius method, of class GameEngine.
     */
    @Test
    public void testGetRadius() {
        System.out.println("getRadius");
        GameEngine instance = new GameEngine(500);
        double expResult = 500;
        double result = instance.getRadius();
        assertEquals(expResult, result, 0.0);
        System.out.println("getRadius works correctly");
    }

    /**
     * Test of getShields method, of class GameEngine.
     */
    @Test
    public void testGetShields() {
        System.out.println("getShields");
        GameEngine instance = new GameEngine(500);
        IShield piece = new Shield(123, "Brick Wall", 10, 0.5, 100, 70, 30);
        instance.addShield(piece);
        List<IShield> expResult = new ArrayList<>();
        expResult.add(piece);
        List<IShield> result = instance.getShields();
        assertEquals(expResult, result);
        System.out.println("getShields works correctly");
    }

    /**
     * Test of getWeapons method, of class GameEngine.
     */
    @Test
    public void testGetWeapons() {
        System.out.println("getWeapons");
        GameEngine instance = new GameEngine(500);
        IWeapon piece = new Weapon(123, "Gun", 10, 100, 70, 30);
        instance.addWeapon(piece);
        List<IWeapon> expResult = new ArrayList<>();
        expResult.add(piece);
        List<IWeapon> result = instance.getWeapons();
        assertEquals(expResult, result);
        System.out.println("getWeapons works correctly");
    }

    /**
     * Test of addWeapon method, of class GameEngine.
     */
    @Test
    public void testAddWeapon() {
        System.out.println("addWeapon");
        IWeapon piece = new Weapon(123, "Gun", 10, 100, 70, 30);
        GameEngine instance = new GameEngine(500);
        int expResult = 0;
        int result = instance.addWeapon(piece);
        assertEquals(expResult, result);
        System.out.println("addWeapon works correctly");
    }

    /**
     * Test of addShield method, of class GameEngine.
     */
    @Test
    public void testAddShield() {
        System.out.println("addShield");
        IShield piece = new Shield(123, "Brick Wall", 10, 0.5, 100, 70, 30);
        GameEngine instance = new GameEngine(500);
        int expResult = 0;
        int result = instance.addShield(piece);
        assertEquals(expResult, result);
        System.out.println("addShield works correctly");
    }

    /**
     * Test of getWeapon method, of class GameEngine.
     */
    @Test
    public void testGetWeapon() {
        System.out.println("getWeapon");
        double x = 70;
        double y = 30;
        IWeapon piece = new Weapon(123, "Gun", 10, 100, 70, 30);
        GameEngine instance = new GameEngine(500);
        instance.addWeapon(piece);
        IWeapon expResult = piece;
        IWeapon result = instance.getWeapon(x, y);
        assertEquals(expResult, result);
        System.out.println("getWeapon works correctly");
    }

    /**
     * Test of getShield method, of class GameEngine.
     */
    @Test
    public void testGetShield() {
        System.out.println("getShield");
        double x = 70;
        double y = 30;
        GameEngine instance = new GameEngine(500);
        IShield piece = new Shield(123, "Brick Wall", 10, 0.5, 100, 70, 30);
        instance.addShield(piece);
        IShield expResult = piece;
        IShield result = instance.getShield(x, y);
        assertEquals(expResult, result);
        System.out.println("getShield works correctly");
    }

    /**
     * Test of fireProjectile method, of class GameEngine.
     */
    @Test
    public void testFireProjectile() {
        System.out.println("fireProjectile");
        IWeapon origin = new Weapon(123, "Gun", 10, 100, 70, 30);
        GameEngine instance = new GameEngine(500);
        instance.addWeapon(origin);
        List<IImpact> expResult = new ArrayList<>();
        
        
        
        List<IImpact> result = instance.fireProjectile(origin);
        assertEquals(expResult, result);
        System.out.println("fireProjectile works correctly");
    }

    /**
     * Test of movePiece method, of class GameEngine.
     */
    @Test
    public void testMovePiece() {
        System.out.println("movePiece");
        IWeapon piece = new Weapon(123, "Gun", 10, 100, 70, 30);
        double x = 40.0;
        double y = 50.0;
        GameEngine instance = new GameEngine(500);
        instance.addWeapon(piece);
        int expResult = 0;
        int result = instance.movePiece(piece, x, y);
        assertEquals(expResult, result);
        System.out.println("movePiece works correctly");
    }
    
}
