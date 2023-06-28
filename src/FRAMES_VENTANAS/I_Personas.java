package FRAMES_VENTANAS;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.io.File;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Enumeration;
import java.util.Hashtable;

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

import CLASES.Creation;
import CLASES.Metodo_BC;
import CLASES.Productos_BE;
import CLASES.Proveedores_BE;
import CLASES.Return_DALC;
import CLASES.Clientes_BE;

public class I_Personas extends JInternalFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
    private JTextField textNombre;
    private JTextField textDireccion;
    private JTextField txtIDCliente;
    private JTextField textID;
    private JTextField txtNombreCliente;
    private JTable table_Proveedores;
    private int selectedRowIndex = -1;
    private JTable tableClientes;
    private Metodo_BC modi = new Metodo_BC();
   	private Return_DALC especific = new Return_DALC();
   	private Creation Hash = new Creation();
   	private String relativePath = "Base de datos" + File.separator + "Productos.txt";
	private String filePath = System.getProperty("user.dir") + File.separator + relativePath;
	private int fila;
	private int colum;


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
	  setTitle("PERSONAS");
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setBounds(100, 100, 1366, 768);
      contentPane = new JPanel();
      contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
      contentPane.setBackground(Color.WHITE);
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
                new String[]{"Nombre", "Direccion", "ID"}
        ));
        table_Proveedores.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollPane_Proveedores = new JScrollPane();
        scrollPane_Proveedores.setBounds(318, 11, 1015, 661);
        Proveedor.add(scrollPane_Proveedores);
        table_Proveedores.getSelectionModel().addListSelectionListener(e -> {
            if (table_Proveedores.getSelectedRow() != -1) {
                selectedRowIndex = table_Proveedores.getSelectedRow();
                DefaultTableModel model = (DefaultTableModel) table_Proveedores.getModel();
                textNombre.setText(model.getValueAt(selectedRowIndex, 0).toString());
                textDireccion.setText(model.getValueAt(selectedRowIndex, 1).toString());
                textID.setText(model.getValueAt(selectedRowIndex, 2).toString());
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
        
        JLabel lblID = new JLabel("ID:");
        lblID.setBounds(22, 240, 61, 16);
        Proveedor.add(lblID);
        
        textID = new JTextField();
        textID.setBounds(92, 235, 116, 26);
        Proveedor.add(textID);
        textID.setColumns(10);
        
        JButton btnAgregar = new JButton("Agregar");
        btnAgregar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
        		 String name = textNombre.getText();
        	        String direccion = textDireccion.getText();
        	        String idText = textID.getText();
        	        

        	        // Validación de solo letras en el nombre y dirección
        	        if (!name.matches("[a-zA-Z]+")) {
        	            JOptionPane.showMessageDialog(null, "El campo Nombre solo debe contener letras.");
        	            return;
        	        }

        	        if (!direccion.matches("[a-zA-Z\\s]+")) {
        	            JOptionPane.showMessageDialog(null, "El campo Dirección solo debe contener letras y espacios.");
        	            return;
        	        }

        	        int ID = 0;

        	        try {
        	            ID = Integer.parseInt(idText);
        	        } catch (NumberFormatException ex) {
        	            // Manejar la excepción de formato numérico incorrecto
        	            JOptionPane.showMessageDialog(null, "El campo ID debe ser un número válido.");
        	            return; // Salir del método actionPerformed
        	        }

        	        DefaultTableModel model = (DefaultTableModel) table_Proveedores.getModel();
        	        model.addRow(new Object[]{name, direccion, ID});

        	        // Limpiar los campos de texto
        	        textNombre.setText("");
        	        textDireccion.setText("");
        	        textID.setText("");
        	    }
        	});

        btnAgregar.setBounds(21, 324, 117, 29);
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
        btnListar.setBounds(21, 386, 117, 29);
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
                    textID.setText("");
                    selectedRowIndex = -1;
                }
            }
        });
        btnEliminar.setBounds(21, 450, 117, 29);
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
        txtNombreCliente.addKeyListener((KeyListener) new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z') || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))) {
                    e.consume();
                }
            }
        });
        
        JLabel lblNewLabel_2 = new JLabel("ID:");
        lblNewLabel_2.setBounds(202, 11, 46, 14);
        Clientes.add(lblNewLabel_2);
        
        txtIDCliente = new JTextField();
        txtIDCliente.setColumns(10);
        txtIDCliente.setBounds(233, 8, 86, 20);
        Clientes.add(txtIDCliente);
        txtIDCliente.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!(Character.isDigit(c) || c == KeyEvent.VK_BACK_SPACE || c == KeyEvent.VK_DELETE) || txtIDCliente.getText().length() >= 8) {
                    e.consume();
                }
            }
        });
        
        JButton btnBuscarCliente = new JButton("Buscar");
        btnBuscarCliente.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		buscar();
        	}
        });
        btnBuscarCliente.setForeground(new Color(51, 0, 255));
        btnBuscarCliente.setFont(new Font("Tahoma", Font.BOLD, 13));
        btnBuscarCliente.setBackground(Color.GRAY);
        btnBuscarCliente.setBounds(329, 6, 89, 23);
        Clientes.add(btnBuscarCliente);
           

		DefaultTableModel model = new DefaultTableModel(
			    new String[] {"Nombre", "ID"}, 0) {
			    private static final long serialVersionUID = 1L;
			    Class<?>[] columnTypes = new Class[] {
			        String.class,
			        Integer.class,
			    };
			    public Class<?> getColumnClass(int columnIndex) {
			        return columnTypes[columnIndex];
			    }
			};
		
		
		JScrollPane scrollPaneClientes = new JScrollPane(tableClientes);
		scrollPaneClientes.setBounds(10, 37, 1338, 665);
		Clientes.add(scrollPaneClientes);
		    
        tableClientes = new JTable();
        tableClientes.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				fila = tableClientes.getSelectedRow();
				colum = tableClientes.getSelectedColumn();
			}
		});
        scrollPaneClientes.setViewportView(tableClientes);
		tableClientes.setModel(model);
		
		JButton btnCargar = new JButton("Cargar");
		btnCargar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cargar();
			}
		});
		btnCargar.setBounds(443, 7, 89, 23);
		Clientes.add(btnCargar);
        
		modi.addToUniversalHashtable3(1, new Clientes_BE("Luis",  43321105));
		modi.addToUniversalHashtable3(2, new Clientes_BE("Marco", 53327708));
		modi.addToUniversalHashtable3(3, new Clientes_BE("Aaron", 33344105));
		modi.addToUniversalHashtable3(4, new Clientes_BE("Alex", 44343434));
		cargar();
  }
  
  private void buscar() {

	  	DefaultTableModel model = (DefaultTableModel) tableClientes.getModel();
	  	int rowCount = model.getRowCount();
	  	
	  	String nameCorrect = txtNombreCliente.getText();
	  	int idCorrect = Integer.parseInt(txtIDCliente.getText());
	  	
	  	for (int n = rowCount - 1; n >= 0; n--) {
	        String determinante = (String) model.getValueAt(n, 0);
	        if (!determinante.equals(nameCorrect)) {
	            model.removeRow(n);
	        }
	    }
	  	
	  	for (int n = rowCount - 1; n >= 0; n--) {
	        String determinante1 = (String) model.getValueAt(n, 1);
	        if (!determinante1.equals(idCorrect)) {
	            model.removeRow(n);
	        }
	    }
	  	
  }
  
  private void cargar() {
  
	  DefaultTableModel model = (DefaultTableModel) tableClientes.getModel();
	    
	  Hashtable<Integer, Clientes_BE> hashtable = Hash.getHashtable3();
	  Enumeration<Integer> cliente = hashtable.keys();
	  while(cliente.hasMoreElements()) {
		  int num = cliente.nextElement();
		  Clientes_BE c = hashtable.get(num);
		  model.addRow(new Object[]{
				  c.getName(),
				  c.getID(),
		  			});
	 }
			 
  }
} 