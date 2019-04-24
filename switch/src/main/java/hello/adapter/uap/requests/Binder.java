package hello.adapter.uap.requests;

import hello.adapter.sockets.Switch;
import hello.adapter.uap.messages.UssdBind;
import hello.adapter.uap.messages.UssdBindResp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Timer;
import java.util.TimerTask;

public class Binder extends Thread {
    public Socket s;
    private String username;
    private String password;
    private Logger logger = LoggerFactory.getLogger(Shaker.class);
    public  Binder(Socket s,String username,String password){
        this.s = s;
        this.username = username;
        this.password = password;
    }

    public void run() {
        UssdBind ussdBind = new UssdBind(username,password);
        byte [] ussdbindreq = ussdBind.encode();
        byte [] resp = new byte[26];
            Timer timer = new Timer();
            timer.scheduleAtFixedRate(new TimerTask() {
                @Override
                public void run() {
                    try {
                        s = new Socket("172.29.127.177", 9090);
                        DataOutputStream dataOutputStream = new DataOutputStream(s.getOutputStream());
                        DataInputStream dataInputStream = new DataInputStream(s.getInputStream());
                        dataOutputStream.write(ussdbindreq);
                        dataOutputStream.flush();
                        dataInputStream.readFully(resp);
                        logger.info("RAW STRING RESPONSE:::" + new String(resp));
                        UssdBindResp ussdBindResp = new UssdBindResp(resp);
                        ;
                        logger.info("account name" + ussdBindResp.getAccountName());
                        ;
                        logger.info("command status" + ussdBindResp.getCommandStatus());
                        dataOutputStream.close();
                    }
                    catch (IOException e) {

                    }

                }
            },0,2000);

        }

}
