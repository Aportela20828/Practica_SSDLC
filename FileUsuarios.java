package com.mycompany.ssdlc_practica;

import java.io.*;
import java.util.Scanner;
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
        while(i<=(FileUsuarios.getContadorUsuarios())){
            System.out.println("Contador de usuarios " + FileUsuarios.getContadorUsuarios());
            linea = br.readLine();
            linea = linea.trim();
            i=i+1;
            if(linea!=null){
            lineaSeparada=linea.split(",");
            System.out.println("Linea separada " + lineaSeparada.length);
            for(int j = 0; j<=(lineaSeparada.length)-1; i++){
                System.out.println("Hola4");
                System.out.println("Hueco" + i + "palabra" + lineaSeparada[i]);
            }
            if((lineaSeparada[1].equals(nombre)) && lineaSeparada[2].equals(password)){
                System.out.println("Hola5");
                fr.close();
                return tipoUsuario.valueOf(lineaSeparada[0]);
            }
            }
        }
        fr.close();
        return null;
    }
    
    public static void agregarUsuario() throws FileNotFoundException, IOException{
      
        FileWriter fw = new FileWriter (FileUsuarios.getArchivo(), true);
        PrintWriter pw = new PrintWriter(fw);
        Scanner sc = new Scanner(System.in);
        String tipoUsuario ,usuario, contraseña, nombre, apellido, tlfn;
        System.out.println("Escribame el tipo de usuario");
        tipoUsuario = sc.nextLine();
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
        pw.println(tipoUsuario + "," + usuario + "," + contraseña + "," + 
            nombre +","+ apellido + "," + tlfn + "\n");
        fw.close();
    }
        
}
