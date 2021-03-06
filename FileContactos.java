package com.mycompany.ssdlc_practica;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;


public class FileContactos{
    
    private static File archivo;
    private static int contadorContactos;
    
    public static File getArchivo() {
        return archivo;
    }

    public static void setArchivo(File aArchivo) {
        archivo = aArchivo;
    }

    public static int getContadorContactos() {
        return contadorContactos;
    }

    public static void setContadorContactos(int aContadorContactos) {
        contadorContactos = aContadorContactos;
    }
    
    public static void crearFicheroContactos() throws IOException{
        if(FileContactos.getArchivo()==null){
            FileContactos.setArchivo(new File("contactos.txt"));
            FileContactos.getArchivo().createNewFile();
        }
    }
    
    public static void contarContactos() throws FileNotFoundException, IOException{
    FileReader fr = new FileReader (FileContactos.getArchivo());
    BufferedReader br = new BufferedReader(fr);
    String linea;
    int contador = 0;
    while((linea=br.readLine())!=null){
        contador = contador + 1;
    }
    System.out.println("Contador contac "+ contador);
    FileContactos.setContadorContactos(contador);
    } 
    
    public static void agregarContacto(Usuario actor) throws IOException {
        FileWriter fw = null;
        try {
            fw = new FileWriter (FileContactos.getArchivo(), true);
        } catch (IOException ex) {
            Logger.getLogger(FileContactos.class.getName()).log(Level.SEVERE, null, ex);
        }
        PrintWriter pw = new PrintWriter(fw);
        Scanner sc = new Scanner(System.in);
        String contacto, apellido, tlfn;
        System.out.println("Escribame el nombre contacto");
        contacto = sc.nextLine();
        System.out.println("Escribame el apellido");
        apellido = sc.nextLine();
        System.out.println("Escribame el tlfno");
        tlfn = sc.nextLine();
        pw.println(contacto + "," + apellido + "," + tlfn + "\n");
        try {
            fw.close();
        } catch (IOException ex) {
            Logger.getLogger(FileContactos.class.getName()).log(Level.SEVERE, null, ex);
        }
        FileAuditoria.lineaAuditoria("gestor", "agregarContacto", true, archivo, actor);
    }

    
    public static void modificarContacto(Usuario actor) {
        FileReader fr = null;
        try {
            FileWriter fw = null;
            try {
                fw = new FileWriter (FileContactos.getArchivo(), true);
            } catch (IOException ex) {
                Logger.getLogger(FileContactos.class.getName()).log(Level.SEVERE, null, ex);
            }
            fr = new FileReader (FileUsuarios.getArchivo());
            BufferedReader br = new BufferedReader(fr);
            PrintWriter pw = new PrintWriter(fw);
            Scanner sc = new Scanner(System.in);
            String contacto, apellido, tlfn, contactoACambiar, linea;
            String lineaFinal = "";
            String[] lineaSeparada;
            boolean resultado = false;
            System.out.println("Escribame el nombre de contacto que quiere cambiar");
            contactoACambiar = sc.nextLine();
            while((linea=br.readLine())!=null){
            if(linea!=null && linea.trim()!=""){
            lineaSeparada = linea.split(",");
            if(lineaSeparada[0].equals(contactoACambiar)){
            System.out.println("Escribame el nombre contacto");
            contacto = sc.nextLine();
            System.out.println("Escribame el apellido");
            apellido = sc.nextLine();
            System.out.println("Escribame el tlfno");
            tlfn = sc.nextLine();
            linea = contacto + "," + apellido + "," + tlfn;
            resultado = true;
            }
            lineaFinal = linea + "\n";
            }
            try {
                fw.close();
            } catch (IOException ex) {
                Logger.getLogger(FileContactos.class.getName()).log(Level.SEVERE, null, ex);
            }
            }
            FileContactos.getArchivo().delete();
            File txt2 = new File("contactos.txt");
	    FileWriter fileNew = new FileWriter(txt2);
	    BufferedWriter bw = new BufferedWriter(fileNew);
	    fileNew.write(lineaFinal);
	    fileNew.close();
            FileAuditoria.lineaAuditoria("gestor", "modificarContacto", resultado, archivo, actor);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FileContactos.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FileContactos.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                fr.close();
            } catch (IOException ex) {
                Logger.getLogger(FileContactos.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public static void modificarContacto(String contacto) {
        FileReader fr = null;
        try {
            FileWriter fw = null;
            try {
                fw = new FileWriter (FileContactos.getArchivo(), true);
            } catch (IOException ex) {
                Logger.getLogger(FileContactos.class.getName()).log(Level.SEVERE, null, ex);
            }
            fr = new FileReader (FileContactos.getArchivo());
            BufferedReader br = new BufferedReader(fr);
            PrintWriter pw = new PrintWriter(fw);
            Scanner sc = new Scanner(System.in);
            String nombre, apellido, tlfn, linea;
            String lineaFinal = "";
            while((linea=br.readLine())!=null){
            if(linea!=null && linea.trim()!=""){
            if(linea.equals(contacto)){
            System.out.println("Modificar dato");
            System.out.println("Escribame el nombre contacto");
            nombre = sc.nextLine();
            System.out.println("Escribame el apellido");
            apellido = sc.nextLine();
            System.out.println("Escribame el tlfno");
            tlfn = sc.nextLine();
            linea = nombre + "," + apellido + "," + tlfn;
            }
            lineaFinal = linea + "\n";
            }
            try {
                fw.close();
            } catch (IOException ex) {
                Logger.getLogger(FileContactos.class.getName()).log(Level.SEVERE, null, ex);
            }
            }
            FileContactos.getArchivo().delete();
            File txt2 = new File("contactos.txt");
	    FileWriter fileNew = new FileWriter(txt2);
	    BufferedWriter bw = new BufferedWriter(fileNew);
	    fileNew.write(lineaFinal);
	    fileNew.close();

        } catch (FileNotFoundException ex) {
            Logger.getLogger(FileContactos.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FileContactos.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                fr.close();
            } catch (IOException ex) {
                Logger.getLogger(FileContactos.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public static void eliminarContacto(Usuario actor) {
        FileReader fr = null;
        try {
            FileWriter fw = null;
            try {
                fw = new FileWriter (FileContactos.getArchivo(), true);
            } catch (IOException ex) {
                Logger.getLogger(FileContactos.class.getName()).log(Level.SEVERE, null, ex);
            }
            fr = new FileReader (FileContactos.getArchivo());
            BufferedReader br = new BufferedReader(fr);
            PrintWriter pw = new PrintWriter(fw);
            Scanner sc = new Scanner(System.in);
            String contactoACambiar, linea;
            String lineaFinal = "";
            String[] lineaSeparada;
            boolean contactoAEliminar = false;
            boolean resultado = false;
            System.out.println("Escribame el nombre de contacto que quiere cambiar");
            contactoACambiar = sc.nextLine();
            while((linea=br.readLine())!=null){
            if(linea!=null && linea.trim()!=""){
            lineaSeparada = linea.split(",");
            if(lineaSeparada[0].equals(contactoACambiar)){
            contactoAEliminar = true;
            resultado = true;
            }
            if(contactoAEliminar == false){
            lineaFinal = linea + "\n";
            contactoAEliminar = false;
            }
            }
            }
            try {
                fw.close();
            } catch (IOException ex) {
                Logger.getLogger(FileContactos.class.getName()).log(Level.SEVERE, null, ex);
            }
            FileContactos.getArchivo().delete();
            File txt2 = new File("contactos.txt");
	    FileWriter fileNew = new FileWriter(txt2);
	    BufferedWriter bw = new BufferedWriter(fileNew);
	    fileNew.write(lineaFinal);
	    fileNew.close();
            FileAuditoria.lineaAuditoria("administrador", "eliminarContacto", resultado, archivo, actor);
            FileContactos.setArchivo(txt2);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FileContactos.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FileContactos.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                fr.close();
            } catch (IOException ex) {
                Logger.getLogger(FileContactos.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
    }
    
    public static void eliminarContacto(String contacto) {
        FileReader fr = null;
        try {
            FileWriter fw = null;
            try {
                fw = new FileWriter (FileContactos.getArchivo(), true);
            } catch (IOException ex) {
                Logger.getLogger(FileContactos.class.getName()).log(Level.SEVERE, null, ex);
            }
            fr = new FileReader (FileUsuarios.getArchivo());
            BufferedReader br = new BufferedReader(fr);
            PrintWriter pw = new PrintWriter(fw);
            String linea;
            String lineaFinal = "";
            boolean contactoAEliminar = false;
            while((linea=br.readLine())!=null){
            if(linea!=null && linea.trim()!=""){
            if(linea.equals(contacto)){
            contactoAEliminar = true;
            }
            if(contactoAEliminar == false){
            lineaFinal = linea + "\n";
            }
            contactoAEliminar = false;
            }
            }
            try {
                fw.close();
            } catch (IOException ex) {
                Logger.getLogger(FileContactos.class.getName()).log(Level.SEVERE, null, ex);
            }
            FileContactos.getArchivo().delete();
            File txt2 = new File("contactos.txt");
	    FileWriter fileNew = new FileWriter(txt2);
	    BufferedWriter bw = new BufferedWriter(fileNew);
	    fileNew.write(lineaFinal);
	    fileNew.close();
            FileContactos.setArchivo(txt2);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FileContactos.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FileContactos.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                fr.close();
            } catch (IOException ex) {
                Logger.getLogger(FileContactos.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
    }
    

    public static void listarContactos(Usuario actor) {
        FileReader fr = null;
        try {
            fr = new FileReader (FileUsuarios.getArchivo());
            BufferedReader br = new BufferedReader(fr);
            String linea;
            while((linea=br.readLine())!=null){
            if(linea!=null && linea.trim()!=null){
                System.out.println(linea);
            }
            }   
            fr.close();
            FileAuditoria.lineaAuditoria("asistente", "listarContacto", true, archivo, actor);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FileContactos.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FileContactos.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                fr.close();
            } catch (IOException ex) {
                Logger.getLogger(FileContactos.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
