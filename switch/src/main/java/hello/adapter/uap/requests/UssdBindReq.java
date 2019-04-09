package hello.adapter.uap.requests;

import hello.adapter.uap.messages.UssdBind;
import hello.adapter.uap.messages.UssdBindResp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RestController;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

@RestController
public class UssdBindReq {
    @Value("${AccountName}")
    private String testusername;
    @Value("${Password}")
    private String password;
    private Logger logger = LoggerFactory.getLogger(UssdBindReq.class);

    public void performUssdBind(DataOutputStream dataOutputStream,DataInputStream inputStream){
        logger.info("====>"+testusername);
        logger.info("=====>"+password);
        UssdBind ussdBind = new UssdBind(testusername,password);
        byte [] ussdbindreq = ussdBind.encode();
        byte [] resp = new byte[26];
        try{
            //DataOutputStream dataOutputStream = new DataOutputStream(s.getOutputStream());
            //DataInputStream inputStream = new DataInputStream(s.getInputStream());
            dataOutputStream.write(ussdbindreq);
            dataOutputStream.flush();
            //DataInputStream din = inputStream;
            inputStream.readFully(resp);
            logger.info("RAW STRING RESPONSE:::"+new String(resp));
            UssdBindResp ussdBindResp = new UssdBindResp(resp);
            logger.info("account name"+ussdBindResp.getAccountName());
            logger.info("command status"+ussdBindResp.getCommandStatus());
            //dataOutputStream.close();
        }
        catch (IOException e){
            System.out.println(e);
        }
    }
}
