package codigo;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

import javax.swing.JButton;
import javax.swing.JFrame;

public class hiloKeepAlive extends Thread {
	
	public Integer puerto_escucha_KA;
	private JButton boton;
	
	public hiloKeepAlive(Integer puerto_escucha, JButton Boton){
		puerto_escucha_KA=puerto_escucha;
		boton=Boton;
	}
	
	public void run() {
		
		int MAX_LONGITUD = 1;
		byte [] bufferRecepcion = new byte[MAX_LONGITUD];//Preparamos el buffer de recepcion
		try {
		//Preparamos el datagrama de respuesta a recibir
			DatagramSocket socketA = new DatagramSocket (puerto_escucha_KA);
			DatagramPacket datagrama_respuesta = new DatagramPacket (bufferRecepcion,MAX_LONGITUD);
			//El fin es verificar si se reciben mensajes de un proceso barrera determinado para saber si se
			//debe activar el boton que permite enviar patentes por esa barrera (en funcion de si el proceso esta conectado al servidor)
				while(true){
						socketA.receive(datagrama_respuesta);
						String respuesta = new String(bufferRecepcion);
						if(respuesta.equals("1"))
						{
							boton.setVisible(true);
						}
						else
						{
							boton.setVisible(false);
						}
				}
			}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}


