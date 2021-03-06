package hello.adapter.sockets;


import hello.adapter.uap.messages.UssdBind;
import hello.adapter.uap.messages.UssdBindResp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.SocketException;

@RestController
public class Client {

    @Value("${ussdcip}")
    private String ussdcip;
    @Value("${ussdcport}")
    private String ussdcport;
    @Value("${AccountName}")
    private String testusername;
    @Value("${Password}")
    private String password;
    private Logger logger = LoggerFactory.getLogger(Client.class);
    UssdBind ussdBind = new UssdBind();
    UssdBindResp ussdBindResp;
    /*public static void main(String [] args) {
        System.out.println("socket client....");
        UssdBind ussdBind = new UssdBind();
        UssdBindResp ussdBindResp;
        try{
            Socket ss = new Socket("localhost",1992);
            DataOutputStream dout = new DataOutputStream(ss.getOutputStream());
            DataInputStream din = new DataInputStream(ss.getInputStream());
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

            String str = "",str2 = "";
            String accountName = "";
            byte [] resp = new byte[1024];
            int status = 0;
            while(!str.equals("stop")) {
                str = br.readLine();
                //dout.writeUTF(str);
                //dout.writeUTF(new String(ussdBind.encode()));
                dout.write(ussdBind.encode());
                dout.flush();
                status = din.read(resp);
                System.out.println("Read status:"+status);
                System.out.println("Server says: "+new String(resp));
                accountName =  new UssdBindResp(resp).getAccountName();
                System.out.println("AccountName: "+accountName);

            }
            dout.close();
            ss.close();
        }
        catch(Exception e) {
            System.out.println(e);
        }
    }*/

    public void connectToServer() {
        try {
            logger.info("-----ussd bind request-------");
            logger.info(String.format("USSDC HOST ADDRESS=%s    PORT=%s  ", ussdcip, ussdcport));
            logger.info("MS USERNAME:==>"+testusername);
            logger.info("MS PASSWORD:==>"+password);
            String requeststrr = "";
            //String requeststrr = new String(ussdBind.encode(testusername, password,"USSDBIND"));
            UssdBindResp ussdBindResp = new UssdBindResp(ussdBind.encode(testusername, password,"USSDBIND"));
            logger.info("ACCOUNT==>"+ussdBindResp.getAccountName());
            logger.info("PASSWORD==>"+ussdBindResp.getPassword());
            logger.info("COMMAND LENGTH"+ussdBindResp.getCommandLength());
            logger.info("COMMAND ID==>"+ussdBindResp.getCommandID());
            logger.info("SYSTEM TYPE"+ussdBindResp.getSystemType());
            logger.info("RECEIVER CB"+ussdBindResp.getReceiverCB());
            logger.info("SENDER CB"+ussdBindResp.getSenderCB());
            logger.info("BIND RESPONSE:==>"+ussdBindResp.getString());
            //logger.info("----------------RAW STRING REQUEST------------"+requeststrr);
            try {
                    Socket s = new Socket(ussdcip,Integer.parseInt(ussdcport));
                    s.setKeepAlive(true);
                    DataInputStream din = new DataInputStream(s.getInputStream());
                    DataOutputStream dout = new DataOutputStream(s.getOutputStream());
                    String requeststr = "", str2 = "", accountName = "";
                    while(!str2.equals("stop")) {
                        byte[] resp = new byte[1024];
                        requeststr = new String(ussdBind.encode(testusername, password,"USSDBIND"));
                        logger.info("----------------RAW STRING REQUEST------------"+requeststr);
                        dout.write(ussdBind.encode(testusername, password,"USSDBIND"));
                        dout.flush();
                        din.read(resp);
                        str2 = new String(resp);
                        accountName = new UssdBindResp(resp).getAccountName();
                        //UssdBindResp ussdBindResp = new UssdBindResp(resp);
                        logger.info("-------------------RAW STRING RESPONSE-----------"+str2);
                        logger.info("--------------------USSSD BIND RESPONSE--------------------");
                        logger.info("BIND RESPONSE:==>"+ussdBindResp.toString());
                        logger.info("[account name]"+ussdBindResp.getAccountName());
                        logger.info("system type"+ussdBindResp.getSystemType());
                        System.out.println("Server says: "+accountName);
                    }
                    dout.close();
                    s.close();
                }
                catch(SocketException e){
                    System.out.println(e);
                    //e.printStackTrace();
                }

        }
        catch(Exception e) {
            System.out.println(e);
        }
    }
}
//headerse