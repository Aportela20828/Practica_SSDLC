package com.mycompany.ssdlc_practica;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

public class FileAuditoria {

    private static File fileAuditoria;

    public static File getFileAuditoria() {
        return fileAuditoria;
    }

    public static void setFileAuditoria(File aFileAuditoria) {
        fileAuditoria = aFileAuditoria;
    }
    
    public static void crearFicheroAuditoria() throws IOException{
        if(FileAuditoria.getFileAuditoria()==null){
            FileAuditoria.setFileAuditoria(new File("auditoria.txt"));
            FileAuditoria.getFileAuditoria().createNewFile();
        }
    }
    
    public static void lineaAuditoria(String categoria, String accion, boolean resultado, File objeto, Usuario actor) throws IOException{
        FileWriter fw = null;
        fw = new FileWriter (FileAuditoria.getFileAuditoria(), true);
        PrintWriter pw = new PrintWriter(fw);
        Date fecha_hora = new Date();
        int fecha = fecha_hora.getDay();
        int hora = fecha_hora.getHours();
        String resul;
        if(resultado==true){
            resul = "exito";
        }else{
            resul = "fracaso";
        }
        pw.println("Fecha: "+ fecha + ", Hora: "+ hora +", Categoría: "+ categoria +", Accion: "+ accion +", Resultado: "+ resul + ", Usuario: "+ objeto.toString() +", Actor: "+ actor.toString()+".\n");
        fw.close();
    }
}
