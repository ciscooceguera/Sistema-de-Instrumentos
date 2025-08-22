import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Scanner;

public class Sistema {
    private JFrame ventana;
    private JButton agregar, eliminar, consulta, salir;
    private Control control;
    public Sistema(){
        control = new Control();
    }
    public void iniciarFrame(){
        ventana = new JFrame();
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setTitle("Sistema Integral de Instrumentos");
        ventana.setSize(600,600);
        ventana.setResizable(false);
        ventana.setLayout(new GridLayout(4,1, 0,10));
        ventana.setLocationRelativeTo(null);

        agregar = new JButton("Agregar");
        agregar.setSize(200,50);
        agregar.setBackground(new Color(70,130,180));
        agregar.setForeground(Color.WHITE);
        agregar.setFocusPainted(false);
        agregar.setFont(new Font("Arial",Font.BOLD,15));
        eliminar = new JButton("Eliminar");
        eliminar.setSize(200,50);
        eliminar.setBackground(new Color(70,130,180));
        eliminar.setForeground(Color.WHITE);
        eliminar.setFocusPainted(false);
        eliminar.setFont(new Font("Arial",Font.BOLD,15));
        consulta = new JButton("Consultar");
        consulta.setSize(200,50);
        consulta.setBackground(new Color(70,130,180));
        consulta.setForeground(Color.WHITE);
        consulta.setFocusPainted(false);
        consulta.setFont(new Font("Arial",Font.BOLD,15));
        salir = new JButton("Salir");
        salir.setSize(200,50);
        salir.setBackground(new Color(70,130,180));
        salir.setForeground(Color.WHITE);
        salir.setFocusPainted(false);
        salir.setFont(new Font("Arial",Font.BOLD,15));

        agregar.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                agregarInstrumento();
            }
        });
        eliminar.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                eliminarInstrumento();
            }
        });
        consulta.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                consultar();
            }
        });
        salir.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                System.exit(0);
            }
        });

        ventana.add(agregar);
        ventana.add(eliminar);
        ventana.add(consulta);
        ventana.add(salir);

        ventana.setVisible(true);
    }
    public void agregarInstrumento(){
        control.altas(llenarInstrumento());
    }
    public Instrumento llenarInstrumento(){
        String nombre = JOptionPane.showInputDialog("Ingrese el nombre del instrumento: ");
        String cita = JOptionPane.showInputDialog("Ingrese la cita: ");
        Object[] opcionUtilidad = {"Manejar","Identificar"};
        int utilidadInt = JOptionPane.showOptionDialog(
                null,
                "Seleccione la utilidad","Utilidad",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                opcionUtilidad,
                opcionUtilidad[0]);
        String utilidad;
        if (utilidadInt == 0){
            utilidad = "Manejar";
        }else{
            utilidad = "Identificar";
        }
        Object[] opcionCondicion = {"Ansiedad","Estrés","Ambos"};
        int condicionInt = JOptionPane.showOptionDialog(
                null,
                "Seleccione la condición","Condición",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                opcionCondicion,
                opcionCondicion[0]);
        String condicion;
        if (condicionInt == 0){
            condicion = "Ansiedad";
        }else {
            condicion = "Estrés";
        }
        Object[] opcionTipo = {"Test","Cuestionario","Escala"};
        Object tipoInt = JOptionPane.showInputDialog(null,
                "Seleccione el tipo", "Tipo",
                JOptionPane.INFORMATION_MESSAGE,
                null,
                opcionTipo,
                opcionTipo[1]);
        String tipo = tipoInt.toString();
        int numAutores = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el número de autores: "));
        ArrayList<String> autores = new ArrayList<>();
        for (int i = 0; i < numAutores; i++){
            autores.add(JOptionPane.showInputDialog("Ingrese el nombre de autor: "));
        }
        int confiabilidad = JOptionPane.showConfirmDialog(null,
                "Es confiable?",
                "Confiabilidad",
                JOptionPane.YES_NO_OPTION);
        if (confiabilidad == 0){
            confiabilidad = 1;
        }else{
            confiabilidad = 0;
        }
        return new Instrumento(cita,nombre,utilidad,condicion,tipo,confiabilidad,0,autores);
    }
    public Instrumento eliminarInstrumento(){
        int clave = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la clave del instrumento: "));
        Instrumento instrumento = control.encontrarPorClave(clave);
        if (instrumento != null){
            control.eliminar(instrumento);
        }else{
            JOptionPane.showMessageDialog(null, "El instrumento no existe");
        }
        return instrumento;
    }
    public void consultar(){
        Object[] opcConsulta = {"por condición", "por tipo","por utilidad","por autor","por clave",
                "consulta general ordenados por clave","consulta general ordenados por primer autor"};
        String consulta = JOptionPane.showInputDialog(null,
                "Seleccione el tipo de consulta",
                "Menú consulta",
                JOptionPane.INFORMATION_MESSAGE,
                null,
                opcConsulta,
                opcConsulta[0]).toString();
        String mensaje = "";
        switch (consulta){
            case "por condición":
                Object[] opcionCondicion = {"Ansiedad","Estrés"};
                int condicionInt = JOptionPane.showOptionDialog(
                        null,
                        "Seleccione la condición","Condición",
                        JOptionPane.DEFAULT_OPTION,
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        opcionCondicion,
                        opcionCondicion[0]);
                String condicion = "";
                if (condicionInt == 0){
                    condicion = "Ansiedad";
                }else {
                    condicion = "Estrés";
                }
                mensaje = control.consultarPorCondicion(condicion);
                break;
            case "por tipo":
                Object[] opcionTipo = {"Test","Cuestionario","Escala"};
                int tipoInt = JOptionPane.showOptionDialog(
                        null,
                        "Selecciona el tipo","Tipo",
                        JOptionPane.DEFAULT_OPTION,
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        opcionTipo,
                        opcionTipo[0]);
                String tipo = "";
                switch (tipoInt){
                    case 0:
                        tipo = "Test";
                        break;
                    case 1:
                        tipo = "Cuestionario";
                        break;
                    case 2:
                        tipo = "Escala";
                        break;
                }
                mensaje = control.consultarPorTipo(tipo);
                break;
            case "por utilidad":
                Object[] opcionUtilidad = {"Manejar","Identificar"};
                int utilidadInt = JOptionPane.showOptionDialog(
                        null,
                        "Selecciona la utilidad","Utilidad",
                        JOptionPane.DEFAULT_OPTION,
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        opcionUtilidad,opcionUtilidad[0]);
                String utilidad = "";
                if (utilidadInt == 0){
                    utilidad = "Manejar";
                }else{
                    utilidad = "Identificar";
                }
                mensaje = control.consultarPorUtilidad(utilidad);
                break;
            case "por autor":
                String nombreAutor = JOptionPane.showInputDialog("Ingrese el nombre de autor: ");
                mensaje = control.consultarPorAutor(nombreAutor);
                break;
            case "por clave":
                int clave = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la clave del instrumento: "));
                mensaje = control.consultarPorClave(clave);
                break;
            case "consulta general ordenados por clave":
                control.ordenarPorClave();
                mensaje = control.consultarTodos();
                break;
            case "consulta general ordenados por primer autor":
                control.ordenarPorPrimerAutor();
                mensaje = control.consultarTodos();
                break;
        }
        if (!mensaje.equals("")) {
            JOptionPane.showMessageDialog(null,
                    "Instrumentos consultados: " + mensaje, "Consulta,",
                    JOptionPane.INFORMATION_MESSAGE);
        }else{
            JOptionPane.showMessageDialog(null,
                    "No hubo coincidencias " + mensaje, "Consulta",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
