/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espol.proyectog4;

import java.util.ArrayList;
import java.util.Scanner;


public class Oferta {
    private int precio_oferta;
    private String correo;
    private Vehiculo vehiculo;
    
    
   @Override
   public String toString(){
       return vehiculo.getMarca()+vehiculo.getModelo()+this.precio_oferta;
   }
   
}
