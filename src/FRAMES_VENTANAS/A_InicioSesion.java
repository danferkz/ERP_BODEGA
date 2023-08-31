package FRAMES_VENTANAS;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

//import CLASES.Creation;
//import CLASES.Metodo_BC;
//import CLASES.Return_DALC;
//import CLASES.Productos_BE;
//import CLASES.Proveedores_BE;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.Font;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Toolkit;

public class A_InicioSesion extends JFrame {

	private JPanel contentPane;
	private JTextField textUser;
	private JPasswordField Contra;
	//private Metodo_BC modi = new Metodo_BC();
	//private Return_DALC especific = new Return_DALC();
	//private Creation Hash = new Creation();
	
	private static final long serialVersionUID = 1L;

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
		setIconImage(Toolkit.getDefaultToolkit().getImage(A_InicioSesion.class.getResource("/IMAGENES/lazer.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 335, 500);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(78, 167, 180));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnInicio = new JButton("Iniciar Sesion");
		btnInicio.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnInicio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			    VentanaMenu Cuerpo = new VentanaMenu();
			    
			    String usuario = "Paxo";
			    char[] contrasenaCorrecta = "0123".toCharArray(); // Convierte la contraseña a un arreglo de caracteres

			    char[] contrasenaIngresada = Contra.getPassword(); // Obtiene la contraseña ingresada como un arreglo de caracteres

			    // Convierte el arreglo de caracteres a una cadena para compararla con el texto del campo de texto
			    String contrasenaIngresadaStr = new String(contrasenaIngresada);

			    // Compara el nombre de usuario y la contraseña como cadenas de texto
			    if (textUser.getText().equals(usuario) && contrasenaIngresadaStr.equals(new String(contrasenaCorrecta))) {
			        Cuerpo.setVisible(true);
			        A_InicioSesion.this.dispose();
			        // Acciones adicionales si la contraseña es correcta
			    } else {
			        Cuerpo.setVisible(false);
			        JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrectos");
			    }
			}
		});
		btnInicio.setBounds(67, 397, 173, 38);
		contentPane.add(btnInicio);
		
		JLabel lblUser = new JLabel("Usuario:");
		lblUser.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblUser.setBounds(117, 264, 80, 23);
		contentPane.add(lblUser);
		
		JLabel lblCode = new JLabel("Contraseña:");
		lblCode.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblCode.setBounds(106, 330, 110, 14);
		contentPane.add(lblCode);
		
		textUser = new JTextField();
		textUser.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textUser.setBounds(84, 298, 131, 20);
		contentPane.add(textUser);
		textUser.setColumns(10);
		
		Contra = new JPasswordField();
		Contra.setFont(new Font("Tahoma", Font.PLAIN, 16));
		Contra.setBounds(86, 355, 129, 20);
		contentPane.add(Contra);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(A_InicioSesion.class.getResource("/IMAGENES/loginnn.png")));
		lblNewLabel.setBounds(30, 11, 250, 250);
		contentPane.add(lblNewLabel);
		
	}
}
