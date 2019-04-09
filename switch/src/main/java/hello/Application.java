package hello;

import hello.adapter.configs.ResourceConfig;
import hello.adapter.sockets.Server;
import hello.adapter.sockets.Switch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;

import java.util.Properties;
import java.util.Timer;
import java.util.TimerTask;

@SpringBootApplication
public class Application extends SpringBootServletInitializer {
    @Autowired
    Server server;
    @Autowired
    Switch switchboard;
    private Logger logger = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        //System.out.println("application base ###"+ResourceConfig.APPLICATION_PATH);
        SpringApplication.run(Application.class, args);
    }

    static Properties getProperties() {
        Properties props = new Properties();
        props.put("spring.config.location", ResourceConfig.APPLICATION_PATH);
        return props;
    }




    @Bean
    CommandLineRunner run(){
        return (String... args) ->{
            logger.info("Init......");
            byte[] message = {};byte[] outputmessage = {};
            switchboard.connectToServer();
            //server.openSocketConnection();
            //client.connectToServer();
            /*for(int i = 0;i<8;i++) {
                ThreaderTest threaderTest = new ThreaderTest(message,outputmessage);
                threaderTest.start();

            }*/

   /*         Timer timer = new Timer();
            timer.scheduleAtFixedRate(new TimerTask() {
                    @Override
                    public void run() {
                      logger.info("logging.....");
                    }
                },10000,10000);*/
        };
    }


}
