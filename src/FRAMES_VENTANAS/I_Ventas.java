package FRAMES_VENTANAS;

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
import java.io.FileWriter;
import java.time.LocalDate;

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
			scrollPane.setBounds(10, 79, 694, 205);
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
			lblNew.setBounds(10, 11, 62, 14);
			contentPane.add(lblNew);
			
			textProduct = new JTextField();
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
			textProduct.setBounds(70, 11, 115, 20);
			contentPane.add(textProduct);
			textProduct.setColumns(10);
			
			scrollSurgimiento = new JScrollPane();
			scrollSurgimiento.setBounds(70, 31, 115, 37);
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
			lblNew2.setBounds(195, 11, 62, 14);
			contentPane.add(lblNew2);
			
			textCant = new JTextField();
			textCant.addKeyListener(new KeyAdapter() {
				public void keyTyped(KeyEvent e) {
					char objt = e.getKeyChar();
					if (Character.isAlphabetic(objt) == true) {
						e.consume();
					}
				}
			});
			textCant.setBounds(254, 8, 86, 20);
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
			btnAdicionar.setBounds(379, 7, 89, 23);
			contentPane.add(btnAdicionar);
			
			JButton btnDelete = new JButton("Eliminar");
			btnDelete.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					model.removeRow(changfila);
				}
			});
			btnDelete.setBounds(498, 7, 89, 23);
			contentPane.add(btnDelete);
			
			JButton btnModify = new JButton("Modificar");
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
			btnModify.setBounds(597, 7, 89, 23);
			contentPane.add(btnModify);
			
			JLabel lblNew3 = new JLabel("ID del Cliente:");
			lblNew3.setBounds(137, 319, 86, 14);
			contentPane.add(lblNew3);
			
			textID = new JTextField();
			textID.addKeyListener(new KeyAdapter() {
				public void keyTyped(KeyEvent e) {
					char objt = e.getKeyChar();
					if (Character.isAlphabetic(objt) == true) {
						e.consume();
					}
				}
			});
			textID.setBounds(217, 316, 91, 20);
			contentPane.add(textID);
			textID.setColumns(10);
			
			lblNew4 = new JLabel("Nombre del Cliente:");
			lblNew4.setBounds(137, 298, 121, 14);
			contentPane.add(lblNew4);
			
			textClient = new JTextField();
			textClient.addKeyListener(new KeyAdapter() {
				public void keyTyped(KeyEvent e) {
					char objt = e.getKeyChar();
					if (Character.isDigit(objt) == true) {
						e.consume();
					}
				}
			});
			textClient.setBounds(254, 295, 131, 20);
			contentPane.add(textClient);
			textClient.setColumns(10);
			
			String filePath = "C:\\Users\\andre\\Downloads\\Icon.png";
			ImageIcon icon = new ImageIcon(filePath);
			
			btnRealizar = new JButton("CONCRETAR");
			btnRealizar.setHorizontalAlignment(SwingConstants.RIGHT);
			btnRealizar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					boleta();
				}
			});
			btnRealizar.setBounds(529, 295, 143, 38);
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
			btnRegresar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					A_InicioSesion s1 = new A_InicioSesion();
					s1.setVisible(true);
					I_Ventas.this.dispose();
				}
			});
			btnRegresar.setBounds(26, 315, 89, 23);
			contentPane.add(btnRegresar);
		
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
}