package dataStructureAlgo;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Project.HomePage;

import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JComboBox;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import java.util.Calendar;


public class User_Rental_Form extends JFrame {

    private JPanel contentPane;
    private JTextField NametxtField;
    private JTextField AddressTxtField;
    private JTextField MobileNoTextField;
    private JComboBox<Equipment> EQUIPMENTS;
    private JComboBox<Integer> QUANTITY;
    private JComboBox Buwan;
    private JComboBox Day;
    private JComboBox Year;
    private JTextField TimetxtField;
    private JTextField ReturnTimetxtField;

    private List<Equipment> equipmentList;
    

    public class Equipment {
        private String name;
        private int quantity;

        public Equipment(String name, int quantity) {
            this.name = name;
            this.quantity = quantity;
        }

        public String getName() {
            return name;
        }

        public int getQuantity() {
            return quantity;
        }

        @Override
        public String toString() {
            return name;
        }
    }

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    User_Rental_Form frame = new User_Rental_Form();
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
    public User_Rental_Form() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1073, 650);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        equipmentList = new ArrayList<>();
        loadEquipmentData();

       

        QUANTITY = new JComboBox<Integer>();
        QUANTITY.setBounds(659, 387, 56, 36);
        contentPane.add(QUANTITY);

        EQUIPMENTS = new JComboBox<Equipment>();
        EQUIPMENTS.setBounds(399, 392, 168, 31);
        for (Equipment equipment : equipmentList) {
            EQUIPMENTS.addItem(equipment);
        }
        EQUIPMENTS.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Equipment selectedEquipment = (Equipment) EQUIPMENTS.getSelectedItem();
                if (selectedEquipment != null) {
                    updateQuantityComboBox(selectedEquipment.getQuantity());
                }
            }
        });
        contentPane.add(EQUIPMENTS);

        Day = new JComboBox<Integer>();
        Day.setBounds(297, 444, 50, 37);
        // Populate the Day JComboBox with day numbers
        for (int day = 1; day <= 31; day++) {
            Day.addItem(day);
        }
        
        Buwan = new JComboBox<String>();
        Buwan.setBounds(188, 444, 99, 37);
        // Populate the Month JComboBox with months
        Buwan.addItem("January");
        Buwan.addItem("February");
        Buwan.addItem("March");
        Buwan.addItem("April");
        Buwan.addItem("May");
        Buwan.addItem("June");
        Buwan.addItem("July");
        Buwan.addItem("August");
        Buwan.addItem("September");
        Buwan.addItem("October");
        Buwan.addItem("November");
        Buwan.addItem("December");
        contentPane.add(Buwan);
        contentPane.add(Day);

        Year = new JComboBox<Integer>();
        Year.setBounds(352, 444, 65, 37);
        // Populate the Year JComboBox with year numbers
        for (int year = 2023; year <= 2040; year++) {
            Year.addItem(year);
        }
        contentPane.add(Year);

        NametxtField = new JTextField();
        NametxtField.setForeground(new Color(255, 255, 255));
        NametxtField.setBackground(null);
        NametxtField.setBorder(null);
        NametxtField.setOpaque(false);
        NametxtField.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        NametxtField.setBounds(297, 213, 309, 42);
        contentPane.add(NametxtField);
        NametxtField.setColumns(10);

        AddressTxtField = new JTextField();
        AddressTxtField.setForeground(new Color(255, 255, 255));
        AddressTxtField.setBackground(null);
        AddressTxtField.setBorder(null);
        AddressTxtField.setOpaque(false);
        AddressTxtField.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        AddressTxtField.setBounds(297, 270, 309, 42);
        contentPane.add(AddressTxtField);
        AddressTxtField.setColumns(10);

        MobileNoTextField = new JTextField();
        MobileNoTextField.setForeground(new Color(255, 255, 255));
        MobileNoTextField.setBackground(null);
        MobileNoTextField.setBorder(null);
        MobileNoTextField.setOpaque(false);
        MobileNoTextField.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        MobileNoTextField.setBounds(297, 331, 309, 42);
        contentPane.add(MobileNoTextField);
        MobileNoTextField.setColumns(10);

        TimetxtField = new JTextField();
        TimetxtField.setForeground(new Color(255, 255, 255));
        TimetxtField.setBackground(null);
        TimetxtField.setBorder(null);
        TimetxtField.setOpaque(false);
        TimetxtField.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        TimetxtField.setBounds(188, 492, 159, 47);
        contentPane.add(TimetxtField);
        TimetxtField.setColumns(10);

        ReturnTimetxtField = new JTextField();
        ReturnTimetxtField.setForeground(new Color(255, 255, 255));
        ReturnTimetxtField.setBackground(null);
        ReturnTimetxtField.setBorder(null);
        ReturnTimetxtField.setOpaque(false);
        ReturnTimetxtField.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        ReturnTimetxtField.setBounds(603, 491, 159, 48);
        contentPane.add(ReturnTimetxtField);
        ReturnTimetxtField.setColumns(10);
        
        JLabel lblNewLabel = new JLabel("");
        lblNewLabel.setIcon(new ImageIcon("C:\\Users\\VJRM14\\Downloads\\9 (1).png"));
        lblNewLabel.setBounds(-11, 0, 1095, 600);
        contentPane.add(lblNewLabel);

        // ... (Rest of the code)

        JButton SubmitBttn = new JButton("");
        SubmitBttn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (isFormComplete()) {
                    if (isValidMobileNo()) {
                        if (isCurrentDateSelected()) {
                            saveFormDataToAdminRecords();

                            Welcome_Page welcome = new Welcome_Page();
                            welcome.setVisible(true);
                            dispose();
                            JOptionPane.showMessageDialog(contentPane, "Rent Successful", "Success", JOptionPane.INFORMATION_MESSAGE);
                        } else {
                            JOptionPane.showMessageDialog(contentPane, "Please select the current date.", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    } else {
                        JOptionPane.showMessageDialog(contentPane, "Mobile number must start with '09' and have 11 digits.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(contentPane, "Please complete the form", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        SubmitBttn.setBounds(837, 547, 151, 31);
        SubmitBttn.setOpaque(false);
        SubmitBttn.setContentAreaFilled(false);
        SubmitBttn.setBorderPainted(false);
        contentPane.add(SubmitBttn);
    }

    private void updateQuantityComboBox(int maxQuantity) {
        QUANTITY.removeAllItems();
        for (int quantity = 1; quantity <= maxQuantity; quantity++) {
            QUANTITY.addItem(quantity);
        }
    }
    private boolean isCurrentDateSelected() {
        Calendar calendar = Calendar.getInstance();
        int currentDay = calendar.get(Calendar.DAY_OF_MONTH);
        int currentMonth = calendar.get(Calendar.MONTH);
        int currentYear = calendar.get(Calendar.YEAR);

        int selectedDay = (int) Day.getSelectedItem();
        int selectedMonth = Buwan.getSelectedIndex(); // Index matches the month (0 - January, 1 - February, etc.)
        int selectedYear = (int) Year.getSelectedItem();

        return currentDay == selectedDay && currentMonth == selectedMonth && currentYear == selectedYear;
    }


    private void loadEquipmentData() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("ADDEQUIPS.txt"));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    String equipmentName = parts[0].trim();
                    int quantity = Integer.parseInt(parts[1].trim());
                    Equipment equipment = new Equipment(equipmentName, quantity);
                    equipmentList.add(equipment);
                }
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean isFormComplete() {
        return !NametxtField.getText().isEmpty() && !AddressTxtField.getText().isEmpty()
                && !MobileNoTextField.getText().isEmpty() && EQUIPMENTS.getSelectedIndex() != -1
                && QUANTITY.getSelectedIndex() != -1 && Buwan.getSelectedIndex() != -1
                && Day.getSelectedIndex() != -1 && Year.getSelectedIndex() != -1
                && !TimetxtField.getText().isEmpty() && !ReturnTimetxtField.getText().isEmpty();
    }

    private boolean isValidMobileNo() {
        String mobileNo = MobileNoTextField.getText();
        return mobileNo.matches("09\\d{9}"); // Starts with "09" and has 11 digits in total
    }

    // Method to save form data to "ADMINRECORDS.txt"
    private void saveFormDataToAdminRecords() {
        String name = NametxtField.getText();
        String address = AddressTxtField.getText();
        String mobileNo = MobileNoTextField.getText();
        Equipment selectedEquipment = (Equipment) EQUIPMENTS.getSelectedItem();
        String equipmentName = selectedEquipment.getName();
        int rentedQuantity = (Integer) QUANTITY.getSelectedItem();
        String month = Buwan.getSelectedItem().toString();
        String day = Day.getSelectedItem().toString();
        String year = Year.getSelectedItem().toString();
        String time = TimetxtField.getText();
        String returnTime = ReturnTimetxtField.getText();

        // Create a formatted data string
        String data = name + "\t" + address + "\t" + mobileNo + "\t" + equipmentName + "\t" + rentedQuantity + "\t"
                + month + "\t" + day + "\t" + year + "\t" + time + "\t" + returnTime;

        // Update the quantity in the text file
        updateEquipmentQuantity(equipmentName, rentedQuantity);

        // Now, you can save the data to your ADMINRECORDS.txt file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("ADMINRECORDS.txt", true))) {
            writer.write(data);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void updateEquipmentQuantity(String equipmentName, int rentedQuantity) {
        try {
            // Read the equipment data from the file
            BufferedReader reader = new BufferedReader(new FileReader("ADDEQUIPS.txt"));
            List<String> lines = new ArrayList<>();

            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    String currentEquipmentName = parts[0].trim();
                    int currentQuantity = Integer.parseInt(parts[1].trim());

                    // If the equipment names match, update the quantity
                    if (currentEquipmentName.equals(equipmentName)) {
                        currentQuantity -= rentedQuantity;
                    }

                    // Create the updated line and add it to the list
                    String updatedLine = currentEquipmentName + "," + currentQuantity;
                    lines.add(updatedLine);
                } else {
                    // Invalid line in the file, just add it to the list as it is
                    lines.add(line);
                }
            }
            reader.close();

            // Write the updated data back to the file
            BufferedWriter writer = new BufferedWriter(new FileWriter("ADDEQUIPS.txt"));
            for (String updatedLine : lines) {
                writer.write(updatedLine);
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
