package hello.adapter.sockets;

import hello.adapter.uap.messages.UssdBind;
import hello.adapter.uap.messages.UssdBindResp;
import hello.adapter.uap.messages.UssdShake;
import hello.adapter.uap.messages.UssdShakeResp;
import hello.adapter.uap.requests.Binder;
import hello.adapter.uap.requests.ShakeReq;
import hello.adapter.uap.requests.Shaker;
import hello.adapter.uap.requests.UssdBindReq;
import org.apache.tomcat.jni.Thread;
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
import java.net.Socket;
import java.net.SocketException;
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
        UssdBindResp ussdBindResp;
        public void connectToServer() {
            try {
                Socket s = new Socket(ussdcip,Integer.parseInt(ussdcport));
                DataOutputStream dout = new DataOutputStream(s.getOutputStream());
                //DataOutputStream doutt = new DataOutputStream(s.getOutputStream());

                DataInputStream din = new DataInputStream(s.getInputStream());
                //DataInputStream dinn = new DataInputStream(s.getInputStream());


                String requeststr = "", str2 = "xxx", accountName = "";;
                //ShakeReq shakeReq = new ShakeReq(s,ussdcip,Integer.parseInt(ussdcport));
                int bind = 0;
                long previousTime = new Date().getTime();
                int i = 0;
                byte [] b = new byte[30];
                //ussdBindReq.performUssdBind(dout,din);
                byte [] bindresp = new byte[26];
                byte [] ussdbindreq = ussdBindReq.perfomUssdBindTest();
                dout.write(ussdbindreq);
                dout.flush();
                int x = din.read(bindresp);
                logger.info("x value:"+x);
                ussdBindReq.decodeUssdBindResp(bindresp);
                /*Binder binder = new Binder(s,testusername,password);
                binder.start();*/
                Shaker  shakethread = new Shaker(s,din,dout);
                shakethread.start();
                byte [] resp =  new byte[256];
                while(x>0){
                    logger.info("response length:"+resp.length);
                    x = din.read(resp);
                    //call thread to process resp
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
            }



    }


}
