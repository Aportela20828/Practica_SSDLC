package com.mycompany.ssdlc_practica;

import java.io.*;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
public class FileUsuarios {

    private static File archivo;
    private static int contadorUsuarios;
    
    public static File getArchivo() {
        return archivo;
    }

    public static void setArchivo(File aArchivo) {
        archivo = aArchivo;
    }

    public static int getContadorUsuarios() {
        return contadorUsuarios;
    }

    public static void setContadorUsuarios(int aContadorUsuarios) {
        contadorUsuarios = aContadorUsuarios;
    }
    
    public static void crearFicheroUsuarios() throws IOException{
        if(FileUsuarios.getArchivo()==null){
            FileUsuarios.setArchivo(new File("usuarios.txt"));
            FileUsuarios.getArchivo().createNewFile();
        }
    }
    
    public static void contarUsuarios() throws FileNotFoundException, IOException{
    FileReader fr = new FileReader (FileUsuarios.getArchivo());
    BufferedReader br = new BufferedReader(fr);
    String linea;
    int contador = 0;
    while((linea=br.readLine())!=null){
        contador = contador + 1;
    }
    FileUsuarios.setContadorUsuarios(contador - 1);
        System.out.println("Usuarios" + FileUsuarios.getContadorUsuarios());
    } 
    
    public static tipoUsuario buscarUsuario(String nombre, String password) throws FileNotFoundException, IOException{
        FileReader fr = new FileReader (FileUsuarios.getArchivo());
        BufferedReader br = new BufferedReader(fr);
        String linea;
        int i = 0;
        String[] lineaSeparada;
        //while((linea=br.readLine())!=null){
        while(i<(FileUsuarios.getContadorUsuarios())){
            System.out.println("Contador de usuarios " + FileUsuarios.getContadorUsuarios());
            linea = br.readLine();
            linea = linea.trim();
            i=i+1;
            if(linea!=null){
            lineaSeparada=linea.split(",");
            System.out.println("Linea separada " + lineaSeparada.length);
            if((lineaSeparada[1].equals(nombre)) && lineaSeparada[2].equals(password)){
                fr.close();
                return tipoUsuario.valueOf(lineaSeparada[0]);
            }
            }
        }
        fr.close();
        return null;
    }
    
    public static tipoUsuario agregarUsuario() throws FileNotFoundException, IOException{
      
        FileWriter fw = new FileWriter (FileUsuarios.getArchivo(), true);
        PrintWriter pw = new PrintWriter(fw);
        Scanner sc = new Scanner(System.in);
        String rol,usuario, contraseña, nombre, apellido, tlfn;
        System.out.println("Escribame el tipo de usuario");
        rol = sc.nextLine();
        System.out.println("Escribame el nombre usuario");
        usuario = sc.nextLine();
        System.out.println("Escribame la contraseña");
        contraseña = sc.nextLine();
        System.out.println("Escribame el nombre");
        nombre = sc.nextLine();
        System.out.println("Escribame el apellido");
        apellido = sc.nextLine();
        System.out.println("Escribame el tlfno");
        tlfn = sc.nextLine();
        Usuario actor = new Usuario(usuario, contraseña, rol);
        pw.println(rol + "," + usuario + "," + contraseña + "," + 
            nombre +","+ apellido + "," + tlfn);
        FileAuditoria.lineaAuditoria("administardor", "agregarUsuario", true, archivo, actor);
        fw.close();
        return tipoUsuario.valueOf(rol);
    }
    
        public static void eliminarUsuario(String usuario) {
        FileReader fr = null;
        try {
            FileWriter fw = null;
            try {
                fw = new FileWriter (FileUsuarios.getArchivo(), true);
            } catch (IOException ex) {
                Logger.getLogger(FileUsuarios.class.getName()).log(Level.SEVERE, null, ex);
            }
            fr = new FileReader (FileUsuarios.getArchivo());
            BufferedReader br = new BufferedReader(fr);
            PrintWriter pw = new PrintWriter(fw);
            String linea;
            String lineaFinal = "";
            boolean contactoAEliminar = false;
            while((linea=br.readLine())!=null){
            if(linea!=null && linea.trim()!=""){
            if(linea.equals(usuario)){
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
        
        public static void modificarUsuario(String usuario) {
        FileReader fr = null;
        try {
            FileWriter fw = null;
            try {
                fw = new FileWriter (FileUsuarios.getArchivo(), true);
            } catch (IOException ex) {
                Logger.getLogger(FileUsuarios.class.getName()).log(Level.SEVERE, null, ex);
            }
            fr = new FileReader (FileContactos.getArchivo());
            BufferedReader br = new BufferedReader(fr);
            PrintWriter pw = new PrintWriter(fw);
            Scanner sc = new Scanner(System.in);
            String papel, nUsuario, contra, nombre, apellido, tlfn, linea;
            String lineaFinal = "";
            while((linea=br.readLine())!=null){
            if(linea!=null && linea.trim()!=""){
            if(linea.equals(usuario)){
            System.out.println("Modificar dato");
            System.out.println("Escribame el papel del usuario");
            papel = sc.nextLine();
            System.out.println("Escribame el nombre de usuario");
            nUsuario = sc.nextLine();
            System.out.println("Escribame ela contraseña");
            contra = sc.nextLine();
            System.out.println("Escribame el nombre");
            nombre = sc.nextLine();
            System.out.println("Escribame el apellido");
            apellido = sc.nextLine();
            System.out.println("Escribame el tlfno");
            tlfn = sc.nextLine();
            linea = papel + "," + nUsuario + "," + contra + "," + nombre + "," + apellido + "," + tlfn;
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
    
}
