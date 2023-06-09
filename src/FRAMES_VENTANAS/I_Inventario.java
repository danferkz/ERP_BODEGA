package FRAMES_VENTANAS;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Comparator;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Hashtable;
import java.util.Enumeration;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Hashtable;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.ArrayList;
import CLASES.Creation;
import CLASES.Metodo_BC;
import CLASES.Return_DALC;
import CLASES.Productos_BE;


import java.awt.Font;

import CLASES.Creation;
import CLASES.Productos_BE;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class I_Inventario extends JInternalFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
    private JTextField textFieldCodigo;
    private JTextField textFieldNombre;
    private JTextField textFieldCantidad;
    private JTextField textFieldPrecio;
    private JTable table;
    private DefaultListModel<String> suggestionsModel;
    private int selectedRowIndex;
    private Metodo_BC modi = new Metodo_BC();
	private Return_DALC especific = new Return_DALC();
	private static Creation Hash = new Creation();
	private static String relativePath = "Base de datos" + File.separator + "Inventario_re.txt";
	private static String filePath = System.getProperty("user.dir") + File.separator + relativePath;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                	I_Inventario frame = new I_Inventario();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public I_Inventario() {
    	setTitle("INVENTARIO");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1366, 848);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(141, 185, 205));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        table = new JTable();
        
        
        
        
        DefaultTableModel model = new DefaultTableModel(
        	    new String[] {"Código", "Nombre", "Cantidad", "Precio"}, 0) {
        	    private static final long serialVersionUID = 1L;
        	    Class<?>[] columnTypes = new Class[] {
        	        String.class,
        	        String.class,
        	        Integer.class,
        	        Double.class
        	    };
        	    public Class<?> getColumnClass(int columnIndex) {
        	        return columnTypes[columnIndex];
        	    }
        	};
        	

        
    	


        	

        	

        JLabel lblCodigo = new JLabel("Código:");
        lblCodigo.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblCodigo.setBounds(88, 315, 78, 20);
        contentPane.add(lblCodigo);

        textFieldCodigo = new JTextField();
        textFieldCodigo.setBounds(172, 311, 129, 32);
        contentPane.add(textFieldCodigo);
        textFieldCodigo.setColumns(10);

        JLabel lblNombre = new JLabel("Nombre:");
        lblNombre.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblNombre.setBounds(88, 371, 116, 20);
        contentPane.add(lblNombre);

        textFieldNombre = new JTextField();
        textFieldNombre.setBounds(172, 362, 129, 32);
        contentPane.add(textFieldNombre);
        textFieldNombre.setColumns(10);

        JLabel lblCantidad = new JLabel("Cantidad:");
        lblCantidad.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblCantidad.setBounds(88, 422, 100, 20);
        contentPane.add(lblCantidad);

        textFieldCantidad = new JTextField();
        textFieldCantidad.setBounds(172, 418, 129, 32);
        contentPane.add(textFieldCantidad);
        textFieldCantidad.setColumns(10);

        JLabel lblPrecio = new JLabel("Precio:");
        lblPrecio.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblPrecio.setBounds(88, 472, 100, 20);
        contentPane.add(lblPrecio);

        textFieldPrecio = new JTextField();
        textFieldPrecio.setBounds(172, 468, 129, 32);
        contentPane.add(textFieldPrecio);
        textFieldPrecio.setColumns(10);

        JButton btnAgregar = new JButton("Agregar");
        btnAgregar.setFont(new Font("Tahoma", Font.BOLD, 16));
        btnAgregar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String codigo = textFieldCodigo.getText();
                String nombre = textFieldNombre.getText();
                String cantidadText = textFieldCantidad.getText();
                String precioText = textFieldPrecio.getText();

                // Verificar si algún campo está vacío
                if (codigo.trim().isEmpty() || nombre.trim().isEmpty() || cantidadText.trim().isEmpty() || precioText.trim().isEmpty()) {
                    JOptionPane.showConfirmDialog(null, "Rellene los espacios de producto o cantidad");
                } else {
                    // Convertir a números solo si los campos no están vacíos
                    int cantidad = Integer.parseInt(cantidadText);
                    double precio = Double.parseDouble(precioText);

                    // Agregar la nueva fila a la tabla
                    model.addRow(new Object[]{codigo, nombre, cantidad, precio});

                    // Limpiar los campos de texto
                    textFieldCodigo.setText("");
                    textFieldNombre.setText("");
                    textFieldCantidad.setText("");
                    textFieldPrecio.setText("");

                    

      
                    
                    // Agregar los datos ingresados a la hashtable universal
                    int codigoInt = Integer.parseInt(codigo);
                    modi.addToUniversalHashtable(codigoInt, new Productos_BE(codigoInt, cantidad, nombre, precio));
                }
                modifyFile1();
                CARGA();
            }
        });

        btnAgregar.setBounds(118, 540, 116, 57);
        contentPane.add(btnAgregar);


        JButton btnModificar = new JButton("Modificar");
        btnModificar.setFont(new Font("Tahoma", Font.BOLD, 16));
        btnModificar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (selectedRowIndex != -1) {
                    String codigo = textFieldCodigo.getText();
                    String nombre = textFieldNombre.getText();
                    String cantidadText = textFieldCantidad.getText();
                    String precioText = textFieldPrecio.getText();

                    // Verificar si algún campo está vacío
                    if (codigo.trim().isEmpty() || nombre.trim().isEmpty() || cantidadText.trim().isEmpty() || precioText.trim().isEmpty()) {
                        JOptionPane.showConfirmDialog(null, "Rellene los espacios de producto o cantidad");
                        return;
                    }

                    int cod = Integer.parseInt(codigo);
                    int cant = Integer.parseInt(cantidadText);
                    double price = Double.parseDouble(precioText);

                    DefaultTableModel model = (DefaultTableModel) table.getModel();
                    model.setValueAt(codigo, selectedRowIndex, 0);
                    model.setValueAt(nombre, selectedRowIndex, 1);
                    model.setValueAt(cant, selectedRowIndex, 2);
                    model.setValueAt(price, selectedRowIndex, 3);

                    // Actualizar los datos en la Hashtable
                    modi.addToUniversalHashtable(cod, new Productos_BE(cod, cant, nombre, price));

                    // Limpiar los campos de texto y restablecer el índice de fila seleccionado
                    textFieldCodigo.setText("");
                    textFieldNombre.setText("");
                    textFieldCantidad.setText("");
                    textFieldPrecio.setText("");
                    selectedRowIndex = -1;
                }
                modifyFile1();
                CARGA();
            }
        });
        btnModificar.setBounds(32, 608, 116, 57);
        contentPane.add(btnModificar);


        JButton btnEliminar = new JButton("Eliminar");
        btnEliminar.setFont(new Font("Tahoma", Font.BOLD, 16));
        btnEliminar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (selectedRowIndex != -1) {
                    DefaultTableModel model = (DefaultTableModel) table.getModel();
                    int indez = (Integer) table.getValueAt(selectedRowIndex, 0);
                    model.removeRow(selectedRowIndex);
                    modi.removeFromUniversalHashtable(indez);

                    // Limpiar los campos de texto y restablecer el índice de fila seleccionado
                    textFieldCodigo.setText("");
                    textFieldNombre.setText("");
                    textFieldCantidad.setText("");
                    textFieldPrecio.setText("");
                    selectedRowIndex = -1;
                }
                modifyFile1();
                CARGA();
            }
        });
        btnEliminar.setBounds(221, 608, 116, 57);
        contentPane.add(btnEliminar);
        
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(390, 17, 950, 693);
        contentPane.add(scrollPane);
        

        table = new JTable();
        table.addMouseListener(new MouseAdapter() {
        	public void mouseClicked(MouseEvent e) {
        		selectedRowIndex = table.getSelectedRow();
        	}
        });
        
        scrollPane.setViewportView(table);
        table.setModel(model);
        
        CARGA();
        
        

        // Define el tamaño de fuente deseado para el encabezado de la tabla
        Font tableHeaderFont = new Font("Tahoma", Font.BOLD, 19);
        Font tableFont = new Font("Tahoma", Font.PLAIN, 19);

        // Obtiene el renderizador de encabezado de la tabla
        JTableHeader tableHeader = table.getTableHeader();
        table.setFont(tableFont);
        
        JLabel lblNewLabel = new JLabel("");
        lblNewLabel.setIcon(new ImageIcon(I_Inventario.class.getResource("/IMAGENES/inventario.png")));
        lblNewLabel.setBounds(77, 21, 250, 250);
        contentPane.add(lblNewLabel);
        
        JLabel lblNewLabel_2 = new JLabel("");
        lblNewLabel_2.setIcon(new ImageIcon(I_Inventario.class.getResource("/IMAGENES/images.jpg")));
        lblNewLabel_2.setBounds(77, 293, 225, 225);
        contentPane.add(lblNewLabel_2);

        // Aplica el tamaño de fuente al renderizador de encabezado de la tabla
        tableHeader.setFont(tableHeaderFont);

        table.getSelectionModel().addListSelectionListener(e -> {
            if (table.getSelectedRow() != -1) {
                selectedRowIndex = table.getSelectedRow();
               
                textFieldCodigo.setText(model.getValueAt(selectedRowIndex, 0).toString());
                textFieldNombre.setText(model.getValueAt(selectedRowIndex, 1).toString());
                textFieldCantidad.setText(model.getValueAt(selectedRowIndex, 2).toString());
                textFieldPrecio.setText(model.getValueAt(selectedRowIndex, 3).toString());
            }
        });
    }

    
    private void CARGA()
    {

    	DefaultTableModel model = (DefaultTableModel) table.getModel();
       
    	model.setRowCount(0);
  	  
  	  	table.setModel(model);
    	
    	
        Hashtable<Integer, Productos_BE> hashtable = Hash.getHashtable();
        	Enumeration<Integer> enumera = hashtable.keys();

    		while(enumera.hasMoreElements()) {
    			Integer m = enumera.nextElement();
                Productos_BE produc1 = hashtable.get(m);
    			model.addRow(new Object[] {
    					produc1.getCod(),
    	        		produc1.getNombre(),
    	        		produc1.getCant(),
    	        		produc1.getPrice()
    					});
    		}
    		table.setModel(model);
    		
    }
    
    private void modifyFile1() {
        try {
            // Clear the contents of the file
            String filePath = "src/Datos/Inventario_re.txt"; // Update the file path accordingly

            // Clear the file contents
            FileOutputStream fileOutputStream = new FileOutputStream(filePath);
            fileOutputStream.close();

            Hashtable<Integer, Productos_BE> hashtable = Hash.getHashtable();
            Enumeration<Integer> productos = hashtable.keys();

            // Append new text to the file
            FileWriter fileWriter = new FileWriter(filePath, true);
            BufferedWriter writer = new BufferedWriter(fileWriter);

            while (productos.hasMoreElements()) {
                int code = productos.nextElement();
                Productos_BE revision = hashtable.get(code);
                String nombre = revision.getNombre();
                int actual = revision.getCant();
                int id = revision.getCod();
                double price = revision.getPrice();

                writer.write(id + ",");
                writer.write(nombre + ",");
                writer.write(actual + ",");
                writer.write(price + "");
                writer.newLine();
            }

            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    
}