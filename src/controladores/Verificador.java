/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

/**
 *
 * @author Gomin47
 */
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.Signature;
import java.security.spec.X509EncodedKeySpec;

public class Verificador {

    public boolean validarFirma(String rutaArchivo, String rutaFirma, String rutaClavePublica) throws Exception {
        byte[] datos = leerArchivo(rutaArchivo);
        byte[] firma = leerArchivo(rutaFirma);
        PublicKey clavePublica = leerClavePublicaDesdeArchivo(rutaClavePublica);
        Signature signature = Signature.getInstance("SHA1withRSA");
        signature.initVerify(clavePublica);
        signature.update(datos);

        return signature.verify(firma);
    }

    public byte[] leerArchivo(String ruta) throws Exception {
        return Files.readAllBytes(Paths.get(ruta));
    }

    public PublicKey leerClavePublicaDesdeArchivo(String ruta) throws Exception {
        byte[] keyBytes = leerArchivo(ruta);
        X509EncodedKeySpec spec = new X509EncodedKeySpec(keyBytes);
        KeyFactory kf = KeyFactory.getInstance("RSA");
        return kf.generatePublic(spec);
    }
}
