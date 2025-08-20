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
        Scanner sc = new Scanner(System.in);
        System.out.println("Ingrese el nombre del instrumento: ");
        String nombre = sc.nextLine();
        System.out.println("Ingrese la cita: ");
        String cita = sc.nextLine();
        System.out.println("Ingrese la utilidad (MANEJAR/IDENTIFICAR): ");
        String utilidad = sc.nextLine();
        System.out.println("Ingrese la condicion (ANSIEDAD/ESTRES: ");
        String condicion = sc.nextLine();
        System.out.println("Ingrese el tipo (TEST/CUESTIONARIO/ESCALA: ");
        String tipo = sc.nextLine();
        System.out.println("Ingrese el número de autores: ");
        int numAutores = Integer.parseInt(sc.nextLine());
        ArrayList<String> autores = new ArrayList<>();
        for (int i = 0; i < numAutores; i++){
            System.out.println("Ingrese el nombre del autor: ");
            autores.add(sc.nextLine());
        }
        System.out.println("0. NO Confiable\n1. Confiable\nIngrese si es confiable: ");
        int confiabilidad = Integer.parseInt(sc.nextLine());
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
