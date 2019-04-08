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

import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 *
 * @author Ghasem Fattahpour
 */
public class UssdBind extends MessageBase {
    private Logger logger = LoggerFactory.getLogger(UssdBind.class);
    private String accountName;
    private String password;
    public UssdBind(String AccountName,String Password) {
        this.CommandID = CommandIDs.UssdBind;
        accountName = AccountName;
        password = Password;
    }

    public String getAccountName() {
        return AccountName;
    }

    public void setAccountName(String AccountName) {
        this.AccountName = AccountName;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }

    @Override
    public byte[] encode() {
        byte[] byteArray = null;
        try {
            ByteArrayOutputStream arrayOutputStream = new ByteArrayOutputStream();

            arrayOutputStream.write(StringUtility.GetBytesFromCOctetString(String.format("%s\0",accountName), 11));
            arrayOutputStream.write(StringUtility.GetBytesFromCOctetString(String.format("%s\0",password), 9));
            arrayOutputStream.write(StringUtility.GetBytesFromCOctetString(this.SystemType, 13));
            arrayOutputStream.write(IntUtility.toByte(this.InterfaceVersion));
            
            return this.generateMessage( arrayOutputStream.toByteArray());
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        } catch (IOException ex) {
            System.out.println(ex);
            //Logger.getLogger(UssdBind.class.getName()).log(Level.SEVERE, null, ex);
        }
        return byteArray;
    }

    public byte[] customencode() {
        byte[] byteArray = null;
        logger.info("account name"+accountName);
        logger.info("password"+password);
        try {
            ByteArrayOutputStream arrayOutputStream = new ByteArrayOutputStream();

            arrayOutputStream.write(StringUtility.GetBytesFromCOctetString(accountName, 11));
            arrayOutputStream.write(StringUtility.GetBytesFromCOctetString(password, 9));
            arrayOutputStream.write(StringUtility.GetBytesFromCOctetString(this.SystemType, 13));;;
            arrayOutputStream.write(IntUtility.toByte(this.InterfaceVersion));;;

            return this.generateMessage( arrayOutputStream.toByteArray());
        } catch (IOException ex) {
            System.out.println(ex);
            //Logger.getLogger(UssdBind.class.getName()).log(Level.SEVERE, null, ex);
        }
        return byteArray;
    }



}
