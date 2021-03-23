import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChatClient {
    public JPanel panelMain;
    private JTextArea textArea2;
    private JButton sendMsgBtn;
    private JButton regBtn;
    private JTextField textField1;
    private JTextArea textArea1;
    private Client client = new Client(textArea1);
    private String userName;


    public ChatClient() {
        sendMsgBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //textArea1.append(textArea2.getText()+"\n");
                client.start();

            }
        });

        regBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                userName = textField1.getText();
                textField1.setVisible(false);
                regBtn.setVisible(false);
                //JScrollPane scrollPane = new JScrollPane(textArea1);

            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Chat");
        frame.setContentPane(new ChatClient().panelMain);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);


        //JOptionPane.showMessageDialog(null, args[0]);
    }
}
