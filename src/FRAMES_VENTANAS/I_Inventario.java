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
    private int selectedRowIndex = -1;

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
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 650, 400);
        contentPane = new JPanel();
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblCodigo = new JLabel("Código:");
        lblCodigo.setBounds(10, 10, 60, 20);
        contentPane.add(lblCodigo);

        textFieldCodigo = new JTextField();
        textFieldCodigo.setBounds(80, 10, 100, 20);
        contentPane.add(textFieldCodigo);
        textFieldCodigo.setColumns(10);

        JLabel lblNombre = new JLabel("Nombre:");
        lblNombre.setBounds(10, 40, 60, 20);
        contentPane.add(lblNombre);

        textFieldNombre = new JTextField();
        textFieldNombre.setBounds(80, 40, 100, 20);
        contentPane.add(textFieldNombre);
        textFieldNombre.setColumns(10);

        JLabel lblCantidad = new JLabel("Cantidad:");
        lblCantidad.setBounds(10, 70, 60, 20);
        contentPane.add(lblCantidad);

        textFieldCantidad = new JTextField();
        textFieldCantidad.setBounds(80, 70, 100, 20);
        contentPane.add(textFieldCantidad);
        textFieldCantidad.setColumns(10);

        JLabel lblPrecio = new JLabel("Precio:");
        lblPrecio.setBounds(10, 100, 60, 20);
        contentPane.add(lblPrecio);

        textFieldPrecio = new JTextField();
        textFieldPrecio.setBounds(80, 100, 100, 20);
        contentPane.add(textFieldPrecio);
        textFieldPrecio.setColumns(10);

        JButton btnAgregar = new JButton("Agregar");
        btnAgregar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String codigo = textFieldCodigo.getText();
                String nombre = textFieldNombre.getText();
                double cantidad = Double.parseDouble(textFieldCantidad.getText());
                double precio = Double.parseDouble(textFieldPrecio.getText());

                DefaultTableModel model = (DefaultTableModel) table.getModel();
                model.addRow(new Object[]{codigo, nombre, cantidad, precio});

                // Limpiar los campos de texto
                textFieldCodigo.setText("");
                textFieldNombre.setText("");
                textFieldCantidad.setText("");
                textFieldPrecio.setText("");
            }
        });
        btnAgregar.setBounds(10, 130, 100, 23);
        contentPane.add(btnAgregar);

        JButton btnModificar = new JButton("Modificar");
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
        btnModificar.setBounds(10, 160, 100, 23);
        contentPane.add(btnModificar);

        JButton btnEliminar = new JButton("Eliminar");
        btnEliminar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (selectedRowIndex != -1) {
                    DefaultTableModel model = (DefaultTableModel) table.getModel();
                    model.removeRow(selectedRowIndex);

                    // Limpiar los campos de texto y restablecer el índice de fila seleccionado
                    textFieldCodigo.setText("");
                    textFieldNombre.setText("");
                    textFieldCantidad.setText("");
                    textFieldPrecio.setText("");
                    selectedRowIndex = -1;
                }
            }
        });
        btnEliminar.setBounds(10, 190, 100, 23);
        contentPane.add(btnEliminar);

        JButton btnListar = new JButton("Listar");
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
        btnListar.setBounds(10, 220, 100, 23);
        contentPane.add(btnListar);

        table = new JTable();
        table.setModel(new DefaultTableModel(
                new Object[][]{},
                new String[]{"Código", "Nombre", "Cantidad", "Precio"}
        ));
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(200, 10, 420, 340);
        contentPane.add(scrollPane);

        table.getSelectionModel().addListSelectionListener(e -> {
            if (table.getSelectedRow() != -1) {
                selectedRowIndex = table.getSelectedRow();
                DefaultTableModel model = (DefaultTableModel) table.getModel();
                textFieldCodigo.setText(model.getValueAt(selectedRowIndex, 0).toString());
                textFieldNombre.setText(model.getValueAt(selectedRowIndex, 1).toString());
                textFieldCantidad.setText(model.getValueAt(selectedRowIndex, 2).toString());
                textFieldPrecio.setText(model.getValueAt(selectedRowIndex, 3).toString());
            }
        });
    }
}