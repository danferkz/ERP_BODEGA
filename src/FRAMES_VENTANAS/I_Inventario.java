package FRAMES_VENTANAS;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Comparator;

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
import CLASES.Creation;
import CLASES.Metodo_BC;
import CLASES.Return_DALC;
import CLASES.Productos_BE;


import java.awt.Font;

import CLASES.Creation;
import CLASES.Productos_BE;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

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
	private Creation Hash = new Creation();

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
        setBounds(100, 100, 1366, 768);
        contentPane = new JPanel();
        contentPane.setBackground(Color.WHITE);
        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        
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
        lblCodigo.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblCodigo.setBounds(71, 146, 78, 20);
        contentPane.add(lblCodigo);

        textFieldCodigo = new JTextField();
        textFieldCodigo.setBounds(172, 134, 150, 48);
        contentPane.add(textFieldCodigo);
        textFieldCodigo.setColumns(10);

        JLabel lblNombre = new JLabel("Nombre:");
        lblNombre.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblNombre.setBounds(71, 205, 116, 20);
        contentPane.add(lblNombre);

        textFieldNombre = new JTextField();
        textFieldNombre.setBounds(172, 193, 150, 48);
        contentPane.add(textFieldNombre);
        textFieldNombre.setColumns(10);

        JLabel lblCantidad = new JLabel("Cantidad:");
        lblCantidad.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblCantidad.setBounds(71, 278, 100, 20);
        contentPane.add(lblCantidad);

        textFieldCantidad = new JTextField();
        textFieldCantidad.setBounds(172, 266, 150, 48);
        contentPane.add(textFieldCantidad);
        textFieldCantidad.setColumns(10);

        JLabel lblPrecio = new JLabel("Precio:");
        lblPrecio.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblPrecio.setBounds(71, 342, 100, 20);
        contentPane.add(lblPrecio);

        textFieldPrecio = new JTextField();
        textFieldPrecio.setBounds(172, 330, 150, 48);
        contentPane.add(textFieldPrecio);
        textFieldPrecio.setColumns(10);

        JButton btnAgregar = new JButton("Agregar");
        btnAgregar.setFont(new Font("Tahoma", Font.BOLD, 16));
        btnAgregar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String codigo = textFieldCodigo.getText();
                String nombre = textFieldNombre.getText();
                double cantidad = Double.parseDouble(textFieldCantidad.getText());
                double precio = Double.parseDouble(textFieldPrecio.getText());

                DefaultTableModel model = (DefaultTableModel) table.getModel();

                // Verificar si algún campo está vacío
                if (codigo.trim().isEmpty() || nombre.trim().isEmpty() || textFieldCantidad.getText().trim().isEmpty() || textFieldPrecio.getText().trim().isEmpty()) {
                    JOptionPane.showConfirmDialog(null, "Rellene los espacios de producto o cantidad");
                } else {
                    // Agregar la nueva fila a la tabla
                    model.addRow(new Object[]{codigo, nombre, cantidad, precio});

                    // Limpiar los campos de texto
                    textFieldCodigo.setText("");
                    textFieldNombre.setText("");
                    textFieldCantidad.setText("");
                    textFieldPrecio.setText("");

                    // Realizar la acción adicional
                    int quant = Integer.parseInt(textFieldCantidad.getText());
                    double totalix = 0;
                    int advertencia = 0;
                    Hashtable<Integer, Productos_BE> hashtable = Hash.getHashtable();
                    Enumeration<Integer> productos = hashtable.keys();
                    while (productos.hasMoreElements()) {
                        int cod = productos.nextElement();
                        Productos_BE revision = hashtable.get(cod);
                        if (revision != null) { // Add null check here
                            String set = revision.getNombre();
                            advertencia = revision.getCant();
                            if (set.equals(nombre)) {
                                precio = revision.getPrice();
                                totalix = quant * precio;
                            }
                        }
                   
                    }
                }
            }
        });

        btnAgregar.setBounds(92, 409, 230, 71);
        contentPane.add(btnAgregar);


        JButton btnModificar = new JButton("Modificar");
        btnModificar.setFont(new Font("Tahoma", Font.BOLD, 16));
        btnModificar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (selectedRowIndex != -1) {
                    String codigo = textFieldCodigo.getText();
                    String nombre = textFieldNombre.getText();
                    double cantidad = Double.parseDouble(textFieldCantidad.getText());
                    double precio = Double.parseDouble(textFieldPrecio.getText());

                    DefaultTableModel model = (DefaultTableModel) table.getModel();
                    model.setValueAt(codigo, selectedRowIndex, 0);
                    model.setValueAt(nombre, selectedRowIndex, 1);
                    model.setValueAt(cantidad, selectedRowIndex, 2);
                    model.setValueAt(precio, selectedRowIndex, 3);

                    // Limpiar los campos de texto y restablecer el índice de fila seleccionado
                    textFieldCodigo.setText("");
                    textFieldNombre.setText("");
                    textFieldCantidad.setText("");
                    textFieldPrecio.setText("");
                    selectedRowIndex = -1;
                }
            }
        });
        btnModificar.setBounds(92, 501, 230, 71);
        contentPane.add(btnModificar);

        JButton btnEliminar = new JButton("Eliminar");
        btnEliminar.setFont(new Font("Tahoma", Font.BOLD, 16));
        btnEliminar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
 
                    model.removeRow(selectedRowIndex);
                    int indez = (Integer)table.getValueAt(selectedRowIndex, 0);
                    modi.removeFromUniversalHashtable(indez);
                    // Limpiar los campos de texto y restablecer el índice de fila seleccionado
                    textFieldCodigo.setText("");
                    textFieldNombre.setText("");
                    textFieldCantidad.setText("");
                    textFieldPrecio.setText("");
            }
        });
        btnEliminar.setBounds(92, 594, 230, 71);
        contentPane.add(btnEliminar);

        JButton btnListar = new JButton("Listar");
        btnListar.setFont(new Font("Tahoma", Font.BOLD, 16));
        btnListar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                DefaultTableModel model = (DefaultTableModel) table.getModel();
                Object[][] rowData = new Object[model.getRowCount()][model.getColumnCount()];
                for (int i = 0; i < model.getRowCount(); i++) {
                    for (int j = 0; j < model.getColumnCount(); j++) {
                        rowData[i][j] = model.getValueAt(i, j);
                    }
                }

                Arrays.sort(rowData, Comparator.comparingDouble(o -> (double) o[0]));

                model.setRowCount(0); // Limpiar la tabla

                for (Object[] row : rowData) {
                    model.addRow(row);
                }
            }
        });
        btnListar.setBounds(89, 758, 230, 71);
        contentPane.add(btnListar);

        table = new JTable();
        table.addMouseListener(new MouseAdapter() {
        	public void mouseClicked(MouseEvent e) {
        		selectedRowIndex = table.getSelectedRow();
        	}
        });
        table.setModel(new DefaultTableModel(
                new Object[][]{},
                new String[]{"Código", "Nombre", "Cantidad", "Precio"}
        ));
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(390, 17, 950, 648);
        contentPane.add(scrollPane);

        // Define el tamaño de fuente deseado para el encabezado de la tabla
        Font tableHeaderFont = new Font("Tahoma", Font.BOLD, 19);
        Font tableFont = new Font("Tahoma", Font.PLAIN, 19);

        // Obtiene el renderizador de encabezado de la tabla
        JTableHeader tableHeader = table.getTableHeader();
        table.setFont(tableFont);
        
        JLabel lblNewLabel = new JLabel("");
        lblNewLabel.setIcon(new ImageIcon("C:\\Users\\Usuario\\eclipse-workspace\\ERP_BODEGA (1)\\ERP_BODEGA\\src\\ICONOS\\1440500.png"));
        lblNewLabel.setBounds(72, 17, 250, 250);
        contentPane.add(lblNewLabel);

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
}