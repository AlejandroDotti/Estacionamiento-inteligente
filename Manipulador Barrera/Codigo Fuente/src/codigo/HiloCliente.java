package codigo;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Date;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JTextPane;

public class HiloCliente implements Runnable{  //Hago esto para que se tome como hilo
	

	//Socket que utilizaremos para comunicarnos con el servidor
	private InetAddress dirip_b;
	private Integer puerto_b;
	private Integer puerto_e;
	private String mensaje_b;
	private JButton boton;
	private JTextPane texto;

	
	//Constructor de la clase encargado de referenciar el socket
	public HiloCliente(InetAddress dirIP_B, Integer puerto_B, Integer puerto_E, String mensaje, JButton Boton, JTextPane Texto){
		dirip_b = dirIP_B;
		puerto_b= puerto_B;
		puerto_e= puerto_E;
		mensaje_b= mensaje;
		boton=Boton;
		texto=Texto;
	}
	
	//metodo run que debemos implementar para procesar
	@SuppressWarnings("deprecation")
	public void run() {
		// TODO Auto-generated method stub
		try{

			DatagramSocket socketA = new DatagramSocket (puerto_e);
			
			//Reservamos el buffer de envio y construimos el datagrama a enviar
			byte [] bufferEnvio = mensaje_b.getBytes();//Preparamos el vector a enviar
			
			DatagramPacket datagrama_peticion = new DatagramPacket (//Preparamos el envio
						bufferEnvio,
						bufferEnvio.length,
						dirip_b,
						puerto_b
					);
			socketA.send(datagrama_peticion);//Realizamos el envio asicrono
			System.out.println("Enviado el mensaje");
			//Reservamos el buffer de recepcion y construimos el datagrama a recibir
			int MAX_LONGITUD = 4;
			byte [] bufferRecepcion = new byte[MAX_LONGITUD];//Preparamos el buffer de recepcion
			//Preparamos el datagrama de respuesta a recibir
			DatagramPacket datagrama_respuesta = new DatagramPacket (bufferRecepcion,MAX_LONGITUD);
			System.out.println("Esperando mensaje de"+dirip_b);
			//Recibimos la respuesta del proceso B, la presentamos al usuario y cerramos la conexion.
			socketA.receive(datagrama_respuesta);//Realizamos la recepcion bloqueante
			String respuesta = new String(bufferRecepcion);
			System.out.println("La respuesta de B: " + respuesta);
			/*
			for(int i=0;i<bufferRecepcion.length;i++){
				System.out.print(String.format("0x%02X",bufferEnvio[i])+", ");
			}*/
			socketA.close();
			//Verificamos la respuesta recibida para saber que accion realizar
			if(respuesta.compareTo("ALTA")==0)
			{
				System.out.println("Entre al IF");
				boton.hide();
				texto.setText("\n             ALTA");
				texto.setBounds(44, 142, 115, 70);
				texto.setBackground(Color.GREEN);
				Thread.sleep(3000);
				boton.show();
				texto.setText("             BAJA");
				texto.setBounds(44, 142, 115, 26);
				texto.setBackground(Color.CYAN);
			}
			else
			{
				texto.setBackground(Color.RED);
				texto.setText("             BAJA");	
				Thread.sleep(5000);
				texto.setText("             BAJA");
				texto.setBackground(Color.CYAN);
			}
					
		}//fin try
		catch (Exception ex){
			ex.printStackTrace();
		}//fin catch
	}
}
