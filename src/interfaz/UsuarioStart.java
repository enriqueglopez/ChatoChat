package interfaz;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.net.InetAddress;
import java.net.UnknownHostException;
import static javax.swing.JOptionPane.showMessageDialog;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import socket_tcp_chat.Usuario;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.UIManager;
import java.awt.Toolkit;

/**
 * 
 * @author Enrique Guajardo, Miguel Rubio 2.2 DAM
 *
 */
public class UsuarioStart extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JLabel lblEscribeTuNombre;
	private JTextField textField_1;
	private JLabel lblEscribeIpA;
	private JTextField textField_2;
	static public String host;
	static public String puertoEntrada;
	static public String puertoSalida;
	static public String ipPropia;
	
	public static String nombre;
	
	private JTextField textField_3;
	private JLabel lblTuPuertoDe;
	private JTextField textField_4;
	private ImageIcon icono;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UsuarioStart frame = new UsuarioStart();
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
	public UsuarioStart() {
		setResizable(false);
		setTitle("ChatoChat");
		
		icono = new ImageIcon("img/speech-bubble.png");
		setIconImage(icono.getImage());

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 492, 650);
		contentPane = new JPanel();
		contentPane.setBackground(Color.BLACK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textField = new JTextField();
		textField.setBackground(Color.BLACK);
		textField.setForeground(Color.GREEN);
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setFont(new Font("Consolas", Font.PLAIN, 20));
		textField.setEditable(false);
		textField.setBounds(111, 55, 258, 51);
		contentPane.add(textField);
		textField.setColumns(10);
		
		InetAddress ip;
		try {
			ip = InetAddress.getLocalHost(); //Obtenemos la IP del PC que ejecuta este programa
		
			String[] parts = ip.toString().split("/"); //Dividimos la string separandolo por / (NombrePc/XXX.XXX.XXX.XXX)
			String part1 = parts[0]; //Esto es el nombre localhost
			String part2 = parts[1];  //Esto es la ip
			ipPropia = part2; //Guardamos la ip propia

			textField.setText(part2); //Establecemos que el texto que muestra la IP es la ip
			
			JLabel lblNewLabel = new JLabel("Tu IP es: ");
			lblNewLabel.setForeground(Color.GREEN);
			lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewLabel.setFont(new Font("Consolas", Font.PLAIN, 20));
			lblNewLabel.setBounds(130, 22, 221, 25);
			contentPane.add(lblNewLabel);
			
			lblEscribeTuNombre = new JLabel("Escribe tu nombre:");
			lblEscribeTuNombre.setForeground(Color.GREEN);
			lblEscribeTuNombre.setHorizontalAlignment(SwingConstants.CENTER);
			lblEscribeTuNombre.setFont(new Font("Consolas", Font.PLAIN, 20));
			lblEscribeTuNombre.setBounds(130, 133, 221, 25);
			contentPane.add(lblEscribeTuNombre);
			
			textField_1 = new JTextField();
			textField_1.setForeground(Color.GREEN);
			textField_1.setBackground(Color.BLACK);
			textField_1.setHorizontalAlignment(SwingConstants.CENTER);
			textField_1.setFont(new Font("Consolas", Font.PLAIN, 20));
			textField_1.setColumns(10);
			textField_1.setBounds(111, 166, 258, 51);
			contentPane.add(textField_1);
			
			lblEscribeIpA = new JLabel("Escribe IP a la que te vas a conectar:");
			lblEscribeIpA.setForeground(Color.GREEN);
			lblEscribeIpA.setHorizontalAlignment(SwingConstants.CENTER);
			lblEscribeIpA.setFont(new Font("Consolas", Font.PLAIN, 20));
			lblEscribeIpA.setBounds(26, 233, 428, 25);
			contentPane.add(lblEscribeIpA);
			
			textField_2 = new JTextField();
			textField_2.setForeground(Color.GREEN);
			textField_2.setBackground(Color.BLACK);
			textField_2.setHorizontalAlignment(SwingConstants.CENTER);
			textField_2.setFont(new Font("Consolas", Font.PLAIN, 20));
			textField_2.setColumns(10);
			textField_2.setBounds(110, 268, 258, 51);
			contentPane.add(textField_2);
			
			JButton btnNewButton = new JButton("Conectar");
			btnNewButton.setForeground(Color.MAGENTA);
			btnNewButton.setBackground(UIManager.getColor("CheckBoxMenuItem.acceleratorForeground"));
			
			btnNewButton.setFont(new Font("Consolas", Font.PLAIN, 20));
			btnNewButton.setBounds(150, 547, 182, 56);
			contentPane.add(btnNewButton);
			
			textField_3 = new JTextField();
			textField_3.setHorizontalAlignment(SwingConstants.CENTER);
			textField_3.setForeground(Color.GREEN);
			textField_3.setFont(new Font("Consolas", Font.PLAIN, 20));
			textField_3.setColumns(10);
			textField_3.setBackground(Color.BLACK);
			textField_3.setBounds(110, 381, 258, 51);
			contentPane.add(textField_3);
			
			JLabel lblPuertoDeEntrada = new JLabel("Tu puerto de entrada");
			lblPuertoDeEntrada.setHorizontalAlignment(SwingConstants.CENTER);
			lblPuertoDeEntrada.setForeground(Color.GREEN);
			lblPuertoDeEntrada.setFont(new Font("Consolas", Font.PLAIN, 20));
			lblPuertoDeEntrada.setBounds(26, 346, 428, 25);
			contentPane.add(lblPuertoDeEntrada);
			
			lblTuPuertoDe = new JLabel("Tu puerto de salida");
			lblTuPuertoDe.setHorizontalAlignment(SwingConstants.CENTER);
			lblTuPuertoDe.setForeground(Color.GREEN);
			lblTuPuertoDe.setFont(new Font("Consolas", Font.PLAIN, 20));
			lblTuPuertoDe.setBounds(26, 445, 428, 25);
			contentPane.add(lblTuPuertoDe);
			
			textField_4 = new JTextField();
			textField_4.setHorizontalAlignment(SwingConstants.CENTER);
			textField_4.setForeground(Color.GREEN);
			textField_4.setFont(new Font("Consolas", Font.PLAIN, 20));
			textField_4.setColumns(10);
			textField_4.setBackground(Color.BLACK);
			textField_4.setBounds(110, 480, 258, 51);
			contentPane.add(textField_4);
			
			/**
			 * Cuando pulsemos el boton de conectar
			 */
			
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					/*
					 * Primero verificamos que ninguno de los inputs esté vacio.
					 * */
					if (!textField_1.getText().isEmpty() && !textField_2.getText().isEmpty() && !textField_3.getText().isEmpty() &&
							!textField_4.getText().isEmpty()) {
						
						try {
							nombre = textField_1.getText();
							host = textField_2.getText();
							puertoEntrada = textField_3.getText();
							puertoSalida = textField_4.getText();
							
							/*
							 * Hacemos un try catch para que, en caso de no poder meter un int pase al catch directamente y no inserte.
							 * Esto evita que intente meter un puerto que no es un numero entero.
							 */
							
							int t1 = Integer.parseInt(puertoEntrada);
							int t2 = Integer.parseInt(puertoSalida);
							new Usuario().setVisible(true);
						    dispose();
						}catch(Exception ex) {
							showMessageDialog(null, "No has ingresado puertos validos.");
						}
					}else {
						showMessageDialog(null, "Es necesario rellenar todos los campos para continuar.");
					}
				}
			});
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}
}
