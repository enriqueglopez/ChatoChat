package socket_tcp_chat;

import java.util.Date;
import java.util.Observable;
import java.util.Observer;

import interfaz.UsuarioStart;
import java.awt.Font;
import java.text.SimpleDateFormat;
import java.awt.Color;
import javax.swing.UIManager;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Usuario extends javax.swing.JFrame implements Observer {
	
	private ImageIcon icono;

    public Usuario() {
    	setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\EnriqueGuajardoL\u00F3pez\\Desktop\\Chat_miguel_enrique\\speech-bubble.png"));
    	setBackground(Color.BLACK);
    	getContentPane().setBackground(Color.BLACK);
    	getContentPane().setForeground(Color.WHITE);
        iniciarComponentes();
        this.getRootPane().setDefaultButton(this.btnEnviar);
        Servidor servidor = new Servidor(Integer.parseInt(UsuarioStart.puertoEntrada));
        servidor.addObserver(this);
        Thread thread = new Thread(servidor);
        thread.start();
    }

    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {} catch (InstantiationException ex) {} catch (IllegalAccessException ex) {} catch (javax.swing.UnsupportedLookAndFeelException ex) {}

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Usuario().setVisible(true);
            }
        });
    }
    
    private void enviarMensaje(java.awt.event.ActionEvent evt) {
    	
    		//Para obtener la fecha y hora
        	Date date = new Date(); 
        	SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm");
        	
        	//Añadimos la fecha y el nombre antes del texto a enviar.
        	
        	String mensaje = formatter.format(date)+" <"+UsuarioStart.nombre+">: " + txtTextoEnviar.getText() + "\n"; 
        	

        	//Le pasamos el puerto de salida especificado en el anterior formulario
        	
        	Cliente cliente = new Cliente(Integer.parseInt(UsuarioStart.puertoSalida), mensaje); 
        	Thread thread = new Thread(cliente);
        	thread.start();
        	
        	/**
        	 * En caso de que el atributo msgError sea distinto de vacio (a lo que se inicializa) 
        	 * se envia el mensaje de error y no se envia el mensaje que queriamos enviar.
        	 */
        	
        	if (Cliente.msgError != "") { 
        		txtTexto.append(Cliente.msgError);
        		txtTextoEnviar.setText("");
        	}
        	
        	txtTexto.append(mensaje);
        	txtTextoEnviar.setText("");
        	
        	
    }

    @Override
    public void update(Observable o, Object arg) {
        txtTexto.append((String) arg);
    }

    

    private javax.swing.JButton btnEnviar;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea txtTexto;
    private javax.swing.JTextField txtTextoEnviar;

    /* Metodo que inicia y configura la interfaz grafica */
    private void iniciarComponentes() {
    	
    	icono = new ImageIcon("img/speech-bubble.png");
		setIconImage(icono.getImage());
		
    	setResizable(false);
        jScrollPane1 = new javax.swing.JScrollPane();
        txtTexto = new javax.swing.JTextArea();
        txtTexto.setBackground(Color.BLACK);
        txtTexto.setForeground(Color.GREEN);
        txtTexto.setEditable(false);
        txtTexto.setFont(new Font("Consolas", Font.BOLD, 20));
        btnEnviar = new javax.swing.JButton();
        btnEnviar.setForeground(Color.MAGENTA);
        btnEnviar.setBackground(UIManager.getColor("CheckBoxMenuItem.acceleratorForeground"));
        btnEnviar.setFont(new Font("Consolas", Font.BOLD, 20));
        txtTextoEnviar = new javax.swing.JTextField();
        txtTextoEnviar.setForeground(Color.GREEN);
        txtTextoEnviar.setBackground(Color.BLACK);
        txtTextoEnviar.setFont(new Font("Consolas", Font.BOLD, 20));

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        
        /*
         * 
         */
        setTitle("ChatoChat - Usuario: "+UsuarioStart.nombre+" | Tu IP: ["+UsuarioStart.ipPropia+"] | IP conectado: ["+UsuarioStart.host+"] | PUERTOS E/S: ["+UsuarioStart.puertoEntrada+"/"+UsuarioStart.puertoSalida+"]");

        txtTexto.setColumns(40);
        txtTexto.setRows(5);
        jScrollPane1.setViewportView(txtTexto);

        btnEnviar.setText("Enviar");
        btnEnviar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                enviarMensaje(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txtTextoEnviar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnEnviar, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1300, Short.MAX_VALUE)) //Ancho chat general
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE) //El alto chat general
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnEnviar, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
                    .addComponent(txtTextoEnviar))
                .addContainerGap())
        );
        pack();
    }
}
