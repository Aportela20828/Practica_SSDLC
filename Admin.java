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
                    FileContactos.listarContactos(usuario);
                    break;
                }
                case 2:{
                    System.out.println("Agregar contacto");
                try {
                    FileContactos.agregarContacto(usuario);
                } catch (IOException ex) {
                    Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
                }
                case 3:{
                    System.out.println("Modificar contacto");
                    FileContactos.modificarContacto(usuario);
                    break;
                }
                case 4:{
                    System.out.println("Eliminar contacto");
                    FileContactos.eliminarContacto(usuario);
                    break;
                }
                case 5:{
                    System.out.println("Agregar usuario");
                try {
                    FileUsuarios.agregarUsuario();
                } catch (IOException ex) {
                    Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);
                }
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
