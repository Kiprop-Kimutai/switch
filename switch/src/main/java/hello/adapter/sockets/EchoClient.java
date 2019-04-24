package hello.adapter.sockets;

import hello.adapter.uap.messages.UssdBind;
import hello.adapter.uap.requests.UssdBindReq;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

@RestController
public class EchoClient {
    private static SocketChannel client;
    private static ByteBuffer buffer;
    private static EchoClient instance;
    private Logger logger = LoggerFactory.getLogger(EchoClient.class);
    public static EchoClient start() {
        if (instance == null)
            instance = new EchoClient();

        return instance;
    }

    public static void stop() throws IOException {
        client.close();
        buffer = null;
    }

    private EchoClient() {
        try {
            logger.info("echo client init...");
            client = SocketChannel.open(new InetSocketAddress("172.29.127.177", 9090));
            client.configureBlocking(false);
            buffer = ByteBuffer.allocate(256);
            //sendMessage(ussdBindReq.perfomUssdBindTest());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String sendMessage(byte [] msg) {
        buffer = ByteBuffer.wrap(msg);
        String response = null;
        try {
            client.write(buffer);
            buffer.clear();
            client.read(buffer);
            response = new String(buffer.array()).trim();
            System.out.println("response=" + response);
            buffer.clear();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;

    }
}
