package hello;

import hello.adapter.configs.ResourceConfig;
import hello.adapter.sockets.Client;
import hello.adapter.sockets.Server;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.flyway.FlywayAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.core.env.Environment;

import java.util.Properties;

@SpringBootApplication
public class Application extends SpringBootServletInitializer {
    @Autowired
    Server server;
    @Autowired
    Client client;
    private Logger logger = LoggerFactory.getLogger(Application.class);
    /*@Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(Application.class).properties(getProperties());
    }*/
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
            //server.openSocketConnection();
            client.connectToServer();
        };
    }


}
