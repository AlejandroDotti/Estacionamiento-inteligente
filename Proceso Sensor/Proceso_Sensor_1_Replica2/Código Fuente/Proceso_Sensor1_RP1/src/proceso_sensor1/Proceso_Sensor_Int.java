package proceso_sensor1;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import javax.swing.JTextPane;
import javax.swing.Timer;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;


public class Proceso_Sensor_Int {
  
	
	
	public JFrame frmProcesoSensor;
	private JTextField campoSensor;
	private JTextField campoAccion;
	private JTextField campoEstado;
	private JTextField Bloque;

	/**
	 * Launch the application.
	 */
	


	static int z = 0;
	
	public static void main(String[] args) {
		
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Proceso_Sensor_Int frame = new Proceso_Sensor_Int();
					frame.frmProcesoSensor.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Proceso_Sensor_Int() {
		initialize();
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	
	@SuppressWarnings("static-access")
	private void initialize() {
	

		
		frmProcesoSensor = new JFrame();
		frmProcesoSensor.setTitle("Proceso Sensor");
		frmProcesoSensor.setBounds(100, 100, 450, 300);
		frmProcesoSensor.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmProcesoSensor.getContentPane().setLayout(new BorderLayout(0, 0));
		
		//Bloquera maximizar y cosas
		frmProcesoSensor.setAlwaysOnTop(true);
		frmProcesoSensor.setResizable(false);
		frmProcesoSensor.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//
		
		///LOGO/////////////////////////////////////////////////
		Image iconoPropio=Toolkit.getDefaultToolkit().getImage(getClass().getResource("UNMIcon.png"));
		frmProcesoSensor.setIconImage(iconoPropio);
		
		
		JTextPane textPane = new JTextPane();
		frmProcesoSensor.getContentPane().add(textPane, BorderLayout.EAST);
		
		JPanel panel = new JPanel();
		panel.setForeground(Color.WHITE);
		panel.setBackground(Color.WHITE);
		frmProcesoSensor.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JTextPane txtpnBloque = new JTextPane();
		txtpnBloque.setBackground(Color.WHITE);
		txtpnBloque.setForeground(Color.BLACK);
		txtpnBloque.setFont(new Font("Calibri Light", Font.PLAIN, 14));
		txtpnBloque.setEditable(false);
		txtpnBloque.setText("Bloque");
		txtpnBloque.setBounds(36, 25, 81, 32);
		panel.add(txtpnBloque);
		
		JTextPane txtpnSensor = new JTextPane();
		txtpnSensor.setEditable(false);
		txtpnSensor.setForeground(Color.BLACK);
		txtpnSensor.setBackground(Color.WHITE);
		txtpnSensor.setFont(new Font("Calibri Light", Font.PLAIN, 14));
		txtpnSensor.setText("Parsela");
		txtpnSensor.setBounds(36, 68, 81, 32);
		panel.add(txtpnSensor);
		
		JTextPane txtpnEstado = new JTextPane();
		txtpnEstado.setEditable(false);
		txtpnEstado.setBackground(Color.WHITE);
		txtpnEstado.setForeground(Color.BLACK);
		txtpnEstado.setText("Estado");
		txtpnEstado.setFont(new Font("Calibri Light", Font.PLAIN, 14));
		txtpnEstado.setBounds(36, 154, 81, 32);
		panel.add(txtpnEstado);
		
		JTextPane txtpnAccin = new JTextPane();
		txtpnAccin.setEditable(false);
		txtpnAccin.setForeground(Color.BLACK);
		txtpnAccin.setBackground(Color.WHITE);
		txtpnAccin.setText("Acci\u00F3n");
		txtpnAccin.setFont(new Font("Calibri Light", Font.PLAIN, 14));
		txtpnAccin.setBounds(36, 111, 81, 32);
		panel.add(txtpnAccin);
		
		campoSensor = new JTextField();
		campoSensor.setHorizontalAlignment(SwingConstants.CENTER);
		
		campoSensor.setColumns(10);
		campoSensor.setBounds(144, 69, 250, 20);
		panel.add(campoSensor);
		campoAccion = new JTextField();
		campoAccion.setHorizontalAlignment(SwingConstants.CENTER);
		campoAccion.setColumns(10);
		campoAccion.setBounds(144, 111, 250, 20);
		panel.add(campoAccion);
		
		campoEstado = new JTextField("");
		campoEstado.setHorizontalAlignment(SwingConstants.CENTER);
		campoEstado.setEditable(true);
		campoEstado.setColumns(10);
		campoEstado.setBounds(144, 154, 250, 20);
		panel.add(campoEstado);
		
		Bloque = new JTextField();
		Bloque.setHorizontalAlignment(SwingConstants.CENTER);
		Bloque.setColumns(10);
		Bloque.setBounds(144, 29, 250, 20);
		panel.add(Bloque);
		

				Timer t= new Timer(1000, new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
                
		
                 if(z==0) {
                	 z=1;
						 FileWriter fichero = null;
					        PrintWriter pw = null;
					        try{
					            fichero = new FileWriter("Datos.txt");
					            pw = new PrintWriter(fichero); 
					            pw.println("");

					           } catch (Exception e1) {
					            e1.printStackTrace();
					        } finally {
					           try {
					           // Nuevamente aprovechamos el finally para 
					           // asegurarnos que se cierra el fichero.
					           if (null != fichero)
					              fichero.close();
					           	   } catch (Exception e2) {
					              e2.printStackTrace();
					           	   						   }
					                  }					
                 }
	      File archivo = null;
	      FileReader fr = null;
	      BufferedReader br = null;

	      try {
	       
	         archivo = new File ("Datos.txt");
	         
	         fr = new FileReader (archivo);
	         br = new BufferedReader(fr);
	        
	            
	         // Lectura del fichero
	         String linea;
	         String mensaje = "";	
	    
	         
	         while((linea=br.readLine())!=null) {
	            mensaje=mensaje+linea+" ";
	       }
  
	           Bloque.setText("1");
	                     
				int i=0;
				String[] datos = mensaje.split(" ");
				for(String item : datos) {
					
					switch(i) {
					case 1:
						campoSensor.setText(item);
						
						break;
					case 2:
						campoAccion.setText(item);
						break;			
					case 3:
						if(item.equals("V"))							
						campoEstado.setText("Liberado");
						else if(item.equals("L"))
						campoEstado.setText("Estacionado");
						else 
						campoEstado.setText("Hubo un error");							
						i=-1;
			            break;		
							  }
					i++;
				}
if(i==3) {
campoEstado.setText("Esperando respuesta del servidor");
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

try {
Thread.sleep(500);
} catch (InterruptedException e1) {
// TODO Auto-generated catch block
e1.printStackTrace();
}		      
				}
	
		});
				t.start();
			
		

	
	}
}
