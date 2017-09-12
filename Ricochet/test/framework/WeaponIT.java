/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package framework;

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
public class WeaponIT {
    
    public WeaponIT() {
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
     * Test of onFireProjectile method, of class Weapon.
     */
    @Test
    public void testOnFireProjectile() {
        System.out.println("onFireProjectile");
        IWeapon instance = new Weapon(123, "Gun", 10, 100, 100, 70, 30);
        IProjectile expResult = new Projectile(100.0, 0.0, 70.0, 30.0);
        IProjectile result = instance.onFireProjectile();
        assertEquals(expResult, result);
        System.out.println("onFireProjectile works correctly");
    }

    
}
