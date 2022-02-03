package com.mycompany.ssdlc_practica;

import java.util.Scanner;

public class Asistente extends Roles{
    
    @Override
    public void menu(Usuario usuario){
        int opcion = 5;
        while(opcion != 0){
            System.out.println("Escribame la opción");
            System.out.println("1) Listar contactos");
            System.out.println("0) Salir");
            Scanner sc = new Scanner(System.in);
            opcion = sc.nextInt();
            switch(opcion){
                case 1:{
                    System.out.println("Listar contactos");
                    FileContactos.listarContactos(usuario);
                    break;
                }
                case 0:{
                    System.out.println("Salir");
                    break;
                }
                default:{
                    System.out.println("Esa opción no es valida");
                    break;
                }
            }
        }
    }
    
}
