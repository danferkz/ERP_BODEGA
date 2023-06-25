package FRAMES_VENTANAS;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Comparator;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class I_Personas extends JInternalFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
    private JTextField textNombre;
    private JTextField textDireccion;
    private JTextField textRUC;
    private JTextField txtDNICliente;
    private JTextField txtNombreCliente;
    private JTable table_Proveedores;
    private int selectedRowIndex = -1;


    /*
     
Launch the application.*/
    
  public static void main(String[] args) {
      EventQueue.invokeLater(new Runnable() {
          public void run() {
              try {
            	  I_Personas frame = new I_Personas();
                  frame.setVisible(true);} catch (Exception e) {
                  e.printStackTrace();}}});}

    /*
     
Create the frame.*/
 
  public I_Personas() {
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setBounds(100, 100, 1869, 1075);
      contentPane = new JPanel();
      contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
        tabbedPane.setBounds(0, 6, 1797, 994);
        contentPane.add(tabbedPane);

        JPanel Proveedor = new JPanel();
        tabbedPane.addTab("Proveedores", null, Proveedor, null);
        Proveedor.setLayout(null);
        table_Proveedores = new JTable();
        table_Proveedores.setModel(new DefaultTableModel(
                new Object[][]{},
                new String[]{"Nombre", "Direccion", "RUC"}
        ));
        table_Proveedores.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollPane_Proveedores = new JScrollPane();
        scrollPane_Proveedores.setBounds(499, 10, 1249, 913);
        Proveedor.add(scrollPane_Proveedores);
        table_Proveedores.getSelectionModel().addListSelectionListener(e -> {
            if (table_Proveedores.getSelectedRow() != -1) {
                selectedRowIndex = table_Proveedores.getSelectedRow();
                DefaultTableModel model = (DefaultTableModel) table_Proveedores.getModel();
                textNombre.setText(model.getValueAt(selectedRowIndex, 0).toString());
                textDireccion.setText(model.getValueAt(selectedRowIndex, 1).toString());
                textRUC.setText(model.getValueAt(selectedRowIndex, 2).toString());
            }
        });
        
        scrollPane_Proveedores.setViewportView(table_Proveedores);
        
        JLabel lblNombre = new JLabel("Nombre: ");
        lblNombre.setBounds(21, 170, 98, 14);
        Proveedor.add(lblNombre);
        
        textNombre = new JTextField();
        textNombre.setBounds(92, 163, 116, 29);
        Proveedor.add(textNombre);
        textNombre.setColumns(10);
        
        JLabel lblDirección = new JLabel("Dirección:");
        lblDirección.setBounds(21, 208, 70, 16);
        Proveedor.add(lblDirección);
        
        textDireccion = new JTextField();
        textDireccion.setBounds(92, 198, 116, 26);
        Proveedor.add(textDireccion);
        textDireccion.setColumns(10);
        
        JLabel lblRUC = new JLabel("RUC:");
        lblRUC.setBounds(22, 240, 61, 16);
        Proveedor.add(lblRUC);
        
        textRUC = new JTextField();
        textRUC.setBounds(92, 235, 116, 26);
        Proveedor.add(textRUC);
        textRUC.setColumns(10);
        
        JButton btnAgregar = new JButton("Agregar");
        btnAgregar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
        		 String name = textNombre.getText();
        	        String direccion = textDireccion.getText();
        	        String rucText = textRUC.getText();

        	        // Validación de solo letras en el nombre y dirección
        	        if (!name.matches("[a-zA-Z]+")) {
        	            JOptionPane.showMessageDialog(null, "El campo Nombre solo debe contener letras.");
        	            return;
        	        }

        	        if (!direccion.matches("[a-zA-Z\\s]+")) {
        	            JOptionPane.showMessageDialog(null, "El campo Dirección solo debe contener letras y espacios.");
        	            return;
        	        }

        	        int RUC = 0;

        	        try {
        	            RUC = Integer.parseInt(rucText);
        	        } catch (NumberFormatException ex) {
        	            // Manejar la excepción de formato numérico incorrecto
        	            JOptionPane.showMessageDialog(null, "El campo RUC debe ser un número válido.");
        	            return; // Salir del método actionPerformed
        	        }

        	        DefaultTableModel model = (DefaultTableModel) table_Proveedores.getModel();
        	        model.addRow(new Object[]{name, direccion, RUC});

        	        // Limpiar los campos de texto
        	        textNombre.setText("");
        	        textDireccion.setText("");
        	        textRUC.setText("");
        	    }
        	});

        btnAgregar.setBounds(29, 570, 117, 29);
        Proveedor.add(btnAgregar);
        
        JButton btnListar = new JButton("Listar");
        btnListar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		DefaultTableModel model = (DefaultTableModel) table_Proveedores.getModel();
                Object[][] rowData = new Object[model.getRowCount()][model.getColumnCount()];
                for (int i = 0; i < model.getRowCount(); i++) {
                    for (int j = 0; j < model.getColumnCount(); j++) {
                        rowData[i][j] = model.getValueAt(i, j);
                    }
                }

                Arrays.sort(rowData, Comparator.comparing(o -> o[0].toString()));

                model.setRowCount(0); // Limpiar la tabla

                for (Object[] row : rowData) {
                    model.addRow(row);
                }
            }
        });
        btnListar.setBounds(29, 686, 117, 29);
        Proveedor.add(btnListar);
        
        JButton btnEliminar = new JButton("Eliminar");
        btnEliminar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		if (selectedRowIndex != -1) {
                    DefaultTableModel model = (DefaultTableModel) table_Proveedores.getModel();
                    model.removeRow(selectedRowIndex);

                    // Limpiar los campos de texto y restablecer el índice de fila seleccionado
                    textNombre.setText("");
                    textDireccion.setText("");
                    textRUC.setText("");
                    selectedRowIndex = -1;
                }
            }
        });
        btnEliminar.setBounds(29, 796, 117, 29);
        Proveedor.add(btnEliminar);

        JPanel Clientes = new JPanel();
        tabbedPane.addTab("Clientes", null, Clientes, null);
        Clientes.setLayout(null);
        
        JLabel lblNewLabel = new JLabel("Nombres:");
        lblNewLabel.setBounds(38, 10, 87, 16);
        Clientes.add(lblNewLabel);
        
        txtNombreCliente = new JTextField();
        txtNombreCliente.setBounds(106, 8, 86, 20);
        Clientes.add(txtNombreCliente);
        txtNombreCliente.setColumns(10);
        
        JLabel lblNewLabel_2 = new JLabel("DNI:");
        lblNewLabel_2.setBounds(202, 11, 46, 14);
        Clientes.add(lblNewLabel_2);
        
        txtDNICliente = new JTextField();
        txtDNICliente.setColumns(10);
        txtDNICliente.setBounds(233, 8, 86, 20);
        Clientes.add(txtDNICliente);
        
        JButton btnBuscarCliente = new JButton("Buscar");
        btnBuscarCliente.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		//buscar();
        	}
        });
        btnBuscarCliente.setForeground(new Color(51, 0, 255));
        btnBuscarCliente.setFont(new Font("Tahoma", Font.BOLD, 13));
        btnBuscarCliente.setBackground(new Color(255, 255, 255));
        btnBuscarCliente.setBounds(329, 6, 89, 23);
        Clientes.add(btnBuscarCliente);
        
        JScrollPane scrollPane1 = new JScrollPane();
        scrollPane1.setBounds(20, 46, 1740, 890);
        Clientes.add(scrollPane1);      
  }
  
  public void buscarCliente() {

 /* public void buscar() {
	  String nombre = txtNombreCliente.getText();
      int dni = Integer.parseInt(txtDNICliente.getText());
     
      Clientes cliente = new Clientes();
      cliente.setname(nombre);
      cliente.setID(dni);

      ArrayList<Clientes> resultados = buscarCliente(cliente);

      JTextArea txtAreaResultados = new JTextArea();
      txtAreaResultados.setEditable(false);

      for (Clientes c : resultados) {
          txtAreaResultados.append("Nombre: " + c.getname() + "\n");
          txtAreaResultados.append("DNI: " + c.getID() + "\n");
          txtAreaResultados.append("------------------\n");
      }

      JScrollPane scrollPane1 = new JScrollPane(txtAreaResultados);
      scrollPane1.setBounds(20, 46, 398, 159);
      Clientes.add(scrollPane1);
      
  }*/
  
  /*public ArrayList<Clientes> buscarCliente(Clientes cliente) {
	  
	    ArrayList<Clientes> resultados = new ArrayList<Clientes>();
	    ArrayList<Clientes> listaClientes = new ArrayList<Clientes>();
	    listaClientes.add(new Clientes());
	    listaClientes.add(new Clientes());
	    listaClientes.add(new Clientes());
	   
	    for (Clientes c : listaClientes) {
	        if (c.getname().equals(cliente.getname()) && c.getID() == cliente.getID()) {
	            resultados.add(c);
	        }
	    }

	    return resultados;
	}*/
  }
} 