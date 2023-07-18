/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espol.proyectog4;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Oferta {
    private double precio_oferta;
    private String correo;
    private Vehiculo vehiculo;
    public static ArrayList<Oferta> ofertasVehiculos=new ArrayList<>();

public Oferta(double precio_oferta, String correo, Vehiculo vehiculo) {
        this.precio_oferta = precio_oferta;
        this.correo = correo;
        this.vehiculo = vehiculo;
    }

    public double getPrecio_oferta() {
        return precio_oferta;
    }

    public void setPrecio_oferta(int precio_oferta) {
        this.precio_oferta = precio_oferta;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }
    
    
   @Override
   public String toString(){
       return vehiculo.getMarca()+vehiculo.getModelo()+this.precio_oferta;
   }
   
    public static ArrayList<Double> precioVeh(String placa){
        ArrayList<Double> precio= new ArrayList<>();
        for(Oferta o: Oferta.ofertasVehiculos ){
            if(o.getVehiculo().getPlaca().equals(placa) ){
               precio.add(o.getPrecio_oferta());
            }
        }
        return precio;
    }
    
    public void saveArchivoOferta(String nfile){
        try(PrintWriter pw = new PrintWriter(new FileOutputStream(new File(nfile),true))){
            pw.println(this.precio_oferta+"|"+this.correo+"|"+this.vehiculo.getId());
            
        } catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    
    public static ArrayList<Oferta> searchBycorreo(ArrayList<Oferta> ofertas, String correo){
        ArrayList<Oferta> ofertasComprador = new ArrayList<>();
        for(Oferta o: ofertas){
            if(o.getCorreo().equals(correo))
                ofertasComprador.add(o);
        }
        return ofertasComprador;
    }
    
    public static ArrayList<Oferta> readFileOfertas(String nfile){
        ArrayList<Oferta> ofertas = new ArrayList<>();
        try(Scanner sc = new Scanner(new File(nfile))){
            while(sc.hasNextLine()){
                String line = sc.nextLine();
                String[] tokens = line.split("\\|");
                ArrayList<Vehiculo> vehiculos = Vehiculo.readFileVehiculos("Vehiculos.txt");
                Vehiculo vehiculo = Vehiculo.searchByID(vehiculos,Integer.parseInt(tokens[2]));
                Oferta oferta = new Oferta(Double.parseDouble(tokens[0]),tokens[1],vehiculo);
                ofertas.add(oferta);
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return ofertas;
    }
    
    public static ArrayList<String> correoVeh(String placa){
        ArrayList<String> correo= new ArrayList<>();
        for(Oferta o: Oferta.ofertasVehiculos ){
            if(o.getVehiculo().getPlaca().equals(placa) ){
               correo.add(o.getCorreo());
            }
        }
        return correo;
    }
}
