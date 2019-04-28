
package Controlador;

/**
 *
 * @author ALEX
 */
public class Polera {
    private String material;
    private String talla;
    private boolean estampado;

    public String getMaterial() {
        return material;
    }

    public String getTalla() {
        return talla;
    }

    /*
    Este método indica si la polera tiene o no estampado.
    @param void.
    @return mensaje escrito que depende del valor boolean del atributo estampado.
    */
    public String isEstampado() {
        if (this.estampado){
            return "Si tiene estampado";
        }
        else{
            return "No tiene estampado";
        }
    }

    public Polera(String material, String talla, boolean estampado) {
        this.material = material;
        this.talla = talla;
        this.estampado = estampado;
    }
    
    
}
