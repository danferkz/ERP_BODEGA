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
import javax.swing.ImageIcon;

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
        lblNombre.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblNombre.setBounds(47, 364, 98, 14);
        Proveedor.add(lblNombre);
        
        textNombre = new JTextField();
        textNombre.setFont(new Font("Tahoma", Font.PLAIN, 16));
        textNombre.setBounds(147, 359, 116, 29);
        Proveedor.add(textNombre);
        textNombre.setColumns(10);
        
        JLabel lblDirección = new JLabel("Dirección:");
        lblDirección.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblDirección.setBounds(47, 405, 90, 16);
        Proveedor.add(lblDirección);
        
        textDireccion = new JTextField();
        textDireccion.setFont(new Font("Tahoma", Font.PLAIN, 16));
        textDireccion.setBounds(147, 400, 116, 26);
        Proveedor.add(textDireccion);
        textDireccion.setColumns(10);
        
        JLabel lblID = new JLabel("ID:");
        lblID.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblID.setBounds(47, 442, 61, 16);
        Proveedor.add(lblID);
        
        textID = new JTextField();
        textID.setFont(new Font("Tahoma", Font.PLAIN, 16));
        textID.setBounds(147, 437, 116, 26);
        Proveedor.add(textID);
        textID.setColumns(10);
        
        JButton btnAgregar = new JButton("Agregar");
        btnAgregar.setFont(new Font("Tahoma", Font.PLAIN, 16));
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

        btnAgregar.setBounds(91, 526, 117, 40);
        Proveedor.add(btnAgregar);
        
        JButton btnListar = new JButton("Listar");
        btnListar.setFont(new Font("Tahoma", Font.PLAIN, 16));
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
        btnListar.setBounds(21, 584, 117, 40);
        Proveedor.add(btnListar);
        
        JButton btnEliminar = new JButton("Eliminar");
        btnEliminar.setFont(new Font("Tahoma", Font.PLAIN, 16));
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
        btnEliminar.setBounds(168, 584, 117, 40);
        Proveedor.add(btnEliminar);
        
        JLabel lblNewLabel_1 = new JLabel("");
        lblNewLabel_1.setIcon(new ImageIcon(I_Personas.class.getResource("/IMAGENES/proveedor.png")));
        lblNewLabel_1.setBounds(28, 44, 257, 284);
        Proveedor.add(lblNewLabel_1);
        
        JLabel lblNewLabel_3 = new JLabel("");
        lblNewLabel_3.setIcon(new ImageIcon(I_Personas.class.getResource("/IMAGENES/images.jpg")));
        lblNewLabel_3.setBounds(0, 0, 225, 225);
        Proveedor.add(lblNewLabel_3);
        
        JLabel lblNewLabel_3_1 = new JLabel("");
        lblNewLabel_3_1.setIcon(new ImageIcon(I_Personas.class.getResource("/IMAGENES/images.jpg")));
        lblNewLabel_3_1.setBounds(91, 0, 225, 225);
        Proveedor.add(lblNewLabel_3_1);
        
        JLabel lblNewLabel_3_2 = new JLabel("");
        lblNewLabel_3_2.setIcon(new ImageIcon(I_Personas.class.getResource("/IMAGENES/images.jpg")));
        lblNewLabel_3_2.setBounds(0, 224, 225, 225);
        Proveedor.add(lblNewLabel_3_2);
        
        JLabel lblNewLabel_3_2_1 = new JLabel("");
        lblNewLabel_3_2_1.setIcon(new ImageIcon(I_Personas.class.getResource("/IMAGENES/images.jpg")));
        lblNewLabel_3_2_1.setBounds(91, 224, 225, 225);
        Proveedor.add(lblNewLabel_3_2_1);
        
        JLabel lblNewLabel_3_2_2 = new JLabel("");
        lblNewLabel_3_2_2.setIcon(new ImageIcon(I_Personas.class.getResource("/IMAGENES/images.jpg")));
        lblNewLabel_3_2_2.setBounds(0, 405, 225, 225);
        Proveedor.add(lblNewLabel_3_2_2);
        
        JLabel lblNewLabel_3_2_2_1 = new JLabel("");
        lblNewLabel_3_2_2_1.setIcon(new ImageIcon(I_Personas.class.getResource("/IMAGENES/images.jpg")));
        lblNewLabel_3_2_2_1.setBounds(91, 408, 225, 225);
        Proveedor.add(lblNewLabel_3_2_2_1);
        
        JLabel lblNewLabel_3_2_2_2 = new JLabel("");
        lblNewLabel_3_2_2_2.setIcon(new ImageIcon(I_Personas.class.getResource("/IMAGENES/images.jpg")));
        lblNewLabel_3_2_2_2.setBounds(0, 485, 225, 225);
        Proveedor.add(lblNewLabel_3_2_2_2);
        
        JLabel lblNewLabel_3_2_2_3 = new JLabel("");
        lblNewLabel_3_2_2_3.setIcon(new ImageIcon(I_Personas.class.getResource("/IMAGENES/images.jpg")));
        lblNewLabel_3_2_2_3.setBounds(91, 485, 225, 225);
        Proveedor.add(lblNewLabel_3_2_2_3);

        JPanel Clientes = new JPanel();
        tabbedPane.addTab("Clientes", null, Clientes, null);
        Clientes.setLayout(null);
        
        JLabel lblNewLabel = new JLabel("Nombres:");
        lblNewLabel.setBounds(171, 61, 87, 16);
        Clientes.add(lblNewLabel);
        
        txtNombreCliente = new JTextField();
        txtNombreCliente.setBounds(233, 59, 86, 20);
        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblNewLabel.setBounds(67, 375, 87, 16);
        Clientes.add(lblNewLabel);
        
        txtNombreCliente = new JTextField();
        txtNombreCliente.setBounds(154, 365, 123, 30);
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
        lblNewLabel_2.setBounds(372, 62, 46, 14);
        lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblNewLabel_2.setBounds(69, 414, 46, 14);
        Clientes.add(lblNewLabel_2);
        
        txtIDCliente = new JTextField();
        txtIDCliente.setFont(new Font("Tahoma", Font.PLAIN, 16));
        txtIDCliente.setColumns(10);
        txtIDCliente.setBounds(403, 59, 86, 20);
        txtIDCliente.setBounds(154, 406, 123, 30);
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
        btnBuscarCliente.setFont(new Font("Tahoma", Font.BOLD, 16));
        btnBuscarCliente.setBackground(new Color(255, 255, 255));
        btnBuscarCliente.setBounds(517, 57, 89, 23);
        btnBuscarCliente.setBounds(125, 456, 123, 47);
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
		scrollPaneClientes.setBounds(362, 37, 986, 665);
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
        
		
		cargar();
		    
		
        JLabel lblNewLabel_4 = new JLabel("");
        lblNewLabel_4.setIcon(new ImageIcon(I_Personas.class.getResource("/IMAGENES/cliente.png")));
        lblNewLabel_4.setBounds(67, 77, 250, 250);
        Clientes.add(lblNewLabel_4);
        
        JLabel lblNewLabel_5 = new JLabel("");
        lblNewLabel_5.setIcon(new ImageIcon(I_Personas.class.getResource("/IMAGENES/a.png")));
        lblNewLabel_5.setBounds(125, 514, 120, 120);
        Clientes.add(lblNewLabel_5);
        
        JLabel lblNewLabel_3_3 = new JLabel("");
        lblNewLabel_3_3.setIcon(new ImageIcon(I_Personas.class.getResource("/IMAGENES/images.jpg")));
        lblNewLabel_3_3.setBounds(0, 0, 225, 225);
        Clientes.add(lblNewLabel_3_3);
        
        JLabel lblNewLabel_3_3_1 = new JLabel("");
        lblNewLabel_3_3_1.setIcon(new ImageIcon(I_Personas.class.getResource("/IMAGENES/images.jpg")));
        lblNewLabel_3_3_1.setBounds(137, 0, 225, 225);
        Clientes.add(lblNewLabel_3_3_1);
        
        JLabel lblNewLabel_3_3_2 = new JLabel("");
        lblNewLabel_3_3_2.setIcon(new ImageIcon(I_Personas.class.getResource("/IMAGENES/images.jpg")));
        lblNewLabel_3_3_2.setBounds(0, 220, 225, 225);
        Clientes.add(lblNewLabel_3_3_2);
        
        JLabel lblNewLabel_3_3_3 = new JLabel("");
        lblNewLabel_3_3_3.setIcon(new ImageIcon(I_Personas.class.getResource("/IMAGENES/images.jpg")));
        lblNewLabel_3_3_3.setBounds(137, 220, 225, 225);
        Clientes.add(lblNewLabel_3_3_3);
        
        JLabel lblNewLabel_3_3_4 = new JLabel("");
        lblNewLabel_3_3_4.setIcon(new ImageIcon(I_Personas.class.getResource("/IMAGENES/images.jpg")));
        lblNewLabel_3_3_4.setBounds(0, 443, 225, 225);
        Clientes.add(lblNewLabel_3_3_4);
        
        JLabel lblNewLabel_3_3_5 = new JLabel("");
        lblNewLabel_3_3_5.setIcon(new ImageIcon(I_Personas.class.getResource("/IMAGENES/images.jpg")));
        lblNewLabel_3_3_5.setBounds(135, 443, 225, 225);
        Clientes.add(lblNewLabel_3_3_5);
        
        JLabel lblNewLabel_3_3_6 = new JLabel("");
        lblNewLabel_3_3_6.setIcon(new ImageIcon(I_Personas.class.getResource("/IMAGENES/images.jpg")));
        lblNewLabel_3_3_6.setBounds(135, 491, 225, 225);
        Clientes.add(lblNewLabel_3_3_6);
        
        JLabel lblNewLabel_3_3_7 = new JLabel("");
        lblNewLabel_3_3_7.setIcon(new ImageIcon(I_Personas.class.getResource("/IMAGENES/images.jpg")));
        lblNewLabel_3_3_7.setBounds(0, 491, 225, 225);
        Clientes.add(lblNewLabel_3_3_7);

  }
  
  private void buscar() {
	    DefaultTableModel model = (DefaultTableModel) tableClientes.getModel();
	    int rowCount = model.getRowCount();

	    String nameCorrect = txtNombreCliente.getText();
	    int idCorrect = Integer.parseInt(txtIDCliente.getText());

	    for (int n = rowCount - 1; n >= 0; n--) {
	        String determinante = (String) model.getValueAt(n, 0);
	        int deter = (int) model.getValueAt(n, 1);

	        if (!determinante.equals(nameCorrect) || deter != idCorrect) {
	            model.removeRow(n);
	        }
	    }
	}
  
  private void cargar() {
  
	  DefaultTableModel model = (DefaultTableModel) tableClientes.getModel();
	  
	  model.setRowCount(0);
	  
	  tableClientes.setModel(model);
	    
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
	 tableClientes.setModel(model);
			 
  }
} 