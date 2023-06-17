package FRAMES_VENTANAS;

import java.awt.Component;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTabbedPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JLayeredPane;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;
import javax.swing.JDesktopPane;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.JList;
import javax.swing.AbstractListModel;
import javax.swing.DefaultListModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class Home extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTextField textProduct;
	private JScrollPane scrollSurgimiento;
	private JList suggestions;
	private DefaultListModel<String> suggestionsModel;
	private JLabel lblNew2;
	private JTextField textCant;
	private JTextField textID;
	private JLabel lblNew4;
	private JTextField textClient;
	private JButton btnRealizar;
	private int changfila;
	private int changcolum;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Home frame = new Home();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Home() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 730, 396);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		suggestionsModel = new DefaultListModel<>();
		
		setContentPane(contentPane);
		contentPane.setLayout(null);
		DefaultTableModel model = new DefaultTableModel(
			    new String[] {"Nombre", "Precio", "Cantidad", "Total"}, 0) {
			    private static final long serialVersionUID = 1L;
			    Class<?>[] columnTypes = new Class[] {
			        String.class,
			        Double.class,
			        Integer.class,
			        Double.class
			    };
			    public Class<?> getColumnClass(int columnIndex) {
			        return columnTypes[columnIndex];
			    }
			};
			model.addRow(new Object[] {
					"Dove",
					2.5,
					4,
					10.0,
			});
			
			
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setBounds(10, 145, 694, 139);
			contentPane.add(scrollPane);
			
			table = new JTable();
			table.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					changfila = table.getSelectedRow();
					changcolum = table.getSelectedColumn();
				}
			});
			scrollPane.setViewportView(table);
			table.setModel(model);
			
			int max = model.getRowCount();
			double finalcount = 0; 
			for (int i = 0; i<max ; i++) {
				Object v1 = table.getValueAt(i, 3);
				double d1 = (double)v1;
				finalcount = finalcount + d1;
			};
			System.out.println(finalcount);
			
			
			JLabel lblNew = new JLabel("Producto: ");
			lblNew.setBounds(161, 35, 62, 14);
			contentPane.add(lblNew);
			
			textProduct = new JTextField();
			textProduct.getDocument().addDocumentListener(new DocumentListener() {
	            @Override
	            public void insertUpdate(DocumentEvent e) {
	            	scrollSurgimiento.setVisible(true);
	                updateSuggestions();
	            }
	            
	            @Override
	            public void removeUpdate(DocumentEvent e) {
	            	scrollSurgimiento.setVisible(true);
	                updateSuggestions();
	            }
	            
	            @Override
	            public void changedUpdate(DocumentEvent e) {
	            	scrollSurgimiento.setVisible(true);
	                updateSuggestions();
	            }
	        });
			textProduct.setBounds(231, 32, 115, 20);
			contentPane.add(textProduct);
			textProduct.setColumns(10);
			
			scrollSurgimiento = new JScrollPane();
			scrollSurgimiento.setBounds(231, 52, 115, 37);
			contentPane.add(scrollSurgimiento);
			
			suggestions = new JList<>(suggestionsModel);
			scrollSurgimiento.setViewportView(suggestions);
			suggestions.setVisibleRowCount(5);
			suggestions.addListSelectionListener(new ListSelectionListener() {
	            @Override
	            public void valueChanged(ListSelectionEvent e) {
	                if (!e.getValueIsAdjusting()) {
	                    String selectedValue = (String) suggestions.getSelectedValue();
	                    if (selectedValue != null) {
	                        textProduct.setText(selectedValue);
	                        hideSuggestions();
	                        scrollSurgimiento.setVisible(false);
	                    }
	                }
	            }
	        });
			
			lblNew2 = new JLabel("Cantidad: ");
			lblNew2.setBounds(161, 103, 62, 14);
			contentPane.add(lblNew2);
			
			textCant = new JTextField();
			textCant.setBounds(231, 100, 86, 20);
			contentPane.add(textCant);
			textCant.setColumns(10);
			
			JButton btnAdicionar = new JButton("Agregar");
			btnAdicionar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String nom = textProduct.getText();
					int quant = Integer.parseInt(textCant.getText());
					model.addRow(new Object[] {
							nom,
							3.5,
							quant,
							(quant*3.5),
					});
				}
			});
			btnAdicionar.setBounds(583, 31, 89, 23);
			contentPane.add(btnAdicionar);
			
			JButton btnDelete = new JButton("Eliminar");
			btnDelete.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					model.removeRow(changfila);
				}
			});
			btnDelete.setBounds(583, 65, 89, 23);
			contentPane.add(btnDelete);
			
			JButton btnModify = new JButton("Modificar");
			btnModify.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
				}
			});
			btnModify.setBounds(583, 99, 89, 23);
			contentPane.add(btnModify);
			
			JLabel lblNew3 = new JLabel("ID del Cliente:");
			lblNew3.setBounds(137, 319, 86, 14);
			contentPane.add(lblNew3);
			
			textID = new JTextField();
			textID.setBounds(217, 316, 91, 20);
			contentPane.add(textID);
			textID.setColumns(10);
			
			lblNew4 = new JLabel("Nombre del Cliente:");
			lblNew4.setBounds(137, 298, 121, 14);
			contentPane.add(lblNew4);
			
			textClient = new JTextField();
			textClient.setBounds(254, 295, 131, 20);
			contentPane.add(textClient);
			textClient.setColumns(10);
			
			btnRealizar = new JButton("CONCRETAR");
			btnRealizar.setBounds(557, 295, 115, 38);
			contentPane.add(btnRealizar);
		
			DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
			renderer.setHorizontalAlignment(SwingConstants.CENTER); 

			
			for (int columnIndex = 0; columnIndex < table.getColumnCount(); columnIndex++) {
			    table.getColumnModel().getColumn(columnIndex).setCellRenderer(renderer);
			}
			
			
			scrollSurgimiento.setVisible(false);
			
			Object v1 = table.getValueAt(0, 3);
			double d1 = (double)v1;
			System.out.println(d1);
	}
	
	private void updateSuggestions() {
        String input = textProduct.getText().toLowerCase();
        suggestionsModel.clear();
        
        // Add matching elements to the suggestion list
        if (!input.isEmpty()) {
            // Perform your logic to retrieve matching elements based on the input
            // For simplicity, we'll use a predefined array of elements
            String[] elements = {"Apple", "Banana", "Orange", "Grapes", "Mango"};
            
            for (String element : elements) {
                if (element.toLowerCase().startsWith(input)) {
                	suggestionsModel.addElement(element);
                }
            }
        }
        
        boolean hasSuggestions = !suggestionsModel.isEmpty();
        suggestions.setVisible(hasSuggestions);
        contentPane.revalidate();
    }
	
	 private void hideSuggestions() {
	        suggestionsModel.clear();
	        suggestions.setVisible(false);
	        contentPane.revalidate();
	    }
}
