package procesoBarrera;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JSplitPane;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;

import javax.swing.JCheckBox;

public class GUI_procesoBarrera extends JFrame {
	public JTextField txtCochera_Num;
	public JTextField txtIrPorCalle;
	public JTextField txtEstacionamientoLleno;
	public JTextField txtPatente;
	public JTextField textNumBarrera;
	public JTextField txtBarreraMulticast;
    public  BufferedWriter bwOutputKeeoAliveGui_F;
    public File FileBorrar;

	public GUI_procesoBarrera(BufferedWriter FileOutputKeeoAliveGui,File filetowriteBorrar ) {
		bwOutputKeeoAliveGui_F =FileOutputKeeoAliveGui;
		FileBorrar=filetowriteBorrar;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\tomas\\eclipse-workspace\\ProcesoBarrera(11-07-2020)\\Lib\\UNMIcon.png"));
		setResizable(false);

		Dimension pantalla=Toolkit.getDefaultToolkit().getScreenSize();
		int alto = pantalla.height;
		int ancho= pantalla.width;
		setAlwaysOnTop(true);
		setForeground(Color.RED);
		setBackground(Color.BLUE);
		setTitle("Proceso Barrera");
//		setBounds(100, 100, 704, 284);
		setBounds(ancho/2-352, alto/2-142, 704, 284);
		getContentPane().setLayout(null);
		
		JLabel lblCochera_Num = new JLabel("Cochera N°");
		lblCochera_Num.setBounds(12, 86, 100, 15);
		getContentPane().add(lblCochera_Num);
		
		txtCochera_Num = new JTextField();
		txtCochera_Num.setForeground(Color.BLACK);
		txtCochera_Num.setHorizontalAlignment(SwingConstants.CENTER);
		txtCochera_Num.setText("");
		txtCochera_Num.setEditable(false);
		txtCochera_Num.setBounds(100, 84, 202, 19);
		getContentPane().add(txtCochera_Num);
		txtCochera_Num.setColumns(10);
		
		JLabel lbl_IrPorCalle = new JLabel("Calle:");
		lbl_IrPorCalle.setBounds(12, 120, 100, 15);
		getContentPane().add(lbl_IrPorCalle);
		
		txtIrPorCalle = new JTextField();
		txtIrPorCalle.setForeground(Color.BLACK);
		txtIrPorCalle.setEditable(false);
		txtIrPorCalle.setHorizontalAlignment(SwingConstants.CENTER);
		txtIrPorCalle.setText("");
		txtIrPorCalle.setColumns(10);
		txtIrPorCalle.setBounds(100, 118, 202, 19);
		getContentPane().add(txtIrPorCalle);
		
		JLabel lblBarrera = new JLabel("Barrera N°");
		lblBarrera.setBounds(12, 31, 100, 15);
		getContentPane().add(lblBarrera);
		
		txtEstacionamientoLleno = new JTextField();
		txtEstacionamientoLleno.setHorizontalAlignment(SwingConstants.CENTER);
		txtEstacionamientoLleno.setForeground(Color.BLACK);
		txtEstacionamientoLleno.setBackground(Color.GREEN);
		txtEstacionamientoLleno.setEditable(false);
		txtEstacionamientoLleno.setText("HAY LUGAR");
		txtEstacionamientoLleno.setBounds(12, 204, 290, 19);
		getContentPane().add(txtEstacionamientoLleno);
		txtEstacionamientoLleno.setColumns(10);
		
		txtBarreraMulticast = new JTextField();
		txtBarreraMulticast.setHorizontalAlignment(SwingConstants.CENTER);
		txtBarreraMulticast.setForeground(Color.BLACK);
		txtBarreraMulticast.setBackground(Color.CYAN);
		txtBarreraMulticast.setEditable(false);
		txtBarreraMulticast.setText("");
		txtBarreraMulticast.setBounds(12, 204, 290, 19);
		getContentPane().add(txtBarreraMulticast);
		txtBarreraMulticast.setColumns(10);
		txtBarreraMulticast.setVisible(false);
		
		JLabel lblPatente = new JLabel("Patente:");
		lblPatente.setBounds(12, 60, 100, 15);
		getContentPane().add(lblPatente);
		
		txtPatente = new JTextField();
		txtPatente.setEditable(false);
		txtPatente.setText("");
		txtPatente.setHorizontalAlignment(SwingConstants.CENTER);
		txtPatente.setForeground(Color.BLACK);
		txtPatente.setColumns(10);
		txtPatente.setBounds(100, 56, 202, 19);
		getContentPane().add(txtPatente);
		
		textNumBarrera = new JTextField();
		textNumBarrera.setText("");
		textNumBarrera.setHorizontalAlignment(SwingConstants.CENTER);
		textNumBarrera.setForeground(Color.BLACK);
		textNumBarrera.setEditable(false);
		textNumBarrera.setColumns(10);
		textNumBarrera.setBounds(100, 28, 202, 19);
		getContentPane().add(textNumBarrera);
		
		
		   addWindowListener(new WindowAdapter() {
			   @Override
			    public void windowClosing(WindowEvent e) {
			        try {
						bwOutputKeeoAliveGui_F.close();
						FileBorrar.delete();
						FileBorrar.deleteOnExit();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
			        System.exit(0);
			    }
			});

		
	}


}
