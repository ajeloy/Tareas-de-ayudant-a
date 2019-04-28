

import Controlador.InteraccionPoleraTexto;
import java.io.IOException;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 *
 * @author ALEX
 */
public class testInteraccion {
    
    InteraccionPoleraTexto inter;
    InteraccionPoleraTexto inter2;
    
    @Before
    public void setUp() throws IOException{
        inter = new InteraccionPoleraTexto();
        inter2 = new InteraccionPoleraTexto("src\\main\\java\\Archivos\\listado.csv", "algodon,XL,false");
    }
    
    /*
    Se pone a prueba el método contarLineasTexto usando una dirección que no corresponde a ningún archivo.
    */
    @Test
    public void testContarLineasTexto() throws IOException{
        int resultadoObtenido = inter.contarLineasTexto("nada.txt");
        int resultadoEsperado = -1;
        assertEquals(resultadoEsperado,resultadoObtenido);
    }
    
    /*
    Se pone a pueba el método leerLineasTexto introduciendo como parámetros una dirección inválida y una longitud erronea para un arreglo.
    */
    @Test
    public void testLeerLineasTexto() throws IOException{
        boolean resultadoObtenido = inter.leerLineasTexto("nada.txt", -3);
        boolean resultadoEsperado = false;
        assertEquals(resultadoEsperado,resultadoObtenido);
    }
    
    /*
    Se pone a pueba el método separarLineas usando como parámetro una posición inexistente para un arreglo.
    */
    @Test
    public void testSepararLineas(){
        String [] resultadoObtenido = inter2.separarLineas(-4);
        String [] resultadoEsperado = {"nada", "nada", "nada"};
        assertEquals(resultadoEsperado,resultadoObtenido);
    }
    
    /*
    Se pone a pueba el método añadirObjeto usando como parámetros de entrada la dirección de un archivo inexistente.
    */
    @Test
    public void testAñadirObjeto() throws IOException{
        boolean resultadoObtenido = inter.añadirObjeto("noExisto.txt", "Esto no se agregará xd");
        boolean resultadoEsperado = false;
        assertEquals(resultadoEsperado,resultadoObtenido);
    }
}
