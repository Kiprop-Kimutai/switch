package hello.adapter.uap.responses;

import hello.adapter.uap.messages.UssdBindResp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UssdBindResponse extends Thread {
    private byte [] message;
    private Logger logger = LoggerFactory.getLogger(UssdBindResponse.class);
    public UssdBindResponse (byte [] message) {
        this.message = message;
    }
    public void run (){
        UssdBindResp ussdBindResp = new UssdBindResp(message);
        logger.info("account name"+ussdBindResp.getAccountName());
        logger.info("command status"+ussdBindResp.getCommandStatus());
    }
}
