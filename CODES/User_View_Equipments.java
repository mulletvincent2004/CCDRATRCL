package dataStructureAlgo;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.Font;
import javax.swing.border.MatteBorder;
import java.awt.Color;

public class User_View_Equipments extends JFrame {

	private JPanel contentPane;
	private JTable table;
    private boolean dataLoaded = false; // Add a flag to track if data is already loaded

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					User_View_Equipments frame = new User_View_Equipments();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
		private void loadEquipmentsData() {
        if (dataLoaded) {
            return; // Data is already loaded, no need to load it again
        }
        DefaultTableModel model = (DefaultTableModel) table.getModel();

        try (BufferedReader br = new BufferedReader(new FileReader("ADDEQUIPS.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    String equipmentName = parts[0];
                    String equipmentQuantity = parts[1];
                    model.addRow(new Object[]{equipmentName, equipmentQuantity});
                }
            }
            dataLoaded = true; // Set the flag to true to indicate data is loaded
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

	/**
	 * Create the frame.
	 */
	public User_View_Equipments() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1068, 650);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(131, 240, 481, 317);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setBackground(new Color(175, 175, 254));
		table.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(0, 0, 0)));
		table.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"EQUIPMENTS", "QUANTITY"
			}
		));
		scrollPane.setViewportView(table);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\VJRM14\\Downloads\\5 (1).png"));
		lblNewLabel.setBounds(-15, 0, 1099, 600);
		contentPane.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				User_Rental_Form userFillUp = new User_Rental_Form();
				userFillUp.setVisible(true);
				dispose();
			}
		});
		btnNewButton.setBounds(802, 458, 180, 42);
		btnNewButton.setOpaque(false);
		btnNewButton.setContentAreaFilled(false);
		btnNewButton.setBorderPainted(false);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnNewButton_1.setBounds(802, 522, 180, 47);
		btnNewButton_1.setOpaque(false);
		btnNewButton_1.setContentAreaFilled(false);
		btnNewButton_1.setBorderPainted(false);
		contentPane.add(btnNewButton_1);
		
		  // Load data from the "ADDEQUIPS.txt" file when the frame is created
        loadEquipmentsData();
	}

}
