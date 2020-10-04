package servidorTest;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

class hiloMonitor extends Thread{
	
	public void run() {

		
		System.out.println("Entrando al Hilo\n");
		
		while(true){
		
			Nodo2 inicio=Servidor.lista.inicio;
			Nodo2 aux=inicio;							
			
			Date date = new Date();
			DateFormat format = new SimpleDateFormat("HH:mm:ss"); 
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			//String lugar;
			while(aux!=null) {
				
				Date d1 = null; 
				Date d2 = null; 
				Date d3 = null;
				
				if(aux.estado.equals("R"))
				{
					String horaActual=format.format(date);
					//String horaActual="22:39:31";
					try { 
						d2 = format.parse(aux.getHoraDeReserva());
						d3 = format.parse(horaActual);
					} 
					catch (ParseException e) 
					{ 
						e.printStackTrace(); 
					} // Get msec from each, and subtract.
					
					
					long diff = d3.getTime() - d2.getTime(); //hora de la reserva - hora actual
					
					
					long diffSeconds = diff / 1000 % 60; 
					long diffMinutes = diff / (60 * 1000) % 60;
					
					//System.out.println("diferencia : " + diffMinutes);
					
					if(diffMinutes>=5)
					{
						System.out.println("Se eliminara la reserva de : " + aux.getValorParsela());
						aux.setHora("");
						aux.setValorEstado("V");
						aux.setValorPatente("");
						
						Servidor.actualizarGUI();
						
					}
					
				}
			
				
				
				aux=aux.siguiente;
			}
		
		}
	
		
	}
	
	
}