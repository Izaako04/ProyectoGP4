/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espol.proyectog4;
import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 *
 * @author USER
 */
public class Camioneta extends Vehiculo {
    private String traccion;

    public Camioneta(String traccion, int id, String placa, String marca, String modelo, String tipo_motor, int año, int recorrido, String color, String tipo_comb, int precio, Vendedor vendedor) {
        super(id, placa, marca, modelo, tipo_motor, año, recorrido, color, tipo_comb, precio,vendedor);
        this.traccion = traccion;
    }
    public static Camioneta ingresarCamioneta(Scanner sc, String nfilev,Vendedor vendedor) {
        Vehiculo vehiculo=ingresarVehiculo(sc, nfilev,vendedor);
        System.out.println("Ingrese la tracción:");
        String traccion = sc.nextLine();
        Camioneta camioneta = new Camioneta(traccion, vehiculo.getId(), vehiculo.getPlaca(), vehiculo.getMarca(), vehiculo.getModelo(), vehiculo.getTipo_motor(), vehiculo.getAño(), vehiculo.getRecorrido(), vehiculo.getColor(), vehiculo.getTipo_comb(), vehiculo.getPrecio(),vehiculo.getVendedor());
        return camioneta;
    }
    @Override
    public void saveArchivo(String nfile){
        try(PrintWriter pw = new PrintWriter(new FileOutputStream(new File(nfile),true))){
            pw.println(super.getId()+"|"+super.getVendedor().getId()+"|"+super.getPlaca()+"|"+super.getMarca()+"|"+super.getModelo()+"|"+super.getTipo_motor()+"|"+super.getAño()+"|"+super.getRecorrido()+"|"+this.getColor()+"|"+super.getTipo_comb()+"|"+super.getPrecio()+"|"+this.traccion);            
        } catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public String toString() {
        return super.toString() + "\n traccion=" + traccion;
    }
    
    
}
