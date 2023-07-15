/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espol.proyectog4;

import java.util.Scanner;

/**
 *
 * @author romiy
 */
public class Menu {
    public static void Vendedor(){
        System.out.println("1. Registrar un nuevo vendedor\n" +
        "2. Registrar un nuevo vehículo\n" +
        "3. Aceptar oferta\n" +
        "4. Regresar");
        Scanner sc= new Scanner(System.in);
        int opcion= sc.nextInt();
        
        if (opcion==1){
            Vendedor.nextVendedor(sc,"Vendedores");
        }
        if (opcion==2){
            Vendedor.registrarVehiculo(sc, "Vendedor.txt");
        }
        if (opcion==3)
            System.out.println("falta aceptarOferta");
            //Vendedor.aceptarOferta(); 
    }
           
    
    public static void Comprador(){
    
    }
}
