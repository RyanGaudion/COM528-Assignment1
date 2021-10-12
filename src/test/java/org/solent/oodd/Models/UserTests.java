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
        user.SetFirstName("Ryan");
        assertEquals("Ryan", user.GetFirstName());
    }
    
    @Test
    public void setLastNameTest()
    {
        User user = new User();
        user.SetLastName("Gaudion");
        assertEquals("Gaudion", user.GetLastName());
    }
    
    @Test
    public void getFullNameTest()
    {
        User user = new User();
        user.SetFirstName("Joe");
        user.SetLastName("Bloggs");
        assertEquals("Joe Bloggs", user.GetFullName());
    }
}
