/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espol.proyectog4;

import java.util.ArrayList;
import java.util.Scanner;


public class Oferta {
    private int precio_oferta;
    private Vehiculo vehiculo;
    private Vendedor vendedor;
    private Comprador comprador;
    
    
   @Override
   public String toString(){
       return vehiculo.getMarca()+" "+vehiculo.getModelo()+" Precio:"+vehiculo.getPrecio()+"\n"+this.vendedor.getCorreo_electronico()+"\n"+this.precio_oferta;
   }
   
}
