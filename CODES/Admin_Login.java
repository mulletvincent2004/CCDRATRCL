package dataStructureAlgo;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JPasswordField;

public class Admin_Login extends JFrame {

	private JPanel contentPane;
	private JTextField AdminEmail;
	private JPasswordField AdminPassword;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Admin_Login frame = new Admin_Login();
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
	public Admin_Login() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1085, 650);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		AdminEmail = new JTextField();
		AdminEmail.setFont(new Font("Times New Roman", Font.BOLD, 20));
		AdminEmail.setBounds(368, 198, 402, 56);
		AdminEmail.setBackground(null);
		AdminEmail.setBorder(null);
		AdminEmail.setOpaque(false);
		contentPane.add(AdminEmail);
		AdminEmail.setColumns(10);
		
		AdminPassword = new JPasswordField();
		AdminPassword.setFont(new Font("Times New Roman", Font.BOLD, 20));
		AdminPassword.setBackground(null);
		AdminPassword.setBorder(null);
		AdminPassword.setOpaque(false);
		AdminPassword.setBounds(368, 287, 402, 56);
		contentPane.add(AdminPassword);
		AdminPassword.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\VJRM14\\Downloads\\2 (1).png"));
		lblNewLabel.setBounds(0, 0, 1084, 600);
		contentPane.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("");
		btnNewButton.addActionListener(new ActionListener() {
			
		    public void actionPerformed(ActionEvent e) {
		        boolean userExists = false;

		        String UserInput = AdminEmail.getText();
		        String PassInput = AdminPassword.getText(); // Use getText() for JTextField

		        String[] username = {"Admin1"};
		        String[] password = {"123"};

		        for (int i = 0; i < username.length; i++) {
		            if (UserInput.equals(username[i]) && PassInput.equals(password[i])) {
		                JOptionPane.showMessageDialog(null, "Login successful!");
		                Admin_File adminFile = new Admin_File();
		                adminFile.setVisible(true);
		                dispose();
		                userExists = true;
		                break;
		            }
		        }

		        if (!userExists) {
		            if (!UserInput.equals(username[0]) && PassInput.equals(password[0])) {
		                JOptionPane.showMessageDialog(null, "Incorrect username or password.");
		            } else if (UserInput.equals(username[0]) && !PassInput.equals(password[0])) {
		                JOptionPane.showMessageDialog(null, "Incorrect username or password.");
		            } else {
		                JOptionPane.showMessageDialog(null, "Incorrect username or password.");
		            }
		        }
		        
		    }
		});

		btnNewButton.setBounds(464, 371, 189, 56);
		btnNewButton.setOpaque(false);
		btnNewButton.setContentAreaFilled(false);
		btnNewButton.setBorderPainted(false);
		contentPane.add(btnNewButton);
	}

}
