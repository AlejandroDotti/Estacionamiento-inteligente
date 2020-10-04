package procesomanipulador;         //EN ESTA ULTIMA VERSION SE BUSCA PODER COMUNICARME CON LOS DISTINTOS PRECESOS , SELECCIONANDO EL PUERTO DE DESTINO, EN LUGAR DE SU IP

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;



import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketTimeoutException;

import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;








import java.net.UnknownHostException;

		














public class Proceso_Manipuador_Int extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField texto_enviar;
	private JTextField texto_leer;
	JComboBox<String> combo_bloque = new JComboBox<String>(); //DECLARO BLOQUE DESLIZABLE
	JComboBox<String> combo_accion = new JComboBox<String>();  //DECLARO ACCION DESLIZABLE
	int delay=12000; //tiempo de espera
	/////Variable de archivo//////////
	 File archivo = null;
     FileReader fr = null;
     BufferedReader br = null;
     String direccion1 = null,direccion2 = null,direccion3=null,direccion4=null,DNS=null;
 	InetAddress MyinetAddress,Dir1,Dir2,Dir3,Dir4;
     
     String puesto="";
	 


	/**
	 * Launch the application.
	 */
	/////////////////////////////////////////////////////////////////////////////////////
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Proceso_Manipuador_Int frame = new Proceso_Manipuador_Int();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
    //////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * Create the frame.
	 */
	
	
	
	
	
	
	public Proceso_Manipuador_Int() {
		
	   //VENTANA///////////////////////////////////////////
		setTitle("Manipulador Sensor");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 470, 297);
		contentPane = new JPanel();
	//	contentPane.setBackground(Color.GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		////////////////////////////////////////////////////////
		
		/////BLOQUEAR MAXUMIZAR///////////////
		
		 setAlwaysOnTop(true);
		 setResizable(false);
		 setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		///////////////////////////////////////////////////
		
		///LOGO/////////////////////////////////////////////////
		Image iconoPropio=Toolkit.getDefaultToolkit().getImage(getClass().getResource("UNMIcon.png"));
		setIconImage(iconoPropio);
		
		// ENTRADA DE DATOS ///////////////////////////////////
		texto_enviar = new JTextField();
		texto_enviar.addKeyListener(new KeyAdapter() {
			
		
			public void keyTyped(KeyEvent evt) {
				char car = evt.getKeyChar();
				if(Character.isLetter(car) || Character.isDigit(car)){    //PERMITO SOLO LETRAS Y NUMERO

				}else{
				evt.consume();
				
				getToolkit().beep();
				}
				
				if(texto_enviar.getText().length()==7)  //LARGO PERMITIDO 7
				{
				evt.consume();
				}
				}
		});
		texto_enviar.setBounds(132, 46, 150, 20);
		contentPane.add(texto_enviar);
		texto_enviar.setColumns(10);
		/////////////////////////////////////////////////////////
		
		//////////// BLOQUES DESLIZABLE //////////////////////////////////////
	    combo_bloque.setBounds(132, 95, 53, 20);
		getContentPane().add(combo_bloque);                 //esto se usara para seleccionar los puertos de procesos sensores
		combo_bloque.addItem("1");                           
        combo_bloque.addItem("2");
        combo_bloque.addItem("3");
        combo_bloque.addItem("4");
         // combo_bloque.addActionListener(new paquete()); ACTUALIZAR AL DESLIZAR
         contentPane.add(combo_bloque);
		////////////////////////////////////////////////////////////////////////////
		
         
         
		///////// ACCIONES /////////////////////////////////////////////////////////
        combo_accion.setBounds(284, 95, 65, 20);
		combo_accion.addItem("EST");
		combo_accion.addItem("LIB");
		//  combo_accion.addActionListener(new paquete()); ACTUALIZAR AL DESLIZAR
		contentPane.add(combo_accion);
		
		////////////////////////////////////////////////////////////////////////
		
		
		
		/////////////////////// Labels////////////////////////////////////////////
		JLabel lblNewLabel = new JLabel("PATENTE");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 12));
		lblNewLabel.setBounds(57, 49, 65, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblBloque = new JLabel("BLOQUE");
		lblBloque.setFont(new Font("Times New Roman", Font.BOLD, 12));
		lblBloque.setBounds(57, 98, 65, 14);
		contentPane.add(lblBloque);
		
		JLabel lblAccion = new JLabel("ACCION");
		lblAccion.setFont(new Font("Times New Roman", Font.BOLD, 12));
		lblAccion.setBounds(217, 98, 65, 14);
		contentPane.add(lblAccion);
		////////////////////////////////////////////////////////////
		
		
		
		////////////////////BOTONES/////////////////////////////////////////
		
		JButton sen_1 = new JButton("SEN 1");
		sen_1.setFont(new Font("Tahoma", Font.PLAIN, 10));
		sen_1.addActionListener(new paquete1() ); 
		sen_1.setBounds(57, 144, 65, 23);
		contentPane.add(sen_1);

		/////////////////////////////////////////////////////////////////////
	   
		JButton sen_2 = new JButton("SEN 2");
		sen_2.setFont(new Font("Tahoma", Font.PLAIN, 10));
		sen_2.addActionListener(new paquete2() );
		sen_2.setBounds(132, 144, 65, 23);
		contentPane.add(sen_2);

		/////////////////////////////////////////////////////////////////////
		
        JButton sen_3 = new JButton("SEN 3");
		sen_3.setFont(new Font("Tahoma", Font.PLAIN, 10));
		sen_3.addActionListener(new paquete3() ); 
		sen_3.setBounds(207, 144, 65, 23);
		contentPane.add(sen_3);

		/////////////////////////////////////////////////////////////////////
		
		JButton sen_4 = new JButton("SEN 4");
		sen_4.setFont(new Font("Tahoma", Font.PLAIN, 10));
		sen_4.addActionListener(new paquete4() );
		sen_4.setBounds(284, 144, 65, 23);
		contentPane.add(sen_4);

		/////////////////////////////////////////////////////////////////////
		
		JButton sen_5 = new JButton("SEN 5");
		sen_5.setFont(new Font("Tahoma", Font.PLAIN, 10));
		
		sen_5.addActionListener(new paquete5());
		sen_5.setBounds(359, 144, 65, 23);
		contentPane.add(sen_5);
         /////////////////////////////////////////////////////////////////////
		
		
		
		
		///////DISPLAY//////////////////////
		texto_leer = new JTextField();
		texto_leer.setBounds(56, 192, 368, 30);
		contentPane.add(texto_leer);
		texto_leer.setColumns(10);
		/////////////////////////////////////////////
		
		
		/////////////////Labels///////////////////////////
//		JLabel lblNewLabel_1 = new JLabel("PROCESO MANIPULADOR");
//		lblNewLabel_1.setFont(new Font("Arial Black", Font.BOLD, 14));   //TITULOOO
//		lblNewLabel_1.setBounds(113, 11, 246, 14);
//		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("V 1.0");
		lblNewLabel_2.setBounds(408, 244, 46, 14);
		contentPane.add(lblNewLabel_2);
		////////////////////////////////////////////
		
///////////////////ARCHIVOS/////////////////
		
		try {
			  
		    archivo = new File ("Datos.txt");
		    
		    fr = new FileReader (archivo);
		    br = new BufferedReader(fr);

		    // Lectura del fichero
		    String linea;
		    linea=br.readLine();

		    
		       String mensaje2 = new String(linea);						
				int i=0;
				String[] datos = mensaje2.split(" ");
				for(String item : datos) {
					
				switch(i) {
				
				case 0:	
					direccion1=item;
		            			
					break;
				case 1:
					
					direccion2=item;
					break;
				case 2:
					direccion3=item;
					break;	
				case 3:
					direccion4=item;
					break;		
				case 4:
					DNS=item+"";	
					
					i=-1;
					
				   
					break;		
					default:
		           break;		
				}
				i++;
				}
		 
		 }
		 catch(Exception e1){
		    e1.printStackTrace();
		 }finally{
		   
		    try{                    
		       if( null != fr ){   
		          fr.close();     
		       }                  
		    }catch (Exception e2){ 
		       e2.printStackTrace();
		    }
		 }
////////////////////////////////////////////////////////////////////////				
		
////////////////////SERVER DNS/////////////////////////////////////////
		try {
		System.setProperty("sun.net.spi.nameservice.nameservers", DNS);
		System.setProperty("sun.net.spi.nameservice.provider.1", "dn,sun");
		
		MyinetAddress = InetAddress.getByName("dns.unm.ar"); //Primer consulta
		MyinetAddress = InetAddress.getByName("manipulador.unm.ar");                       
	   	
	

		
		
			Dir1=InetAddress.getByName(direccion1);
			Dir2=InetAddress.getByName(direccion2);
			Dir3=InetAddress.getByName(direccion3);
			Dir4=InetAddress.getByName(direccion4);
			
			
			
			
			
			
			
		} catch (UnknownHostException e) {
		   	e.printStackTrace();
			 texto_leer.setText("PROBLEMA CON EL SERVER DNS");
			
		}
	
		
		
		
		
		
		
		
		
		
		
		}
	
	
	
	
	
class paquete1 implements ActionListener{

@Override
public void actionPerformed(ActionEvent e) {
       
String puertoB = "3001"; 
String puertoA = "3000" ; 
		
		
		  //Guardo en string los datos de los deslizadores
		 String bloque=(String)combo_bloque.getSelectedItem();
		 String accion=(String)combo_accion.getSelectedItem();
		 String patente= new String(texto_enviar.getText()) ;	
		 
		 // los concateno para poder leerlos y enviarlos juntos 
		 String mensaje=patente+" "+"1"+" "+accion;
	
		
   	  
   	    
//ENVIO DEL PAQUETE////////////////////////////////////////////////////////////////////////
		
		
 //Variables que contendran los parametros solicitados por consola.
		InetAddress dirIP_B;
		Integer puerto_A, puerto_B;
		
		
	try{
			
			
       InetAddress direccionB = null;
 		
             
 
 
      //seleccion de PUERTO mediante el nro de bloque

 switch(bloque) {
    
    case "1":  
    	puertoB = "3001"; 
		puertoA = "3000" ;
		direccionB= Dir1;
		
		
    	puesto="01";
    	break;
    case "2":
     puertoB = "3003"; 
	 puertoA = "3002" ;    	
	 direccionB= Dir2;
     puesto="06";
    	break;
    case "3":
    	puertoB = "3005"; 
   	    puertoA = "3004" ; 
   	 direccionB= Dir3;
    	puesto="11";
    	break;
    case "4":
    	puertoB = "3007"; 
   	    puertoA = "3006" ; 
   	 direccionB= Dir4;
 	    puesto="16";
    	break;
    default:
    	break;
    
  }	
			
         //DIR DE PRUEBA LOCAL
       //   direccionB= "localhost";
    
		 
		
		
		    
		//Conversiones de tipos entre entrada por teclado y lo que espera la clase socket.
		dirIP_B = direccionB;//cambio de tipos
		puerto_A = Integer.parseInt(puertoA);//para direcciones IP
		puerto_B = Integer.parseInt(puertoB);// y puertos
	
			
		//Instanciamos un socket asociado al puerto de escucha y posterior envio.
		DatagramSocket socketA = new DatagramSocket (puerto_A);
			
		//Reservamos el buffer de envio y construimos el datagrama a enviar
		byte [] bufferEnvio = mensaje.getBytes();//Preparamos el vector a enviar
			
			DatagramPacket datagrama_peticion = new DatagramPacket (//Preparamos el envio
						bufferEnvio,
						bufferEnvio.length,
						dirIP_B,
						puerto_B
					);
			
			
			socketA.send(datagrama_peticion);//Realizamos el envio asicrono
			
			
			
			//Reservamos el buffer de recepcion y construimos el datagrama a recibir
			int MAX_LONGITUD = 1;
			
			//Preparamos el buffer de recepcion
			byte [] bufferRecepcion = new byte[MAX_LONGITUD];
			
			//Preparamos el datagrama de respuesta a recibir
			DatagramPacket datagrama_respuesta = new DatagramPacket (bufferRecepcion,MAX_LONGITUD);
		
	
			
			 socketA.setSoTimeout(delay);   // set the timeout  2 millisecounds. 
              try { //TIMEOUT
			  
			   //Recibimos la respuesta del proceso B, la presentamos al usuario y cerramos la conexion.
				 socketA.receive(datagrama_respuesta);//Realizamos la recepcion bloqueante
			  
				String respuesta = new String(bufferRecepcion);
		        
				switch(respuesta) {
		         case "L":
		        	 texto_leer.setText("AUTO ESTACIONADO E:"+puesto);
		         	break;
		         	
		         case "V":
		             texto_leer.setText("LIBERADO E:"+puesto);
		            break;
		         	
		         	
		         default:
		        	 texto_leer.setText("ERROR DE SOLICITUD");
		         	break;
		         }
              
          }
          catch (SocketTimeoutException A) {
        	  texto_leer.setText("TIEMPO DE ESPERA AGOTADO");
          } //TIMEOUT
	
	
         
		     
         socketA.close();
		}//fin try
		catch (Exception ex){
			ex.printStackTrace();
		}//fin catch
		
	}
} // fin paquete1

	
	
	class paquete2 implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent e) {

		
		  //Guardo en string los datos de los deslizadores
		 String bloque=(String)combo_bloque.getSelectedItem();
		 String accion=(String)combo_accion.getSelectedItem();
		 String patente= new String(texto_enviar.getText()) ;	
		 
		// los concateno para poder leerlos y enviarlos juntos 
        String mensaje=patente+" "+"2"+" "+accion;
	
		
   	  	 ////////////////////////ENVIO DEL PAQUETE////////////////////////////////////7
		
		
	  //Variables que contendran los parametros solicitados por consola.
		InetAddress dirIP_B;
		Integer puerto_A, puerto_B;
		
		try{
			
			
 InetAddress direccionB= null;
 		
			
 //Puertos por Defaul
 String puertoB = "3001"; 
 String puertoA = "3000" ;
	   	    
 
 
			//seleccion de PUERTO e IP mediante el nro de bloque
 switch(bloque) {
    
    case "1":  
    	puertoB = "3001"; 
		  puertoA = "3000" ;
		  direccionB= Dir1;
    	puesto="02";
    	break;
    case "2":
    	puertoB = "3003"; 
		  puertoA = "3002" ;
		  direccionB= Dir2;
    	 puesto="07";
    	   break;
    case "3":
    	puertoB = "3005"; 
		  puertoA = "3004" ;
		  direccionB= Dir3;
    	puesto="12";
    	break;
    case "4":
    	puertoB = "3007"; 
		  puertoA = "3006" ;
		  direccionB= Dir4;
 	   puesto="17";
    	break;
    default:
    	break;
    
  }	
			
//DIR DE PRUEBA LOCAL
// direccionB= "localhost";
 
	
	
	
	    
	//Conversiones de tipos entre entrada por teclado y lo que espera la clase socket.
	dirIP_B = direccionB;//cambio de tipos
	puerto_A = Integer.parseInt(puertoA);//para direcciones IP
	puerto_B = Integer.parseInt(puertoB);// y puertos

		
	//Instanciamos un socket asociado al puerto de escucha y posterior envio.
	DatagramSocket socketA = new DatagramSocket (puerto_A);
		
	//Reservamos el buffer de envio y construimos el datagrama a enviar
	byte [] bufferEnvio = mensaje.getBytes();//Preparamos el vector a enviar
		
		DatagramPacket datagrama_peticion = new DatagramPacket (//Preparamos el envio
					bufferEnvio,
					bufferEnvio.length,
					dirIP_B,
					puerto_B
				);
		socketA.send(datagrama_peticion);//Realizamos el envio asicrono
		
		//Reservamos el buffer de recepcion y construimos el datagrama a recibir
		int MAX_LONGITUD = 1;
		
		//Preparamos el buffer de recepcion
		byte [] bufferRecepcion = new byte[MAX_LONGITUD];
		
		//Preparamos el datagrama de respuesta a recibir
		DatagramPacket datagrama_respuesta = new DatagramPacket (bufferRecepcion,MAX_LONGITUD);
		
		
		 socketA.setSoTimeout(delay);   // set the timeout  2 millisecounds. 
         try { //TIMEOUT
		  
		   //Recibimos la respuesta del proceso B, la presentamos al usuario y cerramos la conexion.
			 socketA.receive(datagrama_respuesta);//Realizamos la recepcion bloqueante
		  
			String respuesta = new String(bufferRecepcion);
	         switch(respuesta) {
	         case "L":
	        	 texto_leer.setText("AUTO ESTACIONADO E:"+puesto);
	         	break;
	         	
	         case "V":
	        	 texto_leer.setText("LIBERADO E:"+puesto);
	            break;
	         default:
	        	 texto_leer.setText("ERROR DE SOLICITUD");
	         	break;
	         }
         
     }
     catch (SocketTimeoutException A) {
   	  texto_leer.setText("TIEMPO DE ESPERA AGOTADO");
     } //TIMEOUT

		

   
		 
			
		socketA.close();
		}//fin try
		catch (Exception ex){
			ex.printStackTrace();
		}//fin catch
		
	}
} // fin paquete2

	class paquete3 implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {

			
			  //Guardo en string los datos de los deslizadores
			 String bloque=(String)combo_bloque.getSelectedItem();
			 String accion=(String)combo_accion.getSelectedItem();
			 String patente= new String(texto_enviar.getText()) ;	
			 
			// los concateno para poder leerlos y enviarlos juntos 
			
			String mensaje=patente+" "+"3"+" "+accion;
		
			
	   	  
	   	    
	   	    
	   	
	   	    
	   	 ////////////////////////ENVIO DEL PAQUETE////////////////////////////////////7
			
			
		  //Variables que contendran los parametros solicitados por consola.
			InetAddress dirIP_B;
			Integer puerto_A, puerto_B;
			
			try{
				
				
	InetAddress direccionB= null;
	 		
				
	 
	 
	 //Puertos por Defaul
	    String puertoB = "3001"; 
	    String puertoA = "3000" ;

	 
		   	    
				//seleccion de PUERTO e IP mediante el nro de bloque
	 switch(bloque) {
	    
	    case "1":  
	    	puertoB = "3001"; 
		    puertoA = "3000" ;
		    direccionB= Dir1;
	    	puesto="03";
	    	break;
	    case "2":
	    	puertoB = "3003"; 
		    puertoA = "3002" ;
		    direccionB=Dir2;
	    	 puesto="08";
	    	   break;
	    case "3":
	    	puertoB = "3005"; 
		    puertoA = "3004" ;
		    direccionB= Dir3;
	    	puesto="13";
	    	break;
	    case "4":
	    	puertoB = "3007"; 
		    puertoA = "3006" ;
		    direccionB= Dir4;
	 	   puesto="18";
	    	break;
	    default:
	    	break;
	    
	  }	
				
	//Conversiones de tipos entre entrada por teclado y lo que espera la clase socket.
		dirIP_B = direccionB;//cambio de tipos
		puerto_A = Integer.parseInt(puertoA);//para direcciones IP
		puerto_B = Integer.parseInt(puertoB);// y puertos
	
			
		//Instanciamos un socket asociado al puerto de escucha y posterior envio.
		DatagramSocket socketA = new DatagramSocket (puerto_A);
			
		//Reservamos el buffer de envio y construimos el datagrama a enviar
		byte [] bufferEnvio = mensaje.getBytes();//Preparamos el vector a enviar
			
			DatagramPacket datagrama_peticion = new DatagramPacket (//Preparamos el envio
						bufferEnvio,
						bufferEnvio.length,
						dirIP_B,
						puerto_B
					);
			socketA.send(datagrama_peticion);//Realizamos el envio asicrono
			
			//Reservamos el buffer de recepcion y construimos el datagrama a recibir
			int MAX_LONGITUD = 1;
			
			//Preparamos el buffer de recepcion
			byte [] bufferRecepcion = new byte[MAX_LONGITUD];
			
			//Preparamos el datagrama de respuesta a recibir
			DatagramPacket datagrama_respuesta = new DatagramPacket (bufferRecepcion,MAX_LONGITUD);
			
			 socketA.setSoTimeout(delay);   // set the timeout  2 millisecounds. 
             try { //TIMEOUT
			  
			   //Recibimos la respuesta del proceso B, la presentamos al usuario y cerramos la conexion.
				 socketA.receive(datagrama_respuesta);//Realizamos la recepcion bloqueante
			  
				String respuesta = new String(bufferRecepcion);
		         switch(respuesta) {
		         case "L":
		        	 texto_leer.setText("AUTO ESTACIONADO E:"+puesto);
		         	break;
		         	
		         case "V":
		        	 texto_leer.setText("LIBERADO E:"+puesto);
		           break;
		         	
		         	
		         default:
		        	 texto_leer.setText("ERROR DE SOLICITUD");
		         	break;
		         }
             
         }
         catch (SocketTimeoutException A) {
       	  texto_leer.setText("TIEMPO DE ESPERA AGOTADO");
         } //TIMEOUT
	
        
			 
				
			socketA.close();
			}//fin try
			catch (Exception ex){
				ex.printStackTrace();
			}//fin catch
			
		}
	} // fin paquete3	
	

	class paquete4 implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {

			
			  //Guardo en string los datos de los deslizadores
			 String bloque=(String)combo_bloque.getSelectedItem();
			 String accion=(String)combo_accion.getSelectedItem();
			 String patente= new String(texto_enviar.getText()) ;	
			 
			// los concateno para poder leerlos y enviarlos juntos 
			//texto_leer.setText(patente+" "+"4"+" "+accion); 
			String mensaje=patente+" "+"4"+" "+accion;
		
	   	    
	   	 ////////////////////////ENVIO DEL PAQUETE////////////////////////////////////7
			
			
		  //Variables que contendran los parametros solicitados por consola.
			InetAddress dirIP_B;
			Integer puerto_A, puerto_B;
			
			try{
				
				
	 InetAddress direccionB= null;
 
	 //Puertos por Defaul
	    String puertoB = "3001"; 
	    String puertoA = "3000" ; 		
				
		
		   	    
				//seleccion de PUERTO e IP mediante el nro de bloque
	 switch(bloque) {
	    
	    case "1":  
	    	
	    	puertoB = "3001"; 
		    puertoA = "3000" ;
		    direccionB= Dir1;
	    	puesto="04";
	    	break;
	    case "2":
	    	puertoB = "3003"; 
		    puertoA = "3002" ;
		    direccionB= Dir2;
	    	 puesto="09";
	    	   break;
	    case "3":
	    	puertoB = "3005"; 
		    puertoA = "3004" ;
		    direccionB= Dir3;
	    	puesto="14";
	    	break;
	    case "4":
	    	puertoB = "3007"; 
		    puertoA = "3006" ;
		    direccionB= Dir4;
	 	   puesto="19";
	    	break;
	    default:
	    	break;
	    
	  }	
				 
		
		    
		//Conversiones de tipos entre entrada por teclado y lo que espera la clase socket.
		dirIP_B =direccionB;//cambio de tipos
		puerto_A = Integer.parseInt(puertoA);//para direcciones IP
		puerto_B = Integer.parseInt(puertoB);// y puertos
	
			
		//Instanciamos un socket asociado al puerto de escucha y posterior envio.
		DatagramSocket socketA = new DatagramSocket (puerto_A);
			
		//Reservamos el buffer de envio y construimos el datagrama a enviar
		byte [] bufferEnvio = mensaje.getBytes();//Preparamos el vector a enviar
			
			DatagramPacket datagrama_peticion = new DatagramPacket (//Preparamos el envio
						bufferEnvio,
						bufferEnvio.length,
						dirIP_B,
						puerto_B
					);
			socketA.send(datagrama_peticion);//Realizamos el envio asicrono
			
			//Reservamos el buffer de recepcion y construimos el datagrama a recibir
			int MAX_LONGITUD = 1;
			
			//Preparamos el buffer de recepcion
			byte [] bufferRecepcion = new byte[MAX_LONGITUD];
			
			//Preparamos el datagrama de respuesta a recibir
			DatagramPacket datagrama_respuesta = new DatagramPacket (bufferRecepcion,MAX_LONGITUD);
			
			 socketA.setSoTimeout(delay);   // set the timeout  2 millisecounds. 
             try { //TIMEOUT
			  
			   //Recibimos la respuesta del proceso B, la presentamos al usuario y cerramos la conexion.
				 socketA.receive(datagrama_respuesta);//Realizamos la recepcion bloqueante
			  
				String respuesta = new String(bufferRecepcion);
		         switch(respuesta) {
		         case "L":
		        	 texto_leer.setText("AUTO ESTACIONADO E:"+puesto);
		         	break;
		         	
		         case "V":
		        	 texto_leer.setText("LIBERADO E:"+puesto);
		           break;
		         	
		         default:
		        	 texto_leer.setText("ERROR DE SOLICITUD");
		         	break;
		         }
             
         }
         catch (SocketTimeoutException A) {
       	  texto_leer.setText("TIEMPO DE ESPERA AGOTADO");
         } //TIMEOUT
	
        
			 
				
			socketA.close();
			}//fin try
			catch (Exception ex){
				ex.printStackTrace();
			}//fin catch
			
		}
	} // fin paquete4

	class paquete5 implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {

			
			  //Guardo en string los datos de los deslizadores
			 String bloque=(String)combo_bloque.getSelectedItem();
			 String accion=(String)combo_accion.getSelectedItem();
			 String patente= new String(texto_enviar.getText()) ;	
			 
			// los concateno para poder leerlos y enviarlos juntos 
		
			String mensaje=patente+" "+"5"+" "+accion;
		
			
	   	  
	   	    
	   	    
	   	
	   	    
	   	 ////////////////////////ENVIO DEL PAQUETE////////////////////////////////////7
			
			
		  //Variables que contendran los parametros solicitados por consola.
			InetAddress dirIP_B;
			Integer puerto_A, puerto_B;
			
			try{
				
				InetAddress direccionB= null;
				 
				 //Puertos por Defaul
				    String puertoB = "3001"; 
				    String puertoA = "3000" ; 		
							
		
		   	    
				//seleccion de ip mediante el nro de bloque
	 switch(bloque) {
	    
	    case "1":  
	    	puertoB = "3001"; 
	 	    puertoA = "3000" ; 	
	 	   direccionB= Dir1;
	    	puesto="05";
	    	break;
	    case "2":
	    	puertoB = "3003"; 
	 	    puertoA = "3002" ; 	
	 	   direccionB= Dir2;
	    	 puesto="10";
	    	   break;
	    case "3":
	    	puertoB = "3005"; 
	 	    puertoA = "3004" ; 	
	 	   direccionB= Dir3;
	    	puesto="15";
	    	break;
	    case "4":
	    	puertoB = "3007"; 
	 	    puertoA = "3006" ; 	
	 	   direccionB= Dir4;
	 	   puesto="20";
	    	break;
	    default:
	    	break;
	    
	  }	
				

		    
		//Conversiones de tipos entre entrada por teclado y lo que espera la clase socket.
		dirIP_B = direccionB;//cambio de tipos
		puerto_A = Integer.parseInt(puertoA);//para direcciones IP
		puerto_B = Integer.parseInt(puertoB);// y puertos
	
			
		//Instanciamos un socket asociado al puerto de escucha y posterior envio.
		DatagramSocket socketA = new DatagramSocket (puerto_A);
			
		//Reservamos el buffer de envio y construimos el datagrama a enviar
		byte [] bufferEnvio = mensaje.getBytes();//Preparamos el vector a enviar
			
			DatagramPacket datagrama_peticion = new DatagramPacket (//Preparamos el envio
						bufferEnvio,
						bufferEnvio.length,
						dirIP_B,
						puerto_B
					);
			socketA.send(datagrama_peticion);//Realizamos el envio asicrono
			
			//Reservamos el buffer de recepcion y construimos el datagrama a recibir
			int MAX_LONGITUD = 1;
			
			//Preparamos el buffer de recepcion
			byte [] bufferRecepcion = new byte[MAX_LONGITUD];
			
			//Preparamos el datagrama de respuesta a recibir
			DatagramPacket datagrama_respuesta = new DatagramPacket (bufferRecepcion,MAX_LONGITUD);
			
			 socketA.setSoTimeout(delay);   // set the timeout  2 millisecounds. 
             try { //TIMEOUT
			  
			   //Recibimos la respuesta del proceso B, la presentamos al usuario y cerramos la conexion.
				 socketA.receive(datagrama_respuesta);//Realizamos la recepcion bloqueante
			  
				String respuesta = new String(bufferRecepcion);
		         switch(respuesta) {
		         case "L":
		        	 texto_leer.setText("AUTO ESTACIONADO E:"+puesto);
		         	break;
		         	
		         case "V":
		         texto_leer.setText("LIBERADO E:"+puesto);
		            break;
		         	
		          default:
		        	 texto_leer.setText("ERROR DE SOLICITUD");
		         	break;
		         }
             
         }
         catch (SocketTimeoutException A) {
       	  texto_leer.setText("TIEMPO DE ESPERA AGOTADO");
         } //TIMEOUT
	
        
        
			 
				
			socketA.close();
			}//fin try
			catch (Exception ex){
				ex.printStackTrace();
			}//fin catch
			
		}
	} // fin paquete5




}
