import javax.swing.*;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;

public class Client extends Thread {
    private JTextArea textArea;
    private String msg="";
    private InetSocketAddress socketAddress = new InetSocketAddress("192.168.0.108", 53535);
    private SocketChannel socketChannel = SocketChannel.open();

    public void setMsg(String msg) throws IOException {
        this.msg = msg;
        socketChannel.write(ByteBuffer.wrap(msg.getBytes(StandardCharsets.UTF_8)));
        //Thread.sleep(2000);
        this.msg="";

    }

    public Client(JTextArea textArea) throws IOException {
        this.textArea = textArea;
    }


    @Override
    public void run() {
        try {
            socketChannel.connect(socketAddress);
            final ByteBuffer inputBuffer = ByteBuffer.allocate(2048);


            while (true) {
                if (msg.equals("end")) break;//
                int bytesCount = socketChannel.read(inputBuffer);
                textArea.append(new String(inputBuffer.array(), 0, bytesCount, StandardCharsets.UTF_8));
                textArea.append("\n");
                inputBuffer.clear();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}