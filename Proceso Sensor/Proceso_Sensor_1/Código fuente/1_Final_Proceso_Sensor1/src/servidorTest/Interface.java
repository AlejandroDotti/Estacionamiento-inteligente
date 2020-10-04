package servidorTest;

/**
 * Este metodo remoto devuelve un mensaje basado en el argumento nombre - string que contiene un nombre,
 * devuelve un string con la fecha y la hora del servidor.
 * 
 *
 */

import java.rmi.Remote;

public interface Interface extends Remote {
	
	public String reservar(String patente)throws java.rmi.RemoteException;
	public String darEstado(String patente,String parsela, String Accion)throws java.rmi.RemoteException;
}
