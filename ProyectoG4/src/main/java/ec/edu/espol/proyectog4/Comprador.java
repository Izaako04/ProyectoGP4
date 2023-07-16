/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espol.proyectog4;

import static ec.edu.espol.util.Util.getSHA;
import static ec.edu.espol.util.Util.nextID;
import static ec.edu.espol.util.Util.toHexString;
import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Scanner;


public class Comprador {
    private int id;
    private String nombres;
    private String apellidos;
    private String organizacion;
    private String correo_electronico;
    private String clave;

    public Comprador(int id, String nombres, String apellidos, String organizacion, String correo_electronico, String clave) {
        this.id = id;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.organizacion = organizacion;
        this.correo_electronico = correo_electronico;
        this.clave = clave;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getOrganizacion() {
        return organizacion;
    }

    public void setOrganizacion(String organizacion) {
        this.organizacion = organizacion;
    }

    public String getCorreo_electronico() {
        return correo_electronico;
    }

    public void setCorreo_electronico(String correo_electronico) {
        this.correo_electronico = correo_electronico;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }
    public void saveArchivo(String nfile){
        try(PrintWriter pw = new PrintWriter(new FileOutputStream(new File(nfile),true))){
            pw.println(this.nombres+"|"+this.apellidos+"|"+this.organizacion+"|"+this.correo_electronico+"|"+this.clave);
            
        } catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    
    public static ArrayList<String> readFileCorreos(String nfile){
        ArrayList<String> correos = new ArrayList<>();
        try(Scanner sc = new Scanner(new File(nfile))){
            while(sc.hasNextLine()){
                String line = sc.nextLine();
                String[] tokens = line.split("\\|");
                String correo_elec = tokens[3];
                correos.add(correo_elec);
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return correos;
    }
       
    public static void nextComprador(Scanner sc,String nfile){
        int id_comprador = nextID(nfile);
        System.out.println("Ingrese nombres");
        String n = sc.next();
        System.out.println("Ingrese apellidos");
        String ape = sc.next();
        System.out.println("Ingrese organizacion");
        String org = sc.next();
        System.out.println("Ingrese clave");
        String cv = sc.next();
        String password;
        try{
            password = toHexString(getSHA(cv));
            System.out.println("Ingrese correo");
            String correo = sc.next();
            String correof;
            ArrayList<String> correos_dados = readFileCorreos(nfile);
            for (String c : correos_dados) {
                if (c.equals(correo)) {
                    System.out.println("Correo ya registrado");
                } else {
                    correof = correo;
                    Comprador comp = new Comprador(id_comprador,n,ape,org,password,correof);
                    comp.saveArchivo(nfile);
                }
            }
        }catch (NoSuchAlgorithmException e) {
            System.out.println("Exception thrown for incorrect algorithm: " + e.getMessage());
        }        
    }
    
    public ArrayList<Vehiculo> identificarTipo(String nfile, String tipo){
        ArrayList<Vehiculo> listaMotos = new ArrayList <>();
        ArrayList<Vehiculo> listaCamionetas = new ArrayList <>();
        ArrayList<Vehiculo> listaAutos = new ArrayList <>();
        ArrayList<Vehiculo> listaTotal = new ArrayList <>();
        try(Scanner sc = new Scanner(new File(nfile))){
            while(sc.hasNextLine()){
                String line = sc.nextLine();
                String[] tokens = line.split("\\|");
                if(tokens.length==10){
                    Vehiculo moto= new Vehiculo(Integer.parseInt(tokens[0]),tokens[1],tokens[2],tokens[3],tokens[4],Integer.parseInt(tokens[5]),Integer.parseInt(tokens[6]),tokens[7],tokens[8],Integer.parseInt(tokens[9]));
                    listaMotos.add(moto);
                    listaTotal.add(moto);
                }
                if(tokens.length==11){
                    Vehiculo camioneta= new Camioneta(tokens[10],Integer.parseInt(tokens[0]),tokens[1],tokens[2],tokens[3],tokens[4],Integer.parseInt(tokens[5]),Integer.parseInt(tokens[6]),tokens[7],tokens[8],Integer.parseInt(tokens[9]));
                    listaCamionetas.add(camioneta); 
                    listaTotal.add(camioneta);
                }
                if(tokens.length==12){
                    Vehiculo auto= new Auto(Integer.parseInt(tokens[0]),tokens[1],tokens[2],tokens[3],tokens[4],Integer.parseInt(tokens[5]),Integer.parseInt(tokens[6]),tokens[7],tokens[8],Integer.parseInt(tokens[9]),tokens[10],tokens[11]);
                    listaAutos.add(auto); 
                    listaTotal.add(auto);
                }
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        if(tipo=="moto")
            return listaMotos;
        if(tipo=="camioneta")
            return listaCamionetas;
        if(tipo=="auto")
            return listaAutos;
        else
            return listaTotal;
    }
    
    public ArrayList<Vehiculo> identificarRango(String nfile,String tipo,int recMin,int recMax,int anioMin,int anioMax,int preMin,int preMax){
        ArrayList<Vehiculo> vehiculos=identificarTipo(nfile, tipo);
        ArrayList<Vehiculo> listaF= new ArrayList<>();
        for(Vehiculo v: vehiculos){
            if(v.getRecorrido()>=recMin && v.getRecorrido()<=recMax && v.getAño()>=anioMin && v.getAño()<=anioMax && v.getPrecio()>=preMin && v.getPrecio()<=preMax);
                listaF.add(v);
        }
        return listaF;
    }
    
    
    public void ofertarVehiculo(Scanner sc, String nfile){
        System.out.println("¿Desea ingresar tipo de vehiculo? si/no :");
        String resp1=sc.nextLine();
        int tipo=0;
        if (resp1.toLowerCase().equals("si")){
            System.out.println("Ingrese el tipo de vehiculo por sú numero: \n 1.Moto \n 2.Auto \n 3.Camioneta " );
            tipo+=sc.nextInt();
        }
        else{
            tipo+=0;
        }
        String tipoS="";
        if(tipo==0){
            tipoS+=" ";
        if(tipo==1){
            tipoS+="moto";
        if(tipo==2){
            tipoS+="auto";
        if(tipo==3){
            tipoS+="camioneta";
        
        
        System.out.println("¿Desea ingresar el recorrido mínimo del vehiculo? si/no :");
        String resp2=sc.nextLine();
        int recorridoMin=0;
        if (resp2.toLowerCase().equals("si")){
            System.out.println("Ingrese el valor del recorrido mínimo del vehiculo: " );
            recorridoMin+=sc.nextInt();
        }
        else{
            recorridoMin+=0;
        }    
        
        System.out.println("¿Desea ingresar el recorrido máximo del vehiculo? si/no :");
        String resp3=sc.nextLine();
        int recorridoMax=0;
        if (resp3.toLowerCase().equals("si")){
            System.out.println("Ingrese el valor del recorrido máximo del vehiculo: " );
            recorridoMax+=sc.nextInt();
        }
        else{
            recorridoMax+=Integer.MAX_VALUE;
        }
        
        System.out.println("¿Desea ingresar el año mínimo del vehiculo? si/no :");
        String resp4=sc.nextLine();
        int anioMin=0;
        if (resp4.toLowerCase().equals("si")){
            System.out.println("Ingrese el año mínimo del vehiculo: " );
            anioMin+=sc.nextInt();
        }
        else{
            anioMin+=0;
        }
        
        System.out.println("¿Desea ingresar el año máximo del vehiculo? si/no :");
        String resp5=sc.nextLine();
        int anioMax=0;
        if (resp5.toLowerCase().equals("si")){
            System.out.println("Ingrese el año máximo del vehiculo: " );
            anioMax+=sc.nextInt();
        }
        else{
            anioMax+=Integer.MAX_VALUE;
        }
        
        System.out.println("¿Desea ingresar el precio mínimo del vehiculo? si/no :");
        String resp6=sc.nextLine();
        int precioMin=0;
        if (resp6.toLowerCase().equals("si")){
            System.out.println("Ingrese el valor del precio mínimo del vehiculo: " );
            precioMin+=sc.nextInt();
        }
        else{
            precioMin+=0;
        }
        
        System.out.println("¿Desea ingresar el precio máximo del vehiculo? si/no :");
        String resp7=sc.nextLine();
        int precioMax=0;
        if (resp7.toLowerCase().equals("si")){
            System.out.println("Ingrese el valor del precio máximo del vehiculo: " );
            precioMax+=sc.nextInt();
        }
        else{
            precioMax+=Integer.MAX_VALUE;
        }
        
        ArrayList<Vehiculo> listaSel= identificarRango(nfile,tipoS,recorridoMin,recorridoMax,anioMin,anioMax,precioMin,precioMax);
        
        for(int i=0;i<listaSel.size();i++){
            
        }
        
            
            
    }
}
