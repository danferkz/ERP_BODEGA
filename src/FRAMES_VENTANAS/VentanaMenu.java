package FRAMES_VENTANAS;

import javax.swing.border.EmptyBorder;

import CLASES.Clientes_BE;
import CLASES.Creation;
import CLASES.Metodo_BC;
import CLASES.Return_DALC;
import CLASES.Productos_BE;
import CLASES.Proveedores_BE;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.beans.PropertyVetoException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.FileOutputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;


public class VentanaMenu extends JFrame {
    private JPanel contentPane; // Agregamos la variable contentPane
    private Metodo_BC modi = new Metodo_BC();
	private Return_DALC especific = new Return_DALC();
	private static Creation Hash = new Creation();
	
	

    public VentanaMenu() {
    	setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaMenu.class.getResource("/IMAGENES/lazer.png")));
    	setResizable(false);
        setTitle("Menú");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1366, 768);
        setLocationRelativeTo(null);
   
        						
        lectura();
        lectura2();
        lectura3();
        
        JMenuBar menuBar = new JMenuBar();
        
        JMenu areasMenu = new JMenu("Áreas");
        
        JMenuItem ventasItem = new JMenuItem("VENTAS");
        ventasItem.setFont(new Font("Segoe UI Black", Font.BOLD, 16));
        JMenuItem almacenItem = new JMenuItem("INVENTARIO");
        almacenItem.setFont(new Font("Segoe UI Black", Font.BOLD, 16));
        JMenuItem personasItem = new JMenuItem("PERSONAS");
        personasItem.setFont(new Font("Segoe UI Black", Font.BOLD, 16));
        
        areasMenu.add(ventasItem);
        areasMenu.add(almacenItem);
        areasMenu.add(personasItem);
        
        menuBar.add(areasMenu);
        
        setJMenuBar(menuBar);
        
        contentPane = new JPanel();
        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout(0,0));
        
        JDesktopPane descktopPane = new JDesktopPane();
        descktopPane.setBackground(Color.LIGHT_GRAY);
        contentPane.add(descktopPane, BorderLayout.CENTER);
        
        JLabel lblNewLabel = new JLabel("");
        lblNewLabel.setIcon(new ImageIcon(VentanaMenu.class.getResource("/IMAGENES/Home.jpg")));
        lblNewLabel.setBounds(0, 0, 1350, 707);
        descktopPane.add(lblNewLabel);
        
        ventasItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	I_Ventas  ventasItem = new I_Ventas();
            	descktopPane.add(ventasItem);
            	try {
					ventasItem.setMaximum(true);
				} catch (PropertyVetoException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
            	ventasItem.show();
            }
        });   
		
        almacenItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	I_Inventario  almacenItem = new I_Inventario();
            	descktopPane.add(almacenItem);
            	try {
					almacenItem.setMaximum(true);
				} catch (PropertyVetoException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
            	almacenItem.show();
            }
        });

        personasItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Lógica para la opción de personas
               I_Personas personasItem = new I_Personas();
               descktopPane.add(personasItem);
               try {
            	   personasItem.setMaximum(true);
				} catch (PropertyVetoException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
               personasItem.show();
            }
        });

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new VentanaMenu();
            }
        });
    }
    
    private void lectura() {
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream("Datos/Inventario_re.txt")) {
            if (inputStream != null) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream, StandardCharsets.US_ASCII);
                BufferedReader br = new BufferedReader(inputStreamReader);
                ArrayList<String[]> datos = new ArrayList<>();
                String linea = br.readLine();
                while (linea != null) {
                    String[] elementos = linea.split(",");
                    datos.add(elementos);
                    linea = br.readLine();
                }
                
                for (String[] elementos : datos) {
                    int cod = Integer.parseInt(elementos[0]);
                    String nom = elementos[1];
                    int cantidad = Integer.parseInt(elementos[2]);
                    double pricy = Double.parseDouble(elementos[3]);
                    
                    modi.addToUniversalHashtable(cod, new Productos_BE(cod, cantidad, nom, pricy));
                }
            } else {
                JOptionPane.showMessageDialog(null, "No se ha encontrado el archivo Datos/Inventario_re.txt");
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
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

    
    private void modifyFile2() {
        try {
            // Clear the contents of the file
            String filePath = "src/Datos/Clientes_BE.txt"; // Update the file path accordingly

            // Clear the file contents
            FileOutputStream fileOutputStream = new FileOutputStream(filePath);
            fileOutputStream.close();

            Hashtable<Integer, Clientes_BE> hashtable = Hash.getHashtable3();
            Enumeration<Integer> indic = hashtable.keys();

            // Append new text to the file
            FileWriter fileWriter = new FileWriter(filePath, true);
            BufferedWriter writer = new BufferedWriter(fileWriter);

            while (indic.hasMoreElements()) {
                int code = indic.nextElement();
                Clientes_BE revision = hashtable.get(code);
                String nombre = revision.getName();
                int id = revision.getID();
                
                writer.write(nombre + ",");
                writer.write(id + "");
                writer.newLine();
            }

            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


private void lectura2() {
    try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream("Datos/Clientes_BE.txt")) {
        if (inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, StandardCharsets.US_ASCII);
            BufferedReader br = new BufferedReader(inputStreamReader);
            ArrayList<String[]> datos = new ArrayList<>();
            String linea = br.readLine();
            while (linea != null) {
                String[] elementos = linea.split(",");
                datos.add(elementos);
                linea = br.readLine();
            }
            
            for (String[] elementos : datos) {
                String nom = elementos[0];
                int ident = Integer.parseInt(elementos[1]);
                
                modi.addToUniversalHashtable3(ident, new Clientes_BE(nom, ident));
            }
        } else {
            JOptionPane.showMessageDialog(null, "No se ha encontrado el archivo Datos/Inventario_re.txt");
        }
    } catch (IOException ioe) {
        ioe.printStackTrace();
    }
}

private void modifyFile3() {
    try {
        // Clear the contents of the file
        String filePath = "src/Datos/Proveedores_BE.txt"; // Update the file path accordingly

        // Clear the file contents
        FileOutputStream fileOutputStream = new FileOutputStream(filePath);
        fileOutputStream.close();

        Hashtable<Integer, Proveedores_BE> hashtable = Hash.getHashtable2();
        Enumeration<Integer> indic = hashtable.keys();

        // Append new text to the file
        FileWriter fileWriter = new FileWriter(filePath, true);
        BufferedWriter writer = new BufferedWriter(fileWriter);

        while (indic.hasMoreElements()) {
            int code = indic.nextElement();
            Proveedores_BE revision = hashtable.get(code);
            String nombre = revision.getName();
	        String directiv = revision.getDirection();
            int id = revision.getID();
            
            writer.write(nombre + ",");
	writer.write(directiv + ",");
            writer.write(id + "");
            writer.newLine();
        }

        writer.close();
    } catch (IOException e) {
        e.printStackTrace();
    }
}


private void lectura3() {
try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream("Datos/Proveedores_BE.txt")) {
    if (inputStream != null) {
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream, StandardCharsets.US_ASCII);
        BufferedReader br = new BufferedReader(inputStreamReader);
        ArrayList<String[]> datos = new ArrayList<>();
        String linea = br.readLine();
        while (linea != null) {
            String[] elementos = linea.split(",");
            datos.add(elementos);
            linea = br.readLine();
        }
        
        for (String[] elementos : datos) {
            String nom = elementos[0];
            String direct = elementos[1]; 
            int idd = Integer.parseInt(elementos[2]);
            
            modi.addToUniversalHashtable2(idd, new Proveedores_BE(nom, direct,idd));
        }
    } else {
        JOptionPane.showMessageDialog(null, "No se ha encontrado el archivo Datos/Inventario_re.txt");
    }
} catch (IOException ioe) {
    ioe.printStackTrace();
}
}
    
    

  
    
}
