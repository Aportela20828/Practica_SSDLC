package com.mycompany.ssdlc_practica;

import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Admin extends Roles{
    @Override
    public void menu(Usuario usuario){
        int opcion = 6;
        while(opcion != 0){
            System.out.println("Escribame la opción");
            System.out.println("1) Listar contactos");
            System.out.println("2) Agregar contacto");
            System.out.println("3) Modificar contacto");
            System.out.println("4) Eliminar contacto");
            System.out.println("5) Agregar usuario");
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
                case 4:{
                    System.out.println("Eliminar contacto");
                    FileContactos.eliminarContacto();
                }
                case 5:{
                    try {
                        System.out.println("Modificar contacto");
                        FileUsuarios.agregarUsuario();
                    } catch (IOException ex) {
                        Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);
                    }
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
