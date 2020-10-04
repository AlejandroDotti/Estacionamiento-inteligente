package servidorTest;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Toolkit;

public class serverGUI extends JFrame {

	private JPanel contentPane;
	public JTextField text1;
	
	public JTextField text3;
	public JTextField text4;
	public JTextField text5;
	public JTextField text6;
	public JTextField text7;
	public JTextField text8;
	public JTextField text9;
	public JTextField text10;
	public JTextField text20;
	public JTextField text19;
	public JTextField text18;
	public JTextField text17;
	public JTextField text16;
	public JTextField text15;
	public JTextField text14;
	public JTextField text13;
	public JTextField text12;
	public JTextField text11;
	public JTextField text2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					serverGUI frame = new serverGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public serverGUI() {
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\voyag\\Desktop\\UNMIcon.png"));
		setTitle("Servidor");
		setAlwaysOnTop(true);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 234, 358);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		text1 = new JTextField();
		text1.setEditable(false);
		text1.setBounds(10, 11, 86, 20);
		contentPane.add(text1);
		text1.setColumns(10);
		
		text3 = new JTextField();
		text3.setEditable(false);
		text3.setColumns(10);
		text3.setBounds(10, 73, 86, 20);
		contentPane.add(text3);
		
		text4 = new JTextField();
		text4.setEditable(false);
		text4.setColumns(10);
		text4.setBounds(10, 104, 86, 20);
		contentPane.add(text4);
		
		text5 = new JTextField();
		text5.setEditable(false);
		text5.setColumns(10);
		text5.setBounds(10, 135, 86, 20);
		contentPane.add(text5);
		
		text6 = new JTextField();
		text6.setEditable(false);
		text6.setColumns(10);
		text6.setBounds(10, 166, 86, 20);
		contentPane.add(text6);
		
		text7 = new JTextField();
		text7.setEditable(false);
		text7.setColumns(10);
		text7.setBounds(10, 197, 86, 20);
		contentPane.add(text7);
		
		text8 = new JTextField();
		text8.setEditable(false);
		text8.setColumns(10);
		text8.setBounds(10, 228, 86, 20);
		contentPane.add(text8);
		
		text9 = new JTextField();
		text9.setEditable(false);
		text9.setColumns(10);
		text9.setBounds(10, 259, 86, 20);
		contentPane.add(text9);
		
		text10 = new JTextField();
		text10.setEditable(false);
		text10.setColumns(10);
		text10.setBounds(10, 290, 86, 20);
		contentPane.add(text10);
		
		text20 = new JTextField();
		text20.setEditable(false);
		text20.setColumns(10);
		text20.setBounds(119, 290, 86, 20);
		contentPane.add(text20);
		
		text19 = new JTextField();
		text19.setEditable(false);
		text19.setColumns(10);
		text19.setBounds(119, 259, 86, 20);
		contentPane.add(text19);
		
		text18 = new JTextField();
		text18.setEditable(false);
		text18.setColumns(10);
		text18.setBounds(119, 228, 86, 20);
		contentPane.add(text18);
		
		text17 = new JTextField();
		text17.setEditable(false);
		text17.setColumns(10);
		text17.setBounds(119, 197, 86, 20);
		contentPane.add(text17);
		
		text16 = new JTextField();
		text16.setEditable(false);
		text16.setColumns(10);
		text16.setBounds(119, 166, 86, 20);
		contentPane.add(text16);
		
		text15 = new JTextField();
		text15.setEditable(false);
		text15.setColumns(10);
		text15.setBounds(119, 135, 86, 20);
		contentPane.add(text15);
		
		text14 = new JTextField();
		text14.setEditable(false);
		text14.setColumns(10);
		text14.setBounds(119, 104, 86, 20);
		contentPane.add(text14);
		
		text13 = new JTextField();
		text13.setEditable(false);
		text13.setColumns(10);
		text13.setBounds(119, 73, 86, 20);
		contentPane.add(text13);
		
		text12 = new JTextField();
		text12.setEditable(false);
		text12.setColumns(10);
		text12.setBounds(119, 42, 86, 20);
		contentPane.add(text12);
		
		text11 = new JTextField();
		text11.setEditable(false);
		text11.setColumns(10);
		text11.setBounds(119, 11, 86, 20);
		contentPane.add(text11);
		
		text2 = new JTextField();
		text2.setEditable(false);
		text2.setColumns(10);
		text2.setBounds(10, 42, 86, 20);
		contentPane.add(text2);
	}

}
