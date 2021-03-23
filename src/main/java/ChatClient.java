import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;

public class ChatClient {
    private static JFrame frame;
    public JPanel panelMain;
    private JTextArea textArea2;
    private JButton sendMsgBtn;
    private JButton regBtn;
    private JTextField textField1;
    private JTextArea textArea1;
    private JLabel label1;
    private Client client;


    private String userName;


    public ChatClient() {
        sendMsgBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (client != null) {
                    if (!textArea2.getText().trim().isEmpty()) {
                        if (client.sendMsg(textArea2.getText() + "\n")) {
                            label1.setText("Сообщение отправлено");
                            textArea2.setText("");
                        } else {
                            label1.setText("Не удалось отправить сообщение");
                        }
                    } else {
                        label1.setText("Нельзя отпраить пустое сообщение");
                        textArea2.setText("");
                    }
                } else JOptionPane.showMessageDialog(frame, "Клиент не подключен к серверу.");
            }
        });

        regBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (!textField1.getText().trim().isEmpty()) {
                    userName = textField1.getText();
                    textField1.setVisible(false);
                    regBtn.setVisible(false);
                    client = new Client(textArea1, userName);
                    client.start();
                } else JOptionPane.showMessageDialog(frame, "Не введен никнейм для чата.");
            }
        });

        frame.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {

            }

            @Override
            public void windowClosing(WindowEvent e) {
                if (client != null) {
                    client.sendMsg("*END*");
                }
            }

            @Override
            public void windowClosed(WindowEvent e) {

            }

            @Override
            public void windowIconified(WindowEvent e) {

            }

            @Override
            public void windowDeiconified(WindowEvent e) {

            }

            @Override
            public void windowActivated(WindowEvent e) {

            }

            @Override
            public void windowDeactivated(WindowEvent e) {

            }
        });
    }

    public static void main(String[] args) throws IOException {
        frame = new JFrame("NIOChat");
        frame.setContentPane(new ChatClient().panelMain);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public static void close() {
    }
}
