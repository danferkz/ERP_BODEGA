package FRAMES_VENTANAS;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

public class Personas extends JFrame {

    private JPanel contentPane;
    private JTextField txtNombreProv;
    private JTextField txtNombreClin;

    /*
     
Launch the application.*/
    
  public static void main(String[] args) {
      EventQueue.invokeLater(new Runnable() {
          public void run() {
              try {
                  Personas frame = new Personas();
                  frame.setVisible(true);} catch (Exception e) {
                  e.printStackTrace();}}});}

    /*
     
Create the frame.*/
  
  public Personas() {
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setBounds(100, 100, 450, 300);
      contentPane = new JPanel();
      contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
        tabbedPane.setBounds(0, 6, 450, 266);
        contentPane.add(tabbedPane);

        JPanel Proveedor = new JPanel();
        tabbedPane.addTab("Proveedor", null, Proveedor, null);
        Proveedor.setLayout(null);
        
        JLabel lblNewLabel_1 = new JLabel("Nombre: ");
        lblNewLabel_1.setBounds(10, 11, 46, 14);
        Proveedor.add(lblNewLabel_1);
        
        txtNombreProv = new JTextField();
        txtNombreProv.setBounds(70, 8, 86, 20);
        Proveedor.add(txtNombreProv);
        txtNombreProv.setColumns(10);

        JPanel Clientes = new JPanel();
        tabbedPane.addTab("Clientes", null, Clientes, null);
        Clientes.setLayout(null);
        
        JLabel lblNewLabel = new JLabel("Nombre: ");
        lblNewLabel.setBounds(10, 11, 46, 14);
        Clientes.add(lblNewLabel);
        
        txtNombreClin = new JTextField();
        txtNombreClin.setBounds(66, 8, 86, 20);
        Clientes.add(txtNombreClin);
        txtNombreClin.setColumns(10);
        
        JButton btnBuscarCliente = new JButton("Buscar");
        btnBuscarCliente.setBounds(329, 7, 89, 23);
        Clientes.add(btnBuscarCliente);
    }
}