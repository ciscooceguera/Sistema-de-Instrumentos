import java.util.ArrayList;
public class Control {
    private ArrayList<Instrumento> instrumentos;
    public Control(){
        instrumentos = new ArrayList<>();
    }
    public void altas(Instrumento instrumento){
        instrumento.setClave(instrumentos.size());
        instrumentos.add(instrumento);
    }
    public Instrumento eliminar(Instrumento instrumento){
        instrumentos.remove(instrumento);
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
    public String consultaPorUtilidad(String utilidad){
        String mensajeConsulta = "";
        for (Instrumento instrumento : instrumentos) {
            if(instrumento.getUtilidad().equals(utilidad)){
                mensajeConsulta += instrumento + "\n";
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
        instrumentos.sort((o1, o2) ->
                o1.getClave());
    }
}
