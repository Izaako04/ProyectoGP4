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
    
//    public void ofertarVehiculo(Scanner sc, String nfile){
//        System.out.println("¿Desea ingresar tipo de vehiculo? si/no :");
//        String resp1=sc.nextLine();
//        if (resp1.toLowerCase().equals("si")){
//            System.out.println("Ingrese el tipo de vehiculo: " );
//            String tipo=sc.nextLine();
//        }
//        
//        System.out.println("¿Desea ingresar el recorrido mínimo del vehiculo? si/no :");
//        String resp2=sc.nextLine();
//        if (resp2.toLowerCase().equals("si")){
//            System.out.println("Ingrese el valor del recorrido mínimo del vehiculo: " );
//            int recorridoMin=sc.nextInt();
//        }
//        
//        System.out.println("¿Desea ingresar el recorrido máximo del vehiculo? si/no :");
//        String resp3=sc.nextLine();
//        if (resp3.toLowerCase().equals("si")){
//            System.out.println("Ingrese el valor del recorrido máximo del vehiculo: " );
//            int recorridoMax=sc.nextInt();
//        }
//        
//        System.out.println("¿Desea ingresar el año mínimo del vehiculo? si/no :");
//        String resp4=sc.nextLine();
//        if (resp4.toLowerCase().equals("si")){
//            System.out.println("Ingrese el año mínimo del vehiculo: " );
//            int anioMin=sc.nextInt();
//        }
//        
//        System.out.println("¿Desea ingresar el año máximo del vehiculo? si/no :");
//        String resp5=sc.nextLine();
//        if (resp5.toLowerCase().equals("si")){
//            System.out.println("Ingrese el año máximo del vehiculo: " );
//            int anioMax=sc.nextInt();
//        }
//        
//        System.out.println("¿Desea ingresar el precio mínimo del vehiculo? si/no :");
//        String resp6=sc.nextLine();
//        if (resp6.toLowerCase().equals("si")){
//            System.out.println("Ingrese el valor del precio mínimo del vehiculo: " );
//            int precioMin=sc.nextInt();
//        }
//        
//        System.out.println("¿Desea ingresar el precio máximo del vehiculo? si/no :");
//        String resp7=sc.nextLine();
//        if (resp7.toLowerCase().equals("si")){
//            System.out.println("Ingrese el valor del precio máximo del vehiculo: " );
//            int precioMax=sc.nextInt();
//        }
//        ArrayList<Vehiculo> vehiculosObtenidos=new ArrayList<>();
//        try(Scanner sc1 = new Scanner(new File(nfile))){
//            while(sc1.hasNextLine()){
//                String line = sc1.nextLine();
//                String[] tokens = line.split("\\|");
//                
//                if((tokens[0].equals(tipo)))
//                
//                
//            
//            }
//        }catch(Exception e){
//            System.out.println(e.getMessage());
//        }
//        
//    }
}
