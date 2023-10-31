package dataStructureAlgo;

import java.awt.EventQueue;
import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.CompoundBorder;
import javax.swing.table.DefaultTableModel;

public class Admin_Add_Equipments extends JFrame {

    private JPanel contentPane;
    private JTextField EquipType;
    private JTextField QuantityType;
    private JTable table;
    private DefaultTableModel model;
    private EquipmentLinkedList equipmentList;


    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Admin_Add_Equipments frame = new Admin_Add_Equipments();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void loadDataFromFile() {
        model.setRowCount(0); // Clear the existing data in the table model
        equipmentList = new EquipmentLinkedList(); // Clear the existing linked list

        try (BufferedReader br = new BufferedReader(new FileReader("ADDEQUIPS.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    String equipmentName = parts[0];
                    String equipmentQuantity = parts[1];
                    equipmentList.addEquipment(equipmentName, equipmentQuantity);
                    model.addRow(new Object[]{equipmentName, equipmentQuantity});
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void writeToFile(String equipType, String quantityType) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("ADDEQUIPS.txt", true))) {
            writer.write(equipType + "," + quantityType);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private void saveDataToTextFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("ADDEQUIPS.txt"))) {
            EquipmentNode current = equipmentList.head;
            while (current != null) {
                writer.write(current.data.name + "," + current.data.quantity);
                writer.newLine();
                current = current.next;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    /**
     * Create the frame.
     */
    public Admin_Add_Equipments() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1074, 650);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        EquipType = new JTextField();
        EquipType.setForeground(new Color(255, 255, 255));
        EquipType.setBounds(237, 214, 221, 29);
        EquipType.setFont(new Font("Times New Roman", Font.BOLD, 16));
        EquipType.setBackground(null);
        EquipType.setBorder(null);
        EquipType.setOpaque(false);
        contentPane.add(EquipType);
        EquipType.setColumns(10);

        QuantityType = new JTextField();
        QuantityType.setForeground(new Color(255, 255, 255));
        QuantityType.setBounds(621, 214, 58, 29);
        QuantityType.setFont(new Font("Times New Roman", Font.BOLD, 16));
        QuantityType.setBackground(null);
        QuantityType.setBorder(null);
        QuantityType.setOpaque(false);
        contentPane.add(QuantityType);
        QuantityType.setColumns(10);

        model = new DefaultTableModel(
            new Object[][] {},
            new String[] { "Equipment", "Quantity" }
        );

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(249, 269, 450, 314);
        contentPane.add(scrollPane);

        table = new JTable(model);
        table.setBackground(new Color(175, 175, 254));
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int i = table.getSelectedRow();
                EquipType.setText(model.getValueAt(i, 0).toString());
                QuantityType.setText(model.getValueAt(i, 1).toString());
            }
        });
        table.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 18));
        table.setBorder(new CompoundBorder());
        scrollPane.setViewportView(table);
        
        equipmentList = new EquipmentLinkedList();
        loadDataFromFile();

        JLabel lblNewLabel = new JLabel("");
        lblNewLabel.setIcon(new ImageIcon("C:\\Users\\VJRM14\\Downloads\\6 (1).png"));
        lblNewLabel.setBounds(-13, 0, 1113, 600);
        contentPane.add(lblNewLabel);

        JButton btnNewButton = new JButton("");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Admin_Available_Equipments availableEquips = new Admin_Available_Equipments();
                availableEquips.setVisible(true);
                dispose();
            }
        });
        btnNewButton.setBounds(869, 542, 99, 23);
        btnNewButton.setOpaque(false);
        btnNewButton.setContentAreaFilled(false);
        btnNewButton.setBorderPainted(false);
        contentPane.add(btnNewButton);

        JButton AddButton = new JButton("");
        AddButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String equipType = EquipType.getText();
                String quantityType = QuantityType.getText();

                if (!equipType.isEmpty() && !quantityType.isEmpty()) {
                    boolean equipmentExists = false;

                    // Check if the equipment already exists in the equipmentList
                    EquipmentNode current = equipmentList.head;
                    while (current != null) {
                        if (current.data.name.equals(equipType)) {
                            equipmentExists = true;
                            break;
                        }
                        current = current.next;
                    }

                    if (!equipmentExists) {
                        // Equipment doesn't exist, so add it to the equipmentList, table model, and text file
                        equipmentList.addEquipment(equipType, quantityType);
                        model.addRow(new Object[]{equipType, quantityType});
                        writeToFile(equipType, quantityType);
                        JOptionPane.showMessageDialog(contentPane, "Add successful");
                    } else {
                        JOptionPane.showMessageDialog(contentPane, "Equipment already exists.");
                    }
                } else {
                    JOptionPane.showMessageDialog(contentPane, "Please complete fill up");
                }
            }
        });

        AddButton.setBounds(869, 203, 99, 23);
        AddButton.setOpaque(false);
        AddButton.setContentAreaFilled(false);
        AddButton.setBorderPainted(false);
        contentPane.add(AddButton);

        JButton EditButton = new JButton("");
        EditButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow >= 0) {
                    String equipType = EquipType.getText();
                    String quantityType = QuantityType.getText();

                    if (!equipType.isEmpty() && !quantityType.isEmpty()) {
                        model.setValueAt(equipType, selectedRow, 0);
                        model.setValueAt(quantityType, selectedRow, 1);
                        // Update the corresponding data in the equipmentList
                        EquipmentNode current = equipmentList.head;
                        for (int i = 0; i < selectedRow; i++) {
                            current = current.next;
                        }
                        current.data.name = equipType;
                        current.data.quantity = quantityType;

                        // Save the updated equipment data to the text file
                        saveDataToTextFile();

                        JOptionPane.showMessageDialog(null, "Update Successful!");
                    } else {
                        JOptionPane.showMessageDialog(null, "Please complete fill up");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Please select a row first.");
                }
            }
        });

        EditButton.setBounds(869, 247, 99, 23);
        EditButton.setOpaque(false);
        EditButton.setContentAreaFilled(false);
        EditButton.setBorderPainted(false);
        contentPane.add(EditButton);
    }
}
