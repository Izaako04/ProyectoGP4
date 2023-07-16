/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espol.proyectog4;

import static ec.edu.espol.util.Util.nextID;
import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author romiy
 */
public class Vehiculo {
    private int id;
    private String placa; 
    private String marca; 
    private String modelo; 
    private String tipo_motor; 
    private int año; 
    private int recorrido; 
    private String color; 
    private String tipo_comb; 
    private int precio;
    private ArrayList<Registro> registros;

    public Vehiculo(int id, String placa, String marca, String modelo, String tipo_motor, int año, int recorrido, String color, String tipo_comb, int precio) {
        this.id = id;
        this.placa = placa;
        this.marca = marca;
        this.modelo = modelo;
        this.tipo_motor = tipo_motor;
        this.año = año;
        this.recorrido = recorrido;
        this.color = color;
        this.tipo_comb = tipo_comb;
        this.precio = precio;
        this.registros = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
 
    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getTipo_motor() {
        return tipo_motor;
    }

    public void setTipo_motor(String tipo_motor) {
        this.tipo_motor = tipo_motor;
    }

    public int getAño() {
        return año;
    }

    public void setAño(int año) {
        this.año = año;
    }

    public int getRecorrido() {
        return recorrido;
    }

    public void setRecorrido(int recorrido) {
        this.recorrido = recorrido;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getTipo_comb() {
        return tipo_comb;
    }

    public void setTipo_comb(String tipo_comb) {
        this.tipo_comb = tipo_comb;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public ArrayList<Registro> getRegistros() {
        return registros;
    }

    public void setRegistros(ArrayList<Registro> registros) {
        this.registros = registros;
    }

    public void saveArchivo(String nfile){
        try(PrintWriter pw = new PrintWriter(new FileOutputStream(new File(nfile),true))){
            pw.println(this.id+"|"+this.placa+"|"+this.marca+"|"+this.modelo+"|"+this.tipo_motor+"|"+this.tipo_comb+"|"+this.año+"|"+this.recorrido+"|"+this.recorrido+"|"+this.color+"|"+this.tipo_comb+"|"+this.precio);
        } catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    
    public static Vehiculo searchByID(ArrayList<Vehiculo> vehiculos, int id){
        for(Vehiculo x: vehiculos){
            if(x.id == id)
                return x;
        }
        return null;
    }
    
    public static ArrayList<String> readFilePlacas(String nfilev){
        ArrayList<String> placas = new ArrayList<>();
        try(Scanner sc = new Scanner(new File(nfilev))){
            while(sc.hasNextLine()){
                String line = sc.nextLine();
                String[] tokens = line.split("\\|");
                String placau = tokens[1];
                placas.add(placau);
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return placas;
    }
    
    public static boolean verificarplaca(ArrayList<String> placas, String placa){
        for(String x: placas){
            if(x.equals(placa))
                return true;
        }
        return false;        
    }

    @Override
    public String toString() {
        return "id= " + id + "\n placa=" + placa + "\n marca=" + marca + "\n modelo=" + modelo + "\n tipo_motor=" + tipo_motor + "\n año=" + año + "\n recorrido=" + recorrido + "\n color=" + color + "\n tipo_comb=" + tipo_comb + "\n precio=" + precio + "\n registros=" + registros;
    }
    
    public static Vehiculo ingresarVehiculo(Scanner sc, String nfilev){
        ArrayList<String> placas = readFilePlacas(nfilev);
        int idv = nextID(nfilev);
        System.out.println("Ingrese placa:");
        String placa = sc.nextLine();
        while(verificarplaca(placas,placa)== true){
            System.out.println("Placa ya registrada, registre otra placa");
            placa = sc.nextLine();
        }            
        System.out.println("Ingrese marca");
        String marca= sc.nextLine();
        System.out.println("Ingrese modelo");
        String modelo= sc.nextLine();
        System.out.println("Ingrese tipo de motor");
        String tmotor= sc.nextLine();
        System.out.println("Ingrese año:");
        int año = Integer.parseInt(sc.nextLine());
        System.out.println("Ingrese recorrido");
        int recorrido= Integer.parseInt(sc.nextLine());
        System.out.println("Ingrese color:");
        String color = sc.nextLine();
        System.out.println("Ingrese tipo de combustible");
        String tcombustible= sc.nextLine();
        System.out.println("Ingrese precio:");
        int precio = Integer.parseInt(sc.nextLine());
        
       Vehiculo v= new Vehiculo(idv,placa,marca,modelo,tmotor,año,recorrido,color,tcombustible,precio);
       return v;
    } 
}    
