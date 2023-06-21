package FRAMES_VENTANAS;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;

public class A_InicioSesion extends JFrame {

	private JPanel contentPane;
	private JTextField textUser;
	private JPasswordField Contra;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					A_InicioSesion frame = new A_InicioSesion();
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
	public A_InicioSesion() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 231, 285);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnInicio = new JButton("Iniciar Sesion");
		btnInicio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaMenu Cuerpo = new VentanaMenu();
				
				String usuario = "0";
				String contraseña = "0";
				
				if (textUser.getText().equals(usuario) && Contra.getText().equals(contraseña) ) {
					Cuerpo.setVisible(true);
					A_InicioSesion.this.dispose();
				}
				else {
					JOptionPane.showMessageDialog(null, "Usuario o codigo incorrectos");
				}
				
			}
		});
		btnInicio.setBounds(39, 169, 125, 23);
		contentPane.add(btnInicio);
		
		JLabel lblUser = new JLabel("Usuario:");
		lblUser.setBounds(37, 59, 55, 23);
		contentPane.add(lblUser);
		
		JLabel lblCode = new JLabel("Contraseña:");
		lblCode.setBounds(29, 122, 83, 14);
		contentPane.add(lblCode);
		
		textUser = new JTextField();
		textUser.setBounds(102, 60, 86, 20);
		contentPane.add(textUser);
		textUser.setColumns(10);
		
		Contra = new JPasswordField();
		Contra.setBounds(102, 119, 86, 20);
		contentPane.add(Contra);
	}
}
