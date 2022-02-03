package com.mycompany.ssdlc_practica;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class Principal {
    public static void main(String args[]) throws IOException, NoSuchAlgorithmException{
        Scanner sc = new Scanner(System.in);
        FileUsuarios.crearFicheroUsuarios();
        FileContactos.crearFicheroContactos();
        FileUsuarios.contarUsuarios();
        FileContactos.contarContactos();
        FileHash.crearFicheroHashContactos();
        FileHash.crearFicheroHashUsuarios();
        FileHash.compararHashContactos(FileHash.getHashContactos());
        FileHash.compararHashUsuario(FileHash.getHashUsuarios());
        FileAuditoria.crearFicheroAuditoria();
        System.out.println("Escribame el nombre del usuario.");
        String nombre = sc.nextLine();
        System.out.println("Escribame la contraseña");
        String contraseña = sc.nextLine();
        tipoUsuario userActual = FileUsuarios.buscarUsuario(nombre, contraseña);
        Usuario user;
        if(userActual == null){
            userActual = FileUsuarios.agregarUsuario();
        }
        switch(userActual){
            case administrador:{
                user = new Usuario(nombre, contraseña, userActual.toString()); 
                user.getRol().menu(user);
            }
            case gestor:{
                user = new Usuario(nombre, contraseña, userActual.toString()); 
                user.getRol().menu(user);
            }
            case asistente:{
                user = new Usuario(nombre, contraseña, userActual.toString()); 
                user.getRol().menu(user);
            }
            default:
                System.out.println("Hay algún error");
        }
        FileHash.compararHashContactos(FileHash.getHashContactos());
        FileHash.compararHashUsuario(FileHash.getHashUsuarios());
    }
}
