import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.Socket;

public class Client {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Client");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            JPanel panel = new JPanel();
            JTextField textField = new JTextField(20);
            JButton sendButton = new JButton("Send");
            JLabel resultLabel = new JLabel("Result: ");

            sendButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String inputText = textField.getText();
                    try {
                        String result = sendRequestToServer(inputText);
                        resultLabel.setText("Result: " + result);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                        resultLabel.setText("Error communicating with the server.");
                    }
                }
            });

            panel.add(textField);
            panel.add(sendButton);
            panel.add(resultLabel);

            frame.getContentPane().add(panel);
            frame.setSize(300, 150);
            frame.setVisible(true);
        });
    }

    private static String sendRequestToServer(String inputText) throws IOException {
        try (Socket socket = new Socket("localhost", 4000);
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

            out.println(inputText);
            return in.readLine();
        }
    }
}

