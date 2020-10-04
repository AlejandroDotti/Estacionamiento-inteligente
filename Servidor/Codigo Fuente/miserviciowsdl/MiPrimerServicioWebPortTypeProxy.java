package miserviciowsdl;

public class MiPrimerServicioWebPortTypeProxy implements miserviciowsdl.MiPrimerServicioWebPortType {
  private String _endpoint = null;
  private miserviciowsdl.MiPrimerServicioWebPortType miPrimerServicioWebPortType = null;
  
  public MiPrimerServicioWebPortTypeProxy() {
    _initMiPrimerServicioWebPortTypeProxy();
  }
  
  public MiPrimerServicioWebPortTypeProxy(String endpoint) {
    _endpoint = endpoint;
    _initMiPrimerServicioWebPortTypeProxy();
  }
  
  private void _initMiPrimerServicioWebPortTypeProxy() {
    try {
      miPrimerServicioWebPortType = (new miserviciowsdl.MiPrimerServicioWebLocator()).getMiPrimerServicioWebPort();
      if (miPrimerServicioWebPortType != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)miPrimerServicioWebPortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)miPrimerServicioWebPortType)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (miPrimerServicioWebPortType != null)
      ((javax.xml.rpc.Stub)miPrimerServicioWebPortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public miserviciowsdl.MiPrimerServicioWebPortType getMiPrimerServicioWebPortType() {
    if (miPrimerServicioWebPortType == null)
      _initMiPrimerServicioWebPortTypeProxy();
    return miPrimerServicioWebPortType;
  }
  
  public java.lang.String buscarPatente(java.lang.String pat) throws java.rmi.RemoteException{
    if (miPrimerServicioWebPortType == null)
      _initMiPrimerServicioWebPortTypeProxy();
    return miPrimerServicioWebPortType.buscarPatente(pat);
  }
  
  public java.lang.String liberarParsela(java.lang.String id) throws java.rmi.RemoteException{
    if (miPrimerServicioWebPortType == null)
      _initMiPrimerServicioWebPortTypeProxy();
    return miPrimerServicioWebPortType.liberarParsela(id);
  }
  
  public java.lang.String insertarVehiculo(java.lang.String pat, java.lang.String parsela) throws java.rmi.RemoteException{
    if (miPrimerServicioWebPortType == null)
      _initMiPrimerServicioWebPortTypeProxy();
    return miPrimerServicioWebPortType.insertarVehiculo(pat, parsela);
  }
  
  
}