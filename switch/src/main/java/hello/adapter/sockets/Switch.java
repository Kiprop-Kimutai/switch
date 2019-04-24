package hello.adapter.sockets;

import hello.adapter.uap.messages.*;
import hello.adapter.uap.requests.*;
import hello.adapter.uap.responses.UssdBindResponse;
import hello.adapter.uap.responses.UssdShakeResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

@RestController
public class Switch {


        @Value("${ussdcip}")
        private String ussdcip;
        @Value("${ussdcport}")
        private String ussdcport;
        @Value("${AccountName}")
        private String testusername;
        @Value("${Password}")
        private String password;
        private Logger logger = LoggerFactory.getLogger(Switch.class);
        @Autowired
        UssdBindReq ussdBindReq;
        @Autowired
        Shaker shakethread;
        @Autowired
        BindThread bindThread;
        UssdBindResp ussdBindResp;
        private DataOutputStream dout;
        private DataInputStream din;
        public Socket s;
        private static SocketChannel client;
        private static ByteBuffer buffer;
        byte [] resp = new byte [4096];
        DecodeMessage decodeMessage;
        public Switch(){

        }
        public void connectToServer() {
            try {
                s = new Socket(ussdcip, Integer.parseInt(ussdcport));
                s.setKeepAlive(true);
                s.setSoTimeout(0);
                s.setSendBufferSize(1000);
                dout = new DataOutputStream(s.getOutputStream());
                din = new DataInputStream(s.getInputStream());
                //DataInputStream dinn = new DataInputStream(s.getInputStream());
             /*   String requeststr = "", str2 = "xxx", accountName = "";;
                //ShakeReq shakeReq = new ShakeReq(s,ussdcip,Integer.parseInt(ussdcport));
                byte [] bindresp = new byte[26];
                byte [] ussdbindreq = ussdBindReq.perfomUssdBindTest();
                dout.write(ussdbindreq);
                dout.flush();
                int x = din.read(bindresp);
                logger.info("x value:"+x);
                ussdBindReq.decodeUssdBindResp(bindresp);
                Shaker  shakethread = new Shaker(s,din,dout);
                shakethread.start();*/
                //writeMessage(ussdBindReq.perfomUssdBindTest());

                //Shaker shakethread = new Shaker();
                //byte[] bindresp = new byte[36];
                //writeMessage(ussdBindReq.perfomUssdBindTest());
                //din.read(bindresp);
                //decodeMessage(bindresp);
                //decodeMessage = new DecodeMessage(bindresp);
                //decodeMessage.start();
                int x = 0;
                byte[] resp = new byte[256];
                writeMessage(ussdBindReq.perfomUssdBindTest());
                x = din.read(resp);
                decodeMessage(resp);
                resp = new byte[256];
                //bindThread.start();
                //shakethread.start();
                while(true) {
                    while (x>=0) {
                        x = din.read(resp);

                        if(x>=0) {
                            decodeMessage(resp);
                            logger.info("response length:" + x);
                            logger.info("resp" + din.available());
                        }
                        resp = new byte[256];
                        if (x == -1) {
                            x= 0;
                        }
                        //call thread to process resp
                    }
                }
                //dout.close();
                //s.close();
            }
            catch(SocketException e){
                logger.info("------");
                logger.info(e.getMessage());
                System.out.println(e);

                //e.printStackTrace();
            }
            catch (IOException e) {
                logger.info(e.getMessage());
                System.out.println(e);
                logger.info("---x-----");
            }



    }

    public void writeMessage(byte[] message)  {

        try {
            dout.write(message);
            logger.info("------flushing-----");
            dout.flush();

            logger.info("dout before size" + dout.size());
            dout = new DataOutputStream(s.getOutputStream());
            logger.info("dout after size" + dout.size());
        } catch (IOException e) {
            logger.info("------xxx----");
            logger.info(e.getMessage());
            try {
                s = new Socket("172.29.127.177", 9090);
                dout = new DataOutputStream(s.getOutputStream());
                din = new DataInputStream(s.getInputStream());
            } catch (IOException exception) {
                logger.info(e.getMessage());
            }
        }

    }

    public void decodeMessage(byte [] message) {
        logger.info("decoding.....");
        //logger.info("raw string==>"+new String(message));
        DecodeMessage decodeMessage = new DecodeMessage(message);
        decodeMessage.start();
    }

}

class WriteMessage extends Thread {
    private Logger logger = LoggerFactory.getLogger(WriteMessage.class);
     byte[] message;
     DataOutputStream dout;
     DataInputStream din;
    Socket s;
    public WriteMessage(byte [] message,Socket s,DataOutputStream dout,DataInputStream din) {
        this.message = message;
        this.dout = dout;
        this.s = s;
        this.din = din;
    }
    public void run() {
        try{
            dout.write(message);
            logger.info("------flushing-----");
            dout.flush();

            logger.info("dout before size"+dout.size());
            dout = new DataOutputStream(s.getOutputStream());
            logger.info("dout after size"+dout.size());
        }
        catch (IOException e){
            logger.info("------xxx----");
            logger.info(e.getMessage());
            try {
                s = new Socket("172.29.127.177", 9090);
                dout = new DataOutputStream(s.getOutputStream());
                din = new DataInputStream(s.getInputStream());;
            }
            catch (IOException exception){
                logger.info(e.getMessage());
            }
        }
    }
}
class DecodeMessage extends Thread {
    private Logger logger = LoggerFactory.getLogger(DecodeMessage.class);
    private byte[] message;
    public  DecodeMessage(byte [] message){
        this.message = message;
    }

    public void run(){
        logger.info("raw string==>"+new String(message));
        //UssdBindResp ussdBindResp = new UssdBindResp(message);
        GenericUssdResp genericUssdResp = new GenericUssdResp(message);
        //logger.info("account name"+ussdBindResp.getAccountName());
        //logger.info("command status"+ussdBindResp.getCommandStatus());
        logger.info("COMMAND ID"+genericUssdResp.getCommandID());
        CommandIDs commandIDs = genericUssdResp.getCommandID();
        switch (commandIDs){
            case UssdBindResp:
                UssdBindResponse ussdBindResponse = new UssdBindResponse(message);
                ussdBindResponse.start();
                break;
            case UssdShakeResp:
                UssdShakeResponse ussdShakeResponse = new UssdShakeResponse(message);
                ussdShakeResponse.start();
                break;
            default:
                break;
        }
    }
}


