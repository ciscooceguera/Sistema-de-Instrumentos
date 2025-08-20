import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Scanner;

public class Sistema {
    private JFrame ventana;
    private JButton agregar, eliminar, consulta;
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
        ventana.setLayout(new GridLayout(3,1, 0,10));
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

        agregar.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                llenarInstrumento();
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

        ventana.add(agregar);
        ventana.add(eliminar);
        ventana.add(consulta);

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
        Object[] opcionCondicion = {"Ansiedad","Estrés"};
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
        return new Instrumento(cita,nombre,utilidad,condicion,tipo,confiabilidad,0,autores);
    }

    public Instrumento eliminarInstrumento(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Ingrese la clave: ");
        int clave = sc.nextInt();
        Instrumento instrumento = control.encontrarPorClave(clave);
        if (instrumento!=null){
            control.eliminar(instrumento);
        }
        return instrumento;
    }
    public void consultar(){
        Scanner sc = new Scanner(System.in);
        System.out.println("1. Consultar por condición\n2. Consultar por tipo\n3. Consultar por utilidad" +
                "\n4. Consultar por Autor\n5. Consultar por Clave\nIngrese el tipo de consulta: ");
        int tipo = Integer.parseInt(sc.nextLine());
        String consulta;
        switch (tipo){
            case 1:
                System.out.println("Ingrese la condición: ");
                consulta = sc.nextLine();
                System.out.println(control.consultarPorCondicion(consulta));
                break;
            case 2:
                System.out.println("Ingrese el tipo: ");
                consulta = sc.nextLine();
                System.out.println(control.consultarPorTipo(consulta));
                break;
            case 3:
                System.out.println("Ingrese la utilidad: ");
                consulta = sc.nextLine();
                System.out.println(control.consultaPorUtilidad(consulta));
                break;
            case 4:
                System.out.println("Ingrese un autor: ");
                consulta = sc.nextLine();
                System.out.println(control.consultarPorAutor(consulta));
                break;
            case 5:
                control.ordenarPorClave();
                System.out.println(control.consultarTodos());
                break;
        }
    }
}
