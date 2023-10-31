package dataStructureAlgo;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.Font;
import javax.swing.border.CompoundBorder;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.awt.Color;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;

public class Admin_Available_Equipments extends JFrame {

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
                    Admin_Available_Equipments frame = new Admin_Available_Equipments();
                    frame.setVisible(true);
                    frame.loadEquipmentsData(); // Load data when the frame is created
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
    public Admin_Available_Equipments() {
        setBounds(100, 100, 1072, 650);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(323, 224, 556, 323);
        contentPane.add(scrollPane);

        table = new JTable();
        table.setBackground(new Color(175, 175, 254));
        table.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, new Color(175, 175, 254), new Color(175, 175, 254), new Color(175, 175, 254), new Color(175, 175, 254)));
        table.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 18));
        table.setModel(new DefaultTableModel(
            new Object[][] {},
            new String[] {
                "EQUIPMENTS", "QUANTITY"
            }
        ));
        scrollPane.setViewportView(table);

        JLabel lblNewLabel = new JLabel("");
        lblNewLabel.setIcon(new ImageIcon("C:\\Users\\VJRM14\\Downloads\\7 (1).png"));
        lblNewLabel.setBounds(-12, 0, 1096, 600);
        contentPane.add(lblNewLabel);

        JButton ContinueButton = new JButton("");
        ContinueButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Admin_Records adminRecords = new Admin_Records();
                adminRecords.setVisible(true);
                dispose();
            }
        });
        ContinueButton.setBounds(88, 460, 117, 29);
        ContinueButton.setOpaque(false);
        ContinueButton.setContentAreaFilled(false);
        ContinueButton.setBorderPainted(false);
        contentPane.add(ContinueButton);

        JButton BackButton = new JButton("");
        BackButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Admin_Add_Equipments addEquips = new Admin_Add_Equipments();
                addEquips.setVisible(true);
                dispose();
            }
        });
        BackButton.setBounds(88, 511, 117, 23);
        BackButton.setOpaque(false);
        BackButton.setContentAreaFilled(false);
        BackButton.setBorderPainted(false);
        contentPane.add(BackButton);
        
     // Load data from the "ADDEQUIPS.txt" file when the frame is created
        loadEquipmentsData();

    }

    
   
}
