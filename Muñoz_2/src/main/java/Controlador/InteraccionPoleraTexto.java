
package Controlador;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author ALEX
 */
public class InteraccionPoleraTexto {
    private String [] textoExtraido;
    private ArrayList<Polera> poleras = new ArrayList<Polera>();
    
    public InteraccionPoleraTexto(){       
    }
    
    /*
    Constructor que se encarga de ejecutar todos los métodos de manera ordenada.
    @param ubicacion del texto con el que se trabajará, línea que se agregará en el texto al final.
    */
    public InteraccionPoleraTexto(String ubicacionTexto, String textoParaAgregar) throws IOException{
        int numeroDeLineas = contarLineasTexto(ubicacionTexto);
        leerLineasTexto(ubicacionTexto, numeroDeLineas);
        crearObjetos(numeroDeLineas);
        mostrarPoleras();
        añadirObjeto(ubicacionTexto, textoParaAgregar);
    }
    
    /*
    El método contará las líneas del texto exceptuando la primera.
    @param ubicacion del texto.
    @return cantidad de líneas resultantes.
    */
    public int contarLineasTexto(String ubicacionTexto) throws IOException{
        int numeroDeLineas = 0;
        try{
            FileReader fr = new FileReader(ubicacionTexto);
            BufferedReader bf = new BufferedReader(fr);
            while (bf.readLine()!=null) {
                numeroDeLineas++;
            }
            return numeroDeLineas - 1;
        }catch(FileNotFoundException e){
            System.out.println(e);
            System.out.println("El archivo ingresado no existe");
            return -1;
        }
    }
    
    /*
    El método almacena las líneas del texto (excepto la primera) en un arreglo de String.
    @param ubicacion del texto, numero de líneas a almacenar.
    @return confirmación booleana si existe o no el texto en cuestión.
    */
    public boolean leerLineasTexto(String ubicacionTexto, int numeroDeLineas) throws FileNotFoundException, IOException{
        try{
            textoExtraido = new String[numeroDeLineas];
            File file = new File (ubicacionTexto);
            if (file.exists() && numeroDeLineas >= 0){
                FileReader fl = new FileReader(ubicacionTexto);
                BufferedReader br = new BufferedReader(fl);  
                while(br.readLine() != null){
                    for(int i = 0; i < numeroDeLineas; i++){
                        textoExtraido[i] = br.readLine();
                    }
                }
                return true;
            }
            else{
                return false;
            }
        }catch(NegativeArraySizeException e){
            System.out.println(e);
            System.out.println("El número de líneas introducido no puede ser negativo");
            return false;
        }
    }
    
    /*
    Este método crea los objetos poleras asignándole cada uno de sus atributos.
    @param la cantidad de lineas del texto.
    @return void.
    */
    public void crearObjetos(int numeroDeLineas){
        for (int i = 0; i < numeroDeLineas; i++){
            this.poleras.add(new Polera(separarLineas(i)[0], separarLineas(i)[1], Boolean.parseBoolean(separarLineas(i)[2])));
        }
    }
    
    /*
    El método se encarga de separar la línea del texto elegida y almacenada en el arreglo de String, siempre que esté el caracter "," presente.
    @param la posición de la línea del texto. 
    @return particiones resultantes de la línea.
    */
    public String[] separarLineas(int posicion){
        try{
             return textoExtraido[posicion].split(",");
        }catch(ArrayIndexOutOfBoundsException e){
            System.out.println(e);
            System.out.println("La posición introducida debe ser positiva");
            String [] vacio = {"nada", "nada", "nada"};
            return vacio;
        }
    }
    
    /*
    Este método muestra por consola cada polera con sus respectivos atributos.
    @param void.
    @return void.
    */
    public void mostrarPoleras(){
        for(int i = 0; i < this.poleras.size(); i++){
            System.out.println("Polera "+(i+1)+":" );
            System.out.println("Material: "+this.poleras.get(i).getMaterial());
            System.out.println("Talla: "+this.poleras.get(i).getTalla());
            System.out.println(this.poleras.get(i).isEstampado());
            System.out.println();
        }
    }
    
    /*
    El método añade una línea al final del texto.
    @param ubicación del texto, línea a agregar al final del texto.
    @return confirmación booleana si existe o no el texto a escribir.
    */
    public boolean añadirObjeto (String ubicacionTexto, String textoParaAgregar) throws IOException{
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
}
