/**
 * MiPrimerServicioWebPortType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package miserviciowsdl;

public interface MiPrimerServicioWebPortType extends java.rmi.Remote {
    public java.lang.String buscarPatente(java.lang.String pat) throws java.rmi.RemoteException;
    public java.lang.String liberarParsela(java.lang.String id) throws java.rmi.RemoteException;
    public java.lang.String insertarVehiculo(java.lang.String pat, java.lang.String parsela) throws java.rmi.RemoteException;
}
