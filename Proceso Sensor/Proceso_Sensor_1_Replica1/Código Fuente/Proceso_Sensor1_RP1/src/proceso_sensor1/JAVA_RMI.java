package proceso_sensor1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import servidorTest.Interface;

public class JAVA_RMI {
	private static String Accionx;
	private static String Patentex;
	private static String Slotx;
	JAVA_RMI(String accion ,String patente, String Slot ){
		Accionx=accion;
		Patentex=patente;
		Slotx=Slot;
		
														 }
	public synchronized void comunicar(){
		try{
			int puertoRMI;	
			InetAddress dirIPSRMI;
			//Direccion Server RMI
			String nombreServidor;
			//Puerto RMI
			String puerto = "1900";
						
			
			try {
				
				
				 File archivo2 = null;
			     FileReader fr2 = null;
			     BufferedReader br2 = null;
				 String DirDNS=null;
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

				System.setProperty("sun.net.spi.nameservice.nameservers", DirDNS);
				System.setProperty("sun.net.spi.nameservice.provider.1", "dn,sun");
				
			    //Consulto esta IP	
				dirIPSRMI = InetAddress.getByName("servidorobjdistribuidos.unm.ar");
                
			} catch (UnknownHostException e) {
				e.printStackTrace();
			}	
			dirIPSRMI = InetAddress.getByName("manipulador.unm.ar");
            		
		
		    nombreServidor=dirIPSRMI.toString();
		    
			puertoRMI = Integer.parseInt(puerto);
			Registry registro = LocateRegistry.getRegistry(nombreServidor, puertoRMI);
			Interface stub = (Interface) registro.lookup("registro");
			//System.out.println("Busqueda completada");
			String mensaje2 = stub.darEstado(Patentex,Slotx,Accionx);
			//System.out.println("Informacion recibida del objeto distribuido: " + mensaje2);
			String mensaje3=mensaje2;
			if(!(mensaje3.equals("L")|| mensaje2.equals("V"))) {
			mensaje2="E";	
			}
			
		   	BufferedWriter bw = null;
		    FileWriter fw = null;
		    try {     
		        File file = new File("Datos.txt");
		        fw = new FileWriter(file.getAbsoluteFile(),true);
		        bw = new BufferedWriter(fw);
		        bw.write(""+mensaje2);
		        bw.close();
		    } catch (IOException e) {
		        e.printStackTrace();
		    						} 			
		  }
		catch(Exception e){
			System.out.println("Excepcion en ObtenerEstado" + e);
						  }
	
									}		
						}