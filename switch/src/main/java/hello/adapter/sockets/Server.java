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
import java.util.Timer;
import java.util.TimerTask;

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
            String str = "",str1 = "", str2 = "";
            byte[] bytess = new byte[57];
            int i = 0;
            din.read(bytess);
            str = new String(bytess);
            logger.info("client says: "+str);
            Timer timer = new Timer();
            while(true){
                str2 = "USSDBEGINsessionsucwakala12345";
                DataOutputStream dout = new DataOutputStream(s.getOutputStream());
               /* TimerTask timerTask = new TimerTask(){
                    public void run(){
                        try{
                            dout.write("USSDBEGINsessionsucwakala12345".getBytes(),0,30);
                            logger.info("timing....");
                        }
                        catch (IOException e){

                        }
                    }
                };*/
                //dout.write(str2.getBytes());
                //dout.write(str2.getBytes(),0,str2.length());
                //dout.writeUTF(str2);

                /*timer.scheduleAtFixedRate(new TimerTask() {
                    @Override
                    public void run() {
                        try{
                            dout.write("USSDBEGINsessionsucwakala12345".getBytes(),0,30);
                            logger.info("timing....");
                        }
                        catch (IOException e){

                        }
                    }
                },10000,10000);*/
                if(send()){
                   dout.write(str2.getBytes(),0,str2.length());
                   dout.flush();
                }
                //dout.flush();
                i++;
            }
            //din.close();
            //s.close();
            //ss.close();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    public boolean send(){
        double i = (Math.random()*10);
        double w = (Math.random()*10);
        double x = (Math.random()*10);
        double y = (Math.random()*10);
        double z = (Math.random()*10);

        if(i>=9.9 && w >=9.9 && x >= 9.9 &&y>=9.9){
            logger.info("I value::"+i);
            logger.info("X value::"+x);
            logger.info("Y value::"+y);
            return true;
        }
        else{
            return false;
        }
    }
}
