package hello.adapter.uap.requests;

import hello.adapter.sockets.Switch;
import hello.adapter.uap.messages.UssdShake;
import hello.adapter.uap.messages.UssdShakeResp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Timer;
import java.util.TimerTask;

@Controller
public class Shaker extends Thread {
    private Logger logger = LoggerFactory.getLogger(Shaker.class);
    UssdShake ussdShake = new UssdShake();
    Socket s;
    DataInputStream inputStream;
    DataOutputStream dataOutputStream;
    @Autowired
    private Switch switchboard;
/*    public Shaker(Socket s, DataInputStream inputStream, DataOutputStream dataOutputStream) {
        this.s = s;
        this.dataOutputStream = dataOutputStream;
        this.inputStream = inputStream;
    }*/
    /*public void run(){
        //while(keepRunning()) {
        try {
            logger.info("------shaking-----");
            byte [] ussdshakereq = ussdShake.encode();
            byte [] shakeresp = new byte[20];
            Timer timer = new Timer();
            timer.scheduleAtFixedRate(new TimerTask() {
                @Override
                public void run() {
                    try{
                           s = new Socket("172.29.127.177",9090);
                           dataOutputStream = new DataOutputStream(s.getOutputStream());
                           inputStream = new DataInputStream(s.getInputStream());
                            logger.info("---------");
                            logger.info("data outputstream size::"+dataOutputStream.size());
                            logger.info("data outputstream size::"+dataOutputStream.size());
                            dataOutputStream.write(ussdshakereq,0,20);
                            logger.info("------flushing-----");
                            dataOutputStream.flush();
                            ;
                            inputStream.read(shakeresp, 0, 20);
                            UssdShakeResp ussdshakeResp = new UssdShakeResp(shakeresp);
                            logger.info("-----------USSD SHAKE RESPONSE--------------");
                            logger.info("COMMAND ID:::" + ussdshakeResp.getCommandID());
                            logger.info("COMMAND LENGTH::::" + ussdshakeResp.getCommandLength());
                            logger.info("COMMAND ID::" + ussdshakeResp.getCommandID());
                            logger.info("COMMAND STATUS:+:" + ussdshakeResp.getCommandStatus());
                            logger.info("SENDERCB==>" + ussdshakeResp.getSenderCB());
                            logger.info("RECEIVERCB==>" + ussdshakeResp.getSenderCB());
                            dataOutputStream = new DataOutputStream(s.getOutputStream());
                    }
                    catch(IOException e){
                        System.out.println(e);
                        logger.info("------------y--------y");
                        logger.info("socket is connected ?"+s.isConnected());
                        try{
                            inputStream.available();
                        }
                        catch (IOException ex){
                            ex.getMessage();
                        }
                    }
                }
            },0,2000);

        } catch (Exception e) {
            System.out.print(e);
            logger.info("-----x--xxx----------");
        }
        //}
    }*/

    public void run(){
        //while(true) {
        try {
            logger.info("------shaking-----");
            byte [] ussdshakereq = ussdShake.encode();
            byte [] respmessage = new byte[20];
            Timer timer = new Timer();
            timer.scheduleAtFixedRate(new TimerTask() {
                @Override
                public void run() {
                        switchboard.writeMessage(ussdshakereq);
                }
            },10000,2000);

        } catch (Exception e) {
            System.out.print(e);
            logger.info("-----x--xxx----------");
        }
        //}
    }


}
