
package Lanzador;

import Controlador.ManejoArchivo;
import java.io.IOException;

/**
 *
 * @author ALEX
 */
public class Main {
    public static void main (String [] args) throws IOException{
        ManejoArchivo man = new ManejoArchivo("src\\main\\java\\Archivos\\zapatillas.json");
        man.mostrarObjetos();
        man.reemplazarUltimaLinea("src\\main\\java\\Archivos\\zapatillas.json");
        man.agregarObjeto("src\\main\\java\\Archivos\\zapatillas.json","{\"marca\":\"Nike\",\"modelo\":\"AirForce1\",\"color\":\"Black\"}]}");
    }
}
