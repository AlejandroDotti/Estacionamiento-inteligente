package procesomanipulador;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;


public class procesoB3 {

	public static void main(String[] args) {
		/*
		 * El proceso B recibe una peticion a A y posteriormente envia una respuesta con una marca de tiempo.
		 * A pide un texto al usuario por consola, y luego se lo envia a B. B recibe el texto,
		 * e indica la hora en que lo ha recibido y se lo devuelve al proceso A para que este
		 * lo muestre por pantalla.
		 * Necesitamos 3 argumentos por consola:
		 * <IP del emisor A>
		 * <Puerto de escucha del socket tipo datagrama instanciado en A>
		 * <Puerto al que enlazar el socket del proceso B>
		 * 
		 * Nota: Para probar este proceso B debe ser lanzado antes que el proceso A. Sino el mensaje se pierde.
		*/
		
	
		
		//Variables que contendran los parametros solicitados por consola.
		InetAddress dirIP_A;
		Integer puerto_A, puerto_B;
	//    int cont=1;
		
	  //  while (cont>0)
	   // {
		try{
		
		
	   
	      
			String	direccionA = "localhost";//usamos una direccion(IP) por defecto.
			System.out.println("Direccion del emisor asignada => "+direccionA);
			String	puertoA = "3004";//PuertoA por defecto
			
			System.out.println("Puerto del emisor asignado => "+puertoA);
			
			
			String	puertoB = "3005";//PuertoB por defecto
			System.out.println("Puerto del receptor asignado => "+puertoB);
			
			//Conversiones de tipos entre entrada por teclado y lo que espera la clase socket.
			dirIP_A = InetAddress.getByName(direccionA);//cambio de tipos
			puerto_A = Integer.parseInt(puertoA);//para direcciones IP
			puerto_B = Integer.parseInt(puertoB);// y puertos
			System.out.println("Enviando: "+dirIP_A.toString()+ " " +puerto_A + " " + puerto_B);
			
			//Instanciamos un socket asociado al puerto de escucha y posterior envio.
			DatagramSocket socketB = new DatagramSocket (puerto_B);
			
			//Reservamos el buffer de recepcion y construimos el datagrama a recibir
			int MAX_LONGITUD = 1000;
			byte [] bufferRecepcion = new byte[MAX_LONGITUD];//Preparamos el buffer de recepcion
			//Preparamos el datagrama de respuesta a recibir
			DatagramPacket datagrama_respuesta = new DatagramPacket (bufferRecepcion,MAX_LONGITUD);
			
			System.out.println("Esperando envio de proceso manipulador!!! ");
			socketB.receive(datagrama_respuesta);//Realizamos la recepcion bloqueante
			
			//Procesamos la respuesta
			String mensaje = new String(bufferRecepcion);
		
		//TimeUnit.SECONDS.sleep(3); //TIEMPO DE ESPERA DE 6 SEGUN
		
			String respuesta = "L"; 
			                           
			byte [] bufferEnvio = respuesta.getBytes();//Preparamos el vector a enviar
			
			//preparamos el datagrama a enviar
			DatagramPacket datagrama_enviado = new DatagramPacket (//Preparamos el envio
						bufferEnvio,
						bufferEnvio.length,
						dirIP_A,
						puerto_A
					);
			
			//Enviamos la respuesta y cerramos la conexion.
		
			socketB.send(datagrama_enviado);//Realizamos el envio no bloqueante
			System.out.println("Auto: "+mensaje);
			socketB.close();
		}//fin try
		catch (Exception ex){
			ex.printStackTrace();
		}//fin catch
		
		
	//	}//while
		
	}

}
