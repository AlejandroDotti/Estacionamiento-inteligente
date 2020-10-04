package vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import java.awt.GridLayout;
import javax.swing.JButton;
import java.awt.FlowLayout;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

import codigo.HiloCliente;
import codigo.hiloKeepAlive;

import javax.swing.JTextPane;
import java.awt.Color;



public class Ventana {

	private JFrame frmControladorBarreras;
	private JTextField txtIngresePatente;
	private InetAddress dirIP_1;
	private InetAddress dirIP_2;
	private InetAddress dirIP_3;
	private InetAddress dirIP_4;
	private InetAddress IP_DNS;
	
	private Integer puerto;
	private Integer puerto_escucha;
	public  Integer escucha_keepalive1;
	public  Integer escucha_keepalive2;
	public  Integer escucha_keepalive3;
	public  Integer escucha_keepalive4;
	public String IP_DNS_S;
	public String IP_DNS_S_2;
	
	private String mensaje;
	private JTextPane txtpnBaja;
	private JTextPane txtpnBaja_1;
	private JTextPane txtpnBaja_2;
	private JTextPane txtpnBaja_3;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Ventana window = new Ventana();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	public Ventana() {
		initialize();
	}

	private void initialize() {
		File archivo = null;
	    FileReader fr = null;     
	    try{ 
	    	 //Configuracion para leer el archivo (debe modificarse la direccion para localizarlo)
	         archivo = new File ("C:\\Users\\Agustinceravolo\\Documents\\Java\\Programas\\TP_DISTRIBUIDOS_UDP2_27-07\\documentoALeer.txt");
	         fr = new FileReader (archivo);
	         BufferedReader buffer = new BufferedReader(fr);
	         //Configuracion para localicar el DNS
	         IP_DNS_S=buffer.readLine();
	         System.setProperty("sun.net.spi.nameservice.nameservers", IP_DNS_S);
	         System.setProperty("sun.net.spi.nameservice.provider.1", "dn,sun");
	        
	         IP_DNS=InetAddress.getByName("dns.unm.ar");     
	         IP_DNS_S_2=IP_DNS.getHostAddress();
	         if(IP_DNS_S_2.contentEquals(IP_DNS_S)){	  
	        	 //Configuracion para solicitar las direcciones IP en el server DNS
			        dirIP_1 = InetAddress.getByName("barrera1.unm.ar");
			        dirIP_2 = InetAddress.getByName("barrera2.unm.ar");
			        dirIP_3 = InetAddress.getByName("barrera3.unm.ar");
			        dirIP_4 = InetAddress.getByName("barrera4.unm.ar");
			        puerto = Integer.parseInt(buffer.readLine());
			        puerto_escucha = Integer.parseInt(buffer.readLine());
			        escucha_keepalive1 = Integer.parseInt(buffer.readLine());
			        escucha_keepalive2 = Integer.parseInt(buffer.readLine());
			        escucha_keepalive3 = Integer.parseInt(buffer.readLine());
			        escucha_keepalive4 = Integer.parseInt(buffer.readLine());
	         
			        fr.close();
	         
			    //Se prepara la interface con todos sus botones y características.
			        
				 	frmControladorBarreras = new JFrame();
					frmControladorBarreras.setTitle("Manipulador Barreras");
					frmControladorBarreras.setBounds(352, 142, 704, 284);
					frmControladorBarreras.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					frmControladorBarreras.getContentPane().setLayout(null);     
					frmControladorBarreras.setVisible(true);
	    	
				    JTextPane textPane;
				    txtpnBaja_2 = new JTextPane();
				    txtpnBaja_2.setText("             BAJA");
				    txtpnBaja_2.setBackground(Color.CYAN);
					txtpnBaja_2.setBounds(44, 142, 115, 26);
					frmControladorBarreras.getContentPane().add(txtpnBaja_2);
					
					JTextPane textPane_1;
					txtpnBaja_1 = new JTextPane();
					txtpnBaja_1.setText("             BAJA");
					txtpnBaja_1.setBackground(Color.CYAN);
					txtpnBaja_1.setBounds(200, 142, 115, 26);
					frmControladorBarreras.getContentPane().add(txtpnBaja_1);
			
					JTextPane textPane_2;
					txtpnBaja_3 = new JTextPane();
					txtpnBaja_3.setText("             BAJA");
					txtpnBaja_3.setBackground(Color.CYAN);
					txtpnBaja_3.setBounds(354, 142, 115, 26);
					frmControladorBarreras.getContentPane().add(txtpnBaja_3);
					
					JTextPane textPane_3;
					txtpnBaja = new JTextPane();
					txtpnBaja.setText("             BAJA");
					txtpnBaja.setBackground(Color.CYAN);
					txtpnBaja.setBounds(517, 142, 115, 26);
					frmControladorBarreras.getContentPane().add(txtpnBaja);
	    
					//Cuando en la interface se presiona el boton X se inicia un hilox pasando todos los parametros que corresponden
					//Para lorgar el contacto con el proceso barrera que le corresponde a ese boton
							JButton btnBarrera = new JButton("Barrera 1");
							btnBarrera.setVisible(false);
							btnBarrera.addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent e) {
									mensaje = "1"+txtIngresePatente.getText();
									Thread hilo1 = new Thread(new HiloCliente(dirIP_1,puerto,puerto_escucha,mensaje,btnBarrera,txtpnBaja_2));
									hilo1.start();
								}
							});
							btnBarrera.setBounds(44, 183, 115, 29);
							frmControladorBarreras.getContentPane().add(btnBarrera);
							
							JButton btnBarrera_1 = new JButton("Barrera 2");
							btnBarrera_1.setVisible(false);
							btnBarrera_1.addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent arg0) {
									mensaje = "2"+txtIngresePatente.getText();
									Thread hilo2 = new Thread(new HiloCliente(dirIP_2,puerto,puerto_escucha,mensaje,btnBarrera_1,txtpnBaja_1));
									hilo2.start();
								}
							});
							btnBarrera_1.setBounds(200, 183, 115, 29);
							frmControladorBarreras.getContentPane().add(btnBarrera_1);
							
							JButton btnBarrera_2 = new JButton("Barrera 3");
							btnBarrera_2.setVisible(false);
							btnBarrera_2.addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent e) {
									mensaje = "3"+txtIngresePatente.getText();
									Thread hilo3 = new Thread(new HiloCliente(dirIP_3,puerto,puerto_escucha,mensaje,btnBarrera_2,txtpnBaja_3));
									hilo3.start();
								}
							});
							btnBarrera_2.setBounds(354, 183, 115, 29);
							frmControladorBarreras.getContentPane().add(btnBarrera_2);
							
							JButton btnBarrera_3 = new JButton("Barrera 4");
							btnBarrera_3.setVisible(false);
							btnBarrera_3.addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent e) {
									mensaje = "4"+txtIngresePatente.getText();
									Thread hilo4 = new Thread(new HiloCliente(dirIP_4,puerto,puerto_escucha,mensaje,btnBarrera_3,txtpnBaja));
									hilo4.start();
								}
							});
							btnBarrera_3.setBounds(517, 183, 115, 29);
							frmControladorBarreras.getContentPane().add(btnBarrera_3);
							
							txtIngresePatente = new JTextField();
							txtIngresePatente.setHorizontalAlignment(SwingConstants.CENTER);
							txtIngresePatente.setBounds(266, 49, 146, 26);
							frmControladorBarreras.getContentPane().add(txtIngresePatente);
							txtIngresePatente.setColumns(10);
							//Llamado a los hilos que permiten intercambiar mensajes para definir si los procesos barrera estan conectados al servidor 
							        Thread HiloAliveBoton1 = new Thread(new hiloKeepAlive(escucha_keepalive1,btnBarrera));
							        HiloAliveBoton1.start();
							        Thread HiloAliveBoton2 = new Thread(new hiloKeepAlive(escucha_keepalive2,btnBarrera_1));
							        HiloAliveBoton2.start();
							        Thread HiloAliveBoton3 = new Thread(new hiloKeepAlive(escucha_keepalive3,btnBarrera_2));
							        HiloAliveBoton3.start();
							        Thread HiloAliveBoton4 = new Thread(new hiloKeepAlive(escucha_keepalive4,btnBarrera_3));
							        HiloAliveBoton4.start();

	         	}
		         else
		         {
		        	 	System.out.println("******************** ERROR - No es posible conectarse con el DNS ********************");
		         }
	    	}
      	catch(Exception e){
      		 			System.out.println("******************** ERROR - No es posible conectarse con el DNS ********************");
      						}
	}
}
