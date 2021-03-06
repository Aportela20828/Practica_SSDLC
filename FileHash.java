package com.mycompany.ssdlc_practica;

import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class FileHash {

    private static File hashUsuarios;
    private static File hashContactos;

    public static File getHashUsuarios() {
        return hashUsuarios;
    }

    public static void setHashUsuarios(File aHashUsuarios) {
        hashUsuarios = aHashUsuarios;
    }

    public static File getHashContactos() {
        return hashContactos;
    }

    public static void setHashContactos(File aHashContactos) {
        hashContactos = aHashContactos;
    }

    
    public static void crearFicheroHashUsuarios() throws IOException, FileNotFoundException, NoSuchAlgorithmException{
    FileReader fr = null;
    FileHash.setHashUsuarios(new File("hashUsuarios.txt"));
    FileHash.getHashUsuarios().createNewFile();
    fr = new FileReader (FileHash.getHashUsuarios());
    BufferedReader br = new BufferedReader(fr);
    if(br.readLine()==null){
        System.out.println("Entran a crear usuarios");
        FileHash.generarFicheroHashUsuarios(FileUsuarios.getArchivo());
    }
    }
    
        public static void crearFicheroHashContactos() throws IOException, FileNotFoundException, NoSuchAlgorithmException{
    FileReader fr = null;
    FileHash.setHashContactos(new File("hashContactos.txt"));
    FileHash.getHashContactos().createNewFile();
    fr = new FileReader (FileHash.getHashContactos());
    BufferedReader br = new BufferedReader(fr);
    if(br.readLine()==null){
        System.out.println("Entran a crear contactos");
        FileHash.generarFicheroHashContactos(FileContactos.getArchivo());
    }
    }
    
    public static String generarHash(String linea) throws NoSuchAlgorithmException{
        MessageDigest md5 =  MessageDigest.getInstance("MD5");
        md5.update(linea.getBytes());
        byte resultado[] = md5.digest();
        StringBuilder sb = new StringBuilder();
        for (int i=0;i< resultado.length;i++){
        sb.append(String.format("%02x", resultado[i]));   
    }
    return sb.toString();
    }
    
    public static void generarFicheroHashUsuarios(File fichero) throws FileNotFoundException, IOException, NoSuchAlgorithmException{
        String linea;
        FileReader fr = null;
        FileWriter fw = null;
        fr = new FileReader (fichero);
        fw = new FileWriter (FileHash.getHashUsuarios(), true);
        PrintWriter pw = new PrintWriter(fw);
        BufferedReader br = new BufferedReader(fr);
        int i = 0;
        System.out.println("Generar hash");
            while(FileUsuarios.getContadorUsuarios()>i){
                linea=br.readLine();
                System.out.println("Linea "+ linea);
                i = i + 1;
                System.out.println("Contador " + i);
                linea = linea.trim();
            if(linea!=null){
                System.out.println("Entra a linea distinto de null");
            String lineaHash = FileHash.generarHash(linea);
                System.out.println("Linea Hash" + lineaHash);
            pw.println(lineaHash);
            }
            }
        pw.close();
        br.close();
    }
    
        public static void generarFicheroHashContactos(File fichero) throws FileNotFoundException, IOException, NoSuchAlgorithmException{
        String linea;
        FileReader fr = null;
        FileWriter fw = null;
        fr = new FileReader (fichero);
        fw = new FileWriter (FileHash.getHashContactos(), true);
        PrintWriter pw = new PrintWriter(fw);
        BufferedReader br = new BufferedReader(fr);
        int i = 1;
            while(FileContactos.getContadorContactos()>=i){
                linea=br.readLine();
                i = i + 1;
                linea = linea.trim();
            if(linea!=null){
            String lineaHash = FileHash.generarHash(linea);
            pw.println(lineaHash);
            }
            }
        pw.close();
        br.close();
    }
    
    public static void compararHashUsuario(File archivo) throws FileNotFoundException, IOException, NoSuchAlgorithmException{
        FileReader hash = null;
        FileReader fichero = null;
        hash = new FileReader (FileHash.getHashUsuarios());
        fichero = new FileReader (archivo);
        BufferedReader brhash = new BufferedReader(hash);
        BufferedReader brfichero = new BufferedReader(fichero);
        String lineaHash, lineaFichero, linea;
        int i = 1;
        while(FileUsuarios.getContadorUsuarios()<=i){
            lineaHash = brhash.readLine();
            lineaFichero = brfichero.readLine();
            i = 1 + 1;
            lineaHash = lineaHash.trim();
            lineaFichero = lineaFichero.trim();
            linea = FileHash.generarHash(lineaFichero);
            if(linea!=lineaHash){
                MenuHash.menuHash(lineaFichero, archivo, FileHash.getHashUsuarios());
            }
            }
        brfichero.close();
        brhash.close();
    }
    
        public static void compararHashContactos(File archivo) throws FileNotFoundException, IOException, NoSuchAlgorithmException{
        FileReader hash = null;
        FileReader fichero = null;
        hash = new FileReader (FileHash.getHashContactos());
        fichero = new FileReader (archivo);
        BufferedReader brhash = new BufferedReader(hash);
        BufferedReader brfichero = new BufferedReader(fichero);
        String lineaHash, lineaFichero, linea;
        int i = 0;
        while(FileContactos.getContadorContactos()<i){
            lineaHash = brhash.readLine();
            lineaFichero = brfichero.readLine();
            i = 1 + 1;
            lineaHash = lineaHash.trim();
            lineaFichero = lineaFichero.trim();
            linea = FileHash.generarHash(lineaFichero);
            if(linea!=lineaHash){
                MenuHash.menuHash(lineaFichero, archivo, FileHash.getHashContactos());
            }
            }
        brfichero.close();
        brhash.close();
    }
    
    public static void eliminarHash(String lineaACambiar, File archivoHash) throws NoSuchAlgorithmException, FileNotFoundException, IOException{
        String lineaHash = FileHash.generarHash(lineaACambiar);
        String lineaFinal = "";
        String linea;
        FileReader hash = null;
        FileWriter fw = null;
        hash = new FileReader (archivoHash);
        fw = new FileWriter (archivoHash, true);
        BufferedReader brhash = new BufferedReader(hash);
        PrintWriter pw = new PrintWriter(fw);
        boolean datoAEliminar = false;
        while((linea=brhash.readLine())!=null){
            if(linea!=null && linea.trim()!=""){
                if(linea.equals(lineaHash)){
            datoAEliminar = true;
            }
            if(datoAEliminar == false){
            lineaFinal = linea + "\n";
            }    
            }
        }
        fw.close();
        if(archivoHash.toString().equals("hashUsuarios.txt")){
            FileHash.nuevoHashUsuarios(lineaFinal);
        }else if(archivoHash.toString().equals("hashContactos.txt")){
            FileHash.nuevoHashContactos(lineaFinal);
        }else{
            System.out.println("Hay un error");
        }
    }
    
    private static void nuevoHashUsuarios(String lineaFinal) throws IOException{
        FileHash.getHashUsuarios().delete();
        File txt2 = new File("hashUsuarios.txt");
	FileWriter fileNew = new FileWriter(txt2);
	BufferedWriter bw = new BufferedWriter(fileNew);
	fileNew.write(lineaFinal);
	fileNew.close();
    }
    
    private static void nuevoHashContactos(String lineaFinal) throws IOException{
        FileHash.getHashContactos().delete();
        File txt2 = new File("hashContactos.txt");
	FileWriter fileNew = new FileWriter(txt2);
	BufferedWriter bw = new BufferedWriter(fileNew);
	fileNew.write(lineaFinal);
	fileNew.close();
    }
    
        public static void clonarHash(File archivoHash, File archivo) throws NoSuchAlgorithmException, FileNotFoundException, IOException{
        String linea;
        File archivoClon = new File("archivoClon.txt");
        archivoClon.createNewFile();
        FileReader fr = null;
        FileWriter fw = null;
        fr = new FileReader (archivo);
        fw = new FileWriter (archivoClon, true);
        PrintWriter pw = new PrintWriter(fw);
        BufferedReader br = new BufferedReader(fr);
            while((linea=br.readLine())!=null){
                linea = linea.trim();
            if(linea!=null){
            String lineaHash = FileHash.generarHash(linea);
            pw.println(lineaHash + "\n");
            }
            }
        if(archivo.toString().equals("Usuario.txt")){
            FileHash.getHashUsuarios().delete();
            File nombreArchivo = new File("hashUsuarios.txt");
            archivoClon.renameTo(nombreArchivo);
            FileHash.setHashUsuarios(archivoClon);
        }else if(archivo.toString().equals("Contactos.txt")){
            FileHash.getHashContactos().delete();
            File nombreArchivo = new File("hashContactos.txt");
            archivoClon.renameTo(nombreArchivo);
            FileHash.setHashContactos(archivoClon);
        }else{
            System.out.println("Hay un error");
        }
        pw.close();
        br.close();
    }
        
        
}

