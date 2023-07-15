/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package ec.edu.espol.proyectog4;

import java.util.Scanner;

/**
 *
 * @author romiy
 */
public class ProyectoG4 {

    public static void main(String[] args) {
        System.out.println("\"Menú de Opciones:\n" +
        "1.	Vendedor\n" +
        "2.	Comprador\n" +
        "3.	Salir)");
        
        Scanner sc= new Scanner(System.in);
        int opcion= sc.nextInt();
        if (opcion==1){
            Menu.Vendedor();
        }
        
        if (opcion==2){
            Menu.Comprador(); 
        }
        
    }
}    
