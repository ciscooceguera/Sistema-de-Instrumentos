import java.util.ArrayList;

public class Instrumento {
    private String cita, nombre;
    private String utilidad; // Manejar o identificar
    private String condicion; // Ansiedad o estrés
    private String tipo; // Test, Cuestionario o escala
    private int clave, confiabilidad;
    private ArrayList<String> autores;
    public Instrumento(String cita, String nombre, String utilidad, String condicion, String tipo, int confiabilidad, int clave,
                       ArrayList<String> autores) {
        this.cita = cita;
        this.nombre = nombre;
        this.utilidad = utilidad;
        this.condicion = condicion;
        this.tipo = tipo;
        this.confiabilidad = confiabilidad;
        this.clave = clave;
        this.autores = autores;
    }
    public String getPrimerAutor() {
        return autores.getFirst();
    }
    public String getCondicion(){
        return condicion;
    }
    public String getTipo(){
        return tipo;
    }
    public String getUtilidad(){
        return utilidad;
    }
    public ArrayList<String> getAutores(){
        return autores;
    }
    public int getClave(){
        return clave;
    }
    public void setClave(int clave){
        this.clave = clave;
    }
    public String toString(){
        String instrumentoString = "";
        return instrumentoString;
    }

}
