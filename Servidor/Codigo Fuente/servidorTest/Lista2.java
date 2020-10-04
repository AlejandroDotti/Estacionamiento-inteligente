package servidorTest;

import servidorTest.Nodo2;

public class Lista2 {
	protected Nodo2 inicio,fin;
	
	public Lista2(){
		inicio=null;
		fin=null;
	}
	
	// Met. para agregar nodo al principio
	public void agregarAlinicio(String elemento1, String elemento2, String elemento3, String elemento4, String elemento5) { //PARA AGREGAR NODOS, NO SE UTILIZA MAS
		inicio = new Nodo2(elemento1,elemento2,elemento3,elemento4,elemento5,inicio);
		if(fin==null) {
			fin=inicio;
		}
	}
	
	// Mostrar
	public void mostrarLista() { //NO SE UTILIZA
		Nodo2 recorrer=inicio;
		while(recorrer!=null) {
			System.out.print("["+recorrer.parsela+"]--->");
				recorrer=recorrer.siguiente;
		}
	}
	
	// Lista vacia?
	public boolean estaVacia() { //NO SE UTILIZA
		if(inicio==null) {
			return true;
		}else {
				return false;
			}
		
	}
	// Borra el 1er elemento
	public String borrarDelInicio () { //NO SE UTILIZA
		String elemento= inicio.patente;
		if(inicio==fin) {
			inicio=fin=null;			
		}else {
			inicio=inicio.siguiente;
		}
		
		return elemento;	
		
	}
	
	// Borrar el dato elegido
	public void Borrar(String elemento) { //NO SE UTILIZA
		if(!estaVacia()){
				if(inicio==fin && elemento==inicio.patente) {
					inicio=fin=null;
				}else if(elemento==inicio.patente){
						inicio=inicio.siguiente;
				}else {
					Nodo2 anterior,aux;
					anterior=inicio;
					aux=inicio.siguiente;
					while(aux!=null && aux.patente!=elemento) {
						anterior=anterior.siguiente;
						aux=aux.siguiente;
					}
					if(aux!=null) {
						anterior.siguiente=aux.siguiente;
						if(aux==fin) {
							fin=anterior;
						}
					}
					
					}
				}
			}
	
	// Buscar en la lista
	public String buscarEnLista(String elemento) { //Busca V,R,L y devuelve numero de parsela. 
		Nodo2 aux=inicio;							//Si no encuentra una V, entonces AUX es NULL, Devuelve LLENO.
		//String lugar;
		
		while(aux!=null && !aux.estado.equals(elemento)) {
			aux=aux.siguiente;
		}
		
		if(aux==null)
			return "LLENO";
		
		elemento=aux.parsela;
		//System.out.print("["+lugar+"]--->");
		
		return elemento;
	}

	public String buscarEnListaDuplicado(String elemento) { 
		Nodo2 aux=inicio;							
		//String lugar;
		
		while(aux!=null && !aux.patente.equals(elemento)) {
			aux=aux.siguiente;
		}
		
		if(aux==null)
			return "OK";
		
		//elemento=aux.parsela;
		//System.out.print("["+lugar+"]--->");
		
		return "ERROR";
	}
	
	// BUCAR por referencia
	public boolean buscar(String referencia){ //BUSCA EL NUMERO DE PARSELA, SOLO DEVUELVE TRUE
        // Crea una copia de la lista.
        Nodo2 aux = inicio;
        // Bandera para indicar si el valor existe.
        boolean encontrado = false;
        // Recorre la lista hasta encontrar el elemento o hasta 
        // llegar al final de la lista.
        while(aux != null && encontrado != true){
            // Consulta si el valor del nodo es igual al de referencia.
            if (referencia == aux.getValorParsela()){
                // Canbia el valor de la bandera.
                encontrado = true;
            }
            else{
                // Avanza al siguiente. nodo.
                aux = aux.getSiguiente();
            }
        }
        // Retorna el resultado de la bandera.
        return encontrado;
    }
	
	public void editarPorReferencia(String referencia, String valor,String horaActual){ //la referencia es el num de parela y el valor es el nuevo estado
        // Consulta si el valor existe en la lista.
        if (buscar(referencia)) {
            // Crea ua copia de la lista.
            Nodo2 aux = inicio;
            // Recorre la lista hasta llegar al nodo de referencia.
            while(aux.getValorParsela() != referencia){
                aux = aux.getSiguiente();
            }
            // Actualizamos el valor del nodo
            aux.setValorEstado(valor);
            aux.setHora(horaActual);
        }
    }
	
	public void editarPorReferenciaParente(String referencia, String valor){ //la referencia es el num de parela y el valor es la nueva patente
        // Consulta si el valor existe en la lista.
        if (buscar(referencia)) {
            // Crea ua copia de la lista.
            Nodo2 aux = inicio;
            // Recorre la lista hasta llegar al nodo de referencia.
            while(aux.getValorParsela() != referencia){
                aux = aux.getSiguiente();
            }
            // Actualizamos el valor del nodo
            aux.setValorPatente(valor);
        }
    }
	
	
	public void listar(){
        // Verifica si la lista contiene elementoa.
        if (!estaVacia()) {
            // Crea una copia de la lista.
            Nodo2 aux = inicio;
            // Posicion de los elementos de la lista.
            int i = 0;
            // Recorre la lista hasta el final.
            while(aux != null){
                // Imprime en pantalla el valor del nodo.
                System.out.print(i + ".[ " + aux.getValorParsela() + "-"+aux.getValorEstado() + "-"+ aux.getValorPatente() + " ]" + " ->  ");
                // Avanza al siguiente nodo.
                aux = aux.getSiguiente();
                // Incrementa el contador de la posión.
                i++;
            }
        }
    }

	public String buscarPatente(String elemento) { 
		Nodo2 aux=inicio;						//Buscamos la patente 
		
		while(aux!=null && !aux.patente.equals(elemento)) {
			aux=aux.siguiente;
		}
		
		if(aux==null)
			return "error";
		
		elemento=aux.parsela;  //retornamos la parsela
		
		
		return elemento;   //
	}
		
	
	public String busPatReEstWOSoloError(String elemento,String elemento2) { 
		Nodo2 aux=inicio;						//Buscamos la patente 
		
		while(aux!=null && !aux.patente.equals(elemento)) {
			aux=aux.siguiente;
		}
		if(aux==null || aux.estado.equals("L") || aux.estado.equals("V"))
			return "error";
	return "OK";
	}
	
	public String busPatReEstWO(String elemento,String elemento2) { 
		Nodo2 aux=inicio;						//Buscamos la patente 
		
		while(aux!=null && !aux.patente.equals(elemento)) {
			aux=aux.siguiente;
		}
		if(aux==null || aux.estado.equals("L") || aux.estado.equals("V"))
			return "error";
		aux.setValorEstado("L");  //Escribimos Ocupado si estaba reservado
		aux.setidEstacionamiento(elemento2);
		elemento=aux.estado;  //retornamos estado
	
		return elemento;   //
	}
	
	public String busPatReEstWL(String elemento) { 
		Nodo2 aux=inicio;						//Buscamos la parsela
		while(aux!=null && !aux.parsela.equals(elemento)) {	
			aux=aux.siguiente;
		}		
		if(aux==null || aux.estado.equals("V") || aux.estado.equals("R"))
			return "error";
		    //elemento=aux.estado;  //retornamos estado
		    aux.setValorEstado("V");  //Escribimos Ocupado si estaba ocupado
            aux.setValorPatente("");
            aux.setidEstacionamiento("");
		    elemento=aux.getValorEstado();		
		    return elemento;   //
	}
	
	
	public String busidEstacionamiento(String elemento) { 
		Nodo2 aux=inicio;						//Buscamos la patente 
		while(aux!=null && !aux.parsela.equals(elemento)) {	
			aux=aux.siguiente;
		}		
		if(aux==null || aux.idEstacionamiento.equals(""))
			return "error";
		    
		elemento=aux.getifEstacionamiento();		
		    return elemento;   //
	}
	
	
	public String busPatReEstSoloError(String elemento,String elemento2,String elemento3) { //Parsela, Patente, ID
		Nodo2 aux=inicio;						//Buscamos la patente 
		
		while(aux!=null && !aux.parsela.equals(elemento)) {
			aux=aux.siguiente;
		}
	    if(aux==null || aux.estado.equals("R") || aux.estado.equals("L"))
			return "error";
	    
	    aux=inicio;
		
	    while(aux!=null && !aux.patente.equals(elemento2)) {	
			aux=aux.siguiente;
		}
		
		if(aux==null || aux.estado.equals("V") || aux.estado.equals("L"))
			return "error";
	    
	    return "OK";
	}
	
	public String busPatReEst(String elemento,String elemento2,String elemento3) { //Parsela, Patente, ID
		Nodo2 aux=inicio;						//Buscamos la patente 
		
		while(aux!=null && !aux.parsela.equals(elemento)) {
			aux=aux.siguiente;
		}
	    if(aux==null || aux.estado.equals("R") || aux.estado.equals("L"))
			return "error";
		
	    aux=inicio;
		
		while(aux!=null && !aux.patente.equals(elemento2)) {	
			aux=aux.siguiente;
		}
		
		if(aux==null || aux.estado.equals("V") || aux.estado.equals("L"))
			return "error";
	
	//	    elemento=aux.estado;  
		    
		    aux.setValorEstado("V");  //Escribimos vacio si estaba ocupado
		    aux.setValorPatente("");
		    aux.setHora("");
		    aux=inicio;
		    
		    while(aux!=null && !aux.parsela.equals(elemento)) {
				aux=aux.siguiente;
			}
		    if(aux==null)
				return "error";
		    
			    aux.setValorEstado("L");
			    aux.setValorPatente(elemento2);
			    aux.setidEstacionamiento(elemento3);
		           	    
		    return  aux.getValorEstado();   //
	}
	
	
	
	
	
	
	
	
}