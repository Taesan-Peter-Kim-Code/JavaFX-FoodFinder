/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import model.Event;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author taesankim
 */
public class NewEventControllerTest {
    
    public NewEventControllerTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }

    /**
     * Test of createEntry method, of class NewEventController.
     */
    @Test
    public void testCreateEntry() {
        System.out.println("createEntry");
        Event newEvent = new Event(1, "eventname", "organization", "date", "time", "location", "description");
        Integer expResult = 1;
        Integer result = newEvent.getId();
        assertEquals(expResult, result);
    }
    
}
