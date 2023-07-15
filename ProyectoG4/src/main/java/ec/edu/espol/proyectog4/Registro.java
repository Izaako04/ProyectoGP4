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

public class Registro {
    private int id;
    private int idVehiculo;
    private int idVendedor;
    private Vehiculo vehiculo;
    private Vendedor vendedor;

    public Registro(int id, int idVehiculo, int idVendedor) {
        this.id = id;
        this.idVehiculo = idVehiculo;
        this.idVendedor = idVendedor;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdVehiculo() {
        return idVehiculo;
    }

    public void setIdVehiculo(int idVehiculo) {
        this.idVehiculo = idVehiculo;
    }

    public int getIdVendedor() {
        return idVendedor;
    }

    public void setIdVendedor(int idVendedor) {
        this.idVendedor = idVendedor;
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }

    public Vendedor getVendedor() {
        return vendedor;
    }

    public void setVendedor(Vendedor vendedor) {
        this.vendedor = vendedor;
    }
    
    public void saveArchivo(String nfile){
        try(PrintWriter pw = new PrintWriter(new FileOutputStream(new File(nfile),true))){
            pw.println(this.id+"|"+this.idVehiculo+"|"+this.idVendedor);
            
        } catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    
    public static ArrayList<Registro> readFile(String nfile){
        ArrayList<Registro> registros = new ArrayList<>();
        try(Scanner sc = new Scanner(new File(nfile))){
            while(sc.hasNextLine()){
                String linea = sc.nextLine();
                String[] tokens = linea.split("\\|");
                Registro r = new Registro(Integer.parseInt(tokens[0]),Integer.parseInt(tokens[1]),Integer.parseInt(tokens[2]));
                registros.add(r);
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return registros;
    }
    
    public static void vincular(ArrayList<Vendedor> vendedores, ArrayList<Registro> registros, ArrayList<Vehiculo> vehiculos){
        for(Registro r: registros){
            Vendedor vend = Vendedor.searchByID(vendedores,r.getIdVendedor());
            Vehiculo veh = Vehiculo.searchByID(vehiculos, r.getIdVehiculo());
            vend.getRegistros().add(r);
            veh.getRegistros().add(r);
            r.setVehiculo(veh);
            r.setVendedor(vend);
        }
    }
}
