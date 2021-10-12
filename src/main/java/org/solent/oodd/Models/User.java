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
    
    public User(){
        
    }
    public User(String fn, String ln){
        firstName = fn;
        lastName = ln;
    }
    
    public String GetFirstName(){
        return firstName;
    }
    public String GetLastName(){
        return lastName;
    }
    
    public String GetFullName(){
        if(firstName == null){
            return lastName.trim();
        }
        else if (lastName == null){
            return firstName.trim();
        }
        return firstName.trim() + " " + lastName.trim();
    }
    
    public Boolean SetFirstName(String firstname){
        if(firstname != null){
            firstName = firstname;
            return true;
        }
        return false;
    }
    
    public Boolean SetLastName(String lastname){
        if(lastname != null){
            lastName = lastname;
            return true;
        }
        return false;
    }
}
