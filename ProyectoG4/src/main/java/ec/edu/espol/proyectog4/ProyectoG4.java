/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package ec.edu.espol.proyectog4;

import ec.edu.espol.util.Util;
import java.util.Scanner;

/**
 *
 * @author romiy
 */
public class ProyectoG4 {

    public static void main(String[] args) {
        System.out.println("""
                           Menú de Opciones:
                           1. Vendedor
                           2. Comprador
                           3. Salir""");
        Scanner sc= new Scanner(System.in);
        sc.useDelimiter("\n");
        Util.crearArchivo("Vendedores.txt");
        Util.crearArchivo("Vehiculos.txt");
        int opcion= Integer.parseInt(sc.nextLine());
        while(opcion != 3){
            if (opcion==1){
                Menu.Vendedor();
            }
            else if(opcion==2){
                Menu.Comprador();
            }                
            System.out.println("Terminado con éxito");
            }
        }
        
}    
