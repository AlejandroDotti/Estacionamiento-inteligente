package procesoBarrera;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class hiloKeepAlive extends Thread {


	public   File homedir    = new File(System.getProperty("user.home"));
	public	 File filetowrite = new File(homedir, "degugger_keep_alive.out");
	

    	   
 //   public   BufferedWriter bw;

	public static  int Flag=0;
	public static  int CounterAux=0;
	public static  int ProcesoMultiCastActivo=0;
	int BarreraRespondioMulticast_tmp=0;
	int FlagEscuchaSolictudMulticast=0;
	long  NummeroKeepAliveenviados=0;
	GUI_procesoBarrera WindProcesoBarrera;
	BufferedWriter bw;
	BufferedWriter Bwoutborrar;
	boolean b_ServerRMI_ok=false;
	boolean degugger_keep_aliveON=false;


	
    public hiloKeepAlive (GUI_procesoBarrera frame,BufferedWriter bw1,BufferedWriter bwBORRAR)
    {
        bw=bw1;
        Bwoutborrar=bwBORRAR;
    	WindProcesoBarrera=frame;

    }
	public void run () {
	while (true)	
	{
	//public  hiloKeepAlive(GUI_procesoBarrera frame)
	//{
		    InputStreamReader is = new InputStreamReader(System.in);
			BufferedReader br = new BufferedReader(is);
			InetAddress dirIP_Servdir_RMI,dirIP_Manipuldor,dirIP_Manipulaor,IPvideomulticast;
			Integer puerto_Servdir_RMI, puerto_ClienteBarreraEscuchaalServerRMI,puerto_EstadoManipulador,puertoManipuladorKeepaAlive;
			Integer TimerKeepAlive,GrupoBarreras_PortTX,MulticastGrupoBarreras_PortTX,GrupoBarreras_PortRX,TimerEnvioKeepAliveServerRMI;
			String tmp,s_degugger_procesoBarrera;
			String NumBarreraPropia;
			String String_Msg="OK";
			String String_Msg_maniúlador1="1";// hay conexion
			String String_Msg_maniúlador0="0";// no hay conexion con Server RMI
			String String_Msg_manipulador="OK";
			File archivoConfiguracion =null;
		    FileReader fileR = null;
     	    PrintWriter out =new PrintWriter(bw);

 
     	    
		    try {
		    	   File homedir    = new File(System.getProperty("user.home"));
		    	   File fileToRead = new File(homedir, "BarrerasConfiguracion.txt");
		    	   fileR = new FileReader (fileToRead);
		    	   BufferedReader buffer = new BufferedReader (fileR);        
		    	   tmp=buffer.readLine();
		    	   while (tmp.charAt(0)==35)// 35--> #
			    	   tmp=buffer.readLine();
		    	   //buffer.readLine();
		    	   NumBarreraPropia=(SinEspacios(buffer.readLine())); // Numero de Barrera 
		    	   buffer.readLine();
    	    	   dirIP_Servdir_RMI= InetAddress.getByName(SinEspacios(buffer.readLine())+".unm.ar");    // IP RMI
		    	   buffer.readLine();
    	    	   dirIP_Manipulaor= InetAddress.getByName(SinEspacios(buffer.readLine())+".unm.ar");     // IP proceso barrera
		    	   buffer.readLine();
		    	   puertoManipuladorKeepaAlive= Integer.parseInt(SinEspacios(buffer.readLine()));  //Puerto en el cual escucha barrera estado de servidor
				   GrupoBarreras_PortTX = Integer.parseInt(SinEspacios(buffer.readLine()));      //Puerto 3002 
 			       GrupoBarreras_PortRX = Integer.parseInt(SinEspacios(buffer.readLine()));      //Puerto 3003 
		    	   puerto_Servdir_RMI= Integer.parseInt(SinEspacios(buffer.readLine()));        // Puerto RMI
 			       puerto_ClienteBarreraEscuchaalServerRMI= Integer.parseInt(SinEspacios(buffer.readLine()));      // puerto escucha de respuesta desde Servidor RMI
 			       IPvideomulticast = InetAddress.getByName(SinEspacios(buffer.readLine())+".unm.ar");     // IP Multicast
 			       s_degugger_procesoBarrera=SinEspacios(buffer.readLine());
 			       if (s_degugger_procesoBarrera.equals("degugger_keep_alive_ON"))
 			    	  degugger_keep_aliveON=true;
		    	   buffer.readLine();
 			      TimerKeepAlive = Integer.parseInt(SinEspacios(buffer.readLine()));      //Puerto 3003 
 			      TimerEnvioKeepAliveServerRMI = Integer.parseInt(SinEspacios(buffer.readLine()));      //Puerto 3003 
 			      
 			       fileR.close();

                   if (degugger_keep_aliveON)
                   {
       		    	   out.println("*******************************************************************************************");
       		    	   out.println("**************************   degugger_keep_aliveON    *************************************");
       		    	   out.println("*******************************************************************************************");
       		    	   out.println("*******************************************************************************************");
       		    	   out.println("NumBarreraPropia                        ="+NumBarreraPropia+"");
       		    	   out.println("dirIP_Servdir_RMI                       ="+dirIP_Servdir_RMI+"");
       		    	   out.println("dirIP_Manipulaor                        ="+dirIP_Manipulaor+"");
       		    	   out.println("puertoManipuladorKeepaAlive             ="+puertoManipuladorKeepaAlive+"");
       		    	   out.println("GrupoBarreras_PortTX                    ="+GrupoBarreras_PortTX+"");
       		    	   out.println("GrupoBarreras_PortRX                    ="+GrupoBarreras_PortRX+"");
       		    	   out.println("puerto_Servdir_RMI                      ="+puerto_Servdir_RMI+"");
       		    	   out.println("puerto_ClienteBarreraEscuchaalServerRMI ="+puerto_ClienteBarreraEscuchaalServerRMI+"");
       		    	   out.println("IPvideomulticast                        ="+IPvideomulticast+"");
       		    	   out.print  ("s_degugger_procesoBarrera               =");
       		    	   if (degugger_keep_aliveON) 
       		    	      out.println("ACTIVO");
       		    	   else
       		    		   out.println("PASIVO");
       		    	   out.println  ("Timer keepAlive                         =" +TimerKeepAlive );
       		    	   out.println  ("Timer envio keepAlive al Server RMI     =" +TimerEnvioKeepAliveServerRMI );
     		    	
       		       	   out.println("*******************************************************************************************");
       		    	   out.println("*******************************************************************************************");
                   }
		    	   
		    	   
		    	   
		    	   int MAX_LONGITUD_Multicast=10;
					byte [] bufferRecepcion = new byte [MAX_LONGITUD_Multicast];
			    	
		    	    // mensajes UDP unicast chequeo conexion contra el OBJ_RMI//
		    	    DatagramSocket Socket_Enviar = new DatagramSocket (puerto_ClienteBarreraEscuchaalServerRMI);//Port 3000
					byte [] bufferEnvio = String_Msg.getBytes();//Preparamos el vector a enviar
					DatagramPacket datagrama_enviar = new DatagramPacket(bufferEnvio,bufferEnvio.length,dirIP_Servdir_RMI,puerto_Servdir_RMI);

					
		    	    // mensajes UDP unicast aviso de estado servidor al manupilador//
		    	    DatagramSocket Socket_EnviarEstadoManipulador = new DatagramSocket (puertoManipuladorKeepaAlive);//Port 3000
		    	    byte [] bufferEnvioestadoManipulador1 = String_Msg_maniúlador1.getBytes();
					DatagramPacket datagrama_enviar_Estado_a_Manipulador1 = new DatagramPacket(bufferEnvioestadoManipulador1,bufferEnvioestadoManipulador1.length,dirIP_Manipulaor,puertoManipuladorKeepaAlive);
		    	    byte [] bufferEnvioestadoManipulador0 = String_Msg_maniúlador0.getBytes();
					DatagramPacket datagrama_enviar_Estado_a_Manipulador0 = new DatagramPacket(bufferEnvioestadoManipulador0,bufferEnvioestadoManipulador0.length,dirIP_Manipulaor,puertoManipuladorKeepaAlive);

					
					
				
//					Integer GrupoBarreras_PortRX;
//				    String direccionGrupo = "231.1.1.1";//IPvideomulticast
//				    IPvideomulticast = InetAddress.getByName(direccionGrupo);//cambio de tipos
//				    String puertoGrupoTX = "3002";//Puerto por defecto

//				    String GrupoBarreras_PortRX_txt = "3003";//Puerto al cual se RESPONDE cuando otra barrera solicita ayuda
//				    GrupoBarreras_PortRX = Integer.parseInt(GrupoBarreras_PortRX_txt);// 
				    
//				    String MulticastGrupoBarreras_PortTX_txt = "3002";//Puerto donde se escucha por solicita ayuda desde otras barreras
//				    MulticastGrupoBarreras_PortTX = Integer.parseInt(MulticastGrupoBarreras_PortTX_txt);// 
	        
	
					// Multicast recepcion de respuesta de Barrera_Activa //
//				    String GrupoBarreras_PortTX = "3003";//Puerto por defecto
					
					String MensajeEcho ="SOS"+NumBarreraPropia;

				    
					DatagramPacket datagrama_respuesta = new DatagramPacket(bufferRecepcion, MAX_LONGITUD_Multicast);
				
	        		MulticastSocket socketGrupoMulticast_TX=new MulticastSocket(GrupoBarreras_PortRX);
	        		socketGrupoMulticast_TX.setInterface(InetAddress.getLocalHost());
					socketGrupoMulticast_TX.joinGroup(IPvideomulticast);
					// Solictando ayuda a otra barreras
					DatagramPacket MensajeMulticastEnvioSolicitud     = new DatagramPacket(MensajeEcho.getBytes(),MensajeEcho.length(),IPvideomulticast,GrupoBarreras_PortTX); //3002 pidiendo Ayuda
					DatagramPacket MensajeMulticastReespuestaSolictud = new DatagramPacket(bufferRecepcion,MAX_LONGITUD_Multicast);//port 3003
	

					MulticastSocket socketGrupoMulticast_RX = new MulticastSocket(GrupoBarreras_PortTX);
					socketGrupoMulticast_RX.setInterface(InetAddress.getLocalHost());
					socketGrupoMulticast_RX.joinGroup(IPvideomulticast);
					socketGrupoMulticast_RX.setTimeToLive(10);
					// brindando ayuda a otras barreras
					DatagramPacket MensajeMulticastRecepcionSolictud      = new DatagramPacket(bufferRecepcion,MAX_LONGITUD_Multicast);//port 3002
					DatagramPacket MensajeMulticastEnvioRespuestaSolicitud= new DatagramPacket(NumBarreraPropia.getBytes(),NumBarreraPropia.length(),IPvideomulticast,GrupoBarreras_PortRX); //3003 pidiendo Ayuda
//					socketGrupoMulticast_TX.leaveGroup(IPvideomulticast);



					
				do {
					Socket_Enviar.send(datagrama_enviar);//Envia un OK Server RMI (para saber si esta activo) 
					NummeroKeepAliveenviados++;
                   if (degugger_keep_aliveON)
                   {
                	out.println();
					out.println("Se envio un keepAlive al Server RMI, N° de msg = "+NummeroKeepAliveenviados+ " uno cada " + TimerEnvioKeepAliveServerRMI + " milisegundos");
                   }
					Flag=0; //Se envio el OK al Server RMI
					Thread Timer=new Thread(){
						public void run(){
							try {
								Thread.sleep(TimerKeepAlive);
								Flag=2;// 2 porque el timer expiro
				                   if (degugger_keep_aliveON)
				                   {
				                	   out.println("");
				                	   out.println("		Hilo TIMER, Expiro el TIMER, no se recibio respuesta desde el Server RMI, Timer = "+ TimerKeepAlive + " milisegundos");
				                   }
				                   try {
									Socket_EnviarEstadoManipulador.send(datagrama_enviar_Estado_a_Manipulador0);
					                   if (degugger_keep_aliveON)
					                   {
					                	   out.print("		Hilo TIMER, Se envio estado NO disponible del ProcesoBarrera-ServerRMI al MANIPULAODR, desde el Proceso Barrera N°" + NumBarreraPropia);
					                   }
					                   b_ServerRMI_ok=false;

								} catch (IOException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}

							} catch (InterruptedException e) {
							}
						}
					};
					Thread EsperaRespuestaServerRMI=new Thread(){
						public void run(){
							int MAX_LONGITUD = 10;
							byte [] bufferRecepcion = new byte[MAX_LONGITUD];    //Preparamos el buffer de recepcion
							DatagramPacket datagrama_respuesta = new DatagramPacket (bufferRecepcion,MAX_LONGITUD);
							try {
				                   if (degugger_keep_aliveON)
				                   {
				                	   out.println("");
				                	   out.println("	Hilo EsperaRespuestaServerRMI, Esperando MSG desde el Server RMI");
				                   }
									Socket_Enviar.receive(datagrama_respuesta);
									BarreraRespondioMulticast_tmp=0;
									b_ServerRMI_ok=true;
					                   if (degugger_keep_aliveON)
					                   {
					                	   out.println("	Hilo EsperaRespuestaServerRMI,Se recibio Respuesta al keepAlive desde Server RMI  CONEXION OK SERVER_RMI-PROCESOBARRERA");
					                   }
					            } catch (IOException e) {
								e.printStackTrace();
							}

							try {
								Thread.sleep(TimerEnvioKeepAliveServerRMI);
								Flag=1;//vale 1 porque se recibiò el ok desde OBJ_DISTR
								ProcesoMultiCastActivo=0;
								try {
									Socket_EnviarEstadoManipulador.send(datagrama_enviar_Estado_a_Manipulador1);
				                   if (degugger_keep_aliveON)
				                   {
									  out.println("");
									  out.println("			Hilo EsperaRespuestaServerRMI,Se envio estado DISPONIBLE del ProcesoBarrera-ServerRMI al MANIPULADOR , desde el Proceso Barrera N°"+NumBarreraPropia );
				                   }
				                   } catch (IOException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}// enviando estado de Servidor RMi a manipulador
								
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}
					};
					
					Thread escuchaSolictudMulticast=new Thread(){
						public void run(){
							try {
                                String QuienPidioAyuda="";
				                   if (degugger_keep_aliveON)
				                    {
                                      out.println("");
                                      out.println("				Hilo escuchaSolictudMulticast");
					                }
                					MulticastSocket socketGrupoMulticast_RX = new MulticastSocket(GrupoBarreras_PortTX);
                					socketGrupoMulticast_RX.setInterface(InetAddress.getLocalHost());
                					socketGrupoMulticast_RX.joinGroup(IPvideomulticast);
                					socketGrupoMulticast_RX.setTimeToLive(10);
                					// brindando ayuda a otras barreras
                					DatagramPacket MensajeMulticastRecepcionSolictud      = new DatagramPacket(bufferRecepcion,MAX_LONGITUD_Multicast);//port 3002
            			        	
                					DatagramPacket MensajeMulticastEnvioRespuestaSolicitud= new DatagramPacket(NumBarreraPropia.getBytes(),NumBarreraPropia.length(),IPvideomulticast,GrupoBarreras_PortRX); //3003 pidiendo Ayuda
//                					socketGrupoMulticast_TX.leaveGroup(IPvideomulticast);
        			            	FlagEscuchaSolictudMulticast=1;
            						do           						
            						{	
            						// Multicast se envia solicitud  de respuesta de Barrera_Activa //
            						if (degugger_keep_aliveON)
    				                {
                                        out.println("				Hilo escuchaSolictudMulticast, Escuchando pedido de ayuda desde otros proceso_barreras");
    				                }
            						socketGrupoMulticast_RX.receive(MensajeMulticastRecepcionSolictud); // Port 3002
               						QuienPidioAyuda = new String(MensajeMulticastRecepcionSolictud.getData(),MensajeMulticastRecepcionSolictud.getOffset(),MensajeMulticastRecepcionSolictud.getLength());
               						if (degugger_keep_aliveON)
				                    {
                                      out.println("				Hilo escuchaSolictudMulticast,Barrera N° "+NumBarreraPropia+" Se recibio pedido de ayuda desde la barrera N° "+QuienPidioAyuda );
				                      if (QuienPidioAyuda.equals(MensajeEcho))
           			            	    out.println("				Hilo escuchaSolictudMulticast,Barrera N° "+NumBarreraPropia+" Escucha su propio pedido, sigue escuahdno respuestas desde otras barreras, respuesta desde barrera=" +QuienPidioAyuda );
				                    }
            						}while (QuienPidioAyuda.equals(MensajeEcho));
        							socketGrupoMulticast_RX.send(MensajeMulticastEnvioRespuestaSolicitud);        //port 3003
              						if (degugger_keep_aliveON)
				                    {
              							out.println("				Hilo escuchaSolictudMulticast,Barrera N° "+NumBarreraPropia+" Se envio respuesta al pedido de ayuda desde la barrera N° "+QuienPidioAyuda );
				                    }
       			            	    String EnvioRespuesta = new String(MensajeMulticastEnvioRespuestaSolicitud.getData(),MensajeMulticastEnvioRespuestaSolicitud.getOffset(),MensajeMulticastEnvioRespuestaSolicitud.getLength());
        					//		socketGrupoMulticast_RX.close();
        							FlagEscuchaSolictudMulticast=0;
							} catch (IOException e) {
								e.printStackTrace();
							}
						}
					};
					Timer.start();
					EsperaRespuestaServerRMI.start();
				
					
					if ((Flag==0) && (ProcesoMultiCastActivo==0) && (FlagEscuchaSolictudMulticast==0))
					{
						escuchaSolictudMulticast.start();
					}
					try {
		    		File filetmp = new File(homedir, "borrar");
		     		//FileWriter filetmp;
		    		FileWriter fborrar = new FileWriter(filetmp);
			     	BufferedWriter bwBorrar =new  BufferedWriter(fborrar);
						while(Flag==0)
		                {
							bwBorrar.write("*");
		                }
						bwBorrar.close();
						filetmp.delete();
//						fileBorrar.delete();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

	                if(Timer.isAlive()){
  						if (degugger_keep_aliveON)
	                    {
  							out.println("");
  							out.println(" Se interrumpe el Hilo TIMER");
	                    }
	                	Timer.interrupt();
	        		}
	                if(EsperaRespuestaServerRMI.isAlive()){
	                	Timer.interrupt();
  						if (degugger_keep_aliveON)
	                    {
  							out.println("");
  							out.println(" Se interrumpe el Hilo EsperaRespuestaServerRMI");
	                    }
	        		}
	    			if ((Flag==2)&&(ProcesoMultiCastActivo==0))// NO se recibio el OK desde el OBJ_DISTR, ProcesoMultiCastActivo=0 No se envio Multicast aun.
	    			{
	        			ProcesoMultiCastActivo=1; //Se envia Multicast
		        	    FlagEscuchaSolictudMulticast=1;
	        			Thread RX_MulticastBarreraActiva=new Thread(){
	        				public void run(){
	        					try {
        							//socketGrupoMulticast_RX.close();
	        						out.println("");
 	        						if(escuchaSolictudMulticast.isAlive())
	        						{
	        							escuchaSolictudMulticast.interrupt();
	              						if (degugger_keep_aliveON)
					                    {
	              							out.println("No se recibio Respuesta desde el Server RMI, en entonces se interrumpe el HILO escuchaSolictudMulticast");
					                    }
	        						}
        		    				MulticastSocket socketGrupoMulticast_TX=new MulticastSocket(GrupoBarreras_PortRX);
        			        		socketGrupoMulticast_TX.setInterface(InetAddress.getLocalHost());
        							socketGrupoMulticast_TX.joinGroup(IPvideomulticast);
        							DatagramPacket MensajeMulticastEnvioSolicitud     = new DatagramPacket(MensajeEcho.getBytes(),MensajeEcho.length(),IPvideomulticast,GrupoBarreras_PortTX); //3002 pidiendo Ayuda
 	        						socketGrupoMulticast_TX.send(MensajeMulticastEnvioSolicitud);//port 3002
              						if (degugger_keep_aliveON)
				                    {
              							out.println("No se recibio Respuesta desde el Server RMI, see envio Multicast pedido de ayuda a otras barreras");
				                    }	
	        						Flag=3; // Se ha enviado consulta de procesobarrera Activo
	       							DatagramPacket MensajeMulticastReespuestaSolictud = new DatagramPacket(bufferRecepcion,MAX_LONGITUD_Multicast);//port 3003
	        	        			socketGrupoMulticast_TX.receive(MensajeMulticastReespuestaSolictud);//port 3003
	        						String MulticastRespuestaBarrera = new String(MensajeMulticastReespuestaSolictud.getData(),MensajeMulticastReespuestaSolictud.getOffset(),MensajeMulticastReespuestaSolictud.getLength());
              						if (degugger_keep_aliveON)
				                    {
              							out.println("Se recibio resuesta de la barrera N° "+ MulticastRespuestaBarrera);
				                    }	
	        	        			BarreraRespondioMulticast_tmp=Integer.parseInt(MulticastRespuestaBarrera);
	        	        			//socketGrupoMulticast_TX.close();
								} catch (IOException e) {
									e.printStackTrace();
								}
	        					}
	        			};
	        			RX_MulticastBarreraActiva.start();
	        		 }
//	    			if (BarreraRespondioMulticast_tmp !=0)
    				WindProcesoBarrera.textNumBarrera.setText("Usted esta en la barrera Nº "+NumBarreraPropia);

	    			if ((b_ServerRMI_ok==false))
	    			{
	    				WindProcesoBarrera.txtEstacionamientoLleno.setVisible(false);
	    				if (BarreraRespondioMulticast_tmp==0)
		    				WindProcesoBarrera.txtBarreraMulticast.setText("Dirigirse a otra barrera");
	    				else
	    				    WindProcesoBarrera.txtBarreraMulticast.setText("Dirigirse a la barrera Nº: " + BarreraRespondioMulticast_tmp);
	    				WindProcesoBarrera.txtBarreraMulticast.setVisible(true);
	    				WindProcesoBarrera.txtPatente.setText("---");
	    				WindProcesoBarrera.txtCochera_Num.setText("---");
	    				WindProcesoBarrera.txtIrPorCalle.setText("---");
  						if (degugger_keep_aliveON)
	                    {
  							out.println("");
  							out.println("Se modifica GUI, se notifica que se debe ir por otra barrera");
  							out.println("Modificacion de mensaje de reenvio hacia otra barrera b_ServerRMI_ok= "+ b_ServerRMI_ok);
	                    }
	    			}
	    			else
	    			{
	    				WindProcesoBarrera.txtEstacionamientoLleno.setVisible(true);
	    				WindProcesoBarrera.txtBarreraMulticast.setVisible(false);
	    	//			WindProcesoBarrera.textNumBarrera.setText("");

	    			}	
	 	    	}
				while(true);
			 }
			 catch (Exception ex){
				ex.printStackTrace();
			 }
		
//	}
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

