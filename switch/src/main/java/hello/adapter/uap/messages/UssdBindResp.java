package hello.adapter.uap.messages;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import hello.adapter.uap.utility.IntUtility;
import hello.adapter.uap.utility.StringUtility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

/**
 *
 * @author Ghasem Fattahpour
 */
public class UssdBindResp extends MessageBase {
    private Logger logger = LoggerFactory.getLogger(UssdBindResp.class);
    int commandLength,commandStatus,senderCB,receiverCB;
    CommandIDs commandID;
    byte [] messagee;
    String AccountName;
    public UssdBindResp(byte[] message) {
        this.Message = message;
        this.dencode();
        this.decode(message);
        customdecode(message);
        this.CommandID = CommandIDs.UssdUnBindResp;
    }

    @Override
    protected boolean dencode() {
        super.dencode(); //To change body of generated methods, choose Tools | Templates.
        //this.AccountName = StringUtility.GetCOctetStringFromBytes(this.Message, 20, 11);
        return true;
        
    }


    protected boolean decode(byte [] message) {
        super.dencode(); //To change body of generated methods, choose Tools | Templates.
        //AccountName = StringUtility.GetCOctetStringFromBytes(message, 20, 11);
        AccountName = StringUtility.GetCOctetStringFromBytes(message, 20, 6);
        return true;

    }
    protected void customdecode(byte [] message){
        commandLength = IntUtility.toInt(Arrays.copyOfRange(message, 0, 4));;
        commandID = CommandIDs.fromInteger(IntUtility.toInt(Arrays.copyOfRange(message, 4, 8)));
        commandStatus = IntUtility.toInt(Arrays.copyOfRange(message, 8, 12));
        senderCB = IntUtility.toInt(Arrays.copyOfRange(message, 12, 16));;
        receiverCB = IntUtility.toInt(Arrays.copyOfRange(message, 16, 20));


        this.CommandLength = IntUtility.toInt(Arrays.copyOfRange(message, 0, 4));
        this.CommandID = CommandIDs.fromInteger(IntUtility.toInt(Arrays.copyOfRange(message, 4, 8)));
        this.CommandStatus = IntUtility.toInt(Arrays.copyOfRange(message, 8, 12));;;
        this.SenderCB = IntUtility.toInt(Arrays.copyOfRange(message, 12, 16));;
        this.ReceiverCB = IntUtility.toInt(Arrays.copyOfRange(message, 16, 20));

        System.out.println();
        logger.info("---------PARSE HEADER PARAMS-------------");
        logger.info("COMMAND LENGTH"+commandLength);
        logger.info("COMMAND ID==>"+commandID);
        logger.info("COMMAND STATUS==>"+commandStatus);
        logger.info("RECEIVER CB"+receiverCB);
        logger.info("SENDER CB"+senderCB);


    }

    public String getAccountName() {
        return AccountName;
    }
    
    

}
