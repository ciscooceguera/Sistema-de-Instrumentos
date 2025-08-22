import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class Control {
    private ArrayList<Instrumento> instrumentos;
    public Control(){
        instrumentos = new ArrayList<>();
        recuperarArchivo();
    }
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
    public void altas(Instrumento instrumento){
        int clave = 0;
        for (Instrumento instrumentoIteracion: instrumentos){
            if (clave == instrumento.getClave()){
                clave++;
            }
        }
        instrumento.setClave(clave);
        instrumentos.add(instrumento);
        actualizarArchivo();
    }
    public Instrumento eliminar(Instrumento instrumento){
        instrumentos.remove(instrumento);
        actualizarArchivo();
        return instrumento;
    }
    public ArrayList<Instrumento> getInstrumentos(){
        return instrumentos;
    }
    public Instrumento encontrarPorClave(int clave){
        for (Instrumento instrumento : instrumentos) {
            if(instrumento.getClave() == clave){
                return instrumento;
            }
        }
        return null;
    }
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
    public String consultarPorTipo(String tipo){
        String mensajeInstrumentos = "";
        for (Instrumento instrumento : instrumentos) {
            if(instrumento.getTipo().equals(tipo)){
                mensajeInstrumentos += instrumento + "\n";
            }
        }
        return mensajeInstrumentos;
    }
    public String consultarPorCondicion(String condicion){
        String mensajeConsulta = "";
        for (Instrumento instrumento : instrumentos) {
            if(instrumento.getCondicion().equals(condicion)){
                mensajeConsulta += instrumento + "\n";
            }
        }
        return mensajeConsulta;
    }
    public String consultarPorUtilidad(String utilidad){
        String mensajeConsulta = "";
        for (Instrumento instrumento : instrumentos) {
            if(instrumento.getUtilidad().equals(utilidad)){
                mensajeConsulta += instrumento + "\n";
            }
        }
        return mensajeConsulta;
    }
    public String consultarPorClave(int clave){
        String mensajeConsulta = "";
        for (Instrumento instrumento : instrumentos) {
            if(instrumento.getClave() == clave){
                mensajeConsulta = instrumento + "\n";
            }
        }
        return mensajeConsulta;
    }
    public void ordenarPorPrimerAutor(){
        instrumentos.sort((o1, o2) ->
                o1.getPrimerAutor().compareToIgnoreCase(o2.getPrimerAutor()));
    }
    public String consultarTodos(){
        String mensajeConsulta = "";
        for (Instrumento instrumento : instrumentos) {
            mensajeConsulta += instrumento + "\n";
        }
        return mensajeConsulta;
    }
    public void ordenarPorClave(){
        instrumentos.sort(Comparator.comparingInt(Instrumento::getClave));
    }
}
