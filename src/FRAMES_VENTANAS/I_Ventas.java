package FRAMES_VENTANAS;

import CLASES.Creation;
import CLASES.Metodo_BC;
import CLASES.Return_DALC;
import CLASES.Productos_BE;
import CLASES.Clientes_BE;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Hashtable;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.Color;
import java.util.Enumeration;

public class I_Ventas extends JInternalFrame {

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
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;
	private JLabel lblNewLabel_4;
	private JLabel lblNewLabel_5;
	private Metodo_BC modi = new Metodo_BC();
	private Return_DALC especific = new Return_DALC();
	private Creation Hash = new Creation();
	private String relativePath = "Base de datos" + File.separator + "Productos.txt";
	private String filePath = System.getProperty("user.dir") + File.separator + relativePath;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					I_Ventas frame = new I_Ventas();
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
	public I_Ventas() {
		setTitle("VENTAS");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1366, 768);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
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
			
			
			
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setBounds(98, 187, 1206, 408);
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
			
			
			JLabel lblNew = new JLabel("Producto: ");
			lblNew.setFont(new Font("Tahoma", Font.PLAIN, 16));
			lblNew.setBounds(98, 103, 80, 14);
			contentPane.add(lblNew);
			
			textProduct = new JTextField();
			textProduct.setFont(new Font("Tahoma", Font.PLAIN, 16));
			textProduct.addKeyListener(new KeyAdapter() {
				public void keyTyped(KeyEvent e) {
					char objt = e.getKeyChar();
					if (Character.isDigit(objt) == true) {
						e.consume();
					}
				}
			});
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
			textProduct.setBounds(184, 100, 115, 20);
			contentPane.add(textProduct);
			textProduct.setColumns(10);
			
			scrollSurgimiento = new JScrollPane();
			scrollSurgimiento.setBounds(184, 119, 115, 37);
			contentPane.add(scrollSurgimiento);
			
			suggestions = new JList<>(suggestionsModel);
			scrollSurgimiento.setViewportView(suggestions);
			suggestions.setFont(new Font("Tahoma", Font.PLAIN, 16));
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
			lblNew2.setFont(new Font("Tahoma", Font.PLAIN, 16));
			lblNew2.setBounds(323, 103, 101, 14);
			contentPane.add(lblNew2);
			
			textCant = new JTextField();
			textCant.setFont(new Font("Tahoma", Font.PLAIN, 16));
			textCant.addKeyListener(new KeyAdapter() {
				public void keyTyped(KeyEvent e) {
					char objt = e.getKeyChar();
					if (Character.isAlphabetic(objt) == true) {
						e.consume();
					}
				}
			});
			textCant.setBounds(398, 100, 115, 20);
			contentPane.add(textCant);
			textCant.setColumns(10);
			
			JButton btnAdicionar = new JButton("Agregar");
			btnAdicionar.setForeground(new Color(0, 0, 0));
			btnAdicionar.setFont(new Font("Tahoma", Font.PLAIN, 16));
			btnAdicionar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String nom = textProduct.getText();
					String nuuma = textCant.getText();
					if (nom.trim().isEmpty() || nuuma.trim().isEmpty()) {
					    JOptionPane.showConfirmDialog(null, "Rellene los espacios de producto o cantidad");
					} 
					else {
					int quant = Integer.parseInt(textCant.getText());
					double precio=0; 
					double totalix=0; 
					int advertencia = 0;
					Hashtable<Integer, Productos_BE> hashtable = Hash.getHashtable();
					Enumeration<Integer> productos = hashtable.keys();
					while (productos.hasMoreElements()) {
					    int cod = productos.nextElement();
					    Productos_BE revision = hashtable.get(cod);
					    if (revision != null) { // Add null check here
					        String set = revision.getNombre();
					        if (set.equals(nom)) {
					            nom = set;
					            precio = revision.getPrice();
					            totalix = quant * precio;
					            advertencia = revision.getCant();
					        }
					    }
					}
				   
				    if (quant>advertencia) {
				    	JOptionPane.showConfirmDialog(null, "No existen suficientes existencias de "+nom);
				    }
				    else {
					model.addRow(new Object[] {
							nom,
							precio,
							quant,
							totalix,
					});
				    }
				 }
				}
			});
			btnAdicionar.setBounds(667, 99, 115, 23);
			contentPane.add(btnAdicionar);
			
			JButton btnDelete = new JButton("Eliminar");
			btnDelete.setFont(new Font("Tahoma", Font.PLAIN, 16));
			btnDelete.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					model.removeRow(changfila);
				}
			});
			btnDelete.setBounds(1068, 99, 115, 23);
			contentPane.add(btnDelete);
			
			JButton btnModify = new JButton("Modificar");
			btnModify.setFont(new Font("Tahoma", Font.PLAIN, 16));
			btnModify.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String nw = textProduct.getText();
					int nw2 = Integer.parseInt(textCant.getText());
					model.setValueAt(5.5, changfila, 1);
					model.setValueAt(nw, changfila, 0);
					model.setValueAt(nw2,changfila,2);
					double nw3 = (double)model.getValueAt(changfila, 1) * nw2 ; 
					model.setValueAt(nw3, changfila, 3);
				}
			});
			btnModify.setBounds(889, 99, 115, 23);
			contentPane.add(btnModify);
			
			JLabel lblNew3 = new JLabel("ID del Cliente:");
			lblNew3.setFont(new Font("Tahoma", Font.PLAIN, 16));
			lblNew3.setBounds(424, 639, 138, 14);
			contentPane.add(lblNew3);
			
			textID = new JTextField();
			textID.setFont(new Font("Tahoma", Font.PLAIN, 16));
			textID.addKeyListener(new KeyAdapter() {
				public void keyTyped(KeyEvent e) {
					char objt = e.getKeyChar();
					if (Character.isAlphabetic(objt) == true) {
						e.consume();
					}
				}
			});
			textID.setBounds(566, 636, 131, 20);
			contentPane.add(textID);
			textID.setColumns(10);
			
			lblNew4 = new JLabel("Nombre del Cliente:");
			lblNew4.setFont(new Font("Tahoma", Font.PLAIN, 16));
			lblNew4.setBounds(98, 637, 193, 18);
			contentPane.add(lblNew4);
			
			textClient = new JTextField();
			textClient.setFont(new Font("Tahoma", Font.PLAIN, 16));
			textClient.addKeyListener(new KeyAdapter() {
				public void keyTyped(KeyEvent e) {
					char objt = e.getKeyChar();
					if (Character.isDigit(objt) == true) {
						e.consume();
					}
				}
			});
			textClient.setBounds(267, 636, 131, 20);
			contentPane.add(textClient);
			textClient.setColumns(10);
			
			String filePath = "C:\\Users\\andre\\Downloads\\Icon.png";
			ImageIcon icon = new ImageIcon(filePath);
			
			btnRealizar = new JButton("CONCRETAR");
			btnRealizar.setFont(new Font("Tahoma", Font.BOLD, 16));
			btnRealizar.setHorizontalAlignment(SwingConstants.RIGHT);
			btnRealizar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String name = textClient.getText();
					String id = String.valueOf(textID.getText());
					int id1 = Integer.parseInt(textID.getText());
					if (name==null || id== null) {
						JOptionPane.showConfirmDialog(null, "Falta datos en los campos de Cliente o ID");
					}
					else {
						boleta();	
						extraccion();
						modi.addToUniversalHashtable3(id1, new Clientes_BE(name,id1));
					}
					modifyFile1();
					
					
				}
			});
			btnRealizar.setBounds(1053, 627, 166, 38);
			contentPane.add(btnRealizar);
			
			// Get the dimensions of the button
			int buttonWidth = btnRealizar.getWidth();
			int buttonHeight = btnRealizar.getHeight();

			// Calculate the desired icon size (e.g., 80% of the button's size)
			int iconWidth = (int) (buttonWidth * 0.2);
			int iconHeight = (int) (buttonHeight * 0.4);

			// Resize the icon to the desired size
			Image resizedIcon = icon.getImage().getScaledInstance(iconWidth, iconHeight, Image.SCALE_SMOOTH);

			// Create a new ImageIcon with the resized image
			ImageIcon resizedImageIcon = new ImageIcon(resizedIcon);

			// Set the resized icon as the button's icon
			btnRealizar.setIcon(resizedImageIcon);
			
			// Position the icon at the left side of the button
			btnRealizar.setHorizontalTextPosition(SwingConstants.RIGHT);
			
			
			JButton btnRegresar = new JButton("MENU");
			btnRegresar.setFont(new Font("Tahoma", Font.BOLD, 16));
			btnRegresar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					A_InicioSesion s1 = new A_InicioSesion();
					s1.setVisible(true);
					I_Ventas.this.dispose();
				}
			});
			btnRegresar.setBounds(900, 630, 143, 32);
			contentPane.add(btnRegresar);
			
			lblNewLabel = new JLabel("");
			lblNewLabel.setIcon(new ImageIcon("C:\\Users\\Usuario\\eclipse-workspace\\ERP_BODEGA (1)\\ERP_BODEGA\\src\\ICONOS\\descarga.png"));
			lblNewLabel.setBounds(88, 78, 225, 121);
			contentPane.add(lblNewLabel);
			
			lblNewLabel_1 = new JLabel("");
			lblNewLabel_1.setIcon(new ImageIcon("C:\\Users\\Usuario\\eclipse-workspace\\ERP_BODEGA (1)\\ERP_BODEGA\\src\\ICONOS\\descarga.png"));
			lblNewLabel_1.setBounds(309, 78, 225, 121);
			contentPane.add(lblNewLabel_1);
			
			lblNewLabel_2 = new JLabel("");
			lblNewLabel_2.setIcon(new ImageIcon("C:\\Users\\Usuario\\eclipse-workspace\\ERP_BODEGA (1)\\ERP_BODEGA\\src\\ICONOS\\descarga.png"));
			lblNewLabel_2.setBounds(972, 103, 225, 82);
			contentPane.add(lblNewLabel_2);
			
			lblNewLabel_3 = new JLabel("");
			lblNewLabel_3.setIcon(new ImageIcon("C:\\Users\\Usuario\\eclipse-workspace\\ERP_BODEGA (1)\\ERP_BODEGA\\src\\ICONOS\\descarga.png"));
			lblNewLabel_3.setBounds(1191, 103, 225, 82);
			contentPane.add(lblNewLabel_3);
			
			lblNewLabel_4 = new JLabel("");
			lblNewLabel_4.setIcon(new ImageIcon("C:\\Users\\Usuario\\eclipse-workspace\\ERP_BODEGA (1)\\ERP_BODEGA\\src\\ICONOS\\descarga.png"));
			lblNewLabel_4.setBounds(88, 606, 225, 121);
			contentPane.add(lblNewLabel_4);
			
			lblNewLabel_5 = new JLabel("");
			lblNewLabel_5.setIcon(new ImageIcon("C:\\Users\\Usuario\\eclipse-workspace\\ERP_BODEGA (1)\\ERP_BODEGA\\src\\ICONOS\\descarga.png"));
			lblNewLabel_5.setBounds(199, 606, 225, 121);
			contentPane.add(lblNewLabel_5);
		
			DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
			renderer.setHorizontalAlignment(SwingConstants.CENTER); 

			
			for (int columnIndex = 0; columnIndex < table.getColumnCount(); columnIndex++) {
			    table.getColumnModel().getColumn(columnIndex).setCellRenderer(renderer);
			}
			
			
			scrollSurgimiento.setVisible(false);
			
		
	
	}
	
	private void updateSuggestions() {
	    String input = textProduct.getText().toLowerCase();
	    suggestionsModel.clear();

	    // Add matching elements to the suggestion list
	    if (!input.isEmpty()) {
	        // Perform your logic to retrieve matching elements based on the input
	        // For simplicity, we'll use a predefined array of elements
	        Hashtable<Integer, Productos_BE> hashtable = Hash.getHashtable();
	        int listado = hashtable.size();
	        Enumeration<Integer> productos = hashtable.keys();
	        String[] elements = new String[listado];
	        int i = 0;
	        while (productos.hasMoreElements()) {
	            int cod = productos.nextElement();
	            Productos_BE produc1 = hashtable.get(cod);
	            String name = produc1.getNombre();
	            elements[i] = name;
	            i++;
	        }
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
	 private void boleta() {
		    JFileChooser f1 = new JFileChooser(".") {
		        private static final long serialVersionUID = 1L;

		        public void approveSelection() {
		            File f = getSelectedFile();
		            if (f.exists() && getDialogType() == SAVE_DIALOG) {
		                int result = JOptionPane.showConfirmDialog(this, "El archivo existe, desea sobreescribir?", "Verificar archivo", JOptionPane.YES_NO_CANCEL_OPTION);
		                switch (result) {
		                    case JOptionPane.YES_OPTION:
		                        super.approveSelection();
		                        return;
		                    case JOptionPane.NO_OPTION:
		                        return;
		                    case JOptionPane.CLOSED_OPTION:
		                        return;
		                    case JOptionPane.CANCEL_OPTION:
		                        cancelSelection();
		                        return;
		                }
		            }
		            super.approveSelection();
		        }
		    };
			
		    FileFilter filtrox = new FileNameExtensionFilter("Archivos de texto (.txt)","txt");
		    f1.setFileFilter(filtrox);
		    f1.setDialogTitle("Especifique archivo a guardar.");
		    int selected = f1.showSaveDialog(this);

		    try {
		        if (selected == JFileChooser.APPROVE_OPTION) {
		            File archsaved = f1.getSelectedFile();
		            String csvFilePath = archsaved.getAbsolutePath() + ".txt";
		            FileWriter alone = new FileWriter(csvFilePath);
		            BufferedWriter diff = new BufferedWriter(alone);
		            String client = textClient.getText();
			    int id = Integer.parseInt(textID.getText());
			    LocalDate currentDate = LocalDate.now();
			    int filas = table.getRowCount();
			    double total = 0;
		            diff.write("	BODEGA 24/7	   R.U.C. 79209726509");
			    diff.newLine();
			    diff.write("			    BOLETA DE VENTA");
			    diff.newLine(); 
			    diff.write("");
			    diff.newLine();
		            diff.write("Cliente:" + " "+client); 
			    diff.newLine();
			    diff.write ("ID: "+id+"           Fecha de emision:"+currentDate);
			    diff.newLine();
			    diff.write("");
			    diff.newLine();
             	    diff.write("CANTIDAD	DESCRIPCION	   P. UNIT. 	   IMPORTE");
             	    diff.newLine();
			    for (int i=0; i<filas; i++){
			    	int cant = (int)table.getValueAt(i,2);
			    	String descrip = (String)table.getValueAt(i,0);
			    	double unit = (double)table.getValueAt(i,1);
			    	double importe = unit*cant;
			    	diff.write(cant+"               "+descrip+"		   "+unit+"		    "+importe);
			    	diff.newLine();
				total = total + importe;
			    };
			    diff.write("");
			    diff.newLine();
			    diff.write("");
			    diff.newLine();
		            diff.write("					   TOTAL   "+total);
		            diff.close();
		        }
		    } catch (Exception e) {
		        e.printStackTrace();
		    }
		}
	 
	 private void extraccion() {
		    Hashtable<Integer, Productos_BE> hashtable = Hash.getHashtable();
		    Enumeration<Integer> productos = hashtable.keys();
		    int filas = table.getRowCount();
		    for (int i = 0; i < filas; i++) {
		        String descrip = (String) table.getValueAt(i, 0);
		        int cant = (int) table.getValueAt(i, 2);
		        while (productos.hasMoreElements()) {
		            int cod = productos.nextElement();
		            Productos_BE revision = hashtable.get(cod);
		            String nombre = revision.getNombre();
		            int actual = revision.getCant();
		            int id = revision.getCod();
		            double price = revision.getPrice();
		            if (nombre.equals(descrip)) {
		                actual = actual - cant;
		                modi.addToUniversalHashtable(id, new Productos_BE(id, actual, nombre, price));
		            }
		        }
		    }
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