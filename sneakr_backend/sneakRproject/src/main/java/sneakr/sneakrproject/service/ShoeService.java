/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sneakr.sneakrproject.service;

import java.util.ArrayList;
import sneakr.sneakrproject.model.Cipok;

/**
 *
 * @author Krisztián
 */
public class ShoeService {
    
    private Cipok layer = new Cipok();
    
     public ArrayList<Cipok> getAllShoes() {
    ArrayList<Cipok> shoesList = new ArrayList<>(); 
    try {
        shoesList = layer.getAllShoes();     
        
    } catch (Exception e) {
        System.err.println("Error fetching shoes: " + e.getMessage());
    }

    return shoesList;
}
     
     public ArrayList<Cipok> getShoesNamePrice() {
    ArrayList<Cipok> shoesList = new ArrayList<>(); 
    try {
        shoesList = layer.getShoesNamePrice();     
        
    } catch (Exception e) {
        System.err.println("Error fetching shoes: " + e.getMessage());
    }

    return shoesList;
}
    public ArrayList<Cipok> getShoesByAir() {
    ArrayList<Cipok> shoesList = new ArrayList<>(); 
    try {
        shoesList = layer.getShoesByAir();     
        
    } catch (Exception e) {
        System.err.println("Error fetching shoes: " + e.getMessage());
    }

    return shoesList;
}
     
    
}
