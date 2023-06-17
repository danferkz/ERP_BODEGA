package FRAMES_VENTANAS;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;

import java.awt.Color;
import java.awt.Font;



public class Personas extends JFrame {

    private JPanel contentPane;
    private JTextField txtNombreProv;

    private JTextField txtNombreClin;
    private JTextField textField;
    private JTextField textField_1;
    private JTextField txtNombreCliente;
    private JTextField txtDNICliente;


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
        tabbedPane.addTab("Proveedores", null, Proveedor, null);
        Proveedor.setLayout(null);
        
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(214, 2, 213, 162);
        Proveedor.add(scrollPane);
        
        JButton btnAgregar = new JButton("Agregar");
        btnAgregar.setBounds(14, 176, 117, 29);
        Proveedor.add(btnAgregar);
        
        JButton btnListar = new JButton("Listar");
        btnListar.setBounds(150, 174, 117, 29);
        Proveedor.add(btnListar);
        
        JButton btnEliminar = new JButton("Eliminar");
        btnEliminar.setBounds(291, 174, 117, 29);
        Proveedor.add(btnEliminar);
        
        JLabel lblNombre = new JLabel("Nombre: ");
        lblNombre.setBounds(10, 11, 98, 14);
        Proveedor.add(lblNombre);
        
        txtNombreProv = new JTextField();
        txtNombreProv.setBounds(92, 8, 112, 20);
        Proveedor.add(txtNombreProv);
        txtNombreProv.setColumns(10);
        
        JLabel lblDirección = new JLabel("Dirección:");
        lblDirección.setBounds(10, 44, 70, 16);
        Proveedor.add(lblDirección);
        
        JLabel lblRUC = new JLabel("RUC:");
        lblRUC.setBounds(11, 76, 61, 16);
        Proveedor.add(lblRUC);
        
        textField = new JTextField();
        textField.setBounds(92, 40, 110, 26);
        Proveedor.add(textField);
        textField.setColumns(10);
        
        textField_1 = new JTextField();
        textField_1.setBounds(91, 78, 116, 26);
        Proveedor.add(textField_1);
        textField_1.setColumns(10);

        JPanel Clientes = new JPanel();
        tabbedPane.addTab("Clientes", null, Clientes, null);
        Clientes.setLayout(null);
        
        JLabel lblNewLabel = new JLabel("Nombre: ");
        lblNewLabel.setBounds(10, 11, 79, 14);
        Clientes.add(lblNewLabel);
        
        txtNombreCliente = new JTextField();
        txtNombreCliente.setBounds(85, 8, 86, 20);
        Clientes.add(txtNombreCliente);
        txtNombreCliente.setColumns(10);
        
        JButton btnBuscarCliente = new JButton("Buscar");
        btnBuscarCliente.setForeground(new Color(51, 0, 255));
        btnBuscarCliente.setFont(new Font("Tahoma", Font.BOLD, 13));
        btnBuscarCliente.setBackground(new Color(51, 102, 204));
        btnBuscarCliente.setBounds(329, 7, 89, 23);
        Clientes.add(btnBuscarCliente);
        
        JLabel lblNewLabel_2 = new JLabel("DNI:");
        lblNewLabel_2.setBounds(183, 11, 46, 14);
        Clientes.add(lblNewLabel_2);
        
        txtDNICliente = new JTextField();
        txtDNICliente.setColumns(10);
        txtDNICliente.setBounds(214, 8, 86, 20);
        Clientes.add(txtDNICliente);
        
        JScrollPane scrollPane1 = new JScrollPane();
        scrollPane1.setBounds(20, 46, 398, 159);
        Clientes.add(scrollPane1);
    }
} 