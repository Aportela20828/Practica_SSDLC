package com.mycompany.ssdlc_practica;

import java.util.Scanner;

public class Gestor extends Roles{
    
    @Override
    public void menu(Usuario usuario){
        int opcion = 5;
        while(opcion != 0){
            System.out.println("Escribame la opción");
            System.out.println("1) Listar contactos");
            System.out.println("2) Agregar contacto");
            System.out.println("3) Modificar contacto");
            System.out.println("0) Salir");
            Scanner sc = new Scanner(System.in);
            opcion = sc.nextInt();
            switch(opcion){
                case 1:{
                    System.out.println("Listar contactos");
                    FileContactos.listarContactos();
                }
                case 2:{
                    System.out.println("Agregar contacto");
                    FileContactos.agregarContacto();
                }
                case 3:{
                    System.out.println("Modificar contacto");
                    FileContactos.modificarContacto();
                }
                case 0:{
                    System.out.println("Salir");
                }
                default:{
                    System.out.println("Esa opción no es valida");
                }
            }
        }
    }
    
}
