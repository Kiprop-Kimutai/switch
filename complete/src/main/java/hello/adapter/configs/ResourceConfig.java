package hello.adapter.configs;

import java.nio.file.FileSystems;
import java.nio.file.Path;

public class ResourceConfig {
    public static final String OTC_API_v1 ="/otc/rest/v1";
    public static final String CATALINA_BASE = System.getProperty("catalina.base");
    public static Path path = FileSystems.getDefault().getPath("").toAbsolutePath();
    public static  final String  APPLICATION_PATH = path.toString();
    //public static  final String  APPLICATION_PATH = "D:\\repo\\gs-rest-service\\complete\\src\\main\\resources\\";
    //public static  final String  APPLICATION_PATH ="C:\\Users\\USER\\Music\\";
}
