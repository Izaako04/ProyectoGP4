/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espol.proyectog4;

import java.util.ArrayList;
import static ec.edu.espol.util.Util.getSHA;
import static ec.edu.espol.util.Util.nextID;
import static ec.edu.espol.util.Util.toHexString;
import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Scanner;


public class Vendedor {
    private int id;
    private String nombres;
    private String apellidos;
    private String organizacion;
    private String correo_electronico;
    private String clave;
    private ArrayList<Registro> registros;
    
    public Vendedor(int id, String nombres, String apellidos, String organizacion, String correo_electronico, String clave) {
        this.id = id;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.organizacion = organizacion;
        this.correo_electronico = correo_electronico;
        this.clave = clave;
        this.registros = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public ArrayList<Registro> getRegistros() {
        return registros;
    }

    public void setRegistros(ArrayList<Registro> registros) {
        this.registros = registros;
    }
    
    public void saveArchivo(String nfile){
        try(PrintWriter pw = new PrintWriter(new FileOutputStream(new File(nfile),true))){
            pw.println(this.nombres+"|"+this.apellidos+"|"+this.organizacion+"|"+this.correo_electronico+"|"+this.clave);
            
        } catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    
    public static void saveArchivo(ArrayList<Vendedor> vendedores, String nfile){
        try(PrintWriter pw = new PrintWriter(new FileOutputStream(new File(nfile),true))){
            for(Vendedor x: vendedores)
                pw.println(x.nombres+"|"+x.apellidos+"|"+x.organizacion+"|"+x.correo_electronico+"|"+x.clave);
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
    
    public static ArrayList<String> readFileClaves(String nfile){
        ArrayList<String> claves = new ArrayList<>();
        try(Scanner sc = new Scanner(new File(nfile))){
            while(sc.hasNextLine()){
                String line = sc.nextLine();
                String[] tokens = line.split("\\|");
                String correo_elec = tokens[4];
                claves.add(correo_elec);
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return claves;
    }
            
    public static void nextVendedor(Scanner sc,String nfile){
        int id_vendedor = nextID(nfile);
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
                    Vendedor v = new Vendedor(id_vendedor,n,ape,org,password,correof);
                    v.saveArchivo(nfile);
                }
            }
        }catch (NoSuchAlgorithmException e) {
            System.out.println("Exception thrown for incorrect algorithm: " + e.getMessage());
        }
        
    }
    
    public static Vendedor searchByID(ArrayList<Vendedor> vendedores, int id){
        for(Vendedor x: vendedores){
            if(x.id == id)
                return x;
        }
        return null;
    }
    
    public static boolean validarCredenciales(Scanner sc, String nfilevendedores){
        System.out.println("Ingrese su correo electronico de vendedor");
        String correo = sc.nextLine();
        Boolean correoin = false;
        ArrayList<String> correos_dados = readFileCorreos(nfilevendedores);
        for (String c : correos_dados) {
            if (c.equals(correo)) {
                correoin = true;
            } else {
                System.out.println("Correo no encontrado");
            }
        }
        System.out.println("Ingrese su clave de vendedor");
        String cv = sc.nextLine();
        Boolean clavein = false;
        ArrayList<String> claves = readFileClaves(nfilevendedores);
        try{
            String password = toHexString(getSHA(cv));
            for (String c : claves) {
                if (c.equals(password)) {
                    clavein = true;
                } else {
                    System.out.println("Clave incorrecta");
                }
            }
        }catch (NoSuchAlgorithmException e){
                System.out.println("Exception thrown for incorrect algorithm: " + e.getMessage());
            }
        return correoin && clavein;
    }
    
    public static void registrarVehiculo(Scanner sc, String nfilevendedores){
        boolean credenciales = validarCredenciales(sc, nfilevendedores);
        while(credenciales != true){
            System.out.println("Usuario o contraseña incorrecto - Ingrese de nuevo:");
            credenciales = validarCredenciales(sc, nfilevendedores);
        }
        if (credenciales==true){
            System.out.println("Ingrese tipo de vehiculo a registrar: \n 1.Moto \n 2.Auto \n 3.Camioneta");
            int opcion = sc.nextInt();
            switch (opcion) {
                case (1):
                    Vehiculo moto = Vehiculo.ingresarVehiculo(sc, "vehiculos");
                    moto.saveArchivo("vehiculos.txt");
                case (2):
                    Auto auto = Auto.ingresarAuto(sc, "vehiculos");
                    auto.saveArchivo("vehiculos.txt");
                case (3):
                    Camioneta camioneta = Camioneta.ingresarCamioneta(sc, "vehiculos");
                    camioneta.saveArchivo("vehiculos.txt");
            }
        }
    }
    
    public static void aceptarOferta(Scanner sc, String nfilevendedores, String nfilecompradores, ArrayList<Oferta> ofertasVehiculos){
        boolean credenciales = validarCredenciales(sc, nfilevendedores);
        while(credenciales != true){
            System.out.println("Usuario o contraseña incorrecto - Ingrese de nuevo:");
            credenciales = validarCredenciales(sc, nfilevendedores);
        }
        int i=0;
        if (credenciales==true){
            System.out.println(ofertasVehiculos.get(i));
            System.out.println("1.Aceptar Oferta \n 2.Siguiente Oferta");
            int opcion = sc.nextInt();
            do{
                switch(opcion){
                case (1):
                    i += 1;
                    System.out.println(ofertasVehiculos.get(i));
                    System.out.println("1.Aceptar Oferta \n 2.Siguiente Oferta \n 3.Anterior Oferta");
                    opcion = sc.nextInt();
                case (2):
                    i-= 1;
                    System.out.println(ofertasVehiculos.get(i));
                    System.out.println("1.Aceptar Oferta \n 2.Siguiente Oferta \n 3.Anterior Oferta");
                    opcion = sc.nextInt();
                }
            }while(opcion != 1);
            
        }
            
    }

}
