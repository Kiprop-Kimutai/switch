package hello.adapter.uap.responses;

import hello.adapter.uap.messages.GenericUssdResp;
import hello.adapter.uap.messages.UssdBegin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;

import java.io.DataInput;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class Responses {
    DataOutputStream dataOutputStream;
    DataInputStream dataInputStream;
    private Logger logger = LoggerFactory.getLogger(Responses.class);
    public Responses(DataInputStream dataInputStream, DataOutputStream dataOutputStream,String ussdcip,String ussdcport){
        this.dataInputStream = dataInputStream;
        this.dataOutputStream = dataOutputStream;
    }

    public void decodeMessage() {
        byte[]resp = new byte[226];
        try{
            dataInputStream.readFully(resp);
            GenericUssdResp genericUssdResp = new GenericUssdResp(resp);
            logger.info("Generic ussd resp command id:"+genericUssdResp.getCommandID());
            switch (genericUssdResp.getCommandID()) {
                case UssdBegin:
                    //process ussdbegin messages
                    break;
                case UssdContinue:
                    //process ussd continue
                    break;
                case UssdEnd:
                    //process ussd end
                    break;
                case UssdAbort:
                    //process abort
                    break;
                default:
                    break;
            }
        }
        catch (IOException e){
            logger.info(e.getMessage());
        }
    }

    public void  ussbegin(byte [] message) {
        UssdBegin ussdBegin = new UssdBegin(message);
        //build string
        logger.info("-----------USSD BEGIN PARAMS------------");
        logger.info("msidn::+"+ussdBegin.getMsIsdn());
        logger.info("session"+ussdBegin.getSenderCB());
        logger.info("service code::"+ussdBegin.getServiceCode());
        logger.info("ussd string"+ussdBegin.getUssdString());
    }

}
