package dataStructureAlgo;

import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Project.ViewEquipment;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedWriter;

public class User_Fill_Up extends JFrame {

	private JPanel contentPane;
	private JTextField Name;
	private JTextField Address;
	private JTextField Contact;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					User_Fill_Up frame = new User_Fill_Up();
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
	private void UserInfo(String name, String address, String phone) {
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter("USERINFO.txt", true));
			writer.write( name + " , " + address + " , " + phone);
			writer.newLine();
			writer.close();
			
			JOptionPane.showMessageDialog(null, "Successfully Login");
		} catch (IOException ex) {
			JOptionPane.showMessageDialog(null, "Invalid Credentials");
			ex.printStackTrace();
			
		}
	}
	public User_Fill_Up() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1072, 650);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		Name = new JTextField();
		Name.setBounds(351, 151, 332, 43);
		Name.setBackground(null);
		Name.setBorder(null);
		Name.setOpaque(false);
		Name.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		contentPane.add(Name);
		Name.setColumns(10);
		
		Address = new JTextField();
		Address.setBounds(356, 230, 327, 43);
		Address.setBackground(null);
		Address.setBorder(null);
		Address.setOpaque(false);
		Address.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		contentPane.add(Address);
		Address.setColumns(10);
		
		Contact = new JTextField();
		Contact.setBounds(356, 307, 327, 43);
		Contact.setBackground(null);
		Contact.setBorder(null);
		Contact.setOpaque(false);
		Contact.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		contentPane.add(Contact);
		Contact.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\VJRM14\\Downloads\\3 (1).png"));
		lblNewLabel.setBounds(-11, 0, 1095, 600);
		contentPane.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Welcome_Page welcomePage = new Welcome_Page();
				welcomePage.setVisible(true);
				dispose();
			}
		});
		btnNewButton.setBounds(287, 434, 179, 43);
		btnNewButton.setOpaque(false);
		btnNewButton.setContentAreaFilled(false);
		btnNewButton.setBorderPainted(false);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("");
		btnNewButton_1.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        String name = Name.getText();
		        String addr = Address.getText();
		        String phone = Contact.getText();

		        if (name.isEmpty() || addr.isEmpty() || phone.isEmpty()) {
		            JOptionPane.showMessageDialog(null, "Incomplete fields. Please fill in all the required information.");
		        } else if (phone.startsWith("09") && phone.length() == 11) {
		            UserInfo(name, addr, phone);
		            User_View_Equipments userView = new User_View_Equipments();
		            userView.setVisible(true);
		            dispose();
		        } else {
		            JOptionPane.showMessageDialog(null, "Invalid phone number. Please enter a valid 11-digit number starting with '09'.");
		        }
		    }
		});

		btnNewButton_1.setBounds(584, 434, 179, 43);
		btnNewButton_1.setOpaque(false);
		btnNewButton_1.setContentAreaFilled(false);
		btnNewButton_1.setBorderPainted(false);
		contentPane.add(btnNewButton_1);
	}

}
