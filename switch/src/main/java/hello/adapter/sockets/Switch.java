package hello.adapter.sockets;

import hello.adapter.uap.messages.UssdBind;
import hello.adapter.uap.messages.UssdBindResp;
import hello.adapter.uap.requests.UssdBindReq;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RestController;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;

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
                s.setKeepAlive(true);;
                DataOutputStream dout = new DataOutputStream(s.getOutputStream());
                DataInputStream din = new DataInputStream(s.getInputStream());
                String requeststr = "", str2 = "xxx", accountName = "";;
                int bind = 0;
                while(true){
                    //send bind only once
                    if(bind ==0){
                        //bind
                        ussdBindReq.performUssdBind(dout,din);
                        bind = 1;
                    }
                    else{
                        //process all other requests including shake

                    }

                    //one handler to process all input streams and write back
                    //bind =1;
                }
                //dout.close();
                //s.close();
            }
            catch(SocketException e){
                System.out.println(e);
                //e.printStackTrace();
            }
            catch (IOException e) {
                System.out.println(e);
            }



    }
}


