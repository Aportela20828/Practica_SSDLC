package com.mycompany.ssdlc_practica;


public class Usuario {

    private String usuario;
    private String contraseña;
    private Roles rol;
    
    public Roles getRol() {
        return rol;
    }

    public void setRol(Roles rol) {
        this.rol = rol;
    }
    
    public Usuario(String usuario, String contraseña, String rol){
        this.usuario = usuario;
        this.contraseña = contraseña;
        if(rol.equals("administrador")){
            this.rol = new Admin();
        }else if(rol.equals("gestor")){
            this.rol = new Gestor();
        }else if(rol.equals("asistente")){
            this.rol = new Asistente();
        }
    }       
}
