package procesoBarrera;

import java.awt.Color;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import javax.swing.plaf.synth.SynthOptionPaneUI;

import servidorTest.Interface;



public class ProcesoBarrera {

  //  static GUI_procesoBarrera WindProcesoBarrera = new GUI_procesoBarrera();
	
	
	public static void main(String[] args) throws IOException {

    try {
		
		  String s_IpDNSServer,s2_IpDNSServer;
	      String s_PuertoLocalEsuchaAlManipuladoir = null;
	      String s_ManipuladorPort = null;
		  String s_ServerRMI = null; //rmi
		  String s_puertoServerRmi = null;
		  String s_NumeroDeBarrera = null;
		  String s_ManipuladorIp = null;
		  String s_ManipuladorPortTrafico = null;
		  String s_ManipuladorPortKeepAlive = null;
		  String s_PortMultiCastBarreraBarreraTX = null;
		  String s_PortMultiCastBarreraBarreraRX = null;
		  String s_PortKeepAliveEnvio_a_ServerRMI = null;
		  String s_PortKeepAliveRecepcion_desde_ServerRMI = null;
		  
		  String tmp2,tmp,s_tmpFile,tmp3;
		  
		  int puertoRMI;
		  InetAddress IP_Manipulador, IpServerRMI,IpDNSServer;
		  Integer puerto_Manipulador, puerto_Own;
		  
		  int BarreraRespondioMulticast=0;
		  
			
		  File archivoConfiguracion =null;
	      FileReader fileR = null;
	      
	    	   File homedir = new File(System.getProperty("user.home"));
	    	   File fileToRead = new File(homedir, "BarrerasConfiguracion.txt");

//	      	   File Filetowrite = new File(tmp);
//		       FileWriter File_out_KeepAlive=new FileWriter(tmp);

	    	  // archivoConfiguracion = new File (fileToRead);   
	    	   fileR = new FileReader (fileToRead);
	    	   BufferedReader buffer = new BufferedReader (fileR);
	    	   tmp=buffer.readLine();
	    	   while (tmp.charAt(0)==35)// 35--> #
		    	   tmp=buffer.readLine();
	    	   s_IpDNSServer=SinEspacios(tmp);
	    	   
	    	   s_NumeroDeBarrera=SinEspacios(buffer.readLine());
	    	   s_PuertoLocalEsuchaAlManipuladoir=SinEspacios(buffer.readLine());
	    	   s_ServerRMI=SinEspacios(buffer.readLine());
	    	   s_puertoServerRmi=SinEspacios(buffer.readLine());
	    	   s_ManipuladorIp=SinEspacios(buffer.readLine());
	    	   s_ManipuladorPortTrafico=SinEspacios(buffer.readLine());
	    	   s_ManipuladorPortKeepAlive=SinEspacios(buffer.readLine());
	    	   s_PortMultiCastBarreraBarreraTX=SinEspacios(buffer.readLine());
	    	   s_PortMultiCastBarreraBarreraRX=SinEspacios(buffer.readLine());
	    	   s_PortKeepAliveEnvio_a_ServerRMI=SinEspacios(buffer.readLine());
	    	   s_PortKeepAliveRecepcion_desde_ServerRMI=SinEspacios(buffer.readLine());
	    	   
	 		   System.setProperty("sun.net.spi.nameservice.nameservers", s_IpDNSServer);
			   System.setProperty("sun.net.spi.nameservice.provider.1", "dn,sun");
			   InetAddress inetAddress;
	    	   System.out.println("Carga de configuracion desde file\n\n");
	    	   System.out.println("IP  DNS Server                                       : " + s_IpDNSServer);
	    	   System.out.println("Numero de Barrera                                    : " + s_NumeroDeBarrera );
	    	   System.out.println("Puerto local esucha al manipulador                   : "+ s_PuertoLocalEsuchaAlManipuladoir);
	    	   System.out.println("IP del Server RMI                                    : " + s_ServerRMI);
	    	   System.out.println("Puerto del Server RMI                                : " + s_puertoServerRmi);
	    	   System.out.println("IP del manipulador                                   : "+ s_ManipuladorIp);
	    	   System.out.println("Puerto de recepccion en el manipuador                : " + s_ManipuladorPortTrafico);
	    	   System.out.println("Puerto de recepcion de KeepAlive en el Manipipulador : " + s_ManipuladorPortKeepAlive);
	    	   System.out.println("Puerto proceso comunicacion Barrera-barrera TX       : " + s_PortMultiCastBarreraBarreraTX);
	    	   System.out.println("Puerto proceso comunicacion Barrera-barrera RX       : " + s_PortMultiCastBarreraBarreraRX);
	    	   System.out.println("Puerto recepcion de keepAlive en Server RMI          : " + s_PortKeepAliveEnvio_a_ServerRMI);
	    	   System.out.println("Puerto recepcion de keepAlive en Proceso Barrera     : " + s_PortKeepAliveEnvio_a_ServerRMI);

	    	   fileR.close();
	
	   try {
//    	   File homedir = new File(System.getProperty("user.home"));
    	   s_tmpFile= homedir+"/degugger_keep_alive.out";
       	   File filetowrite = new File(s_tmpFile);
   	       FileWriter fw;
		   fw = new FileWriter(filetowrite);
    	   BufferedWriter bw =new  BufferedWriter(fw);

    	   s_tmpFile= homedir+"/borrar.out";
	       File filetowriteBorrar = new File(s_tmpFile);
	   	   FileWriter fwBorrar;
	       fwBorrar = new FileWriter(filetowriteBorrar);
		   BufferedWriter bwBORRAR =new  BufferedWriter(fwBorrar);


/* 
     	   File filetowrite = new File(homedir, "degugger_keep_alive.out");
	    	   FileWriter fw=new FileWriter(filetowrite);
*/       		    	
//     	    FileWriter fw=  new FileWriter(filetowrite);
//	    	    BufferedWriter bw =new  BufferedWriter(fw);

			
			IpDNSServer = InetAddress.getByName("dns.unm.ar");
			s2_IpDNSServer=IpDNSServer.getHostAddress();
			if (s2_IpDNSServer.contentEquals(s_IpDNSServer))
			{
					puertoRMI = Integer.parseInt(s_puertoServerRmi);
				    GUI_procesoBarrera WindProcesoBarrera = new GUI_procesoBarrera(bw,filetowriteBorrar);
				    Thread Video=new Thread(new PlayVideo(WindProcesoBarrera));
					Video.start();
				    WindProcesoBarrera.setVisible(true);
		//		    Thread hilo_KeepAlive=new Thread(new hiloKeepAlive());
		
				    Thread hilo_KeepAlive=new Thread(new hiloKeepAlive(WindProcesoBarrera,bw,bwBORRAR));
				    hilo_KeepAlive.start();
					IP_Manipulador = InetAddress.getByName(s_ManipuladorIp+".unm.ar");
					IpServerRMI = InetAddress.getByName(s_ServerRMI+".unm.ar");
								  
					while (true) {
					try{
						System.out.println("\n ----------------------------");
						System.out.println("Bienvenido al proceso barrera");
						//Conversiones de tipos entre entrada por teclado y lo que espera la clase socket.
						//dirIP_Manipulador = InetAddress.getByName(s_ManipuladorIp);//cambio de tipos
		//				IP_Manipulador = InetAddress.getByName(s_ManipuladorIp+".unm.ar");
						
						puerto_Manipulador = Integer.parseInt(s_ManipuladorPortTrafico);//para direcciones IP
						puerto_Own = Integer.parseInt(s_PuertoLocalEsuchaAlManipuladoir);// y puertos
		
						//Instanciamos un socket asociado al puerto de escucha y posterior envio.
						DatagramSocket socketB = new DatagramSocket (puerto_Own);
						
						//Reservamos el buffer de recepcion y construimos el datagrama a recibir
						int MAX_LONGITUD = 7;
						byte [] bufferRecepcion = new byte[MAX_LONGITUD];//Preparamos el buffer de recepcion
						
						//Preparamos el datagrama de respuesta a recibir
						DatagramPacket datagrama_respuesta = new DatagramPacket (bufferRecepcion,MAX_LONGITUD);
						
						System.out.println("ESPERANDO AL MANIPULADOR!\n");
						
						socketB.receive(datagrama_respuesta);//Realizamos la recepcion bloqueante	
						System.out.println("Se recibio Msg desde Manipulador!\n");
						
						String mensaje = new String(bufferRecepcion);
						
						String patente="";
						int nroBarrera= -1;
						for (int n = 0; n <mensaje.length (); n ++) //SEPARAMOS LA PATENTE Y EL NUMERO DE BARRERA
						{ 
							if (n==0)
								nroBarrera = Character.getNumericValue(mensaje.charAt(n));
							if (n>0)
							{
								patente=patente + mensaje.charAt(n);
							}
						}
						System.out.println("Patente: "+ patente);
						System.out.println("Barrera que ingresa: "+ nroBarrera);
						
						WindProcesoBarrera.textNumBarrera.setText(String.valueOf(nroBarrera));
						
				
						//A PARTIR DE ACA YA TENEMOS LA PATENTE Y EL SLOT, HAY QUE ENVIARLO AL SERVIDOR RMI
		//				IpServerRMI = InetAddress.getByName(s_ServerRMI+".unm.ar");
						s_ServerRMI=IpServerRMI.getHostAddress();
						Registry registro = LocateRegistry.getRegistry(s_ServerRMI, puertoRMI);
						Interface stub = (Interface) registro.lookup("registro"); //Nos registramos en registro
						System.out.println("Busqueda completada, listo para registrar lugar");
						
						//Invocamos el metodo del objeto remoto y presentamos el resultado
						
						String respuestaServer = stub.reservar(patente); //envio la patente
						System.out.println("Respuesta servidor RMI: " + respuestaServer);  //Respuesta del 1al20 o LLENO
						
						if(respuestaServer.equals("DUPLICADO"))
						{
							String respuesta = "BAJA";
							
							byte [] bufferEnvio = respuesta.getBytes();//Preparamos el vector a enviar
							
							//preparamos el datagrama a enviar
							DatagramPacket datagrama_enviado = new DatagramPacket (//Preparamos el envio
										bufferEnvio,
										bufferEnvio.length,
										IP_Manipulador,
										puerto_Manipulador
									);
							socketB.send(datagrama_enviado);//Realizamos el envio no bloqueante
							WindProcesoBarrera.txtPatente.setText(patente);
							WindProcesoBarrera.txtCochera_Num.setText("E--");
							WindProcesoBarrera.txtIrPorCalle.setText("---");
							WindProcesoBarrera.txtEstacionamientoLleno.setBackground(Color.white);
							WindProcesoBarrera.txtEstacionamientoLleno.setText("PATENTE DUPLICADA");
		
			            }
									
						
						if ((respuestaServer.equals ("LLENO")) || (respuestaServer.equals("REGISTRESE"))){
							
							String marca = "BAJA";
							String respuesta = marca;
							byte [] bufferEnvio = respuesta.getBytes();//Preparamos el vector a enviar
							
							//preparamos el datagrama a enviar
							DatagramPacket datagrama_enviado = new DatagramPacket (//Preparamos el envio
										bufferEnvio,
										bufferEnvio.length,
										IP_Manipulador,
										puerto_Manipulador
									);
							
							socketB.send(datagrama_enviado);//Realizamos el envio no bloqueante
							
							if (respuestaServer.equals ("LLENO"))
							{
							WindProcesoBarrera.txtPatente.setText(patente);
							WindProcesoBarrera.txtCochera_Num.setText("E--");
							WindProcesoBarrera.txtIrPorCalle.setText("---");
							WindProcesoBarrera.txtEstacionamientoLleno.setBackground(Color.RED);
							WindProcesoBarrera.txtEstacionamientoLleno.setText("Estacionamiento LLENO");
							}
							else
							{
								WindProcesoBarrera.txtPatente.setText(patente);
								WindProcesoBarrera.txtCochera_Num.setText("E--");
								WindProcesoBarrera.txtIrPorCalle.setText("---");
								WindProcesoBarrera.txtEstacionamientoLleno.setBackground(Color.white);
								WindProcesoBarrera.txtEstacionamientoLleno.setText("COMUNIQUESE CON EL ADMINISTRADOR");
							}
							System.out.println("Se ha enviando: " + respuesta);
		
									
						}
						
						else {
							
							if (!respuestaServer.contentEquals("DUPLICADO"))
							{	
							      int numParcela = Integer.parseInt(respuestaServer);
							
							if ((numParcela>0) && (numParcela<6)) //PRIMERO
							{
								String respuesta = "ALTA";
							
								byte [] bufferEnvio = respuesta.getBytes();//Preparamos el vector a enviar
								
								//preparamos el datagrama a enviar
								DatagramPacket datagrama_enviado = new DatagramPacket (//Preparamos el envio
											bufferEnvio,
											bufferEnvio.length,
											IP_Manipulador,
											puerto_Manipulador
										);
										
								
								socketB.send(datagrama_enviado);//Realizamos el envio no bloqueante
								
								
								//Modificamos la ventana
								WindProcesoBarrera.txtPatente.setText(patente);
								WindProcesoBarrera.txtCochera_Num.setText("E" + respuestaServer);
								
								//FALTA POR QUE CALLE IR PERO ES FUNCION DEL NRO BARRERA
								
								WindProcesoBarrera.txtIrPorCalle.setText("1");
								WindProcesoBarrera.txtEstacionamientoLleno.setBackground(Color.GREEN);
								WindProcesoBarrera.txtEstacionamientoLleno.setText("Hay lugar");
								
								System.out.println("Se ha enviando: "+respuesta);
								
								//POR CADA IF HAY QUE AGREGAR LO QUE HAY QUE PONER EN LAS VENTANAS
							}
							
							if ((numParcela>5) && (numParcela<11)) //SEGUNDO
							{
								String respuesta = "ALTA";
							
								byte [] bufferEnvio = respuesta.getBytes();//Preparamos el vector a enviar
								
								//preparamos el datagrama a enviar
								DatagramPacket datagrama_enviado = new DatagramPacket (//Preparamos el envio
											bufferEnvio,
											bufferEnvio.length,
											IP_Manipulador,
											puerto_Manipulador
										);
										
								
								socketB.send(datagrama_enviado);//Realizamos el envio no bloqueante
								
								
								//Modificamos la ventana
								WindProcesoBarrera.txtPatente.setText(patente);
								WindProcesoBarrera.txtCochera_Num.setText("E" + respuestaServer);
								
								//FALTA POR QUE CALLE IR PERO ES FUNCION DEL NRO BARRERA
								
								WindProcesoBarrera.txtIrPorCalle.setText("1");
								WindProcesoBarrera.txtEstacionamientoLleno.setBackground(Color.GREEN);
								WindProcesoBarrera.txtEstacionamientoLleno.setText("Hay lugar");
								
								System.out.println("Se ha enviando: "+respuesta);
								
								//POR CADA IF HAY QUE AGREGAR LO QUE HAY QUE PONER EN LAS VENTANAS
							}
							
							if ((numParcela>10) && (numParcela<16)) //TERCERO
							{
								String respuesta = "ALTA";
							
								byte [] bufferEnvio = respuesta.getBytes();//Preparamos el vector a enviar
								
								//preparamos el datagrama a enviar
								DatagramPacket datagrama_enviado = new DatagramPacket (//Preparamos el envio
											bufferEnvio,
											bufferEnvio.length,
											IP_Manipulador,
											puerto_Manipulador
										);
										
								
								socketB.send(datagrama_enviado);//Realizamos el envio no bloqueante
								
								
								//Modificamos la ventana
								WindProcesoBarrera.txtPatente.setText(patente);
								WindProcesoBarrera.txtCochera_Num.setText("E" + respuestaServer);
								
								//FALTA POR QUE CALLE IR PERO ES FUNCION DEL NRO BARRERA
								
								WindProcesoBarrera.txtIrPorCalle.setText("3");
								WindProcesoBarrera.txtEstacionamientoLleno.setBackground(Color.GREEN);
								WindProcesoBarrera.txtEstacionamientoLleno.setText("Hay lugar");
								
								System.out.println("Se ha enviando: "+respuesta);
								
								//POR CADA IF HAY QUE AGREGAR LO QUE HAY QUE PONER EN LAS VENTANAS
							}
							
							if ((numParcela>15) && (numParcela<21)) //CUARTO
							{
								String respuesta = "ALTA";
							
								byte [] bufferEnvio = respuesta.getBytes();//Preparamos el vector a enviar
								
								//preparamos el datagrama a enviar
								DatagramPacket datagrama_enviado = new DatagramPacket (//Preparamos el envio
											bufferEnvio,
											bufferEnvio.length,
											IP_Manipulador,
											puerto_Manipulador
										);
								socketB.send(datagrama_enviado);//Realizamos el envio no bloqueante
								//Modificamos la ventana
								WindProcesoBarrera.txtPatente.setText(patente);
								WindProcesoBarrera.txtCochera_Num.setText("E" + respuestaServer);
								
								//FALTA POR QUE CALLE IR PERO ES FUNCION DEL NRO BARRERA
								
								WindProcesoBarrera.txtIrPorCalle.setText("3");
								WindProcesoBarrera.txtEstacionamientoLleno.setBackground(Color.GREEN);
								WindProcesoBarrera.txtEstacionamientoLleno.setText("Hay lugar");
								
								System.out.println("Se ha enviando: "+respuesta);
								
								//POR CADA IF HAY QUE AGREGAR LO QUE HAY QUE PONER EN LAS VENTANAS
							}
		
		
						}
					
						}
					socketB.close();
						
						   
					}//fin try
					catch (Exception ex){
						ex.printStackTrace();
					}//fin catch
					
					}
			}
			else
			{
		    	System.out.println("");
		    	System.out.println("");
		    	System.out.println("******************************************************************************************************");
				System.out.println("************ No hay conexion con el DNS Server, solucionar el problema y volver a intentar ***********");
		    	System.out.println("******************************************************************************************************");
				
			}
	    } catch (UnknownHostException e) {
		// TODO Auto-generated catch block
		//e.printStackTrace();
	    	System.out.println("");
	    	System.out.println("");
	    	System.out.println("******************************************************************************************************");
			System.out.println("************ No hay conexion con el DNS Server, solucionar el problema y volver a intentar ***********");
	    	System.out.println("******************************************************************************************************");
	    	
	  }
    }
    catch (Exception e) {
    	System.out.println("NO es posible cargar la configuracion, verifique que exista el archivo de confifuracion (BarrerasConfiguracion.txt) en el directorio de trabajo del user");
    }


	}
	
    public static String SinEspacios (String X) {
    	int a,b,c;
    	int [] ptr = new int [4];
    	ptr[0]=X.indexOf(" ");    	
    	ptr[1]=X.indexOf("#");
    	ptr[2]=X.indexOf("	");
    	ptr[3]=255;
    	for (int i=0; i<=2;i++)
    		if (ptr[i]<0)
    			ptr[i]=255;
    	for (int i=0; i<=2;i++)
    	{
    		if (ptr[i]<=ptr[3])
    			ptr[3]=ptr[i];
   	}
   	    X=X.substring(0, ptr[3]);
           return X;
    }
    
  


}