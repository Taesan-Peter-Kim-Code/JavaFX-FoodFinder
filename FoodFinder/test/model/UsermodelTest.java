/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author taesankim
 */
public class UsermodelTest {
    
    public UsermodelTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }

    /**
     * Test of getPassword method, of class Usermodel.
     */
    @Test
    public void testGetPassword() {
        System.out.println("getPassword");
        Usermodel instance = new Usermodel(321, "firstname", "lastname", "email", "password");
        String expResult = "password";
        String result = instance.getPassword();
        assertEquals(expResult, result);  
    }

    /**
     * Test of setPassword method, of class Usermodel.
     */
    @Test
    public void testSetPassword() {
        System.out.println("setPassword");
        String password = "password";
        Usermodel instance = new Usermodel(321, "firstname", "lastname", "email", "password");
        instance.setPassword(password);
    }

    
}
