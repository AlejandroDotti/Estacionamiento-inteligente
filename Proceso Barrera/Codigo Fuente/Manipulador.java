package procesoBarrera;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Manipulador {

	public static void main(String[] args) {

		/**
		 * El proceso A envia una peticion a B y posteriormente espera una respuesta.
		 * A pide un texto al usuario por consola, y luego se lo envia a B. B recibe el texto,
		 * e indica la hora en que lo ha recibido y se lo devuelve al proceso A para que este
		 * lo muestre por pantalla.
		 * Necesitamos 3 argumentos por consola:
		 * <IP del proceso B>
		 * <Puerto de escucha del socket tipo datagrama instanciado en B>
		 * <Puerto al que enlazar el socket del proceso A>
		 * 
		 * Nota: Para probar este proceso A, debe ser lanzado despues que el proceso B
		 * @param args
		 */
		 
		//Instanciamos el buffer de entrada estandard.
		InputStreamReader is = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(is);
		
		//Variables que contendran los parametros solicitados por consola.
		InetAddress dirIP_B;
		Integer puerto_A, puerto_B;
		
		while (true) {
		
		try{
			System.out.println("Bienvenido al proceso manipulador.\n"+
					"Introduzca direccion IP del proceso barrera "
					);


			String direccionB = "192.168.1.35";//usamos la direccion por defecto
			//System.out.println("Direccion del receptor asignada => "+direccionB);
			
			//System.out.println("Introduzca el puerto de escucha de B ");
			//String puertoB = br.readLine();
			//if(puertoB.length() == 0)
			String puertoB = "5000";//PuertoB por defecto
			System.out.println("Puerto del receptor asignado => "+puertoB);
			
			//System.out.println("Introduzca el puerto de escucha del A ");
			//String puertoA = br.readLine();
			//if(puertoA.length() == 0)
			String puertoA = "5001";//PuertoA por defecto
			//System.out.println("Puerto del emisor asignado => "+puertoA);
			
			System.out.println("Introduzca la patente del auto: ");
			String mensaje = br.readLine();
			
			//Conversiones de tipos entre entrada por teclado y lo que espera la clase socket.
			dirIP_B = InetAddress.getByName(direccionB);//cambio de tipos
			puerto_A = Integer.parseInt(puertoA);//para direcciones IP
			puerto_B = Integer.parseInt(puertoB);// y puertos
			System.out.println("Enviando a: "+dirIP_B.toString()+ " " +puerto_A + " " + puerto_B);
			
			//Instanciamos un socket asociado al puerto de escucha y posterior envio.
			DatagramSocket socketA = new DatagramSocket (puerto_A);
			
			//Reservamos el buffer de envio y construimos el datagrama a enviar
			byte [] bufferEnvio = mensaje.getBytes();//Preparamos el vector a enviar
			
			DatagramPacket datagrama_peticion = new DatagramPacket (//Preparamos el envio
						bufferEnvio,
						bufferEnvio.length,
						dirIP_B,
						puerto_B
					);
			socketA.send(datagrama_peticion);//Realizamos el envio asicrono
			
			//Reservamos el buffer de recepcion y construimos el datagrama a recibir
			int MAX_LONGITUD = 7;
			byte [] bufferRecepcion = new byte[MAX_LONGITUD];//Preparamos el buffer de recepcion
			//Preparamos el datagrama de respuesta a recibir
			DatagramPacket datagrama_respuesta = new DatagramPacket (bufferRecepcion,MAX_LONGITUD);
			
			//Recibimos la respuesta del proceso B, la presentamos al usuario y cerramos la conexion.
			socketA.receive(datagrama_respuesta);//Realizamos la recepcion bloqueante
			String respuesta = new String(bufferRecepcion);
			System.out.println("La respuesta de B: " + respuesta);
			socketA.close();
		}//fin try
		catch (Exception ex){
			ex.printStackTrace();
		}//fin catch
		
		}

	}

}