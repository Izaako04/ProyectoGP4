/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espol.proyectog4;

import ec.edu.espol.util.Util;
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

    @Override
    public String toString() {
        return "Vendedor{" + "id=" + id + ", nombres=" + nombres + ", apellidos=" + apellidos + ", organizacion=" + organizacion + ", correo_electronico=" + correo_electronico + ", clave=" + clave + ", registros=" + registros + '}';
    }
    
    public static ArrayList<String> readFileCorreos(String nfile) {
        ArrayList<String> correos = new ArrayList<>();
        try (Scanner sc = new Scanner(new File(nfile))) {
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] tokens = line.split("\\|");
                String correo_elec = tokens[4];
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
                String correo_elec = tokens[5];
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
                    Vendedor v = new Vendedor(id_vendedor,n,ape,org,correo,password);
                    v.saveArchivo(nfileVendores);
                    }
                }
            }else{
                Vendedor v = new Vendedor(id_vendedor,n,ape,org,correo,password);
                v.saveArchivo(nfileVendores);
            }                
        } catch (NoSuchAlgorithmException e) {
            System.out.println("Exception thrown for incorrect algorithm: " + e.getMessage());
        }
    }
    
        public static Vendedor searchByID(ArrayList<Vendedor> v, int id){
            for(Vendedor x: v){
                if(x.id==id)
                    return x;
        }
        return null;
    }

    public static Vendedor searchByID(String nfilevendedores, int id){
        ArrayList<Vendedor> vendedores = new ArrayList<>();
        try (Scanner sc = new Scanner(new File(nfilevendedores))) {
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
        for(Vendedor x: vendedores){
            if(x.id==id)
                return x;
        }
        return null;
    }
    
    public static Vendedor searchByCorreo(ArrayList<Vendedor> vendedores, String correo){
        for(Vendedor x: vendedores){
            if(x.getCorreo_electronico().equals(correo)) {
                return x;
            } else {
                System.out.println("Vendedor no encontrado");
            }
        }
        return null;
    }
    
    public static boolean validarCredenciales(String correo, String cv, String nfilevendedores){
        Boolean correoin = false;
        ArrayList<String> correos_dados = readFileCorreos(nfilevendedores);
        for (String c : correos_dados) {
            correoin = c.equals(correo);
        }
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
        System.out.println("Ingrese su correo electronico de vendedor");
        String correo = sc.nextLine();
        System.out.println("Ingrese su clave de vendedor");
        String cv = sc.nextLine();
        boolean credenciales = validarCredenciales(correo, cv, nfilevendedores);
        while(credenciales != true){
            System.out.println("Usuario o contraseña incorrecto - Ingrese de nuevo:");
            System.out.println("Ingrese su correo electronico de vendedor");
            correo = sc.nextLine();
            System.out.println("Ingrese su clave de vendedor");
            cv = sc.nextLine();
            credenciales = validarCredenciales(correo, cv, nfilevendedores);
        }
        ArrayList<Vendedor> vendedores = readFileVendedores("Vendedores.txt");
        Vendedor vendedor = searchByCorreo(vendedores,correo);
        if (credenciales==true){
            System.out.println("Ingrese tipo de vehiculo a registrar: \n 1.Moto \n 2.Auto \n 3.Camioneta");
            int opcion = Integer.parseInt(sc.nextLine());
            switch (opcion) {
                case (1):
                    Vehiculo moto = Vehiculo.ingresarVehiculo(sc, nfilevehiculos,vendedor);
                    moto.saveArchivo("vehiculos.txt");
                    break;
                case (2):
                    Auto auto = Auto.ingresarAuto(sc, nfilevehiculos,vendedor);
                    auto.saveArchivo("vehiculos.txt");
                    break;
                case (3):
                    Camioneta camioneta = Camioneta.ingresarCamioneta(sc,nfilevehiculos,vendedor);
                    camioneta.saveArchivo("vehiculos.txt");
                    break;
            }
        }
    }
    
    public static void aceptarOferta(Scanner sc, String nfilevendedores,String nfileVeh){
        System.out.println("Ingrese su correo electronico de vendedor");
        String correo = sc.nextLine();
        System.out.println("Ingrese su clave de vendedor");
        String cv = sc.nextLine();
        boolean credenciales = validarCredenciales(correo, cv, nfilevendedores);
        while(credenciales != true){
            System.out.println("Usuario o contraseña incorrecto - Ingrese de nuevo:");
            System.out.println("Ingrese su correo electronico de vendedor");
            correo = sc.nextLine();
            System.out.println("Ingrese su clave de vendedor");
            cv = sc.nextLine();
            credenciales = validarCredenciales(correo, cv, nfilevendedores);
        }
        int i=0;
        int x= 0;
        while(x==0){
            System.out.println("Ingrese la placa: ");
            String placaIn=sc.nextLine();
            ArrayList<Vehiculo> vehSel= Vehiculo.vehConPlaca(nfileVeh, placaIn);
            Vehiculo vehiculoI=vehSel.get(i);
            ArrayList<Double> precioSel=Oferta.precioVeh(placaIn);
            double precioI=precioSel.get(i);
            ArrayList<String> correoSel=Oferta.correoVeh(placaIn);
            String correoI=correoSel.get(i);
            
            System.out.println(vehiculoI.getMarca()+" "+vehiculoI.getModelo()+" Precio: "+vehiculoI.getPrecio() );
            System.out.println("Se han realizado "+ precioSel.size() +" ofertas");
            System.out.println(" ");
            System.out.println("Oferta"+(i+1));
            System.out.println("Correo: "+correoI);
            System.out.println("Precio ofertado: "+ precioI);
            
            int resp=0;
            if(i==0){
                System.out.println("Ingrese el número de la opción que desea: \n 0.Aceptar oferta \n 1.Siguiente oferta ");
                resp=sc.nextInt();
            } 
            else if(i>0 && i<vehSel.size()-1){
                System.out.println("Ingrese el número de la opción que desea: \n 0.Aceptar oferta \n 1.Siguiente oferta \n 2. Anteior oferta");
                resp=sc.nextInt();
            }
            else if(i==vehSel.size()-1){
                System.out.println("Ingrese el número de la opción que desea: \n 0.Aceptar oferta \n 1.Anterior oferta ");
                resp=sc.nextInt();
            }
            
            if (resp==0){
                Util.enviarCorreo(correoI,"Oferta Aceptada","La oferta que ha realizado ha sido aceptada por el vendedor del vehiculo",correo,cv);
                int pos = Vehiculo.searchPosByPlaca(nfileVeh, placaIn);
                Util.eliminarInformacion("Vehiculos.txt", pos);
            }
            else if(resp==1){
                if(i>=0 && i<vehSel.size()-1)
                    i++;
                else {
                    i--;
                }
            }
            else if(resp==2)
                i--;
           
        }
    }
}
