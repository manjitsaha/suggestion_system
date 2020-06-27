package suggestingArticles;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.EventQueue;
import java.sql.Connection;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;


import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class User extends JFrame {

	private JPanel contentPane;
	Connection con = null; 
	private JTextField usernameTf;
	private JPasswordField passwordTf;
	JLabel errorLbl;
	static String manjitN = "manjit";
	static String manjitP = "manjit";
	static String aditN = "adit";
	static String aditP = "adit";
	static String meenakshiN = "meenakshi";
	static String meenakshiP = "meenakshi";
	static String loggedInUser;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					User frame = new User();
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
	
	public static void theme() {
		 try {
	            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
	                if ("Windows".equals(info.getName())) {
	                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
	                    break;
	                }
	            }
	        } catch (ClassNotFoundException ex) {
	            java.util.logging.Logger.getLogger(User.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
	        } catch (InstantiationException ex) {
	            java.util.logging.Logger.getLogger(User.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
	        } catch (IllegalAccessException ex) {
	            java.util.logging.Logger.getLogger(User.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
	        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
	            java.util.logging.Logger.getLogger(User.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
	        }
		 
	}
	
	
	public void checkUser() {
		String uName = usernameTf.getText().toString();
		String uPass = passwordTf.getText().toString();
		
		if(uName.equals(manjitN) || uName.equals(aditN) ||uName.equals(meenakshiN) && uPass.equals(manjitP) || uPass.equals(aditP) || uPass.equals(meenakshiP)) {
			
			loggedInUser = uName;
			Home.main(null);
			this.dispose();
		}else {
			errorLbl.setText("Invalid username or password");
			errorLbl.setVisible(true);
		}
	}
	
	
	
	public User() {
		theme();
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 616, 438);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		usernameTf = new JTextField();
		usernameTf.setFont(new Font("SansSerif", Font.PLAIN, 16));
		usernameTf.setToolTipText("Username");
		usernameTf.setBounds(236, 99, 156, 34);
		contentPane.add(usernameTf);
		usernameTf.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Username");
		lblNewLabel.setFont(new Font("SansSerif", Font.PLAIN, 16));
		lblNewLabel.setBounds(236, 74, 100, 27);
		contentPane.add(lblNewLabel);
		
		passwordTf = new JPasswordField();
		passwordTf.setFont(new Font("SansSerif", Font.PLAIN, 16));
		passwordTf.setToolTipText("Username");
		passwordTf.setColumns(10);
		passwordTf.setBounds(236, 170, 156, 34);
		contentPane.add(passwordTf);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("SansSerif", Font.PLAIN, 16));
		lblPassword.setBounds(236, 144, 100, 27);
		contentPane.add(lblPassword);
		
		JButton signinbtn = new JButton("Sign In");
		signinbtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				checkUser();
			}
		});
		signinbtn.setBounds(236, 237, 156, 23);
		contentPane.add(signinbtn);
		
		
		errorLbl = new JLabel("New label");
		errorLbl.setVisible(false);
		errorLbl.setForeground(Color.RED);
		errorLbl.setHorizontalAlignment(SwingConstants.CENTER);
		errorLbl.setBounds(236, 211, 150, 14);
		contentPane.add(errorLbl);
		
		
	}
	
	
	
	
	
}
