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
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.Font;
import javax.swing.table.DefaultTableModel;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Admin_Records extends JFrame {

    private JPanel contentPane;
    private JTable table;
    private DefaultTableModel model;
    private RentalHistory rentalHistory;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Admin_Records frame = new Admin_Records();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public Admin_Records() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1072, 650);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(104, 210, 738, 326);
        contentPane.add(scrollPane);

        table = new JTable();
        model = new DefaultTableModel(
            new Object[][] {},
            new String[] {
                "NAME", "ADDRESS", "CONTACT NO.", "EQUIPMENT/VENUE", "QUANTITY", "MONTH", "DAY", "YEAR", "TIME", "RETURN TIME"
            }
        );
        table.setModel(model);
        table.setFont(new Font("Times New Roman", Font.PLAIN, 18));
        scrollPane.setViewportView(table);

        rentalHistory = new RentalHistory(); // Initialize the rental history

        loadRecordsFromFile("ADMINRECORDS.txt"); // Load data from the text file

        JLabel lblNewLabel = new JLabel("");
        lblNewLabel.setIcon(new ImageIcon("C:\\Users\\VJRM14\\Downloads\\8 (1).png"));
        lblNewLabel.setBounds(-13, 0, 1097, 600);
        contentPane.add(lblNewLabel);

        JButton btnNewButton = new JButton("");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {
                    model.removeRow(selectedRow);
                    updateRentFile();
                    // Also remove the corresponding record from the rental history
                    rentalHistory.removeRentalRecord(selectedRow);
                    JOptionPane.showMessageDialog(contentPane, "Remove Successfully");
                } else {
                    JOptionPane.showMessageDialog(contentPane, "Please choose a row");
                }
            }
        });
        btnNewButton.setBounds(852, 523, 115, 30);
        btnNewButton.setOpaque(false);
        btnNewButton.setContentAreaFilled(false);
        btnNewButton.setBorderPainted(false);
        contentPane.add(btnNewButton);
    }

    private void loadRecordsFromFile(String fileName) {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split("\t"); // Assuming tab-separated values
                model.addRow(data);
                
                // Create a new RentalRecord and add it to the rental history
                RentalRecord record = new RentalRecord(data[0], data[1], data[2], data[3], Integer.parseInt(data[4]),
                        data[5], Integer.parseInt(data[6]), Integer.parseInt(data[7]), data[8], data[9]);
                rentalHistory.addRentalRecord(record);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void updateRentFile() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("ADMINRECORDS.txt"))) {
            for (int row = 0; row < model.getRowCount(); row++) {
                for (int col = 0; col < model.getColumnCount(); col++) {
                    bw.write(model.getValueAt(row, col).toString());
                    if (col < model.getColumnCount() - 1) {
                        bw.write("\t"); // Use tab as delimiter
                    }
                }
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class RentalRecord {
    private String name;
    private String address;
    private String contactNo;
    private String equipmentVenue;
    private int quantity;
    private String month;
    private int day;
    private int year;
    private String time;
    private String returnTime;
     RentalRecord previous;
     RentalRecord next;
    

    public RentalRecord(String name, String address, String contactNo, String equipmentVenue, int quantity, String month,
            int day, int year, String time, String returnTime) {
        this.name = name;
        this.address = address;
        this.contactNo = contactNo;
        this.equipmentVenue = equipmentVenue;
        this.quantity = quantity;
        this.month = month;
        this.day = day;
        this.year = year;
        this.time = time;
        this.returnTime = returnTime;
        this.previous = null;
        this.next = null;
    }
    

    // Getters
    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getContactNo() {
        return contactNo;
    }

    public String getEquipmentVenue() {
        return equipmentVenue;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getMonth() {
        return month;
    }

    public int getDay() {
        return day;
    }

    public int getYear() {
        return year;
    }

    public String getTime() {
        return time;
    }

    public String getReturnTime() {
        return returnTime;
    }

    // Setters
    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public void setEquipmentVenue(String equipmentVenue) {
        this.equipmentVenue = equipmentVenue;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public RentalRecord getPrevious() {
        return previous;
    }

    public void setPrevious(RentalRecord previous) {
        this.previous = previous;
    }

    public RentalRecord getNext() {
        return next;
    }

    public void setNext(RentalRecord next) {
        this.next = next;
    }
}


class RentalHistory {
    private RentalRecord head;
    private RentalRecord tail;

    public RentalHistory() {
        head = null;
        tail = null;
    }

    public void addRentalRecord(RentalRecord record) {
        if (head == null) {
            head = record;
            tail = record;
        } else {
            tail.next = record;
            record.previous = tail;
            tail = record;
        }
    }

    public void removeRentalRecord(int row) {
        RentalRecord current = head;
        int index = 0;
        while (current != null) {
            if (index == row) {
                if (current == head) {
                    head = current.next;
                    if (head != null) {
                        head.previous = null;
                    }
                } else if (current == tail) {
                    tail = current.previous;
                    tail.next = null;
                } else {
                    current.previous.next = current.next;
                    current.next.previous = current.previous;
                }
                break;
            }
            current = current.next;
            index++;
        }
    }
}
