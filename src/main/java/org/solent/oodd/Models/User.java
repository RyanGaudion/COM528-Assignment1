/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.solent.oodd.Models;

/**
 *
 * @author rgaud
 */
public class User {
    private String firstName;
    private String lastName;
    
    public String GetFirstName(){
        return firstName;
    }
    public String GetLastName(){
        return lastName;
    }
    
    public String GetFullName(){
        return firstName.trim() + " " + lastName.trim();
    }
    
    public String SetFirstName(String firstname){
        this.firstName = firstname;
        return GetFirstName();
    }
    
    public String SetLastName(String lastname){
        this.lastName = lastname;
        return GetLastName();
    }
}
