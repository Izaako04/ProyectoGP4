/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espol.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;


public class Util {
   private Util(){}
    
    public static int nextID(String nfile){
        int id = 0;
        try(Scanner sc = new Scanner(new File(nfile))){
            while(sc.hasNextLine()){
                String line = sc.nextLine();
                String[] tokens = line.split("\\|");
                id = Integer.parseInt(tokens[0]);
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return id+1;
    }
    
    public static byte[] getSHA(String input) throws NoSuchAlgorithmException
    {
        // Static getInstance method is called with hashing SHA
        MessageDigest md = MessageDigest.getInstance("SHA-256");
 
        // digest() method called
        // to calculate message digest of an input
        // and return array of byte
        return md.digest(input.getBytes(StandardCharsets.UTF_8));
    }
     
    public static String toHexString(byte[] hash)
    {
        // Convert byte array into signum representation
        BigInteger number = new BigInteger(1, hash);
 
        // Convert message digest into hex value
        StringBuilder hexString = new StringBuilder(number.toString(16));
 
        // Pad with leading zeros
        while (hexString.length() < 64)
        {
            hexString.insert(0, '0');
        }
 
        return hexString.toString();
    }
    
        public static void crearArchivo(String nfile){
        try{
            PrintWriter pw = new PrintWriter(new FileOutputStream(new File(nfile)));
            pw.close();
        } catch(FileNotFoundException e){
            System.out.println(e.getMessage());
        }
    }
}
