package com.mycompany.ssdlc_practica;

import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class FileHash {

    private static File hash;

    public static File getHash() {
        return hash;
    }

    public static void setHash(File aHash) {
        hash = aHash;
    }
    
    public static void crearFicheroHash() throws IOException{
    if(FileHash.getHash()==null){
        FileHash.setHash(new File("hash.txt"));
        FileHash.getHash().createNewFile();
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
    
    public static void generarFicheroHash(File fichero) throws FileNotFoundException, IOException, NoSuchAlgorithmException{
        String linea;
        FileReader fr = null;
        FileWriter fw = null;
        fr = new FileReader (fichero);
        fw = new FileWriter (FileHash.getHash(), true);
        PrintWriter pw = new PrintWriter(fw);
        BufferedReader br = new BufferedReader(fr);
            while((linea=br.readLine())!=null){
                linea = linea.trim();
            if(linea!=null){
            String lineaHash = FileHash.generarHash(linea);
            pw.println(lineaHash + "\n");
            }
            }
        pw.close();
        br.close();
    }
    
    public static void compararHash(File archivo) throws FileNotFoundException{
        FileReader hash = null;
        FileReader fichero = null;
        hash = new FileReader (FileHash.getHash());
        fichero = new FileReader (archivo);
        BufferedReader brhash = new BufferedReader(hash);
        BufferedReader brfichero = new BufferedReader(fichero);
        String lineaHash, lineaFichero;
        while((lineaHash = brhash.readLine())!=null && (lineaFichero = brfichero.readLine())!=null){
            lineaHash = lineaHash.trim();
            lineaFichero = lineaFichero.trim();
            if(lineaFichero!=null && lineaHash!=null){
                
            }
            }
        pw.close();
        br.close();
    }
}