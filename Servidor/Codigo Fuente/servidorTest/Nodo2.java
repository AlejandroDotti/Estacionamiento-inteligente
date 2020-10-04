package servidorTest;

import servidorTest.Nodo2;

public class Nodo2 {
	
	public String parsela;
	public String estado;
    public String patente;		// Variable en la cual se va a guardar el valor.
    public Nodo2 siguiente;		// Variable puntero (tipo Nodo2) para enlazar los nodos.
    public String idEstacionamiento;
    public String horaDeReserva;
    
    //Constructor para insertar al final	    
    public Nodo2 (String par, String est,String pat) {
    	parsela=par;
	    estado=est;
	    patente = pat;

    }
    
  //Constructor para insertar al inicio	
    public Nodo2 (String par, String est,String pat,String idEsta,String horaRes, Nodo2 n) {
        parsela=par;
        estado=est;
    	patente = pat;
        siguiente = n;
        idEstacionamiento=idEsta;
        horaDeReserva=horaRes;
        
    }
    
    
    
    ///////////////////////////////////////////////////
    public String getValorParsela() {
        return parsela;
    }
    public String getValorEstado() {
        return estado;
    }
    public String getValorPatente() {
        return patente;
    }
    
    public void setValorEstado(String valor) {
        this.estado = valor;
    }
    
    public void setValorPatente(String valor) {
        this.patente = valor;
    }
    
    public void setHora(String valor) {
        this.horaDeReserva = valor;
    }

    public Nodo2 getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(Nodo2 siguiente) {
        this.siguiente = siguiente;
    } 
    public void setidEstacionamiento(String valor) {
        this.idEstacionamiento = valor;
    }
    
    public String getifEstacionamiento() {
        return idEstacionamiento;
    }
    
    public String getHoraDeReserva() {
        return horaDeReserva;
    }
    
    
}
