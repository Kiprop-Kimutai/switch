package hello.adapter.sockets;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.Properties;
//@PropertySource({"file:D:\\repo\\gs-rest-service\\complete\\src\\main\\resources\\application.properties"})
@RestController
public class Server {

    @Value("${ussdcip}")
    private String ussdcip;
    @Value("${ussdcport}")
    private String ussdcport;
    private Logger logger = LoggerFactory.getLogger(Server.class);

    public  void openSocketConnection() {
        try{
            ServerSocket ss = new ServerSocket(Integer.parseInt(ussdcport));
            Socket s = ss.accept();
            DataInputStream din = new DataInputStream(s.getInputStream());
            DataOutputStream dout = new DataOutputStream(s.getOutputStream());
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String str = "",str1 = "", str2 = " USSDBEGINsessionsucwakala";
            byte[] bytess = new byte[57];
            while(!str1.equals("stop")){
                din.read(bytess);
                str = new String(bytess);
                logger.info("client says: "+str);
                str1 = br.readLine();
                dout.write(str2.getBytes());
                dout.flush();
            }
            din.close();
            s.close();
            ss.close();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
}
