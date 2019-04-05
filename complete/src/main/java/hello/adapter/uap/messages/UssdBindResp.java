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
    public UssdBindResp(byte[] message) {
        this.Message = message;
        this.dencode(message);
        customdecode(message);
        this.CommandID = CommandIDs.UssdUnBindResp;
    }

    @Override
    protected boolean dencode(byte [] message) {
        super.dencode(message); //To change body of generated methods, choose Tools | Templates.
        this.AccountName = StringUtility.GetCOctetStringFromBytes(message, 20, 11);
        this.Password = StringUtility.GetCOctetStringFromBytes(message,31,11);
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

    //public String getPassword() {return Password;}

    public CommandIDs getCommandID() {
        return commandID;
    }

    public int getReceiverCB() {
        return receiverCB;
    }

    public int getSenderCB() {
        return receiverCB;
    }

    public String getString() {
        return " MessageBase{" + "CommandLength=" + commandLength + ", CommandID=" + commandID + ", CommandStatus=" + commandStatus + ", SenderCB=" + senderCB + ", ReceiverCB=" + receiverCB + ", AccountName=" + AccountName + ", Password=" + Password + ", SystemType=" + SystemType + ", InterfaceVersion=" + InterfaceVersion + ", UssdVersion=" + UssdVersion + ", UssdOpType=" + UssdOpType + ", MsIsdn=" + MsIsdn + ", ServiceCode=" + ServiceCode + ", CodeScheme=" + CodeScheme + ", UssdString=" + UssdString + ", SwitchMode=" + SwitchMode + ", ChargeRatio=" + ChargeRatio + ", ChargeType=" + ChargeType + ", ChargeSource=" + ChargeSource + ", ChargeLocation=" + ChargeLocation + ", Message=" + Message + '}';
    }
    
    

}
