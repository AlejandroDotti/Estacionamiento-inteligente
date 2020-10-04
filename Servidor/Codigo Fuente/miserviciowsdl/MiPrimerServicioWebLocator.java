/**
 * MiPrimerServicioWebLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package miserviciowsdl;

public class MiPrimerServicioWebLocator extends org.apache.axis.client.Service implements miserviciowsdl.MiPrimerServicioWeb {

    public MiPrimerServicioWebLocator() {
    }


    public MiPrimerServicioWebLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public MiPrimerServicioWebLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for MiPrimerServicioWebPort
    private java.lang.String MiPrimerServicioWebPort_address = "http://localhost/2020/wsparaDB/servidor/servicio.php";

    public java.lang.String getMiPrimerServicioWebPortAddress() {
        return MiPrimerServicioWebPort_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String MiPrimerServicioWebPortWSDDServiceName = "MiPrimerServicioWebPort";

    public java.lang.String getMiPrimerServicioWebPortWSDDServiceName() {
        return MiPrimerServicioWebPortWSDDServiceName;
    }

    public void setMiPrimerServicioWebPortWSDDServiceName(java.lang.String name) {
        MiPrimerServicioWebPortWSDDServiceName = name;
    }

    public miserviciowsdl.MiPrimerServicioWebPortType getMiPrimerServicioWebPort() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(MiPrimerServicioWebPort_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getMiPrimerServicioWebPort(endpoint);
    }

    public miserviciowsdl.MiPrimerServicioWebPortType getMiPrimerServicioWebPort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            miserviciowsdl.MiPrimerServicioWebBindingStub _stub = new miserviciowsdl.MiPrimerServicioWebBindingStub(portAddress, this);
            _stub.setPortName(getMiPrimerServicioWebPortWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setMiPrimerServicioWebPortEndpointAddress(java.lang.String address) {
        MiPrimerServicioWebPort_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (miserviciowsdl.MiPrimerServicioWebPortType.class.isAssignableFrom(serviceEndpointInterface)) {
                miserviciowsdl.MiPrimerServicioWebBindingStub _stub = new miserviciowsdl.MiPrimerServicioWebBindingStub(new java.net.URL(MiPrimerServicioWebPort_address), this);
                _stub.setPortName(getMiPrimerServicioWebPortWSDDServiceName());
                return _stub;
            }
        }
        catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        java.lang.String inputPortName = portName.getLocalPart();
        if ("MiPrimerServicioWebPort".equals(inputPortName)) {
            return getMiPrimerServicioWebPort();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("urn:miserviciowsdl", "MiPrimerServicioWeb");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("urn:miserviciowsdl", "MiPrimerServicioWebPort"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("MiPrimerServicioWebPort".equals(portName)) {
            setMiPrimerServicioWebPortEndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
