package hello.adapter.uap.requests;

import hello.adapter.sockets.Switch;
import hello.adapter.uap.messages.UssdBind;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;

import java.util.Timer;
import java.util.TimerTask;

@Controller
public class BindThread extends Thread {
    @Value("${ussdcip}")
    private String ussdcip;
    @Value("${ussdcport}")
    private String ussdcport;
    @Value("${AccountName}")
    private String testusername;
    @Value("${Password}")
    private String password;
    @Autowired
    Switch switchboard;

    public void run(){
        UssdBind ussdBind = new UssdBind(testusername,password);
        byte [] ussdbindreq = ussdBind.encode();
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                switchboard.writeMessage(ussdbindreq);
            }
        },0,5000);
    }

}
