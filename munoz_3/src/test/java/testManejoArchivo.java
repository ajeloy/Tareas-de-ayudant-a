
import Controlador.ManejoArchivo;
import java.io.IOException;
import org.junit.Assert;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 *
 * @author ALEX
 */
public class testManejoArchivo {
    
    ManejoArchivo man;
    
    @Before
    public void setUp() throws IOException{
        man = new ManejoArchivo("src\\main\\java\\Archivos\\zapatillas.json");
    }
    
    /*
    Se quieren establecer la cantidad de carácteres innecesarios para una posición que no existe en el contexto del problema. 
    */
    @Test
    public void testEstablecerCaractInnecesarios(){
        int [] resultadoConseguido = man.establecerCaracteresInnecesarios(4);
        int [] resultadoEsperado = new int[]{-1,-1};
        Assert.assertArrayEquals(resultadoEsperado,resultadoConseguido);
    }
    
    /*
    Se quieren contar los carácteres de una oración en blanco.
    */
    @Test
    public void testContarCaractOracion(){
        int resultadoConseguido = man.contarCaracteresOracion("");
        int resultadoEsperado = 0;
        assertEquals(resultadoEsperado,resultadoConseguido);
    }
    
    /*
    Se quiere agregar un línea a un atexto que no existe.
    */
    @Test
    public void testAgregarObjeto() throws IOException{
        boolean resultadoConseguido = man.agregarObjeto("noExisto.txt","Esto no se agregará");
        boolean resultadoEsperado = false;
        assertEquals(resultadoEsperado,resultadoConseguido);
    }
    
    /*
    Se quiere reemplazar la última línea de un texto que no existe.
    */
    @Test
    public void testReemplazarUltimaLinea() throws IOException{
        boolean resultadoConseguido = man.reemplazarUltimaLinea("noExisto.txt");
        boolean resultadoEsperado = false;
        assertEquals(resultadoEsperado,resultadoConseguido);
    }
    
    /*
    Se quiere almacenar las líneas de un texto que no existe.
    */
    @Test
    public void testAlmacenarLineasTexto() throws IOException{
        boolean resultadoConseguido = man.almacenarLineasTexto("noExisto.txt");
        boolean resultadoEsperado = false;
        assertEquals(resultadoEsperado,resultadoConseguido);
    }
    
    /*
    Se quiere depurar una arreglo de String que se encuentra en una posición nula para un ArrayList.
    */
    @Test
    public void testExtraerDatosLinea(){
        String[] resultadoConseguido = man.extraerDatosLinea(-1);
        String[] resultadoEsperado = {null,null,null};
        Assert.assertArrayEquals(resultadoEsperado,resultadoConseguido);
    }
}
