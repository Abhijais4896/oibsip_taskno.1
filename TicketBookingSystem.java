import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TicketBookingSystem {
    private JFrame frame;
    private JLabel nameLabel;
    private JTextField nameTextField;
    private JLabel seatLabel;
    private JComboBox<String> seatComboBox;
    private JButton bookButton;
    private JTextArea bookingDetailsTextArea;

    private String[] seatNumbers = { "A1", "A2", "A3", "A4", "A5", "B1", "B2", "B3", "B4", "B5" };
    private boolean[] seatAvailability = new boolean[seatNumbers.length];

    public TicketBookingSystem() {
        // Create the main frame
        frame = new JFrame("Online Ticket Booking");

        // Create the components
        nameLabel = new JLabel("Name:");
        nameTextField = new JTextField(20);
        seatLabel = new JLabel("Select Seat:");
        seatComboBox = new JComboBox<>(seatNumbers);
        bookButton = new JButton("Book Ticket");
        bookingDetailsTextArea = new JTextArea(10, 30);

        // Set up the layout
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 1));
        panel.add(nameLabel);
        panel.add(nameTextField);
        panel.add(seatLabel);
        panel.add(seatComboBox);
        panel.add(bookButton);

        // Add action listener to the book button
        bookButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameTextField.getText();
                String selectedSeat = (String) seatComboBox.getSelectedItem();

                // Check seat availability
                int seatIndex = getSeatIndex(selectedSeat);
                if (seatIndex >= 0 && !seatAvailability[seatIndex]) {
                    // Book the seat
                    seatAvailability[seatIndex] = true;

                    // Display booking details
                    String bookingDetails = "Name: " + name + "\nSeat: " + selectedSeat;
                    bookingDetailsTextArea.setText(bookingDetails);

                    // Show success message
                    JOptionPane.showMessageDialog(frame, "Ticket booked successfully!");
                } else {
                    // Show error message for unavailable seats
                    JOptionPane.showMessageDialog(frame, "Selected seat is not available. Please choose another seat.");
                }
            }
        });

        // Create scroll pane for the booking details text area
        JScrollPane scrollPane = new JScrollPane(bookingDetailsTextArea);

        // Set up the main frame
        frame.getContentPane().add(panel, BorderLayout.NORTH);
        frame.getContentPane().add(scrollPane, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    private int getSeatIndex(String seatNumber) {
        for (int i = 0; i < seatNumbers.length; i++) {
            if (seatNumbers[i].equals(seatNumber)) {
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new TicketBookingSystem();
            }
        });
    }
}
