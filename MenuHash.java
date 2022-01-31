package com.mycompany.ssdlc_practica;

import java.util.Scanner;

public class MenuHash {
    
    public static void menuHash(){
        System.out.println("Hay algún problema en un dato en el fichero hash. \n");
        System.out.println("1) Eliminar dato.\n");
        System.out.println("2) Modificar dato.\n");
        System.out.println("3) Crear nuevo fichero.\n");
        Scanner sc = new Scanner(System.in);
        int opcion = sc.nextInt();
        switch(opcion){
            case 1:{
                
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
