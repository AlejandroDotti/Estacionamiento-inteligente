package servidorTest;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.UnknownHostException;
import java.rmi.Naming;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.GregorianCalendar;











public class Servidor {

	/**
	 * @param args Los argumentos de la linea de comandos
	 */
	
	
	 static Lista2 lista = new Lista2();
	
	 static serverGUI iGrafica =new serverGUI();
	 
	
	public static void main(String[] args) {
		InputStreamReader input = new InputStreamReader(System.in);
		
	
		String ipBarrera1="",ipBarrera2="",ipBarrera3="",ipBarrera4="",s_IpDNSServer="";
		InetAddress IpDNSServer = null;
		
		 File archivoConfiguracion =null;
	      FileReader fileR0 = null;
	       try {
	    	   File homedir0 = new File(System.getProperty("user.home"));
	    	   File fileToRead0 = new File(homedir0, "ipBarrerasServidor.txt");
	    	  // archivoConfiguracion = new File (fileToRead);   
	    	   fileR0 = new FileReader (fileToRead0);
	    	   BufferedReader buffer0 = new BufferedReader (fileR0);
	    	   s_IpDNSServer=buffer0.readLine();
	    	   ipBarrera1=buffer0.readLine();
	    	   ipBarrera2=buffer0.readLine();
	    	   ipBarrera3=buffer0.readLine();
	    	   ipBarrera4=buffer0.readLine();
	    	   fileR0.close();
	       }
	       catch (Exception e) {
	    	   e.printStackTrace();
	       }
		
	    System.setProperty("sun.net.spi.nameservice.nameservers", s_IpDNSServer);
		System.setProperty("sun.net.spi.nameservice.provider.1", "dn,sun");   
	    
		try {
		
			IpDNSServer = InetAddress.getByName("dns.unm.ar");

				
			
		} catch (UnknownHostException e) {
			System.out.println("");
	    	System.out.println("");
	    	System.out.println("******************************************************************************************************");
			System.out.println("************ NoNo hay conexion con el DNS Server, solucionar el problema y volver a intentar ***********");
	    	System.out.println("******************************************************************************************************");
		}
		if(IpDNSServer.getHostAddress().equals(s_IpDNSServer))
		{
	    iGrafica.setVisible(true);
		
		Thread t1=new Thread(new hiloMonitor());
		t1.start();
		
		Thread t2=new Thread(new hiloBarrera1(ipBarrera1));
		t2.start();
		
		Thread t3=new Thread(new hiloBarrera2(ipBarrera2));
		t3.start();
		
		Thread t4=new Thread(new hiloBarrera3(ipBarrera3));
		t4.start();
		
		Thread t5=new Thread(new hiloBarrera4(ipBarrera4));
		t5.start();
		
		
		
		
		//!!!!!!!!!!!!!AGREGAR CON IP DE HAMACHI
		
		System.setProperty("java.rmi.server.hostname","25.106.211.49");
		
	    //!!!!!!!!!!!!!AGREGAR CON IP DE HAMACHI
	   
		/*
		lista.agregarAlinicio("20","V","","","");	//L=LLENO V=VACIO.
		lista.agregarAlinicio("19","L","","","");	//String par, String est,String pat,String idEsta,String horaRes,
		lista.agregarAlinicio("18","L","","","");	//Formato hora:"22:39:31"
		lista.agregarAlinicio("17","L","","","");
		lista.agregarAlinicio("16","L","","","");
		lista.agregarAlinicio("15","L","","","");
		lista.agregarAlinicio("14","L","","","");
		lista.agregarAlinicio("13","L","","","");
		lista.agregarAlinicio("12","L","","","");
		lista.agregarAlinicio("11","L","","","");
		lista.agregarAlinicio("10","L","","","");
		lista.agregarAlinicio("09","L","","","");
		lista.agregarAlinicio("08","L","","","");
		lista.agregarAlinicio("07","L","","","");
		lista.agregarAlinicio("06","L","","","");
		lista.agregarAlinicio("05","L","","","");
		lista.agregarAlinicio("04","L","","","");
		lista.agregarAlinicio("03","L","","","");
		lista.agregarAlinicio("02","L","","","");
		lista.agregarAlinicio("01","L","","","");
		
		*/
		leerArchivo();
		actualizarGUI();
		
		String puerto, urlRegistro;
		try{
			//Solicitamos el puerto de trabajo y hacemos su conversion correspondiente
			System.out.println("Iniciando Servidor ");
				puerto = "1900";
			int puertoRMI = Integer.parseInt(puerto);
			
			//Iniciamos el registro RMI. Si no fue lanzado la funcion se encarga de lanzarlo.
			startRegistry(puertoRMI);
			
			//Creamos el objeto que publicaremos en el registro de objetos y obtenemos una referencia a su stub
			Implementacion objetoRemoto = new Implementacion ();
			Remote stub = UnicastRemoteObject.toStub(objetoRemoto);
			
			//Creamos la URL que asociaremos al objeto publicado
			//URL>> protocolo://direccion:puerto/alias_objeto_remoto
			urlRegistro = "rmi://localhost:"+ puerto + "/registro";
			
			/*
			*	Hacemos el binding del objeto a publicar con su correspondiente URL para que ante una solicitud
			*	el registro de objetos sepa a que objeto nos referimos
			*/
			Registry registro = LocateRegistry.getRegistry(puertoRMI);
			registro.bind("registro", stub);
			
			//Opcional listamos los elementos del registro que han sido publicados
			System.out.println("Servidor registrado. El registro contiene: ");
			listRegistry(urlRegistro);//Listamos los objetos del registro
			
			//Hasta aca todo listo y ejecutando.
			System.out.println("Servidor de Registro listo!!");
		}// fin try
		catch(Exception re){
			System.out.println("Excepcion: " + re);
		}//fin catch
		}
		else
		{
			System.out.println("");
	    	System.out.println("");
	    	System.out.println("******************************************************************************************************");
			System.out.println("************ No hay conexion con el DNS Server, solucionar el problema y volver a intentar ***********");
	    	System.out.println("******************************************************************************************************");
	    	
		}
		}//fin main

	//Permite iniciar el registro de objetos si este no fue iniciado
	
	public static void leerArchivo()
	{
		File archivoConfiguracion =null;
	      FileReader fileR = null;
	       try {
	    	   File homedir = new File(System.getProperty("user.home"));
	    	   File fileToRead = new File(homedir, "ConfiguracionServidor.txt");
	    	  // archivoConfiguracion = new File (fileToRead);   
	    	   fileR = new FileReader (fileToRead);
	    	   BufferedReader buffer = new BufferedReader (fileR);
	    	   
	    	   //String par, String est,String pat,String idEsta,String horaRes,
	    	   
	    	   for (int i = 0; i < 20; i++) {
	    		   String par="";
	    		   String est="";
	    		   String pat="";
	    		   String idEsta="";
	    		   String horaRes="";
	    		   
	    		   par=buffer.readLine();
		    	   est=buffer.readLine();
		    	   pat=buffer.readLine();
		    	   idEsta=buffer.readLine();
		    	   horaRes=buffer.readLine();
		    	   
	    		   
	    		   lista.agregarAlinicio(par,est,pat,idEsta,horaRes);   
	    	   }
	    	   

	    	   fileR.close();
	       }
	       catch (Exception e) {
	    	   e.printStackTrace();
	       }
		
		
	}
	
	
	public static void actualizarGUI()
	{
		
		Nodo2 inicio=lista.inicio;
		Nodo2 aux=inicio;							
		
		String parsela,estado,patente;
		
		//String lugar;
		while(aux!=null) {
			parsela=aux.parsela;
			estado=aux.estado;
			patente=aux.patente;
			
			switch (parsela) {
			case "01":
				if(estado.equals("V"))
				{
					iGrafica.text1.setText("01");
					aux.setValorPatente("");
					iGrafica.text1.setBackground(Color.GREEN);
				}
				if(estado.equals("R"))
				{
					iGrafica.text1.setText("01-" + patente);
					iGrafica.text1.setBackground(Color.YELLOW);
				}
				if(estado.equals("L"))
				{
					iGrafica.text1.setText("01-" + patente);
					iGrafica.text1.setBackground(Color.RED);
				}
				
				break;

			case "02":
				if(estado.equals("V"))
				{
					iGrafica.text2.setText("02");
					aux.setValorPatente("");
					iGrafica.text2.setBackground(Color.GREEN);
				}
				if(estado.equals("R"))
				{
					iGrafica.text2.setText("02-" + patente);
					iGrafica.text2.setBackground(Color.YELLOW);
				}
				if(estado.equals("L"))
				{
					iGrafica.text2.setText("02-" + patente);
					iGrafica.text2.setBackground(Color.RED);
				}
				
				break;
				
			case "03":
				if(estado.equals("V"))
				{
					iGrafica.text3.setText("03");
					aux.setValorPatente("");
					iGrafica.text3.setBackground(Color.GREEN);
				}
				if(estado.equals("R"))
				{
					iGrafica.text3.setText("03-" + patente);
					iGrafica.text3.setBackground(Color.YELLOW);
				}
				if(estado.equals("L"))
				{
					iGrafica.text3.setText("03-" + patente);
					iGrafica.text3.setBackground(Color.RED);
				}
				
				break;
				
			case "04":
				if(estado.equals("V"))
				{
					iGrafica.text4.setText("04");
					aux.setValorPatente("");
					iGrafica.text4.setBackground(Color.GREEN);
				}
				if(estado.equals("R"))
				{
					iGrafica.text4.setText("04-" + patente);
					iGrafica.text4.setBackground(Color.YELLOW);
				}
				if(estado.equals("L"))
				{
					iGrafica.text4.setText("04-" + patente);
					iGrafica.text4.setBackground(Color.RED);
				}
				
				break;
				
			case "05":
				if(estado.equals("V"))
				{
					iGrafica.text5.setText("05");
					aux.setValorPatente("");
					iGrafica.text5.setBackground(Color.GREEN);
				}
				if(estado.equals("R"))
				{
					iGrafica.text5.setText("05-" + patente);
					iGrafica.text5.setBackground(Color.YELLOW);
				}
				if(estado.equals("L"))
				{
					iGrafica.text5.setText("05-" + patente);
					iGrafica.text5.setBackground(Color.RED);
				}
				
				break;
				
			case "06":
				if(estado.equals("V"))
				{
					iGrafica.text6.setText("06");
					aux.setValorPatente("");
					iGrafica.text6.setBackground(Color.GREEN);
				}
				if(estado.equals("R"))
				{
					iGrafica.text6.setText("06-" + patente);
					iGrafica.text6.setBackground(Color.YELLOW);
				}
				if(estado.equals("L"))
				{
					iGrafica.text6.setText("06-" + patente);
					iGrafica.text6.setBackground(Color.RED);
				}
				
				break;
				
			case "07":
				if(estado.equals("V"))
				{
					iGrafica.text7.setText("07");
					aux.setValorPatente("");
					iGrafica.text7.setBackground(Color.GREEN);
				}
				if(estado.equals("R"))
				{
					iGrafica.text7.setText("07-" + patente);
					iGrafica.text7.setBackground(Color.YELLOW);
				}
				if(estado.equals("L"))
				{
					iGrafica.text7.setText("07-" + patente);
					iGrafica.text7.setBackground(Color.RED);
				}
				
				break;
				
			case "08":
				if(estado.equals("V"))
				{
					iGrafica.text8.setText("08");
					aux.setValorPatente("");
					iGrafica.text8.setBackground(Color.GREEN);
				}
				if(estado.equals("R"))
				{
					iGrafica.text8.setText("08-" + patente);
					iGrafica.text8.setBackground(Color.YELLOW);
				}
				if(estado.equals("L"))
				{
					iGrafica.text8.setText("08-" + patente);
					iGrafica.text8.setBackground(Color.RED);
				}
				
				break;
				
			case "09":
				if(estado.equals("V"))
				{
					iGrafica.text9.setText("09");
					aux.setValorPatente("");
					iGrafica.text9.setBackground(Color.GREEN);
				}
				if(estado.equals("R"))
				{
					iGrafica.text9.setText("09-" + patente);
					iGrafica.text9.setBackground(Color.YELLOW);
				}
				if(estado.equals("L"))
				{
					iGrafica.text9.setText("09-" + patente);
					iGrafica.text9.setBackground(Color.RED);
				}
				
				break;
				
			case "10":
				if(estado.equals("V"))
				{
					iGrafica.text10.setText("10");
					aux.setValorPatente("");
					iGrafica.text10.setBackground(Color.GREEN);
				}
				if(estado.equals("R"))
				{
					iGrafica.text10.setText("10-" + patente);
					iGrafica.text10.setBackground(Color.YELLOW);
				}
				if(estado.equals("L"))
				{
					iGrafica.text10.setText("10-" + patente);
					iGrafica.text10.setBackground(Color.RED);
				}
				
				break;
				
			case "11":
				if(estado.equals("V"))
				{
					iGrafica.text11.setText("11");
					aux.setValorPatente("");
					iGrafica.text11.setBackground(Color.GREEN);
				}
				if(estado.equals("R"))
				{
					iGrafica.text11.setText("11-" + patente);
					iGrafica.text11.setBackground(Color.YELLOW);
				}
				if(estado.equals("L"))
				{
					iGrafica.text11.setText("11-" + patente);
					iGrafica.text11.setBackground(Color.RED);
				}
				
				break;
				
			case "12":
				if(estado.equals("V"))
				{
					iGrafica.text12.setText("12");
					aux.setValorPatente("");
					iGrafica.text12.setBackground(Color.GREEN);
				}
				if(estado.equals("R"))
				{
					iGrafica.text12.setText("12-" + patente);
					iGrafica.text12.setBackground(Color.YELLOW);
				}
				if(estado.equals("L"))
				{
					iGrafica.text12.setText("12-" + patente);
					iGrafica.text12.setBackground(Color.RED);
				}
				
				break;
				
			case "13":
				if(estado.equals("V"))
				{
					iGrafica.text13.setText("13");
					aux.setValorPatente("");
					iGrafica.text13.setBackground(Color.GREEN);
				}
				if(estado.equals("R"))
				{
					iGrafica.text13.setText("13-" + patente);
					iGrafica.text13.setBackground(Color.YELLOW);
				}
				if(estado.equals("L"))
				{
					iGrafica.text13.setText("13-" + patente);
					iGrafica.text13.setBackground(Color.RED);
				}
				
				break;
				
			case "14":
				if(estado.equals("V"))
				{
					iGrafica.text14.setText("14");
					aux.setValorPatente("");
					iGrafica.text14.setBackground(Color.GREEN);
				}
				if(estado.equals("R"))
				{
					iGrafica.text14.setText("14-" + patente);
					iGrafica.text14.setBackground(Color.YELLOW);
				}
				if(estado.equals("L"))
				{
					iGrafica.text14.setText("14-" + patente);
					iGrafica.text14.setBackground(Color.RED);
				}
				
				break;
				
			case "15":
				if(estado.equals("V"))
				{
					iGrafica.text15.setText("15");
					aux.setValorPatente("");
					iGrafica.text15.setBackground(Color.GREEN);
				}
				if(estado.equals("R"))
				{
					iGrafica.text15.setText("15-" + patente);
					iGrafica.text15.setBackground(Color.YELLOW);
				}
				if(estado.equals("L"))
				{
					iGrafica.text15.setText("15-" + patente);
					iGrafica.text15.setBackground(Color.RED);
				}
				
				break;
				
			case "16":
				if(estado.equals("V"))
				{
					iGrafica.text16.setText("16");
					aux.setValorPatente("");
					iGrafica.text16.setBackground(Color.GREEN);
				}
				if(estado.equals("R"))
				{
					iGrafica.text16.setText("16-" + patente);
					iGrafica.text16.setBackground(Color.YELLOW);
				}
				if(estado.equals("L"))
				{
					iGrafica.text16.setText("16-" + patente);
					iGrafica.text16.setBackground(Color.RED);
				}
				
				break;
				
			case "17":
				if(estado.equals("V"))
				{
					iGrafica.text17.setText("17");
					aux.setValorPatente("");
					iGrafica.text17.setBackground(Color.GREEN);
				}
				if(estado.equals("R"))
				{
					iGrafica.text17.setText("17-" + patente);
					iGrafica.text17.setBackground(Color.YELLOW);
				}
				if(estado.equals("L"))
				{
					iGrafica.text17.setText("17-" + patente);
					iGrafica.text17.setBackground(Color.RED);
				}
				
				break;
				
			case "18":
				if(estado.equals("V"))
				{
					iGrafica.text18.setText("18");
					aux.setValorPatente("");
					iGrafica.text18.setBackground(Color.GREEN);
				}
				if(estado.equals("R"))
				{
					iGrafica.text18.setText("18-" + patente);
					iGrafica.text18.setBackground(Color.YELLOW);
				}
				if(estado.equals("L"))
				{
					iGrafica.text18.setText("18-" + patente);
					iGrafica.text18.setBackground(Color.RED);
				}
				
				break;
				
			case "19":
				if(estado.equals("V"))
				{
					iGrafica.text19.setText("19");
					aux.setValorPatente("");
					iGrafica.text19.setBackground(Color.GREEN);
				}
				if(estado.equals("R"))
				{
					iGrafica.text19.setText("19-" + patente);
					iGrafica.text19.setBackground(Color.YELLOW);
				}
				if(estado.equals("L"))
				{
					iGrafica.text19.setText("19-" + patente);
					iGrafica.text19.setBackground(Color.RED);
				}
				
				break;
				
			case "20":
				if(estado.equals("V"))
				{
					iGrafica.text20.setText("20");
					aux.setValorPatente("");
					iGrafica.text20.setBackground(Color.GREEN);
				}
				if(estado.equals("R"))
				{
					iGrafica.text20.setText("20-" + patente);
					iGrafica.text20.setBackground(Color.YELLOW);
				}
				if(estado.equals("L"))
				{
					iGrafica.text20.setText("20-" + patente);
					iGrafica.text20.setBackground(Color.RED);
				}
				
				break;
				
			default:
				break;
			}
			
			
			
			
			aux=aux.siguiente;
		}
		
		
		
	}
	
	
	private static void startRegistry(int puertoRMI)throws RemoteException{
		try{
			//Intentamos obtener una referencia al registro.  Sino tenemos exito probamos creando
			Registry registro = LocateRegistry.getRegistry(puertoRMI);
			registro.list();
			//Esta llamada lanza una excepcion si el registro no existe
		}//fin try
		catch(RemoteException e){
			//No existe el registro en este puerto
			System.out.println("No hay un registro RMI previo en el puerto " + puertoRMI);
			Registry registro = LocateRegistry.createRegistry(puertoRMI);
			System.out.println("registro RMIcreado en el puerto: " + puertoRMI);
		}//fin catch
	}//Fin startRegistry
	
	//Este metodo lista todos los nombres registrados en el registro de objetos RMI registry
	private static void listRegistry(String urlRegistro) throws RemoteException, MalformedURLException{
		// TODO Auto-generated method stub
		System.out.println("Registro " + urlRegistro + " contiene: ");
		String [] nombres = Naming.list(urlRegistro);
		for(int i=0; i < nombres.length; i++)
			System.out.println(nombres[i]);
	}//Fin ListRegistry

}