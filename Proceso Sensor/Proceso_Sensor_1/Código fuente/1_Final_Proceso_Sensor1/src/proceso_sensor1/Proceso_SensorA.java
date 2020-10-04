package proceso_sensor1;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class Proceso_SensorA {
     static boolean p=true;
	public static void main(String[] args) {
	Abuson();
	}
	
	public static int Abuson()
	{	
	//	System.out.print("El Proceso Sensor se une la eleccion");
		InetAddress grupo;
		Integer puerto;
		
		try{
			String direccionGrupo = "224.5.6.7";
			String puertoSocket = "3009";
			String nickname = "4";
			
			grupo = InetAddress.getByName(direccionGrupo);//cambio de tipos
			puerto = Integer.parseInt(puertoSocket);// 
					
			MulticastSocket socket = new MulticastSocket(puerto); //Instanciamos el socket multicast acorde al puerto de escucha del grupo
			
			socket.joinGroup(grupo);
			socket.setTimeToLive(1);
					
			Thread hilo = new Thread (new HiloReceptor(socket, nickname));//Cuando estamos unidos al grupo lanzamos el thread de escucha que imprime en pantalla
			hilo.start(); //Lanzamos el hilo principal

		String IDRead=null;
		File archivo2 = null;
	    FileReader fr2 = null;
	     BufferedReader br2 = null;
	     
		int z=0; //Para vaciar el txt al inicio	
		
         if(z==0) {
        	 z=1;
				 FileWriter fichero = null;
			        PrintWriter pw = null;
			        try{
			            fichero = new FileWriter("Bully.txt");
			            pw = new PrintWriter(fichero); 
			            pw.println("");

			           } catch (Exception e1) {
			            e1.printStackTrace();
			                                  } finally {
			           try {
			           // Nuevamente aprovechamos el finally para 
			           // asegurarnos que se cierra el fichero.
			           if (null != fichero)
			              fichero.close();
			           	   } catch (Exception e2) {
			              e2.printStackTrace();
			           	   						   }
			                                           }       
         		 }	      
	   while(p) {
		          try {
			  
		    archivo2 = new File ("Bully.txt");			    
		    fr2 = new FileReader (archivo2);
		    br2 = new BufferedReader(fr2);
		    IDRead=br2.readLine();
		               }
		               catch(Exception e1){
		    e1.printStackTrace();
		                                  }finally{
		    try{                    
		       if( null != fr2 ){   
		          fr2.close();     
		                        }                  
		       }catch (Exception e2){ 
		       e2.printStackTrace();
		                            }
		                                           }
		if(IDRead==null) {
		IDRead="x";	
		}
		          
		if(IDRead.contains("1") ||(IDRead.contains("WIN") && IDRead.contains(nickname))){ //Cada vez que recibimos un uno enviamos nuestro ID por multicast

			String mensaje =nickname;
			DatagramPacket paquete = new DatagramPacket(mensaje.getBytes(),mensaje.length(),grupo,puerto); //Preparamos el datagrama y lo enviamos
			socket.send(paquete); //Enviamos nuestro Nick y ya estamos para arrancar
		    
			if(IDRead.contains("W")) {	//Si ganamos
			p=false;// Salimos del bucle
		                           }				
                     //Cada vez que llegue un uno vaciamos. Porque sino al no recibir mensajes el txt seguira conteniendo un 1 
		             //Lo que provocara que estemos enviando todo el día nuestro ID por multicast
					    FileWriter fichero = null;
				        PrintWriter pw = null;
				        try{
				             fichero = new FileWriter("Bully.txt");
				             pw = new PrintWriter(fichero); 
				             pw.println("");

				           } catch (Exception e1) {
				            e1.printStackTrace();
				                                  } finally {
				           try {
				           // Nuevamente aprovechamos el finally para 
				           // asegurarnos que se cierra el fichero.
				           if (null != fichero)
				              fichero.close();
				           	   } catch (Exception e2) {
				              e2.printStackTrace();
				           	   						   }
				                                             }       
		                        }
	            }
		}//fin try
		catch(Exception ex){
			ex.printStackTrace();
		                   }
	return 1;
                                         }
                }
