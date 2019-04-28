
package Controlador;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author ALEX
 */
public class ManejoArchivo {
    private ArrayList<Zapatilla> zapatillas = new ArrayList<Zapatilla>();
    private ArrayList<String[]> lineasExtraidas = new ArrayList<String[]>();
    
    /*
    Constructor que se encarga de crear los objetos a partir del texto.
    @param ubicación relativa del texto.
    */
    public ManejoArchivo(String ubicacionTexto) throws IOException{
        boolean validador = almacenarLineasTexto(ubicacionTexto);
        if (validador){
            crearObjetos();
        }
    }
    
     /*
    Almacena cada línea del texto en un arreglo de String, que corresponde a la línea separada por el caracter ','.
    @param direccion relativa del texto en cuestión.
    @return booleano que indica la existencia del texto.
    */
    public boolean almacenarLineasTexto(String ubicacionTexto) throws FileNotFoundException, IOException{
        File file = new File (ubicacionTexto);
            if (file.exists()){
                String cadena;
                FileReader fr = new FileReader(ubicacionTexto);
                BufferedReader br = new BufferedReader(fr);  
                while((cadena=br.readLine()) != null){
                    String [] arreglo = cadena.split(",");
                    lineasExtraidas.add(arreglo);
                }
                fr.close();
                br.close();
                return true;
            }
            else{
                return false;
            }
    }
    
    /*
    Muestra los objetos ya creados con sus respectivos atributos.
    @return void
    */
    public void mostrarObjetos(){
        for (int i = 0; i < zapatillas.size(); i++){
            System.out.println((i+1)+"° Zaptilla: ");
            System.out.println("Marca: "+zapatillas.get(i).getMarca());
            System.out.println("Modelo: "+zapatillas.get(i).getModelo());
            System.out.println("Color: "+zapatillas.get(i).getColor());
            System.out.println();
        }
    }
    
    /*
    Se deshace de los carácteres innecesarios de cada elemento del arreglo de String seleccionado.
    @param posición del arreglo de String en el ArrayList de líneas.
    @return arreglo de String con cada elemento ya depurado.
    */
    public String[] extraerDatosLinea(int numObjeto){
        try{
            String [] datosExtraidos = new String[3];
            for (int i = 0; i < lineasExtraidas.get(numObjeto).length; i++){
                String oracion = lineasExtraidas.get(numObjeto)[i];
                int [] caracteresInnecesarios = establecerCaracteresInnecesarios(i);
                if (numObjeto == 0 && i == 0){
                    caracteresInnecesarios[0] = caracteresInnecesarios[0]  + 17;
                }
                else if(numObjeto == 6 && i == 2){
                    caracteresInnecesarios[1] = caracteresInnecesarios[1] +2;
                }
                String nuevaOracion = oracion.substring(caracteresInnecesarios[0]);
                String oracionFinal = nuevaOracion.substring(0,nuevaOracion.length()- caracteresInnecesarios[1]);
                datosExtraidos[i] = oracionFinal;
            }
            return datosExtraidos;
        }catch (Exception e){
            System.out.println(e);
            System.out.println("Introduzca la posición de un arreglo existente.");
            String [] datosNulos = {null, null,null};
            return datosNulos;
        }
    }
    
    /*
    Cuenta los carácteres de un String.
    @param String en cuestión.
    @return cantidad de carácteres del String.
    */
    public int contarCaracteresOracion(String oracion){
        return oracion.length();
    }
    
    /*
    Establece los carácteres innecesarios de cada oración en el arreglo de String.
    @param posición de la oración en el arreglo de String.
    @return 2 valores que corresponden a los carácteres innecesarios antes y después del valor importante.
    */
    public int[] establecerCaracteresInnecesarios(int posicion){
        if (posicion== 0 || posicion==1){
            int [] caracteres = new int[]{10,1};
            return caracteres;
        }
        else if (posicion == 2){
            int [] caracteres = new int[]{9,2};
            return caracteres;
        }
        else{
            int [] caracteres = new int[]{-1,-1};
            return caracteres;
        }
    }
    
    /*
    Crea los objetos con los datos extraídos del arreglo de String.
    return void.
    */
    public void crearObjetos (){
        for (int i = 0; i < lineasExtraidas.size(); i++){
            String[] datos = extraerDatosLinea(i);
            zapatillas.add(new Zapatilla(datos[0], datos[1], datos[2]));
        }
    }
    
    /*
    Agrega una línea al final del texto con su respectivo formato.
    @param ubicación relativa del texto en cuestión, texto para agregar.
    @return booleano que indica la existencia del texto.
    */
    public boolean agregarObjeto(String ubicacionTexto, String textoParaAgregar) throws IOException{
        File file = new File(ubicacionTexto);
        if(file.exists()){
            FileWriter fw = new FileWriter(file.getAbsoluteFile(), true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.newLine();
            bw.write(textoParaAgregar);
            bw.close();
            fw.close();
            return true;
        }
        else{
            return false;
        }
    }
    
    /*
    Reemplaza la última línea del texto para que quede con el formato de la penúltima línea.
    @param ubicación relativa del texto.
    @return booleano que indica la existencia del texto.
    */
    public boolean reemplazarUltimaLinea(String ubicacionTexto) throws FileNotFoundException, IOException{
        File file = new File(ubicacionTexto);
        if(file.exists()){
            FileWriter fw = new FileWriter(file.getAbsoluteFile(), true);
            BufferedWriter bw = new BufferedWriter(fw);
            BufferedReader br = new BufferedReader(new FileReader(ubicacionTexto));
            String linea;
            String nuevaLinea = "";
            while((linea = br.readLine())!=null){
                if(linea.contains("]")){
                    nuevaLinea = nuevaLinea +  linea.replaceAll("]}", "");
                }
                else{
                    nuevaLinea = nuevaLinea + linea + "\r\n";
                }
            }
            FileOutputStream fileOut = new FileOutputStream(ubicacionTexto);
            fileOut.write(nuevaLinea.getBytes());
            fileOut.close();
            br.close();
            return true;
        }
        else{
            return false;
        }
    }
}
