import javax.swing.*;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;

public class Client extends Thread {
    private JTextArea textArea;
    private String userName;

    private InetSocketAddress socketAddress = new InetSocketAddress("192.168.0.108", 53535);
    private SocketChannel socketChannel;

    {
        try {
            socketChannel = SocketChannel.open();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean sendMsg(String msg)  {
        try {
            socketChannel.write(ByteBuffer.wrap(msg.getBytes(StandardCharsets.UTF_8)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    public Client(JTextArea textArea, String userName) {
        this.textArea = textArea;
        this.userName = userName;
        try {
            socketChannel.connect(socketAddress);
            sendMsg(userName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            final ByteBuffer inputBuffer = ByteBuffer.allocate(2048);
            while (!isInterrupted()) {
                int bytesCount = socketChannel.read(inputBuffer);
                String answer = new String(inputBuffer.array(), 0, bytesCount, StandardCharsets.UTF_8);
                if (answer.equals("*CONF_END*")) {
                    sleep(1000);
                    break;
                }
                textArea.append(answer);
                inputBuffer.clear();
            }
            socketChannel.close();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}