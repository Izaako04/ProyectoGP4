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
        System.out.println("""
                           1. Registrar un nuevo vendedor
                           2. Registrar un nuevo veh\u00edculo
                           3. Aceptar oferta
                           4. Regresar""");
        Scanner sc= new Scanner(System.in);
        int opcion= sc.nextInt();
        
        if (opcion==1){
            Vendedor.nextVendedor(sc,"Vendedores.txt");
        }
        if (opcion==2){
            Vendedor.registrarVehiculo(sc, "Vendedores.txt");
        }
        if (opcion==3)
            System.out.println("falta aceptarOferta");
            //Vendedor.aceptarOferta(); 
    }
           
    
    public static void Comprador(){
        System.out.println("""
                           1. Registrar un nuevo comprador
                           2. Ofertar por Vehiculo
                           3. Regresar""");
        Scanner sc= new Scanner(System.in);
        int opcion= sc.nextInt();
        
//        if (opcion==1){
//            Vendedor.nextComprador(sc,"Compradores.txt");
//        }
//        if (opcion==2){
//            Vendedor.registrarVehiculo(sc, "Vendedor.txt");
//        }
//        if (opcion==3)
//            System.out.println("falta aceptarOferta");
//            //Vendedor.aceptarOferta(); 
    }
}
