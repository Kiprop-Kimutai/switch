package hello.adapter.sockets;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class ThreaderTest extends Thread {
    private Logger logger = LoggerFactory.getLogger(ThreaderTest.class);
    private byte[] message;
    private byte[] outputmessage;
    public ThreaderTest(byte[] message,byte[]outputmessage){
        this.message = message;
        this.outputmessage = message;
    }
    private boolean doStop = false;

    public  synchronized void doStop() {
        this.doStop = true;
    }

    private synchronized boolean keepRunning(){
        return this.doStop == false;
    }
    public void run(){
        //while(keepRunning()) {
            try {
                //System.out.println("Thread " + Thread.currentThread().getId() + "is running");
                logger.info("raw string:::"+new String(message));
            } catch (Exception e) {
                System.out.print(e);
            }
        //}
    }

}
