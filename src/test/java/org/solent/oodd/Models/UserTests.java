/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.solent.oodd.Models;

import static org.junit.Assert.*;
import org.junit.Test;

/**
 *
 * @author rgaud
 */
public class UserTests {
    
    @Test
    public void setFirstNameTest()
    {
        User user = new User();
        assertEquals(true, user.SetFirstName("Ryan"));
        assertEquals("Ryan", user.GetFirstName());
    }
    
    @Test
    public void setLastNameTest()
    {
        User user = new User();
        assertEquals(true, user.SetLastName("Gaudion"));
        assertEquals("Gaudion", user.GetLastName());
    }
    
    @Test
    public void getFullNameTest()
    {
        User user = new User();
        assertEquals(true, user.SetFirstName("Joe"));
        assertEquals(true, user.SetLastName("Bloggs"));
        assertEquals("Joe Bloggs", user.GetFullName());
    }
        
    @Test
    public void constructorTest()
    {
        User user = new User("Joe", "Bloggs");
        assertEquals("Joe", user.GetFirstName());
        assertEquals("Bloggs", user.GetLastName());
        assertEquals("Joe Bloggs", user.GetFullName());
    }
}
