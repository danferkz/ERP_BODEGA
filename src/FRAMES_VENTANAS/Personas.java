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
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Personas extends JFrame {//asdagfaghagag

    private JPanel contentPane;
    private JTextField txtNombreProv;
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
        lblNewLabel.setBounds(46, 11, 46, 14);
        Clientes.add(lblNewLabel);
        
        txtNombreCliente = new JTextField();
        txtNombreCliente.setBounds(102, 8, 86, 20);
        Clientes.add(txtNombreCliente);
        txtNombreCliente.setColumns(10);
        
        JButton btnBuscarCliente = new JButton("Buscar");
        btnBuscarCliente.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		//Aqui va la fuincion buscar
        	}
        });
        btnBuscarCliente.setForeground(new Color(51, 0, 255));
        btnBuscarCliente.setFont(new Font("Tahoma", Font.BOLD, 13));
        btnBuscarCliente.setBackground(new Color(255, 255, 255));
        btnBuscarCliente.setBounds(329, 6, 89, 23);
        Clientes.add(btnBuscarCliente);
        
        JLabel lblNewLabel_2 = new JLabel("DNI:");
        lblNewLabel_2.setBounds(196, 11, 46, 14);
        Clientes.add(lblNewLabel_2);
        
        txtDNICliente = new JTextField();
        txtDNICliente.setColumns(10);
        txtDNICliente.setBounds(233, 8, 86, 20);
        Clientes.add(txtDNICliente);
        
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(20, 46, 398, 159);
        Clientes.add(scrollPane);
    }
}