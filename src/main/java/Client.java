import javax.swing.*;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;

public class Client extends Thread {
    private JTextArea textArea;

    public Client(JTextArea textArea) {
        this.textArea = textArea;
    }

    @Override
    public void run() {
        InetSocketAddress socketAddress = new InetSocketAddress("192.168.0.108", 53535);
        try (SocketChannel socketChannel = SocketChannel.open()) {
            socketChannel.connect(socketAddress);
            final ByteBuffer inputBuffer = ByteBuffer.allocate(2048);
            String msg="123";

            while(true){
                if (msg.equals("end")) break;
                socketChannel.write(ByteBuffer.wrap(msg.getBytes(StandardCharsets.UTF_8)));
                Thread.sleep(2000);

                int bytesCount = socketChannel.read(inputBuffer);
                textArea.append(new String(inputBuffer.array(),0,bytesCount,StandardCharsets.UTF_8));
                textArea.append("\n");
                inputBuffer.clear();
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}