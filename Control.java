import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class Control {
    private ArrayList<Instrumento> instrumentos;
    /* El constructor inicializa el ArrayList de instrumentos y llama el
    método encargado de la recuperación de los instrumentos registrados en el archivo txt
     */
    public Control(){
        instrumentos = new ArrayList<>();
        recuperarArchivo();
    }
    /* Lee el archivo línea por línea, hacer un split en un arreglo tipo String
    guardo las posiciones de dicho arreglo en variables de los mismos tipos
    en relación a los atributos de la clase Instrumento (separados por comas), de manera respectiva,
    finalmente guardo los autores (separados por punto y coma),
    el orden en que se ordenan en el vector tipo String es acorde a la manera
    en que los registro en el archivo txt al momento de la captura
     */
    public void recuperarArchivo(){
        File archivo = new File("instrumentos.txt");
        if (!archivo.exists()){
            return;
        }
        try(BufferedReader br = new BufferedReader(new FileReader("instrumentos.txt"))) {
            String linea;
            while ((linea = br.readLine()) != null){
                String[] caracteres = linea.split(",");
                String nombre = caracteres[0];
                int clave = Integer.parseInt(caracteres[1]);
                String cita = caracteres[2];
                String utilidad = caracteres[3];
                String condicion = caracteres[4];
                String tipo = caracteres[5];
                int confiabilidad = Integer.parseInt(caracteres[6]);
                ArrayList<String> autores = new ArrayList<>();
                String[] autoresStr = caracteres[7].split(";");
                autores.addAll(Arrays.asList(autoresStr));
                instrumentos.add(new Instrumento(cita,nombre,utilidad,condicion,tipo,confiabilidad,clave,autores));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    /* Este método se ejecuta al momento de la ejecución del programa
    para registrar en el ArrayList de tipo Instrumento
    todos los intrumentos registrados dentro del archivo
     */
    public void actualizarArchivo(){
        try {
            FileWriter escritor = new FileWriter("instrumentos.txt");
            for (Instrumento instrumento : instrumentos) {
                String autoresString = "";
                for (String autor : instrumento.getAutores()) {
                    autoresString += autor + ";";
                }
                escritor.write(instrumento.getNombre() + "," +
                        instrumento.getClave() + "," +
                        instrumento.getCita() + "," +
                        instrumento.getUtilidad() + "," +
                        instrumento.getCondicion() + "," +
                        instrumento.getTipo() + "," +
                        instrumento.getConfiabilidad() + "," +
                        autoresString + "\n");
            }
            escritor.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    /* Recibe una instancia de la clase Instrumento
    en este método también se genera la clave del instrumento,
    se utiliza un ciclo for() para evaluar que la clase no esté
    repetida y posteriormente se modifica en el objeto para finalmente
    agregarlo al ArrayList y al archivo txt.
     */
    public void altas(Instrumento instrumento){
        int clave = 0;
        ordenarPorClave();
        for (Instrumento instrumentoIteracion: instrumentos){
            if (clave == instrumentoIteracion.getClave()){
                clave++;
            }
        }
        instrumento.setClave(clave);
        instrumentos.add(instrumento);
        actualizarArchivo();
    }
    /* Se encarga de la eliminación de instrumentos,
    recibe el instrumento a eliminar cómo parámetro,
    y una vez eliminado se actualiza el archivo y retorna
    el instrumento mismo.
     */
    public Instrumento eliminar(Instrumento instrumento){
        instrumentos.remove(instrumento);
        actualizarArchivo();
        return instrumento;
    }
    /* Recibe una clave (int) y con un ciclo for() y el ArrayList de instrumentos
    busca un instrumento con dicha clave, si encuentra una coincidencia retorna el
    instrumento, caso contrario retorna null.
     */
    public Instrumento encontrarPorClave(int clave){
        for (Instrumento instrumento : instrumentos) {
            if(instrumento.getClave() == clave){
                return instrumento;
            }
        }
        return null;
    }
    /* Recibe un autor (String) y con un ciclo for() itera
    los instrumentos en el ArrayList, y concatena en una variable String
    todos los instrumentos con coincidencias, retorna una cadena String.
     */
    public String consultarPorAutor(String autor){
        String mensajeConsulta = "";
        for (Instrumento instrumento : instrumentos) {
            ArrayList<String> autores = instrumento.getAutores();
            if(autores.contains(autor)){
                mensajeConsulta += instrumento + "\n";
            }
        }
        return mensajeConsulta;
    }
    /* Consulta por tipo, recibe el tipo (String) como parámetro,
    posteriormente por medio de un ciclo for itera los instrumentos
    contenidos en el ArrayList, y concatena las coincidencias.
     */
    public String consultarPorTipo(String tipo){
        String mensajeInstrumentos = "";
        for (Instrumento instrumento : instrumentos) {
            if(instrumento.getTipo().equals(tipo)){
                mensajeInstrumentos += instrumento + "\n";
            }
        }
        return mensajeInstrumentos;
    }
    /* Consulta por condición, recibe la condición (String) como parámetro,
    posteriormente por medio de un ciclo for itera los instrumentos
    contenidos en el ArrayList, y concatena las coincidencias.
     */
    public String consultarPorCondicion(String condicion){
        String mensajeConsulta = "";
        for (Instrumento instrumento : instrumentos) {
            if(instrumento.getCondicion().equals(condicion)){
                mensajeConsulta += instrumento + "\n";
            }
        }
        return mensajeConsulta;
    }
    /* Consulta por utilidad, recibe la utilidad (String) como parámetro,
    posteriormente por medio de un ciclo for itera los instrumentos
    contenidos en el ArrayList, y concatena las coincidencias.
     */
    public String consultarPorUtilidad(String utilidad){
        String mensajeConsulta = "";
        for (Instrumento instrumento : instrumentos) {
            if(instrumento.getUtilidad().equals(utilidad)){
                mensajeConsulta += instrumento + "\n";
            }
        }
        return mensajeConsulta;
    }
    /* Consulta por clave, recibe la clave (int) como parámetro,
    posteriormente por medio de un ciclo for itera los instrumentos
    contenidos en el ArrayList, y concatena las coincidencias.
     */
    public String consultarPorClave(int clave){
        String mensajeConsulta = "";
        for (Instrumento instrumento : instrumentos) {
            if(instrumento.getClave() == clave){
                mensajeConsulta = instrumento + "\n";
            }
        }
        return mensajeConsulta;
    }
    /* Ordena el ArrayList de instrumentos alfabéticamente de acuerdo a su primer autor,
    sort recibe un Comparator, o1 y o2 son los parámetros del Comparator, que corresponden
    a un instrumento cada uno.
     */
    public void ordenarPorPrimerAutor(){
        instrumentos.sort((o1, o2) ->
                o1.getPrimerAutor().compareToIgnoreCase(o2.getPrimerAutor()));
    }
    // Es una consulta general donde concatena todos los instrumentos registrados
    public String consultarTodos(){
        String mensajeConsulta = "";
        for (Instrumento instrumento : instrumentos) {
            mensajeConsulta += instrumento + "\n";
        }
        return mensajeConsulta;
    }
    // Ordena el ArrayList de instrumentos por orden de la clave
    public void ordenarPorClave(){
        instrumentos.sort(Comparator.comparingInt(Instrumento::getClave));
    }
}
