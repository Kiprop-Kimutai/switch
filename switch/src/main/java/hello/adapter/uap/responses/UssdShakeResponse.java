package hello.adapter.uap.responses;

import hello.adapter.uap.messages.UssdShakeResp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UssdShakeResponse extends Thread {
    private byte[] message;
    private Logger logger = LoggerFactory.getLogger(UssdShakeResp.class);
    public UssdShakeResponse(byte[] message) {
        this.message = message;
    }
    public void run(){
        UssdShakeResp ussdShakeResp = new UssdShakeResp(message);
        logger.info("-----------USSD SHAKE RESPONSE--------------");
        logger.info("COMMAND ID:::"+ussdShakeResp.getCommandID());
        logger.info("COMMAND LENGTH:::"+ussdShakeResp.getCommandLength());
        logger.info("COMMAND ID:::"+ussdShakeResp.getCommandID());
        logger.info("COMMAND STATUS::"+ussdShakeResp.getCommandStatus());
        logger.info("SENDERCB===>"+ussdShakeResp.getSenderCB());
        logger.info("RECEIVERCB==>"+ussdShakeResp.getSenderCB());
    }

}
