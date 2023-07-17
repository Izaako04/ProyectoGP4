/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espol.proyectog4;

import java.util.ArrayList;

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
