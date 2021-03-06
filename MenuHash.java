package com.mycompany.ssdlc_practica;

import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class MenuHash {
    
    public static void menuHash(String linea, File archivo, File archivoHash) throws NoSuchAlgorithmException, IOException{
        System.out.println("Hay algún problema en un dato en el fichero hash.");
        System.out.println("1) Eliminar dato.");
        System.out.println("2) Modificar dato.");
        System.out.println("3) Crear nuevo fichero.");
        Scanner sc = new Scanner(System.in);
        int opcion = sc.nextInt();
        switch(opcion){
            case 1:{
                FileHash.eliminarHash(linea, archivoHash);
                if(archivo.toString().equals("Usuarios.txt")){
                    FileUsuarios.eliminarUsuario(linea);
        }       else if(archivoHash.toString().equals("hashContactos.txt")){
                    FileContactos.eliminarContacto(linea);
        }       else{
                    System.out.println("Hay un error");
        }
                break;
            }
            case 2:{
                FileHash.clonarHash(archivoHash, archivo);
            }
            case 3:{
                if(archivo.toString().equals("Usuarios.txt")){
                    FileUsuarios.modificarUsuario(linea);
                    FileHash.generarFicheroHashUsuarios(FileUsuarios.getArchivo());
        }       else if(archivoHash.toString().equals("hashContactos.txt")){
                    FileContactos.modificarContacto(linea);
                    FileHash.generarFicheroHashContactos(FileContactos.getArchivo());
        }       else{
                    System.out.println("Hay un error");
        }
                break;
            }
            default:{
                System.out.println("Esa opcion no es valida");
                break;
            }
        }
    }
}
