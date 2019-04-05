package hello.adapter.uap.messages;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import hello.adapter.uap.utility.IntUtility;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ghasem Fattahpour
 */
public abstract class MessageBase {
/*    @Value("${AccountName}")
    private String testusername;
    @Value("${Password}")
    private String password;
    @Value("${SystemType}")
    private String systemtype;
    @Value("${InterfaceVersion}")
    private String interfaceVersion;*/
    protected int CommandLength;
    //protected CommandIDs CommandID = CommandIDs.NONE;
    protected CommandIDs CommandID = CommandIDs.UssdBind;
    protected int CommandStatus = 0;
    protected int SenderCB = 0xFFFFFFFF;
    protected int ReceiverCB = 0xFFFFFFFF;
    protected String AccountName = "AccountName";
    protected String Password = "Password";
    protected final String SystemType = "USSD";
    protected final int InterfaceVersion = 0x10;
    protected UssdVersions UssdVersion;
    protected UssdOpTypes UssdOpType;
    protected String MsIsdn;
    protected int ServiceCode;
    protected CodeSchemes CodeScheme;
    protected String UssdString;
    protected int SwitchMode;
    protected int ChargeRatio;
    protected int ChargeType;
    protected String ChargeSource;
    protected int ChargeLocation;
    protected byte[] Message;

    public int getCommandLength() {
        return CommandLength;
    }

    public CommandIDs getCommandID() {
        return CommandID;
    }

    public int getCommandStatus() {
        return CommandStatus;
    }

    public int getSenderCB() {
        return SenderCB;
    }

    public int getReceiverCB() {
        return ReceiverCB;
    }

    public void setSenderCB(int SenderCB) {
        this.SenderCB = SenderCB;
    }

    public void setReceiverCB(int ReceiverCB) {
        this.ReceiverCB = ReceiverCB;
    }

    
    
    public int getInterfaceVersion() {
        return InterfaceVersion;
    }

    public String getSystemType() {
        return SystemType;
    }

    protected byte[] generateMessage() {
        return this.generateMessage(new byte[0],"USSDBIND");
    }

    protected byte[] generateMessage(byte[] body,String command) {
        ByteArrayOutputStream header = new ByteArrayOutputStream();
        try {

            switch(command) {
                case "USSDBIND":
               /* final byte[] commandID = {(byte)0x00,(byte)0x00, (byte)0x00, (byte)0x65};
                final byte[] commandStatus = {(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00};
                final byte[] senderCB = {(byte)0xFF,(byte)0xFF,(byte)0xFF,(byte)0xFF,(byte)0xFF,(byte)0xFF,(byte)0xFF,(byte)0xFF};
                final byte[] receiverCB = {(byte)0xFF,(byte)0xFF,(byte)0xFF,(byte)0xFF,(byte)0xFF,(byte)0xFF,(byte)0xFF,(byte)0xFF};
                header.write(IntUtility.toInt(commandID));
                header.write(IntUtility.toInt(commandStatus));
                header.write(IntUtility.toInt(senderCB));
                header.write(IntUtility.toInt(receiverCB));*/

                int commandID =  0x00000065;
                int commandStatus = 0x00000000;
                int senderCB = 0xFFFFFFFF;
                int receiverCB = 0xFFFFFFFF;
                header.write(IntUtility.toByte(commandID));
                header.write(IntUtility.toByte(commandStatus));
                header.write(IntUtility.toByte(senderCB));
                header.write(IntUtility.toByte(receiverCB));
                break;
                default:
                    break;
            }
            ByteArrayOutputStream message = new ByteArrayOutputStream();
            this.CommandLength = header.toByteArray().length + body.length + 4;
            message.write(IntUtility.toByte(header.toByteArray().length + body.length + 4));
            //System.out.println(header.toByteArray().length + body.length + 4);
            message.write(header.toByteArray());
            message.write(body);
            return message.toByteArray();
        } catch (IOException ex) {
            Logger.getLogger(MessageBase.class.getName()).log(Level.SEVERE, null, ex);
        }
        return header.toByteArray();



    }



    protected byte[] encode(String uname,String pwd,String command) {
        return null;
    }
    protected byte[] encode() {
        return null;
    }
    protected boolean headerDecode(byte[]message) {
        try {
            /*this.CommandLength = IntUtility.toInt(Arrays.copyOfRange(this.Message, 0, 4));
            this.CommandID = CommandIDs.fromInteger(IntUtility.toInt(Arrays.copyOfRange(this.Message, 4, 8)));
            this.CommandStatus = IntUtility.toInt(Arrays.copyOfRange(this.Message, 8, 12));
            this.SenderCB = IntUtility.toInt(Arrays.copyOfRange(this.Message, 12, 16));
            this.ReceiverCB = IntUtility.toInt(Arrays.copyOfRange(this.Message, 16, 20));*/

            this.CommandLength = IntUtility.toInt(Arrays.copyOfRange(message, 0, 4));
            this.CommandID = CommandIDs.fromInteger(IntUtility.toInt(Arrays.copyOfRange(message, 4, 8)));
            this.CommandStatus = IntUtility.toInt(Arrays.copyOfRange(message, 8, 12));
            this.SenderCB = IntUtility.toInt(Arrays.copyOfRange(message, 12, 16));
            this.ReceiverCB = IntUtility.toInt(Arrays.copyOfRange(message, 16, 20));
            return true;
        } catch (Exception e) {
            return false;
        }

    }

    protected boolean dencode(byte[]message) {
        if (this.headerDecode(message)) {
            return true;
        } else {
            return false;
        }
    }

    public void setCommandID(CommandIDs CommandID) {
        this.CommandID = CommandID;
    }

    
    
    @Override
    public String toString() {
        return "MessageBase{" + "CommandLength=" + this.CommandLength + ", CommandID=" + this.CommandID + ", CommandStatus=" + this.CommandStatus + ", SenderCB=" + SenderCB + ", ReceiverCB=" + ReceiverCB + ", AccountName=" + AccountName + ", Password=" + Password + ", SystemType=" + SystemType + ", InterfaceVersion=" + InterfaceVersion + ", UssdVersion=" + UssdVersion + ", UssdOpType=" + UssdOpType + ", MsIsdn=" + MsIsdn + ", ServiceCode=" + ServiceCode + ", CodeScheme=" + CodeScheme + ", UssdString=" + UssdString + ", SwitchMode=" + SwitchMode + ", ChargeRatio=" + ChargeRatio + ", ChargeType=" + ChargeType + ", ChargeSource=" + ChargeSource + ", ChargeLocation=" + ChargeLocation + ", Message=" + Message + '}';
    }
    
    
}
