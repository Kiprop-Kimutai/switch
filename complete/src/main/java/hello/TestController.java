package hello;

import java.util.Properties;
import java.util.concurrent.atomic.AtomicLong;

import hello.adapter.sockets.Client;
import hello.adapter.sockets.Server;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
@RestController
public class TestController {
    @Value("${ussdcip}")
    private String ussdcip;
    @Autowired
    private Client client;
    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();
    @RequestMapping("/greeting")
    public Greeting greeting(@RequestParam(value="name", defaultValue="World") String name) {
        System.out.println("Server listening at port ....." +ussdcip);
        client.connectToServer();
        return new Greeting(counter.incrementAndGet(),
                            String.format(template, name));
    }
}
