/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import model.Usermodel;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author taesankim
 */
public class RegisterPageViewControllerTest {
    
    public RegisterPageViewControllerTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }

    /**
     * Test of searchUser method, of class RegisterPageViewController.
     */
    @Test
    public void testSearchUser() {
        System.out.println("searchUser");
        Usermodel user = new Usermodel(321, "firstname", "lastname", "email", "password");
        String expResult = "email";
        String result = user.getEmail();
        assertEquals(expResult, result);
        
    }
    
}
