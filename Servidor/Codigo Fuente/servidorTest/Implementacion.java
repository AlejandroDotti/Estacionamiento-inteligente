package servidorTest;

import java.rmi.RemoteException;
//import servidorTest.Servidor;
import java.rmi.server.UnicastRemoteObject;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.xml.rpc.ServiceException;

import miserviciowsdl.MiPrimerServicioWeb;
import miserviciowsdl.MiPrimerServicioWebLocator;
import miserviciowsdl.MiPrimerServicioWebPortType;






public class Implementacion extends UnicastRemoteObject implements Interface {

	
	/**
	 *  Debemos crear como minimo el constructor sin parametros invocando al constructor sin parametros de la
	 *	clase que estamos heredando mediante la llamada a super.
	 */
	public Implementacion() throws RemoteException{
		super();
	}


	
	
	public String reservar(String patente) throws RemoteException {
		
		
		//if(patente.equals("OK"))
			//return "OK";
		
				
		//RECIBO PATENTE, RESPONDO LUGAR DE ESTACIONAMIENTO O SI ESTA LLENO
		
		//RECORRER EL VECTOR, EL PRIMERO LIBRE DEVOLVERLO, MARCARLO COMO RESERVADO, SI NO HAY LIBRE, RESPONDER LLENO.
		
		//PROCESO SENSOR, CONFIRMA RESERVA
		Date date = new Date();
		
		DateFormat format = new SimpleDateFormat("HH:mm:ss"); 
		
		
		String esta_registrado="ERROR";
		
		MiPrimerServicioWeb miWebService = new MiPrimerServicioWebLocator();
		
		try {
			//Luego obtenemos una referencia al stub del que invocamos el / los metodos
			MiPrimerServicioWebPortType miWebServicePort= miWebService.getMiPrimerServicioWebPort();
			esta_registrado = miWebServicePort.buscarPatente(patente);
			
			//Presentamos el resultado
			System.out.println(esta_registrado);
		} catch (ServiceException | RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(esta_registrado.equals("NO"))
			return "REGISTRESE";
		
		
		String duplicado="";
		
		duplicado=Servidor.lista.buscarEnListaDuplicado(patente);
		
		if(duplicado.equals("ERROR"))
			return "DUPLICADO";
		
		
		String lugar=Servidor.lista.buscarEnLista("V"); //Busca uno vacio y retorna la parsela que encontro vacia. 
		
		if (lugar.equals("LLENO"))
			return "LLENO";
		
		
		String horaReserva=format.format(date);
		
		Servidor.lista.editarPorReferencia(lugar,"R",horaReserva); //busca el numero de parsela y edita con R y le grabo la hora que se reservo
		
		
		
		
		
		Servidor.lista.editarPorReferenciaParente(lugar, patente); //busco el lugar y le grabo la patente.
		
		System.out.println("\nLugar retornado: " + lugar + ".Se reservo a la hora:"+ horaReserva);
		Servidor.actualizarGUI();
		return lugar;// RETORNO PARCELA
	}
	
	public String darEstado(String patente,String parsela, String Accion) throws RemoteException {
		 

		String insertar="ERROR";
		
		MiPrimerServicioWeb miWebService = new MiPrimerServicioWebLocator();
		
		String Estado=null; //buscamos por patente el estado, si no es 
		// reservado devuelve error
		
		if(patente.equals(""))
		{
 	//UPDATE PHP SALIDA
    	   
    	    
    	    
 
    	    
    	    String id=Servidor.lista.busidEstacionamiento(parsela);
    		
    	    try {
    			//Luego obtenemos una referencia al stub del que invocamos el / los metodos
    			MiPrimerServicioWebPortType miWebServicePort= miWebService.getMiPrimerServicioWebPort();
    			insertar = miWebServicePort.liberarParsela(id);
    			
    			
    			//Presentamos el resultado
    			System.out.println(insertar);
    		} catch (ServiceException | RemoteException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
    	    
    		
    		Estado=Servidor.lista.busPatReEstWL(parsela); //La accion es liberar, ¿El estado es ocupado? Lo cambiamos por libre y retornamos libre  	
    	    	Servidor.actualizarGUI();
    	    	return Estado; // devolvemos libre o error segun correponda
			
		}
		
		
		String Parsela=Servidor.lista.buscarPatente(patente); //Busca uno vacio y retorna la parsela que encontro vacia. 
		
		System.out.println(Parsela);
		
		if (Parsela.equals("error")) {
    	    System.out.println("error");   
    	    Servidor.actualizarGUI();
			return "hubo un error";}
		
        if(Parsela.equals(parsela)) { //¿Es la misma parsela?
    	    if(Accion.equals("EST")) { //¿La accion es estacionar?
    	    
    	    
    	    	
    	    Estado=Servidor.lista.busPatReEstWOSoloError(patente,insertar); 
			
        	if(Estado.equals("error"))
        	{
        		System.out.println(Estado);   
        	    Servidor.actualizarGUI();
        		return Estado;
        	}	
    	    
    		try {
    			//Luego obtenemos una referencia al stub del que invocamos el / los metodos
    			MiPrimerServicioWebPortType miWebServicePort= miWebService.getMiPrimerServicioWebPort();
    			insertar = miWebServicePort.insertarVehiculo(patente,parsela);
    			
    			
    			//Presentamos el resultado
    			System.out.println(insertar);
    		} catch (ServiceException | RemoteException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
    		
    		Estado=Servidor.lista.busPatReEstWO(patente,insertar); //Si estaba reservada, la actualizamos a ocupada y sino devolvemos error 
    	    
    	    //PHP INSERTAR
    	    
    	    System.out.println(Estado);   
    	    Servidor.actualizarGUI();
    	    return Estado; //devolvemos ocupado o error segun corresponda	
    		   
    		   
    	    }
       	} 
        else //No era la misma parsela
        	if(Accion.equals("EST")) { //¿La accion es estacionar?
        		
    	   
        	
        
            Estado=Servidor.lista.busPatReEstSoloError(parsela,patente,insertar); //¿El estado es reservado?, Si es asi borramos los datos de la parsela y escribimos en la parsela recibida por RMI

            
        			
        	if(Estado.equals("error"))
        	{
        		System.out.println(Estado);   
        	    Servidor.actualizarGUI();
        		return Estado;
        	}
        	try {
        			//Luego obtenemos una referencia al stub del que invocamos el / los metodos
        		MiPrimerServicioWebPortType miWebServicePort= miWebService.getMiPrimerServicioWebPort();
        		insertar = miWebServicePort.insertarVehiculo(patente,parsela);
        			
        			
        			//Presentamos el resultado
        		System.out.println(insertar);
        	} catch (ServiceException | RemoteException e) {
        			// TODO Auto-generated catch block
        		e.printStackTrace();
        		}	
        		
        	 
    	    //PHP INSERTAR
    	    
        	Estado=Servidor.lista.busPatReEst(parsela,patente,insertar); //¿El estado es reservado?, Si es asi borramos los datos de la parsela y escribimos en la parsela recibida por RMI
    	    
    	    System.out.println(Estado);   
    	    Servidor.actualizarGUI();
    	    return Estado; //retornamos ocupado o error segun corresponda
    		   					}
		//En caso contrario error
		
	    System.out.println("\nError, POSIBLE PARSELA INCORRECTA EN EL ACTO DE LIBERAR");   
	    Servidor.actualizarGUI();
        return "error";
	}

	
}

