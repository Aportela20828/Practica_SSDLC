package com.mycompany.ssdlc_practica;

import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class MenuHash {
    
    public static void menuHash(String linea, File archivo, File archivoHash) throws NoSuchAlgorithmException, IOException{
        System.out.println("Hay algún problema en un dato en el fichero hash. \n");
        System.out.println("1) Eliminar dato.\n");
        System.out.println("2) Modificar dato.\n");
        System.out.println("3) Crear nuevo fichero.\n");
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
            }
            case 2:{
                
            }
            case 3:{
                
            }
            default:{
                System.out.println("Esa opcion no es valida");
            }
        }
    }
}
