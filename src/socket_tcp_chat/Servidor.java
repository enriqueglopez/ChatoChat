package socket_tcp_chat;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Observable;

public class Servidor extends Observable implements Runnable {

    private int puerto;

    public Servidor(int puerto) {
        this.puerto = puerto;
    }

    @Override
    public void run() {
    	System.out.println("Servidor iniciado");
    	ServerSocket servidor = null;
    	Socket cliente = null;
    	DataInputStream in;
    	
    	try {
    		servidor = new ServerSocket(puerto);
    		
    		while(true) {
    			cliente = servidor.accept();
        		in = new DataInputStream(cliente.getInputStream());
        		String mensaje = in.readUTF();
        		
        		setChanged();
        		notifyObservers(mensaje);
        		clearChanged();
        		
        		cliente.close();
    		}
        }catch(Exception e) {
        	e.printStackTrace();
        }
    }
}
