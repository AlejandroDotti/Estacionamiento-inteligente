package proceso_sensor1;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.MulticastSocket;

public class HiloReceptor implements Runnable {
	MulticastSocket socket;
	String nickname;
	
	HiloReceptor(MulticastSocket multicast_socket,String nickname_usuario){
		socket = multicast_socket;
		nickname = nickname_usuario;
																		  }

	public void run(){
			
		int MAX_LONGITUD = 10;	
				
		try{			
			byte [] bufferRecepcion = new byte [MAX_LONGITUD];			
			DatagramPacket datagrama_respuesta = new DatagramPacket(bufferRecepcion, MAX_LONGITUD); //Preparamos el datagrama de respuesta a recibir
			String auxW="0";
			while(true){
				socket.receive(datagrama_respuesta);				
				String respuesta = new String(datagrama_respuesta.getData(),datagrama_respuesta.getOffset(),datagrama_respuesta.getLength());		
				if(!respuesta.startsWith(nickname)){
					
					if(respuesta.contains("1")){					
					auxW="1";
					                           }
					if(respuesta.contains("WIN") && respuesta.contains(nickname)) {
					auxW=nickname+" WIN";	
					                              }
					
					
					
				   	BufferedWriter bw = null;
				    FileWriter fw = null;
				    try {     
				        File file = new File("Bully.txt");
				        fw = new FileWriter(file.getAbsoluteFile());
				        bw = new BufferedWriter(fw);
				        bw.write(auxW);//Escribimos en un txt la respuesta
				        bw.close();
				        } catch (IOException e) {
				        e.printStackTrace();
				    						    } 

					//System.out.println("Mensaje de otro miembro: " + respuesta); //Mensaje de prueba
				}
			}// fin while
		}//fin try
		catch(Exception ex){
			ex.printStackTrace();
		}//fin catch
	}	
}
