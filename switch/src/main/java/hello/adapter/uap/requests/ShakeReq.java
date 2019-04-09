package hello.adapter.uap.requests;

import hello.adapter.uap.messages.UssdBindResp;
import hello.adapter.uap.messages.UssdShake;
import hello.adapter.uap.messages.UssdShakeResp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;

import javax.xml.crypto.Data;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Date;
import java.util.TimerTask;

public class ShakeReq {
    private Logger logger = LoggerFactory.getLogger(ShakeReq.class);
    Socket s;
    String ussdcip;
    int ussdcport;
     public ShakeReq(Socket s, String ussdcip,int ussdcport){
        this.s = s;
        this.ussdcip = ussdcip;
        this.ussdcport = ussdcport;
     }
    public void performUssdShake() {
        logger.info("perfoming ussd shake");

        UssdShake ussdShake = new UssdShake();
        byte [] ussdshakereq = ussdShake.encode();
        logger.info("encoded successfully");
        byte[] ussdshakeresp = new byte[20];
        try{
            Socket ss = new Socket(ussdcip,ussdcport);
            DataOutputStream dataOutputStream = new DataOutputStream(ss.getOutputStream());
            DataInputStream inputStream = new DataInputStream(ss.getInputStream());
            dataOutputStream.write(ussdshakereq);
            dataOutputStream.flush();;
            inputStream.read(ussdshakeresp);
            UssdShakeResp ussdshakeResp = new UssdShakeResp(ussdshakeresp);
            logger.info("-----------USSD SHAKE RESPONSE--------------");
            logger.info("COMMAND ID:::"+ussdshakeResp.getCommandID());
            logger.info("COMMAND LENGTH:::"+ussdshakeResp.getCommandLength());
            logger.info("COMMAND ID::"+ussdshakeResp.getCommandID());
            logger.info("COMMAND STATUS::"+ussdshakeResp.getCommandStatus());
            logger.info("SENDERCB==>"+ussdshakeResp.getSenderCB());
            logger.info("RECEIVERCB==>"+ussdshakeResp.getSenderCB());
            inputStream.close();
            dataOutputStream.close();
        }
        catch (IOException e){
            System.out.println(e);
            e.printStackTrace();
        }
    }


}
