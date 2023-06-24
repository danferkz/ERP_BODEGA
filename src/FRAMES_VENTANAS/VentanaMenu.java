package FRAMES_VENTANAS;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.beans.PropertyVetoException;

import javax.swing.JDesktopPane;


public class VentanaMenu extends JFrame {
    private JPanel contentPane; // Agregamos la variable contentPane

    public VentanaMenu() {
    	setResizable(false);
        setTitle("Menú");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1920, 1080);
        setLocationRelativeTo(null);

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
}
