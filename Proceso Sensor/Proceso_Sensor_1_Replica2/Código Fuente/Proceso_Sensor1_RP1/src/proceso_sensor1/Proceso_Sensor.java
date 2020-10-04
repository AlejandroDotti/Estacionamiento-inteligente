package proceso_sensor1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.UnknownHostException;




class Hilo1 extends Thread{
	private JAVA_RMI obj; 	
	Hilo1(JAVA_RMI objValor){
		obj=objValor;
							}	
	public void run() {						
		obj.comunicar();
					  }						
						  }

class Hilo2 extends Thread{
	private JAVA_RMI obj; 	
	Hilo2(JAVA_RMI objValor){
		obj=objValor;
							}	
	public void run() {	
		obj.comunicar();
					  }						
						  }

class Hilo3 extends Thread{
	private JAVA_RMI obj; 
	
	Hilo3(JAVA_RMI objValor){
		obj=objValor;
							}	
	public void run() {	
		obj.comunicar();
					  }						
						  }

class Hilo4 extends Thread{
	private JAVA_RMI obj; 
	Hilo4(JAVA_RMI objValor){
		obj=objValor;
	}
	public void run() {	
		obj.comunicar();
					  }						
						  }

class Hilo5 extends Thread{
	private JAVA_RMI obj; 	
	Hilo5(JAVA_RMI objValor){
		obj=objValor;
	                        }
	public void run() {	

		obj.comunicar();
					  }						
						  }

public class Proceso_Sensor {
	
	static Proceso_Sensor_Int iGrafica =new Proceso_Sensor_Int();

	
private static String patente="";
private static String Accion="";
private static String Slot="";

public static void setAccion(String accion) {
	Accion = accion;
											}
public static void setPatente(String Patente) {
	patente = Patente;
										}
public static void setSlot(String slot) {
	Slot = slot;
							            }
public static String getAccion() {
	return Accion;
								 }
public static String getSlot() {
	return Slot;
							   }

public static String getPatente() {
	return patente;
								  }

	public static void main(String[] args) {

		iGrafica.frmProcesoSensor.setVisible(true);
		
		new Proceso_SensorA();
		Proceso_SensorA.Abuson();

		InetAddress dirIP_MS; //direccion IP de quien nos envia el mensaje, en este caso el proceso manipulador
		Integer puerto_MS; //Puerto en el que escuchamos al manipulador del sensor(asignamos por defecto 3000)
		Integer puerto_PS; //Puerto en el que PS enviara la respuesta (asignamos por defecto 3001)
		 String DirDNS = null;
		try{  		
			 try {
				 
				 File archivo2 = null;
			     FileReader fr2 = null;
			     BufferedReader br2 = null;
				
				try {
					  
				    archivo2 = new File ("IP.txt");			    
				    fr2 = new FileReader (archivo2);
				    br2 = new BufferedReader(fr2);

                 DirDNS=br2.readLine();
				 
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
	
			System.setProperty("sun.net.spi.nameservice.nameservers",DirDNS);
			System.setProperty("sun.net.spi.nameservice.provider.1", "dn,sun");
			
		    //Consulto esta IP	
			dirIP_MS = InetAddress.getByName("manipulador.unm.ar");

		} catch (UnknownHostException e) {
			e.printStackTrace();
		}	
		dirIP_MS = InetAddress.getByName("manipulador.unm.ar");

	puerto_MS = Integer.parseInt("3000");//cambio de tipo de entero a Integer
	puerto_PS = Integer.parseInt("3001");//cambio de tipo de entero a Integer
	
			int p=0;
			while(p == 0) {
				DatagramSocket socketPS = new DatagramSocket (puerto_PS);
			int MAX_LONGITUD = 20;
			byte [] bufferRecepcion = new byte[MAX_LONGITUD];//Preparamos el buffer de recepcion
			DatagramPacket datagrama_respuesta = new DatagramPacket (bufferRecepcion,MAX_LONGITUD);
			
			//System.out.println("Esperando envio del manipulador!!!");
			socketPS.receive(datagrama_respuesta);//Realizamos la recepcion bloqueante

			String mensaje = new String(bufferRecepcion);
			//System.out.println("Datos recibidos del manipulador>> "+mensaje);
			//ya tenemos el mensaje que recibimos, en el String mensaje
				       
			int i=0;
			String[] datos = mensaje.split(" ");
			for(String item : datos) {	
			switch(i) {
			
			case 0:
				Proceso_Sensor.setPatente(item);
				break;
			case 1:
				Proceso_Sensor.setSlot("0"+item);
				break;
			case 2:
				int j=0;
				String[] datos2 = item.split("");
				for(String item2 : datos2) {	
				
				switch(j) {
				case 0:
					
					if(item2.equals("E")) {
						item2="EST";
					Proceso_Sensor.setAccion(item2);
					}
					else {item2="LIB";
					Proceso_Sensor.setAccion(item2);
						 
					}
					j++;
					break;
				default:
				break;	
					}                }				
				i=-1;				
				break;			
				default:
	            break;		
					  }
				i++;
									  }	
			
			 FileWriter fichero = null;
		        PrintWriter pw = null;
		        try{
		            fichero = new FileWriter("Datos.txt");
		            pw = new PrintWriter(fichero); 
		            pw.println(Proceso_Sensor.getPatente()+" "+Proceso_Sensor.getSlot()+" "+Proceso_Sensor.getAccion());

		           } catch (Exception e) {
		            e.printStackTrace();
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
			
			//Proceso_Sensor.getPatente()+""+Proceso_Sensor.getSlot()+""+Proceso_Sensor.getAccion()
			//System.out.println("Accion\t:"+Proceso_Sensor.getAccion());	
			//System.out.println("Patente\t:"+Proceso_Sensor.getPatente());	
			//System.out.println("Slot\t:"+Proceso_Sensor.getSlot());	
	
			  JAVA_RMI cnt = new JAVA_RMI(Proceso_Sensor.getAccion(),Proceso_Sensor.getPatente(),Proceso_Sensor.getSlot());	
			  int Test = Integer.parseInt("0"+Proceso_Sensor.getSlot());
			  switch (Test) {
			  case 01:	
				  Hilo1 t1 = new Hilo1(cnt);
				  t1.start();				  
					try {
						t1.join();	
						} catch (InterruptedException e) {
						e.printStackTrace();
														 }
			  	break;
			  case 02:	
				  	 Hilo2 t2 = new Hilo2(cnt);  
				  	 t2.start();
				  	try {
						t2.join();	
					} catch (InterruptedException e) {
						e.printStackTrace();
													 }
						break;
			   case 03:	
				  Hilo3 t3 = new Hilo3(cnt);  
				  	 t3.start();
				  	try {
						t3.join();	
					    } catch (InterruptedException e) {
						e.printStackTrace();
													     }
				  	    break;	  		  	
			   case 04:	
				  Hilo4 t4 = new Hilo4(cnt);  
				  	 t4.start();
				  	try {
						t4.join();	
					    } catch (InterruptedException e) {
						e.printStackTrace();
													     }
				  	    break;	  			  	
			   case 05:	
				  Hilo5 t5 = new Hilo5(cnt);  
				  	 t5.start();
				  	try {
						t5.join();	
					    } catch (InterruptedException e) {
						e.printStackTrace();
					                                     }
				  		break;	
			  default:
				  System.out.println("Datos erroneos");	
			  	break;
			  				}//fin JAVA RMI  

			
             int k=0;	 
			 File archivo = null;
		      FileReader fr = null;
		      BufferedReader br3 = null;
		      String respuesta = null;

		      try {
		         archivo = new File ("Datos.txt");
		         
		         fr = new FileReader (archivo);
		         br3 = new BufferedReader(fr);
		         String linea;
		         
		         while((linea=br3.readLine())!=null) {
			         k++;
		        	 respuesta=linea;
			         if(!(respuesta.equals("L")||respuesta.equals("V")||respuesta.equals("R")))
			        	 respuesta="error"; 
			             
			       }
		         if(k==1) {
		        	respuesta="Error_Com._con_servidor_rmi"; 
		 		   	BufferedWriter bw = null;
				    FileWriter fw = null;
				    try {     
				        File file = new File("Datos.txt");
				        fw = new FileWriter(file.getAbsoluteFile(),true);
				        bw = new BufferedWriter(fw);
				        bw.write(""+respuesta);
				        bw.close();
				    } catch (IOException e) {
				        e.printStackTrace();
				    						} 			
				      
				catch(Exception e){
					System.out.println("Excepcion en ObtenerEstado" + e);
								  }
		         }
                   
		         //   System.out.println("Leimos de Datos:"+respuesta);
		      }
		      catch(Exception e){
		         e.printStackTrace();
		      }finally{

		         try{                    
		            if( null != fr ){   
		               fr.close();     
		            }                  
		         }catch (Exception e2){ 
		            e2.printStackTrace();
		         }
		      }

				byte [] bufferEnvio = respuesta.getBytes();//Preparamos el vector a enviar
				DatagramPacket datagrama_enviado = new DatagramPacket (//Preparamos el envio, el sockets lo enviamos con el formato necesario
							bufferEnvio,  
							bufferEnvio.length,
							dirIP_MS,
							puerto_MS
						);				
				System.out.println();
				socketPS.send(datagrama_enviado);//Realizamos el envio no bloqueante
				socketPS.close();
			}//fin try
		}
			catch (Exception ex){
				ex.printStackTrace();
								}//fin catch
									}
							}