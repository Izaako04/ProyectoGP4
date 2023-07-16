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
import java.util.Locale;
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
            pw.println(this.id+"|"+this.nombres+"|"+this.apellidos+"|"+this.organizacion+"|"+this.correo_electronico+"|"+this.clave);
            
        } catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    
    public static void saveArchivo(ArrayList<Vendedor> vendedores, String nfile){
        try(PrintWriter pw = new PrintWriter(new FileOutputStream(new File(nfile),true))){
            for(Vendedor x: vendedores)
                pw.println(x.id+"|"+x.nombres+"|"+x.apellidos+"|"+x.organizacion+"|"+x.correo_electronico+"|"+x.clave);
        } catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    
    public static ArrayList<String> readFileCorreos(String nfile) {
        ArrayList<String> correos = new ArrayList<>();
        try (Scanner sc = new Scanner(new File(nfile))) {
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] tokens = line.split("\\|");
                String correo_elec = tokens[5];
                correos.add(correo_elec);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return correos;
    }

    public static ArrayList<Vendedor> readFileVendedores(String nfile) {
        ArrayList<Vendedor> vendedores = new ArrayList<>();
        try (Scanner sc = new Scanner(new File(nfile))) {
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] tokens = line.split("\\|");
                int ids = Integer.parseInt(tokens[0]);
                Vendedor vend = new Vendedor(ids,tokens[1],tokens[2],tokens[3],tokens[4],tokens[5]);
                vendedores.add(vend);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return vendedores;
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
            
    public static void nextVendedor(Scanner sc, String nfileVendores) {
        int id_vendedor = nextID(nfileVendores);
        sc.useDelimiter("\n");
        sc.useLocale(Locale.US);
        System.out.println("Ingrese nombres");
        String n = sc.next();
        System.out.println("Ingrese apellidos");
        String ape = sc.next();
        System.out.println("Ingrese organizacion");
        String org = sc.next();
        System.out.println("Ingrese clave");
        String cv = sc.next();
        String password;
        try {
            password = toHexString(getSHA(cv));
            System.out.println("Ingrese correo");
            String correo = sc.next();
            ArrayList<String> correos_dados = readFileCorreos(nfileVendores);
            if(!correos_dados.isEmpty()){
                for (String c : correos_dados) {
                    if (c.equals(correo)) {
                    System.out.println("Correo ya registrado");
                    } else {
                    Vendedor v = new Vendedor(id_vendedor,n,ape,org,password,correo);
                    v.saveArchivo(nfileVendores);
                    }
                }
            }else{
                Vendedor v = new Vendedor(id_vendedor,n,ape,org,password,correo);
                v.saveArchivo(nfileVendores);
            }                
        } catch (NoSuchAlgorithmException e) {
            System.out.println("Exception thrown for incorrect algorithm: " + e.getMessage());
        }
    }
    
    public static Vendedor searchByCorreo(ArrayList<Vendedor> vendedores, String correo){
        for(Vendedor x: vendedores){
            if(x.correo_electronico.equals(correo))
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
            correoin = c.equals(correo);
        }
        System.out.println("Ingrese su clave de vendedor");
        String cv = sc.nextLine();
        Boolean clavein = false;
        ArrayList<String> claves = readFileClaves(nfilevendedores);
        try{
            String password = toHexString(getSHA(cv));
            for (String c : claves) {
                clavein = c.equals(password);
            }
        }catch (NoSuchAlgorithmException e){
                System.out.println("Exception thrown for incorrect algorithm: " + e.getMessage());
            }
        return correoin && clavein;
    }
    
    public static void registrarVehiculo(Scanner sc, String nfilevendedores, String nfilevehiculos){
        boolean credenciales = validarCredenciales(sc, nfilevendedores);
        while(credenciales != true){
            System.out.println("Usuario o contraseña incorrecto - Ingrese de nuevo:");
            credenciales = validarCredenciales(sc, nfilevendedores);
        }
        if (credenciales==true){
            System.out.println("Ingrese tipo de vehiculo a registrar: \n 1.Moto \n 2.Auto \n 3.Camioneta");
            int opcion = Integer.parseInt(sc.nextLine());
            switch (opcion) {
                case (1):
                    Vehiculo moto = Vehiculo.ingresarVehiculo(sc, nfilevehiculos);
                    moto.saveArchivo("vehiculos.txt");
                    break;
                case (2):
                    Auto auto = Auto.ingresarAuto(sc, nfilevehiculos);
                    auto.saveArchivo("vehiculos.txt");
                    break;
                case (3):
                    Camioneta camioneta = Camioneta.ingresarCamioneta(sc,nfilevehiculos);
                    camioneta.saveArchivo("vehiculos.txt");
                    break;
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
                case (1) -> {
                    i += 1;
                    System.out.println(ofertasVehiculos.get(i));
                    System.out.println("1.Aceptar Oferta \n 2.Siguiente Oferta \n 3.Anterior Oferta");
                    opcion = sc.nextInt();
                    }
                case (2) -> {
                    i-= 1;
                    System.out.println(ofertasVehiculos.get(i));
                    System.out.println("1.Aceptar Oferta \n 2.Siguiente Oferta \n 3.Anterior Oferta");
                    opcion = sc.nextInt();
                    }
                }
            }while(opcion != 1);
            
        }
            
    }

}
