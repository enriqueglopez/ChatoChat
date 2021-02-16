package socket_tcp_chat;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

import interfaz.UsuarioStart;

public class Cliente implements Runnable {

    private int puerto;
    private String mensaje;
    private String host = "";
    static public String msgError = "";
    

    public Cliente(int puerto, String mensaje) {
        this.puerto = puerto;
        this.mensaje = mensaje;
    }

    @Override
    public void run() {
        String host = UsuarioStart.host;
        DataOutputStream out;
        
        try {
        	Socket cliente = new Socket(host, puerto);
        	out = new DataOutputStream(cliente.getOutputStream());
        	out.writeUTF(mensaje);
        	cliente.close();
        	msgError = "";
        }catch(Exception e) {
        	e.printStackTrace();
        	msgError = "<MENSAJE DE SERVIDOR> ERROR DE CONEXIÓN. PRUEBE A REINICIAR LA APLICACIÓN. \n";
        }
    }
    
    public String getHost() {
    	return this.host;
    }
    
    public void setHost(String host) {
    	this.host = host;
    }
}
