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
    public UssdBind() {
        this.CommandID = CommandIDs.UssdBind;
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
    public byte[] encode(String username,String password,String command) {
        byte[] byteArray = null;
        try {
            ByteArrayOutputStream arrayOutputStream = new ByteArrayOutputStream();

           /* arrayOutputStream.write(StringUtility.GetBytesFromCOctetString(this.AccountName, 11));
            arrayOutputStream.write(StringUtility.GetBytesFromCOctetString(this.Password, 9));
            arrayOutputStream.write(StringUtility.GetBytesFromCOctetString(this.SystemType, 13));
            arrayOutputStream.write(IntUtility.toByte(this.InterfaceVersion));*/

            arrayOutputStream.write(StringUtility.GetBytesFromCOctetString(username, 11));
            arrayOutputStream.write(StringUtility.GetBytesFromCOctetString(password, 9));
            arrayOutputStream.write(StringUtility.GetBytesFromCOctetString(this.SystemType, 13));
            arrayOutputStream.write(IntUtility.toByte(this.InterfaceVersion));
            
            return this.generateMessage( arrayOutputStream.toByteArray(),command);
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        } catch (IOException ex) {
            System.out.println(ex);
            //Logger.getLogger(UssdBind.class.getName()).log(Level.SEVERE, null, ex);
        }
        logger.info("-------PARSE REQUEST MESSAGE----------");
        return byteArray;
    }



}
